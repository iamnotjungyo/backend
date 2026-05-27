package com.likelion14.PBL_Spring.member.controller;

import com.likelion14.PBL_Spring.member.domain.Member;
import com.likelion14.PBL_Spring.member.dto.*;
import com.likelion14.PBL_Spring.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/lions")
    public ResponseEntity<MemberResponse> createLion(@RequestBody LionCreateRequest request) {
        Member member = memberService.createLion(request);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(MemberResponse.from(member));
    }

    @PostMapping("/staffs")
    public ResponseEntity<MemberResponse> createStaff(@RequestBody StaffCreateRequest request) {
        Member member = memberService.createStaff(request);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(MemberResponse.from(member));
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers() {
        List<MemberResponse> responses = memberService.getAllMembers().stream()
                .map(MemberResponse::from)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable Long id) {
        Member member = memberService.findById(id);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(MemberResponse.from(member));
    }

    @PutMapping("/lions/{id}")
    public ResponseEntity<MemberResponse> updateLion(@PathVariable Long id,
                                                     @RequestBody LionUpdateRequest request) {
        Member updated = memberService.updateLion(id, request);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(MemberResponse.from(updated));
    }

    @PutMapping("/staffs/{id}")
    public ResponseEntity<MemberResponse> updateStaff(@PathVariable Long id,
                                                      @RequestBody StaffUpdateRequest request) {
        Member updated = memberService.updateStaff(id, request);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(MemberResponse.from(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        boolean success = memberService.deleteMember(id);
        if (!success) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
