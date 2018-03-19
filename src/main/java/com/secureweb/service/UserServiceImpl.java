package com.secureweb.service;

import com.secureweb.model.User;
import com.secureweb.repository.RoleRepository;
import com.secureweb.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class UserServiceImpl implements UserService {

	@PersistenceContext
	private EntityManager manager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    @Override
    public List getAllUser() {
    	List userList = new ArrayList();
    	userList=userRepository.findAll();
    	//userList=manager.createNamedQuery("getAllUsers", User.class).getResultList();
    	
		return userList;
        
    }
    
    @Override
	public void updateUser(String uId,String name,String dob,String state,String city,String mobile,String email) {
    	User user=userRepository.findByUsername(uId);
    	user.setUsername(uId);
    	user.setName(name);
    	user.setDob(dob);
    	user.setState(state);
    	user.setCity(city);
    	user.setMobile(mobile);
    	user.setEmail(email);
    	userRepository.save(user);
      
    }
}
