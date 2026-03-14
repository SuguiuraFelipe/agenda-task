package com.suguiura.Agendatask.infrastructure.security;


import com.suguiura.Agendatask.business.dto.UserDTO;
import com.suguiura.Agendatask.infrastructure.client.UserClient;
import com.suguiura.user.infrastructure.entity.UserEntity;
import com.suguiura.user.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UserClient client;

    // Implementação do método para carregar detalhes do usuário pelo e-mail
    public UserDetails loadUserByUsername(String email, String token){
        UserDTO userDTO = client.findUserByEmail(email, token);
        return User
                .withUsername(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
    }
}
