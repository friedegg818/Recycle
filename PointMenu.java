package _Recycle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import db.util.DBCon;

public class PointMenu {
	private Connection con = DBCon.getConnection();
	private PointDTO dto = new PointDTO();
	private PointDAO dao = new PointDAOImpl();
	private Scanner sc = new Scanner(System.in);

	public void usePoint() { // 포인트 사용
		ResultSetMetaData rsmd = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			System.out.println("[적립하신 포인트로 상품을 구매 합니다.]");
			String tel;
			System.out.println(" >> 휴대전화 번호를 입력 해 주세요.");
			tel = sc.next();
			System.out.println(" >> 비밀번호를 입력 해 주세요.");
			String pwd = sc.next();
			MemberDAO mdao = new MemberDAOImpl();
			MemberDTO mdto = mdao.readMember(tel);
			if (mdto == null) { // 회원 리스트 돌려서 확인
				System.out.println(" >> 잘못 되거나 없는 번호 입니다. 다시 입력 하여 주세요.");
				return;
			} else if (!mdto.getPwd().equals(pwd)) {
				System.out.println(" >> 잘못된 비밀번호 입니다. 다시 입력하여 주세요");
				return;
			} else {
				dto.setPtel(tel);
			}
			String sql;

			sql = "SELECT * FROM product";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			System.out.println("\n[상품 목록]");
			System.out.println("===========================");
			System.out.println(" 상품명\t상품코드\t가격\t개수\t");
			System.out.println("---------------------------");
			while (rs.next()) {
				for (int i = 1; i <= cols; i++) {
					System.out.print(" "+rs.getString(i) + "\t");
				}				
				System.out.println();
			}
			System.out.println("===========================");
			System.out.println("\n[구매하실 제품을 선택 해 주세요.] ");
			String pname;
			pname = sc.next();
			ProductDAO pdao = new ProductDAOImpl();
			ProductDTO pdto = pdao.findBynameProduct(pname);
			if (pdto == null) {
				System.out.println(" >> 입력하신 상품이 존재하지 않습니다.");
			} else {
				if (pdto.getPcount() == 0) {
					System.out.println(" >> 구매하시려는 상품이 소진되었습니다.");
					return;
				} 
			}
			if(mdto.getPoint()<pdto.getPrice()) {
				System.out.println(" → 포인트가 부족합니다ㅠㅠ");
				System.out.println(" → 다른 상품을 구매하시거나, 포인트를 조금 더 쌓아보세요!");
				return;
			}
			dto.setPtel(tel);
			dto.setUsename(pname);
			int result = dao.buyProduct(dto);
			mdao.updateMember(mdto.getPoint()-pdto.getPrice(), tel);
			if (result >= 1) {				
				System.out.println(" >> "+mdto.getName()+"님, 선택하신 '"+pdto.getPname()+"'구매가 완료 되었습니다 ♬");
				pdao.minusproduct(pname, pdto.getPcount() - 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e2) {
				}
			}
		}
	}

	public void stackPoint(String tel, String name, int point) { // 포인트 적립
		dto.setPtel(tel);
		dto.setUsename(name);
		dto.setPoint(point);
		dao.stackPoint(dto);
	}

	public void viewPoint() { // 포인트 조회
		String tel;
		try {
			Member member = new Member();
			MemberDAO mdao = new MemberDAOImpl();
			
			System.out.println("[포인트 정보를 조회합니다.]");
			System.out.println(" >> 휴대전화 번호를 입력 해 주세요.");
			tel = sc.next();
			MemberDTO mdto = mdao.readMember(tel);
			if (mdto == null) {
				System.out.println(" >> 등록된 번호가 아닙니다. 회원가입을 하시겠습니까? [Y/N]");
				String a = sc.next();
				if (a.equals("Y") || a.equals("y")) {
					member.makeId();
					return;
				} else if (a.equals("N") || a.equals("n")) {
					return;
				} else {
					System.out.println(" >> 잘못 되거나 없는 번호 입니다. 다시 입력 하여 주세요.");
					return;
				}
			}
			  int i;
			  System.out.println(" >> "+mdto.getName()+"님 환영합니다. 원하시는 서비스를 선택하세요 :D");
		         while (true) {
		            do {
		            	System.out.println("     ☞   1.마이 포인트   2.포인트 적립/사용 내역  3.뒤로 가기 =>");
		               i = sc.nextInt();
		            } while (i < 1 || i > 3);
		            if (i == 3)
		               break;
		            switch (i) {
		            case 1:
		               dto = dao.viewPoint(tel);
		               System.out.println("=========================");
		               System.out.print(" 회원번호\t\t 포인트\n");
		               System.out.println("-------------------------");
		               System.out.print(" "+tel + "\t");
					   System.out.print(" "+dto.getPoint() + "\n");
					   System.out.println("=========================\n");	
		               break;

		            case 2:
		               List<PointDTO> list = dao.PointList(tel);
		               System.out.println("===================================");
		               System.out.print(" 회원번호\t\t 이용내역\t 사용포인트\n");
		               for (PointDTO dto : list) {
		            	  System.out.println("-----------------------------------");
		                  System.out.print(tel + "\t");
		                  System.out.print(dto.getUsename() + "\t");
		                  System.out.print(dto.getPoint() + "\n");		                
		               }
		           	System.out.println("===================================\n");
		           	break;
		            }

		         }		

		} catch (InputMismatchException e) {
			System.out.println(" >> 잘못 입력 하셨습니다. 다시 입력하여 주세요.");
			sc.nextLine();
		}
	}

}
