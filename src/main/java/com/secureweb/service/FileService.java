package com.secureweb.service;

import java.util.List;

import com.secureweb.model.ProfileImg;

public interface FileService {
	void save(String username,String path);
	ProfileImg findImgByUsername(String username);
	List getAllFiles();

}
