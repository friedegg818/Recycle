package _Recycle;

public class ProductDTO {
	private String pname;			// 상품 이름
	private String pcode;			// 상품코드
	private int pcount;				// 상품개수
	private int price;				// 상품가격(포인트사용)
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public int getPcount() {
		return pcount;
	}
	public void setPcount(int pcount) {
		this.pcount = pcount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
