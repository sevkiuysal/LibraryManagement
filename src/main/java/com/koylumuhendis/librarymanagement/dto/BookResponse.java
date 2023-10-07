package com.koylumuhendis.librarymanagement.dto;

import com.koylumuhendis.librarymanagement.model.BookStatus;

import java.io.File;

public final class BookResponse {
	private final long id;
	private final String title;
	private final String authorName;
	private final String publisher;
	private final BookStatus bookStatus;
	private final int lastPageNumber;
	private final int totalPage;
	private final File image;
	private final String imageUrl;
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

	public String getImageUrl() {
		return imageUrl;
	}

	public long getCategoryId() {
		return categoryId;
	}

	private final long categoryId;

	public static class builder {

		private long id;
		private String title;
		private String authorName;
		private String publisher;
		private BookStatus bookStatus;
		private int lastPageNumber;
		private int totalPage;
		private File image;
		private String imageUrl;
		private long categoryId;

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

		public builder imageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
			return this;
		}

		public builder categoryId(long categoryId) {
			this.categoryId = categoryId;
			return this;
		}

		public BookResponse build() {
			return new BookResponse(this);
		}
	}

	public BookResponse(builder builder) {
		this.id = builder.id;
		this.title = builder.title;
		this.authorName = builder.authorName;
		this.publisher = builder.publisher;
		this.bookStatus = builder.bookStatus;
		this.lastPageNumber = builder.lastPageNumber;
		this.totalPage = builder.totalPage;
		this.image = builder.image;
		this.imageUrl = builder.imageUrl;
		this.categoryId = builder.categoryId;

	}

}
