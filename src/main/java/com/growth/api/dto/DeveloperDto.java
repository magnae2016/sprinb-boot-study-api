package com.growth.api.dto;

import com.growth.api.entity.Developer;
import com.growth.api.type.DeveloperLevel;
import com.growth.api.type.DeveloperSkill;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeveloperDto {
    private DeveloperLevel developerLevel;
    private DeveloperSkill developerSkill;
    private String memberId;

    public static DeveloperDto fromEntity(Developer developer) {
        return DeveloperDto.builder()
                .developerLevel(developer.getDeveloperLevel())
                .developerSkill(developer.getDeveloperSkill())
                .memberId(developer.getMemberId())
                .build();
    }
}
