package com.wanted.cxxxtxxyxx.domain.post.repository;

import com.wanted.cxxxtxxyxx.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByOrderByIdDesc(PageRequest pageRequest);

}
