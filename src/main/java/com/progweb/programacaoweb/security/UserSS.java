package com.progweb.programacaoweb.security;

import com.progweb.programacaoweb.ENUM.Perfil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserSS implements UserDetails {

    private Integer id;
    private String usuario;
    private String senha;
    Collection<? extends GrantedAuthority> authorities;

    public UserSS(){}

    public UserSS(Integer id, String usuario, String senha, Set<Perfil> perfis) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority((x.getDescricao()))).collect(Collectors.toList());
    }

    public Integer getId() {
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
