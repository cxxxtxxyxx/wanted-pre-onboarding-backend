package com.wanted.cxxxtxxyxx.domain.member.service;

import com.wanted.cxxxtxxyxx.domain.entity.Member;
import com.wanted.cxxxtxxyxx.domain.member.code.MemberErrorCode;
import com.wanted.cxxxtxxyxx.domain.member.dto.SignUpRequestDto;
import com.wanted.cxxxtxxyxx.domain.member.exception.AlreadyExistException;
import com.wanted.cxxxtxxyxx.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void create(SignUpRequestDto signUpRequestDto) {

        Member member = getMemberByEmail(signUpRequestDto.getEmail());

        validatedAlreadyExistMember(member);

        Member signUpMember = createMember(signUpRequestDto);
        memberRepository.save(signUpMember);
    }

    private Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElse(null);
    }

    private Member createMember(SignUpRequestDto signUpRequestDto) {
        String encodedPassword = getEncodedPassword(signUpRequestDto.getPassword());

        return Member.builder()
                .email(signUpRequestDto.getEmail())
                .password(encodedPassword)
                .build();
    }

    private String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    private void validatedAlreadyExistMember(Member member) {
        if (member != null) {
            throw new AlreadyExistException(MemberErrorCode.Already_Exist_Email);
        }
    }
}
