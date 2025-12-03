package academy.devdojo.test;

import academy.devdojo.domain.User;
import academy.devdojo.repository.UserRepository;
import academy.devdojo.service.UserService;

public class UserTest {
    static void main() {
        User user  = User.builder()
                .name("")
                .age(21)
                .build();

        UserService.findByName(user.getName());
    }
}
