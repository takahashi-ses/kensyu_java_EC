package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InquiryDao {

	String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

	String JDBC_URL    = "jdbc:mysql://localhost/kensyu_db?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";

	String USER_ID     = "root";

	String USER_PASS   = "root";

	public boolean doInsert(InquiryDto dto) {

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
			buf.append("INSERT INTO INQUIRY  (  ");
			buf.append("  ID,                   ");
			buf.append("  EMAIL,                ");
			buf.append("  INQUIRY_POST,         ");
			buf.append("  KENMEI,               ");
			buf.append("  NAME,                 ");
			buf.append("  CREATED               ");
			buf.append(") VALUES (              ");
			buf.append("  ?,                    ");
			buf.append("  ?,                    ");
			buf.append("  ?,                    ");
			buf.append("  ?,                    ");
			buf.append("  ?,                    ");
			buf.append("  ?                     ");
			buf.append(")                       ");


			ps = con.prepareStatement(buf.toString());

			ps.setInt(       1, dto.getId()            );
			ps.setString(    2, dto.getEmail()         );
			ps.setString(    3, dto.getInquiry_post()  );
			ps.setString(    4, dto.getKenmei()        );
			ps.setString(    5, dto.getName()          );
			ps.setTimestamp( 6, dto.getCreated()       );

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
