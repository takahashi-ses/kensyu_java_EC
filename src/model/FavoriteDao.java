package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavoriteDao {

	String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

	String JDBC_URL    = "jdbc:mysql://localhost/kensyu_db?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";

	String USER_ID     = "root";

	String USER_PASS   = "root";


	public boolean doInsert(FavoriteDto dto) {

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
			buf.append("INSERT INTO FAVORITE (  ");
			buf.append("  USER_ID,              ");
			buf.append("  ITEM_ID,              ");
			buf.append("  CREATED               ");
			buf.append(") VALUES (              ");
			buf.append("  ?,                    ");
			buf.append("  ?,                    ");
			buf.append("  ?                     ");
			buf.append(")                       ");


			ps = con.prepareStatement(buf.toString());

			ps.setInt(       1, dto.getUser_id()      );
			ps.setInt(       2, dto.getItem_id()      );
			ps.setTimestamp( 3, dto.getCreated()      );

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


	public List<FavoriteDto> doSelect(int inputId) {

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection        con = null ;
		PreparedStatement ps  = null ;
		ResultSet         rs  = null ;

		List<FavoriteDto> dtoList = new ArrayList<FavoriteDto>();

		try {

			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT                         ");
			buf.append("  FAVORITE.USER_ID,             ");
			buf.append("  ITEM_ID,                      ");
			buf.append("  ITEMS.PICTURE,                ");
			buf.append("  ITEMS.NAME,                   ");
			buf.append("  ITEMS.PRICE,                  ");
			buf.append("  FAVORITE.CREATED              ");
			buf.append(" FROM                           ");
			buf.append("  FAVORITE                      ");
			buf.append(" INNER JOIN                     ");
			buf.append("  ITEMS ON                      ");
			buf.append("   ITEM_ID=ITEMS.ID             ");
			buf.append(" WHERE                          ");
			buf.append("  USER_ID = ?                   ");

			ps = con.prepareStatement(buf.toString());


			ps.setInt( 1, inputId     );

			rs = ps.executeQuery();


			while (rs.next()) {
				FavoriteDto dto = new FavoriteDto();
				dto.setUser_id(              rs.getInt("USER_ID")         );
				dto.setItem_id(              rs.getInt("ITEM_ID")         );
				dto.setFavoriteItemPicture(  rs.getString("ITEMS.PICTURE"));
				dto.setFavoriteItemName(     rs.getString("ITEMS.NAME")   );
				dto.setFavoriteItemPrice(    rs.getInt("ITEMS.PRICE")     );
				dto.setCreated(              rs.getTimestamp("FAVORITE.CREATED")  );
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

	public int doSelectFavoriteCheck(FavoriteDto dto) {

		int checkCount = 0;

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection        con = null ;
		PreparedStatement ps  = null ;
		ResultSet         rs  = null ;


		try {

			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT                     ");
			buf.append("  COUNT(ITEM_ID) as COUNT   ");
			buf.append(" FROM                       ");
			buf.append("  FAVORITE                  ");
			buf.append(" WHERE                      ");
			buf.append("  USER_ID = ?  AND          ");
			buf.append("  ITEM_ID = ?               ");

			ps = con.prepareStatement(buf.toString());

			ps.setInt( 1, dto.getUser_id()     );
			ps.setInt( 2, dto.getItem_id()     );


			rs = ps.executeQuery();


			if (rs.next()) {
				checkCount = rs.getInt("COUNT");
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


		return checkCount;
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
			buf.append("   FAVORITE       ");
			buf.append("  WHERE           ");
			buf.append("   ITEM_ID=?      ");
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
