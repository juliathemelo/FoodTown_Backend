package com.foodtown.foodtown.business;

import com.foodtown.foodtown.model.UsuarioModel;
import com.foodtown.foodtown.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

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
        return repository.save(user);
    }
}
