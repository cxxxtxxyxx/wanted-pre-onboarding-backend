package com.wanted.cxxxtxxyxx.domain.post.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanted.cxxxtxxyxx.domain.member.dto.MemberResponseDto;
import com.wanted.cxxxtxxyxx.domain.post.code.PostCode;
import com.wanted.cxxxtxxyxx.domain.post.dto.CreatePostRequestDto;
import com.wanted.cxxxtxxyxx.domain.post.dto.PostResponseDto;
import com.wanted.cxxxtxxyxx.domain.post.dto.ReadPostResponstDto;
import com.wanted.cxxxtxxyxx.domain.post.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("[POST][/api/v1/posts] - 게시물을 생성합니다.")
    @Test
    @WithMockUser(username = "1")
    void createPostTest() throws Exception {
        // given
        CreatePostRequestDto requestDto = CreatePostRequestDto.builder()
                .title("테스트 제목1")
                .content("테스트 제목2")
                .build();
        MemberResponseDto member = MemberResponseDto.builder()
                .id(1L)
                .email("qwer@naver.com")
                .build();

        PostResponseDto postResponseDto = PostResponseDto.builder()
                .id(1L)
                .title("테스트 제목1")
                .content("테스트 제목2")
                .build();

        ReadPostResponstDto readPostResponstDto = ReadPostResponstDto.builder()
                .member(member)
                .post(postResponseDto)
                .build();

        given(postService.create(any(), any())).willReturn(readPostResponstDto);

        // when
        ResultActions resultActions = mockMvc.perform(
                post("/api/v1/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
        );


        // then
        resultActions
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(PostCode.CREATED.getStatus()))
                .andExpect(jsonPath("$.code").value(PostCode.CREATED.getCode()))
                .andExpect(jsonPath("$.message").value(PostCode.CREATED.getMessage()))
                .andExpect(jsonPath("$.data.member.id").hasJsonPath())
                .andExpect(jsonPath("$.data.member.email").hasJsonPath())
                .andExpect(jsonPath("$.data.post.id").hasJsonPath())
                .andExpect(jsonPath("$.data.post.title").hasJsonPath())
                .andExpect(jsonPath("$.data.post.content").hasJsonPath());
        then(postService).should(times(1)).create(any(), any());
    }

}