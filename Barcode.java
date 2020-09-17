package _Recycle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.util.DBCon;

public class Barcode {
	private Connection con = DBCon.getConnection();
	
	
	public BarcodeDTO readBarcode(String code) {
		ResultSet rs = null;
		BarcodeDTO bcdto = null;
		String sql;
		PreparedStatement pstmt = null;
	    try {
	    	
	    	sql = "SELECT bcode, bpoint, bcategory  FROM barcode WHERE SUBSTR(bcode, 4, 1) = SUBSTR(?,4,1)";
			
	    	pstmt = con.prepareStatement(sql);
	    	
	    	pstmt.setString(1, code);
	    	
	    	rs = pstmt.executeQuery();
	    	
	    	if(rs.next()) {
	    	bcdto = new BarcodeDTO();	
	    	bcdto.setBcode(rs.getString("bcode"));
	    	bcdto.setBpoint(rs.getInt("bpoint"));
	    	bcdto.setBcategory(rs.getString("bcategory"));
	    	}

		} catch (SQLException e) {
			
		}	
		
		return bcdto;
		
	}
	public BarcodeDTO readerBarcode(String code) {
		ResultSet rs = null;
		BarcodeDTO bcdto = null;
		String sql;
		PreparedStatement pstmt = null;
	    try {
	    	
	    	sql = "SELECT bcode, bpoint, bcategory  FROM barcode WHERE SUBSTR(bcode, 4, 1) = SUBSTR(?,4,1)";
			
	    	pstmt = con.prepareStatement(sql);
	    	
	    	pstmt.setString(1, code);
	    	
	    	rs = pstmt.executeQuery();
	    	
	    	if(rs.next()) {
	    	bcdto = new BarcodeDTO();	
	    	bcdto.setBcode(rs.getString("bcode"));
	    	bcdto.setBpoint(rs.getInt("bpoint"));
	    	bcdto.setBcategory(rs.getString("bcatefory"));
	    	}

		} catch (SQLException e) {
			
		}	
		
		return bcdto;
		
	}
}
