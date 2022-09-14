package model;

public class ExcuteNewRegistrationBL {


	public boolean excuteInsertRegistration(UserInfoDto dto) {

		boolean succesInsert = false ;

		UserInfoDao dao = new UserInfoDao();
		succesInsert = dao.doInsert(dto);

		return succesInsert;
	}
}
