package com.prasanth.categorizeSms.api;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.websocket.Decoder.Text;

import org.springframework.stereotype.Service;

import com.prasanth.categorizeSms.model.ConsolidatedSmsData;
import com.prasanth.categorizeSms.model.SmsData;
import com.prasanth.categorizeSms.model.SmsObject;
import com.prasanth.categorizeSms.model.SummaryData;

@Service
public class CategorizeService {

	public ConsolidatedSmsData categorize(SmsObject smsObject) {

		ConsolidatedSmsData consolidatedSmsData = new ConsolidatedSmsData();
		DecimalFormat df = new DecimalFormat("#.00");
		Map<String, SummaryData> mapData = getMapOfCategoriesAndSms(smsObject, df);

		consolidatedSmsData.setSummary(mapData.values().stream().collect(Collectors.toList()));
		return consolidatedSmsData;
	}

	public String classifyData(String str) {

		if (str.toLowerCase().contains("bbps")) {
			return "Bill Payment";
		}

		else if (str.toLowerCase().contains("swiggy")) {
			return "Food";
		}
		return "Others";
	}

	public String getPrice(String str) {

		Pattern p = Pattern.compile("[0-9]+.[0-9]+");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return m.group(0);
		}
		return "0";
	}

	public Map<String, SummaryData> getMapOfCategoriesAndSms(SmsObject smsObject, DecimalFormat df) {

		HashMap<String, SummaryData> mapOfCatAndSms = new HashMap<String, SummaryData>();

		smsObject.getSms().stream().forEach(sms -> {
			String cat = classifyData(sms.getText());
			String price = getPrice(sms.getText());
			if (mapOfCatAndSms.containsKey(cat)) {
				SummaryData m = mapOfCatAndSms.get(cat);
				List<SmsData> smsList = new ArrayList<SmsData>(m.getSmsArray());
				float total = Float.parseFloat(m.getTotalAmount()) + Float.parseFloat(price);
				smsList.add(sms);
				m.setSmsArray(smsList);
				m.setTotalAmount(df.format(total));
				mapOfCatAndSms.put(cat, m);
			} else {
				SummaryData m = new SummaryData();
				m.setCategory(cat);
				m.setSmsArray(List.of(sms));
				m.setTotalAmount(df.format(Float.parseFloat(price)));
				mapOfCatAndSms.put(cat, m);
			}
		});

		return mapOfCatAndSms;
	}

}
