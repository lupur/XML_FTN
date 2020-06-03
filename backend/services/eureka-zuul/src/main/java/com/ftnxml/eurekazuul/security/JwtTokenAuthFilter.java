package com.ftnxml.eurekazuul.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtTokenAuthFilter extends OncePerRequestFilter {

    private final String authHeader = "Authorization";
    private final String prefix = "Bearer ";
    private final String secret = "JwtSecretKey";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String header = request.getHeader(authHeader);

        if (header == null || !header.startsWith(prefix)) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace(prefix, "");

        try {
            System.out.println("Trying to authenticate with token: " + token);
            Claims claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();

            String username = claims.getSubject();
            if (username != null) {
                @SuppressWarnings("unchecked")
                List<String> authorities = (List<String>) claims.get("authorities");

                System.out.println("Username: " + username);
                System.out.println("Authorities: " + authorities.get(0).toString());
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
                        authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                System.out.println("SecurityContextHolder.getContext().setAuthentication(auth);");

                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        } catch (Exception e) {
            System.out.println("Error while jwt token auth");
            SecurityContextHolder.clearContext();
        }
        chain.doFilter(request, response);
    }
}
