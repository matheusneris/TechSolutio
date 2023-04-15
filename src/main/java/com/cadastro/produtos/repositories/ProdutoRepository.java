package com.cadastro.produtos.repositories;

import com.cadastro.produtos.models.ProdutoModel;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, UUID> {

    boolean existsByNomeProdutoAndFabricanteProduto(String nomeProduto, String fabricantepProduto);
    Optional<ProdutoModel> findByNomeProduto(String nomeProduto);

}
