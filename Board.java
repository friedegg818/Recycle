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
	private MemberDTO mdto;  //로그인한 유저객체
	private int bnumber;  //게시글번호(ROWNUM)
	private BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
	public void setbnumber(int bnumber) {  //게시글번호 넣어주기
		this.bnumber=bnumber;
	}
	
	public void bdlogin() {
		MemberDTO dto=new MemberDTO();
		System.out.println("[게시판 이용을 위해서는 로그인이 필요합니다.]");
		System.out.println(" >> 휴대폰 번호를 입력 하세요.");
		dto.setTel(sc.next());
		System.out.println(" >> 비밀번호를 입력 하세요.");
		dto.setPwd(sc.next());		
		if(bdao.checkid(dto).getTel()==null) {
			System.out.println(" >> 해당하는 회원 정보가 없습니다. 확인 후 다시 입력 해 주세요.");
			return;
		};
		mdto=dto;
		comment.setmember(dto);
		bmenu();
	}
	
	public void bmenu() { // 게시판메뉴
		while (true) {
			int ch;
			do {
				listbd();
				System.out.print(" ☞ 1.게시글 보기  2.게시글 작성  3.뒤로 가기 =>");
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
	
	public int textmenu() {  //글메뉴
		int ch=0;
		System.out.println(" ☞ 1.댓글 달기   2.댓글 삭제   3.댓글 수정   4.게시글 수정   5.게시글 삭제   6.게시판 목록  ");
		ch=sc.nextInt();
			
		switch(ch) {
		case 1:comment.writecm();break;
		case 2:comment.deletecm();break;
		case 3:comment.updatecm();break;
		case 4:updatebd();break;
		case 5:
			if(deletebd()==1) {
				return 6;
			} break;  //성공이면 목록으로
		case 6:break;
		}
		return ch;
	}
	
	public int textmenu2() {  //글메뉴
		int ch=0;
		System.out.println(" ☞ 1.댓글 달기   2.댓글 삭제   3.댓글 수정   4.게시판 목록 ");
		ch=sc.nextInt();
			
		switch(ch) {
		case 1:comment.writecm();break;
		case 2:comment.deletecm();break;
		case 3:comment.updatecm();break;
		case 4:break;
		}
		return ch;
	}
	
	public void writebd() { // 게시판 글쓰기
		BoardDTO bddto = new BoardDTO();
		try {
			bddto.setTel(mdto.getTel());
			System.out.println(" >> 제목을 입력하세요.");
			String title=br.readLine();
			if(title.length()>40) {
				System.out.println(" >> 제목은 33자가 넘을 수 없습니다.");
				return;
			}
			bddto.setTitle(title);
			System.out.println(">> 내용을 입력하세요.");
			bddto.setText(br.readLine());
			bdao.makeboard(bddto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void bdtext() { // 글보기
		int bnum;
		System.out.print(" >> 열람하실 글 번호를 입력하세요.");
		bnum=sc.nextInt();
		if(bnum>bdao.findBoard().size()||bnum<=0) {
			System.out.println(" >> 해당 게시물이 없습니다.");
			return;
		}
		
		comment.setbnumber(bnum);
		setbnumber(bnum);
			
		while(true) {
			BoardDTO dto=bdao.bdtext(bnum);
			System.out.println();			
			System.out.println("==================================================================");		
			System.out.println("글번호 | "+dto.getBnumber());
			System.out.println("작성자 | "+bdao.searchtel(dto.getTel()));
			System.out.println("제  목  | "+dto.getTitle());
			//System.out.println(dto.getBnumber()+"\t"+dto.getTitle()+"\t\t\t\t\t\t"+bdao.searchtel(dto.getTel()));
			System.out.println("------------------------------------------------------------------");
						
			//줄맞춤
			String s=dto.getText();
			while(true) {
				if(s.length()/44!=0) {
					System.out.println(s.substring(0,44));  //0~9번째문자
					s=s.substring(44);  //출력하고난 나머지 문자 s에 입력
				}
				else {
					System.out.println(s);
					break;
				}
			}			
			System.out.println("------------------------------------------------------------------");
			System.out.println("[댓글]");
			
			//댓글
			int i=1;
			for(CommentDTO dto2:cdao.listcomment(bnum)) {
				System.out.print(i+"  ");
				System.out.print(bdao.searchtel(dto2.getTel())+"\t");
				System.out.println(dto2.getText());
				i++;
			}
			System.out.println("==================================================================");
			
			//로그인한 사람의 전화번호와 게시물쓴사람 전화번호가 같으면 textmenu, 같지않으면 textmenu2
			if(mdto.getTel().equals(dto.getTel())||mdto.getTel().equals("admin")) {
				if(textmenu()==6) return;
			}
			else {
				if(textmenu2()==4) return;
			}
		}
	}

	public int deletebd() { // 글삭제
		//댓글삭제
		int num=cdao.listcomment(bnumber).size();  //댓글개수
		for(int i=1; i<=num; i++) {  //댓글개수만큼 반복
			CommentDTO cdto=new CommentDTO();
			cdto.setBnumber(bnumber);
			cdto.setCnumber(1);
			cdao.decomment(cdto);
		}
		
		//글삭제
		if(bdao.deleteboard(bnumber)==0) {
			System.out.println(" >> 게시글 삭제를 실패했습니다.");
			return 0;
		};
		System.out.println(" >> 삭제가 완료 되었습니다.");
		return 1;
	}

	public void updatebd() { // 글수정
		BoardDTO dto=new BoardDTO();
		try {
			System.out.println(" >> 제목을 수정하세요.[수정 취소:0]");
			dto.setTitle(br.readLine());				
			System.out.println(" >> 내용을 수정하세요.[수정 취소:0]");
			dto.setText(br.readLine());
			dto.setBnumber(bnumber);
			if(bdao.updateboard(dto)==0) {
				System.out.println(">> 게시글 수정을 실패했습니다.");
				return;
			};
			System.out.println(">> 수정이 완료 되었습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void listbd() { // 게시판전체보기
		// (게시판번호, 회원이름, 제목, 내용)
		// 번호 제목 회원이름
		int i=1;	
		System.out.println("                           [자유 게시판]");
		System.out.println("==================================================================");
		System.out.println("번호    글쓴이\t제목");
		System.out.println("-----------------------------------------------------------------");
		for (BoardDTO dto : bdao.findBoard()) {
			//제목옆에 댓글개수
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
