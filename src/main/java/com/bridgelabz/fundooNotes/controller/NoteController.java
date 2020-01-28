package com.bridgelabz.fundooNotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundooNotes.dto.NoteDto;
import com.bridgelabz.fundooNotes.response.Response;
import com.bridgelabz.fundooNotes.service.INoteService;

@RestController
@RequestMapping
public class NoteController {

	@Autowired
	private INoteService noteService;

	@PostMapping("/note/create")
	public ResponseEntity<Response> createNote(@RequestBody NoteDto note, @RequestHeader String token) {
//		boolean result = noteService.create(note, token);
//		return (result) ? ResponseEntity.status(HttpStatus.CREATED).body(new Response("note created", 200, note))
//				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("note not created", 404, note));
		if(noteService.create(note, token)) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new Response("note created", 200, note));
		}
		return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new Response("Error", 400, note));
	}
}
