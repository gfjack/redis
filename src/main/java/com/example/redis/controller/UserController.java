package com.example.redis.controller;

import com.example.redis.model.User;
import com.example.redis.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/rest/")
public class UserController {

  private UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/add/{id}/{name}")
  public User add(@PathVariable("id") final String id, @PathVariable("name") final String name) {
    userRepository.save(new User(id, name, 2000L));

    return userRepository.findById(id);
  }

  @PostMapping("/add/{id}/{name}")
  public User update(@PathVariable("id") final String id, @PathVariable("name") final String name) {
    userRepository.update(new User(id, name, 2000L));

    return userRepository.findById(id);
  }

	@GetMapping("/all")
	public Map<String, User> add() {
		return userRepository.findAll();
	}

}
