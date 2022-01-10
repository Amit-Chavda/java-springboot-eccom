package com.example.demo.controller;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.ImageFile;
import com.example.demo.service.ImageFileService;
import com.example.demo.utility.ImageCompressor;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/files")
public class FileController {
	
	@Autowired
	private ImageFileService iService;
	
	@PostMapping(value="/upload",consumes = "multipart/form-data")
	public ResponseEntity<ImageFile> uploadFile(@RequestParam(required=true) MultipartFile file ) throws Exception {
		
		if(file.isEmpty()) {
			throw new Exception("File is empty!");
		}
		
		
		ImageFile imFile=new ImageFile();
		imFile.setName(file.getOriginalFilename());
		imFile.setImage(Base64.getEncoder().withoutPadding().encodeToString(file.getBytes()));
		System.out.println("Bytes "+imFile.getImage());
		imFile.setType(file.getContentType());	
	
		return new ResponseEntity<ImageFile>(imFile, HttpStatus.CREATED);
	}
	
	@GetMapping("/getfile")
	public ResponseEntity<ImageFile> getFileById(@RequestParam() Integer id ) throws Exception {
		return new ResponseEntity<>(iService.getImageFileById(id).get(), HttpStatus.CREATED);
	}
	
	@GetMapping("/getall")
	public @ResponseBody List<ImageFile> getAllFile() throws Exception {
		return iService.getAllImageFile();
	}
}
