package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewsDao {

	String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

	String JDBC_URL    = "jdbc:mysql://localhost/kensyu_db?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";

	String USER_ID     = "root";

	String USER_PASS   = "root";

	public List<ReviewsDto> doSelectReviews(int inputId) {

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection        con = null ;
		PreparedStatement ps  = null ;
		ResultSet         rs  = null ;

		List<ReviewsDto> dtoList = new ArrayList<ReviewsDto>();

		try {

			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT             ");
			buf.append("  COMMENT,          ");
			buf.append("  STAR,             ");
			buf.append("  REVIEWS.CREATED,  ");
			buf.append("  NICKNAME          ");
			buf.append(" FROM               ");
			buf.append("   REVIEWS          ");
			buf.append(" INNER JOIN         ");
			buf.append("   USERS ON         ");
			buf.append("   USER_ID=USERS.ID ");
			buf.append(" WHERE              ");
			buf.append("   ITEM_ID = ?      ");

			ps = con.prepareStatement(buf.toString());


			ps.setInt( 1, inputId    );

			rs = ps.executeQuery();


			while (rs.next()) {
				ReviewsDto dto = new ReviewsDto();
				dto.setReviewsComment(    rs.getString("COMMENT")            );
				dto.setReviewsStar(       rs.getInt("STAR")                  );
				dto.setReviewsNickname(   rs.getString("NICKNAME")           );
				dto.setReviewsCreated(    rs.getTimestamp("REVIEWS.CREATED") );
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

	public ReviewsDto doSelectReviewsStar(int inputId) {

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection        con = null ;
		PreparedStatement ps  = null ;
		ResultSet         rs  = null ;

		ReviewsDto dto = new ReviewsDto();

		try {

			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT                       ");
			buf.append("  AVG(STAR) AS EVALUATION,    ");
			buf.append("  COUNT(STAR) AS COUNT        ");
			buf.append(" FROM                         ");
			buf.append("   REVIEWS                    ");
			buf.append(" WHERE                        ");
			buf.append("   ITEM_ID = ?                ");

			ps = con.prepareStatement(buf.toString());


			ps.setInt( 1, inputId    );

			rs = ps.executeQuery();


			if (rs.next()) {
				dto.setReviewsStarAvg(  rs.getDouble("EVALUATION")  );
				dto.setReviewsStarCount(  rs.getInt("COUNT")     );
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

	public ReviewsDto doSelectReviewItem(int itemId, int userId) {

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection        con = null ;
		PreparedStatement ps  = null ;
		ResultSet         rs  = null ;

		ReviewsDto dto = new ReviewsDto();

		try {

			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT             ");
			buf.append("  USER_ID,          ");
			buf.append("  ITEM_ID,          ");
			buf.append("  ITEMS.NAME        ");
			buf.append(" FROM               ");
			buf.append("   REVIEWS          ");
			buf.append(" INNER JOIN         ");
			buf.append("   ITEMS ON         ");
			buf.append("   ITEM_ID=ITEMS.ID ");
			buf.append(" WHERE              ");
			buf.append("   ITEM_ID = ? AND  ");
			buf.append("   USER_ID = ?      ");

			ps = con.prepareStatement(buf.toString());


			ps.setInt( 1, itemId    );
			ps.setInt( 2, userId    );

			rs = ps.executeQuery();


			if (rs.next()) {
				dto.setReviewsUser_id(     rs.getInt("USER_ID")          );
				dto.setReviewsItem_id(     rs.getInt("ITEM_ID")          );
				dto.setReviewsItem_name(   rs.getString("ITEMS.NAME")    );

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

	public List<ReviewsDto> doSelectReviewUser(int inputId) {

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection        con = null ;
		PreparedStatement ps  = null ;
		ResultSet         rs  = null ;

		List<ReviewsDto> dtoList = new ArrayList<ReviewsDto>();

		try {

			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT             ");
			buf.append("  REVIEWS.ID,       ");
			buf.append("  COMMENT,          ");
			buf.append("  STAR,             ");
			buf.append("  REVIEWS.CREATED,  ");
			buf.append("  ITEMS.NAME        ");
			buf.append(" FROM               ");
			buf.append("   REVIEWS          ");
			buf.append(" INNER JOIN         ");
			buf.append("   ITEMS ON         ");
			buf.append("   ITEM_ID=ITEMS.ID ");
			buf.append(" WHERE              ");
			buf.append("   USER_ID = ?      ");

			ps = con.prepareStatement(buf.toString());


			ps.setInt( 1, inputId    );

			rs = ps.executeQuery();


			while (rs.next()) {
				ReviewsDto dto = new ReviewsDto();
				dto.setReviewsId(         rs.getInt("ID")                    );
				dto.setReviewsComment(    rs.getString("COMMENT")            );
				dto.setReviewsStar(       rs.getInt("STAR")                  );
				dto.setReviewsItem_name(  rs.getString("ITEMS.NAME")         );
				dto.setReviewsCreated(    rs.getTimestamp("REVIEWS.CREATED") );
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

	public boolean doInsertReview(ReviewsDto dto) {

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
			buf.append("INSERT INTO REVIEWS  (  ");
			buf.append("  COMMENT,              ");
			buf.append("  STAR,                 ");
			buf.append("  USER_ID,              ");
			buf.append("  ITEM_ID,              ");
			buf.append("  CREATED               ");
			buf.append(") VALUES (              ");
			buf.append("  ?,                    ");
			buf.append("  ?,                    ");
			buf.append("  ?,                    ");
			buf.append("  ?,                    ");
			buf.append("  ?                     ");
			buf.append(")                       ");


			ps = con.prepareStatement(buf.toString());


			ps.setString(    1, dto.getReviewsComment()      );
			ps.setInt(       2, dto.getReviewsStar()         );
			ps.setInt(       3, dto.getReviewsUser_id()      );
			ps.setInt(       4, dto.getReviewsItem_id()      );
			ps.setTimestamp( 5, dto.getReviewsCreated()      );

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
