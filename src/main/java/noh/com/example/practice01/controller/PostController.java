package noh.com.example.practice01.controller;

import lombok.RequiredArgsConstructor;
import noh.com.example.practice01.domain.Post;
import noh.com.example.practice01.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

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

        postService.savePost(post);
        return "redirect:/posts";

    }

    @PostMapping(value = "/posts/{postId}/delete")
    public String deletePost(@PathVariable("postId") Long postId){
        log.info("@PostMapping /posts/"+postId+"/delete");
        postService.deletePost(postId);
        return "redirect:/posts";
    }

    @GetMapping(value = "/posts/{postId}/edit")
    public String editPostForm(@PathVariable("postId") Long postId, Model model){
        log.info("@GetMapping /posts/"+postId+"/edit");
        Post post = postService.findPost(postId);
        PostForm form = new PostForm();
        form.setId(post.getId());
        form.setTitle(post.getTitle());
        form.setContent(post.getContent());
        model.addAttribute("form", form);
        return "posts/editPostForm";
    }
    @PostMapping(value = "/posts/{postId}/edit")
    public String editPost(@PathVariable("postId") Long postId, @ModelAttribute("form") PostForm form){
        log.info("@PostMapping /posts/"+postId+"/edit");
        Post post = postService.findPost(postId);
        post.setId(postId);
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());
        postService.savePost(post);
        return "redirect:/posts";
    }
}
