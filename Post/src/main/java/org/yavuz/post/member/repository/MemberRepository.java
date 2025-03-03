package org.yavuz.post.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yavuz.post.member.model.Member;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}

