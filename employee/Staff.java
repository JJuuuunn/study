package employee;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Staff extends Employee {


	public Staff(String strENo, String strName, int nYear, int nMonth, int nDate)
	{
		super(strENo, strName, nYear, nMonth, nDate);
		super.setRole("Staff");
		super.setFirstPay(2000L);
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
				nPay += ( ( nPay * PayRaiseRate.getPayRaiseRate() ) / 100 );
			}
			return nPay;
		}
		return -1;
	}
}
