package com.likelion14.PBL_Spring.member.domain.role;

import com.likelion14.PBL_Spring.member.domain.policy.LionSubmissionPolicy;
import com.likelion14.PBL_Spring.member.domain.policy.SubmissionPolicy;

public class Lion extends Role {
    private String studentId;

    public Lion(String name, String major, int generation, String part, String student_id) {
        super(name, major, generation, part);
        this.studentId = student_id;
    }

    @Override
    public SubmissionPolicy submissionPolicy() {
        return new LionSubmissionPolicy();
    }

    @Override
    public String getInfo() {
        return "이름: " + getName() + " | 전공: " + getMajor() + " | 기수: " + getGeneration()
                + " | 파트: " + getPart() + "\n학번: " + studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    @Override
    public String roleName() { return "아기사자"; }
}
