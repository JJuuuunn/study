package study.querydsl.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private String name;
    private int age;

    public UserDto(String nmae, int age) {
        this.name = nmae;
        this.age = age;
    }
}
