package academy.devdojo.service;

import academy.devdojo.domain.User;
import academy.devdojo.repository.UserRepository;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class UserService {

    public static void save (User user) {
       log.info("Inserting user {} on database", user.getName());
        UserRepository.save(user);

    }

    public static List<User> findByName (String name) {
        log.info("Finding user by name {}", name);
        List<User> byName = UserRepository.findByName(name);
        log.info(byName);
        return byName;
    }
}
