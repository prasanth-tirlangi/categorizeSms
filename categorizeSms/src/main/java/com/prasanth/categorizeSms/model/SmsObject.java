package com.prasanth.categorizeSms.model;

import java.util.List;

public class SmsObject {
	
	private List<SmsData> sms;

	public List<SmsData> getSms() {
		return sms;
	}

	public void setSms(final List<SmsData> sms) {
		this.sms = sms;
	}
}
