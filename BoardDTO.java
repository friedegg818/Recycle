package _Recycle;

public class BoardDTO {
	private int bnumber;		  //게시판번호				// 게시판 번호
	private String tel;						// memberDTO 참조키
	private String title;
	private String text;						// 게시판 내용
	public int getBnumber() {
		return bnumber;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setBnumber(int bnumber) {
		this.bnumber = bnumber;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	
}
