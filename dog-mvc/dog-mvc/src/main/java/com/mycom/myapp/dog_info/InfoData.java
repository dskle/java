package com.mycom.myapp.dog_info;

import java.sql.Timestamp;
import javax.validation.constraints.*;

public class InfoData {
	private String dog_no;
	private String species;
	private String birth;
	private String name;
	private String tel;
	private Timestamp created_date;

	public InfoData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getDog_no() {
		return dog_no;
	}

	public void setDog_no(String dog_no) {
		this.dog_no = dog_no;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Timestamp getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Timestamp created_date) {
		this.created_date = created_date;
	}

}
