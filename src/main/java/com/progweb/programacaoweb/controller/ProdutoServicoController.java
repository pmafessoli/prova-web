package com.progweb.programacaoweb.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.progweb.programacaoweb.dao.ProdutoDAO;
import com.progweb.programacaoweb.model.Produto;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoServicoController {

    @Autowired
    private ProdutoDAO produtoDAO;

    @GetMapping
    public List<Produto> Get() {
        return produtoDAO.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> GetById(@PathVariable("id") Long id) {
        Optional<Produto> produto = produtoDAO.findById(id);
        System.out.println(produto.get());
        if(produto.isPresent()){
            return new ResponseEntity<Produto>(produto.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Object> Post(@RequestBody Produto produto) {
        Produto newProduto = produtoDAO.save(produto);
        if(newProduto != null){
            return new ResponseEntity<>("Produto Criado com sucesso!!", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Produto> Put(@PathVariable("id") Long id, @RequestBody Produto newProduto) {
        Optional<Produto> oldProduto = produtoDAO.findById(id);
        if(oldProduto.isPresent()){
            Produto produto = oldProduto.get();
            produto.setNome(newProduto.getNome());
            produto.setPreco(newProduto.getPreco());
            produtoDAO.save(produto);
            return new ResponseEntity<Produto>(produto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> Delete(@PathVariable("id") Long id) {
        Optional<Produto> produto = produtoDAO.findById(id);
        if(produto.isPresent()){
            produtoDAO.delete(produto.get());
            return new ResponseEntity<>("Produto deletado com sucesso!!", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
