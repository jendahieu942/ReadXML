package xml.read.obj;

public class Detail {
	private String TypeWord;
	private String CateWord;
	private String SubCateWord;
	private String VerbPattern;
	private String CateMean;
	private String Definition;
	private String Example;
	
	//Setter
	public void setTypeWord(String typeWord) {
		TypeWord = typeWord;
	}
	public void setCateWord(String cateWord) {
		this.CateWord = cateWord;
	}
	public void setSubCateWord(String subCateWord) {
		SubCateWord = subCateWord;
	}
	public void setVerbPattern(String verbPattern) {
		VerbPattern = verbPattern;
	}
	public void setCateMean(String cateMean) {
		CateMean = cateMean;
	}
	public void setDefinition(String definition) {
		Definition = definition;
	}
	public void setExample(String example) {
		Example = example;
	}
	
	//Getter
	public String getTypeWord() {
		return TypeWord;
	}
	public String getCateWord() {
		return CateWord;
	}
	public String getSubCateWord() {
		return SubCateWord;
	}
	public String getVerbPattern() {
		return VerbPattern;
	}
	public String getCateMean() {
		return CateMean;
	}
	public String getDefinition() {
		return Definition;
	}
	public String getExample() {
		return Example;
	}
	@Override
	public String toString() {
		return "\tTypeWord: " + this.getTypeWord() + ", \n\tCateWord: " + this.getCateWord() + ", \n\tSubCateWord: " + this.getSubCateWord()
				+ ", \n\tVerbPattern: " + this.getVerbPattern() + ", \n\tCateMean: " + this.getCateMean() + ", \n\tDefinition: " + this.getDefinition()
				+ ", \n\tExample: " + this.getExample() ;
	}
}
