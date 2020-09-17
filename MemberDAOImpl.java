package _Recycle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.util.DBCon;

public class MemberDAOImpl implements MemberDAO {
	Connection con = DBCon.getConnection();
	Map<String, String> mmap = new HashMap<String, String>();
	PreparedStatement pstmt = null;
	int result = 0;
	String sql;

	@Override
	public int insertUser(MemberDTO mdto) {
		try {
			sql = "INSERT INTO member(name, tel, pwd, point) VALUES(?,?,?,0)";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, mdto.getName());
			pstmt.setString(2, mdto.getTel());
			pstmt.setString(3, mdto.getPwd());

			pstmt.executeUpdate();

			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public MemberDTO listUser(String tel) {
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
		MemberDTO mdto = null;
        
		try {
			sb.append("SELECT m.tel, name, point");
			sb.append(" FROM member m ");
			sb.append(" WHERE m.tel = " + tel);

			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mdto = new MemberDTO();
				mdto.setTel(rs.getString("tel"));
				mdto.setName(rs.getString("name"));
				mdto.setPoint(rs.getInt("point"));
				list.add(mdto);
			}

		} catch (SQLException e) {
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
		return mdto;
		
	}

	@Override
	public int deleteUser(String tel) {
		try {
			sql = "DELETE FROM member WHERE tel=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, tel);

			pstmt.executeUpdate();
			
			result = 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<MemberDTO> AllListUser() {
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		ResultSet rs = null;
		
		try {
			sql = "SELECT name, tel, pwd, point "
			+ "FROM member";

			pstmt = con.prepareStatement(sql);
			
		    rs = pstmt.executeQuery();
			
		    while(rs.next()) {
		    	MemberDTO mdto = new MemberDTO();
		    	mdto.setName(rs.getString("name"));
		    	mdto.setTel(rs.getString("tel"));
		    	mdto.setPwd(rs.getString("pwd"));
		    	mdto.setPoint(rs.getInt("point"));
		    	list.add(mdto);
		    }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public MemberDTO readUser(String tel, String pwd) { // 일치 여부
		ResultSet rs = null;
		MemberDTO mdto = null;
	    try {
	    	
	    	sql = "SELECT tel, name, pwd, point FROM member WHERE tel=? AND pwd=?";
			
	    	pstmt = con.prepareStatement(sql);
	    	
	    	pstmt.setString(1, tel);
	    	pstmt.setString(2, pwd);
	    	
	    	rs = pstmt.executeQuery();
	    	
	    	while(rs.next()) {
	    	mdto = new MemberDTO();
	    	mdto.setTel(rs.getString("tel"));
	    	mdto.setPwd(rs.getString("pwd"));
	    	mdto.setName(rs.getString("name"));
	    	mdto.setPoint(rs.getInt("point"));
	    	}
	    	if(mdto!=null) {
	    		return mdto;
	    	} 
		} catch (SQLException e) {
			
		}	
		return null;
	}

	@Override
	public MemberDTO readMember(String tel) {
		ResultSet rs = null;
		MemberDTO mdto = null;
	    try {
	    	
	    	sql = "SELECT name, tel, pwd, point FROM member WHERE tel= ?";
			
	    	pstmt = con.prepareStatement(sql);
	    	
	    	pstmt.setString(1, tel);
	    	
	    	rs = pstmt.executeQuery();
	    	
	    	while(rs.next()) {
	    	mdto = new MemberDTO();	
	    	mdto.setName(rs.getString("name"));
	    	mdto.setTel(rs.getString("tel"));
	    	mdto.setPwd(rs.getString("pwd"));
	    	mdto.setPoint(rs.getInt("point"));
	    	}

		} catch (SQLException e) {
			
		}	
		return mdto;
	}
	@Override
	public MemberDTO updateMember(int point, String tel) { // 포인트 갱신
		PreparedStatement pstmt = null;
		String sql;
		MemberDTO mdto = null;

		try {
			sql = "UPDATE member SET point=? WHERE tel=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, point);
			pstmt.setString(2, tel);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {

				}
			}
		}

		return mdto;
	}
}
