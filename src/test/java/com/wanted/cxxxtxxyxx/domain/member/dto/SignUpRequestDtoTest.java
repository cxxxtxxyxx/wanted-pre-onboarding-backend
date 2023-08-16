package com.wanted.cxxxtxxyxx.domain.member.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SignUpRequestDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    @DisplayName("이메일 및 패스워드 성공 테스트")
    void dtoValidationSuccessTest() {
        SignUpRequestDto body = SignUpRequestDto.builder()
                .email("qwer@naver.com")
                .password("12345678")
                .build();
        Set<ConstraintViolation<SignUpRequestDto>> emailValidation = validator.validate(body);
        assertThat(emailValidation.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("이메일 및 패스워드 검증 실패 테스트")
    void dtoValidationFailTest() {
        SignUpRequestDto body = SignUpRequestDto.builder()
                .email("qwernaver.com")
                .password("1234567")
                .build();
        Set<ConstraintViolation<SignUpRequestDto>> emailValidation = validator.validate(body);
        assertThat(emailValidation.size()).isEqualTo(2);
    }

}