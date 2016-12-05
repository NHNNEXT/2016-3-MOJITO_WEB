package com.mojito.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtils {
	public static final String filePath = "/Users/NEXT/Desktop/profileImages/"; // 프로젝트 외부 경로
//	public static final String filePath = "./src/main/resources/static/image/"; // 프로젝트 내부 경로
	
    public static final String fileUpload(MultipartFile uploadFile) throws IOException {
        String fileName = uploadFile.getOriginalFilename();

        File file = new File(filePath + fileName); //path
        System.out.println(file);
        uploadFile.transferTo(file);

        return fileName;
    }
}