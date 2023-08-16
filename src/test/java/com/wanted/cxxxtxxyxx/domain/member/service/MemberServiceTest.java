package com.wanted.cxxxtxxyxx.domain.member.service;

import com.wanted.cxxxtxxyxx.domain.entity.Member;
import com.wanted.cxxxtxxyxx.domain.member.dto.SignInRequestDto;
import com.wanted.cxxxtxxyxx.domain.member.dto.SignInResponseDto;
import com.wanted.cxxxtxxyxx.domain.member.dto.SignUpRequestDto;
import com.wanted.cxxxtxxyxx.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenManager tokenManager;

    @DisplayName("Member 생성 테스트")
    @Test
    @Transactional
    void createTest() {
        // given
        SignUpRequestDto requestBody = SignUpRequestDto.builder()
                .email("qwer@naver.com")
                .password("12345678")
                .build();
        Member member = Member.builder()
                .id(1L)
                .email("qwer@naver.com")
                .password("12345678")
                .build();
        given(memberRepository.findByEmail(anyString())).willReturn(Optional.empty());
        given(memberRepository.save(any())).willReturn(member);

        // when
        memberService.create(requestBody);

        // then
        then(memberRepository).should(times(1)).findByEmail(anyString());
    }

    @DisplayName("Member 로그인 테스트")
    @Test
    void loginTest() {
        // given
        SignInRequestDto requestDto = SignInRequestDto.builder()
                .email("qwer@naver.com")
                .password("12345678")
                .build();
        Member member = Member.builder()
                .id(1L)
                .email("qwer@naver.com")
                .password("********")
                .build();
        given(memberRepository.findByEmail(anyString())).willReturn(Optional.of(member));
        given(tokenManager.generateAccessToken(any())).willReturn("accessToken");
        given(passwordEncoder.matches(anyString(), anyString())).willReturn(true);

        // when
        SignInResponseDto responseDto = memberService.signIn(requestDto);

        // then
        then(memberRepository).should(times(1)).findByEmail(anyString());
        then(tokenManager).should(times(1)).generateAccessToken(any());
        assertThat(responseDto.getMemberId()).isEqualTo(1L);
        assertThat(responseDto.getAccessToken()).isEqualTo("accessToken");
    }
}