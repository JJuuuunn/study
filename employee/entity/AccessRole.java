package employee.entity;

import lombok.Getter;

@Getter
public enum AccessRole {
    UNTIL_STAFF(1, "직원 정보까지 접근 가능"),
    UNTIL_SECRETARY(2, "비서 정보까지 접근 가능"),
    UNTIL_MANAGER(3, "매니저 정보까지 접근 가능"),
    UNTIL_OWNER(4, "모든 정보에 접근 가능");

    private int value;
    private String desc;

    AccessRole(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}