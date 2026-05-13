package com.likelion14.PBL_Spring.member.domain.policy;

public class GenerationPolicy implements SubmissionPolicy {

    public int minGeneration;

    public GenerationPolicy(int minGeneration) {
        this.minGeneration = minGeneration;
    }

    @Override
    public Boolean canSubmit() {
        return false;
    }

    public Boolean canSubmit(int generation) {
        return generation >= minGeneration;
    }

    public int getMinGeneration() {
        return minGeneration;
    }

}
