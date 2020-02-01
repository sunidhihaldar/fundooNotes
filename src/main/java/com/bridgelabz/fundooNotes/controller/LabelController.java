package com.bridgelabz.fundooNotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundooNotes.dto.LabelDto;
import com.bridgelabz.fundooNotes.response.Response;
import com.bridgelabz.fundooNotes.service.ILabelService;

@RestController
@RequestMapping("label")
public class LabelController {

	@Autowired
	private ILabelService labelService;

	@PostMapping("create")
	public ResponseEntity<Response> createLabel(@RequestBody LabelDto labelDto, @RequestHeader("token") String token) {
		boolean result = labelService.createLabel(labelDto, token);
		return (result) ? ResponseEntity.status(HttpStatus.OK).body(new Response("Label created", 200))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Error...label not created", 404));
	}
	
	@PostMapping("createAndMap/{noteId}")
	public ResponseEntity<Response> createAndMapLabel(@RequestBody LabelDto labelDto, @RequestHeader("token") String token, @PathVariable("noteId") long noteId) {
		boolean result = labelService.createAndMapLabel(labelDto, token, noteId);
		return (result) ? ResponseEntity.status(HttpStatus.OK).body(new Response("Label mapped", 200))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Error...", 404));
	}
}