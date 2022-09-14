package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopItemsDao {

	String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

	String JDBC_URL    = "jdbc:mysql://localhost/kensyu_db?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";

	String USER_ID     = "root";

	String USER_PASS   = "root";


	public List<TopItemsDto> doSelectTopItems(String search) {

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection        con = null ;
		PreparedStatement ps  = null ;
		ResultSet         rs  = null ;

		List<TopItemsDto> dtoList = new ArrayList<TopItemsDto>();

		try {

			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT             ");
			buf.append("  ID,               ");
			buf.append("  NAME,             ");
			buf.append("  PRICE,            ");
			buf.append("  JENRE_ID,         ");
			buf.append("  RETENTION_STOCK,  ");
			buf.append("  STOCK,            ");
			buf.append("  SETUMEI,          ");
			buf.append("  SYOUSAI,          ");
			buf.append("  PICTURE,          ");
			buf.append("  CREATED           ");
			buf.append(" FROM               ");
			buf.append("  ITEMS             ");
			buf.append(" WHERE              ");
			buf.append("   NAME             ");
			buf.append("  LIKE      ?       ");


			ps = con.prepareStatement(buf.toString());

			search = "%" + search + "%";

			ps.setString( 1, search    );

			rs = ps.executeQuery();


			while (rs.next()) {
				TopItemsDto dto = new TopItemsDto();
				dto.setId(              rs.getInt("ID")             );
				dto.setName(            rs.getString("NAME")        );
				dto.setPrice(           rs.getInt("PRICE")          );
				dto.setJenre_id(        rs.getInt("JENRE_ID")       );
				dto.setRetention_stock( rs.getInt("RETENTION_STOCK"));
				dto.setStock(           rs.getInt("STOCK")          );
				dto.setSetumei(         rs.getString("SETUMEI")     );
				dto.setSyousai(         rs.getString("SYOUSAI")     );
				dto.setPicture(         rs.getString("PICTURE")     );
				dto.setCreated(         rs.getTimestamp("CREATED")  );
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

	public boolean doInsertTopItems(TopItemsDto dto) {

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection        con = null ;
		PreparedStatement ps  = null ;


		boolean isSuccess = true ;

		try {

			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			con.setAutoCommit(false);

			StringBuffer buf = new StringBuffer();
			buf.append("INSERT INTO ITEMS    (  ");
			buf.append("  NAME,                 ");
			buf.append("  PRICE,                ");
			buf.append("  STOCK,                ");
			buf.append("  RETENTION_STOCK,      ");
			buf.append("  SETUMEI,              ");
			buf.append("  SYOUSAI,              ");
			buf.append("  PICTURE,              ");
			buf.append("  CREATED               ");
			buf.append(") VALUES (              ");
			buf.append("  ?,                    ");
			buf.append("  ?,                    ");
			buf.append("  ?,                    ");
			buf.append("  ?,                    ");
			buf.append("  ?,                    ");
			buf.append("  ?,                    ");
			buf.append("  ?,                    ");
			buf.append("  ?                     ");
			buf.append(")                       ");


			ps = con.prepareStatement(buf.toString());

			ps.setString(      1, dto.getName()            );
			ps.setInt(        2, dto.getPrice()            );
			ps.setInt(        3, dto.getStock()            );
			ps.setInt(        4, dto.getRetention_stock()  );
			ps.setString(     5, dto.getSetumei()          );
			ps.setString(     6, dto.getSyousai()          );
			ps.setString(     7, dto.getPicture()          );
			ps.setTimestamp(  8, dto.getCreated()          );

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();


			isSuccess = false ;

		} finally {

			if(isSuccess){

				try {
					con.commit();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}else{

				try {
					con.rollback();
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

		return isSuccess;
	}


}
