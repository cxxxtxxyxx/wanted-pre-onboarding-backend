package com.wanted.cxxxtxxyxx.domain.member.repository;

import com.wanted.cxxxtxxyxx.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
