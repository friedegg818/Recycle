package _Recycle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.util.DBCon;

import oracle.jdbc.internal.OracleTypes;

public class ProductDAOImpl implements ProductDAO {
	private Connection con = DBCon.getConnection();

	@Override
	public int insertproduct(ProductDTO pdto) { // 관리자가 있을경우 상품 추가.
		CallableStatement cstmt = null;
		int result = 0;
		String sql;

		try {
			sql = "{CALL insertProduct(?,?,?,?)}";
			cstmt = con.prepareCall(sql);
			cstmt.setString(1, pdto.getPname());
			cstmt.setString(2, pdto.getPcode());
			cstmt.setInt(3, pdto.getPrice());
			cstmt.setInt(4, pdto.getPcount());
			cstmt.executeUpdate();

			result = 1;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		return result;
	}

	@Override
	public int updateproduct(ProductDTO pdto) { // 상품 수정
		CallableStatement cstmt = null;
		int result = 0;
		String sql;

		try {
			sql = "{CALL updateProduct(?,?)}";
			cstmt = con.prepareCall(sql);
			cstmt.setInt(1, pdto.getPcount());
			cstmt.setString(2, pdto.getPcode());
			cstmt.executeUpdate();

			result = 1;
		} catch (SQLException e) {
//			System.out.println(e.toString());
		} finally {
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		return result;
	}
	
	public ProductDTO minusproduct(String name, int count) { // 상품 사기
		PreparedStatement pstmt = null;
		ProductDTO pdto = null;
		String sql;

		try {
			 sql = "UPDATE product SET pcount=? WHERE pname=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, name);
			pstmt.executeUpdate();

			
		} catch (SQLException e) {
//			System.out.println(e.toString());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		return pdto;
	}
	

	@Override
	public int deletproduct(String code) {
		CallableStatement cstmt = null;
		int result = 0;
		String sql;

		try {
			sql = "{CALL deleteProduct(?)}";
			cstmt = con.prepareCall(sql);
			cstmt.setString(1, code);
			cstmt.executeUpdate();

			result = 1;
		} catch (SQLException e) {
//			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public List<ProductDTO> listProduct() { // 상품리스트
		List<ProductDTO> list = new ArrayList<>();
		CallableStatement cstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "{CALL listProduct(?)}";
			cstmt = con.prepareCall(sql);
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.executeUpdate();
			rs = (ResultSet) cstmt.getObject(1);

			while (rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setPname(rs.getString("pname"));
				dto.setPcode(rs.getString("pcode"));
				dto.setPrice(rs.getInt("price"));
				dto.setPcount(rs.getInt("pcount"));
				list.add(dto);
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
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		return list;
	}

	@Override
	public ProductDTO findBynameProduct(String pname) { // 상품 이름검색
		ProductDTO pdto =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT pname, pcode, price, pcount FROM product WHERE pname = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pname);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				pdto = new ProductDTO();
				pdto.setPname(rs.getString("pname"));
				pdto.setPcode(rs.getString("pcode"));
				pdto.setPrice(rs.getInt("price"));
				pdto.setPcount(rs.getInt("pcount"));
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
		return pdto;
	}

	public ProductDTO findBycodeProduct(String pcode) { // 상품 코드검색
		ProductDTO pdto = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "{CALL findBycode(?,?)}";
			cstmt = con.prepareCall(sql);
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.setString(2, pcode);
			cstmt.executeUpdate();
			rs = (ResultSet) cstmt.getObject(1);

			if (rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setPname(rs.getString("pname"));
				dto.setPcode(rs.getString("pcode"));
				dto.setPrice(rs.getInt("price"));
				dto.setPcount(rs.getInt("pcount"));
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
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		return pdto;
	}

}
