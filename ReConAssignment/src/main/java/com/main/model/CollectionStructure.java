package com.main.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CollectionStructure {

	@Id
	public String id;
	public String documentName;
	public String documentData;
	
	public CollectionStructure() {
		// TODO Auto-generated constructor stub
	}

	public CollectionStructure(String documentName, String documentData) {
		
		this.documentName = documentName;
		this.documentData = documentData;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentData() {
		return documentData;
	}

	public void setDocumentData(String documentData) {
		this.documentData = documentData;
	}

	@Override
	public String toString() {
		return "CollectionStructure [id=" + id + ", documentName=" + documentName + ", documentData=" + documentData
				+ "]";
	}
	
	
	
}
