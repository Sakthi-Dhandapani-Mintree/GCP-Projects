package com.resoursetechmapping.bean;

public class ResourceBean {
	private long id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String listOfTechWorkedOn;
	private String certifications;
	private String projects;
	private String applicationWorkLoadTypes;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getListOfTechWorkedOn() {
		return listOfTechWorkedOn;
	}
	public void setListOfTechWorkedOn(String listOfTechWorkedOn) {
		this.listOfTechWorkedOn = listOfTechWorkedOn;
	}
	public String getCertifications() {
		return certifications;
	}
	public void setCertifications(String certifications) {
		this.certifications = certifications;
	}
	public String getProjects() {
		return projects;
	}
	public void setProjects(String projects) {
		this.projects = projects;
	}
	public String getApplicationWorkLoadTypes() {
		return applicationWorkLoadTypes;
	}
	public void setApplicationWorkLoadTypes(String applicationWorkLoadTypes) {
		this.applicationWorkLoadTypes = applicationWorkLoadTypes;
	}
	@Override
	public String toString() {
		return "ResourceBean [id=" + id + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", listOfTechWorkedOn=" + listOfTechWorkedOn + ", certifications=" + certifications
				+ ", projects=" + projects + ", applicationWorkLoadTypes=" + applicationWorkLoadTypes + "]";
	}
	 
	  
}
