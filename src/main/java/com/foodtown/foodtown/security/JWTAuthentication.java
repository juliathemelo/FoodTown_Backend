package com.foodtown.foodtown.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodtown.foodtown.data.UserDetailData;
import com.foodtown.foodtown.model.UsuarioModel;

public class JWTAuthentication extends UsernamePasswordAuthenticationFilter{

    public static final int TOKEN_TIME = 600_000;
    public static final String TOKEN_PASSWORD = "81cf5398-24ae-4555-acb6-5686e8faabd9";
    private final AuthenticationManager authenticationManager;

    public JWTAuthentication(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UsuarioModel user = new ObjectMapper().readValue(request.getInputStream(), UsuarioModel.class);

            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getSenha(), new ArrayList<>()));
            return auth;
        } catch (IOException e) {
            throw new RuntimeException("Falha ao autenticar o user", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetailData userData = (UserDetailData) authResult.getPrincipal();

        String token = JWT.create().
                withSubject(userData.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_TIME))
                .sign(Algorithm.HMAC512(TOKEN_PASSWORD));
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(
                "{\"" + "Authorization" + "\":\"" + "Bearer " + token + "\"}"
        );
        
    }
}
