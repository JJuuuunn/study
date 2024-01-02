package employee;

import lombok.Builder;
import lombok.experimental.SuperBuilder;

import java.util.GregorianCalendar;

@Builder
public abstract class Employee {
	private String m_strENo;
	private String m_strName;
	private GregorianCalendar m_EnteringDay = new GregorianCalendar();
	private long m_nFirstPay;
	private long m_nNowPay;
	private String m_strRole;
	private String m_strSecNo;
	private int m_nYear;
	private int m_nMonth;
	private int m_nDate;

	Employee(String strENo, String strName, int nYear, int nMonth, int nDate)
	{
		setENo(strENo);
		setName(strName);
		setEnteringDay(nYear, nMonth, nDate);
		setYear(nYear);
		setMonth(nMonth);
		setDate(nDate);
	}

	// GETTER
	public String getENo() { return m_strENo; }
	public String getName() { return m_strName; }
	public long getServeYear() { return ( (GregorianCalendar.getInstance().getTimeInMillis() - m_EnteringDay.getTimeInMillis()) / (24L * 60L * 60L * 1000L * 365L)); }
	public long getFirstPay() { return m_nFirstPay; }
	public long getNowPay() { return m_nNowPay; }
	public abstract long getPay();
	public String getRole() { return m_strRole; }
	public String getSecNo() { return m_strSecNo; }
	public int getYear() { return m_nYear; }
	public int getMonth() { return m_nMonth; }
	public int getDate() { return m_nDate; }

	// SETTER
	public void setENo(String strENo) { m_strENo = strENo; }
	public void setName(String strName) { m_strName = strName; }
	public void setEnteringDay(int nYear, int nMonth, int nDate)
	{
		m_EnteringDay.clear();
		m_EnteringDay.set(nYear, nMonth, nDate);
	}
	public void setFirstPay(long nFirstPay) { m_nFirstPay = nFirstPay; }
	public void setNowPay(long nNowPay) { m_nNowPay = nNowPay; }
	public void setRole(String strRole) { m_strRole = strRole; }
	public void setSecNo(String strSecNo) { m_strSecNo = strSecNo; }
	public void setYear(int nYear) { m_nYear = nYear; }
	public void setMonth(int nMonth) { m_nMonth = nMonth; }
	public void setDate(int nDate) { m_nDate = nDate; }
}
