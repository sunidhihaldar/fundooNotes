package com.bridgelabz.fundooNotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundooNotes.dto.NoteDto;
import com.bridgelabz.fundooNotes.dto.NoteUpdation;
import com.bridgelabz.fundooNotes.response.Response;
import com.bridgelabz.fundooNotes.service.INoteService;

@RestController
@RequestMapping("note")
public class NoteController {

	@Autowired
	private INoteService noteService;

	@PostMapping("create")
	public ResponseEntity<Response> createNote(@RequestBody NoteDto note, @RequestHeader String token) {
		boolean result = noteService.createNote(note, token);
		return (result) ? ResponseEntity.status(HttpStatus.CREATED).body(new Response("note created", 200, note))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Error, check your noteId", 404, note));
	}

	@PutMapping("upadteNote")
	public ResponseEntity<Response> updateNote(@RequestBody NoteUpdation update, @RequestHeader("token") String token) {
		boolean result = noteService.updateNote(update, token);
		return (result) ? ResponseEntity.status(HttpStatus.FOUND).body(new Response("Note updated", 200))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Error, check your noteId", 400));
	}

	@PostMapping("delete/{noteId}")
	public ResponseEntity<Response> deleteNotePermanently(@PathVariable("noteId") long noteId,
			@RequestHeader("token") String token) {
		boolean result = noteService.deleteNote(noteId, token);
		return (result) ? ResponseEntity.status(HttpStatus.OK).body(new Response("Note deleted", 200))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Error, check your noteId", 404));
	}

	@PostMapping("archive/{noteId}")
	public ResponseEntity<Response> archiveNote(@PathVariable("noteId") long noteId, @RequestHeader String token) {
		boolean result = noteService.archiveNote(noteId, token);
		return (result) ? ResponseEntity.status(HttpStatus.OK).body(new Response("Note archived", 200))
				: ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new Response("Error, check your noteId", 208));
	}

	@PostMapping("pin/{noteId}")
	public ResponseEntity<Response> pinNote(@PathVariable("noteId") long noteId, @RequestHeader String token) {
		boolean result = noteService.pinNote(noteId, token);
		return (result) ? ResponseEntity.status(HttpStatus.OK).body(new Response("Note pinned", 200))
				: ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new Response("Error, check your noteId", 502));
	}

	@PostMapping("trash/{noteId}")
	public ResponseEntity<Response> trashNote(@PathVariable("noteId") long noteId, @RequestHeader String token) {
		boolean result = noteService.trashNote(noteId, token);
		return (result) ? ResponseEntity.status(HttpStatus.OK).body(new Response("Note trashed", 200))
				: ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new Response("Error, check your noteId", 502));
	}
}
