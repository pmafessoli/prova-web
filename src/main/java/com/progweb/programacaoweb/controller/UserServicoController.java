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

import com.progweb.programacaoweb.dao.UserDAO;
import com.progweb.programacaoweb.model.User;

@RestController
@RequestMapping(value = "/usuarios")
public class UserServicoController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping
    public List<User> Get() {
        return userDAO.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> GetById(@PathVariable("id") Long id) {
        Optional<User> user = userDAO.findById(id);
        if(user.isPresent()){
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Object> Post(@RequestBody User user) {
        User newUser = userDAO.save(user);
        if(newUser != null){
            return new ResponseEntity<>("Usuário criado com sucesso!!", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> Put(@PathVariable("id") Long id, @RequestBody User newUser) {
        Optional<User> oldUser = userDAO.findById(id);
        if(oldUser.isPresent()){
            User user = oldUser.get();
            user.setNome(newUser.getNome());
            user.setSenha(newUser.getSenha());
            user.setEmail(newUser.getEmail());
            user.setPhone(newUser.getPhone());
            userDAO.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> Delete(@PathVariable("id") Long id) {
        Optional<User> vendedor = userDAO.findById(id);
        if(vendedor.isPresent()){
            userDAO.delete(vendedor.get());
            return new ResponseEntity<>("Usuário deletado com sucesso!!", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
