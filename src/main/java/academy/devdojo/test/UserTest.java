package academy.devdojo.test;

import academy.devdojo.domain.User;
import academy.devdojo.repository.UserRepository;
import academy.devdojo.service.UserService;
import lombok.extern.log4j.Log4j2;

import java.util.Scanner;

@Log4j2
public class UserTest {
    static void main() {
        Scanner scanner1 = new Scanner(System.in );
        int opt = 1;
        while (opt != 0) {
            menu();
            opt = Integer.parseInt(scanner1.nextLine());
            UserService.menu(opt);
        }
    }

    public static void menu () {
        log.info("--------Select the number acording with your option-------");
        log.info("1 - Save new user");
        log.info("2 - Find users");
        log.info("3 - Update user");
        log.info("4 - Delete user");
        log.info("0 - Exit");
    }
}
