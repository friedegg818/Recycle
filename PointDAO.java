package _Recycle;

import java.util.List;

public interface PointDAO {
	public PointDTO searchPoint(String tel);				// ��ȣ�� �޾Ƽ� ����Ʈ ��ȸ.
	public int buyProduct(PointDTO dto);				// ��ǰ ���
	public int stackPoint(PointDTO dto);				// ����Ʈ ����
	public PointDTO viewPoint(String tel);							// ����Ʈ Ȯ��
	public List<PointDTO> PointList(String tel);
//	public void login(String tel, String pwd);
}
