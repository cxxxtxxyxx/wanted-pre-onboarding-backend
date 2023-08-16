package com.wanted.cxxxtxxyxx.domain.member.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SignInResponseDto {

    Long memberId;
    String accessToken;
}
