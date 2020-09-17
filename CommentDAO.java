package _Recycle;

import java.util.List;

public interface CommentDAO {
	public int wrcomment(CommentDTO dto);  //´ñ±Û´Þ±â
	public int decomment(CommentDTO dto);  //´ñ±Û»èÁ¦
	public int upcomment(CommentDTO dto);  //´ñ±Û¼öÁ¤
	public List<CommentDTO> listcomment(int bnum);  //´ñ±ÛÃâ·Â
	public int chbnumber(int bnumber);  //±Û¹øÈ£(ROWNUM)¹Þ¾Æ¼­ °Ô½ÃÆÇ¹øÈ£(bnumber)·Î ¹Ù²ãÁÖ±â
	public CommentDTO chcnumber(CommentDTO dto);  //´ñ±Û¹øÈ£ ¹Þ¾Æ¼­ ´ñ±Û°´Ã¼¹ÝÈ¯
}
