package com.mycom.myapp.dog_info;

import javax.validation.constraints.*;

public class InfoDataView {
	@NotNull
	@Size(min = 1, message = "강아지 종을 입력하십시오")
	private String species;
	@NotNull
	@Size(min = 1, max = 8, message = "생일은 8자리입니다")
	private String birth;
	@NotNull
	@Size(min = 1, message = "강아지 이름을 입력하십시오")
	private String name;
	@NotNull
	@Size(min = 1, message = "전화번호를 입력하십시오")
	private String tel;

	public InfoDataView() {
		super();
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

}
