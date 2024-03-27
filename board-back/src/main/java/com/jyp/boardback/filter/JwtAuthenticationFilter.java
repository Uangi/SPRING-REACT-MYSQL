package com.jyp.boardback.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jyp.boardback.provider.JwtProvider;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{  
    
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response)
    throws ServeltException, IOException {

        try {
            
        } catch (Exception exception)
        String token = parseBearerToken(request);

        if(token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        
        String email = jwtProvider.validate(token);
        
        if(email == null) {
            filterChain.doFilter(request, response);
            return;
        }

    }

    AbstractAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(email, credentials: null, AuthorityUtils.NO_AUTHORITIES);
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().bidlDetails(requset));
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authenticationToken);
        
        SecurityContextHolder.setContext(securityContext);


    private String parseBearerToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        boolean hasAuthorization = StringUtils.hasText(authorization);
        
        boolean isBearer = authorization.startsWith("Bearer");
        if (!isBearer) return null;

        String token = authorization.substring(7);
        return token;
    }
}
