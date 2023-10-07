package com.koylumuhendis.librarymanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "category")
public class Category extends BaseEntity {

    private  String name;
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Book> bookList;
	
    public Category() {
	}
	public String getName() {
		return name;
	}
	public List<Book> getBookList() {
		return bookList;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
    
}
