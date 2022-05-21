package main;

import java.util.List;

import dao.BookDao;
import dao.CartDao;
import dao.CategoryDao;
import dao.MemberDao;
import dao.OrderBookDao;
import dao.OrderDao;
import vo.BookVo;
import vo.CartVo;
import vo.CategoryVo;
import vo.MemberVo;
import vo.OrderBookVo;
import vo.OrderVo;

public class BookMall {

	public static void main(String[] args) {
		// insert
//		insertMember();
//		insertBookOrder();
//		insertBook();
		
		// print
		printMember();
		printCategory();
		printBook();
		printCart();
		printOrder();
		printOrderBook();

	}

	public static void printMember() {
		System.out.println("/=========== Member ===============/");
		List<MemberVo> listM = new MemberDao().find();
		for (MemberVo vo : listM) {
			System.out.println(vo);
		}
	}

	public static void printCategory() {
		System.out.println("/========== Category ==============/");
		List<CategoryVo> listC = new CategoryDao().find();
		for (CategoryVo vo : listC) {
			System.out.println(vo);
		}
	}

	public static void printBook() {
		System.out.println("/============ Book ================/");
		List<BookVo> listB = new BookDao().find();
		for (BookVo vo : listB) {
			System.out.println(vo);
		}
	}

	public static void printCart() {
		System.out.println("/============ Cart ================/");
		List<CartVo> listCart = new CartDao().find();
		for (CartVo vo : listCart) {
			System.out.println(vo);
		}
	}

	public static void printOrder() {
		System.out.println("/============ Order ================/");
		List<OrderVo> listO = new OrderDao().find();
		for (OrderVo vo : listO) {
			System.out.println(vo);
		}
	}

	public static void printOrderBook() {
		System.out.println("/========== OrderBook ===============/");
		List<OrderBookVo> listOB = new OrderBookDao().find();
		for (OrderBookVo vo : listOB) {
			System.out.println(vo);
		}
	}

	private static void insertMember() {
		MemberDao dao = new MemberDao();
		MemberVo vo = new MemberVo();

		vo.setName("마이콜");
		vo.setPhone("999-1111-2222");
		vo.setEmail("hello@naver.com");
		vo.setPassword("7777");
		dao.insert(vo);
	}

	private static void insertBook() {
		BookDao dao = new BookDao();
		BookVo vo = new BookVo();

		vo.setTitle("변화하는 세계 질서");
		vo.setPrice(34200L);
		vo.setCategory_no(2L);
		dao.insert(vo);
	}

	private static void insertBookOrder() {
		// insert OrderBookDTKDZ
		OrderBookDao dao = new OrderBookDao();
		OrderBookVo vo = new OrderBookVo();

		vo.setNo(1L);
		vo.setBook_no(1L);
		vo.setAmount("5");
		dao.insert(vo);
	}
}
