package com.wanted.cxxxtxxyxx.domain.post.service;


import com.wanted.cxxxtxxyxx.domain.entity.Member;
import com.wanted.cxxxtxxyxx.domain.member.dto.MemberResponseDto;
import com.wanted.cxxxtxxyxx.domain.member.repository.MemberRepository;
import com.wanted.cxxxtxxyxx.domain.post.dto.CreatePostRequestDto;
import com.wanted.cxxxtxxyxx.domain.post.dto.PostResponseDto;
import com.wanted.cxxxtxxyxx.domain.post.dto.ReadPostResponstDto;
import com.wanted.cxxxtxxyxx.domain.post.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private MemberRepository memberRepository;


    @Test
    @DisplayName("게시글 생성 테스트")
    void createPost() {
        // given

        CreatePostRequestDto requestDto = CreatePostRequestDto.builder()
                .title("테스트 제목1")
                .content("테스트 제목2")
                .build();
        Member member = Member.builder()
                .id(1L)
                .email("qwer@naver.com")
                .password("**********")
                .build();
        MemberResponseDto memberResponseDto = MemberResponseDto.builder()
                .id(1L)
                .email("qwer@naver.com")
                .build();

        PostResponseDto postResponseDto = PostResponseDto.builder()
                .id(1L)
                .title("테스트 제목1")
                .content("테스트 제목2")
                .build();

        ReadPostResponstDto readPostResponstDto = ReadPostResponstDto.builder()
                .member(memberResponseDto)
                .post(postResponseDto)
                .build();

        given(memberRepository.findById(any())).willReturn(Optional.of(member));

        // when
        ReadPostResponstDto responseDto = postService.create(1L, requestDto);

        // then
        then(memberRepository).should(times(1)).findById(any());
        assertThat(readPostResponstDto.getMember().getEmail()).isEqualTo(responseDto.getMember().getEmail());
        assertThat(readPostResponstDto.getMember().getId()).isEqualTo(responseDto.getMember().getId());
        assertThat(readPostResponstDto.getPost().getContent()).isEqualTo(responseDto.getPost().getContent());
        assertThat(readPostResponstDto.getPost().getTitle()).isEqualTo(responseDto.getPost().getTitle());

    }

}