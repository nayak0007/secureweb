package com.secureweb.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secureweb.model.ProfileImg;
import com.secureweb.model.User;
import com.secureweb.repository.FileRepository;

@Service
public class FileServiceImpl implements FileService {
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private FileRepository fileRepository;

	@Override
	public void save(String username, String path) {
		ProfileImg pImg=new ProfileImg();
		pImg.setUsername(username);
		pImg.setFilePath(path);
		fileRepository.save(pImg);

	}

	@Override
	public ProfileImg findImgByUsername(String username) {
		ProfileImg img=fileRepository.findImgByUsername(username);
		fileRepository.findAll();
		return img;
	}

	@Override
	public List getAllFiles() {
		List fileList = new ArrayList();
		fileList=fileRepository.findAll();
//		/fileList=manager.createNamedQuery("getAllFiles", ProfileImg.class).getResultList();
		System.out.println("fileList>>"+fileList.size());
		return fileList;
	}

}
