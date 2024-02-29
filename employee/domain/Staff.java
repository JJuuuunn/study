package employee.domain;

import employee.PayRaiseRate;
import lombok.experimental.SuperBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

@SuperBuilder
public class Staff extends Employee {

    public Staff(String strENo, String strName, int nYear, int nMonth, int nDate) {
        super(strENo, strName, nYear, nMonth, nDate);
        super.setRole("Staff");
        super.setFirstPay(2000L);
        super.setNowPay(getPay());
    }

    //	 GETTER
    public long getPay() {
        long nServeYear = super.getServeYear();
        long nPay = getFirstPay();
        if (nServeYear >= 0) {
            for (int nCnt = 0; nCnt < nServeYear; nCnt++) {
                nPay += ((nPay * PayRaiseRate.getPayRaiseRate()) / 100);
            }
            return nPay;
        }
        return -1;
    }

    public static Staff from(ResultSet rs) throws SQLException {
        Staff staff = Staff.builder()
                .m_strENo(rs.getString("eno"))
                .m_strName(rs.getString("name"))
                .m_nYear(rs.getInt("enteryear"))
                .m_nMonth(rs.getInt("entermonth"))
                .m_nDate(rs.getInt("enterday"))
                .m_strRole("Staff")
                .m_nFirstPay(2000L)
                .build();

        staff.setEnteringDay();
        staff.setNowPay(staff.getPay());

        return staff;
    }
}
