package confg;



public class Library {

	/**----------------------------------------------------------------------*
	 *■■■replaceEscapeCharクラス■■■
	 *概要：文字列データのエスケープを行う
	 *----------------------------------------------------------------------**/
	public String replaceEscapeChar(String inputText) {

		String charAfterEscape = inputText;

		// 「&」を変換
		charAfterEscape = charAfterEscape.replace("&", "&amp;");
		// 「<」を変換
		charAfterEscape = charAfterEscape.replace("<", "&lt;");
		// 「>」を変換
		charAfterEscape = charAfterEscape.replace(">", "&gt;");
		// 「"」を変換
		charAfterEscape = charAfterEscape.replace("\"", "&quot;");
		// 「'」を変換
		charAfterEscape = charAfterEscape.replace("'", "&#039;");

		return charAfterEscape;
	}

}
