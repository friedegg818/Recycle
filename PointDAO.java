package _Recycle;

import java.util.List;

public interface PointDAO {
	public PointDTO searchPoint(String tel);				// 번호를 받아서 포인트 조회.
	public int buyProduct(PointDTO dto);				// 상품 계산
	public int stackPoint(PointDTO dto);				// 포인트 적립
	public PointDTO viewPoint(String tel);							// 포인트 확인
	public List<PointDTO> PointList(String tel);
//	public void login(String tel, String pwd);
}
