package com.dev.market.web.security.filter;

import com.dev.market.domain.service.MarketUserDetailsService;
import com.dev.market.web.security.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j //para usar logs con Lombok
public class JwtFilterRequest extends OncePerRequestFilter {

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private MarketUserDetailsService objUserDetails;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String authorizationHeader=request.getHeader("Authorization");
            log.info("Header de Filtro "+authorizationHeader);
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
            }
            filterChain.doFilter(request,response);
        }catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return;
        }

    }

    private boolean existeJWTtoken(HttpServletRequest req,HttpServletResponse res){
        String authHeader=req.getHeader(HEADER);
        if (authHeader==null || !authHeader.startsWith(PREFIX))
            return false;
        return true;
    }
}
