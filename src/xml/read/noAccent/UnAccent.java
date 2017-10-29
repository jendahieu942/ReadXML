package xml.read.noAccent;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class UnAccent {
	public static String convert(String s) {
		String tmp = Normalizer.normalize(s,Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiaCriticalMarks}+");
		return pattern.matcher(tmp).replaceAll("").replaceAll("Đ", "D").replace("đ", "d");
	}
	public static void main(String[] args) {
		System.out.println(UnAccent.convert("biến thiên"));
	}
}
