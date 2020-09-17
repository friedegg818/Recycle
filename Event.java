package _Recycle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Event {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private EventDAO edao = new EventDAOImpl();
	private MemberDAO mdao = new MemberDAOImpl();
	private MemberDTO mdto;
	

	public void quiz() {

		// ���� Ǯ�� �� ��ȣ �Է��ؼ� ��� ���� �ޱ�
		try {
			Event_quizDTO eqdto = edao.makequiz();
			Event_oxDTO oxdto = edao.makeox();			

			if (eqdto == null && oxdto == null) {
				System.out.println("[���� ���� �ð��� �ƴմϴ�.]");				
				System.out.println(" >> QUIZ TIME");
				System.out.println(" �� ȯ�� ��� ����       PM 13:00 ~ 15:00");
				System.out.println(" �� �и����� OX����    PM 16:00 ~ 18:00");
				System.out.println();
				return;
			}			
		

			String tel;

			System.out.println("[��� Ǯ�� ����Ʈ�� ���� �� ������!]");
			System.out.println(" >> �޴��� ��ȣ�� �Է� �ϼ���.");
			tel = br.readLine();

			mdto = mdao.readMember(tel);

			if (mdto == null) {
				System.out.println(" >> ��ϵ� ȸ���� ��� ���� �� �� �ֽ��ϴ�.\n");
			}

			System.out.println(" >> "+mdto.getName() + "�� ȯ���մϴ� :D");
			System.out.println("         ������ ���߸� ����Ʈ�� ���޵˴ϴ�.");			

			if (eqdto != null && oxdto == null) {

				System.out.println("\n�� ȯ�� ��� ���� ��");
				System.out.println();
				System.out.println("[����]");
				System.out.println(" "+eqdto.getQuestion());

				String answer;

				try {
					System.out.println(" ����>>");
					answer = br.readLine();
					PointMenu pm = new PointMenu();
					if (answer.equals(eqdto.getAnswer())) {
						System.out.println(" �� �����Դϴ�!");
						System.out.println(" �� 100 ����Ʈ�� �����մϴ� ��");
						mdto.setPoint(mdto.getPoint() + 100);
						System.out.println("  ��  ���� " + mdto.getName() + "���� ����Ʈ : " + mdto.getPoint() + "\n");
						
						pm.stackPoint(tel, "�̺�Ʈ", 100);
						mdao.updateMember(mdto.getPoint(), mdto.getTel());

					} else {
						System.out.println(" �� �����Դϴ�. ���� ��ȸ��...�Ф�");
						System.out.println("   [����]"+eqdto.getAnswer());
					}

				} catch (Exception e) {
				}

			} else if (eqdto == null && oxdto != null) {

				System.out.println("\n�� �и����� OX ���� ��");
				System.out.println();
				System.out.println("[����]");
				System.out.println(" "+oxdto.getOxquestion());

				String oxanswer;

				try {
					System.out.println(" ����>>");
					oxanswer = br.readLine();
					PointMenu pm = new PointMenu();
					if (oxanswer.equals(oxdto.getOxanswer()) || oxanswer.equals(oxdto.getOxanswer().toLowerCase())) {
						System.out.println(" �� �����Դϴ�!");
						System.out.println(" �� 50 ����Ʈ�� �����մϴ� ��");

						mdto.setPoint(mdto.getPoint() + 50);
						System.out.println("  ��  ���� " + mdto.getName() + "���� ����Ʈ : " + mdto.getPoint() + "\n");
						
						pm.stackPoint(tel, "�̺�Ʈ", 50);
						mdao.updateMember(mdto.getPoint(), mdto.getTel());

					} else {
						System.out.print(" �� �����Դϴ�. ���� ��ȸ��...�Ф�\n");
						System.out.print("   [�ؼ�] ");
						System.out.println(oxdto.getSolution() + "\n");
					}
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {

		}
	}

	public void eventlistAll() {
		System.out.println("�̺�Ʈ ������ ��Ȳ");

		List<EventDTO> list = edao.eventlist();
		for (EventDTO edto : list) {
			System.out.print(edto.getEvnum() + "\t");
			System.out.print(edto.getTel() + "\t");
			System.out.print(edto.getEpoint() + "\n");
		}
	}

}
