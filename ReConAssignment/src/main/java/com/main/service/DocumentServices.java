package com.main.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.model.CollectionStructure;
import com.main.model.outputStructure;
import com.main.repository.DatabaseRepository;

@Service
public class DocumentServices {

	@Autowired
	private DatabaseRepository dataRepo;
	private CollectionStructure coll;
	private outputStructure output;
	
	private List<outputStructure> returnOutputData = new ArrayList<>();
	
	public void readDocFile()
	{
		try {
			
			File file = new File("./src/main/resources");
			File[] files = file.listFiles((dir, name) -> name.toLowerCase().endsWith(".docx"));
			//System.out.println("number of files available : "+ files.length);
			for(File f : files)
			{
				//coll.setDocumentName(f.getName());
				//System.out.println(f.getName());
				//read the file from file system
				FileInputStream fis = new FileInputStream(f.getAbsolutePath());
				
				//create document with content of above file
				XWPFDocument document = new XWPFDocument(fis);
				
				//extract document content into word format
				XWPFWordExtractor extract = new XWPFWordExtractor(document);
				//System.out.println("Whole doc :: "+ extract.getText());
				//coll.setDocumentData(extract.getText());
				
				
				//save each file into database
				coll = new CollectionStructure(f.getName(),extract.getText());
				dataRepo.save(coll);
				
				fis.close();
			}
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	public void saveFile()
	{
		dataRepo.deleteAll();
		//coll = new CollectionStructure();
		readDocFile();
		//dataRepo.save(coll);
	}
	
	public List<CollectionStructure> getAll()
	{
		//returnWordOccurences();
		return dataRepo.findAll();
	}
	
	public void saveData(int lineNumber, int occurence, HashMap<Integer, Integer> localOutputData)
	{
		if(occurence!=0)
		{
			localOutputData.put(lineNumber, occurence);
		}
	}
	
	public int totalCountOfOccurences(HashMap<Integer, Integer> localOutputData)
	{
		int totalWords = 0;
		for (Integer value : localOutputData.values()) {
		    totalWords += value;
		}
		return totalWords;
	}
	
	public List<outputStructure> returnWordOccurences(String id)
	{
		returnOutputData.clear();
		List<CollectionStructure> allData = dataRepo.findAll();
		
		for(CollectionStructure coll : allData)
		{
			HashMap<Integer, Integer> localOutputData = new HashMap<Integer, Integer>();
			//localOutputData.clear();
			//finalOputputData.clear();
			System.out.println(coll.getDocumentData());
			
			int lineNumber = 1;
	        String[] lines = coll.getDocumentData().split("\\r?\\n");
	        
	        for (String line : lines) {
	        	
	            System.out.println("line " + lineNumber + " : " + line);
	            
	            String[] WordPresentInOneLine = line.split(" ");
				String WordToSearch = id;
				int wordCount = 0;
				for(String word: WordPresentInOneLine)
				{
					if(WordToSearch.equalsIgnoreCase(word))
					{
						wordCount++;
					}
				}
				//System.out.println("line number "+ lineNumber + "word Count: "+wordCount);
				saveData(lineNumber, wordCount, localOutputData);
				lineNumber++;
	        }
	        
	        //output = new outputStructure(coll.getDocumentName(),finalOputputData,totalCountOfOccurences());
	        output = new outputStructure(coll.getDocumentName(),localOutputData,totalCountOfOccurences(localOutputData));
	        returnOutputData.add(output);
	       // finalOputputData.clear();
	        
		}
		
		
		
		return returnOutputData;
	}
	
}
