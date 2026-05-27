package com.likelion14.PBL_Spring.member.service;

import com.likelion14.PBL_Spring.member.domain.Member;
import com.likelion14.PBL_Spring.member.domain.RoleType;
import com.likelion14.PBL_Spring.member.dto.LionCreateRequest;
import com.likelion14.PBL_Spring.member.dto.LionUpdateRequest;
import com.likelion14.PBL_Spring.member.dto.StaffCreateRequest;
import com.likelion14.PBL_Spring.member.dto.StaffUpdateRequest;
import com.likelion14.PBL_Spring.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository repository;

    @Autowired
    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    // 이름으로 검색
    public Member searchByName(String name) {
        return (Member) repository.findByName(name).orElse(null);
   }
    // 전체 조회
    public List<Member> getAllMembers() {
        return repository.findAll();
    }

    // ID로 조회
    public Member findById(Long id) {
        return (Member) repository.findById(id).orElse(null);
    }

    // 삭제
    public boolean deleteMember(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    // Lion 등록
    public Member createLion(LionCreateRequest request) {
        if (repository.existsByName(request.getName())) {
            return null;
        }
        Member member = Member.builder()
                .name(request.getName())
                .major(request.getMajor())
                .generation(request.getGeneration())
                .part(request.getPart())
                .roleType(RoleType.LION)
                .build();

        return repository.save(member);
    }

    // Staff 등록
    public Member createStaff(StaffCreateRequest request) {
        if (repository.existsByName(request.getName())) {
            return null;
        }
        Member member = new Member(request.getName(), request.getMajor(), request.getGeneration(),
                request.getPart(), RoleType.LION, null, request.getPosition());
        return repository.save(member);
    }

    // Lion 수정
    public Member updateLion(Long id, LionUpdateRequest request) {
        Member member = (Member) repository.findById(id).orElse((null));
        if (member == null) {
            return null;
        }
        member.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        member.updatePosition(request.getStudentId());
        return repository.save(member);
    }

    // Staff 수정
    public Member updateStaff(Long id, StaffUpdateRequest request) {
        Member member = (Member) repository.findById(id).orElse((null));
        if (member == null) {
            return null;
        }
        member.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        member.updatePosition(request.getPosition());
        return repository.save(member);
    }
}
