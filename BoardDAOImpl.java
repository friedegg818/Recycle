package _Recycle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.util.DBCon;

public class BoardDAOImpl implements BoardDAO{
	private Connection conn=DBCon.getConnection();
	
	@Override
	public int makeboard(BoardDTO bddto) {  //게시판쓰기
		int result = 0;
		PreparedStatement pstmt=null;
		String sql;
		try {
			sql="INSERT INTO board(bnumber, tel, title, text) ";
			sql+="VALUES(boardseq.NEXTVAL, ?, ?, ?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, bddto.getTel());
			pstmt.setString(2, bddto.getTitle());
			pstmt.setString(3, bddto.getText());
			
			pstmt.executeUpdate();
			result=1;
			
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
		}
		return result;
	}

	@Override
	public int deleteboard(int num) {  //게시판삭제
		int result=0;
		PreparedStatement pstmt=null;
		String sql;
		ResultSet rs=null;
		try {
			//해당 글번호(ROWNUM)의 bnumber 가져오기
			sql="SELECT 글번호, bnumber FROM(SELECT ROWNUM 글번호, bnumber FROM board) WHERE 글번호=? ORDER BY bnumber";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			
			int bnumber=0;
			if(rs.next()) {
				bnumber=rs.getInt("bnumber");
			}
			
			//bnumber에 해당하는 행 지우기
			pstmt.close();
			sql="DELETE FROM board WHERE bnumber=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bnumber);
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
	public int updateboard(BoardDTO dto) {  //게시판수정
		PreparedStatement pstmt=null;
		String sql;
		int result=0;
		ResultSet rs=null;
		
		try {
			//글번호에 해당하는 bnumber를 가져온다.
			sql="SELECT 글번호, bnumber, text, title FROM(SELECT ROWNUM 글번호, bnumber, text, title FROM ";
			sql+="(SELECT bnumber, text, title FROM board ORDER BY bnumber))WHERE 글번호=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getBnumber());
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setBnumber(rs.getInt("bnumber"));
				if(dto.getTitle().equals("0")) {
					dto.setTitle(rs.getString("title"));
				}
				if(dto.getText().equals("0")) {
					dto.setText(rs.getString("text"));
				}
			}
			pstmt.close();
			
			sql="UPDATE board SET title=?, text=? WHERE bnumber=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getText());
			pstmt.setInt(3, dto.getBnumber());
			
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
	public List<BoardDTO> findBoard() {  //게시판출력
		//게시판번호, 제목, 글쓴이이름
		List<BoardDTO> list=new ArrayList<BoardDTO>();
		PreparedStatement pstmt=null;
		String sql;
		ResultSet rs=null;
		try {
			
			sql="SELECT ROWNUM, title, tel, text FROM board ORDER BY bnumber";
			pstmt=conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			//SELECT 해당하는 회원의 tel, pwd를 가져온다.
			while(rs.next()) {
				BoardDTO dto=new BoardDTO();
				dto.setBnumber(rs.getInt("ROWNUM"));
				dto.setTel(rs.getString("tel"));  //
				dto.setTitle(rs.getString("title"));
				list.add(dto);
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
		return list;
	}

	@Override
	public MemberDTO checkid(MemberDTO mdto) {  //아이디확인
		PreparedStatement pstmt=null;
		String sql;
		ResultSet rs=null;
		MemberDTO dto=new MemberDTO();
		try {
			
			sql="SELECT tel, pwd FROM member WHERE tel=? AND pwd=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, mdto.getTel());
			pstmt.setString(2, mdto.getPwd());
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setTel(rs.getString("tel"));
				dto.setPwd(rs.getString("pwd"));
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
		return dto;
	}

	@Override
	public String searchtel(String tel) {  //번호입력받아서 이름불러오기
		PreparedStatement pstmt=null;
		String sql;
		ResultSet rs=null;
		String name=null;
		
		try {
			sql="SELECT name FROM member WHERE tel=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, tel);
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				name=rs.getString("name");
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
		return name;
	}

	@Override
	public BoardDTO bdtext(int num) {  //글보기
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		BoardDTO dto=new BoardDTO();
		try {			
			//bnumber 오름차순 정렬 후에 ROWNUM으로 글번호컬럼을 만들고 해당 글번호에 맞는 글을 SELECT한다.
			sql="SELECT 글번호, bnumber, title, tel, text FROM(SELECT ROWNUM 글번호, bnumber, title, tel, text";
			sql+=" FROM (SELECT bnumber, title, tel, text FROM board ORDER BY bnumber))WHERE 글번호=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto.setBnumber(rs.getInt("글번호"));
				dto.setTel(rs.getString("tel"));
				dto.setText(rs.getString("text"));
				dto.setTitle(rs.getString("title"));
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
		return dto;
	}
}
