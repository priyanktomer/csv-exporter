package com.actually.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsvExporterApi {

	
	@GetMapping("welcome")
	public String welcome() {
		return "Welcoming you.";
	}
}
