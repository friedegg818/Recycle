package _Recycle;

import java.util.List;

public interface CommentDAO {
	public int wrcomment(CommentDTO dto);  //��۴ޱ�
	public int decomment(CommentDTO dto);  //��ۻ���
	public int upcomment(CommentDTO dto);  //��ۼ���
	public List<CommentDTO> listcomment(int bnum);  //������
	public int chbnumber(int bnumber);  //�۹�ȣ(ROWNUM)�޾Ƽ� �Խ��ǹ�ȣ(bnumber)�� �ٲ��ֱ�
	public CommentDTO chcnumber(CommentDTO dto);  //��۹�ȣ �޾Ƽ� ��۰�ü��ȯ
}
