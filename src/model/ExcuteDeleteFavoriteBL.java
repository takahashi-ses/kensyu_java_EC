package model;

public class ExcuteDeleteFavoriteBL {

	public boolean excuteDeleteFavorite(int id) {

		boolean succesInsert = false ;

		FavoriteDao dao = new FavoriteDao();
		succesInsert = dao.doDelete(id);

		return succesInsert;
	}

}
