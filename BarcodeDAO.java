package _Recycle;

public interface BarcodeDAO {
	public int InsertRecycle(BarcodeDTO dto);						// 재활용품명 추가
	public int UpdateRecycle(BarcodeDTO dto);						// 재활용품명 수정
	public int DeleteRecycle(String code);						// 재활용품명 삭제
}
