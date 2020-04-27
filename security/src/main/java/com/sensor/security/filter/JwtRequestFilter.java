package com.sensor.security.filter;

import com.google.gson.Gson;
import com.sensor.security.jwt.JwtUtil;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.sensor.constant.ApplicationConstant.AUTHORIZATION;
import static com.sensor.constant.ApplicationConstant.JWT_PREFIX;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private static final int START_OF_TOKEN = 7;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader = httpServletRequest.getHeader(AUTHORIZATION);
        String login = null;
        String jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith(JWT_PREFIX)) {
            jwt = authorizationHeader.substring(START_OF_TOKEN);
            try {
                login = jwtUtil.extractUsername(jwt);
            } catch (MalformedJwtException e) {
                httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
                httpServletResponse.getWriter().write(new Gson().toJson("403 access denied"));
                return;
            }
        }
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (login != null && authentication == null) {
            UserDetails userDetails = userService.loadUserByUsername(login);
            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails, jwt, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}