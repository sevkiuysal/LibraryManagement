package com.koylumuhendis.librarymanagement.model;

import java.time.LocalDateTime;


import jakarta.persistence.*;

@Entity
@Table(name="book")

public class Book  extends BaseEntity{

    private  String title;
    private  String authorName;
    private String publisher;
    private int lastPageNumber;
    private int totalPage;
    @Enumerated(value = EnumType.STRING)
    private BookStatus bookStatus;
    @OneToOne
    private Image image;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
	
	public String getTitle() {
		return title;
	}
	public String getAuthorName() {
		return authorName;
	}
	public String getPublisher() {
		return publisher;
	}
	public int getLastPageNumber() {
		return lastPageNumber;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public BookStatus getBookStatus() {
		return bookStatus;
	}
	public Image getImage() {
		return image;
	}
	public Category getCategory() {
		return category;
	}
	public static class builder{
		private Long id;
	    private  String title;
	    private  String authorName;
	    private String publisher;
	    private int lastPageNumber;
	    private int totalPage;
	    private BookStatus bookStatus;
	    private Image image;
	    private Category category;
	    private LocalDateTime createdTime;
	    private LocalDateTime updatedTime;
	    
		
	    public builder id(Long id) {
			this.id = id;
			return this;
		}


		public builder title(String title) {
			this.title = title;
			return this;
		}


		public builder authorName(String authorName) {
			this.authorName = authorName;
			return this;
		}


		public builder publisher(String publisher) {
			this.publisher = publisher;
			return this;
		}


		public builder lastPageNumber(int lastPageNumber) {
			this.lastPageNumber = lastPageNumber;
			return this;
		}


		public builder totalPage(int totalPage) {
			this.totalPage = totalPage;
			return this;
		}


		public builder bookStatus(BookStatus bookStatus) {
			this.bookStatus = bookStatus;
			return this;
		}


		public builder image(Image image) {
			this.image = image;
			return this;
		}


		public builder category(Category category) {
			this.category = category;
			return this;
		}


		public builder createdTime(LocalDateTime createdTime) {
			this.createdTime = createdTime;
			return this;
		}


		public builder updatedTime(LocalDateTime updatedTime) {
			this.updatedTime = updatedTime;
			return this;
		}


		public Book build() {
	    	return new Book(this);
	    }
	}
    
	public Book(builder builder) {
		this.id=builder.id;
		this.title = builder.title;
		this.authorName = builder.authorName;
		this.publisher = builder.publisher;
		this.lastPageNumber = builder.lastPageNumber;
		this.totalPage = builder.totalPage;
		this.bookStatus = builder.bookStatus;
		this.image = builder.image;
		this.category = builder.category;
		this.createdTime=builder.createdTime;
		this.updatedTime=builder.updatedTime;
	}
}
