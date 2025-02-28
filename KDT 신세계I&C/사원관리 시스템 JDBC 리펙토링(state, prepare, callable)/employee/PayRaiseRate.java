package employee;

public class PayRaiseRate {
	private static long m_nPayRaiseRate = 5;
	
	private PayRaiseRate() {}
	
	// GETTER
	public static long getPayRaiseRate() { return m_nPayRaiseRate; }
	// SETTER
	public static void setPayRaiseRate(long nPayRaiseRate) { m_nPayRaiseRate = nPayRaiseRate; }
}
