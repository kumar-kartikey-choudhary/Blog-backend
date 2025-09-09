package blog.backend.parent.controller;


import blog.backend.parent.model.JwtRequest;
import blog.backend.parent.model.JwtResponse;
import blog.backend.parent.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;


    @Autowired
    public AuthController(AuthService authService)
    {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest) throws Exception {
        return ResponseEntity.ok(this.authService.login(jwtRequest));
    }
}
