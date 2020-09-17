package _Recycle;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Manager {
	Scanner sc = new Scanner(System.in);
	ProductMenu pm = new ProductMenu();
	ProductDAO pdao;
	
	public void MangerMenu() {
		int ch;
		Member member = new Member();
		String tel;
		String pwd;
		
		System.out.println(" >> ������ �ڵ带 �Է��ϼ���.");
		tel = sc.next();
		System.out.println(" >> ��й�ȣ�� �Է��ϼ���");
		pwd = sc.next();
		if(!tel.equals("admin")) {
			System.out.println(" >> ��ȣ�� �߸� �Է��Ͽ����ϴ�.");
			return;
		}
		else if(!pwd.equals("12345")) {
			System.out.println(" >> ��й�ȣ�� Ʋ�Ƚ��ϴ�.");
			return;
		}
		System.out.println(" >> �����ڴ� ȯ���մϴ�. ���Ͻô� ���񽺸� �����ϼ��� :D");
		while(true) {
			try {
				do {
					System.out.println("1.��ǰ��� 2.��ǰ�԰� 3.��ǰ ���� 4.��ǰ����Ʈ 5.ȸ����ȸ 6.����");
					ch =sc.nextInt();
				}while(ch<1||ch>6);
				
				if(ch==6) break;;
				
				switch (ch) {
				case 1 :
					pm.insertproduct();
					break;
				case 2 :
					pm.updateproduct();
					break;
				case 3 : 
					pm.deleteproduct();
					break;
				case 4 :
					pm.listAll();
					break;
				case 5 :
					member.MemberList();
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("�߸��Է��Ͽ����ϴ� �ٽ��Է��Ͽ��ּ���");
				sc.nextLine();
			}
		}
	}
}
