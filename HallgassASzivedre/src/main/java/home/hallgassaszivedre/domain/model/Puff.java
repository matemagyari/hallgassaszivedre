package home.hallgassaszivedre.domain.model;

import home.hallgassaszivedre.domain.model.exception.PuffException;

public class Puff {

	private Long id;
	private String phrase;
	private String content;
	private String date;
	private int weight;
	//TODO test
	PuffException exception;
	home.hallgassaszivedre.infrastructure.http.UIAction action;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@Override
	public String toString() {
		return "Puff [id=" + id + ", phrase=" + phrase + ", content=" + content
				+ ", date=" + date + ", weight=" + weight + "]";
	}

}
