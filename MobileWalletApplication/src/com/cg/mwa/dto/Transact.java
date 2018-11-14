package com.cg.mwa.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transact {

	@Id
	@Column(name="ID")
	private Integer id;
	@Column(name="transaction")
	private String tr;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTr() {
		return tr;
	}
	public void setTr(String tr) {
		this.tr = tr;
	}
	public Transact(Integer id, String tr) {
		super();
		this.id = id;
		this.tr = tr;
	}
	public Transact() {
		
	}
	
	
}
