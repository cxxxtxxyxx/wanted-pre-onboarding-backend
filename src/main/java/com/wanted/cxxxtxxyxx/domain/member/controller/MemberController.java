package com.wanted.cxxxtxxyxx.domain.member.controller;

import com.wanted.cxxxtxxyxx.common.response.APIDataResponse;
import com.wanted.cxxxtxxyxx.domain.member.code.MemberCode;
import com.wanted.cxxxtxxyxx.domain.member.dto.SignUpRequestDto;
import com.wanted.cxxxtxxyxx.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpRequestDto signUpRequestDto) {
        memberService.create(signUpRequestDto);

        return APIDataResponse.empty(MemberCode.CREATED);
    }
}
