package com.upload.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upload.model.Imagem;

@Repository
public interface ImagemRepo extends JpaRepository<Imagem, Integer>{

}