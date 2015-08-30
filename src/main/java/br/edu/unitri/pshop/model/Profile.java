/*
 * 
 */
package br.edu.unitri.pshop.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the uj_profile database table.
 * 
 */
@Entity
@NamedQuery(name = "SelectProfiles", query = "SELECT p FROM Profile p")
@XmlRootElement
@Table(name = "st_profile", uniqueConstraints = @UniqueConstraint(columnNames = "description"))
public class Profile implements Serializable {

	private static final long serialVersionUID = 7932962927065807663L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The acronym. */
	private String acronym;

	/** The description. */
	private String description;

	/**
	 * Instantiates a new profile.
	 */
	public Profile() {
	}

	/**
	 * Gets the acronym.
	 *
	 * @return the acronym
	 */
	public String getAcronym() {
		return this.acronym;
	}

	/**
	 * Sets the acronym.
	 *
	 * @param acronym
	 *            the new acronym
	 */
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}