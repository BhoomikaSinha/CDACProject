package com.svs.etracker.model;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
@Cacheable(false)
public class UserExpense {


	private int expenseId;
	private String expenseName;
	private double amount;
	private Date createdDate;	
	private String comments;
	private User user;
	private UserCategory userCategory;
	private byte[] image;


	public UserExpense() {}
	
	public UserExpense(int expenseId, String expenseName, double amount, Date createdDate, String comments) {
		super();
		this.expenseId = expenseId;
		this.expenseName = expenseName;
		this.amount = amount;
		this.createdDate = createdDate;
		this.comments = comments;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getExpenseId() {
		return expenseId;
	}


	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}

	@NotEmpty(message="Expense name cannot be empty")
	public String getExpenseName() {
		return expenseName;
	}


	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}

	@Min(1)
	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Past(message="Date cannot be in the future")
	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Size(min=3, max=200, message = "comment size should be between {min} and {max}")
	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@ManyToOne(targetEntity=UserCategory.class)
	@JoinColumn(name="category_id")
	public UserCategory getUserCategory() {
		return userCategory;
	}


	public void setUserCategory(UserCategory userCategory) {
		this.userCategory = userCategory;
	}

	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Lob
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
}
