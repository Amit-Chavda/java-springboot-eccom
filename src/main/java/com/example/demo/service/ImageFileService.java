package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ImageFile;
import com.example.demo.repository.ImageFileRepo;

@Service
public class ImageFileService {
	
	@Autowired
	ImageFileRepo imageFileRepo;
	
	public ImageFile uploadImageFile(ImageFile imFile) {
		return imageFileRepo.save(imFile);
	}

	public Optional<ImageFile> getImageFileById(Integer id) {
		return imageFileRepo.findById(id);
	}

	public List<ImageFile> getAllImageFile() {
		return imageFileRepo.findAll();
	}	
	
}
