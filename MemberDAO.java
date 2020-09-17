package _Recycle;

import java.util.List;


public interface MemberDAO {
	public int insertUser(MemberDTO mdto);  // 회원 가입
	public int deleteUser(String tel);      // 회원 탈퇴
	public MemberDTO listUser(String tel);  // 회원 개인 정보
	public List<MemberDTO> AllListUser();   // 회원 전체 리스트
	public MemberDTO readUser(String tel, String pwd); // 전화번호, 패스워드로 조회
	public MemberDTO readMember(String tel); // 전화번호로 조회
	public MemberDTO updateMember(int point, String tel);
}