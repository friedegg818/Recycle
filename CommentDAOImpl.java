package _Recycle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.util.DBCon;

public class CommentDAOImpl implements CommentDAO{
	private Connection conn=DBCon.getConnection();

	@Override
	public int wrcomment(CommentDTO dto) {  //댓글달기
		int result=0;
		PreparedStatement pstmt=null;
		String sql;
		try {
			sql="INSERT INTO bdcomment(bnumber, cnumber, ctext, tel) ";
			sql+="VALUES(?,bdcseq.NEXTVAL,?,?)";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, chbnumber(dto.getBnumber()));
			pstmt.setString(2, dto.getText());
			pstmt.setString(3, dto.getTel());
			
			pstmt.executeUpdate();
			
			result=1;
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		return result;
	}

	@Override
	public int decomment(CommentDTO dto) {  //댓글삭제
		//입력받은 번호를 댓글번호로 변경해서 삭제
		PreparedStatement pstmt=null;
		String sql;
		int result=0;
		
		try {
			sql="DELETE FROM bdcomment WHERE cnumber=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, chcnumber(dto).getCnumber());
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		return result;
		
	}

	@Override
	public int upcomment(CommentDTO dto) {  //댓글수정
		PreparedStatement pstmt=null;
		String sql;
		int result=0;
		
		try {
			sql="UPDATE bdcomment SET ctext=? WHERE cnumber=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getText());
			pstmt.setInt(2, chcnumber(dto).getCnumber());
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		return result;
	}

	@Override
	public List<CommentDTO> listcomment(int bnum) {  //댓글전체보기
		List<CommentDTO> list=new ArrayList<CommentDTO>();
		PreparedStatement pstmt=null;
		String sql=null;
		ResultSet rs=null;
		CommentDTO dto=null;
		
		try {
			sql="SELECT cnumber, ctext, tel FROM bdcomment WHERE bnumber=? ORDER BY cnumber";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, chbnumber(bnum));
			rs=pstmt.executeQuery();
			while(rs.next()) {
				dto=new CommentDTO();
				dto.setCnumber(rs.getInt("cnumber"));
				dto.setTel(rs.getString("tel"));
				dto.setText(rs.getString("ctext"));
				list.add(dto);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		return list;
	}

	@Override
	public int chbnumber(int bnumber) {  //글번호(ROWNUM)받아서 게시판번호(bnumber)로 바꿔주기
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		int num=0;
		try {			
			//bnumber 오름차순 정렬 후에 ROWNUM으로 글번호컬럼을 만들고 해당 글번호에 맞는 글을 SELECT한다.
			sql="SELECT 글번호, bnumber FROM(SELECT ROWNUM 글번호, bnumber";
			sql+=" FROM (SELECT bnumber FROM board ORDER BY bnumber))WHERE 글번호=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bnumber);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				num=rs.getInt("bnumber");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		return num;
	}

	@Override
	public CommentDTO chcnumber(CommentDTO dto) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		CommentDTO cdto=new CommentDTO();
		try {			
			//cnumber 오름차순 정렬 후에 ROWNUM으로 글번호컬럼을 만들고 해당 글번호에 맞는 글을 SELECT한다.
			sql="SELECT 댓글번호, cnumber, tel FROM(SELECT ROWNUM 댓글번호, cnumber, tel ";
			sql+="FROM (SELECT cnumber, tel FROM bdcomment WHERE bnumber=? ORDER BY cnumber))WHERE 댓글번호=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, chbnumber(dto.getBnumber()));
			pstmt.setInt(2, dto.getCnumber());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				cdto.setCnumber(rs.getInt("cnumber"));
				cdto.setTel(rs.getString("tel"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		return cdto;
	}
}
