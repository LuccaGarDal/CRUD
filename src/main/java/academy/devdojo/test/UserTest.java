package academy.devdojo.test;

import academy.devdojo.domain.User;
import academy.devdojo.repository.UserRepository;
import academy.devdojo.service.UserService;

public class UserTest {
    static void main() {
        User user  = User.builder()
                .name("Luccao")
                .age(21)
                .id(2)
                .build();

        UserService.update(user);

    }
}
