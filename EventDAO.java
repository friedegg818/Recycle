package _Recycle;

import java.util.List;

public interface EventDAO {
	public Event_quizDTO makequiz();	
	public Event_oxDTO makeox();
	public List<EventDTO> eventlist();
}
