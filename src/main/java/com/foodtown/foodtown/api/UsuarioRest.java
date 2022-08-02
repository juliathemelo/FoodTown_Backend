package com.foodtown.foodtown.api;

import com.foodtown.foodtown.business.UsuarioBO;
import com.foodtown.foodtown.model.UsuarioModel;
import com.foodtown.foodtown.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UsuarioRest {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private final UsuarioBO usuarioBO;

    public UsuarioRest(UsuarioBO usuarioBO) {
        this.usuarioBO = usuarioBO;
    }

    @GetMapping("/all")
    public List<UsuarioModel> getUser() {
        return usuarioBO.getUsers();
    }

    @PostMapping(path = "/salvar")
    public UsuarioModel salvar(@RequestBody UsuarioModel usuario) {
        return usuarioBO.saveUser(usuario);
    }


}
