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
		System.out.println("[분리수거를 시작합니다.]");
		String check = "";
		try {
			do {
				String name;
				int ch;

				System.out.println(" >> 쓰레기를 투입 해 주세요.");
				name = br.readLine();

				GarbageDTO Gdto = dao.insertGarbage(name);

				if (Gdto == null) {
					System.out.println("   넣으신 쓰레기는 재활용품이 아닙니다.");
					System.out.println("   일반 쓰레기로 분류 합니다.");
					return;
				}

				bcdto = barcode.readBarcode(Gdto.getGcode());
				// =============================================

				String qq = null;
				String bb = null;
				do {
					System.out.println(" >> 투입 한 쓰레기를 분류 해 주세요.");
					System.out.println("    1.캔   2.패트병   3.유리병   4.종이   5.일반쓰레기");
					ch = Integer.parseInt(br.readLine());
					

					switch (ch) {
					case 1:
						bb = "캔";
						
						break;

					case 2:
						bb = "패트병";
						break;
					case 3:
						bb = "유리병";
						break;
					case 4:
						bb = "종이";
						break;
					case 5:
						bb = "일반쓰레기";
						break;
					}
					
					System.out.println("잠시만 기다려주세요.\n");
					try {
						for (int i = 5; i >=1; i--) {
							System.out.println (":: 분리수거중... ("+i+")초 후 완료 ::");
							Thread.sleep(500);
						}
					} catch (Exception e) {
					}
				} while (ch < 1 || ch > 5);
				if (dao.insertGarbage(name).getBcategory().equals(bb)) {
					System.out.println("      → ♬ 올바른 분리수거 입니다! 감사합니다.");

					System.out.println("\n[포인트를 적립받으시겠습니까?(Y/N)]");
					qq = br.readLine();
					if (qq.equals("Y") || qq.equals("y")) {
						String t;
						System.out.println(" >> 휴대전화 번호를 입력 해 주세요.");
						t = br.readLine();
						MemberDTO Mdto = dao.serch(t);
						Member member = new Member();
						if (Mdto == null) {
							System.out.println(" >> 등록된 번호가 아닙니다. 회원가입을 하시겠습니까? [Y/N]");
							String a = br.readLine();
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
						PointMenu pm = new PointMenu();

						Mdto.setPoint(Mdto.getPoint() + bcdto.getBpoint());
						pm.stackPoint(t, bcdto.getBcategory() + "분리수거", bcdto.getBpoint());
						System.out.println("   ☞  " + Mdto.getName() + "님에게 " + bcdto.getBpoint() + " 포인트가 지급되었습니다 ^^");
						System.out.println("   ☞ 현재 " + Mdto.getName() + "님의 포인트 : " + Mdto.getPoint());
						dao.pointupdate(Mdto.getPoint(), Mdto.getTel());

					}
				} else {
					System.out.println("  → 잘못 분류 하셨습니다ㅠㅠ 환경을 위해 올바른 분리수거를 실천 해 보아요 ♬");

				}
			 

				System.out.println("\n >> 쓰레기를 더 투입 하시겠습니까?[Y/N]");
				check = br.readLine();
				if (check.equals("n") || check.equals("N")) {
					System.out.println("[분리수거를 종료합니다.]");
					return;
				}
			} while (check.equals("Y") || check.equals("y"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void listall() {
		System.out.println("\n 재활용가능쓰레기목록");

		List<GarbageDTO> list = dao.listGarbage();
		for (GarbageDTO dto : list) {
			System.out.print(dto.getGname() + "\t");
			System.out.print(dto.getGcode() + "\t");
			System.out.print(dto.getBcategory() + "\n");
		}
	}

	
}
