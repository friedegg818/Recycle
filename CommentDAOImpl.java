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
	public int wrcomment(CommentDTO dto) {  //��۴ޱ�
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
	public int decomment(CommentDTO dto) {  //��ۻ���
		//�Է¹��� ��ȣ�� ��۹�ȣ�� �����ؼ� ����
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
	public int upcomment(CommentDTO dto) {  //��ۼ���
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
	public List<CommentDTO> listcomment(int bnum) {  //�����ü����
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
	public int chbnumber(int bnumber) {  //�۹�ȣ(ROWNUM)�޾Ƽ� �Խ��ǹ�ȣ(bnumber)�� �ٲ��ֱ�
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		int num=0;
		try {			
			//bnumber �������� ���� �Ŀ� ROWNUM���� �۹�ȣ�÷��� ����� �ش� �۹�ȣ�� �´� ���� SELECT�Ѵ�.
			sql="SELECT �۹�ȣ, bnumber FROM(SELECT ROWNUM �۹�ȣ, bnumber";
			sql+=" FROM (SELECT bnumber FROM board ORDER BY bnumber))WHERE �۹�ȣ=?";
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
			//cnumber �������� ���� �Ŀ� ROWNUM���� �۹�ȣ�÷��� ����� �ش� �۹�ȣ�� �´� ���� SELECT�Ѵ�.
			sql="SELECT ��۹�ȣ, cnumber, tel FROM(SELECT ROWNUM ��۹�ȣ, cnumber, tel ";
			sql+="FROM (SELECT cnumber, tel FROM bdcomment WHERE bnumber=? ORDER BY cnumber))WHERE ��۹�ȣ=?";
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
