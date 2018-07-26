package com.mycom.myapp.dog_score;

import javax.validation.constraints.*;

public class ScoreDataView {
	private String name;
	@NotNull
	private String dog_no;
	@NotNull
	@Max(value=100, message = "100이하로 입력하십시오")
	private int health;
	@NotNull
	@Max(value=100, message = "100이하로 입력하십시오")
	private int agility;
	@NotNull
	@Max(value=100, message = "100이하로 입력하십시오")
	private int nose_work;
	
	public ScoreDataView() {
		super();
	}
	
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getAgility() {
		return agility;
	}
	public void setAgility(int agility) {
		this.agility = agility;
	}
	public int getNose_work() {
		return nose_work;
	}
	public void setNose_work(int nose_work) {
		this.nose_work = nose_work;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDog_no() {
		return dog_no;
	}

	public void setDog_no(String dog_no) {
		this.dog_no = dog_no;
	}
	
	
}
