package com.cadastro.produtos.controllers;


import com.cadastro.produtos.dtos.ProdutoDto;
import com.cadastro.produtos.requests.ProdutoRequest;
import com.cadastro.produtos.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    final ProdutoService produtoService;

    public ProdutoController (ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Object> salvarProduto(@RequestBody @Valid ProdutoRequest produtoRequest){
        if(produtoService.existsByNomeProdutoAndFabricanteProduto(produtoRequest.getNomeProduto(), produtoRequest.getFabricanteProduto())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Produto já existe.");
        }
        ProdutoDto produtoDto = new ProdutoDto();
        BeanUtils.copyProperties(produtoRequest, produtoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.salvarProduto(produtoDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editarProduto(@PathVariable(value = "id") Long id, @RequestBody @Valid ProdutoRequest produtoRequest){
        Optional<ProdutoDto> produtoDtoOptional = produtoService.buscarProduto(id);
        if(produtoDtoOptional.isPresent()){
            ProdutoDto produtoDto = new ProdutoDto();
            BeanUtils.copyProperties(produtoRequest, produtoDto);

            return ResponseEntity.status(HttpStatus.OK).body(produtoService.editarProduto(id, produtoDto));

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não existe.");

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable(value = "id") Long id){
        Optional<ProdutoDto> produtoDtoOptional = produtoService.buscarProduto(id);
        if(produtoDtoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(produtoDtoOptional);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
    }

    @GetMapping("/buscar/{nome}")
    public ResponseEntity <Object> buscarProdutoPorNome(@PathVariable(value = "nome") String nome){
        Optional<ProdutoDto> produtoDtoOptional = produtoService.buscarProdutoPorNome(nome);
        if(produtoDtoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(produtoDtoOptional);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
    }

    @GetMapping
    public List<ProdutoDto> listarProdutos(){

        List<ProdutoDto> produtoDtos = produtoService.listarProdutos();
        produtoDtos.forEach(produtoDto -> System.out.println(produtoDtos));
        return produtoDtos;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarPorId(@PathVariable(value = "id") Long id){
        Optional<ProdutoDto> produtoDtoOptional = produtoService.buscarProduto(id);
        if(produtoDtoOptional.isPresent()){
            produtoService.deletarProduto(id);
            return ResponseEntity.status(HttpStatus.OK).body(produtoDtoOptional);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
    }

}
