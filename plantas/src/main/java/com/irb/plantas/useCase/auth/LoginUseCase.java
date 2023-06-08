package com.irb.plantas.useCase.auth;

import com.irb.plantas.exceptions.NotFoundException;
import com.irb.plantas.model.User;
import com.irb.plantas.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class LoginUseCase {

    final UserRepository userRepository;

    public User getUser(String username) throws NotFoundException {
        User user = this.userRepository.findByPhone(username).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        //TODO podríamos guardar la fecha de último acceso/login
        return user;
    }

}
