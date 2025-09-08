package blog.backend.main.comment.controller;


import blog.backend.main.comment.dto.CommentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResponseBody
@FeignClient(name = "comments",url = "{comment.url}", path = "/comments")
public interface CommentController {

    @PostMapping(path = "create")
    ResponseEntity<CommentDto> create(@RequestBody CommentDto commentDto);

    @GetMapping(path = "find/{uuid}")
    ResponseEntity<CommentDto> find(@PathVariable(name = "uuid") String uuid);

    @GetMapping(path = "find/{postId}")
    ResponseEntity<List<CommentDto>> findByPostId(@PathVariable(name = "postId") String postId);

    @DeleteMapping(path = "delete")
    void delete(@PathVariable(name = "uuid")  String uuid);

}
