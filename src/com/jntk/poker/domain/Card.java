package com.jntk.poker.domain;

public class Card{
	private int rank;
	private String suite;
	private String name;
	
	public Card() {
		super();
	}
	
	public Card(int rank, String suite, String name) {
		super();
		this.rank = rank;
		this.suite = suite;
		this.name = name;
	}
	
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getSuite() {
		return suite;
	}
	public void setSuite(String suite) {
		this.suite = suite;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
