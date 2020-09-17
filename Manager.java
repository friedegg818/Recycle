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
		
		System.out.println(" >> 관리자 코드를 입력하세요.");
		tel = sc.next();
		System.out.println(" >> 비밀번호를 입력하세요");
		pwd = sc.next();
		if(!tel.equals("admin")) {
			System.out.println(" >> 번호를 잘못 입력하였습니다.");
			return;
		}
		else if(!pwd.equals("12345")) {
			System.out.println(" >> 비밀번호가 틀렸습니다.");
			return;
		}
		System.out.println(" >> 관리자님 환영합니다. 원하시는 서비스를 선택하세요 :D");
		while(true) {
			try {
				do {
					System.out.println("1.상품등록 2.상품입고 3.상품 삭제 4.상품리스트 5.회원조회 6.종료");
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
				System.out.println("잘못입력하였습니다 다시입력하여주세요");
				sc.nextLine();
			}
		}
	}
}
