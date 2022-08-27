package com.growth.api.controller;

import com.growth.api.dto.CreateDeveloper;
import com.growth.api.dto.DeveloperDetailDto;
import com.growth.api.dto.DeveloperDto;
import com.growth.api.service.DMakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DMakerController {
    private final DMakerService dMakerService;

    @GetMapping("/developers")
    public List<DeveloperDto> getAllDevelopers() {
        return dMakerService.getAllEmployedDevelopers();
    }

    @GetMapping("/developers/{memberId}")
    public DeveloperDetailDto getDeveloperDetail(@PathVariable String memberId) {
        return dMakerService.getDeveloperDetail(memberId);
    }

    @PostMapping("/create-developers")
    public CreateDeveloper.Response createDevelopers(
            @Valid @RequestBody CreateDeveloper.Request request) {
        return dMakerService.createDeveloper(request);
    }
}
