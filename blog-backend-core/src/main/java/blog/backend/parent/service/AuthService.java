package blog.backend.parent.service;

import blog.backend.parent.model.JwtRequest;
import blog.backend.parent.model.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest jwtRequest) throws Exception;
}
