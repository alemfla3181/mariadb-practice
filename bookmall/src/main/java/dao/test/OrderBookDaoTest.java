package dao.test;

import java.util.List;

import dao.OrderBookDao;
import vo.OrderBookVo;

public class OrderBookDaoTest {

	public static void main(String[] args) {
//		testInsert(1L, 1L, "5");
//		testDelete(4);
//		testUpdate("둘리", 1L);
		testFind();

	}

	public static void testInsert(Long no, Long book_no, String amount) {
		OrderBookDao dao = new OrderBookDao();
		OrderBookVo vo = new OrderBookVo();

		vo.setNo(no);
		vo.setBook_no(book_no);
		vo.setAmount(amount);
		dao.insert(vo);

	}

	private static void testFind() {
		List<OrderBookVo> list = new OrderBookDao().find();
		for (OrderBookVo vo : list) {
			System.out.println(vo);
		}
	}
}
