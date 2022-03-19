package com.prasanth.categorizeSms.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prasanth.categorizeSms.api.CategorizeService;
import com.prasanth.categorizeSms.model.ConsolidatedSmsData;
import com.prasanth.categorizeSms.model.SmsObject;

@RestController
public class CategorizeController {

	private final CategorizeService extService;

	public CategorizeController(CategorizeService extService) {
		this.extService = extService;
	}

	@PostMapping(value = "/api/v1/categorize-sms")
	public ConsolidatedSmsData categorize(@RequestBody SmsObject sms) {

		return extService.categorize(sms);
	}
}
