package com.irb.plantas.rest;

import com.irb.plantas.model.User;
import com.irb.plantas.useCase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApi {

  private final UserUseCase userUseCase;

  @PostMapping
  public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
    return new ResponseEntity<>(userUseCase.createUser(user), HttpStatus.ACCEPTED);
  }

  @GetMapping(value = "/users")
  public ResponseEntity<List<User>> getUsers() {
    return new ResponseEntity<>(userUseCase.findAllUsers(), HttpStatus.ACCEPTED);
  }

  @GetMapping(value = "/{userPhone}")
  public ResponseEntity<User> getUser(@PathVariable("userPhone") String userPhone) {
    return new ResponseEntity<>(userUseCase.getUser(userPhone), HttpStatus.ACCEPTED);
  }

  @DeleteMapping
  public void deleteUser(@RequestParam UUID idUser) {
    userUseCase.deleteUser(idUser);
  }

  }