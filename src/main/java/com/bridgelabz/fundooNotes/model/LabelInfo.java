package com.bridgelabz.fundooNotes.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "label")
public class LabelInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "label_id")
	private long labelId;

	private String labelName;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<NoteInfo> note;

	// @JoinColumn(name = "")
	private UserEntity userId;

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

	public List<NoteInfo> getNote() {
		return note;
	}

	public void setNote(List<NoteInfo> note) {
		this.note = note;
	}

	public UserEntity getUserId() {
		return userId;
	}

	public void setUserId(UserEntity userId) {
		this.userId = userId;
	}
}
