package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.model.CollectionStructure;
import com.main.model.outputStructure;
import com.main.service.DocumentServices;

@RestController
public class ApiController {

	@Autowired
	private DocumentServices docService;
	
		
	@RequestMapping("/add")
	public String AddFilesToDatabase()
	{
		docService.saveFile();
		return "added Successfully";
	}
	
	@RequestMapping("/get")
	public List<CollectionStructure> getAllFiles()
	{
		return docService.getAll();
	}
	
	@RequestMapping("/search/{id}")
	public List<outputStructure> getWordOccurences(@PathVariable String id)
	{
		return docService.returnWordOccurences(id);
	}
	
}
