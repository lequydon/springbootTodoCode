package com.demo.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class TodoDTO {
private int rowNum;
private String content;
private int statusId;
private int id;
private Date  deadline;
private String statusName;
public TodoDTO() {
}
public TodoDTO(int rowNum, String content, int statusId, int id, Date deadline, String statusName) {
	this.rowNum = rowNum;
	this.content = content;
	this.statusId = statusId;
	this.id = id;
	this.deadline = deadline;
	this.statusName = statusName;
}
public String getStatusName() {
	return statusName;
}
public void setStatusName(String statusName) {
	this.statusName = statusName;
}
public int getRowNum() {
	return rowNum;
}
public void setRowNum(int rowNum) {
	this.rowNum = rowNum;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public int getStatusId() {
	return statusId;
}
public void setStatusId(int statusId) {
	this.statusId = statusId;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Date getDeadline() {
	return deadline;
}
public void setDeadline(Date deadline) {
	this.deadline = deadline;
}

}
