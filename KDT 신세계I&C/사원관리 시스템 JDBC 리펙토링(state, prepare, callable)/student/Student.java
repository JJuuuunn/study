package student;

public class Student {
	static enum GRADE {A, B, C, D, F}
	private String sno;
	private String name;
	private int [] record = new int[4];;
	private int total;
	private float average;
	private String grade;
	private int rank = -1;
	
	public Student(String sno, String name, int korean, int english, int math, int science) {
		this.sno = sno;
		this.name = name;
		this.record[0] = korean;
		this.record[1] = english;
		this.record[2] = math;
		this.record[3] = science;
		
		this.makeScores();
	}
	
	public Student(String sno, String name, int korean, int english, int math, int science, int total, float average, String grade) {
		this.sno = sno;
		this.name = name;
		this.record[0] = korean;
		this.record[1] = english;
		this.record[2] = math;
		this.record[3] = science;
		this.total = total;
		this.average = average;
		this.grade = grade;
	}

	private void makeScores() {
		for (int i = 0 ; i < 4 ; i++)
			this.total += this.record[i];
		this.average = (float) this.total / 4;
		this.makeGrade();
	}
	
	private void makeGrade() {
		if (90 <= this.average && this.average <= 100)
			this.grade = GRADE.A.toString();
		
		else if ( 80 <= this.average && this.average < 90)
			this.grade = GRADE.B.toString();
		
		else if ( 70 <= this.average && this.average < 80)
			this.grade = GRADE.C.toString();
		
		else if ( 60 <= this.average && this.average < 70)
			this.grade = GRADE.D.toString();
		
		else if ( 0 <= this.average && this.average < 60)
			this.grade = GRADE.F.toString();
	}

	public float getAverage() {
		return average;
	}

	public String getGrade() {
		return grade;
	}

	public String getName() {
		return name;
	}
	
	public String getSNo() {
		return sno;
	}

	public int[] getRecord() {
		return record;
	}

	public int getTotal() {
		return total;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
}