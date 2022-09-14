package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryDao {

	String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

	String JDBC_URL    = "jdbc:mysql://localhost/kensyu_db?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";

	String USER_ID     = "root";

	String USER_PASS   = "root";


	public List<OrderHistoryDto> doSelectOrderHistory(int inputId) {

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection        con = null ;
		PreparedStatement ps  = null ;
		ResultSet         rs  = null ;

		List<OrderHistoryDto> dtoList = new ArrayList<OrderHistoryDto>();

		try {

			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT             ");
			buf.append("  HISTORY.NAME,     ");
			buf.append("  HISTORY.PRICE,    ");
			buf.append("  NUM,              ");
			buf.append("  ITEM_ID,          ");
			buf.append("  PICTURE,          ");
			buf.append("  HISTORY.CREATED   ");
			buf.append(" FROM               ");
			buf.append("  HISTORY           ");
			buf.append(" LEFT JOIN          ");
			buf.append("  ITEMS             ");
			buf.append(" ON                 ");
			buf.append("  ITEM_ID=ITEMS.ID  ");
			buf.append(" WHERE              ");
			buf.append("  USER_ID  = ?      ");

			ps = con.prepareStatement(buf.toString());


			ps.setInt( 1, inputId     );

			rs = ps.executeQuery();


			while (rs.next()) {
				OrderHistoryDto dto = new OrderHistoryDto();
				dto.setItemName(  rs.getString("NAME")        );
				dto.setPrice(     rs.getInt("PRICE")          );
				dto.setNum(       rs.getInt("NUM")            );
				dto.setItemId(    rs.getInt("ITEM_ID")        );
				dto.setCreated(   rs.getTimestamp("CREATED")  );
				dto.setPicture(   rs.getString("PICTURE")     );
				dtoList.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}


			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}


			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return dtoList;
	}

	public OrderHistoryDto doSelectOrderHistoryItemName(int inputId) {

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection        con = null ;
		PreparedStatement ps  = null ;
		ResultSet         rs  = null ;

	    OrderHistoryDto dto = new OrderHistoryDto();

		try {

			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT             ");
			buf.append("  NAME              ");
			buf.append(" FROM               ");
			buf.append("  HISTORY           ");
			buf.append(" WHERE              ");
			buf.append("  ITEM_ID  = ?           ");

			ps = con.prepareStatement(buf.toString());


			ps.setInt( 1, inputId     );

			rs = ps.executeQuery();


			if (rs.next()) {
				dto.setItemName(  rs.getString("NAME")        );
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}


			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}


			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return dto;
	}


}
