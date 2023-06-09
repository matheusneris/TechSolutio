package com.cadastro.produtos.services;

import com.cadastro.produtos.dtos.ProdutoDto;
import com.cadastro.produtos.models.ProdutoModel;
import com.cadastro.produtos.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public ProdutoDto salvarProduto(ProdutoDto produtoDto) {
        ProdutoModel produtoModel = new ProdutoModel(produtoDto);
        ProdutoDto produtoDtoRetorno = new ProdutoDto(produtoRepository.save(produtoModel));
        return produtoDtoRetorno;
    }

    @Transactional
    public ProdutoDto editarProduto(Long id, ProdutoDto produtoDto){
        ProdutoModel produtoModel = new ProdutoModel(produtoDto);
        produtoModel.setId(id);
        ProdutoDto produtoDtoRetorno = new ProdutoDto(produtoRepository.save(produtoModel));
        return produtoDtoRetorno;
    }

    public List<ProdutoDto> listarProdutos(){

        List<ProdutoModel> produtoModels = produtoRepository.findAll();
        List<ProdutoDto> produtoDtos = new ArrayList<>();
        produtoModels.stream().forEach(produtoModel -> produtoDtos.add(new ProdutoDto(produtoModel)));
        return produtoDtos;
    }

    public Optional<ProdutoDto> buscarProduto(Long id){
        Optional<ProdutoModel> produtoModelOptional = produtoRepository.findById(id);
        if(produtoModelOptional.isEmpty()){
            Optional<ProdutoDto> produtoDtoOptional = Optional.empty();
            return produtoDtoOptional;
        }
        ProdutoDto produtoDto = new ProdutoDto(produtoModelOptional.get());
        return Optional.of(produtoDto);
    }

    public Optional<ProdutoDto> buscarProdutoPorNome(String nomeProduto){
        Optional<ProdutoModel> produtoModelOptional = produtoRepository.findByNomeProduto(nomeProduto);
        if(produtoModelOptional.isEmpty()){
            return Optional.empty();
        }
        ProdutoDto produtoDto = new ProdutoDto(produtoModelOptional.get());
        return Optional.of(produtoDto);
    }

    @Transactional
    public void deletarProduto(Long id){
        produtoRepository.deleteById(id);
    }

    public boolean existsByNomeProdutoAndFabricanteProduto(String nomeProduto, String fabricanteProduto){
        return produtoRepository.existsByNomeProdutoAndFabricanteProduto(nomeProduto, fabricanteProduto);
    }

}
