package com.jpa.entity;

import java.sql.Blob;
import java.sql.Clob;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Person {
	
	private Integer id;
	private String username;
	private Date birthday;
	private Gendar sex = Gendar.MAN;
	
	private String introduct;
	
	private byte[] photo;
	
	private String imagePath;
	
	private Blob hah;
	
	private Clob hehe;
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	//自动映射成longBLob
	public Blob getHah() {
		return hah;
	}
	public void setHah(Blob hah) {
		this.hah = hah;
	}
	@Lob//映射成longText
	public String getIntroduct() {
		return introduct;
	}
	public void setIntroduct(String introduct) {
		this.introduct = introduct;
	}
	@Lob//映射成longBlob
	@Basic(fetch=FetchType.LAZY)//查询时不会把它加载出来
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	@Transient//不需要映射到数据库表的字段
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@Basic
	@Column(name="user_name", length=20)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	//设置时间类型
	@Temporal(TemporalType.TIMESTAMP)
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Enumerated(EnumType.STRING)
	@Column(length=5)
	public Gendar getSex() {
		return sex;
	}
	public void setSex(Gendar sex) {
		this.sex = sex;
	}
	public Clob getHehe() {
		return hehe;
	}
	public void setHehe(Clob hehe) {
		this.hehe = hehe;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", username=" + username + ", birthday=" + birthday + ", sex=" + sex
				+ ", introduct=" + introduct + ", photo=" + Arrays.toString(photo) + ", imagePath=" + imagePath
				+ ", hah=" + hah + ", hehe=" + hehe + "]";
	}
	
	
	
	

}
