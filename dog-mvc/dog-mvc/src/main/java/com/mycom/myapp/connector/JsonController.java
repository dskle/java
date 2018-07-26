package com.mycom.myapp.connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.dog_info.InfoData;

@RestController
public class JsonController {

	@GetMapping(value = "/api/get_name", produces = "application/json")
	public InfoData api_name(@RequestParam(value = "name_input") String name_input) {
		InfoData infoData = new InfoData();
		
		System.out.println(name_input);
		
		//infoData.getName();

		/*String seletSQL = "select name, dog_no, tel from dog_info where name like ?";

		try {
			Connection con = Connector.getConnection();
			PreparedStatement ps = con.prepareStatement(seletSQL);
			
			System.out.println(name_input);
			ps.setString(1, "%"+name_input+"%");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				infoData.getName();
				infoData.getDog_no();
				infoData.getTel();
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return infoData;

	}

}
