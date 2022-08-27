package com.growth.api.service;

import com.growth.api.dto.CreateDeveloper;
import com.growth.api.dto.DeveloperDetailDto;
import com.growth.api.dto.DeveloperDto;
import com.growth.api.dto.EditDeveloper;
import com.growth.api.entity.Developer;
import com.growth.api.entity.RetiredDeveloper;
import com.growth.api.exception.DMakerErrorCode;
import com.growth.api.exception.DMakerException;
import com.growth.api.repository.DeveloperRepository;
import com.growth.api.repository.RetiredDeveloperRepository;
import com.growth.api.type.DeveloperLevel;
import com.growth.api.type.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DMakerService {
    private final DeveloperRepository developerRepository;
    private final RetiredDeveloperRepository retiredDeveloperRepository;

    public List<DeveloperDto> getAllEmployedDevelopers() {
        return developerRepository.findDevelopersByStatusCodeEquals(StatusCode.EMPLOYED).stream().map(DeveloperDto::fromEntity).collect(Collectors.toList());
    }

    public CreateDeveloper.Response createDeveloper(CreateDeveloper.Request request) {
        DeveloperLevel developerLevel = request.getDeveloperLevel();
        Integer experienceYears = request.getExperienceYears();
        validateDeveloperLevel(developerLevel, experienceYears);

        Developer developer = Developer.builder()
                .developerLevel(request.getDeveloperLevel())
                .developerSkill(request.getDeveloperSkill())
                .experienceYears(request.getExperienceYears())
                .memberId(request.getMemberId())
                .name(request.getName())
                .age(request.getAge())
                .statusCode(StatusCode.EMPLOYED)
                .build();
        developerRepository.save(developer);
        return CreateDeveloper.Response.fromEntity(developer);
    }

    private static void validateDeveloperLevel(DeveloperLevel developerLevel,
                                  Integer experienceYears) {
        if (developerLevel == DeveloperLevel.SENIOR && experienceYears < 10) {
            throw new DMakerException(DMakerErrorCode.LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }

        if (developerLevel == DeveloperLevel.JUNGNIOR && (experienceYears < 4 || experienceYears > 10)) {
            throw new DMakerException(DMakerErrorCode.LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }

        if (developerLevel == DeveloperLevel.JUNIOR && experienceYears > 4) {
            throw new DMakerException(DMakerErrorCode.LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
    }

    public DeveloperDetailDto getDeveloperDetail(String memberId) {
        return developerRepository.findByMemberId(memberId).map(DeveloperDetailDto::fromEntity).orElseThrow(() -> new DMakerException(DMakerErrorCode.NO_DEVELOPER));
    }

    @Transactional
    public DeveloperDetailDto editDeveloper(String memberId, EditDeveloper.Request request) {
        validateEditDeveloperRequest(memberId, request);
        Developer developer =
                developerRepository.findByMemberId(memberId).orElseThrow(() -> new DMakerException(DMakerErrorCode.NO_DEVELOPER));
        developer.setDeveloperLevel(request.getDeveloperLevel());
        developer.setDeveloperSkill(request.getDeveloperSkill());
        developer.setExperienceYears(request.getExperienceYears());

        return DeveloperDetailDto.fromEntity(developer);
    }

    private void validateEditDeveloperRequest(String memberId, EditDeveloper.Request request) {
        validateDeveloperLevel(request.getDeveloperLevel(),
                request.getExperienceYears());
        developerRepository.findByMemberId(memberId).orElseThrow(() -> new DMakerException(DMakerErrorCode.NO_DEVELOPER));
    }

    @Transactional
    public DeveloperDetailDto deleteDeveloper(String memberId) {
        Developer developer =
                developerRepository.findByMemberId(memberId).orElseThrow(() -> new DMakerException(DMakerErrorCode.NO_DEVELOPER));
        developer.setStatusCode(StatusCode.RETIRED);

        RetiredDeveloper retiredDeveloper = RetiredDeveloper.builder()
                .memberId(developer.getMemberId())
                .name(developer.getName())
                .build();

        retiredDeveloperRepository.save(retiredDeveloper);
        return DeveloperDetailDto.fromEntity(developer);
    }
}
