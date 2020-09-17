package _Recycle;

import java.util.List;
import java.util.Scanner;

public class Member {

	Scanner sc = new Scanner(System.in);
	private MemberDAO mdao = new MemberDAOImpl();

	public void makeId() { // 회원가입
		
		PointMenu pm = new PointMenu();
		MemberDTO mdto = new MemberDTO();
		try {

			System.out.println("[회원가입[가입시 포인트 1000원 적립]]");
			System.out.println("이름 : ");
			mdto.setName(sc.next());
			System.out.println("전화번호 : ");
			String tel = sc.next();
			mdto.setTel(tel);
			System.out.println("비밀번호 : ");
			mdto.setPwd(sc.next());

			int result = mdao.insertUser(mdto);
			if (result != 0) {
				System.out.println("회원 가입이 정상적으로 처리되었습니다.");
				mdao.updateMember(1000, tel);
				pm.stackPoint(tel, "회원가입", 1000);
				System.out.println("   ☞  "+mdto.getName()+"님에게 1000 포인트가 지급되었습니다 ^^");
			} else {
				System.out.println("회원 가입이 정상적으로 처리되지 않았습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}


	public void deleteMember() { // 탈퇴
		String tel, pwd;

		System.out.println("[회원 탈퇴]");
		System.out.println("탈퇴할 회원 전화번호를 입력하세요.");
		tel = sc.next();
		System.out.println("비밀번호를 입력하세요.");
		pwd = sc.next();

		if (mdao.readUser(tel, pwd) == null) {
			System.out.println("전화번호 및 전화번호가 일치하지 않습니다.");
		} else {
			mdao.deleteUser(tel);
			System.out.println("회원 탈퇴가 정상적으로 처리 되었습니다.");
		}
	}

	public void MemberList() { // 회원 리스트
		MemberDTO mdto;
		String tel;
		String s;
		List<MemberDTO> list = mdao.AllListUser();

		System.out.println("         [회원리스트]");
		System.out.println("=============================");
		System.out.print("번호\t\t이름\t포인트\n");
		System.out.println("----------------------------");
		if (list == null) {
			System.out.println("등록된 정보가 없습니다.");
			return;
		}
		for (MemberDTO dto : list) {
			System.out.print(dto.getTel() + "\t");
			System.out.print(dto.getName() + "\t");
			System.out.print(dto.getPoint() + "\n");
		}
		System.out.println("=============================");
		
		System.out.println("\n특정 회원을 검색 하시겠습니까?[Y/N]");
		s = sc.next();
		
		if(s.equals("n") || s.equals("N")) { 
			System.out.println("메인으로 돌아갑니다.\n");
			return;
		}		

		System.out.println("찾을 회원의 번호를 입력하세요.");
		tel = sc.next();

		mdto = mdao.readMember(tel);

		if (mdto == null) {
			System.out.println("등록된 전화번호가 없습니다.\n");
			return;
		}
		System.out.println("=============================");
		System.out.print("번호\t\t이름\t포인트\n");
		System.out.println("----------------------------");
		System.out.print(mdto.getTel() + "\t");
		System.out.print(mdto.getName() + "\t");
		System.out.print(mdto.getPoint() + "\n");
		System.out.println("=============================");
	}
}
