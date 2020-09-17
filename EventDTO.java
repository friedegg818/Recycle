package _Recycle;

public class EventDTO {
	private int evnum;						// 이벤트 참여 일련번호	
	private String tel;						// 회원 전화번호 (참조키)
	private int qnum;						// 퀴즈 번호 
	private int epoint;		     		// 이벤트성공시 줄 포인트	

	
	public int getEvnum() {
		return evnum;
	}
	public void setEvnum(int evnum) {
		this.evnum = evnum;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getEpoint() {
		return epoint;
	}
	public void setEpoint(int epoint) {
		this.epoint = epoint;
	}
	public int getQnum() {
		return qnum;
	}
	public void setQnum(int qnum) {
		this.qnum = qnum;
	}	
}
