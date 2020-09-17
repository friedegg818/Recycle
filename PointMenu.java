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

	public void usePoint() { // ����Ʈ ���
		ResultSetMetaData rsmd = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			System.out.println("[�����Ͻ� ����Ʈ�� ��ǰ�� ���� �մϴ�.]");
			String tel;
			System.out.println(" >> �޴���ȭ ��ȣ�� �Է� �� �ּ���.");
			tel = sc.next();
			System.out.println(" >> ��й�ȣ�� �Է� �� �ּ���.");
			String pwd = sc.next();
			MemberDAO mdao = new MemberDAOImpl();
			MemberDTO mdto = mdao.readMember(tel);
			if (mdto == null) { // ȸ�� ����Ʈ ������ Ȯ��
				System.out.println(" >> �߸� �ǰų� ���� ��ȣ �Դϴ�. �ٽ� �Է� �Ͽ� �ּ���.");
				return;
			} else if (!mdto.getPwd().equals(pwd)) {
				System.out.println(" >> �߸��� ��й�ȣ �Դϴ�. �ٽ� �Է��Ͽ� �ּ���");
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
			System.out.println("\n[��ǰ ���]");
			System.out.println("===========================");
			System.out.println(" ��ǰ��\t��ǰ�ڵ�\t����\t����\t");
			System.out.println("---------------------------");
			while (rs.next()) {
				for (int i = 1; i <= cols; i++) {
					System.out.print(" "+rs.getString(i) + "\t");
				}				
				System.out.println();
			}
			System.out.println("===========================");
			System.out.println("\n[�����Ͻ� ��ǰ�� ���� �� �ּ���.] ");
			String pname;
			pname = sc.next();
			ProductDAO pdao = new ProductDAOImpl();
			ProductDTO pdto = pdao.findBynameProduct(pname);
			if (pdto == null) {
				System.out.println(" >> �Է��Ͻ� ��ǰ�� �������� �ʽ��ϴ�.");
			} else {
				if (pdto.getPcount() == 0) {
					System.out.println(" >> �����Ͻ÷��� ��ǰ�� �����Ǿ����ϴ�.");
					return;
				} 
			}
			if(mdto.getPoint()<pdto.getPrice()) {
				System.out.println(" �� ����Ʈ�� �����մϴ٤Ф�");
				System.out.println(" �� �ٸ� ��ǰ�� �����Ͻðų�, ����Ʈ�� ���� �� �׾ƺ�����!");
				return;
			}
			dto.setPtel(tel);
			dto.setUsename(pname);
			int result = dao.buyProduct(dto);
			mdao.updateMember(mdto.getPoint()-pdto.getPrice(), tel);
			if (result >= 1) {				
				System.out.println(" >> "+mdto.getName()+"��, �����Ͻ� '"+pdto.getPname()+"'���Ű� �Ϸ� �Ǿ����ϴ� ��");
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

	public void stackPoint(String tel, String name, int point) { // ����Ʈ ����
		dto.setPtel(tel);
		dto.setUsename(name);
		dto.setPoint(point);
		dao.stackPoint(dto);
	}

	public void viewPoint() { // ����Ʈ ��ȸ
		String tel;
		try {
			Member member = new Member();
			MemberDAO mdao = new MemberDAOImpl();
			
			System.out.println("[����Ʈ ������ ��ȸ�մϴ�.]");
			System.out.println(" >> �޴���ȭ ��ȣ�� �Է� �� �ּ���.");
			tel = sc.next();
			MemberDTO mdto = mdao.readMember(tel);
			if (mdto == null) {
				System.out.println(" >> ��ϵ� ��ȣ�� �ƴմϴ�. ȸ�������� �Ͻðڽ��ϱ�? [Y/N]");
				String a = sc.next();
				if (a.equals("Y") || a.equals("y")) {
					member.makeId();
					return;
				} else if (a.equals("N") || a.equals("n")) {
					return;
				} else {
					System.out.println(" >> �߸� �ǰų� ���� ��ȣ �Դϴ�. �ٽ� �Է� �Ͽ� �ּ���.");
					return;
				}
			}
			  int i;
			  System.out.println(" >> "+mdto.getName()+"�� ȯ���մϴ�. ���Ͻô� ���񽺸� �����ϼ��� :D");
		         while (true) {
		            do {
		            	System.out.println("     ��   1.���� ����Ʈ   2.����Ʈ ����/��� ����  3.�ڷ� ���� =>");
		               i = sc.nextInt();
		            } while (i < 1 || i > 3);
		            if (i == 3)
		               break;
		            switch (i) {
		            case 1:
		               dto = dao.viewPoint(tel);
		               System.out.println("=========================");
		               System.out.print(" ȸ����ȣ\t\t ����Ʈ\n");
		               System.out.println("-------------------------");
		               System.out.print(" "+tel + "\t");
					   System.out.print(" "+dto.getPoint() + "\n");
					   System.out.println("=========================\n");	
		               break;

		            case 2:
		               List<PointDTO> list = dao.PointList(tel);
		               System.out.println("===================================");
		               System.out.print(" ȸ����ȣ\t\t �̿볻��\t �������Ʈ\n");
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
			System.out.println(" >> �߸� �Է� �ϼ̽��ϴ�. �ٽ� �Է��Ͽ� �ּ���.");
			sc.nextLine();
		}
	}

}
