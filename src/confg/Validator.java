package confg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.ValidateBL;
import model.ValidateDto;

public class Validator {


	public static boolean validateNotNull( String pr) {

		boolean validateResult = true ;

		if( pr == null || pr.equals("") ) {
			validateResult = false ;
		}

		return validateResult ;
	}

	public static boolean validateNotNumber( String pr) {

		boolean validateResult = true ;

		Pattern p = Pattern.compile("^[0-9]+$");
		Matcher m = p.matcher(pr);

		if( !m.find() ) {
			validateResult = false ;
		}

		return validateResult ;
	}


	public static boolean checkPassword( String pass1, String pass2) {

		boolean validateResult = true ;

		if( !(pass1.equals(pass2))) {
			validateResult = false ;
		}

		return validateResult ;
	}

	public static boolean checkEmail( String email) {

		boolean validateResult = true ;

		ValidateBL logic = new ValidateBL();
		ValidateDto dto = new ValidateDto();
		dto = logic.excuteSelectEmailCheck(email);

		if (dto.getEmailCount() != 0) {
			validateResult = false ;
		}

		return validateResult ;
	}

	public static boolean checkEmailUpdate( String email) {

		boolean validateResult = true ;

		ValidateBL logic = new ValidateBL();
		ValidateDto dto = new ValidateDto();
		dto = logic.excuteSelectEmailCheck(email);

		if (dto.getEmailCount() > 1) {
			validateResult = false ;
		}

		return validateResult ;
	}

	public static boolean validateName( String pr) {

		boolean validateResult = true ;

		if ( pr.length() > 20) {
			validateResult = false ;
		}

		return validateResult ;
	}

	public static boolean validateName_kana( String pr) {

		boolean validateResult = true ;
		Pattern p = Pattern.compile("[ァ-ヴー\\s]+");
		Matcher m = p.matcher(pr);

		if( (pr.length() > 30) || (!m.find()) )  {
			validateResult = false ;
		}

		return validateResult ;
	}

	public static boolean validateNickname( String pr) {

		boolean validateResult = true ;

		if ( pr.length() > 20) {
			validateResult = false ;
		}

		return validateResult ;
	}

	public static String validateSex( String pr) {

		String sex = "";

		if ( !( (pr.equals("1") || pr.equals("2") || pr.equals("3") )) || ( !validateNotNull(pr))) {
			sex = "3" ;
		}

		return sex ;
	}

	public static boolean validateZipCode( String pr) {

		boolean validateResult = true ;

		Pattern p = Pattern.compile("\\d{3}-?\\d{4}");
		Matcher m = p.matcher(pr);

		if( (!m.find()) || (!validateNotNull(pr)) ) {
			validateResult = false ;
		}

		return validateResult ;
	}

	public static boolean validateAddress( String pr) {

		boolean validateResult = true ;

		if ( (pr.length() > 255) || (!validateNotNull(pr)) ) {
			validateResult = false ;
		}

		return validateResult ;
	}

	public static boolean validateTell( String pr) {

		boolean validateResult = true ;

		Pattern p = Pattern.compile("\\d{3}-?\\d{4}-?\\d{4}");
		Matcher m = p.matcher(pr);

		if( (!m.find()) || (!validateNotNull(pr)) ) {
			validateResult = false ;
		}

		return validateResult ;
	}

	public static boolean validateEmail( String pr) {

		boolean validateResult = true ;

		Pattern p = Pattern.compile("/^[a-zA-Z0-9_.+-]+@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\\.)+[a-zA-Z]{2,}$/");
		Matcher m = p.matcher(pr);

		if( (!m.find()) || (!validateNotNull(pr)) ) {
			validateResult = false ;
		}

		return validateResult ;
	}

	public static boolean validatePass( String pr) {

		boolean validateResult = true ;

		Pattern p = Pattern.compile("(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\\d)[a-zA-Z\\d]{8,}");
		Matcher m = p.matcher(pr);

		if( (!m.find()) || (!validateNotNull(pr)) ) {
			validateResult = false ;
		}

		return validateResult ;
	}

}
