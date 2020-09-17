package _Recycle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.util.DBCon;

public class GarbageDAOImpl implements GarbageDAO {
	private Connection conn = DBCon.getConnection();

	@Override
	public GarbageDTO insertGarbage(String name) { // 카테고리
		GarbageDTO Gdto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		sql = "select gname, bcategory,gcode from garbage where gname = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				Gdto = new GarbageDTO();
				Gdto.setGcode(rs.getString("gcode"));
				Gdto.setGname(rs.getString("gname"));
				Gdto.setBcategory(rs.getString("bcategory"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		return Gdto;

	}

	@Override
	public List<GarbageDTO> listGarbage() {
		List<GarbageDTO> list = new ArrayList<GarbageDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		sql = "select gname, gcode, bcategory from garbage";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GarbageDTO dto = new GarbageDTO();

				dto.setGname(rs.getString("Gname"));
				dto.setGcode(rs.getString("Gcode"));
				dto.setBcategory(rs.getString("bcategory"));

				list.add(dto);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}

		return list;
	}

	@Override
	public MemberDTO serch(String tel) { // 회원정보 비교
		MemberDTO Mdto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		sql = "select name, tel, pwd, point from member where tel =?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tel);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				Mdto = new MemberDTO();
				Mdto.setName(rs.getString("name"));
				Mdto.setTel(rs.getString("Tel"));
				Mdto.setPwd(rs.getString("pwd"));
				Mdto.setPoint(rs.getInt("point"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		return Mdto;

	}

	@Override
	public MemberDTO pointupdate(int point, String tel) { // 포인트적립
		PreparedStatement pstmt = null;
		String sql;
		MemberDTO Mdto = null;

		try {
			sql = "update member set point =? where tel = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, point);
			pstmt.setString(2, tel);
			pstmt.executeUpdate();

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

		return Mdto;
	}

	public GarbageDTO findbynameGarbage(String name) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		GarbageDTO gdto = null;

		sql = "select gname, gcode, bcategory from garbage where gname = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				gdto = new GarbageDTO();

				gdto.setGname(rs.getString("Gname"));
				gdto.setGcode(rs.getString("Gcode"));
				gdto.setBcategory(rs.getString("bcategory"));

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

		return gdto;
	}

}
