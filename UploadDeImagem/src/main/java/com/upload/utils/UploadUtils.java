package com.upload.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.multipart.MultipartFile;

import com.upload.error.MimeTypeException;
import com.upload.model.Imagem;

public class UploadUtils {
	
	public static final Imagem uploadImagem(MultipartFile file, Imagem imagem) {
		
		String[] allowTypes = {"image/png", "image/jpg", "image/jpeg"};
		
		boolean allow = false;
	
		for(String type : allowTypes) {
			if(file.getContentType().equals(type)) {
				allow =  true;
			}
		}
		
		if(!allow) {
			throw new MimeTypeException("São permitidos apenas arquivo de imagem .png, .jpg, .jpeg");
		}
		
		
		DateTimeFormatter time = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
		String newFileName = time.format(LocalDateTime.now()) + file.getOriginalFilename();
		
		Imagem novaImagem = new Imagem();
		novaImagem.setTitle(imagem.getTitle());
		novaImagem.setStorageName(newFileName);
		novaImagem.setOriginalName(file.getOriginalFilename());
		
		return novaImagem;
	}
	
}
