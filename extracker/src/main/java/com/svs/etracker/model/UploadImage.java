package com.svs.etracker.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name="FILE_UPLOAD")
public class UploadImage {

	@Id
	private int expenseId;
	@Lob
	private byte[] image;

	public UploadImage() {
		// TODO Auto-generated constructor stub
	}



	public int getExpenseId() {
		return expenseId;
	}



	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}



	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}



}
