package _Recycle;

import java.util.List;

public interface GarbageDAO {	//����
	public GarbageDTO insertGarbage(String name);					// ������ ��ǰ �Է�   
	public List<GarbageDTO> listGarbage();			// ������ ����Ʈ
	public MemberDTO serch(String tel);
	public MemberDTO pointupdate(int point, String tel);
	public GarbageDTO findbynameGarbage(String name);
}
