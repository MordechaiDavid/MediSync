//package com.medisync.security;
//
//import com.medisync.util.JwtUtil;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import java.io.IOException;
//
//public class JwtFilter extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String path = request.getRequestURI();
//        if (path.equals("/api/users/login") || path.equals("/api/users/register")){
//            filterChain.doFilter(request, response);
//            return;
//        }
//        String token = request.getHeader("Authorization");
//        if (token != null && token.startsWith("Bearer ") &&
//                JwtUtil.validateToken(token.substring(7))){
//            String email = JwtUtil.getEmailFromToken(token.substring(7));
//            request.setAttribute("email", email);
//            filterChain.doFilter(request, response);
//        }else {
//            response.setStatus(401);
//        }
//    }
//}
