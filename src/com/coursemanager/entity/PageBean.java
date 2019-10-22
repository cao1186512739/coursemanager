package com.coursemanager.entity;

import java.util.ArrayList;
import java.util.List;

public class PageBean<T> {
	
	private int currentPage;//当前�?
	private int currentCount;//当前页显示的行数
	private int totalCount;//总条�?
	private int totalPage;//总页�?
	private List<T> courseList = new ArrayList<T>();//每页显示的数�?
	private List<T> evaluationList = new ArrayList<T>();
	private List<T> taskList = new ArrayList<T>();
	private List<T> messageList = new ArrayList<T>();
	private List<T> teacherList = new ArrayList<T>();
	private List<T> studentList = new ArrayList<T>();
		
	public List<T> getTeacherList() {
		return teacherList;
	}
	public void setTeacherList(List<T> teacherList) {
		this.teacherList = teacherList;
	}
	public List<T> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<T> studentList) {
		this.studentList = studentList;
	}
	public List<T> getMessageList() {
		return messageList;
	}
	public void setMessageList(List<T> messageList) {
		this.messageList = messageList;
	}
	public List<T> getTaskList() {
		return taskList;
	}
	public void setTaskList(List<T> taskList) {
		this.taskList = taskList;
	}
	public List<T> getEvaluationList() {
		return evaluationList;
	}
	public void setEvaluationList(List<T> evaluationList) {
		this.evaluationList = evaluationList;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getCourseList() {
		return courseList;
	}
	public void setCourseList(List<T> courseList) {
		this.courseList = courseList;
	}	
}
