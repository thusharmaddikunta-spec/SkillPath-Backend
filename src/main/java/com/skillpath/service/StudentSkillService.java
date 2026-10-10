
package com.skillpath.service;

import com.skillpath.entity.Student;
import com.skillpath.entity.StudentSkill;
import com.skillpath.repository.StudentRepository;
import com.skillpath.repository.StudentSkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentSkillService {

    private final StudentSkillRepository studentSkillRepository;
    private final StudentRepository studentRepository;

    public StudentSkillService(
            StudentSkillRepository studentSkillRepository,
            StudentRepository studentRepository) {
        this.studentSkillRepository = studentSkillRepository;
        this.studentRepository = studentRepository;
    }

    public List<StudentSkill> getSkillsByStudentId(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new RuntimeException(
                    "Student not found with ID: " + studentId);
        }

        return studentSkillRepository.findByStudentId(studentId);
    }

    public StudentSkill addSkill(Long studentId, StudentSkill studentSkill) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student not found with ID: " + studentId));

        boolean alreadyExists =
                studentSkillRepository
                        .existsByStudentIdAndSkillNameIgnoreCase(
                                studentId,
                                studentSkill.getSkillName());

        if (alreadyExists) {
            throw new RuntimeException(
                    "This skill is already added for this student.");
        }

        studentSkill.setStudent(student);

        return studentSkillRepository.save(studentSkill);
    }

    public StudentSkill updateSkill(
            Long studentId,
            Long skillId,
            StudentSkill updatedSkill) {

        StudentSkill existingSkill =
                studentSkillRepository.findById(skillId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Skill not found with ID: " + skillId));

        if (!existingSkill.getStudent().getId().equals(studentId)) {
            throw new RuntimeException(
                    "This skill does not belong to the selected student.");
        }

        existingSkill.setSkillName(updatedSkill.getSkillName());
        existingSkill.setProficiencyLevel(
                updatedSkill.getProficiencyLevel());

        return studentSkillRepository.save(existingSkill);
    }

    public void deleteSkill(Long studentId, Long skillId) {
        StudentSkill existingSkill =
                studentSkillRepository.findById(skillId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Skill not found with ID: " + skillId));

        if (!existingSkill.getStudent().getId().equals(studentId)) {
            throw new RuntimeException(
                    "This skill does not belong to the selected student.");
        }

        studentSkillRepository.delete(existingSkill);
    }
}
