package com.study.boardback.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.study.boardback.provider.JwtProvider;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    //필수 생성자 생성
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String token = parseBearerToken(request);

            if (token == null) {
                //인증 실패
                filterChain.doFilter(request, response);
                return;
            }
            String email = jwtProvider.validate(token);
            if(email==null) {
                //인증 실패
                filterChain.doFilter(request, response);
                return;
            }
    
            //유저 정보 담긴 토큰
            AbstractAuthenticationToken authenticationToken = 
            new UsernamePasswordAuthenticationToken(email, null, AuthorityUtils.NO_AUTHORITIES);
            
            //웹 인증 세부 정보 (디테일 구축)
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authenticationToken);
    
            SecurityContextHolder.setContext(securityContext);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        filterChain.doFilter(request, response);

    }

    private String parseBearerToken (HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");

        //토큰 양식 체크
        boolean hasAuthorization = StringUtils.hasText(authorization);
        if(!hasAuthorization) return null;
        boolean isBearer = authorization.startsWith("Bearer");
        if (!isBearer) return null;

        //Bearer 뒷부분부터 받아옴
        String token = authorization.substring(7);

        return token;
    }

    
}
