package com.upload.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.upload.error.UploadInternalServerErrorException;
import com.upload.error.ValidationErrorException;
import com.upload.messages.ExceptionMessageError;
import com.upload.model.Imagem;
import com.upload.repo.ImagemRepo;
import com.upload.utils.UploadUtils;

@RestController
@RequestMapping("/api/v1/galeria")
public class GaleriaController {
	
	@Autowired
	ImagemRepo imagemRepo;
	
	@GetMapping
	public List<Imagem> listaDeObras() {
		List<Imagem> listaDeImagens = this.imagemRepo.findAll();
		return listaDeImagens;
	}
	
	@GetMapping(path="{id}")
	public ResponseEntity<Object> getImagem(@PathVariable Integer id) {
		Optional<Imagem> data = this.imagemRepo.findById(id);

		if(data.isEmpty()) {
			return new ResponseEntity<>(new ExceptionMessageError("Nada encontrado", HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(data.get(), HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<Object> novoPost(@RequestParam("img") MultipartFile file, @Valid Imagem imagem, BindingResult err) {
		String destination = "C:\\galeria\\imagens\\upload\\";
		
		if(err.hasErrors()) {
			throw new ValidationErrorException(err);
		}
		
		Path localSave = Paths.get(destination);

		Imagem novaImagem = UploadUtils.uploadImagem(file, imagem);

		try {
			Files.createDirectories(localSave); //cria o diretório caso não exista.
			
			Path localSaveFile = Paths.get(destination + novaImagem.getStorageName());
			
			Files.write(localSaveFile, file.getBytes()); //salva  a imagem
			
			return new ResponseEntity<>(this.imagemRepo.save(novaImagem), HttpStatus.OK);
			
		} catch (IOException ex) {
			throw new UploadInternalServerErrorException("Houve um erro interno. Tente novamente em outro momento");
		}
		
	}
	
	//** PUT na versão 2 **
	
	@DeleteMapping(path = "{id}")
	public ResponseEntity<Object> updatePost(@PathVariable("id") Integer id) {
		Optional<Imagem> data = this.imagemRepo.findById(id);

		if(data.isEmpty()) {
			return new ResponseEntity<>(new ExceptionMessageError("Nada encontrado", HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);
		}
		
		this.imagemRepo.delete(data.get());
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
	
}
















