package _Recycle;

public class MemberDTO {
	private String name;				// ȸ���̸�
	private String tel;					// ȸ����ȭ��ȣ
	private String pwd;					// ��й�ȣ
	private int point;				// ȸ�� ���� ����Ʈ
	
	
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
