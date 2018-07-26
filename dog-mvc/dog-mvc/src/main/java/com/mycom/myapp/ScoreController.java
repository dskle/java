package com.mycom.myapp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mycom.myapp.connector.Connector;
import com.mycom.myapp.connector.PagingInfo;
import com.mycom.myapp.dog_score.ScoreData;
import com.mycom.myapp.dog_score.ScoreDataView;

@Controller
@SessionAttributes({ "al_scoreData", "pagingInfo" })
public class ScoreController {
	// 성적등록 - 학번 이름 구하기
	@GetMapping(value = "score/score_add_pre")
	public String Score_add_pre() {

		return "score/score_add_pre";
	}

	@PostMapping(value = "score/score_add_pre_process")
	public String Score_add_pre_process(@ModelAttribute("scoreDataView") ScoreDataView scoreDataView,
			@RequestParam("name") String name, Model model) {

		String seletSQL = "select name, dog_no from dog_info where name like ?";

		try {
			Connection con = Connector.getConnection();
			PreparedStatement ps = con.prepareStatement(seletSQL);

			System.out.println(name);
			ps.setString(1, '%' + name + '%');
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String name1 = rs.getString("name");
				String dog_no = rs.getString("dog_no");

				model.addAttribute("name", name1);
				model.addAttribute("dog_no", dog_no);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "score/score_add";

	}

	// 성적등록
	@GetMapping(value = "score/score_add")
	public String Score_add(@ModelAttribute("scoreDataView") ScoreDataView scoreDataView) {

		return "score/score_add";
	}

	@PostMapping(value = "score/score_add_process")
	public String Score_add_process(@Valid @ModelAttribute("scoreDataView") ScoreDataView scoreDataView, Errors errors,
			Model model) {
		if (errors.hasErrors()) {

			return "score/score_add";
		}

		Connection con = Connector.getConnection();
		String insertSQL = "insert into dog_score(dog_no,name,health,agility,nose_work) values(?,?,?,?,?)";

		try {
			PreparedStatement ps = con.prepareStatement(insertSQL);
			ps.setString(1, scoreDataView.getDog_no());
			ps.setString(2, scoreDataView.getName());
			ps.setInt(3, scoreDataView.getHealth());
			ps.setInt(4, scoreDataView.getAgility());
			ps.setInt(5, scoreDataView.getNose_work());

			if (1 == ps.executeUpdate()) {
				System.out.print("정상등록");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("scoreDataView", scoreDataView);

		return "score/score_add_result";
	}

	@GetMapping(value = "score/score_all")
	public String score_all(@RequestParam(value = "separation", defaultValue = "first") String separation,
			@RequestParam(value = "recordsPerPage", required = false) Integer nRecordsPerPage, Model model,
			HttpSession session) {
		List<ScoreData> al_scoreData = new ArrayList<ScoreData>();
		PagingInfo pagingInfo = new PagingInfo();

		if (session.getAttribute("al_scoreData") != null) {
			al_scoreData = (List<ScoreData>) session.getAttribute("al_scoreData");
			pagingInfo = (PagingInfo) session.getAttribute("pagingInfo");

			if (nRecordsPerPage == null) {
				nRecordsPerPage = pagingInfo.getnRecordsPerPage();
			}
		} else {
			nRecordsPerPage = 5;
			Connection con = Connector.getConnection();

			String countSQL = "select count(dog_no) as 'totalCounts' from dog_score";
			String selectSQL = "select dog_no,name,health,agility,nose_work,(health+agility+nose_work)/3  as 'avg' from dog_score order by avg desc";

			try {
				// 총 레코드수 구하기
				PreparedStatement ps = con.prepareStatement(countSQL);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					pagingInfo.setnTotalRecords(rs.getInt("totalCounts"));
				}
				// DB구하기
				PreparedStatement ps1 = con.prepareStatement(selectSQL);
				ResultSet rs2 = ps1.executeQuery();
				
				while (rs2.next()) {
					String grade="";
					ScoreData scoreData = new ScoreData();
					
					scoreData.setDog_no(rs2.getString("dog_no"));
					scoreData.setName(rs2.getString("name"));
					scoreData.setHealth(rs2.getInt("health"));
					scoreData.setAgility(rs2.getInt("agility"));
					scoreData.setNose_work(rs2.getInt("nose_work"));
					scoreData.setAvg(rs2.getDouble("avg"));
					double avg = rs2.getDouble("avg");
					if (avg >= 95.0 && avg <= 100.0) {
						grade = "A+";
					} else if (avg >= 90.0 && avg <= 94.9) {
						grade = "A";
					} else if (avg >= 85.0 && avg <= 89.9) {
						grade = "B+";
					} else if (avg >= 80.0 && avg <= 84.9) {
						grade = "B";
					} else if (avg >= 75.0 && avg <= 79.9) {
						grade = "C+";
					} else if (avg >= 70.0 && avg <= 74.9) {
						grade = "C";
					} else if (avg >= 65.0 && avg <= 69.9) {
						grade = "D+";
					} else if (avg >= 60.0 && avg <= 64.9) {
						grade = "D";
					} else {
						grade = "F";
					}
					scoreData.setGrade(grade);
					
					al_scoreData.add(scoreData);

					model.addAttribute("al_scoreData", al_scoreData);
					
					

				}

			} catch (Exception e) {
				e.printStackTrace();

			}
		}

		// 페이징
		int nTotalPage = (int) Math.ceil((double) pagingInfo.getnTotalRecords() / (double) nRecordsPerPage);

		pagingInfo.setnRecordsPerPage(nRecordsPerPage);
		pagingInfo.setnTotalPage(nTotalPage);

		int nCurrentPage = pagingInfo.getnCurrentPage();

		switch (separation) {
		case "first":
			nCurrentPage = 1;
			break;
		case "previous":
			nCurrentPage = nCurrentPage > 1 ? --nCurrentPage : 1;
			break;
		case "next":
			nCurrentPage = nCurrentPage < nTotalPage ? ++nCurrentPage : nTotalPage;
			break;
		case "last":
			nCurrentPage = pagingInfo.getnTotalPage();
			break;
		}

		pagingInfo.setnCurrentPage(nCurrentPage);

		int fromIndex = (nCurrentPage - 1) * nRecordsPerPage;
		int toIndex = fromIndex + nRecordsPerPage;
		if (toIndex >= pagingInfo.getnTotalRecords()) {
			toIndex = pagingInfo.getnTotalRecords();
		}

		List<ScoreData> al_sub_scoreData = al_scoreData.subList(fromIndex, toIndex);

		model.addAttribute("al_sub_scoreData", al_sub_scoreData);
		model.addAttribute("pagingInfo", pagingInfo);

		model.addAttribute("currentPage", pagingInfo.getnCurrentPage());
		model.addAttribute("totalPage", pagingInfo.getnTotalPage());
		model.addAttribute("recordsPerPage", pagingInfo.getnRecordsPerPage());
		model.addAttribute("totalCounts", pagingInfo.getnTotalRecords());

		return "score/score_all";

	}

}
