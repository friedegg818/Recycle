package _Recycle;

public class GarbageDTO {
	private String gname;				// 쓰레기 제품명
	private String gcode;				// 쓰레기 코드
	private String bcategory;
	
	public String getBcategory() {
		return bcategory;
	}
	public void setBcategory(String bcategory) {
		this.bcategory = bcategory;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getGcode() {
		return gcode;
	}
	public void setGcode(String gcode) {
		this.gcode = gcode;
	}
}
