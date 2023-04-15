package com.cadastro.produtos.repositories;

import com.cadastro.produtos.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

   Optional<UsuarioModel> findByUsername(String username);

}
