package _Recycle;

import java.util.List;
import java.util.Scanner;

public class Member {

	Scanner sc = new Scanner(System.in);
	private MemberDAO mdao = new MemberDAOImpl();

	public void makeId() { // ȸ������
		
		PointMenu pm = new PointMenu();
		MemberDTO mdto = new MemberDTO();
		try {

			System.out.println("[ȸ������[���Խ� ����Ʈ 1000�� ����]]");
			System.out.println("�̸� : ");
			mdto.setName(sc.next());
			System.out.println("��ȭ��ȣ : ");
			String tel = sc.next();
			mdto.setTel(tel);
			System.out.println("��й�ȣ : ");
			mdto.setPwd(sc.next());

			int result = mdao.insertUser(mdto);
			if (result != 0) {
				System.out.println("ȸ�� ������ ���������� ó���Ǿ����ϴ�.");
				mdao.updateMember(1000, tel);
				pm.stackPoint(tel, "ȸ������", 1000);
				System.out.println("   ��  "+mdto.getName()+"�Կ��� 1000 ����Ʈ�� ���޵Ǿ����ϴ� ^^");
			} else {
				System.out.println("ȸ�� ������ ���������� ó������ �ʾҽ��ϴ�.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}


	public void deleteMember() { // Ż��
		String tel, pwd;

		System.out.println("[ȸ�� Ż��]");
		System.out.println("Ż���� ȸ�� ��ȭ��ȣ�� �Է��ϼ���.");
		tel = sc.next();
		System.out.println("��й�ȣ�� �Է��ϼ���.");
		pwd = sc.next();

		if (mdao.readUser(tel, pwd) == null) {
			System.out.println("��ȭ��ȣ �� ��ȭ��ȣ�� ��ġ���� �ʽ��ϴ�.");
		} else {
			mdao.deleteUser(tel);
			System.out.println("ȸ�� Ż�� ���������� ó�� �Ǿ����ϴ�.");
		}
	}

	public void MemberList() { // ȸ�� ����Ʈ
		MemberDTO mdto;
		String tel;
		String s;
		List<MemberDTO> list = mdao.AllListUser();

		System.out.println("         [ȸ������Ʈ]");
		System.out.println("=============================");
		System.out.print("��ȣ\t\t�̸�\t����Ʈ\n");
		System.out.println("----------------------------");
		if (list == null) {
			System.out.println("��ϵ� ������ �����ϴ�.");
			return;
		}
		for (MemberDTO dto : list) {
			System.out.print(dto.getTel() + "\t");
			System.out.print(dto.getName() + "\t");
			System.out.print(dto.getPoint() + "\n");
		}
		System.out.println("=============================");
		
		System.out.println("\nƯ�� ȸ���� �˻� �Ͻðڽ��ϱ�?[Y/N]");
		s = sc.next();
		
		if(s.equals("n") || s.equals("N")) { 
			System.out.println("�������� ���ư��ϴ�.\n");
			return;
		}		

		System.out.println("ã�� ȸ���� ��ȣ�� �Է��ϼ���.");
		tel = sc.next();

		mdto = mdao.readMember(tel);

		if (mdto == null) {
			System.out.println("��ϵ� ��ȭ��ȣ�� �����ϴ�.\n");
			return;
		}
		System.out.println("=============================");
		System.out.print("��ȣ\t\t�̸�\t����Ʈ\n");
		System.out.println("----------------------------");
		System.out.print(mdto.getTel() + "\t");
		System.out.print(mdto.getName() + "\t");
		System.out.print(mdto.getPoint() + "\n");
		System.out.println("=============================");
	}
}
