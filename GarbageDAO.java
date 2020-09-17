package _Recycle;

import java.util.List;

public interface GarbageDAO {	//투입
	public GarbageDTO insertGarbage(String name);					// 쓰레기 제품 입력   
	public List<GarbageDTO> listGarbage();			// 쓰레기 리스트
	public MemberDTO serch(String tel);
	public MemberDTO pointupdate(int point, String tel);
	public GarbageDTO findbynameGarbage(String name);
}
