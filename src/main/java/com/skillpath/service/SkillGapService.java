
package com.skillpath.service;

import com.skillpath.dto.SkillGapResponse;
import com.skillpath.entity.Student;
import com.skillpath.entity.StudentSkill;
import com.skillpath.repository.StudentRepository;
import com.skillpath.repository.StudentSkillRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class SkillGapService {

    private final StudentRepository studentRepository;
    private final StudentSkillRepository studentSkillRepository;

    public SkillGapService(
            StudentRepository studentRepository,
            StudentSkillRepository studentSkillRepository) {

        this.studentRepository = studentRepository;
        this.studentSkillRepository = studentSkillRepository;
    }

    public SkillGapResponse analyzeSkillGap(Long studentId) {

        // Find the student
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student not found with ID: " + studentId));

        // Check whether the student selected a career
        String targetCareer = student.getTargetCareer();

        if (targetCareer == null || targetCareer.isBlank()) {
            throw new RuntimeException(
                    "Please select a target career first.");
        }

        // Get the student's saved skills
        List<StudentSkill> studentSkills =
                studentSkillRepository.findByStudentId(studentId);

        // Get required skills for the selected career
        List<String> requiredSkills =
                getRequiredSkills(targetCareer);

        List<String> matchedSkills = new ArrayList<>();
        List<String> missingSkills = new ArrayList<>();

        // Compare the student's skills with required skills
        for (String requiredSkill : requiredSkills) {

            boolean found = false;

            for (StudentSkill studentSkill : studentSkills) {

                String savedSkill = studentSkill.getSkillName();

                if (savedSkill != null
                        && savedSkill.trim().equalsIgnoreCase(
                        requiredSkill)) {

                    found = true;
                    break;
                }
            }

            if (found) {
                matchedSkills.add(requiredSkill);
            } else {
                missingSkills.add(requiredSkill);
            }
        }

        int totalRequiredSkills = requiredSkills.size();
        int matchedCount = matchedSkills.size();
        int missingCount = missingSkills.size();

        double readinessPercentage = totalRequiredSkills == 0
                ? 0.0
                : (matchedCount * 100.0) / totalRequiredSkills;

        // Create the response
        return new SkillGapResponse(
                student.getId(),
                student.getName(),
                targetCareer,
                totalRequiredSkills,
                matchedCount,
                missingCount,
                Math.round(readinessPercentage * 100.0) / 100.0,
                matchedSkills,
                missingSkills
        );
    }

    private List<String> getRequiredSkills(String targetCareer) {

        String career = targetCareer.trim()
                .toLowerCase(Locale.ROOT);

        switch (career) {

            case "software developer":
            case "software engineer":
                return Arrays.asList(
                        "Java",
                        "Data Structures",
                        "SQL",
                        "Git",
                        "Problem Solving"
                );

            case "web developer":
                return Arrays.asList(
                        "HTML",
                        "CSS",
                        "JavaScript",
                        "SQL",
                        "Git"
                );

            case "data analyst":
                return Arrays.asList(
                        "SQL",
                        "Python",
                        "Excel",
                        "Statistics",
                        "Data Visualization"
                );

            case "java developer":
                return Arrays.asList(
                        "Java",
                        "OOP",
                        "SQL",
                        "Spring Boot",
                        "Git"
                );

            default:
                throw new RuntimeException(
                        "No required skills configured for career: "
                                + targetCareer);
        }
    }
}
