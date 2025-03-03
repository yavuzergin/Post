package org.yavuz.post.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yavuz.post.member.model.Member;
import org.yavuz.post.member.repository.MemberRepository;
import org.yavuz.post.member.service.MemberService;

import java.util.List;
import java.util.Map;

@CrossOrigin("https://localhost:4200")
@RestController
@RequestMapping("/user-api/")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/get-member/{id}")
    public ResponseEntity<Member> getUser(@PathVariable Long id) {
        Member member = memberService.getMember(id);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/get-all-members")
    public List<Member> getAllMembers() {
        return memberService.getMembers();
    }

    @PostMapping("/update-member/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member memberDetails) {
        Member updatedMember = memberService.updateMember(id, memberDetails);
        return ResponseEntity.ok(updatedMember);
    }

    @DeleteMapping("/delete-member/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteMember(@PathVariable Long id) {
        Map<String, Boolean> response = memberService.deleteMember(id);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/add-member")
    public Member addMember(@RequestBody Member member){
        return memberService.addMember(member);
    }
}

