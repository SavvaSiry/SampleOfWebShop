package com.firststrpsspring.demo.controllers;

import com.firststrpsspring.demo.entity.Post;
import com.firststrpsspring.demo.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/blog")
    public String blog(Model model){

        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);

        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model){

        return "blog-add";
    }

    @GetMapping("/blog/{id}")
    public String blogEdit(@PathVariable(value = "id") long id, Model model){
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-details";
    }

    @PostMapping("blog/add")
    public String blogPostAdd(@RequestParam String title,
                              @RequestParam String anons,
                              @RequestParam String full_text,
                              @RequestParam("file") MultipartFile file,
                              Model model) throws IOException {
        Post post = new Post(title, anons, full_text);
        if (file != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));
            post.setImage(resultFileName);
        }
        postRepository.save(post);
        return "redirect:/blog";
    }
}
