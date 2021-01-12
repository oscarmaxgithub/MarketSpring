package com.dev.market.web.security.filter;

import com.dev.market.domain.service.MarketUserDetailsService;
import com.dev.market.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilterRequest extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private MarketUserDetailsService objUserDetails;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader=request.getHeader("Authorization");
        if (authorizationHeader!=null && authorizationHeader.startsWith("Bearer")){
            String jwt=authorizationHeader.substring(7);// 7 porque descontando el Bearer de la cabecera
            String username=jwtUtil.extractUsername(jwt);

            if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails userDet=objUserDetails.loadUserByUsername(username);

                if (jwtUtil.validateToken(jwt,userDet)){
                    UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDet,null,userDet.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));//los detalles del navegador que esta usando, que hora se conecta, que SO tiene
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request,response);
        }
    }
}
