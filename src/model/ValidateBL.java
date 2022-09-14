package model;

public class ValidateBL {

	public ValidateDto excuteSelectEmailCheck(String email) {

		ValidateDao dao = new ValidateDao();
		ValidateDto dto = dao.doSelectEmaiCount(email);

		return dto;
	}

}
