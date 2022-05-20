package com.cloncoding.book.springboot.domain.posts;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "안녈하세요.";
        String content = "스프링 책 보고 따라 하기.\n 어렵다....";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("buchonsi")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2022, 5, 20, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();
        
        //then
        Posts posts = postsList.get(0);

        System.out.println("posts.getCreateDate() == " + posts.getCreateDate() + "  posts.getModifiedDate() == " + posts.getModifiedDate());
        assertThat(postsList.get(0).getCreateDate()).isAfter(now);
        assertThat(postsList.get(0).getModifiedDate()).isAfter(now);
    }
}