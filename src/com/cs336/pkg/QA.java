package com.cs336.pkg;

public class QA {
	private String question;
	private String answer;
	
	public QA(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}
	
	public String getQuestion() {
		return this.question;
	}
	
	public String getAnswer() {
		return this.answer;
	}
}
