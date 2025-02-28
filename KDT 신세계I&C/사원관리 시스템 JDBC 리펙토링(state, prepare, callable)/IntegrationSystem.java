import employee.domain.Employee;
import employee.domain.Manager;
import employee.domain.Secretary;
import employee.domain.Staff;
import student.*;
import employee.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class IntegrationSystem {
    static StudentManager stuMan = StudentManager.getInstance();
    static EmployeeManager empMan = EmployeeManager.getInstance();
    //	 콘솔 입력 버퍼 생성
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        IntegrationSystem Instance = new IntegrationSystem();
        String buffer = null;

        while (true) {
            System.out.println("====== [ 메뉴를 선택 하세요 ] ======");
            System.out.println("1. 사원 관리 시스템");
            System.out.println("2. 학생 관리 시스템");
            System.out.println("3. 시스템 종료");
            System.out.println("--------------------------------");

            int nSel = System.in.read() - 48;
            System.in.skip(System.in.available());

            switch (nSel) {
                case 1:
                    while (true) {
                        System.out.println("======= [ 사원 관리 시스템 ] =======");
                        System.out.println("1. 일반사원 추가");
                        System.out.println("2. 비서 추가");
                        System.out.println("3. 관리자 추가");
                        System.out.println("4. 사원정보 보기");
                        System.out.println("5. 사원정보 검색");
                        System.out.println("6. 이전으로");
                        System.out.println("--------------------------------");
                        nSel = System.in.read() - 48;
                        System.in.skip(System.in.available());
                        if (nSel == 1) {
                            System.out.println("데이터를 입력하세요!! : (사원번호, 이름, 입사년도, 입사월, 입사일)");
                            System.out.println("===========================================================");
                            buffer = br.readLine();
                            StringTokenizer st = new StringTokenizer(buffer, ",");
                            Staff emp = new Staff(st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                            Instance.insertStaff(emp);
                        } else if (nSel == 2) {
                            System.out.println("데이터를 입력하세요!! : (사원번호, 이름, 입사년도, 입사월, 입사일)");
                            System.out.println("===========================================================");
                            buffer = br.readLine();
                            StringTokenizer st = new StringTokenizer(buffer, ",");
                            Secretary emp = new Secretary(st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                            Instance.insertSecretary(emp);
                        } else if (nSel == 3) {
                            System.out.println("데이터를 입력하세요!! : (사원번호, 비서번호, 이름, 입사년도, 입사월, 입사일)");
                            System.out.println("===========================================================");
                            buffer = br.readLine();
                            StringTokenizer st = new StringTokenizer(buffer, ",");
                            Manager emp = new Manager(st.nextToken(), st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                            Instance.insertManager(emp);
                        } else if (nSel == 4) {
                            System.out.println("본인의 사원 번호를 입력 하세요.");
                            System.out.println("===========================================================");
                            buffer = br.readLine();
                            Instance.getEmployeeList(buffer);
                        } else if (nSel == 5) {
                            System.out.println("본인의 사원 번호를 입력 하세요.");
                            String eno = br.readLine();
                            System.out.println("검색 할 사원 번호를 입력 하세요. (관리자 검색 가능)");
                            System.out.println("===========================================================");
                            buffer = br.readLine();
                            Instance.searchEmployee(eno, buffer);
                        } else if (nSel == 6) {
                            break;
                        }
                    }
                    continue;
                case 2:
                    while (true) {
                        System.out.println("======= [ 학생 관리 시스템 ] =======");
                        System.out.println("1. 학생 추가");
                        System.out.println("2. 학생 전체 보기");
                        System.out.println("3. 학생 검색");
                        System.out.println("4. 학생 석차순으로 보기");
                        System.out.println("5. 이전으로");
                        System.out.println("--------------------------------");
                        nSel = System.in.read() - 48;
                        System.in.skip(System.in.available());
                        switch (nSel) {
                            case 1:
                                Instance.insertStudent();
                                continue;
                            case 2:
                                Instance.getStudentList();
                                continue;
                            case 3:
                                Instance.searchStudent();
                                continue;
                            case 4:
                                Instance.sortedStudent();
                                continue;
                            case 5:
                                break;
                            default:
                                continue;
                        }
                        break;
                    }
                    continue;
                case 3:
                    break;
                default:
                    continue;
            }
            break;
        }
		/*
		EmployeeDBIO empDB = EmployeeDBIO.getInstance();
		Manager emp = new Manager("111", "000", "이번남비서", 2003, 1, 1);
		empDB.insertManager(emp);
		*/
        //ArrayList<EmployeeDBIO> empArray = emp.insertStaff(emp)
    }

    // 학생 삽입
    public void insertStudent() {
        try {
            System.out.println("데이터를 입력하세요!! : (학번, 이름, 국어, 영어, 수학, 과학)");
            System.out.println("입력 종료 : exit");
            System.out.println("===========================================================");

            String buffer = null;
            String sno = null;
            String name = null;

            Student student = null;

            // 라인입력 받아서 exit가 아닐때까지 반복
            while (!(buffer = br.readLine()).equals("exit")) {
                try {
                    // 토큰 구분자 등록해서 객체 생성
                    StringTokenizer st = new StringTokenizer(buffer, ",");

                    // 첫번째 토큰(이름) 이 null이 아닐때
                    if ((sno = st.nextToken()) != null) {
                        name = st.nextToken();
                        // 성적을 토큰단위로 읽어내서 저장
                        int[] record = new int[4];
                        record[0] = Integer.parseInt(st.nextToken());
                        record[1] = Integer.parseInt(st.nextToken());
                        record[2] = Integer.parseInt(st.nextToken());
                        record[3] = Integer.parseInt(st.nextToken());
                        // 학생 객체 생성
                        student = new Student(sno, name, record[0], record[1], record[2], record[3]);
                        stuMan.insertStudent(student);
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("입력 형식에 알맞지 않은 입력입니다. 다시 입력해 주십시오.");
                    continue;
                } catch (NumberFormatException e) {
                    System.out.println("성적 입력은 숫자로만 입력이 가능합니다. 다시 입력해 주십시오.");
                    continue;
                }
            }
            System.out.println("학생입력이 완료 되었습니다!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sortedStudent() {
        ArrayList<Student> stuArray = stuMan.getSortedStudent();

        for (Student stu : stuArray) {
            System.out.println(stu.getSNo() + ", " + stu.getName() + ", " + stu.getTotal() + ", " + stu.getRank());
        }
    }

    public void getStudentList() {
        ArrayList<Student> stuArray = stuMan.getStudentList();

        for (Student stu : stuArray) {
            System.out.println(stu.getSNo() + ", " + stu.getName() + ", " + stu.getTotal());
        }
    }

    public void searchStudent() {
        String strSNo = null;
        try {
            strSNo = br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<Student> stuArray = stuMan.searchStudent(strSNo);

        for (Student stu : stuArray) {
            System.out.println(stu.getSNo() + ", " + stu.getName() + ", " + stu.getTotal());
        }
    }

    public boolean insertStaff(Staff emp) {
        return empMan.insertStaff(emp);
    }

    public boolean insertSecretary(Secretary emp) {
        return empMan.insertSecretary(emp);
    }

    public boolean insertManager(Manager emp) {
        return empMan.insertManager(emp);
    }

    public void getEmployeeList(String strUserID) {
        ArrayList<Employee> resArray = empMan.getEmployeeList(strUserID);

        if (!resArray.isEmpty()) {
            System.out.printf("%-5s \t %-7s \t %-10s \t %-5s \t %-10s\n", "eno", "name", "date", "secNo", "pay");
            System.out.println("===========================================================");
            for (Employee emp : resArray) {
                System.out.printf("%-5s \t %-7s \t %04d.%02d.%02d \t %-5s \t %-10d\n", emp.getENo(), emp.getName(),
                        emp.getYear(), emp.getMonth(), emp.getDate(),
                        emp.getSecNo(), emp.getPay());
            }
        }
    }

    public void searchEmployee(String eno, String strENo) {
        ArrayList<Employee> resArray = empMan.searchEmployee(eno, strENo);
        for (Employee emp : resArray) {
            System.out.printf("%-5s \t %-7s \t %-10s \t %-5s \t %-10s\n", "eno", "name", "date", "secNo", "pay");
            System.out.println("===========================================================");
            System.out.printf("eno \t: %-5s\nname \t: %-7s\ndat \t: %04d.%02d.%02d\nsecNo \t: %-5s\npay \t: %-10d\n", emp.getENo(), emp.getName(),
                    emp.getYear(), emp.getMonth(), emp.getDate(),
                    emp.getSecNo(), emp.getPay());

        }
    }
}