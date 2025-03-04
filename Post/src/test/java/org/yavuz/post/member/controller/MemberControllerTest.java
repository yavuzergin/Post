package org.yavuz.post.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.yavuz.post.member.service.MemberService;
import org.yavuz.post.member.model.Member;
import org.yavuz.post.member.repository.MemberRepository;
import org.yavuz.post.member.controller.MemberController;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.yavuz.post.member.model.Member;
import org.yavuz.post.member.service.MemberService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;
    @MockBean
    private MemberRepository memberRepository;

    @Test
    void testGetMember() throws Exception {
        Member member = new Member(1L, "Rıza", "Baba");
        when(memberService.getMember(1L)).thenReturn(member);
        mockMvc.perform(get("/member-api/get-member/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.memberFirstName").value("Rıza"))
                .andExpect(jsonPath("$.memberLastName").value("Baba"));
    }

    @Test
    void testGetMembers() throws Exception {
        List<Member> members = Arrays.asList(
        new Member(1L, "Rıza", "Baba"),
        new Member(2L, "Münür", "Su"));
        when(memberService.getMembers()).thenReturn(members);
        mockMvc.perform(get("/member-api/get-all-members"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].memberFirstName").value("Rıza"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].memberLastName").value("Su"));
    }

    @Test
    void testAddMember() throws Exception {
        Member member = new Member(1L, "Rıza", "Baba");
        String requestJson = new ObjectMapper().writeValueAsString(member);
        when(memberService.addMember(any(Member.class))).thenReturn(member);
        mockMvc.perform(post("/member-api/add-member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.memberFirstName").value("Rıza"))
                .andExpect(jsonPath("$.memberLastName").value("Baba"));
    }
    @Test
    void testUpdateMember() throws Exception{
        Member member = new Member(1L, "Rıza", "Baba");
        Member memberDetails = new Member (1L, "Hakan", "Haktan");
        String requestJson = new ObjectMapper().writeValueAsString(member);
        when(memberService.updateMember(eq(1L), any(Member.class))).thenReturn(memberDetails);
        mockMvc.perform(put("/member-api/update-member/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.memberFirstName").value("Hakan"))
                .andExpect(jsonPath("$.memberLastName").value("Haktan"));
    }

    @Test
    void testDeleteMember() throws Exception{
        Map<String, Boolean> response = new HashMap<>();
        response.put("Üye silindi", Boolean.TRUE);
        when(memberService.deleteMember(1L)).thenReturn(response);
        mockMvc.perform(delete("/member-api/delete-member/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['Üye silindi']").value(true));
    }

}