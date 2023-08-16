package com.wanted.cxxxtxxyxx.domain.post.dto;

import com.wanted.cxxxtxxyxx.domain.member.dto.SignUpRequestDto;
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

class CreatePostRequestDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("게시물 생성 Dto 유효성 검사 성공 테스트")
    void dtoValidationSucessTest() {
        CreatePostRequestDto body = CreatePostRequestDto.builder()
                .title("테스트")
                .content("테스트")
                .build();
        Set<ConstraintViolation<CreatePostRequestDto>> emailValidation = validator.validate(body);
        assertThat(emailValidation.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("게시물 생성 Dto 유효성 검사 실패 테스트")
    void dtoValidationFailTest() {
        CreatePostRequestDto body = CreatePostRequestDto.builder()
                .title("")
                .content("")
                .build();
        Set<ConstraintViolation<CreatePostRequestDto>> emailValidation = validator.validate(body);
        assertThat(emailValidation.size()).isEqualTo(2);
    }

}