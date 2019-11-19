package com.kitchen.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kitchen.registerPojo.Register;

@Repository("dao")
public class KitchenDao {

	@Autowired

	public JdbcTemplate template;

	public int saveRegisterForm(Register reg) {
		int records = 0;
		try {
			String sql = "insert into register values" + "('" + reg.getName() + "','" + reg.getEmaill() + "','"
					+ reg.getPassword() + "')";
			records = template.update(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return records;
	}

}
