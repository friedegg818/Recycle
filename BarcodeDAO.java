package _Recycle;

public interface BarcodeDAO {
	public int InsertRecycle(BarcodeDTO dto);						// ��Ȱ��ǰ�� �߰�
	public int UpdateRecycle(BarcodeDTO dto);						// ��Ȱ��ǰ�� ����
	public int DeleteRecycle(String code);						// ��Ȱ��ǰ�� ����
}
