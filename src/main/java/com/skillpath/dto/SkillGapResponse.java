
package com.skillpath.dto;

import java.util.List;

public class SkillGapResponse {

    private Long studentId;
    private String studentName;
    private String targetCareer;
    private int totalRequiredSkills;
    private int matchedSkills;
    private int missingSkills;
    private double readinessPercentage;
    private List<String> matchedSkillNames;
    private List<String> missingSkillNames;

    // Default constructor
    public SkillGapResponse() {
    }

    // Parameterized constructor
    public SkillGapResponse(
            Long studentId,
            String studentName,
            String targetCareer,
            int totalRequiredSkills,
            int matchedSkills,
            int missingSkills,
            double readinessPercentage,
            List<String> matchedSkillNames,
            List<String> missingSkillNames) {

        this.studentId = studentId;
        this.studentName = studentName;
        this.targetCareer = targetCareer;
        this.totalRequiredSkills = totalRequiredSkills;
        this.matchedSkills = matchedSkills;
        this.missingSkills = missingSkills;
        this.readinessPercentage = readinessPercentage;
        this.matchedSkillNames = matchedSkillNames;
        this.missingSkillNames = missingSkillNames;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTargetCareer() {
        return targetCareer;
    }

    public void setTargetCareer(String targetCareer) {
        this.targetCareer = targetCareer;
    }

    public int getTotalRequiredSkills() {
        return totalRequiredSkills;
    }

    public void setTotalRequiredSkills(int totalRequiredSkills) {
        this.totalRequiredSkills = totalRequiredSkills;
    }

    public int getMatchedSkills() {
        return matchedSkills;
    }

    public void setMatchedSkills(int matchedSkills) {
        this.matchedSkills = matchedSkills;
    }

    public int getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(int missingSkills) {
        this.missingSkills = missingSkills;
    }

    public double getReadinessPercentage() {
        return readinessPercentage;
    }

    public void setReadinessPercentage(double readinessPercentage) {
        this.readinessPercentage = readinessPercentage;
    }

    public List<String> getMatchedSkillNames() {
        return matchedSkillNames;
    }

    public void setMatchedSkillNames(List<String> matchedSkillNames) {
        this.matchedSkillNames = matchedSkillNames;
    }

    public List<String> getMissingSkillNames() {
        return missingSkillNames;
    }

    public void setMissingSkillNames(List<String> missingSkillNames) {
        this.missingSkillNames = missingSkillNames;
    }
}
