package com.bridgelabz.fundooNotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundooNotes.dto.NoteDto;
import com.bridgelabz.fundooNotes.dto.NoteUpdation;
import com.bridgelabz.fundooNotes.model.NoteInfo;
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

	@PutMapping("updateNote")
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
				: ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
						.body(new Response("Error, check your noteId", 208));
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

	@GetMapping("getAllNotes")
	public ResponseEntity<Response> getAllNotes(@RequestHeader("token") String token) {
		List<NoteInfo> list = noteService.getAllNotes(token);
		if (!list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(new Response("All notes are ", 200, list));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Empty list ", 404));
	}

	@GetMapping("getAllNotes/pinned")
	public ResponseEntity<Response> getAllPinnedNotes(@RequestHeader("token") String token) {
		List<NoteInfo> list = noteService.getAllPinnedNotes(token);

		if (!list.isEmpty()) {

			return ResponseEntity.status(HttpStatus.OK).body(new Response("All pinned notes are", 200, list));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Empty list", 404));
	}

	@GetMapping("getAllNotes/trashed")
	public ResponseEntity<Response> getAllTrashedNotes(@RequestHeader("token") String token) {
		List<NoteInfo> list = noteService.getAllTrashedNotes(token);
		if (!list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(new Response("All trashed notes are", 200, list));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Empty list", 404));
	}
	
	@GetMapping("getAllNotes/archived")
	public ResponseEntity<Response> getAllArchivedNotes(@RequestHeader("token") String token) {
		List<NoteInfo> list = noteService.getAllArchivedNotes(token);
		if(!list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(new Response("All archived notes are ", 200, list));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Empty list", 404));
	}
	
	@PutMapping("updateColour/{noteId}")
	public ResponseEntity<Response> updateColour(@PathVariable long noteId, @RequestHeader("token") String token, String colour) {
		boolean result = noteService.updateColour(noteId, token, colour);
		return (result) ? ResponseEntity.status(HttpStatus.OK).body(new Response("Colour updated", 200))
				: ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new Response("Colour not updated", 304));
	}
}