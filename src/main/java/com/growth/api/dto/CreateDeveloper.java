package com.growth.api.dto;

import com.growth.api.entity.Developer;
import com.growth.api.type.DeveloperLevel;
import com.growth.api.type.DeveloperSkill;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateDeveloper {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    public static class Request {
        @NotNull
        private DeveloperLevel developerLevel;
        @NotNull
        private DeveloperSkill developerSkill;
        @NotNull
        @Min(0)
        @Max(20)
        private Integer experienceYears;

        @NotNull
        @Size(min = 3, max = 50, message = "memberId size must 3 ~ 50")
        private String memberId;
        @NotNull
        @Size(min = 3, max = 20, message = "name size must 3 ~ 20")
        private String name;
        @Min(18)
        private Integer age;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    public static class Response {
        private DeveloperLevel developerLevel;
        private DeveloperSkill developerSkill;
        private Integer experienceYears;
        private String memberId;

        public static Response fromEntity(Developer developer) {
            return Response.builder()
                    .developerLevel(developer.getDeveloperLevel())
                    .developerSkill(developer.getDeveloperSkill())
                    .experienceYears(developer.getExperienceYears())
                    .memberId(developer.getMemberId())
                    .build();
        }
    }
}
