package my.homework.service;

import lombok.RequiredArgsConstructor;
import my.homework.enums.Role;
import my.homework.model.User;
import my.homework.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user, Role role) {
        String login = user.getLogin();
        if(userRepository.findByLogin(login).isPresent()) return false;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(role);
        userRepository.save(user);
        return true;
    }

    public boolean createUser(User user) {
        String login = user.getLogin();
        if(userRepository.findByLogin(login).isPresent()) return false;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_READER);
        userRepository.save(user);
        return true;
    }

}
