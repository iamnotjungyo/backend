package com.likelion14.PBL_Spring.member.domain.role;

import com.likelion14.PBL_Spring.member.domain.policy.AlumniSubmissionPolicy;
import com.likelion14.PBL_Spring.member.domain.policy.SubmissionPolicy;

public class Alumni extends Role {
    private String work;

    public Alumni(String name, String major, int generation, String part, String work) {
        super(name, major, generation, part);
        this.work = work;
    }

    @Override
    public SubmissionPolicy submissionPolicy() { return new AlumniSubmissionPolicy(); }

    @Override
    public String getInfo() {
        return "이름: " + getName() + " | 전공: " + getMajor() + " | 기수: " + getGeneration()
                + " | 파트: " + getPart() + "\n현재 직무: " + work;
    }

    @Override
    public String roleName() { return "수료생"; }

}
