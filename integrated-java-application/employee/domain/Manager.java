package employee.domain;

import employee.PayRaiseRate;
import lombok.experimental.SuperBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Manager 클래스는 직원의 역할을 하는 매니저를 나타냅니다.
 * Employee 클래스를 확장하고 그 속성과 메소드를 상속합니다.
 */
@SuperBuilder
public class Manager extends Employee {

    /**
     * 제공된 매개변수로 Manager 객체를 생성합니다.
     *
     * @param strENo   직원 번호
     * @param strSecNo 섹션 번호
     * @param strName  직원 이름
     * @param nYear    입사 연도
     * @param nMonth   입사 월
     * @param nDate    입사 일
     */

    public Manager(String strENo, String strSecNo, String strName, int nYear, int nMonth, int nDate) {
        super(strENo, strName, nYear, nMonth, nDate);
        super.setRole("Manager");
        super.setFirstPay(2000L);
        super.setSecNo(strSecNo);
        super.setNowPay(getPay());
    }

    /**
     * 직원의 근속 연수 및 매니저의 특별 보너스를 고려하여 급여를 계산하여 반환합니다.
     *
     * @return 직원의 급여
     */
    public long getPay() {
        long nServeYear = super.getServeYear();
        long nPay = getFirstPay();
        if (nServeYear >= 0) {
            for (int nCnt = 0; nCnt < nServeYear; nCnt++) {
                nPay += ((nPay * ((PayRaiseRate.getPayRaiseRate() * 10) + (nServeYear * 5))) / 1000);
            }
            return nPay;
        }
        return -1;
    }

    /**
     * ResultSet에서 검색된 데이터를 기반으로 Manager 객체를 생성합니다.
     *
     * @param rs 직원 데이터를 포함하는 ResultSet
     * @return ResultSet 데이터로 생성된 Manager 객체
     * @throws SQLException 데이터베이스 접근 오류가 발생한 경우
     */
    public static Manager from(ResultSet rs) throws SQLException {
        Manager manager = Manager.builder()
                .m_strENo(rs.getString("eno"))
                .m_strName(rs.getString("name"))
                .m_nYear(rs.getInt("enteryear"))
                .m_nMonth(rs.getInt("entermonth"))
                .m_nDate(rs.getInt("enterday"))
                .m_strSecNo(rs.getString("secno"))
                .m_strRole("Manager")
                .m_nFirstPay(2000L)
                .build();

        manager.setEnteringDay();
        manager.setNowPay(manager.getPay());

        return manager;
    }
}
