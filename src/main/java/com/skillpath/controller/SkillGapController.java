
package com.skillpath.controller;

import com.skillpath.dto.SkillGapResponse;
import com.skillpath.service.SkillGapService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/skill-gap")
public class SkillGapController {

    private final SkillGapService skillGapService;

    public SkillGapController(SkillGapService skillGapService) {
        this.skillGapService = skillGapService;
    }

    @GetMapping("/{studentId}")
    public SkillGapResponse analyzeSkillGap(
            @PathVariable Long studentId) {

        return skillGapService.analyzeSkillGap(studentId);
    }
}
