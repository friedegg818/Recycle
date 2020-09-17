package _Recycle;

import java.util.List;


public interface MemberDAO {
	public int insertUser(MemberDTO mdto);  // ȸ�� ����
	public int deleteUser(String tel);      // ȸ�� Ż��
	public MemberDTO listUser(String tel);  // ȸ�� ���� ����
	public List<MemberDTO> AllListUser();   // ȸ�� ��ü ����Ʈ
	public MemberDTO readUser(String tel, String pwd); // ��ȭ��ȣ, �н������ ��ȸ
	public MemberDTO readMember(String tel); // ��ȭ��ȣ�� ��ȸ
	public MemberDTO updateMember(int point, String tel);
}