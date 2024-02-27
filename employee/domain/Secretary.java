package employee.domain;

import lombok.experimental.SuperBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

@SuperBuilder
public class Secretary extends Staff {
	
	public Secretary(String strENo, String strName, int nYear, int nMonth, int nDate) {
		super(strENo, strName, nYear, nMonth, nDate);
		super.setRole("Secretary");
		super.setNowPay(super.getPay());
	}

	public static Secretary from(ResultSet rs) throws SQLException {
		Secretary secretary = Secretary.builder()
				.m_strENo(rs.getString("eno"))
				.m_strName(rs.getString("name"))
				.m_nYear(rs.getInt("enteryear"))
				.m_nMonth(rs.getInt("entermonth"))
				.m_nDate(rs.getInt("enterday"))
				.m_strRole("Secretary")
				.m_nFirstPay(2000L)
				.build();

		secretary.setEnteringDay();
		secretary.setNowPay(secretary.getPay());

		return secretary;
	}
}