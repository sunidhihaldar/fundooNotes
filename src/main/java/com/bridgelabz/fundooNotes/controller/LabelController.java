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
import com.bridgelabz.fundooNotes.model.NoteInfo;
import com.bridgelabz.fundooNotes.response.Response;
import com.bridgelabz.fundooNotes.service.ILabelService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("label")
public class LabelController {

	@Autowired
	private ILabelService labelService;
	
	@PostMapping("create")
	@ApiOperation(value = "Api to create label", response = Response.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Label created "),
			@ApiResponse(code = 404, message = "The resource you were trying to fetch is not found") })
	public ResponseEntity<Response> createLabel(@RequestBody LabelDto labelDto, @RequestHeader("token") String token) {
		boolean result = labelService.createLabel(labelDto, token);
		return (result) ? ResponseEntity.status(HttpStatus.CREATED).body(new Response("Label created", 201))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Error...label not created", 404));
	}

	@PostMapping("createAndMap/{noteId}")
	@ApiOperation(value = "Api to create label and map it", response = Response.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Label mappped "),
			@ApiResponse(code = 404, message = "The resource you were trying to fetch is not found") })
	public ResponseEntity<Response> createAndMapLabel(@RequestBody LabelDto labelDto,
			@RequestHeader("token") String token, @PathVariable("noteId") long noteId) {
		boolean result = labelService.createAndMapLabel(labelDto, token, noteId);
		return (result) ? ResponseEntity.status(HttpStatus.CREATED).body(new Response("Label mapped", 201))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Error...", 404));
	}

	@DeleteMapping("removeLabel")
	@ApiOperation(value = "Api to remove label", response = Response.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Label removed "),
			@ApiResponse(code = 404, message = "The resource you were trying to fetch is not found") })
	public ResponseEntity<Response> removeLabel(@RequestParam("labelId") long labelId,
			@RequestParam("noteId") long noteId, @RequestHeader("token") String token) {
		boolean result = labelService.removeLabel(labelId, noteId, token);
		return (result) ? ResponseEntity.status(HttpStatus.OK).body(new Response("Label removed", 200))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Error....label not removed", 404));
	}

	@PostMapping("addLabel")
	@ApiOperation(value = "Api to add label", response = Response.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Label added "),
			@ApiResponse(code = 404, message = "The resource you were trying to fetch is not found") })
	public ResponseEntity<Response> addLabel(@RequestParam("labelId") long labelId, @RequestParam("noteId") long noteId,
			@RequestHeader("token") String token) {
		boolean result = labelService.addLabel(labelId, noteId, token);
		return (result) ? ResponseEntity.status(HttpStatus.OK).body(new Response("Label added", 200))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Error....label not added", 404));
	}

	@PutMapping("editLabel")
	@ApiOperation(value = "Api to edit label", response = Response.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Label edited "),
			@ApiResponse(code = 404, message = "The resource you were trying to fetch is not found") })
	public ResponseEntity<Response> editLabel(@RequestParam("labelId") long labelId,
			@RequestHeader("token") String token, @RequestBody LabelDto labelDto) {
		boolean result = labelService.editLabel(labelId, token, labelDto);
		return (result) ? ResponseEntity.status(HttpStatus.OK).body(new Response("Label edited", 200))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Error.....label not edited", 404));
	}

	@DeleteMapping("deleteLabel")
	@ApiOperation(value = "Api to delete label", response = Response.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Label deleted "),
			@ApiResponse(code = 404, message = "The resource you were trying to fetch is not found") })
	public ResponseEntity<Response> deleteLabel(@RequestParam("labelId") long labelId,
			@RequestHeader("token") String token) {
		boolean result = labelService.deletePermanentlyLabel(labelId, token);
		return (result) ? ResponseEntity.status(HttpStatus.OK).body(new Response("Label deleted permanently", 200))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Error....label not deleted", 404));
	}

	@GetMapping("getAllLabels")
	@ApiOperation(value = "Api to get all labels of a user", response = Response.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "All labels are "),
			@ApiResponse(code = 404, message = "The resource you were trying to fetch is not found") })
	public ResponseEntity<Response> getAllLabels(@RequestHeader("token") String token) {
		List<LabelInfo> list = labelService.getLabels(token);
		if (!list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(new Response("All labels are ", 200, list));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Empty list", 404));
	}

	@GetMapping("getAllNotes")
	@ApiOperation(value = "Api to get all notes of a label", response = Response.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "All notes of this label are "),
			@ApiResponse(code = 404, message = "The resource you were trying to fetch is not found") })
	public ResponseEntity<Response> getAllNotes(@RequestParam("labelId") long labelId,
			@RequestHeader("token") String token) {
		List<NoteInfo> list = labelService.getNotes(labelId, token);
		if (!list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(new Response("All notes for this label are ", 200, list));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Empty list", 404));
	}
}