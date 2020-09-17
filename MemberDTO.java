package _Recycle;

public class MemberDTO {
	private String name;				// 회원이름
	private String tel;					// 회원전화번호
	private String pwd;					// 비밀번호
	private int point;				// 회원 보유 포인트
	
	
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
