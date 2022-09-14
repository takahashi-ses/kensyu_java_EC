package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

	String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

	String JDBC_URL    = "jdbc:mysql://localhost/kensyu_db?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";

	String USER_ID     = "root";

	String USER_PASS   = "root";


	public List<AdminDto> doSelectItem() {

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection        con = null ;
		PreparedStatement ps  = null ;
		ResultSet         rs  = null ;

		List<AdminDto> dtoList = new ArrayList<AdminDto>();

		try {

			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT                         ");
			buf.append("  ID,                           ");
			buf.append("  NAME,                         ");
			buf.append("  PRICE,                        ");
			buf.append("  RETENTION_STOCK,              ");
			buf.append("  STOCK,                        ");
			buf.append("  SETUMEI,                      ");
			buf.append("  SYOUSAI,                      ");
			buf.append("  PICTURE,                      ");
			buf.append("  CREATED                       ");
			buf.append(" FROM                           ");
			buf.append("  ITEMS                         ");
			buf.append(" ORDER BY                       ");
			buf.append("  CREATED                       ");
			buf.append("   DESC                         ");

			ps = con.prepareStatement(buf.toString());

			rs = ps.executeQuery();


			while (rs.next()) {
				AdminDto dto = new AdminDto();
				dto.setId(              rs.getInt("ID")              );
				dto.setName(            rs.getString("NAME")         );
				dto.setPrice(           rs.getInt("PRICE")           );
				dto.setRetention_stock( rs.getInt("RETENTION_STOCK") );
				dto.setStock(           rs.getInt("STOCK")           );
				dto.setSetumei(         rs.getString("SETUMEI")       );
				dto.setSyousai(         rs.getString("SYOUSAI")      );
				dto.setPicture(         rs.getString("PICTURE")      );
				dto.setCreated(         rs.getTimestamp("CREATED")   );
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

	public boolean doDelete(int inputId) {

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
			buf.append("DELETE            ");
			buf.append("  FROM            ");
			buf.append("   ITEMS          ");
			buf.append("  WHERE           ");
			buf.append("   ID=?           ");
			buf.append("  LIMIT 1         ");


			ps = con.prepareStatement(buf.toString());


			ps.setInt(   1, inputId    );


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

	public List<AdminDto> doSelectUser() {

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection        con = null ;
		PreparedStatement ps  = null ;
		ResultSet         rs  = null ;

		List<AdminDto> dtoList = new ArrayList<AdminDto>();

		try {

			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT                            ");
			buf.append("  USERS.ID,                        ");
			buf.append("  USERS.NAME,                      ");
			buf.append("  SUM(HISTORY.PRICE*HISTORY.NUM),  ");
			buf.append("  COUNT(HISTORY.NUM)               ");
			buf.append(" FROM                              ");
			buf.append("  USERS                            ");
			buf.append(" LEFT JOIN                         ");
			buf.append("  HISTORY ON                       ");
			buf.append("   USERS.ID=HISTORY.USER_ID        ");
			buf.append(" GROUP BY                          ");
			buf.append("  USERS.ID                         ");


			ps = con.prepareStatement(buf.toString());

			rs = ps.executeQuery();


			while (rs.next()) {
				AdminDto dto = new AdminDto();
				dto.setId(              rs.getInt("USERS.ID")                       );
				dto.setName(            rs.getString("USERS.NAME")                  );
				dto.setPurchase_price(  rs.getInt("SUM(HISTORY.PRICE*HISTORY.NUM)") );
				dto.setPurchase_number( rs.getInt("COUNT(HISTORY.NUM)")             );
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

	public List<AdminDto> doSelectUserReview() {

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection        con = null ;
		PreparedStatement ps  = null ;
		ResultSet         rs  = null ;

		List<AdminDto> dtoList = new ArrayList<AdminDto>();

		try {

			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT                            ");
			buf.append("  USERS.ID,                        ");
			buf.append("  COUNT(STAR)                      ");
			buf.append(" FROM                              ");
			buf.append("  USERS                            ");
			buf.append(" LEFT JOIN                         ");
			buf.append("  REVIEWS ON                       ");
			buf.append("   USERS.ID=REVIEWS.USER_ID        ");
			buf.append(" GROUP BY                          ");
			buf.append("  USERS.ID                         ");


			ps = con.prepareStatement(buf.toString());

			rs = ps.executeQuery();


			while (rs.next()) {
				AdminDto dto = new AdminDto();
				dto.setUserReviewId( rs.getInt("USERS.ID")           );
				dto.setReviewNumber( rs.getInt("COUNT(STAR)")        );
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

	public List<AdminDto> doSelectReviewDetail(int inputId) {

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection        con = null ;
		PreparedStatement ps  = null ;
		ResultSet         rs  = null ;

		List<AdminDto> dtoList = new ArrayList<AdminDto>();

		try {

			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT                            ");
			buf.append("  REVIEWS.ID,                      ");
			buf.append("  COMMENT,                         ");
			buf.append("  STAR,                            ");
			buf.append("  ITEMS.NAME,                      ");
			buf.append("  REVIEWS.CREATED                  ");
			buf.append(" FROM                              ");
			buf.append("  REVIEWS                          ");
			buf.append(" LEFT JOIN                         ");
			buf.append("  ITEMS ON                         ");
			buf.append("   REVIEWS.ITEM_ID=ITEMS.ID        ");
			buf.append(" WHERE                             ");
			buf.append("  REVIEWS.USER_ID = ?               ");


			ps = con.prepareStatement(buf.toString());

			ps.setInt(   1, inputId    );

			rs = ps.executeQuery();


			while (rs.next()) {
				AdminDto dto = new AdminDto();
				dto.setReviewId(       rs.getInt("REVIEWS.ID")           );
				dto.setComment(        rs.getString("COMMENT")           );
				dto.setStar(           rs.getInt("STAR")                 );
				dto.setItemsName(      rs.getString("ITEMS.NAME")        );
				dto.setReviewsCreated( rs.getTimestamp("REVIEWS.CREATED"));
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

	public boolean doDeleteReview(int inputId) {

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
			buf.append("DELETE            ");
			buf.append("  FROM            ");
			buf.append("   REVIEWS        ");
			buf.append("  WHERE           ");
			buf.append("   ID=?           ");
			buf.append("  LIMIT 1         ");


			ps = con.prepareStatement(buf.toString());


			ps.setInt(   1, inputId    );


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
