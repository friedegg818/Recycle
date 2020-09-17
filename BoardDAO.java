package _Recycle;

import java.util.List;

public interface BoardDAO {
	public int makeboard(BoardDTO bddto);  //게시판쓰기
	public int deleteboard(int num);  //게시판삭제
	public int updateboard(BoardDTO dto);  //게시판수정
	public List<BoardDTO> findBoard();  //게시판출력
	public MemberDTO checkid(MemberDTO dto);  //회원확인
	public String searchtel(String tel);  //전화번호에 해당하는 회원이름 반환
	public BoardDTO bdtext(int num);  //게시판보기
}
