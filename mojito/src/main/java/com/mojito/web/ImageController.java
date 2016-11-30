package com.mojito.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mojito.domain.User;
import com.mojito.domain.UserRepository;
import com.mojito.utils.FileUploadUtils;

@Controller
public class ImageController {
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value = "/images/{id}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImage(@PathVariable Long id) throws IOException {
		User user = userRepository.findOne(id);
		String imagePath = FileUploadUtils.filePath + user.getProfileImageName();
		Path p = Paths.get(imagePath);
		byte[] image = Files.readAllBytes(p);
		return ResponseEntity.ok().contentType(
				imagePath.substring(imagePath.length() - 2).equals('n') ? MediaType.IMAGE_PNG : MediaType.IMAGE_JPEG).body(image);
	}
}
