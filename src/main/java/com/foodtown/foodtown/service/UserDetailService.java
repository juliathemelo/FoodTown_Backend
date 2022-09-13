package com.foodtown.foodtown.service;

import com.foodtown.foodtown.data.UserDetailData;
import com.foodtown.foodtown.model.UsuarioModel;
import com.foodtown.foodtown.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailService implements UserDetailsService {

    private final UsuarioRepository repository;

    public UserDetailService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<UsuarioModel> user = repository.findByLogin(login);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Usuário [" + login + "] Não encontrado");
        }

        return new UserDetailData(user);
    }

}
