package dao.test;

import java.util.List;

import dao.OrderDao;
import vo.OrderVo;

public class OrderDaoTest {

	public static void main(String[] args) {
//		testInsert("40000", "SSDASDSD", 1L);
//		testDelete(4);
//		testUpdate("둘리", 1L);
		testFind();

	}

	public static void testInsert(String payment, String address, Long no) {
		OrderDao dao = new OrderDao();
		OrderVo vo = new OrderVo();

		vo.setPayment(payment);
		vo.setAddress(address);
		vo.setMember_no(no);
		dao.insert(vo);

	}

	private static void testFind() {
		List<OrderVo> list = new OrderDao().find();
		for (OrderVo vo : list) {
			System.out.println(vo);
		}
	}

	public static void testUpdate(String address, Long no) {
		OrderDao dao = new OrderDao();
		OrderVo vo = new OrderVo();

		vo.setAddress(address);
		vo.setNo(no);
		dao.update(vo);
	}
}
