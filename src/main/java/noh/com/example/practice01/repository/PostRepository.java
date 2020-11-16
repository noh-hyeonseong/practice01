package noh.com.example.practice01.repository;

import lombok.RequiredArgsConstructor;
import noh.com.example.practice01.domain.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public List<Post> findAll(){
        return em.createQuery("select p from Post p", Post.class).getResultList();
    }

    public void save(Post post){
        if (post.getId() == null) {
            em.persist(post);
        } else {
            em.merge(post);
        }
    }

    public void deleteById(Post post) {
        em.remove(post);
    }

    public Post findById(Long postId) {
        return em.find(Post.class, postId);
    }
}
