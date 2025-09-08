package blog.backend.parent.config;

import blog.backend.parent.constants.SecurityConstant;
import blog.backend.parent.service.CustomUserDetailService;
import blog.backend.parent.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtAuthFilter extends OncePerRequestFilter {


    private final JwtUtil jwtUtil;
    private final CustomUserDetailService customUserDetailService;

    @Autowired
    public JwtAuthFilter(JwtUtil jwtUtil, CustomUserDetailService customUserDetailService)
    {
        this.jwtUtil= jwtUtil;
        this.customUserDetailService= customUserDetailService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestHeader = request.getHeader(SecurityConstant.HEADER_STRING);
        String username = null;
        String token = null;

        if(requestHeader != null && requestHeader.startsWith(SecurityConstant.TOKEN_PREFIX))
        {
             token = requestHeader.substring(7);
             try {
                 username =  jwtUtil.extractUsername(token);
             }catch (UsernameNotFoundException e) {
                 log.error("Could not extract username from token: {}", e.getMessage());
             }
        } else
        {
            log.warn("Authorization header is missing or does not start with Bearer");
        }

        if(username != null && SecurityContextHolder.getContext() == null)
        {
            try {
                UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
                if(jwtUtil.validateToken(token, userDetails))
                {
                    UsernamePasswordAuthenticationToken authenticated = UsernamePasswordAuthenticationToken.authenticated(userDetails, null, userDetails.getAuthorities());
                    authenticated.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticated);
                }
                else
                {
                    log.error("JWT token validation failed for user: {}", username);
                }
            } catch (UsernameNotFoundException e) {
                log.error("User not found for username: {}", username);
                filterChain.doFilter(request, response);
                return;
            }
        }
        else
        {
            log.error("Username is null.....");
        }
        filterChain.doFilter(request, response);
    }
}
