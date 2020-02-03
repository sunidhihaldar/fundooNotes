package com.bridgelabz.fundooNotes.model;

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

@Entity
@Table(name = "label")
public class LabelInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "label_id")
	private long labelId;

	private String labelName;

//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "note_label", joinColumns = { @JoinColumn(name = "label_id") }, inverseJoinColumns = {
//			@JoinColumn(name = "note_id") })
	@ManyToMany(mappedBy = "labelList")
	private List<NoteInfo> noteList;

	public long getLabelId() {
		return labelId;
	}

	public void setLabelId(long labelId) {
		this.labelId = labelId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public List<NoteInfo> getNoteList() {
		return noteList;
	}

	public void setNoteList(List<NoteInfo> noteList) {
		this.noteList = noteList;
	}
}