package employee.dto;

import lombok.Builder;
import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Getter
@Builder
public class EmployeeWithLevelDto {
    private String eno;
    private String name;
    private Long role;
    private LocalDateTime expiredAt;

    public EmployeeWithLevelDto from(ResultSet rs) throws SQLException {
        return EmployeeWithLevelDto.builder()
                .eno(rs.getString("e.eno"))
                .name(rs.getString("e.name"))
                .role(rs.getLong("r.role"))
                .expiredAt(rs.getTimestamp("r.expiredAt").toLocalDateTime())
                .build();
    }
}
