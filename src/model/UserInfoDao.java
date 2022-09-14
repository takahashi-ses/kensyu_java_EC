package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserInfoDao {


	String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

	String JDBC_URL    = "jdbc:mysql://localhost/kensyu_db?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";

	String USER_ID     = "root";

	String USER_PASS   = "root";


	public UserInfoDto doSelect(String inputMail, String inputPassWord) {

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection        con = null ;
		PreparedStatement ps  = null ;
		ResultSet         rs  = null ;

		UserInfoDto dto = new UserInfoDto();

		try {

			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT             ");
			buf.append("  ID,               ");
			buf.append("  NAME,             ");
			buf.append("  NAME_KANA,        ");
			buf.append("  NICKNAME,         ");
			buf.append("  SEX,              ");
			buf.append("  BIRTHDAY,         ");
			buf.append("  ZIPCODE,          ");
			buf.append("  ADDRESS,          ");
			buf.append("  TELL,             ");
			buf.append("  EMAIL,            ");
			buf.append("  PASS,             ");
			buf.append("  CREATED           ");
			buf.append(" FROM               ");
			buf.append("   USERS            ");
			buf.append(" WHERE              ");
			buf.append("   EMAIL    = ? AND ");
			buf.append("   PASS     = ?     ");

			ps = con.prepareStatement(buf.toString());


			ps.setString( 1, inputMail     );
			ps.setString( 2, inputPassWord );

			rs = ps.executeQuery();


			if (rs.next()) {

				dto.setId(         rs.getInt("ID")             );
				dto.setName(       rs.getString("NAME")        );
				dto.setName_kana(  rs.getString("NAME_KANA")   );
				dto.setNickname(   rs.getString("NICKNAME")    );
				dto.setSex(        rs.getInt("SEX")            );
				dto.setBirthday(   rs.getString("BIRTHDAY")    );
				dto.setZipcode(    rs.getString("ZIPCODE")     );
				dto.setAddress(    rs.getString("ADDRESS")     );
				dto.setTell(       rs.getString("TELL")        );
				dto.setEmail(      rs.getString("EMAIL")       );
				dto.setPass(       rs.getString("PASS")        );
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


	public UserInfoDto doSelectUserInfo(int inputId) {

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection        con = null ;
		PreparedStatement ps  = null ;
		ResultSet         rs  = null ;

		UserInfoDto dto = new UserInfoDto();

		try {

			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT             ");
			buf.append("  ID,               ");
			buf.append("  NAME,             ");
			buf.append("  NAME_KANA,        ");
			buf.append("  NICKNAME,         ");
			buf.append("  SEX,              ");
			buf.append("  BIRTHDAY,         ");
			buf.append("  ZIPCODE,          ");
			buf.append("  ADDRESS,          ");
			buf.append("  TELL,             ");
			buf.append("  EMAIL,            ");
			buf.append("  PASS              ");
			buf.append(" FROM               ");
			buf.append("   USERS            ");
			buf.append(" WHERE              ");
			buf.append("   ID = ?           ");

			ps = con.prepareStatement(buf.toString());


			ps.setInt( 1, inputId    );

			rs = ps.executeQuery();


			if (rs.next()) {

				dto.setId(         rs.getInt("ID")             );
				dto.setName(       rs.getString("NAME")        );
				dto.setName_kana(  rs.getString("NAME_KANA")   );
				dto.setNickname(   rs.getString("NICKNAME")    );
				dto.setSex(        rs.getInt("SEX")            );
				dto.setBirthday(   rs.getString("BIRTHDAY")    );
				dto.setZipcode(    rs.getString("ZIPCODE")     );
				dto.setAddress(    rs.getString("ADDRESS")     );
				dto.setTell(       rs.getString("TELL")        );
				dto.setEmail(      rs.getString("EMAIL")       );
				dto.setPass(      rs.getString("PASS")         );

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


	public boolean doInsert(UserInfoDto dto) {

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
			buf.append("INSERT INTO USERS  (  ");
			buf.append("  NAME,               ");
			buf.append("  NAME_KANA,          ");
			buf.append("  NICKNAME,           ");
			buf.append("  SEX,                ");
			buf.append("  BIRTHDAY,           ");
			buf.append("  ZIPCODE,            ");
			buf.append("  ADDRESS,            ");
			buf.append("  TELL,               ");
			buf.append("  EMAIL,              ");
			buf.append("  PASS,               ");
			buf.append("  CREATED             ");
			buf.append(") VALUES (            ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?                   ");
			buf.append(")                     ");


			ps = con.prepareStatement(buf.toString());


			ps.setString(    1, dto.getName()            );
			ps.setString(    2, dto.getName_kana()       );
			ps.setString(    3, dto.getNickname()        );
			ps.setInt(       4, dto.getSex()             );
			ps.setString(    5, dto.getBirthday()        );
			ps.setString(    6, dto.getZipcode()         );
			ps.setString(    7, dto.getAddress()         );
			ps.setString(    8, dto.getTell()            );
			ps.setString(    9, dto.getEmail()           );
			ps.setString(    10, dto.getPass()           );
			ps.setTimestamp( 11, dto.getCreated()        );

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

	public boolean doUpdate(UserInfoDto dto) {

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
			buf.append("UPDATE USERS          ");
			buf.append("  SET                 ");
			buf.append("  	NAME       = ?,   ");
			buf.append("  	NAME_KANA  = ?,   ");
			buf.append("  	NICKNAME   = ?,   ");
			buf.append("  	SEX        = ?,   ");
			buf.append("  	BIRTHDAY   = ?,   ");
			buf.append("  	ZIPCODE    = ?,   ");
			buf.append("  	ADDRESS    = ?,   ");
			buf.append("  	TELL       = ?,   ");
			buf.append("  	EMAIL      = ?,   ");
			buf.append("  	UPDATETIME = ?    ");
			buf.append("  WHERE ID = ?        ");



			ps = con.prepareStatement(buf.toString());


			ps.setString(    1, dto.getName()            );
			ps.setString(    2, dto.getName_kana()       );
			ps.setString(    3, dto.getNickname()        );
			ps.setInt(       4, dto.getSex()             );
			ps.setString(    5, dto.getBirthday()        );
			ps.setString(    6, dto.getZipcode()         );
			ps.setString(    7, dto.getAddress()         );
			ps.setString(    8, dto.getTell()            );
			ps.setString(    9, dto.getEmail()           );
			ps.setTimestamp( 10, dto.getUpdatetime()     );
			ps.setInt(       11, dto.getId()             );



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

	public boolean doUpdatePassword(UserInfoDto dto) {

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
			buf.append("UPDATE USERS          ");
			buf.append("  SET                 ");
			buf.append("  	PASS   = ?        ");
			buf.append("  WHERE               ");
			buf.append("    ID     = ?        ");



			ps = con.prepareStatement(buf.toString());


			ps.setString(    1, dto.getPass()       );
			ps.setInt(       2, dto.getId()         );


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
