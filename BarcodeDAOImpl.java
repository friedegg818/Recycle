package _Recycle;

import java.sql.Connection;
import java.sql.PreparedStatement;

import db.util.DBCon;

public class BarcodeDAOImpl implements BarcodeDAO{

	Connection con = DBCon.getConnection();
	@Override
	public int InsertRecycle(BarcodeDTO dto) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql;
		
		try {
			sql = "INSERT INTO barcode (bcode, bpoint, bcategory) VALUES (?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getBcode());
			pstmt.setInt(2, dto.getBpoint());
			pstmt.setString(3, dto.getBcategory());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
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
	public int UpdateRecycle(BarcodeDTO dto) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql;
		
		try {
			sql = "UPDATE barcode SET bcategory";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getBcode());
			pstmt.setInt(2, dto.getBpoint());
			pstmt.setString(3, dto.getBcategory());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
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
	public int DeleteRecycle(String code) {
		// TODO Auto-generated method stub
		return 0;
	}

}
