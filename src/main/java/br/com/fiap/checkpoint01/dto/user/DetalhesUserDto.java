package br.com.fiap.checkpoint01.dto.user;

import br.com.fiap.checkpoint01.model.user.User;

public record DetalhesUserDto(Long id, String username, String email) {

    public DetalhesUserDto(User user) {
        this(user.getId(),user.getUsername(), user.getEmail());
    }

}