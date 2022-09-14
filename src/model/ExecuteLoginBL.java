package model;

public class ExecuteLoginBL {

	public UserInfoDto executeSelectUserInfo(String mail, String passWord) {

		UserInfoDao dao = new UserInfoDao();
		UserInfoDto dto = dao.doSelect(mail, passWord);

		return dto;
	}

}
