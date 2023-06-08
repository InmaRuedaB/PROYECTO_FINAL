package com.irb.plantas.useCase;

import com.irb.plantas.exceptions.GenericApiException;
import com.irb.plantas.exceptions.NotFoundException;
import com.irb.plantas.model.User;
import com.irb.plantas.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.irb.plantas.service.mail.MailService;
import com.irb.plantas.util.UtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserUseCase {

  final UserRepository userRepository;
  final BCryptPasswordEncoder passwordEncoder;
  final MailService mailService;

  public User getUser(String phone) {
    return this.userRepository.findByPhone(phone).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
  }

  public User createUser(User user) {

    Optional<User> aux = userRepository.findByPhone(user.getPhone());
    if(aux.isPresent()){
      throw new GenericApiException(HttpStatus.CONFLICT.value(), "El teléfono ya existe",null);
    }
    if(!UtilService.validarMail(user.getMail())) {
      throw new GenericApiException(HttpStatus.BAD_REQUEST.value(), "El mail no tiene el formato correcto",null);
    }
    user.setId(UUID.randomUUID());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    if(user.getRoles() == null || user.getRoles().isEmpty()) {
      user.setRoles(List.of(User.RolesEnum.ROLE_USER));
    }

    User createdUser = this.userRepository.save(user);
    //this.mailService.sendRegistrationMail(user.getMail());
    return createdUser;
  }

  public List<User> findAllUsers() {
    return this.userRepository.findAll();
  }

  public void deleteUser(UUID userId) {
    this.userRepository.deleteById(userId);
  }
}
