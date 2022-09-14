package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDao {

	String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

	String JDBC_URL    = "jdbc:mysql://localhost/kensyu_db?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";

	String USER_ID     = "root";

	String USER_PASS   = "root";


	public boolean doInsert(CartDto dto) {

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
			buf.append("INSERT INTO Cart     (  ");
			buf.append("  USER_ID,              ");
			buf.append("  ITEM_ID,              ");
			buf.append("  NUMBER,               ");
			buf.append("  CREATED               ");
			buf.append(") VALUES (              ");
			buf.append("  ?,                    ");
			buf.append("  ?,                    ");
			buf.append("  ?,                    ");
			buf.append("  ?                     ");
			buf.append(")                       ");


			ps = con.prepareStatement(buf.toString());

			ps.setInt(       1, dto.getUser_id()      );
			ps.setInt(       2, dto.getItem_id()      );
			ps.setInt(       3, dto.getNumber()       );
			ps.setTimestamp( 4, dto.getCreated()      );

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

	public boolean doUpdate(CartDto dto) {

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
			buf.append("UPDATE CART           ");
			buf.append("  SET                 ");
			buf.append("  	NUMBER     = ?    ");
			buf.append("  WHERE               ");
			buf.append("   USER_ID = ?  AND   ");
			buf.append("   ITEM_ID = ?        ");



			ps = con.prepareStatement(buf.toString());


			ps.setInt(       1, dto.getNumber()        );
			ps.setInt(       2, dto.getUser_id()       );
			ps.setInt(       3, dto.getItem_id()       );


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

	public List<CartDto> doSelectCart(int inputId) {

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection        con = null ;
		PreparedStatement ps  = null ;
		ResultSet         rs  = null ;

		List<CartDto> dtoList = new ArrayList<CartDto>();

		try {

			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT                         ");
			buf.append("  CART.ID,                      ");
			buf.append("  USER_ID,                      ");
			buf.append("  ITEM_ID,                      ");
			buf.append("  NUMBER,                       ");
			buf.append("  ITEMS.PICTURE,                ");
			buf.append("  ITEMS.NAME,                   ");
			buf.append("  ITEMS.PRICE,                  ");
			buf.append("  NUMBER*ITEMS.PRICE AS TOTAL,  ");
			buf.append("  CART.CREATED                  ");
			buf.append(" FROM                           ");
			buf.append("  CART                          ");
			buf.append(" INNER JOIN                     ");
			buf.append("  ITEMS ON                      ");
			buf.append("   ITEM_ID=ITEMS.ID             ");
			buf.append(" WHERE                          ");
			buf.append("  USER_ID = ?                   ");

			ps = con.prepareStatement(buf.toString());


			ps.setInt( 1, inputId     );

			rs = ps.executeQuery();


			while (rs.next()) {
				CartDto dto = new CartDto();
				dto.setId(             rs.getInt("CART.ID")         );
				dto.setUser_id(        rs.getInt("USER_ID")         );
				dto.setItem_id(        rs.getInt("ITEM_ID")         );
				dto.setNumber(         rs.getInt("NUMBER")          );
				dto.setItems_picture(  rs.getString("ITEMS.PICTURE"));
				dto.setItems_name(     rs.getString("ITEMS.NAME")   );
				dto.setItems_price(    rs.getInt("ITEMS.PRICE")     );
				dto.setTotalPrice(     rs.getInt("TOTAL")           );
				dto.setCreated(        rs.getTimestamp("CART.CREATED")  );
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

	public int doSelectCartCheck(CartDto dto) {

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
			buf.append(" SELECT             ");
			buf.append("  COUNT(ITEM_ID) as COUNT   ");
			buf.append(" FROM               ");
			buf.append("  CART              ");
			buf.append(" WHERE              ");
			buf.append("  USER_ID = ?  AND  ");
			buf.append("  ITEM_ID = ?       ");

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

	public boolean doInsertHistory(List<CartDto> dtoList) {

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
			buf.append("INSERT INTO HISTORY  (      ");
			buf.append("  USER_ID,                  ");
			buf.append("  NAME,                     ");
			buf.append("  PRICE,                    ");
			buf.append("  NUM,                      ");
			buf.append("  ITEM_ID,                  ");
			buf.append("  CREATED             )     ");
			buf.append("  VALUES                    ");
			for (int i=0; i<dtoList.size(); i++) {

				buf.append(" (                      ");
				buf.append("  ?,                    ");
				buf.append("  ?,                    ");
				buf.append("  ?,                    ");
				buf.append("  ?,                    ");
				buf.append("  ?,                    ");
				buf.append("  ?                     ");
				if (i+1 != dtoList.size()) {
					buf.append("),                  ");
				} else {
					buf.append(")                   ");
				}
			}

			ps = con.prepareStatement(buf.toString());

			for (int i=0; i<dtoList.size(); i++) {
				CartDto dto = dtoList.get(i);

				ps.setInt(       i*6+1, dto.getUser_id()      );
				ps.setString(    i*6+2, dto.getItems_name()   );
				ps.setInt(       i*6+3, dto.getItems_price()  );
				ps.setInt(       i*6+4, dto.getNumber()       );
				ps.setInt(       i*6+5, dto.getItem_id()      );
				ps.setTimestamp( i*6+6, dto.getCreated()      );

			}

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
			buf.append("   CART           ");
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

	public boolean doDeleteAll(int inputId) {

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
			buf.append("   CART           ");
			buf.append("  WHERE           ");
			buf.append("   USER_ID=?      ");


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
