package com.jpa.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_group")
public class Group {
	
	private Integer id;
	
	private Integer level;
	
	private Set<Student> students = new HashSet<Student>();

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@OneToMany(fetch=FetchType.LAZY, mappedBy="group", cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", level=" + level + ", students=" + students + "]";
	}
	
	

}
