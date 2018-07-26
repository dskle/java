package com.mycom.myapp.dog_info;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;

import com.mycom.myapp.connector.Connector;

public class Dog_no {
	
	public static String num() {
		// DB에 넣기
		Connection con = Connector.getConnection();
		// 강아지학번넣는 SQL String
		String nowSQL = "select now()";
		String lastSQL = "select dog_no from dog_info order by dog_no desc limit 1";

		String year = "";
		String lastDogNo = "";
		String new_dog_no = "";

		try {
			// 학번 자동적으로 넣는 구문
			// 현재시간 넣는 sql
			PreparedStatement ps = con.prepareStatement(nowSQL);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Date current_year = rs.getDate(1);
				year = MessageFormat.format("{0,date,yyyy}", current_year);
			}
			// 학번
			ps = con.prepareStatement(lastSQL);
			rs = ps.executeQuery();
			if (rs.next()) {
				lastDogNo = rs.getString("dog_no");
			}
			// 학번이 없을때 예외처리
			long nNewNumber = 0;
			if (lastDogNo == null || lastDogNo == "") {
				nNewNumber = 1;

			} else {
				long number = Long.parseLong(lastDogNo.substring(4,8));
				nNewNumber = number + 1;

			}

			new_dog_no = MessageFormat.format("{0}{1,number,0000}", year, nNewNumber);

			System.out.println(year);
			System.out.println(nNewNumber);
			System.out.println(new_dog_no);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new_dog_no;
	}
}
