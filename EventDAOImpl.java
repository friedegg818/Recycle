package _Recycle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.util.DBCon;

public class EventDAOImpl implements EventDAO {
	private Connection conn = DBCon.getConnection();

	@Override
	public Event_quizDTO makequiz() { // 퀴즈 생성 - 진행
		Event_quizDTO eqdto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();

		try {
			sb.append("SELECT Q.* FROM");
			sb.append(" (SELECT question, answer FROM EVENT_QUIZ");
			sb.append(" ORDER BY DBMS_RANDOM.VALUE) Q");
			sb.append(" WHERE ROWNUM = 1");
			sb.append(" AND SUBSTR(TO_CHAR(SYSDATE, 'HH24:MI'),4,2) BETWEEN 45 AND 46");  //--

			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				eqdto = new Event_quizDTO();
				eqdto.setQuestion(rs.getString("question"));
				eqdto.setAnswer(rs.getString("answer"));
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		return eqdto;

	}

	@Override
	public Event_oxDTO makeox() {
		Event_oxDTO oxdto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();

		try {
			sb.append("SELECT OX.* FROM");
			sb.append(" (SELECT oxquestion, oxanswer, solution FROM EVENT_OX");
			sb.append(" ORDER BY DBMS_RANDOM.VALUE) OX");
			sb.append(" WHERE ROWNUM = 1");
			sb.append(" AND SUBSTR(TO_CHAR(SYSDATE, 'HH24:MI'),4,2) BETWEEN 01 AND 02");  //--

			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				oxdto = new Event_oxDTO();
				oxdto.setOxquestion(rs.getString("oxquestion"));
				oxdto.setOxanswer(rs.getString("oxanswer"));
				oxdto.setSolution(rs.getString("solution"));
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		return oxdto;
	}

	@Override
	public List<EventDTO> eventlist() {
		List<EventDTO> list = new ArrayList<EventDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();

		try {
			sb.append("SELECT enum, tel, epoint");
			sb.append(" FROM event");

			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				EventDTO edto = new EventDTO();
				edto.setEvnum(rs.getInt("enum"));
				edto.setTel(rs.getString("tel"));
				edto.setEpoint(rs.getInt("epoint"));

				list.add(edto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}

		return list;
	}

}
