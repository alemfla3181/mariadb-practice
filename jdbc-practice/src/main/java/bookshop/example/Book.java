package bookshop.example;

public class Book {
	int bookNo;
	String title;
	String author;
	int stateCode;

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStateCode() {
		return stateCode;
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}

	Book(int bookNo, String title, String author) {
		this.bookNo = bookNo;
		this.title = title;
		this.author = author;
		this.stateCode = 1;
	}

	public void rent() {
		this.stateCode = 0;
		System.out.println(title + "이(가) 대여 됐습니다.");
	}

	public void print() {
		System.out.print("[" + bookNo + "] 책 제목:" + title + ", 작가:" + author + ", 대여 유무:");
		if (stateCode == 1) {
			System.out.println("재고있음");
		} else {
			System.out.println("대여중");
		}
	}

}
