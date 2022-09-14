package model;

public class ItemDetailBL {

	public ItemDetailDto excuteSelectItemDetail(int itemId) {

		ItemDetailDao dao = new ItemDetailDao();
		ItemDetailDto dto = dao.doSelectItemDetail(itemId);

		return dto;
	}

}
