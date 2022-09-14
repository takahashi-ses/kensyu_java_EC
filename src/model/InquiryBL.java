package model;

public class InquiryBL {

	public boolean excuteInsertInquiry(InquiryDto dto) {

		boolean succesInsert = false ;

		InquiryDao dao = new InquiryDao();
		succesInsert = dao.doInsert(dto);

		return succesInsert;
	}
}
