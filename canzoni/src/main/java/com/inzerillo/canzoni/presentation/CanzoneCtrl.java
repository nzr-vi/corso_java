package com.inzerillo.canzoni.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/canzoni")
public class CanzoneCtrl {

	@GetMapping
	public String getAll() {
		return "pippo";
	}
}
