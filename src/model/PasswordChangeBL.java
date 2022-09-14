package model;

public class PasswordChangeBL {

	public boolean excuteUpdatePassword(UserInfoDto dto) {

		boolean succesUpdate = false ;

		UserInfoDao dao = new UserInfoDao();
		succesUpdate = dao.doUpdatePassword(dto);

		return succesUpdate;
	}


}
