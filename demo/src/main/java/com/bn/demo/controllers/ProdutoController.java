package com.bn.demo.controllers;

import com.bn.demo.models.ProdutoModel;
import com.bn.demo.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = {"/produtos", "/produtos/"})
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
        public ResponseEntity<List<ProdutoModel> > buscarTodosOsProdutos(){
          List<ProdutoModel> requeste = produtoService.buscarTodosProdutos();
        return ResponseEntity.ok().body(requeste);
    }

    @PostMapping
    public ResponseEntity <ProdutoModel> criarProdutos(@RequestBody ProdutoModel produtoModel){
        ProdutoModel requeste = produtoService.criarProduto(produtoModel);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(produtoModel.getId())
                .toUri();
        return  ResponseEntity.created(uri).body(requeste);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarProdutos(@PathVariable Long id){
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public Optional<ProdutoModel> buscarProdutoPorId(@PathVariable Long id){
        return  produtoService.buscarProdutoId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity <ProdutoModel> atualizarProdutos(@PathVariable Long id, @RequestBody ProdutoModel ProdutoModel){
        ProdutoModel requeste = produtoService.atualizarProduto(id, ProdutoModel);
        return  ResponseEntity.ok().body(requeste);

    }








}
