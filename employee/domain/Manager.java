package employee.domain;

import employee.PayRaiseRate;
import lombok.experimental.SuperBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

@SuperBuilder
public class Manager extends Employee {
    public Manager(String strENo, String strSecNo, String strName, int nYear, int nMonth, int nDate) {
        super(strENo, strName, nYear, nMonth, nDate);
        super.setRole("Manager");
        super.setFirstPay(2000L);
        super.setSecNo(strSecNo);
        super.setNowPay(getPay());
    }

    //	 GETTER
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
