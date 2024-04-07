package com.example.demo.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reports")
public class Reports {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "item")
	private String item;
	
	@Column(name = "purchased_by")
	private String purchasedBy;
	
	@Column(name = "purchased_from")
	private String purchasedFrom;
	
	@Column(name = "purchased_date")
	private Date purchasedDate;
	
	@Column(name = "amount")
	private String amount;
	
	@Column(name = "paid_by")
	private String paidBy;

	@Column(name = "status")
	private String status;
	
	public Reports() {
		super();
	}

	public Reports(int id, String item, String purchasedBy, String purchasedFrom, Date purchasedDate, String amount,
			String paidBy, String status) {
		super();
		this.id = id;
		this.item = item;
		this.purchasedBy = purchasedBy;
		this.purchasedFrom = purchasedFrom;
		this.purchasedDate = purchasedDate;
		this.amount = amount;
		this.paidBy = paidBy;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getPurchasedBy() {
		return purchasedBy;
	}

	public void setPurchasedBy(String purchasedBy) {
		this.purchasedBy = purchasedBy;
	}

	public String getPurchasedFrom() {
		return purchasedFrom;
	}

	public void setPurchasedFrom(String purchasedFrom) {
		this.purchasedFrom = purchasedFrom;
	}

	public Date getPurchasedDate() {
		return purchasedDate;
	}

	public void setPurchasedDate(Date purchasedDate) {
		this.purchasedDate = purchasedDate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPaidBy() {
		return paidBy;
	}

	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Reports [id=" + id + ", item=" + item + ", purchasedBy=" + purchasedBy + ", purchasedFrom="
				+ purchasedFrom + ", purchasedDate=" + purchasedDate + ", amount=" + amount + ", paidBy=" + paidBy
				+ ", status=" + status + "]";
	}

	
	
	
}
