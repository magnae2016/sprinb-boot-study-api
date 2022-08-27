package com.growth.api.service;

import com.growth.api.dto.DeveloperDto;
import com.growth.api.repository.DeveloperRepository;
import com.growth.api.type.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DMakerService {
    private final DeveloperRepository developerRepository;

    public List<DeveloperDto> getAllEmployedDevelopers() {
        return developerRepository.findDevelopersByStatusCodeEquals(StatusCode.EMPLOYED).stream().map(DeveloperDto::fromEntity).collect(Collectors.toList());
    }
}
