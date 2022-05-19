package vo;

public class BookVo {
	private Long no;
	private String title;
	private Long price;

	private String category;
	private Long category_no;

	public Long getCategory_no() {
		return category_no;
	}

	public void setCategory_no(Long category_no) {
		this.category_no = category_no;
	}

	public Long getNo() {
		return no;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return no + ". " + title + ", " + price + ", " + category;
	}
}
