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
		
		System.out.println("\t\t\t\t[���ڵ� �ν� �и����� ���α׷�]");
		System.out.println();
		
		System.out.println("\t  -------------------------------------------------------------------------��");
		System.out.println("\t/ \t\t\t / \t\t\t / \t\t\t / |");
		System.out.println("\t------------------------------------------------------------------------- /|");
		System.out.println("\t| \t\t\t | \t\t\t | \t\t\t | |");
		System.out.println("\t| \t��\t\t | \tȰ\t\t | \t��\t\t | |");
		System.out.println("\t| \t\t\t | \t\t\t | \t\t\t | |");
		System.out.println("\t-------------------------------------------------------------------------  ��");
		System.out.println("\t  -------------------------------------------------------------------------��\t\t�� �� �� �� ��\t");
		System.out.println("\t/ \t\t\t / \t\t\t / \t\t\t / |\t\t1. �и����� ��ư�� ���� ������ �����⸦ �˸°� �з��մϴ�.");
		System.out.println("\t------------------------------------------------------------------------- /|\t\t2. �ùٸ��� �з� �ߴٸ�, ��ȣ�� �Է��Ͽ� ����Ʈ�� ���� �� �� �ֽ��ϴ�.");
		System.out.println("\t| \t\t\t | \t\t\t | \t\t\t | |\t\t3. ������ ����Ʈ�� �پ��� ��ǰ ���Ű� �����մϴ�.");
		System.out.println("\t| \t��\t\t | \t��\t\t | \t��\t\t | |\t\t4. �̺�Ʈ������ ��� Ǯ�� �߰� ����Ʈ�� ���� �� �ֽ��ϴ�.");
		System.out.println("\t| \t\t\t | \t\t\t | \t\t\t | |\t\t5. �Խ����� ���� ���� �ǰ��� ���� �� ������.");
		System.out.println("\t-------------------------------------------------------------------------  ��\t\t        �� ���� 010-1111-2222");
		
		
		try {
			while (true) {
				do {
					System.out.println("\n[��� ����]");					
					System.out.println(" 1.�и�����   2.����Ʈ ���   3.����Ʈ ��ȸ   4.�̺�Ʈ   5.�Խ���   6.ȸ��Ż��   7.���� ");	// �����ڴ� 8���� ���ܵ� 
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
			System.out.println("�߸��Է� �Ͽ����ϴ�. �ٽ��Է� �Ͽ��ּ���.");
			sc.nextLine();
		}
		sc.close();
	}
}
