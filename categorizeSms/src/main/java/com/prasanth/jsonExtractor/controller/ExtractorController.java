package com.prasanth.jsonExtractor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prasanth.jsonExtractor.api.ExtractorService;
import com.prasanth.jsonExtractor.model.SmsObject;
import com.prasanth.jsonExtractor.model.ConsolidatedSmsData;

@RestController
public class ExtractorController {
	
	@Autowired
	private ExtractorService extService;
	
	@PostMapping(value = "/mapJson")
	public ConsolidatedSmsData mapJson(@RequestBody SmsObject sms) {
		
		return extService.extractJson(sms);
	}
}
