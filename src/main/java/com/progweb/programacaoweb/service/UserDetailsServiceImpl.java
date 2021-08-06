package com.progweb.programacaoweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.progweb.programacaoweb.dao.UserDAO;
import com.progweb.programacaoweb.model.User;
import com.progweb.programacaoweb.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        User user = null;
        for (User i : userDAO.findAll()){
            if (i.getNome().equals(usuario)){
                user = i;
            }
        }

        if (user == null){
            throw new UsernameNotFoundException("Usuario nao encontrado");
        }

        return new UserSS(user.getId(), user.getNome(), user.getSenha(), user.getPerfis());
    }
}
