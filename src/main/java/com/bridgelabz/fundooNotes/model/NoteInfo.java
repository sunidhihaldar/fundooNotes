package com.bridgelabz.fundooNotes.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * This model class acts as a note entity and the parameters gets stored in the
 * database
 * 
 * @author Sunidhi Haldar
 * @created 2020-01-24
 * @version 1.8
 */

@Entity
@Table(name = "note")
public class NoteInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "note_id")
	private long noteId;

	private String title;

	private String description;

	@Column(columnDefinition = "boolean default false")
	private boolean isPinned;

	@Column(columnDefinition = "boolean default false")
	private boolean isArchived;

	@Column(columnDefinition = "boolean default false")
	private boolean isTrashed;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private String colour;

	private LocalDateTime reminder;

	// @ManyToMany(mappedBy = "noteList")
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "note_label", joinColumns = { @JoinColumn(name = "note_id") }, inverseJoinColumns = {
			@JoinColumn(name = "label_id") })
	private List<LabelInfo> labelList;

	public long getNoteId() {
		return noteId;
	}

	public void setNoteId(long noteId) {
		this.noteId = noteId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isPinned() {
		return isPinned;
	}

	public void setPinned(boolean isPinned) {
		this.isPinned = isPinned;
	}

	public boolean isArchived() {
		return isArchived;
	}

	public void setArchived(boolean isArchived) {
		this.isArchived = isArchived;
	}

	public boolean isTrashed() {
		return isTrashed;
	}

	public void setTrashed(boolean isTrashed) {
		this.isTrashed = isTrashed;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public LocalDateTime getReminder() {
		return reminder;
	}

	public void setReminder(LocalDateTime reminder) {
		this.reminder = reminder;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "NoteInfo [noteId=" + noteId + ", title=" + title + ", description=" + description + ", isPinned="
				+ isPinned + ", isArchived=" + isArchived + ", isTrashed=" + isTrashed + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", colour=" + colour + ", reminder=" + reminder + "]";
	}

	public List<LabelInfo> getLabelList() {
		return labelList;
	}

	public void setLabelList(List<LabelInfo> labelList) {
		this.labelList = labelList;
	}

}
