package model;

import java.util.List;

public class TopPageBL {

	public List<TopItemsDto> excuteSelectTopItem(String search) {

		TopItemsDao dao = new TopItemsDao();
		List<TopItemsDto> dtoList = dao.doSelectTopItems(search);

		return dtoList;
	}

	public boolean excuteInsertItem(TopItemsDto dto) {

		boolean succesInsert = false;

		TopItemsDao dao = new TopItemsDao();
		succesInsert = dao.doInsertTopItems(dto);

		return succesInsert;


	}

}
