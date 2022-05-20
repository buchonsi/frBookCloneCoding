package com.cloncoding.book.springboot.service.posts;

import com.cloncoding.book.springboot.domain.posts.PostsRepository;
import com.cloncoding.book.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
//        postsRepository.save(requestDto.toE
//        );
        return 0L;
    }

}
