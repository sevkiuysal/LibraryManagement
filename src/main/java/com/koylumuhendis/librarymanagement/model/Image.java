package com.koylumuhendis.librarymanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
@Entity
@Table(name = "image")

public class Image extends  BaseEntity{
    private String imageURL;

	public Image() {
	}

	public String getImageURL() {
		return imageURL;
	}

}
