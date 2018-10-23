package com.main.model;

import java.util.HashMap;

public class outputStructure {
	
	public String fileName;
	public HashMap<Integer, Integer> finalOputputData;
	public int totalCount;
	
	public outputStructure() {
		// TODO Auto-generated constructor stub
	}

	public outputStructure(String fileName, HashMap<Integer, Integer> finalOputputData, int totalCount) {
		super();
		this.fileName = fileName;
		this.finalOputputData = finalOputputData;
		this.totalCount = totalCount;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public HashMap<Integer, Integer> getFinalOputputData() {
		return finalOputputData;
	}

	public void setFinalOputputData(HashMap<Integer, Integer> finalOputputData) {
		this.finalOputputData = finalOputputData;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		return "outputStructure [fileName=" + fileName + ", finalOputputData=" + finalOputputData + ", totalCount="
				+ totalCount + "]";
	}
	
	

}
