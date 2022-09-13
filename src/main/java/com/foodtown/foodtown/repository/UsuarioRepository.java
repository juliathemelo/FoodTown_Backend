package com.foodtown.foodtown.repository;

import com.foodtown.foodtown.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
    public Optional<UsuarioModel> findByLogin(String login);
}