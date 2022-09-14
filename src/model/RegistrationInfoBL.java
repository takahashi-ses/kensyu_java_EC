package model;

public class RegistrationInfoBL {

	public UserInfoDto excuteSelectRegistration(int userId) {

		UserInfoDao dao = new UserInfoDao();
		UserInfoDto dto = dao.doSelectUserInfo(userId);

		return dto;
	}

}
