package com.secureweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secureweb.model.ProfileImg;

public interface FileRepository extends JpaRepository<ProfileImg, Long> {
	ProfileImg findImgByUsername(String username);

}
