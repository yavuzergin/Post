package org.yavuz.post.member.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yavuz.post.member.model.Member;
import org.yavuz.post.member.repository.MemberRepository;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;
    @InjectMocks
    private MemberService memberService;

    private static Member testMember() {
        Member member = new Member();
        member.setId(1L);
        member.setMemberFirstName("Hüseyin");
        member.setMemberLastName("Su");
        return member;
    }
    private static Member testMember2() {
        Member member = new Member();
        member.setId(2L);
        member.setMemberFirstName("Ahmet");
        member.setMemberLastName("Su");
        return member;
    }
    @Test
    void getMembers() {
        Member member = testMember();
        Member member2 = testMember2();
        when(memberRepository.findAll()).thenReturn(Arrays.asList(member, member2));
        List<Member> result = memberService.getMembers();
        assertEquals(2, result.size());
        assertEquals("Hüseyin", result.get(0).getMemberFirstName());
        assertEquals("Ahmet", result.get(1).getMemberFirstName());

    }

    @Test
    void addMember() {
        Member member = testMember();
        when(memberRepository.save(member)).thenReturn(member);
        Member result = memberService.addMember(member);
        assertEquals(member, result);
        verify(memberRepository).save(member);
    }

    @Test
    void getMember() {
        Member member = testMember();
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        Member result = memberService.getMember(1L);
        assertEquals(member, result);
    }

    @Test
    void updateMember() {
        Member member = testMember();
        Member memberDetails = new Member();
        memberDetails.setId(1L);
        memberDetails.setMemberFirstName("Rıza");
        memberDetails.setMemberLastName("Su");
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(memberRepository.save(member)).thenReturn(member);
        Member updatedMember = memberService.updateMember(1L, memberDetails);
        assertEquals("Rıza", updatedMember.getMemberFirstName());
        verify(memberRepository, times(1)).save(member);
    }

    @Test
    void deleteMember() {
        Member member = testMember();
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        memberService.deleteMember(1L);
    }
}