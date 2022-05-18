package hr.dao.test;

import java.util.List;

import hr.vo.EmployeeDao;
import hr.vo.EmployeeVo;

public class EmployeeDaoTest {

	public static void main(String[] args) {
		// testFindByFirstNameOrLastName("ken");
		testFindBySalary(20000, 40000);
	}

	public static void testFindByFirstNameOrLastName(String name) {
		List<EmployeeVo> list = new EmployeeDao().findByFirstNameOrLastName(name);
		for (EmployeeVo vo : list) {
			System.out.println(vo);
		}
	}

	public static void testFindBySalary(int min, int max) {
		List<EmployeeVo> list = new EmployeeDao().findBySalary(min, max);
		for (EmployeeVo vo : list) {
			System.out.println(vo);
		}
	}

}