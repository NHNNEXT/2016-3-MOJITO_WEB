package com.mojito.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtils {
    public static final String fileUpload(MultipartFile uploadFile) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String fileName = uploadFile.getOriginalFilename();
        System.out.println(fileName);

        File file = new File("/Users/NEXT/Desktop/profileImages/"+ fileName); //path
        System.out.println(file);
        try{
            inputStream = uploadFile.getInputStream();
            System.out.println(inputStream);

            file.createNewFile();
            outputStream = new FileOutputStream(file);
            System.out.println(outputStream);

            int read = 0;
            byte[] bytes = new byte[1024];

            while((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }
}