package com.kitchen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kitchen.AuthController.SimpleMD5;
import com.kitchen.authnticate.Authnticate;
import com.kitchen.dao.KitchenDao;
import com.kitchen.registerPojo.Register;
import com.kitchen.userpojo.User;

@Service("service")
public class KithchenServices {

	@Autowired
	@Qualifier("dao")
	KitchenDao dao;
	

	public KitchenDao getDao() {
		return dao;
	}


	public void setDao(KitchenDao dao) {
		this.dao = dao;
	}


	public boolean saveRegisterForm(Register register) {

		int records = dao.saveRegisterForm(register);
		return records > 0 ? true : false;
	}
	
	public User validateForm(Authnticate auth) {
		SimpleMD5 md = new SimpleMD5();
		String hash = md.hashing(auth.getPassword());
		return dao.checkValidUser(hash);
	}
}
