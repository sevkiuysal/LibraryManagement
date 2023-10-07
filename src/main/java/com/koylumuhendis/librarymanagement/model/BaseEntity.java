package com.koylumuhendis.librarymanagement.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass

public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    //@CreationTimestamp
    protected LocalDateTime createdTime=LocalDateTime.now();
    @UpdateTimestamp
    protected LocalDateTime updatedTime;
	public BaseEntity() {
	}
	public long getId() {
		return id;
	}
	public LocalDateTime getCreatedTime() {
		return createdTime;
	}
	public LocalDateTime getUpdatedTime() {
		return updatedTime;
	}
    

}
