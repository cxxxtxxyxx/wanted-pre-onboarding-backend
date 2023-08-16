package com.wanted.cxxxtxxyxx.domain.post.controller;

import com.wanted.cxxxtxxyxx.common.config.auth.annotation.LoginMemberId;
import com.wanted.cxxxtxxyxx.common.response.APIDataResponse;
import com.wanted.cxxxtxxyxx.domain.post.code.PostCode;
import com.wanted.cxxxtxxyxx.domain.post.dto.CreatePostRequestDto;
import com.wanted.cxxxtxxyxx.domain.post.dto.CreatePostResponseDto;
import com.wanted.cxxxtxxyxx.domain.post.dto.ReadPostResponstDto;
import com.wanted.cxxxtxxyxx.domain.post.dto.UpdatePostRequestDto;
import com.wanted.cxxxtxxyxx.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
        ReadPostResponstDto responseDto = postService.create(loginMemberId, createPostRequestDto);
        return APIDataResponse.of(responseDto, PostCode.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<ReadPostResponstDto> posts = postService.getAll(page - 1, size);
        return APIDataResponse.of(posts, PostCode.READ_PAGE);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getById(
            @PathVariable Long postId
    ) {
        ReadPostResponstDto responstDto = postService.getById(postId);
        return APIDataResponse.of(responstDto, PostCode.READ_SPECIFIC_POST);
    }


    @PutMapping("/{postId}")
    public ResponseEntity<?> update(
            @LoginMemberId Long loginMemberId,
            @PathVariable Long postId,
            @RequestBody @Valid UpdatePostRequestDto updatePostRequestDto
    ) {
        postService.update(loginMemberId, postId, updatePostRequestDto);
        return APIDataResponse.empty(PostCode.UPDATED);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> delete(
            @LoginMemberId Long loginMemberId,
            @PathVariable Long postId
    ) {
        postService.delete(loginMemberId, postId);
        return APIDataResponse.empty(PostCode.DELETED);
    }
}
