package _Recycle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;


public class Garbage {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private GarbageDAO dao = new GarbageDAOImpl();
	private BarcodeDTO bcdto = new BarcodeDTO();
	private Barcode barcode = new Barcode();

	public void insert() {
		System.out.println("[�и����Ÿ� �����մϴ�.]");
		String check = "";
		try {
			do {
				String name;
				int ch;

				System.out.println(" >> �����⸦ ���� �� �ּ���.");
				name = br.readLine();

				GarbageDTO Gdto = dao.insertGarbage(name);

				if (Gdto == null) {
					System.out.println("   ������ ������� ��Ȱ��ǰ�� �ƴմϴ�.");
					System.out.println("   �Ϲ� ������� �з� �մϴ�.");
					return;
				}

				bcdto = barcode.readBarcode(Gdto.getGcode());
				// =============================================

				String qq = null;
				String bb = null;
				do {
					System.out.println(" >> ���� �� �����⸦ �з� �� �ּ���.");
					System.out.println("    1.ĵ   2.��Ʈ��   3.������   4.����   5.�Ϲݾ�����");
					ch = Integer.parseInt(br.readLine());
					

					switch (ch) {
					case 1:
						bb = "ĵ";
						
						break;

					case 2:
						bb = "��Ʈ��";
						break;
					case 3:
						bb = "������";
						break;
					case 4:
						bb = "����";
						break;
					case 5:
						bb = "�Ϲݾ�����";
						break;
					}
					
					System.out.println("��ø� ��ٷ��ּ���.\n");
					try {
						for (int i = 5; i >=1; i--) {
							System.out.println (":: �и�������... ("+i+")�� �� �Ϸ� ::");
							Thread.sleep(500);
						}
					} catch (Exception e) {
					}
				} while (ch < 1 || ch > 5);
				if (dao.insertGarbage(name).getBcategory().equals(bb)) {
					System.out.println("      �� �� �ùٸ� �и����� �Դϴ�! �����մϴ�.");

					System.out.println("\n[����Ʈ�� ���������ðڽ��ϱ�?(Y/N)]");
					qq = br.readLine();
					if (qq.equals("Y") || qq.equals("y")) {
						String t;
						System.out.println(" >> �޴���ȭ ��ȣ�� �Է� �� �ּ���.");
						t = br.readLine();
						MemberDTO Mdto = dao.serch(t);
						Member member = new Member();
						if (Mdto == null) {
							System.out.println(" >> ��ϵ� ��ȣ�� �ƴմϴ�. ȸ�������� �Ͻðڽ��ϱ�? [Y/N]");
							String a = br.readLine();
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
						PointMenu pm = new PointMenu();

						Mdto.setPoint(Mdto.getPoint() + bcdto.getBpoint());
						pm.stackPoint(t, bcdto.getBcategory() + "�и�����", bcdto.getBpoint());
						System.out.println("   ��  " + Mdto.getName() + "�Կ��� " + bcdto.getBpoint() + " ����Ʈ�� ���޵Ǿ����ϴ� ^^");
						System.out.println("   �� ���� " + Mdto.getName() + "���� ����Ʈ : " + Mdto.getPoint());
						dao.pointupdate(Mdto.getPoint(), Mdto.getTel());

					}
				} else {
					System.out.println("  �� �߸� �з� �ϼ̽��ϴ٤Ф� ȯ���� ���� �ùٸ� �и����Ÿ� ��õ �� ���ƿ� ��");

				}
			 

				System.out.println("\n >> �����⸦ �� ���� �Ͻðڽ��ϱ�?[Y/N]");
				check = br.readLine();
				if (check.equals("n") || check.equals("N")) {
					System.out.println("[�и����Ÿ� �����մϴ�.]");
					return;
				}
			} while (check.equals("Y") || check.equals("y"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void listall() {
		System.out.println("\n ��Ȱ�밡�ɾ�������");

		List<GarbageDTO> list = dao.listGarbage();
		for (GarbageDTO dto : list) {
			System.out.print(dto.getGname() + "\t");
			System.out.print(dto.getGcode() + "\t");
			System.out.print(dto.getBcategory() + "\n");
		}
	}

	
}
