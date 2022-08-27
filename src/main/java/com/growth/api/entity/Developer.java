package com.growth.api.entity;

import com.growth.api.type.DeveloperLevel;
import com.growth.api.type.DeveloperSkill;
import com.growth.api.type.StatusCode;
import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Developer {
    protected Long id;
    private DeveloperLevel developerLevel;
    private DeveloperSkill developerSkill;

    private Integer experienceYears;
    private String memberId;
    private String name;
    private Integer age;

    private StatusCode statusCode;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
