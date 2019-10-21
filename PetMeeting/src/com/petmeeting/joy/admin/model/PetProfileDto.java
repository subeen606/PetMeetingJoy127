package com.petmeeting.joy.admin.model;

import java.io.Serializable;

public class PetProfileDto implements Serializable {
	private String email;
	private String petprofile_img;
	private String petname;
	private String petage;
	private String pettype;
	private String petdetail;
	private String petweight;
	private String petsex;
	private int pettnr;
	private String petintro;
	
	public PetProfileDto() {
	}

	public PetProfileDto(String email, String petprofile_img, String petname, String petage, String pettype,
			String petdetail, String petweight, String petsex, int pettnr, String petintro) {
		super();
		this.email = email;
		this.petprofile_img = petprofile_img;
		this.petname = petname;
		this.petage = petage;
		this.pettype = pettype;
		this.petdetail = petdetail;
		this.petweight = petweight;
		this.petsex = petsex;
		this.pettnr = pettnr;
		this.petintro = petintro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPetprofile_img() {
		return petprofile_img;
	}

	public void setPetprofile_img(String petprofile_img) {
		this.petprofile_img = petprofile_img;
	}

	public String getPetname() {
		return petname;
	}

	public void setPetname(String petname) {
		this.petname = petname;
	}

	public String getPetage() {
		return petage;
	}

	public void setPetage(String petage) {
		this.petage = petage;
	}

	public String getPettype() {
		return pettype;
	}

	public void setPettype(String pettype) {
		this.pettype = pettype;
	}

	public String getPetdetail() {
		return petdetail;
	}

	public void setPetdetail(String petdetail) {
		this.petdetail = petdetail;
	}

	public String getPetweight() {
		return petweight;
	}

	public void setPetweight(String petweight) {
		this.petweight = petweight;
	}

	public String getPetsex() {
		return petsex;
	}

	public void setPetsex(String petsex) {
		this.petsex = petsex;
	}

	public int getPettnr() {
		return pettnr;
	}

	public void setPettnr(int pettnr) {
		this.pettnr = pettnr;
	}

	public String getPetintro() {
		return petintro;
	}

	public void setPetintro(String petintro) {
		this.petintro = petintro;
	}

	@Override
	public String toString() {
		return "PetProfileDto [email=" + email + ", petprofile_img=" + petprofile_img + ", petname=" + petname
				+ ", petage=" + petage + ", pettype=" + pettype + ", petdetail=" + petdetail + ", petweight="
				+ petweight + ", petsex=" + petsex + ", pettnr=" + pettnr + ", petintro=" + petintro + "]";
	}
	
	
}
