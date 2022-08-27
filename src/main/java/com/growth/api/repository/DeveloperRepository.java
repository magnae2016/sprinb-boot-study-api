package com.growth.api.repository;

import com.growth.api.entity.Developer;
import com.growth.api.type.StatusCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    Optional<Developer> findByMemberId(String memberId);
    List<Developer> findDevelopersByStatusCodeEquals(StatusCode statusCode);
}
