package blog.backend.main.user.controller;

import blog.backend.main.user.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ResponseBody
@FeignClient(name = "users" , url = "${user.url}", path = "/users" , primary = false)
public interface UserController {

    @PostMapping(path = "auth/create")
    ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO);

    @GetMapping(path = "find/{uuid}")
    ResponseEntity<UserDTO> find(@PathVariable(name = "uuid") String uuid);

    @GetMapping(path = "findByUsername/{username}")
    ResponseEntity<UserDTO> findByUsername(@PathVariable(name = "username") String username);

}
