package com.wanted.cxxxtxxyxx.domain.post.controller;

import com.wanted.cxxxtxxyxx.common.config.auth.annotation.LoginMemberId;
import com.wanted.cxxxtxxyxx.common.response.APIDataResponse;
import com.wanted.cxxxtxxyxx.domain.post.code.PostCode;
import com.wanted.cxxxtxxyxx.domain.post.dto.CreatePostRequestDto;
import com.wanted.cxxxtxxyxx.domain.post.dto.CreatePostResponseDto;
import com.wanted.cxxxtxxyxx.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;


    @PostMapping
    public ResponseEntity<?> create(
            @LoginMemberId Long loginMemberId,
            @RequestBody @Valid CreatePostRequestDto createPostRequestDto
    ) {
        CreatePostResponseDto responseDto = postService.create(loginMemberId, createPostRequestDto);
        return APIDataResponse.of(responseDto, PostCode.CREATED);
    }

}
