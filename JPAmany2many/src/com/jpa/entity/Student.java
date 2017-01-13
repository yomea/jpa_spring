package com.jpa.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="t_student")
@NamedQuery(query="select s from Student s where s.id=?1", name="find student by id")


public class Student {
	
	private Integer id;
	
	private String name;
	
	private Set<Teacher> teachers = new HashSet<>();

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@ManyToMany
	@JoinTable(inverseJoinColumns={@JoinColumn(name="student_id")},
	joinColumns={@JoinColumn(name="teacher_id")}
			)
	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}
	
	
	
	

}
