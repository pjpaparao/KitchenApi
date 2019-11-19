package com.kitchen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kitchen.dao.KitchenDao;
import com.kitchen.registerPojo.Register;

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
}
