package com.cadastro.produtos.repositories;

import com.cadastro.produtos.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {

    boolean existsByNomeProdutoAndFabricanteProduto(String nomeProduto, String fabricantepProduto);
    Optional<ProdutoModel> findByNomeProduto(String nomeProduto);

}
