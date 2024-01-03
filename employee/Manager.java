package employee;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Manager extends Employee {
	public Manager(String strENo, String strSecNo, String strName, int nYear, int nMonth, int nDate)
	{
		super(strENo, strName, nYear, nMonth, nDate);
		super.setRole("Manager");
		super.setFirstPay(2000L);
		super.setSecNo(strSecNo);
		super.setNowPay(getPay());
	}

	//	 GETTER
	public long getPay()
	{
		long nServeYear = super.getServeYear();
		long nPay = getFirstPay();
		if(nServeYear >= 0)
		{
			for(int nCnt = 0; nCnt < nServeYear; nCnt++)
			{
				nPay += ( ( nPay * ( (PayRaiseRate.getPayRaiseRate()*10) + (nServeYear * 5) ) ) / 1000 );
			}
			return nPay;
		}
		return -1;
	}
}
