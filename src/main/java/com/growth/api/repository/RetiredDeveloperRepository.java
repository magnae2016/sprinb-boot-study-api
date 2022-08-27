package com.growth.api.repository;

import com.growth.api.entity.Developer;
import com.growth.api.entity.RetiredDeveloper;
import com.growth.api.type.StatusCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RetiredDeveloperRepository extends JpaRepository<RetiredDeveloper,
        Long> {
}
