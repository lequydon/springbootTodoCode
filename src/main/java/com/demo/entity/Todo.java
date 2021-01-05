package com.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="todo")
public class Todo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name="deadLine",nullable = false)
    private Date dealine;
    @Column(name="content",nullable=false)
    private String content;
    @Column(name="deleted",nullable=false)
    private int deleted;
    public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	@ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	@ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;
	public Todo() {
		
	}
	public Todo(Date dealine, String content, int deleted, User user, Status status) {
		this.dealine = dealine;
		this.content = content;
		this.deleted = deleted;
		this.user = user;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public Date getDealine() {
		return dealine;
	}
	public void setDealine(Date dealine) {
		this.dealine = dealine;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
    
}
