package _Recycle;

public class BarcodeDTO {
	private String bcode;				// 쓰레기 바코드
	private int bpoint;					// 쓰레기 바코드에 대한 포인트
	private String bcategory;				// 쓰레기 분류
	public String getBcode() {
		return bcode;
	}
	public void setBcode(String bcode) {
		this.bcode = bcode;
	}
	public int getBpoint() {
		return bpoint;
	}
	public void setBpoint(int bpoint) {
		this.bpoint = bpoint;
	}
	public String getBcategory() {
		return bcategory;
	}
	public void setBcategory(String bcategory) {
		this.bcategory = bcategory;
	}
	
	
}
