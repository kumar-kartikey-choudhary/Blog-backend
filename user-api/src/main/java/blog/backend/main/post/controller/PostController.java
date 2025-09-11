package blog.backend.main.post.controller;

import blog.backend.main.post.dto.PostDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResponseBody
@FeignClient(name = "posts" , url = "${post.url}" , primary = false, path = "/posts")
public interface PostController {

    @PostMapping(path = "create")
    ResponseEntity<PostDTO> create(@RequestBody PostDTO postDTO);

    @GetMapping(path = "all")
    ResponseEntity<List<PostDTO>> getAllPosts();

    @GetMapping(path = "findById/{id}")
    ResponseEntity<PostDTO> getById(@PathVariable(name = "id") String id);

    @PutMapping(path = "update/{id}")
    ResponseEntity<PostDTO> update(@PathVariable(name = "id") String id, @RequestBody PostDTO postDTO);

    @DeleteMapping(path = "delete/{id}")
    void delete(@PathVariable(name = "id") String id);

}
