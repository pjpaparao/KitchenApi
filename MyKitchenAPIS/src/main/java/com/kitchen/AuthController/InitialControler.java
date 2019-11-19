package com.kitchen.AuthController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.kitchen.authnticate.Authnticate;
import com.kitchen.registerPojo.Register;
import com.kitchen.services.KithchenServices;
import com.kitchen.userpojo.User;

@RestController
@RequestMapping("/auth")
public class InitialControler {

	@Autowired
	@Qualifier("service")
	KithchenServices service;

	public KithchenServices getService() {
		return service;
	}

	public void setService(KithchenServices service) {
		this.service = service;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, String> login(@RequestBody String form,HttpServletRequest req,HttpServletResponse res) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper=new ObjectMapper();
		Authnticate auth = mapper.readValue(form, Authnticate.class);
		User user = service.validateForm(auth);
		System.out.println(user.getEmail()+"\n"+user.getName());
		Map<String, String> map = new HashMap<String, String>();
		ObjectMapper obj = new ObjectMapper();
		Gson gson = new Gson();
		map.put("user", gson.toJson(user));
		UUID uuid = UUID.randomUUID();
		String token = uuid.toString();
		map.put("token", token);
		return map;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")

	public Map<String, String> String(@RequestBody String postObj, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println(postObj);
		ObjectMapper obj = new ObjectMapper();
		List<Register> list = null;
		Register register;
		boolean validate = false;
		Map<String, String> map = null;
		String token = "";
		try {

			register = obj.readValue(postObj, Register.class);
			list = new ArrayList<Register>();
			System.out.println(register.getPassword() + " " + register.getName() + " " + register.getEmaill());

			if (register.getPassword() != null) {
				SimpleMD5 md = new SimpleMD5();
				String hash = md.hashing(register.getPassword());
				System.out.println(hash);

				register.setPassword(hash);
				boolean val = service.saveRegisterForm(register);

				if (val) {
					validate = val;
					map = new HashMap<String, String>();
					map.put("register", validate + "");
				}
			}

		} catch (JsonParseException e) {

			e.printStackTrace();
		} catch (JsonMappingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

		catch (Exception e) {

			e.printStackTrace();
		}

		return map;
	}
}
