package vo;

public class OrderBookVo {
	private Long no;
	private Long book_no;
	private String amount;
	private String book_title;
	private String book_payment;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Long getBook_no() {
		return book_no;
	}

	public void setBook_no(Long book_no) {
		this.book_no = book_no;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getBook_title() {
		return book_title;
	}

	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}

	public String getBook_payment() {
		return book_payment;
	}

	public void setBook_payment(String book_payment) {
		this.book_payment = book_payment;
	}

	@Override
	public String toString() {
		return no + ". " + book_title + " " + amount + "권 " + book_payment;
	}
}
