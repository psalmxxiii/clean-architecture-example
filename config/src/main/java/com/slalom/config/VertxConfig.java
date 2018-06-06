package com.slalom.config;

import com.slalom.example.db.InMemoryUserRepository;
import com.slalom.example.domain.ports.IdGenerator;
import com.slalom.example.domain.ports.PasswordEncoder;
import com.slalom.example.domain.ports.UserRepository;
import com.slalom.example.domain.usecases.CreateUser;
import com.slalom.example.domain.usecases.FindUser;
import com.slalom.example.domain.usecases.LoginUser;
import com.slalom.example.encoder.Sha256PasswordEncoder;
import com.slalom.example.jug.JugIdGenerator;
import com.slalom.example.vertx.controller.VertxUserController;

public class VertxConfig {

	private final UserRepository userRepository = new InMemoryUserRepository();
	private final IdGenerator idGenerator = new JugIdGenerator();
	private final PasswordEncoder passwordEncoder = new Sha256PasswordEncoder();
	private final CreateUser createUser = new CreateUser(userRepository, passwordEncoder, idGenerator);
	private final FindUser findUser = new FindUser(userRepository);
	private final LoginUser loginUser = new LoginUser(userRepository, passwordEncoder);
	private final VertxUserController userController = new VertxUserController(createUser, findUser, loginUser);

	public VertxUserController getVertxUserController() {
		return userController;
	}
}
