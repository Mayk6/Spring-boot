package my.homework;

import my.homework.enums.Role;
import my.homework.model.User;
import my.homework.repository.UserRepository;
import my.homework.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HomeworkApplication {

	public static void main(String[] args) {
		UserService userService = SpringApplication.run(HomeworkApplication.class, args).getBean(UserService.class);

		User user = new User();
		user.setId(1L);
		user.setLogin("login");
		user.setPassword("pass");
		userService.createUser(user);
		User user1 = new User();
		user1.setId(2L);
		user1.setLogin("login1");
		user1.setPassword("pass1");
		System.out.println(Role.ROLE_ADMIN);
		userService.createUser(user1, Role.ROLE_ADMIN);




	}

}
