package com.example.video_voting.service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

import com.example.video_voting.model.User;
import com.example.video_voting.repository.UserRepository;
import com.example.video_voting.util.PasswordUtil;
import com.example.video_voting.model.supporting.*;

/**
 * UserService
 */
public class UserService {

  private final UserRepository userRepo;

  public UserService() {
    this.userRepo = new UserRepository();
  }

  public List<User> getNonAdminUsers() {
    return userRepo.fetchAll().stream()
        .filter(user -> !user.getRoles().contains(Role.ADMIN))
        .collect(Collectors.toList());
  }

  public User createUser(User user) {
    user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
    return userRepo.save(user);
  }

  public Optional<User> getById(Long id) {
    return userRepo.fetchById(id);
  }

  public Optional<User> getByUsername(String username) {
    return userRepo.fetchByUsername(username);
  }

  public void deleteUser(Long id) {
    userRepo.delete(id);
  }

  public Boolean authenticate(String username, String password) {
    Optional<User> optionalUser = userRepo.fetchByUsername(username);

    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      return PasswordUtil.checkPassword(password, user.getPassword());
    }

    return false;

  }

}
