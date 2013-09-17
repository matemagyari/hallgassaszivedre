package home.hallgassaszivedre.domain.model;

public class Puff {
	
	private int puffId;
	private String phrase;
	private String content;
	private String date;
	private int weight;
	
	public int getPuffId() {
		return puffId;
	}
	public void setPuffId(int puffId) {
		this.puffId = puffId;
	}
	public String getPuffContent() {
		return content;
	}
	public void setPuffContent(String puffContent) {
		this.content = puffContent;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
    public String getPhrase() {
        return phrase;
    }
    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
	
	

}
