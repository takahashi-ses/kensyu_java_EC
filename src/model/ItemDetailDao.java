package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class ItemDetailDao {

	String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

	String JDBC_URL    = "jdbc:mysql://localhost/kensyu_db?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";

	String USER_ID     = "root";

	String USER_PASS   = "root";

	public ItemDetailDto doSelectItemDetail(int inputId) {

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection        con = null ;
		PreparedStatement ps  = null ;
		ResultSet         rs  = null ;

		ItemDetailDto dto = new ItemDetailDto();


		try {

			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT                       ");
			buf.append("  ITEMS.ID,                   ");
			buf.append("  ITEMS.NAME,                 ");
			buf.append("  ITEMS.PRICE,                ");
			buf.append("  ITEMS.RETENTION_STOCK,      ");
			buf.append("  ITEMS.STOCK,                ");
			buf.append("  ITEMS.SETUMEI,              ");
			buf.append("  ITEMS.SYOUSAI,              ");
			buf.append("  ITEMS.PICTURE,              ");
			buf.append("  ITEMS.CREATED               ");
			buf.append(" FROM                         ");
			buf.append("   ITEMS                      ");
			buf.append(" WHERE                        ");
			buf.append("   ITEMS.ID = ?               ");

			ps = con.prepareStatement(buf.toString());


			ps.setInt( 1, inputId    );

			rs = ps.executeQuery();


			if (rs.next()) {

				dto.setItemsId(              rs.getInt("ITEMS.ID")               );
				dto.setItemsName(            rs.getString("ITEMS.NAME")          );
				dto.setItemsPrice(           rs.getInt("ITEMS.PRICE")            );
				dto.setItemsRetention_stock( rs.getInt("ITEMS.RETENTION_STOCK")  );
				dto.setItemsStock(           rs.getInt("ITEMS.STOCK")            );
				dto.setItemsSetumei(         rs.getString("ITEMS.SETUMEI")       );
				dto.setItemsSyousai(         rs.getString("ITEMS.SYOUSAI")       );
				dto.setItemsPicture(         rs.getString("ITEMS.PICTURE")       );
				dto.setItemsCreated(         rs.getTimestamp("ITEMS.CREATED")    );



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
