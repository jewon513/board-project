package com.biz.board.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUpService {

	private final String filePath = "C:/Users/jewon/Desktop/Javaworks/bizwork/files";
	
	public String fileup(MultipartFile upFile) {
		
		// 파일의 이름 추출
		String originalFileName = upFile.getOriginalFilename();
		
		// UUID를 생성
		String strUUID = UUID.randomUUID().toString();
		
		// 부착
		strUUID += originalFileName;
		
		// 변경된 파일 이름과 filepath로 File 객체 생성
		File serverFile = new File(filePath, strUUID);
		
		// 파일을 filepath에 serverFile로 복사 수행
		// 실제로 서버에 업로드 되는 부분.
		
		try {
			upFile.transferTo(serverFile);
			
			return strUUID;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return null;
		
		
	}
	
}
