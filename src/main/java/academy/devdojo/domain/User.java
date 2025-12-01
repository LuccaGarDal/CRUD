package academy.devdojo.domain;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class User {
    int id;
    String name;
    int age;
}
