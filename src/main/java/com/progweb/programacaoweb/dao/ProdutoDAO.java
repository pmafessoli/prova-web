package com.progweb.programacaoweb.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.progweb.programacaoweb.model.Produto;

@Repository
public interface ProdutoDAO extends JpaRepository<Produto, Long> { }

