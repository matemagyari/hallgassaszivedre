package home.hallgassaszivedre.domain.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Puff implements Serializable {
	
	private int id;
	private String phrase;
	private String content;
	private String date;
	private int weight;
	
	
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPhrase() {
        return phrase;
    }
    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
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
	

	
	

}