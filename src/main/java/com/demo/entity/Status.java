package com.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
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


@Entity
@Table(name="status")
public class Status implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    public List<Todo> getTodos() {
		return Todos;
	}
	public void setTodos(List<Todo> todos) {
		Todos = todos;
	}
	public Status() {
	}
	public Status(String nameStatus) {
		this.nameStatus = nameStatus;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "nameStatus", nullable = false)
    private String nameStatus;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
    private List<Todo> Todos=new ArrayList<Todo>();
	public int getId() {
		return id;
	}
	public String getNameStatus() {
		return nameStatus;
	}
	public void setNameStatus(String nameStatus) {
		this.nameStatus = nameStatus;
	}

}
