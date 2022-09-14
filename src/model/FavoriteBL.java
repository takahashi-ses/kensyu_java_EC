package model;

import java.util.List;

public class FavoriteBL {

	public boolean excuteInsertFavorite(FavoriteDto dto) {

		boolean succesInsert = false ;

		FavoriteDao dao = new FavoriteDao();
		succesInsert = dao.doInsert(dto);

		return succesInsert;
	}

	public List<FavoriteDto> excuteSelectFavorite(int id) {

		FavoriteDao dao = new FavoriteDao();
		List<FavoriteDto> dtoList = dao.doSelect(id);

		return dtoList;
	}

	public int excuteSelectFavoriteCheck(FavoriteDto dto) {

		FavoriteDao dao = new FavoriteDao();
		int checkCount = dao.doSelectFavoriteCheck(dto);


		return checkCount;
	}

}
