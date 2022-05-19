package dao.test;

import java.util.List;

import dao.MemberDao;
import vo.MemberVo;

public class MemberDaoTest {

	public static void main(String[] args) {
//		testInsert();
//		testDelete(4);
//		testUpdate("둘리", 1L);
		testFind();

	}

	public static void testInsert(String name, String phone, String email, String password) {
		MemberDao dao = new MemberDao();
		MemberVo vo = new MemberVo();

		vo.setName(name);
		vo.setPhone(phone);
		vo.setEmail(email);
		vo.setPassword(password);
		dao.insert(vo);

	}

	private static void testFind() {
		List<MemberVo> list = new MemberDao().find();
		for (MemberVo vo : list) {
			System.out.println(vo);
		}
	}

	public static void testUpdate(String name, Long no) {
		MemberDao dao = new MemberDao();
		MemberVo vo = new MemberVo();

		vo.setName(name);
		vo.setNo(no);
		dao.update(vo);
	}
}
