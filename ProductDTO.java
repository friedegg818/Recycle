package _Recycle;

public class ProductDTO {
	private String pname;			// ��ǰ �̸�
	private String pcode;			// ��ǰ�ڵ�
	private int pcount;				// ��ǰ����
	private int price;				// ��ǰ����(����Ʈ���)
	
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
