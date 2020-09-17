package _Recycle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.util.DBCon;

public class PointDAOImpl implements PointDAO {
	private Connection con = DBCon.getConnection();

	@Override
	public PointDTO searchPoint(String tel) { // 전화번호를 받아서 포인트 조회.
		PointDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "SELECT ptel, usename, point FROM point WHERE ptel = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, tel);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				dto = new PointDTO();
				dto.setPtel(rs.getString("ptel"));
				dto.setUsename(rs.getString("usename"));
				dto.setPoint(rs.getInt("point"));
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
		return dto;

	}

	@Override
	public int buyProduct(PointDTO dto) { // 포인트 계산
		PreparedStatement pstmt = null;
		int result = 0;
		String sql;
		ProductDAO pdao = new ProductDAOImpl();
		ProductDTO pdto = pdao.findBynameProduct(dto.getUsename());
		try {
			sql = "INSERT INTO point (ptel, usename, point) VALUES(?,?,?)";
//			sql = "UPDATE member SET point = point+? WHERE tel = ?";
			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, dto.getPoint());
//			pstmt.setString(2, dto.getTel());
			pstmt.setString(1, dto.getPtel());
			pstmt.setString(2, dto.getUsename());
			pstmt.setInt(3, -pdto.getPrice());
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
		return result;
	}

	@Override
	public int stackPoint(PointDTO dto) { // 포인트 적립
		PreparedStatement pstmt = null;
		int result = 0;
		String sql;

		try {
			sql = "INSERT INTO point (ptel, usename, point) VALUES (?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getPtel());
			pstmt.setString(2, dto.getUsename());
			pstmt.setInt(3, dto.getPoint());
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
		return result;
	}

	@Override
	public PointDTO viewPoint(String tel) { // 포인트 확인.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		PointDTO dto = null;
		MemberDTO mdto = new MemberDTO();

		try {
			sql = "SELECT ptel, SUM(point) FROM point WHERE ptel = ? GROUP BY ptel";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, tel);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new PointDTO();
				mdto.setTel(rs.getString("ptel"));
				dto.setPoint(rs.getInt("SUM(point)"));
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
		return dto;
	}

	@Override
	public List<PointDTO> PointList(String tel) { //
		List<PointDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT ptel, usename, point FROM point Where ptel = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, tel);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PointDTO dto = new PointDTO();
				dto.setPtel(rs.getString("ptel"));
				dto.setUsename(rs.getString("usename"));
				dto.setPoint(rs.getInt("point"));
				list.add(dto);
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

//	public void login(String tel, String pwd) {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("번호를 입력하여주세요 ");
//		tel = sc.next();
//		System.out.println("비밀번호를 입력하여주세요");
//		pwd = sc.next();
//		MemberDAO mdao = new MemberDAOImpl();
//		MemberDTO mdto = mdao.readMember(tel);
//		sc.close();
//		if (mdto == null) { // 회원 리스트 돌려서 확인
//			System.out.println("전화번호를 잘못 입력하였습니다 다시입력하여 주세요.");
//			return;
//		} else if (!mdto.getPwd().equals(pwd)) {
//			System.out.println("비밀번호를 잘못입력 하였습니다 다시 입력하여 주세요");
//			return;
//
//		}
//	}

}
