package com.koylumuhendis.librarymanagement.dto;

import com.koylumuhendis.librarymanagement.model.BookStatus;

import java.io.File;

public class BookListItemResponse {
	private long id;
	private String title;
	private String authorName;
	private String publisher;
	private BookStatus bookStatus;
	private int lastPageNumber;
	private int totalPage;
	private File image;
	private String categoryName;

	public long getId() {
		return id;
	}

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

	public int getLastPageNumber() {
		return lastPageNumber;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public File getImage() {
		return image;
	}

	public String getCategoryName() {
		return categoryName;
	}
	public void setId(Long id){
		this.id=id;
	}

	public static class builder {

		private long id;
		private String title;
		private String authorName;
		private String publisher;
		private BookStatus bookStatus;
		private int lastPageNumber;
		private int totalPage;
		private File image;
		private String categoryName;

		public builder id(long id) {
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

		public builder bookStatus(BookStatus bookStatus) {
			this.bookStatus = bookStatus;
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

		public builder image(File image) {
			this.image = image;
			return this;
		}

		public builder categoryName(String categoryName) {
			this.categoryName = categoryName;
			return this;
		}

		public BookListItemResponse build() {
			return new BookListItemResponse(this);
		}
	}

	public BookListItemResponse(builder build) {
		this.id = id;
		this.title = title;
		this.authorName = authorName;
		this.publisher = publisher;
		this.bookStatus = bookStatus;
		this.lastPageNumber = lastPageNumber;
		this.totalPage = totalPage;
		this.image = image;
		this.categoryName = categoryName;
	}
}
