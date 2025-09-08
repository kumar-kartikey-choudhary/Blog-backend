package blog.backend.parent.service.impl;

import blog.backend.main.user.repository.UserRepository;
import blog.backend.parent.model.JwtRequest;
import blog.backend.parent.model.JwtResponse;
import blog.backend.parent.service.AuthService;
import blog.backend.parent.service.CustomUserDetailService;
import blog.backend.parent.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private CustomUserDetailService detailsService;
    private JwtUtil jwtUtil;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, CustomUserDetailService detailsService, JwtUtil jwtUtil)
    {
        this.authenticationManager= authenticationManager;
        this.detailsService= detailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public JwtResponse login(JwtRequest request) throws Exception {
        log.info("request {}: ", request);
        log.info("Inside the login of AuthServiceImpl");
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (UsernameNotFoundException e) {
            throw new Exception("Username not found...");
        }
        catch (BadCredentialsException e) {
            throw new Exception("Bad credentials");
        }
        UserDetails userDetails = detailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        return new JwtResponse(token);
    }
}
