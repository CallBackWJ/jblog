package com.douzone.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileuploadService {

	private static final String SAVE_PATH = "/upload";
	private static final String URL = "assets/upload/images";
	
	String originalFileName;
	String extName;
	String saveFileName ;
	long filesize ;
	
	
	public String restore(MultipartFile file) {
		// TODO Auto-generated method stub
		String url = "";
		try {
			if (file.isEmpty()) {
				return url;
			}
			originalFileName = file.getOriginalFilename();
			extName = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
			saveFileName = generateSaveFileName(extName);
			filesize = file.getSize();

			System.out.println("########" + originalFileName);
			System.out.println("########" + extName);
			System.out.println("########" + saveFileName);
			System.out.println("########" + filesize);

			byte[] filedata = file.getBytes();
			OutputStream os=new FileOutputStream(SAVE_PATH + "/" + saveFileName);
			os.write(filedata);
			os.close();
		} catch (IOException e) {
			new RuntimeException("upload fail");
		}

		return URL + "/" + saveFileName;
	}

	private String generateSaveFileName(String extName) {

		String fileName = "";
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += ("." + extName);
		return fileName;
	}

}
