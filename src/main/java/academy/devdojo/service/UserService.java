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
        log.info("Inserted {} as new user", user.getName());
    }

    public static void findByName (String name) {
        log.info("Finding user by name {}", name);
        List<User> usersByName = UserRepository.findByName(name);
        usersByName.forEach(u -> System.out.printf("%d - %s, %d years%n", u.getId(), u.getName(), u.getAge()));
    }

    public static void delete (int id) {
        log.info("Deleting user by id {}", id);
        UserRepository.delete(id);
        log.info("User {} deleted", id);
    }

    public static void update (User user) {
        log.info("Updating user {}", user.getId());
        UserRepository.update(user);
        log.info("User {} update", user.getId());
    }
}
