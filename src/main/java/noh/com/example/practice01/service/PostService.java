package noh.com.example.practice01.service;

import lombok.RequiredArgsConstructor;
import noh.com.example.practice01.domain.Post;
import noh.com.example.practice01.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> findPosts(){
        return postRepository.findAll();
    }

    public Post findPost(Long postId){
        return postRepository.findById(postId);
    }

    @Transactional
    public void savePost(Post post){
        postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId);
        postRepository.deleteById(post);
    }

}
