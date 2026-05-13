package com.likelion14.PBL_Spring.member.service;

import com.likelion14.PBL_Spring.member.domain.role.Role;
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

    public boolean register(Role member) {
        if (repository.existsByName(member.getName())) {
            return false;
        }
        repository.save(member);
        return true;
    }
    public Role searchByName(String name) { return repository.findByName(name); }

    public List<Role> getAllMembers() { return repository.findAll(); }

    public boolean isEmpty() { return repository.findAll().isEmpty(); }
}
