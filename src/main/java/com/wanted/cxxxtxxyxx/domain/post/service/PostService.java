package com.wanted.cxxxtxxyxx.domain.post.service;

import com.wanted.cxxxtxxyxx.common.error.exception.NotExistLoginInformationException;
import com.wanted.cxxxtxxyxx.common.response.code.CommonErrorCode;
import com.wanted.cxxxtxxyxx.domain.entity.Member;
import com.wanted.cxxxtxxyxx.domain.entity.Post;
import com.wanted.cxxxtxxyxx.domain.member.dto.MemberResponseDto;
import com.wanted.cxxxtxxyxx.domain.member.repository.MemberRepository;
import com.wanted.cxxxtxxyxx.domain.post.dto.CreatePostRequestDto;
import com.wanted.cxxxtxxyxx.domain.post.dto.CreatePostResponseDto;
import com.wanted.cxxxtxxyxx.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public CreatePostResponseDto create(Long memberId, CreatePostRequestDto createPostRequestDto) {
        Member loginMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotExistLoginInformationException(CommonErrorCode.HANDLE_UNAUTHORIZED));

        Post newPost = Post.builder()
                .member(loginMember)
                .content(createPostRequestDto.getContent())
                .title(createPostRequestDto.getTitle())
                .build();

        postRepository.save(newPost);

        MemberResponseDto member = MemberResponseDto.builder()
                .id(loginMember.getId())
                .email(loginMember.getEmail())
                .build();

        return CreatePostResponseDto.builder()
                .member(member)
                .title(createPostRequestDto.getTitle())
                .content(createPostRequestDto.getContent())
                .build();
    }
}
