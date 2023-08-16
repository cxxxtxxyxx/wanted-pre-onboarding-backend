package com.wanted.cxxxtxxyxx.domain.member.service;

import com.wanted.cxxxtxxyxx.domain.entity.Member;
import com.wanted.cxxxtxxyxx.domain.member.code.MemberErrorCode;
import com.wanted.cxxxtxxyxx.domain.member.dto.SignInRequestDto;
import com.wanted.cxxxtxxyxx.domain.member.dto.SignInResponseDto;
import com.wanted.cxxxtxxyxx.domain.member.dto.SignUpRequestDto;
import com.wanted.cxxxtxxyxx.domain.member.exception.AlreadyEmailExistException;
import com.wanted.cxxxtxxyxx.domain.member.exception.InvalidPayloadException;
import com.wanted.cxxxtxxyxx.domain.member.exception.NotFoundMemberException;
import com.wanted.cxxxtxxyxx.domain.member.repository.MemberRepository;
import com.wanted.cxxxtxxyxx.domain.member.vo.TokenPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenManager tokenManager;

    @Transactional
    public void create(SignUpRequestDto signUpRequestDto) {

        Member member = getMemberByEmailOrNull(signUpRequestDto.getEmail());

        validateAlreadyExistMember(member);

        Member signUpMember = createMember(signUpRequestDto);
        memberRepository.save(signUpMember);
    }

    public SignInResponseDto signIn(SignInRequestDto signInRequestDto) {

        Long memberId = validateSignInPayload(signInRequestDto);
        String accessToken = tokenManager.generateAccessToken(new TokenPayload(memberId));

        return SignInResponseDto.builder()
                .memberId(memberId)
                .accessToken(accessToken)
                .build();
    }

    private Long validateSignInPayload(SignInRequestDto signInRequestDto) {
        Member member = getMemberByEmailOrThrow(signInRequestDto);

        validatePassword(signInRequestDto.getPassword(), member.getPassword());

        return member.getId();
    }

    private void validatePassword(String payloadPassword, String encodedPassword) {
        if (!passwordEncoder.matches(payloadPassword, encodedPassword)) {
            throw new InvalidPayloadException(MemberErrorCode.INVALID_PAYLOAD);
        }
    }

    private Member getMemberByEmailOrThrow(SignInRequestDto signInRequestDto) {
        return memberRepository.findByEmail(signInRequestDto.getEmail())
                .orElseThrow(() -> new NotFoundMemberException(MemberErrorCode.NOT_FOUND_MEMBER_BY_EMAIL));
    }


    private Member getMemberByEmailOrNull(String email) {
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

    private void validateAlreadyExistMember(Member member) {
        if (member != null) {
            throw new AlreadyEmailExistException(MemberErrorCode.ALREADY_EXIST_EMAIL);
        }
    }
}
