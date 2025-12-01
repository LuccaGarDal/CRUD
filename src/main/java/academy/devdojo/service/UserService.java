package academy.devdojo.service;

import academy.devdojo.domain.User;
import academy.devdojo.repository.UserRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class UserService {

    public static void save (User user) {
       log.info("Inserting user {} on database", user.getName());
        UserRepository.save(user);

    }
}
