package employee.entity;

public enum AccessRole {
    // TODO : 접근 권한 어디까지 만들어야 할까?
    UNTIL_STAFF(1), // 직원 정보까지 접근 가능한 권한
    UNTIL_SECRETARY(2), // 비서 정보까지 접근 가능한 권한
    UNTIL_MANAGER(3), // 매니저 정보까지 접근 가능한 권한
    UNTIL_OWNER(4); // 모든 정보까지 접근 가능한 권한

    private int value;

    AccessRole(int value) {
        this.value = value;
    }
}
