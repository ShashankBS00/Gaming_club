package com.SBS.GamingClub.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.SBS.GamingClub.Entitis.Member;
import com.SBS.GamingClub.services.MemberService;
import com.SBS.GamingClub.dto.MemberDto;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "*")
public class MemberControler {

    @Autowired
    private MemberService memberService;

    @PostMapping("/create")
    public ResponseEntity<Member> createMember(@RequestBody MemberDto memberDto) {
        Member savedMember = memberService.saveMember(memberDto);
        if (savedMember != null) {
            return ResponseEntity.ok(savedMember);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable int id) {
        Optional<Member> member = memberService.getMemberById(id);
        return member.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable int id, @RequestBody MemberDto memberDto) {
        Member updatedMember = memberService.updateMember(id, memberDto);
        if (updatedMember != null) {
            return ResponseEntity.ok(updatedMember);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable int id) {
        boolean deleted = memberService.deleteMember(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/recharge")
    public ResponseEntity<Member> rechargeMemberBalance(
            @PathVariable int id,
            @RequestParam float amount) {
        Member updatedMember = memberService.rechargeMemberBalance(id, amount);
        if (updatedMember != null) {
            return ResponseEntity.ok(updatedMember);
        }
        return ResponseEntity.notFound().build();
    }
}
