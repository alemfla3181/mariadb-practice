package dao.test;

import java.util.List;

import dao.CategoryDao;
import vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
//		testInsert("시사");
//		testDelete(6);
//		testUpdate("우주", 6L);
		testFind();

	}

	public static void testInsert(String category) {
		CategoryDao dao = new CategoryDao();
		CategoryVo vo = new CategoryVo();

		vo.setCategory(category);
		dao.insert(vo);

	}

	private static void testFind() {
		List<CategoryVo> list = new CategoryDao().find();
		for (CategoryVo vo : list) {
			System.out.println(vo);
		}
	}

	public static void testDelete(long no) {
		CategoryDao dao = new CategoryDao();

		dao.delete(no);

	}

	public static void testUpdate(String category, Long no) {
		CategoryDao dao = new CategoryDao();
		CategoryVo vo = new CategoryVo();

		vo.setCategory(category);
		vo.setNo(no);
		dao.update(vo);
		;
	}
}
