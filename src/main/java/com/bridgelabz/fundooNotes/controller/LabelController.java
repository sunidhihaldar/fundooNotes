package com.bridgelabz.fundooNotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundooNotes.dto.LabelDto;
import com.bridgelabz.fundooNotes.model.LabelInfo;
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
		return (result) ? ResponseEntity.status(HttpStatus.CREATED).body(new Response("Label created", 201))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Error...label not created", 404));
	}

	@PostMapping("createAndMap/{noteId}")
	public ResponseEntity<Response> createAndMapLabel(@RequestBody LabelDto labelDto,
			@RequestHeader("token") String token, @PathVariable("noteId") long noteId) {
		boolean result = labelService.createAndMapLabel(labelDto, token, noteId);
		return (result) ? ResponseEntity.status(HttpStatus.CREATED).body(new Response("Label mapped", 201))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Error...", 404));
	}

	@DeleteMapping("removeLabel")
	public ResponseEntity<Response> removeLabel(@RequestParam("labelId") long labelId,
			@RequestParam("noteId") long noteId, @RequestHeader("token") String token) {
		boolean result = labelService.removeLabel(labelId, noteId, token);
		return (result) ? ResponseEntity.status(HttpStatus.OK).body(new Response("Label removed", 200))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Error....label not removed", 404));
	}

	@PostMapping("addLabel")
	public ResponseEntity<Response> addLabel(@RequestParam("labelId") long labelId, @RequestParam("noteId") long noteId,
			@RequestHeader("token") String token) {
		boolean result = labelService.addLabel(labelId, noteId, token);
		return (result) ? ResponseEntity.status(HttpStatus.OK).body(new Response("Label added", 200))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Error....label not added", 404));
	}

	@PutMapping("editLabel")
	public ResponseEntity<Response> editLabel(@RequestParam("labelId") long labelId,
			@RequestHeader("token") String token, @RequestBody LabelDto labelDto) {
		boolean result = labelService.editLabel(labelId, token, labelDto);
		return (result) ? ResponseEntity.status(HttpStatus.OK).body(new Response("Label edited", 200))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Error.....label not edited", 404));
	}

	@DeleteMapping("deleteLabel")
	public ResponseEntity<Response> deleteLabel(@RequestParam("labelId") long labelId,
			@RequestHeader("token") String token) {
		boolean result = labelService.deletePermanentlyLabel(labelId, token);
		return (result) ? ResponseEntity.status(HttpStatus.OK).body(new Response("Label deleted permanently", 200))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Error....label not deleted", 404));
	}
	
	@GetMapping("getAllLabels")
	public ResponseEntity<Response>  getAllLabels(@RequestHeader("token") String token) {
		List<LabelInfo> list = labelService.getLabels(token);
		if(!list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(new Response("All labels are", 200, list));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Empty list", 404));
	}
}