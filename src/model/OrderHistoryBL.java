package model;

import java.util.List;

public class OrderHistoryBL {

	public List<OrderHistoryDto> excuteSelectOrderHistory(int userId) {

		OrderHistoryDao dao = new OrderHistoryDao();
		List<OrderHistoryDto> dtoList = dao.doSelectOrderHistory(userId);

		return dtoList;
	}

	public OrderHistoryDto doSelectOrderHistoryItemName(int historyId) {

		OrderHistoryDao dao = new OrderHistoryDao();
		OrderHistoryDto dto = dao.doSelectOrderHistoryItemName(historyId);

		return dto;
	}
}
