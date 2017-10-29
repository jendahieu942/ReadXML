package xml.read.obj;

public class Test {
	public static void main(String[] args) {
		Word word1 = new Word();
		word1.setHeadWord("Ăn hiếp!");
		word1.setDescription("V", "Composite Word", "Vt", "Sub + V + Dob", "Active",
				"ỷ thế mạnh bắt người khác phải chịu lép mà làm theo ý muốn của mình",
				 "cậy khoẻ ăn hiếp yếu");
		System.out.println(word1.toString());
	}
}
