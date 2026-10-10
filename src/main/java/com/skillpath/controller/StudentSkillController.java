
package com.skillpath.controller;

import com.skillpath.entity.StudentSkill;
import com.skillpath.service.StudentSkillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students/{studentId}/skills")
public class StudentSkillController {

    private final StudentSkillService studentSkillService;

    public StudentSkillController(StudentSkillService studentSkillService) {
        this.studentSkillService = studentSkillService;
    }

    @GetMapping
    public List<StudentSkill> getSkills(@PathVariable Long studentId) {
        return studentSkillService.getSkillsByStudentId(studentId);
    }

    @PostMapping
    public StudentSkill addSkill(
            @PathVariable Long studentId,
            @RequestBody StudentSkill studentSkill) {
        return studentSkillService.addSkill(studentId, studentSkill);
    }

    @PutMapping("/{skillId}")
    public StudentSkill updateSkill(
            @PathVariable Long studentId,
            @PathVariable Long skillId,
            @RequestBody StudentSkill updatedSkill) {
        return studentSkillService.updateSkill(
                studentId, skillId, updatedSkill);
    }

    @DeleteMapping("/{skillId}")
    public String deleteSkill(
            @PathVariable Long studentId,
            @PathVariable Long skillId) {
        studentSkillService.deleteSkill(studentId, skillId);
        return "Skill deleted successfully!";
    }
}
