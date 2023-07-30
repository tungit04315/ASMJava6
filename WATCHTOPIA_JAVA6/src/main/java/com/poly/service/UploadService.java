package com.poly.service;

import java.io.File;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

@Service
public class UploadService {
	@Autowired
	ServletContext context;
	
	public File save(MultipartFile file, String folder) {
		File dir = new File(context.getRealPath(folder));
		if(!dir.exists()) {
			dir.mkdirs();
		}
		try {
			File saveFile = new File(dir, file.getOriginalFilename());
			file.transferTo(saveFile);
			return saveFile;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
