package _Recycle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Board {
	private Scanner sc = new Scanner(System.in);
	private BoardDAO bdao = new BoardDAOImpl();
	private CommentDAO cdao=new CommentDAOImpl();
	private Comment comment=new Comment(cdao); 
	private MemberDTO mdto;  //�α����� ������ü
	private int bnumber;  //�Խñ۹�ȣ(ROWNUM)
	private BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
	public void setbnumber(int bnumber) {  //�Խñ۹�ȣ �־��ֱ�
		this.bnumber=bnumber;
	}
	
	public void bdlogin() {
		MemberDTO dto=new MemberDTO();
		System.out.println("[�Խ��� �̿��� ���ؼ��� �α����� �ʿ��մϴ�.]");
		System.out.println(" >> �޴��� ��ȣ�� �Է� �ϼ���.");
		dto.setTel(sc.next());
		System.out.println(" >> ��й�ȣ�� �Է� �ϼ���.");
		dto.setPwd(sc.next());		
		if(bdao.checkid(dto).getTel()==null) {
			System.out.println(" >> �ش��ϴ� ȸ�� ������ �����ϴ�. Ȯ�� �� �ٽ� �Է� �� �ּ���.");
			return;
		};
		mdto=dto;
		comment.setmember(dto);
		bmenu();
	}
	
	public void bmenu() { // �Խ��Ǹ޴�
		while (true) {
			int ch;
			do {
				listbd();
				System.out.print(" �� 1.�Խñ� ����  2.�Խñ� �ۼ�  3.�ڷ� ���� =>");
				ch = sc.nextInt();

				if (ch == 3) {return;}

				switch (ch) {
				case 1:bdtext();break;
				case 2:writebd();break;
				}
			} while (ch > 3 || ch < 1);
			System.out.println();
		}
	}
	
	public int textmenu() {  //�۸޴�
		int ch=0;
		System.out.println(" �� 1.��� �ޱ�   2.��� ����   3.��� ����   4.�Խñ� ����   5.�Խñ� ����   6.�Խ��� ���  ");
		ch=sc.nextInt();
			
		switch(ch) {
		case 1:comment.writecm();break;
		case 2:comment.deletecm();break;
		case 3:comment.updatecm();break;
		case 4:updatebd();break;
		case 5:
			if(deletebd()==1) {
				return 6;
			} break;  //�����̸� �������
		case 6:break;
		}
		return ch;
	}
	
	public int textmenu2() {  //�۸޴�
		int ch=0;
		System.out.println(" �� 1.��� �ޱ�   2.��� ����   3.��� ����   4.�Խ��� ��� ");
		ch=sc.nextInt();
			
		switch(ch) {
		case 1:comment.writecm();break;
		case 2:comment.deletecm();break;
		case 3:comment.updatecm();break;
		case 4:break;
		}
		return ch;
	}
	
	public void writebd() { // �Խ��� �۾���
		BoardDTO bddto = new BoardDTO();
		try {
			bddto.setTel(mdto.getTel());
			System.out.println(" >> ������ �Է��ϼ���.");
			String title=br.readLine();
			if(title.length()>40) {
				System.out.println(" >> ������ 33�ڰ� ���� �� �����ϴ�.");
				return;
			}
			bddto.setTitle(title);
			System.out.println(">> ������ �Է��ϼ���.");
			bddto.setText(br.readLine());
			bdao.makeboard(bddto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void bdtext() { // �ۺ���
		int bnum;
		System.out.print(" >> �����Ͻ� �� ��ȣ�� �Է��ϼ���.");
		bnum=sc.nextInt();
		if(bnum>bdao.findBoard().size()||bnum<=0) {
			System.out.println(" >> �ش� �Խù��� �����ϴ�.");
			return;
		}
		
		comment.setbnumber(bnum);
		setbnumber(bnum);
			
		while(true) {
			BoardDTO dto=bdao.bdtext(bnum);
			System.out.println();			
			System.out.println("==================================================================");		
			System.out.println("�۹�ȣ | "+dto.getBnumber());
			System.out.println("�ۼ��� | "+bdao.searchtel(dto.getTel()));
			System.out.println("��  ��  | "+dto.getTitle());
			//System.out.println(dto.getBnumber()+"\t"+dto.getTitle()+"\t\t\t\t\t\t"+bdao.searchtel(dto.getTel()));
			System.out.println("------------------------------------------------------------------");
						
			//�ٸ���
			String s=dto.getText();
			while(true) {
				if(s.length()/44!=0) {
					System.out.println(s.substring(0,44));  //0~9��°����
					s=s.substring(44);  //����ϰ� ������ ���� s�� �Է�
				}
				else {
					System.out.println(s);
					break;
				}
			}			
			System.out.println("------------------------------------------------------------------");
			System.out.println("[���]");
			
			//���
			int i=1;
			for(CommentDTO dto2:cdao.listcomment(bnum)) {
				System.out.print(i+"  ");
				System.out.print(bdao.searchtel(dto2.getTel())+"\t");
				System.out.println(dto2.getText());
				i++;
			}
			System.out.println("==================================================================");
			
			//�α����� ����� ��ȭ��ȣ�� �Խù������ ��ȭ��ȣ�� ������ textmenu, ���������� textmenu2
			if(mdto.getTel().equals(dto.getTel())||mdto.getTel().equals("admin")) {
				if(textmenu()==6) return;
			}
			else {
				if(textmenu2()==4) return;
			}
		}
	}

	public int deletebd() { // �ۻ���
		//��ۻ���
		int num=cdao.listcomment(bnumber).size();  //��۰���
		for(int i=1; i<=num; i++) {  //��۰�����ŭ �ݺ�
			CommentDTO cdto=new CommentDTO();
			cdto.setBnumber(bnumber);
			cdto.setCnumber(1);
			cdao.decomment(cdto);
		}
		
		//�ۻ���
		if(bdao.deleteboard(bnumber)==0) {
			System.out.println(" >> �Խñ� ������ �����߽��ϴ�.");
			return 0;
		};
		System.out.println(" >> ������ �Ϸ� �Ǿ����ϴ�.");
		return 1;
	}

	public void updatebd() { // �ۼ���
		BoardDTO dto=new BoardDTO();
		try {
			System.out.println(" >> ������ �����ϼ���.[���� ���:0]");
			dto.setTitle(br.readLine());				
			System.out.println(" >> ������ �����ϼ���.[���� ���:0]");
			dto.setText(br.readLine());
			dto.setBnumber(bnumber);
			if(bdao.updateboard(dto)==0) {
				System.out.println(">> �Խñ� ������ �����߽��ϴ�.");
				return;
			};
			System.out.println(">> ������ �Ϸ� �Ǿ����ϴ�.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void listbd() { // �Խ�����ü����
		// (�Խ��ǹ�ȣ, ȸ���̸�, ����, ����)
		// ��ȣ ���� ȸ���̸�
		int i=1;	
		System.out.println("                           [���� �Խ���]");
		System.out.println("==================================================================");
		System.out.println("��ȣ    �۾���\t����");
		System.out.println("-----------------------------------------------------------------");
		for (BoardDTO dto : bdao.findBoard()) {
			//���񿷿� ��۰���
			System.out.print(i + "        "+bdao.searchtel(dto.getTel())+"\t"+dto.getTitle());
			if(cdao.listcomment(i).size()==0) {
				System.out.println();
			}
			else {
				System.out.println("["+cdao.listcomment(i).size()+"]");				
			}
			i++;
		}
		System.out.println("==================================================================");
	}
}
