package com.wanted.cxxxtxxyxx.domain.member.controller;

import antlr.preprocessor.Preprocessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanted.cxxxtxxyxx.common.config.auth.filter.JwtAuthenticationFilter;
import com.wanted.cxxxtxxyxx.domain.member.dto.SignUpRequestDto;
import com.wanted.cxxxtxxyxx.domain.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureRestDocs
@AutoConfigureMockMvc
@SpringBootTest
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName("[/api/v1/signup] - 회원가입을 진행한다.")
    void createMemberTest() throws Exception {
        // Given
        SignUpRequestDto requestBody = SignUpRequestDto.builder()
                .email("qwer@naver.com")
                .password("12345678")
                .build();

        willDoNothing().given(memberService).create(any());

        // When
        mockMvc.perform(
                post("/api/v1/signup")
                        .content(mapper.writeValueAsString(requestBody))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(document("/api/v1/signup", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").isString())
                .andExpect(jsonPath("$.status").isNumber());

        // Then
        then(memberService).should().create(any());

    }

}