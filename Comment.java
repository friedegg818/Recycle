package _Recycle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Comment {
	private Scanner sc=new Scanner(System.in);
	private CommentDAO cdao;
	private int bnumber;  //�Խñ۹�ȣ
	private MemberDTO mdto;
	private BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
	public Comment(CommentDAO cdao) {
		this.cdao=cdao;
	}
	
	public void setbnumber(int bnumber) {  //�Խñ۹�ȣ �־��ֱ�
		this.bnumber=bnumber;
	}
	
	public void setmember(MemberDTO mdto) {  //�α��������־��ֱ�
		this.mdto=mdto;
	}
	
	
	public void writecm() {  //��۴ޱ�
		System.out.println(" >> ����� �Է��ϼ���.");
		String text;
		try {
			text = br.readLine();
			
			CommentDTO cdto=new CommentDTO();
			cdto.setBnumber(bnumber);
			cdto.setTel(mdto.getTel());
			cdto.setText(text);
			if(cdao.wrcomment(cdto)==0) {
				System.out.println(" >> ��� �ۼ��������߽��ϴ�.");
				return;
			};
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deletecm() {  //��ۻ���
		CommentDTO dto=new CommentDTO();
		System.out.println(" >> ���� �� ��� ��ȣ�� �Է��ϼ���.");
		//��۰��������� �Է¹޴¹�ȣ ����
		int num=sc.nextInt();
		dto.setCnumber(num);
		if(num>cdao.listcomment(bnumber).size()||num<=0) {
			System.out.println(" >> �ش��ϴ� ����� �����ϴ�.");
			return;
		}
		
		dto.setBnumber(bnumber);
		//��۴� ���̵�� �α����� ���̵� �ٸ��� ����
		if(!mdto.getTel().equals(cdao.chcnumber(dto).getTel())) {
			System.out.println(">> ������ �����ϴ�.");
			return;
		}
		
		if(cdao.decomment(dto)==0) {
			System.out.println(">> ������ �����߽��ϴ�.");
			return;
		};
		System.out.println(" >> ����� �����Ǿ����ϴ�.");
	}
	
	public void updatecm() {  //��ۼ���
		CommentDTO dto=new CommentDTO();
		try {
			System.out.println(" >> ���� �� ��� ��ȣ�� �Է��ϼ���.");
			dto.setBnumber(bnumber);
			dto.setCnumber(sc.nextInt());
			System.out.println(" >> ����� �����ϼ���.");
			dto.setText(br.readLine());
			if(!mdto.getTel().equals(cdao.chcnumber(dto).getTel())) {
				System.out.println(" >> ������ �����ϴ�.");
				return; 
			}
			
			if(cdao.upcomment(dto)==0) {
				System.out.println(" >> ������ �����߽��ϴ�.");
				return;
			};
			System.out.println(" >> ����� �����Ǿ����ϴ�.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
