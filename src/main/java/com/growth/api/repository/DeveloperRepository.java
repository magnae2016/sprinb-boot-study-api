package com.growth.api.repository;

import com.growth.api.entity.Developer;
import com.growth.api.type.StatusCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    List<Developer> findDevelopersByStatusCodeEquals(StatusCode statusCode);
}
