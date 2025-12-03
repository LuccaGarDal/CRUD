package academy.devdojo.service;

import academy.devdojo.domain.User;
import academy.devdojo.repository.UserRepository;
import academy.devdojo.test.UserTest;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Scanner;

@Log4j2
public class UserService {
    private static Scanner scanner = new Scanner(System.in);

    public static void menu (int opt) {
        switch (opt) {
            case 1:
                save();
                break;
            case 2:
                findByName();
                break;
            case 3:
                update();
                break;
            case 4:
                delete();
                break;
        }
    }

    public static void save () {
        log.info("Type the name of the user: ");
        String name = scanner.nextLine();
        log.info("Type the age of the user: ");
        int age = Integer.parseInt(scanner.nextLine());
        User user = User.builder()
                .name(name)
                .age(age)
                .build();
        log.info("Inserting user {} on database", user.getName());
        UserRepository.save(user);
        log.info("Inserted {} as new user", user.getName());
    }

    public static void findByName () {
        log.info("Type the name of the user or empty to all");
        String name = scanner.nextLine();
        log.info("Finding user by name {}", name);
        List<User> usersByName = UserRepository.findByName(name);
        usersByName.forEach(u -> System.out.printf("%d - %s, %d years%n", u.getId(), u.getName(), u.getAge()));
    }

    public static void update () {
        log.info("Type the new name");
        String name = scanner.nextLine();
        log.info("Type the new age");
        int age = Integer.parseInt(scanner.nextLine());
        log.info("Type the id");
        int id = Integer.parseInt(scanner.nextLine());
        User user = User.builder()
                .name(name)
                .age(age)
                .id(id)
                .build();
        log.info("Updating user {}", user.getId());
        UserRepository.update(user);
        log.info("User {} update", user.getId());
    }

    public static void delete () {
        log.info("Type the id of the user you want to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        log.info("Are you sure? Y/S");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("y")) {
            log.info("Deleting user by id {}", id);
            UserRepository.delete(id);
            log.info("User {} deleted", id);
        } else {
            UserTest.menu();
        }
    }
}
