package model;

public class ExcuteUpdateRegistrationBL {

	public boolean excuteUpdateRegistration(UserInfoDto dto) {

		boolean succesUpdate = false ;

		UserInfoDao dao = new UserInfoDao();
		succesUpdate = dao.doUpdate(dto);

		return succesUpdate;
	}

}
