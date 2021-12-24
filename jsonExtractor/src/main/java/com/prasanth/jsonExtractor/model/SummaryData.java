package com.prasanth.jsonExtractor.model;

import java.util.List;

public class SummaryData {
	
	private String category;
	
	private String totalAmount;
	
	private List<SmsData> smsArray;

	public String getCategory() {
		return category;
	}

	public void setCategory(final String category) {
		this.category = category;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(final String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<SmsData> getSmsArray() {
		return smsArray;
	}

	public void setSmsArray(final List<SmsData> smsArray) {
		this.smsArray = smsArray;
	}

}
