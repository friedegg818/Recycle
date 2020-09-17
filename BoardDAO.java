package _Recycle;

import java.util.List;

public interface BoardDAO {
	public int makeboard(BoardDTO bddto);  //�Խ��Ǿ���
	public int deleteboard(int num);  //�Խ��ǻ���
	public int updateboard(BoardDTO dto);  //�Խ��Ǽ���
	public List<BoardDTO> findBoard();  //�Խ������
	public MemberDTO checkid(MemberDTO dto);  //ȸ��Ȯ��
	public String searchtel(String tel);  //��ȭ��ȣ�� �ش��ϴ� ȸ���̸� ��ȯ
	public BoardDTO bdtext(int num);  //�Խ��Ǻ���
}
