package _Recycle;

public class Event_quizDTO {
	private int qnum;
	private String question;					// 퀴즈 내용
	private String answer;					// 퀴즈에 대한 정답	

	public int getQnum() {
		return qnum;
	}
	public void setQnum(int qnum) {
		this.qnum = qnum;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
