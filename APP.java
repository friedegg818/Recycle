package _Recycle;

import java.util.InputMismatchException;
import java.util.Scanner;

public class APP {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Garbage garbage = new Garbage();
		PointMenu p = new PointMenu();
		Manager mm = new Manager();
		Member member = new Member();
		Event event = new Event();
		Board board = new Board();
		int ch;
		
		System.out.println("\t\t\t\t[바코드 인식 분리수거 프로그램]");
		System.out.println();
		
		System.out.println("\t  -------------------------------------------------------------------------┓");
		System.out.println("\t/ \t\t\t / \t\t\t / \t\t\t / |");
		System.out.println("\t------------------------------------------------------------------------- /|");
		System.out.println("\t| \t\t\t | \t\t\t | \t\t\t | |");
		System.out.println("\t| \t재\t\t | \t활\t\t | \t용\t\t | |");
		System.out.println("\t| \t\t\t | \t\t\t | \t\t\t | |");
		System.out.println("\t-------------------------------------------------------------------------  ┛");
		System.out.println("\t  -------------------------------------------------------------------------┓\t\t《 메 뉴 얼 》\t");
		System.out.println("\t/ \t\t\t / \t\t\t / \t\t\t / |\t\t1. 분리수거 버튼을 눌러 가져온 쓰레기를 알맞게 분류합니다.");
		System.out.println("\t------------------------------------------------------------------------- /|\t\t2. 올바르게 분류 했다면, 번호를 입력하여 포인트를 적립 할 수 있습니다.");
		System.out.println("\t| \t\t\t | \t\t\t | \t\t\t | |\t\t3. 적립한 포인트로 다양한 상품 구매가 가능합니다.");
		System.out.println("\t| \t쓰\t\t | \t레\t\t | \t기\t\t | |\t\t4. 이벤트란에서 퀴즈를 풀어 추가 포인트를 얻을 수 있습니다.");
		System.out.println("\t| \t\t\t | \t\t\t | \t\t\t | |\t\t5. 게시판을 통해 여러 의견을 공유 해 보세요.");
		System.out.println("\t-------------------------------------------------------------------------  ┛\t\t        ☎ 문의 010-1111-2222");
		
		
		try {
			while (true) {
				do {
					System.out.println("\n[기능 선택]");					
					System.out.println(" 1.분리수거   2.포인트 사용   3.포인트 조회   4.이벤트   5.게시판   6.회원탈퇴   7.종료 ");	// 관리자는 8번에 숨겨둠 
					ch = sc.nextInt();
				} while (ch < 1 || ch > 8);

				if (ch == 7)
					break;

				switch (ch) {
				case 1 : garbage.insert(); break;
				case 2 : p.usePoint(); break;
				case 3 : p.viewPoint(); 	break;
				case 4 : event.quiz();  break;
				case 5 : board.bdlogin(); break;
				case 6 : member.deleteMember();	break;
				case 8 : mm.MangerMenu();  break;
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("잘못입력 하였습니다. 다시입력 하여주세요.");
			sc.nextLine();
		}
		sc.close();
	}
}
