package com.foodtown.foodtown.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.foodtown.foodtown.model.UsuarioModel;
import com.foodtown.foodtown.repository.UsuarioRepository;

@Lazy
@Service("UsuarioBusiness")
public class UsuarioBO {

    @Autowired
    private UsuarioRepository repository;

    public List<UsuarioModel> getUsers() {
        List<UsuarioModel> users = new ArrayList<>();
        repository.findAll().forEach(users::add);
        return users;
    }

    public UsuarioModel saveUser(UsuarioModel user) {
    	
    	BCryptPasswordEncoder hash = new BCryptPasswordEncoder();
    	
    	user.setSenha(hash.encode(user.getSenha()));
    	
        return repository.save(user);
    }
}
