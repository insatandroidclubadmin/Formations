package org.iac.iac_inscription.models;

import java.io.Serializable;

public class Member implements Serializable {

	private static final long serialVersionUID = 1L;

	int id;
	String firstName;
	String lastName;
	String workShop;

	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param workShop
	 */
	public Member(int id, String firstName, String lastName, String workShop
			) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.workShop = workShop;
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param workShop
	 */
	public Member(String firstName, String lastName, String workShop) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.workShop = workShop;
	}

	/**
	 * 
	 */
	public Member() {
		super();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the workShop
	 */
	public String getWorkShop() {
		return workShop;
	}

	/**
	 * @param workShop
	 *            the workShop to set
	 */
	public void setWorkShop(String workShop) {
		this.workShop = workShop;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Member [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", workShop=" + workShop + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((workShop == null) ? 0 : workShop.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (workShop == null) {
			if (other.workShop != null)
				return false;
		} else if (!workShop.equals(other.workShop))
			return false;
		return true;
	}
	
	

}
