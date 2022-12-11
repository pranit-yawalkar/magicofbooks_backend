package com.magicofbooks.backend;

import com.magicofbooks.backend.model.Role;
import com.magicofbooks.backend.model.User;
import com.magicofbooks.backend.repository.UserRepository;
import com.magicofbooks.backend.service.impl.RoleServiceImpl;
import com.magicofbooks.backend.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class  MagicofbooksBackendApplication implements CommandLineRunner {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private RoleServiceImpl roleService;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(MagicofbooksBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Role role = new Role("admin", "Role for the admin of application.");
//		this.roleService.createRole(role);
//
//		Role role2 = new Role("user", "Default role for normal user register into the application.");
//		this.roleService.createRole(role2);

//
//		Role role = this.roleService.getRoleByRoleName("admin");
//		Set<Role> roles = new HashSet<>();
//		roles.add(role);
//		User user = new User();
//		user.setEmail("admin@magicofbooks.com");
//		user.setFirstName("admin");
//		user.setLastName("admin");
//		user.setPassword(this.userService.getEncodedPassword("admin123"));
//		user.setRoles(roles);
//
//		userRepository.save(user);
	}
}
