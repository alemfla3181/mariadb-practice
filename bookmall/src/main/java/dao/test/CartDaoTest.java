package dao.test;

import java.util.List;

import dao.CartDao;
import vo.CartVo;

public class CartDaoTest {
	public static void main(String[] args) {
//		testInsert("1", 1L, 1L);
//		testUpdate("이것이 자바다", 2L);
		testFind();
	}

	public static void testInsert(String amount, Long no, Long member_no) {
		CartDao dao = new CartDao();
		CartVo vo = new CartVo();

		vo.setAmount(amount);
		vo.setNo(no);
		vo.setMember_no(no);
		dao.insert(vo);

	}

	private static void testFind() {
		List<CartVo> list = new CartDao().find();
		for (CartVo vo : list) {
			System.out.println(vo);
		}
	}

	public static void testUpdate(String amount, Long no) {
		CartDao dao = new CartDao();
		CartVo vo = new CartVo();

		vo.setAmount(amount);
		vo.setNo(no);
		dao.update(vo);
	}

}
