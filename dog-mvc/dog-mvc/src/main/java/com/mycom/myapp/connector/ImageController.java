package com.mycom.myapp.connector;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletResponse;

import java.sql.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycom.myapp.connector.Connector;

@Controller // annotation
public class ImageController {
	// @GetMapping("info/show_image/{jumin_no}", method = RequestMethod.GET) 다시 보기
	@RequestMapping(value = "info/show_image/{dog_no}", method = RequestMethod.GET)
	public void showImage(@PathVariable String dog_no, HttpServletResponse response) {
		// System.out.println(jumin_no);

		try {
			Connection con = Connector.getConnection();
			String selectSQL = "SELECT picture FROM dog_info WHERE dog_no=?";
			PreparedStatement ps = con.prepareStatement(selectSQL);

			ps.setString(1, dog_no);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				byte[] bPicture = rs.getBytes("picture");
				// String picture_content_type = rs.getString("picture_content_type");

				response.setContentType("image/jpeg");
				response.getOutputStream().write(bPicture);
				response.getOutputStream().close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}