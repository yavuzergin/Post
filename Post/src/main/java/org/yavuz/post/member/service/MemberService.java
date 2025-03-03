package org.yavuz.post.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yavuz.post.exception.ResourceNotFoundException;
import org.yavuz.post.member.model.Member;
import org.yavuz.post.member.repository.MemberRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getMembers(){
        return memberRepository.findAll();
    }
    public Member addMember(Member member) {
        return memberRepository.save(member);
    }
    public Member getMember(Long id){
       return memberRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException(id + "numaralı üye bulunamadı."));
    }
    public Member updateMember(Long id, Member memberDetails){
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + "numaralı üye bulunamadı."));
        member.setMemberFirstName(memberDetails.getMemberFirstName());
        member.setMemberLastName(memberDetails.getMemberLastName());
        return memberRepository.save(member);
    }
    public Map<String, Boolean> deleteMember(Long id){
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + "numaralı üye bulunamadı."));
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }
}
