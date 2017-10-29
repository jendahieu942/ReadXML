package xml.read.obj;

public class Word {
	private String HeadWord;
	private Detail Description = new Detail();
	
	public Word() {
		super();
	}
	
	public void setHeadWord(String headWord) {
		this.HeadWord = headWord;
	}
	
	public void setDescription(String cateWord, String typeWord, String subCateWord, String verbPattern, 
			String cateMean, String difinition, String example) {
		this.Description.setCateWord(cateWord);
		this.Description.setTypeWord(typeWord);
		this.Description.setSubCateWord(subCateWord);
		this.Description.setVerbPattern(verbPattern);
		this.Description.setCateMean(cateMean);
		this.Description.setDefinition(difinition);
		this.Description.setExample(example);
	}
	
	public String getHeadWord() {
		return this.HeadWord;
	}
	
	public Detail getDesciption() {
		Detail tmp = new Detail();
		tmp.setCateWord(this.Description.getCateWord());
		tmp.setSubCateWord(this.Description.getSubCateWord());
		tmp.setTypeWord(this.Description.getTypeWord());
		tmp.setVerbPattern(this.Description.getVerbPattern());
		tmp.setCateMean(this.Description.getCateMean());
		tmp.setDefinition(this.Description.getDefinition());
		tmp.setExample(this.Description.getExample());
		return tmp;
	}

	@Override
	public String toString() {
		return "Word: " + HeadWord + ", \nDescription: \n" + Description.toString();
	}
}
