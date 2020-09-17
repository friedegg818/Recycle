package _Recycle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Comment {
	private Scanner sc=new Scanner(System.in);
	private CommentDAO cdao;
	private int bnumber;  //게시글번호
	private MemberDTO mdto;
	private BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
	public Comment(CommentDAO cdao) {
		this.cdao=cdao;
	}
	
	public void setbnumber(int bnumber) {  //게시글번호 넣어주기
		this.bnumber=bnumber;
	}
	
	public void setmember(MemberDTO mdto) {  //로그인정보넣어주기
		this.mdto=mdto;
	}
	
	
	public void writecm() {  //댓글달기
		System.out.println(" >> 댓글을 입력하세요.");
		String text;
		try {
			text = br.readLine();
			
			CommentDTO cdto=new CommentDTO();
			cdto.setBnumber(bnumber);
			cdto.setTel(mdto.getTel());
			cdto.setText(text);
			if(cdao.wrcomment(cdto)==0) {
				System.out.println(" >> 댓글 작성에실패했습니다.");
				return;
			};
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deletecm() {  //댓글삭제
		CommentDTO dto=new CommentDTO();
		System.out.println(" >> 삭제 할 댓글 번호를 입력하세요.");
		//댓글개수정도로 입력받는번호 제한
		int num=sc.nextInt();
		dto.setCnumber(num);
		if(num>cdao.listcomment(bnumber).size()||num<=0) {
			System.out.println(" >> 해당하는 댓글이 없습니다.");
			return;
		}
		
		dto.setBnumber(bnumber);
		//댓글단 아이디와 로그인한 아이디가 다르면 실패
		if(!mdto.getTel().equals(cdao.chcnumber(dto).getTel())) {
			System.out.println(">> 권한이 없습니다.");
			return;
		}
		
		if(cdao.decomment(dto)==0) {
			System.out.println(">> 삭제를 실패했습니다.");
			return;
		};
		System.out.println(" >> 댓글이 삭제되었습니다.");
	}
	
	public void updatecm() {  //댓글수정
		CommentDTO dto=new CommentDTO();
		try {
			System.out.println(" >> 수정 할 댓글 번호를 입력하세요.");
			dto.setBnumber(bnumber);
			dto.setCnumber(sc.nextInt());
			System.out.println(" >> 댓글을 수정하세요.");
			dto.setText(br.readLine());
			if(!mdto.getTel().equals(cdao.chcnumber(dto).getTel())) {
				System.out.println(" >> 권한이 없습니다.");
				return; 
			}
			
			if(cdao.upcomment(dto)==0) {
				System.out.println(" >> 수정을 실패했습니다.");
				return;
			};
			System.out.println(" >> 댓글이 수정되었습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
