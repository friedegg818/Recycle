package _Recycle;

public class EventDTO {
	private int evnum;						// �̺�Ʈ ���� �Ϸù�ȣ	
	private String tel;						// ȸ�� ��ȭ��ȣ (����Ű)
	private int qnum;						// ���� ��ȣ 
	private int epoint;		     		// �̺�Ʈ������ �� ����Ʈ	

	
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
