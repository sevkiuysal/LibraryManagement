package com.koylumuhendis.librarymanagement.dto;

import com.koylumuhendis.librarymanagement.model.BaseEntity;
import com.koylumuhendis.librarymanagement.model.BookStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.File;

public final class  BookSaveRequest {
    @NotBlank
    private  String title;
    @NotBlank
    private  String authorName;
    @NotBlank
    private String publisher;
    @NotNull
    private BookStatus bookStatus;
    @NotNull
    private Integer lastPageNumber;
    @NotNull
    private Integer totalPage;

    private File image;
    @NotNull
    private Long categoryId;

	private Long id;
    
    public String getTitle() {
		return title;
	}
	public String getAuthorName() {
		return authorName;
	}
	public String getPublisher() {
		return publisher;
	}
	public BookStatus getBookStatus() {
		return bookStatus;
	}
	public Integer getLastPageNumber() {
		return lastPageNumber;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public File getImage() {
		return image;
	}
	public Long getCategoryId() {
		return categoryId;
	}


    
    public static class builder {
    	
		private String title;
		
		private String authorName;
		
		private String publisher;
		
		private BookStatus bookStatus;
		
		private Integer lastPageNumber;
		
		private Integer totalPage;

		private File image;
		
		private Long categoryId;

		
		public builder title(String title) {
			this.title=title;
			return this;
		}
		public builder authorName(String authorName) {
			this.authorName=authorName;
			return this;
		}
		public builder publisher(String publisher) {
			this.publisher=publisher;
			return this;
		}
		public builder bookStatus(BookStatus bookStatus) {
			this.bookStatus=bookStatus;
			return this;
		}
		public builder lastPageNumber(Integer lastPageNumber) {
			this.lastPageNumber=lastPageNumber;
			return this;
		}
		public builder totalPage(Integer totalPage) {
			this.totalPage=totalPage;
			return this;
		}
		public builder image(File image) {
			this.image=image;
			return this;
		}
		public builder categoryId(Long categoryId) {
			this.categoryId=categoryId;
			return this;
		}

		
		public BookSaveRequest build() {
			return new BookSaveRequest(this);
		}
		
    }
    public BookSaveRequest(builder builder) {
    	this.title=builder.title;
    	this.authorName=builder.authorName;
    	this.publisher=builder.publisher;
    	this.bookStatus=builder.bookStatus;
    	this.lastPageNumber=builder.lastPageNumber;
    	this.totalPage=builder.totalPage;
    	this.image=builder.image;
    	this.categoryId=builder.categoryId;
    }
	
    
    
}
