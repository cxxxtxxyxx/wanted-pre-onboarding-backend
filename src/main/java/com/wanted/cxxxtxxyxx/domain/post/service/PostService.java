package com.wanted.cxxxtxxyxx.domain.post.service;

import com.wanted.cxxxtxxyxx.common.error.exception.NotExistLoginInformationException;
import com.wanted.cxxxtxxyxx.common.response.code.CommonErrorCode;
import com.wanted.cxxxtxxyxx.domain.entity.Member;
import com.wanted.cxxxtxxyxx.domain.entity.Post;
import com.wanted.cxxxtxxyxx.domain.member.dto.MemberResponseDto;
import com.wanted.cxxxtxxyxx.domain.member.repository.MemberRepository;
import com.wanted.cxxxtxxyxx.domain.post.code.PostErrorCode;
import com.wanted.cxxxtxxyxx.domain.post.dto.*;
import com.wanted.cxxxtxxyxx.domain.post.exception.NotFoundPostException;
import com.wanted.cxxxtxxyxx.domain.post.exception.UnauthorizationMemberException;
import com.wanted.cxxxtxxyxx.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public ReadPostResponstDto create(Long memberId, CreatePostRequestDto createPostRequestDto) {
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

        PostResponseDto postResponseDto = PostResponseDto.builder()
                .id(newPost.getId())
                .title(newPost.getTitle())
                .content(newPost.getContent())
                .build();

        return ReadPostResponstDto.builder()
                .member(member)
                .post(postResponseDto)
                .build();
    }

    @Transactional(readOnly = true)
    public ReadPostResponstDto getById(Long postId) {
        Post post = getPostById(postId);

        Member postMember = post.getMember();

        MemberResponseDto memberResponseDto = MemberResponseDto.builder()
                .id(postMember.getId())
                .email(postMember.getEmail())
                .build();

        PostResponseDto postResponseDto = PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();

        return ReadPostResponstDto.builder()
                .member(memberResponseDto)
                .post(postResponseDto)
                .build();
    }

    public void update(Long loginMemberId, Long postId, UpdatePostRequestDto updatePostRequestDto) {
        Post findPost = getPostById(postId);
        validateAuthorization(loginMemberId, findPost.getMember().getId());

        findPost.update(updatePostRequestDto.getTitle(), updatePostRequestDto.getContent());
    }

    public void delete(Long loginMemberId, Long postId) {
        Post findPost = getPostById(postId);
        validateAuthorization(loginMemberId, findPost.getMember().getId());

        postRepository.delete(findPost);
    }

    private void validateAuthorization(Long loginMemberId, Long postMemberId) {
        if (!loginMemberId.equals(postMemberId)) {
            throw new UnauthorizationMemberException(PostErrorCode.FORBIDDEN);
        }
    }

    private Post getPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundPostException(PostErrorCode.NOT_FOUND_POST));
    }

    public List<ReadPostResponstDto> getAll(int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Post> pages = postRepository.findAllByOrderByIdDesc(pageRequest);
        return pages.stream()
                .map(post -> {
                    MemberResponseDto memberResponseDto = MemberResponseDto.builder()
                            .id(post.getMember().getId())
                            .email(post.getMember().getEmail())
                            .build();

                    PostResponseDto postResponseDto = PostResponseDto.builder()
                            .id(post.getId())
                            .title(post.getTitle())
                            .content(post.getContent())
                            .build();

                    return ReadPostResponstDto.builder()
                            .member(memberResponseDto)
                            .post(postResponseDto)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
