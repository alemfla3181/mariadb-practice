package dao.test;

import java.util.List;

import dao.BookDao;
import vo.BookVo;

public class BookDaoTest {
	public static void main(String[] args) {
//		testInsert("이것이 MariaDB다", 29800L, 3L);
//		testUpdate("이것이 자바다", 2L);
		testFind();
	}

	public static void testInsert(String title, Long price, Long category) {
		BookDao dao = new BookDao();
		BookVo vo = new BookVo();

		vo.setTitle(title);
		vo.setPrice(price);
		vo.setCategory_no(category);
		dao.insert(vo);

	}

	private static void testFind() {
		List<BookVo> list = new BookDao().find();
		for (BookVo vo : list) {
			System.out.println(vo);
		}
	}

	public static void testUpdate(String title, Long no) {
		BookDao dao = new BookDao();
		BookVo vo = new BookVo();

		vo.setTitle(title);
		vo.setNo(no);
		dao.update(vo);
		;
	}

}
