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

		// 퀴즈 풀기 전 번호 입력해서 멤버 정보 받기
		try {
			Event_quizDTO eqdto = edao.makequiz();
			Event_oxDTO oxdto = edao.makeox();			

			if (eqdto == null && oxdto == null) {
				System.out.println("[퀴즈 진행 시간이 아닙니다.]");				
				System.out.println(" >> QUIZ TIME");
				System.out.println(" · 환경 상식 퀴즈       PM 13:00 ~ 15:00");
				System.out.println(" · 분리수거 OX퀴즈    PM 16:00 ~ 18:00");
				System.out.println();
				return;
			}			
		

			String tel;

			System.out.println("[퀴즈를 풀고 포인트를 적립 해 보세요!]");
			System.out.println(" >> 휴대폰 번호를 입력 하세요.");
			tel = br.readLine();

			mdto = mdao.readMember(tel);

			if (mdto == null) {
				System.out.println(" >> 등록된 회원만 퀴즈에 참여 할 수 있습니다.\n");
			}

			System.out.println(" >> "+mdto.getName() + "님 환영합니다 :D");
			System.out.println("         정답을 맞추면 포인트가 지급됩니다.");			

			if (eqdto != null && oxdto == null) {

				System.out.println("\n◆ 환경 상식 퀴즈 ◆");
				System.out.println();
				System.out.println("[문제]");
				System.out.println(" "+eqdto.getQuestion());

				String answer;

				try {
					System.out.println(" 정답>>");
					answer = br.readLine();
					PointMenu pm = new PointMenu();
					if (answer.equals(eqdto.getAnswer())) {
						System.out.println(" → 정답입니다!");
						System.out.println(" → 100 포인트를 지급합니다 ♬");
						mdto.setPoint(mdto.getPoint() + 100);
						System.out.println("  ☞  현재 " + mdto.getName() + "님의 포인트 : " + mdto.getPoint() + "\n");
						
						pm.stackPoint(tel, "이벤트", 100);
						mdao.updateMember(mdto.getPoint(), mdto.getTel());

					} else {
						System.out.println(" → 오답입니다. 다음 기회에...ㅠㅠ");
						System.out.println("   [정답]"+eqdto.getAnswer());
					}

				} catch (Exception e) {
				}

			} else if (eqdto == null && oxdto != null) {

				System.out.println("\n◆ 분리수거 OX 퀴즈 ◆");
				System.out.println();
				System.out.println("[문제]");
				System.out.println(" "+oxdto.getOxquestion());

				String oxanswer;

				try {
					System.out.println(" 정답>>");
					oxanswer = br.readLine();
					PointMenu pm = new PointMenu();
					if (oxanswer.equals(oxdto.getOxanswer()) || oxanswer.equals(oxdto.getOxanswer().toLowerCase())) {
						System.out.println(" → 정답입니다!");
						System.out.println(" → 50 포인트를 지급합니다 ♬");

						mdto.setPoint(mdto.getPoint() + 50);
						System.out.println("  ☞  현재 " + mdto.getName() + "님의 포인트 : " + mdto.getPoint() + "\n");
						
						pm.stackPoint(tel, "이벤트", 50);
						mdao.updateMember(mdto.getPoint(), mdto.getTel());

					} else {
						System.out.print(" → 오답입니다. 다음 기회에...ㅠㅠ\n");
						System.out.print("   [해설] ");
						System.out.println(oxdto.getSolution() + "\n");
					}
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {

		}
	}

	public void eventlistAll() {
		System.out.println("이벤트 참여자 현황");

		List<EventDTO> list = edao.eventlist();
		for (EventDTO edto : list) {
			System.out.print(edto.getEvnum() + "\t");
			System.out.print(edto.getTel() + "\t");
			System.out.print(edto.getEpoint() + "\n");
		}
	}

}
