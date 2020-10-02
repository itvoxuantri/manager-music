package com.tma.spring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "musics")
public class Music extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "nameAuthor")
	private String nameAuthor;

	@Column(name = "nameCategory")
	private String nameCategory;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameAuthor() {
		return nameAuthor;
	}

	public void setNameAuthor(String nameAuthor) {
		this.nameAuthor = nameAuthor;
	}

	public String getNameCategory() {
		return nameCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}

	public Music() {
		super();
	}

	@Override
	public String toString() {
		return "Music [Id: " + id + ", Name: " + name + ", Name Author: " + nameAuthor + ", Name Category: " + nameCategory
				+ "]";
	}

	public Music(String name, String nameAuthor, String nameCategory) {
		this.name = name;
		this.nameAuthor = nameAuthor;
		this.nameCategory = nameCategory;
	}

}
