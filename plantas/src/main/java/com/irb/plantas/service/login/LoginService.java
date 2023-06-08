package com.irb.plantas.service.login;

import com.irb.plantas.exceptions.NotFoundException;
import com.irb.plantas.model.User;
import com.irb.plantas.repository.UserRepository;
import com.irb.plantas.useCase.auth.LoginUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    final UserRepository userRepository;

    final LoginUseCase loginUseCase;


    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        try {
            User user = this.loginUseCase.getUser(username);
            Collection<GrantedAuthority> authorities = user.getRoles().stream()
                    .map(rolesEnum -> new SimpleGrantedAuthority(rolesEnum.getValue())).collect(Collectors.toSet());
            return new org.springframework.security.core.userdetails.User(user.getPhone(), user.getPassword(), authorities);
        }catch (NotFoundException e) {
            throw new NotFoundException("Usuario no encontrado");
        }
    }

}
