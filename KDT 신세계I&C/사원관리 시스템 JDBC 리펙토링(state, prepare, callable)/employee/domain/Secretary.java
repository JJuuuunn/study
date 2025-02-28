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

	/**
	 * 주어진 ResultSet으로부터 Secretary 객체를 생성합니다.
	 *
	 * @param rs Secretary의 데이터를 포함한 ResultSet입니다.
	 * @return ResultSet로부터 생성된 Secretary 객체입니다.
	 * @throws SQLException 데이터베이스 접근 오류가 발생한 경우.
	 */
	public static Secretary from(ResultSet rs) throws SQLException {
		// ResultSet에서 데이터를 추출하고 Secretary 객체를 생성합니다.
		Secretary secretary = Secretary.builder()
				.m_strENo(rs.getString("eno"))
				.m_strName(rs.getString("name"))
				.m_nYear(rs.getInt("enteryear"))
				.m_nMonth(rs.getInt("entermonth"))
				.m_nDate(rs.getInt("enterday"))
				.m_strRole("Secretary")
				.m_nFirstPay(2000L)
				.build();

		// Secretary의 입사일을 설정합니다.
		secretary.setEnteringDay();

		// Secretary의 현재 급여를 설정합니다.
		secretary.setNowPay(secretary.getPay());

		return secretary;
	}
}