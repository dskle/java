package com.mycom.myapp;

import java.sql.*;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycom.myapp.connector.Connector;
import com.mycom.myapp.user.UserData;

@Controller
public class UserController {
	//회원가입 화면
	@GetMapping(value="user/join")
	public String join(@ModelAttribute("userData") UserData userData) {
		
		
		return "user/join";	
	}
	//중복확인
	@PostMapping(value="/idcheck.do")
	@ResponseBody
	public UserData idcheck(@RequestBody String user_id) {
		
		
		return userData;
	}
	
	@PostMapping(value="user/join_process")
	public String join_process(@Valid @ModelAttribute("userData") UserData userData, Errors errors) {
		if(errors.hasErrors()) {
			
			return "user/join";
		}
		
		Connection con = Connector.getConnection();
		String insertSQL = "insert into user(user_id,password) values(?,?)";
		try {
			
			PreparedStatement ps = con.prepareStatement(insertSQL);
			ps.setString(1, userData.getUser_id());
			ps.setString(2, userData.getPassword());
			
			if(1==ps.executeUpdate()) {
				System.out.print("정상등록");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "user/login";
		
	}

}
