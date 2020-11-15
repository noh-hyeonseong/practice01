package noh.com.example.practice01.controller;

import lombok.RequiredArgsConstructor;
import noh.com.example.practice01.domain.Post;
import noh.com.example.practice01.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @GetMapping(value = "/posts")
    public String list(Model model){
        log.info("@GetMapping /posts");
        List<Post> posts = postService.findPosts();
        model.addAttribute("posts",posts);
        return "posts/postList";
    }

    @GetMapping(value = "/posts/new")
    public String createForm(Model model){
        model.addAttribute("postForm",new PostForm());
        return "posts/createPostForm";
    }
    @PostMapping(value = "/posts/new")
    public String create(PostForm form){

        log.info("@PostMapping /posts/new");
        log.info(form.getTitle());
        log.info(form.getContent());
        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());

        postService.createPost(post);
        return "redirect:/posts";

    }

    @PostMapping(value = "/posts/{postId}/delete")
    public String deletePost(@PathVariable("postId") Long postId){
        log.info("@PostMapping /posts/"+postId+"/delete");
        postService.deletePost(postId);
        return "redirect:/posts";
    }
}
