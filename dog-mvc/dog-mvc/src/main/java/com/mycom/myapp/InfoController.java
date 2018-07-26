package com.mycom.myapp;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.mycom.myapp.connector.Connector;
import com.mycom.myapp.connector.PagingInfo;
import com.mycom.myapp.dog_info.Dog_no;
import com.mycom.myapp.dog_info.InfoData;
import com.mycom.myapp.dog_info.InfoDataView;

@Controller
@SessionAttributes({"al_infoData", "pagingInfo" })
public class InfoController {
	@RequestMapping(value = "info/info_add", method = RequestMethod.GET)
	public String info_add(@ModelAttribute("infoDataView") InfoDataView infoDataView) {

		return "info/info_add";
	}

	// 정보 등록
	@PostMapping(value = "info/info_add_process")
	public String info_add_process(@RequestParam("picture") MultipartFile picture,
			@Valid @ModelAttribute("infoDataView") InfoDataView infoDataView, Errors errors, HttpServletRequest request,
			Model model) throws IllegalStateException, IOException {
		// @Valid 유효성 검사
		if (errors.hasErrors()) {

			return "info/info_add";
		}
		String dog_no = Dog_no.num();

		// DB에 넣기
		Connection con = Connector.getConnection();
		String insertSQL = "insert into dog_info(dog_no,name,birth,species,tel,created_date,picture)values(?,?,?,?,?,?,?)";

		try {
			// DB에 값 집어넣기
			PreparedStatement ps = con.prepareStatement(insertSQL);

			ps.setString(1, dog_no);
			ps.setString(2, infoDataView.getName());
			ps.setString(3, infoDataView.getBirth());
			ps.setString(4, infoDataView.getSpecies());
			ps.setString(5, infoDataView.getTel());
			Timestamp current_date = new Timestamp(Calendar.getInstance().getTime().getTime());
			ps.setTimestamp(6, current_date);
			InputStream is = picture.getInputStream();
			ps.setBlob(7, is);

			if (1 == ps.executeUpdate()) {
				System.out.println("정상 등록완료");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("dog_no", dog_no);
		model.addAttribute("InfoDataView", infoDataView);

		return "info/info_add_result";
	}

	// 정보 전체 조회
	@GetMapping(value = "info/info_all")
	// nRecordsPerPage 가 안 들어올시 required=false
	public String info_all(@RequestParam(value = "separation", defaultValue = "first") String separation,
			@RequestParam(value = "recordsPerPage", required = false) Integer nRecordsPerPage, Model model,
			HttpSession session) {

		// 값을 넣기위한 List 생성
		List<InfoData> al_infoData = new ArrayList<InfoData>();

		// 페이징
		PagingInfo pagingInfo = new PagingInfo();
		
		System.out.print("첫번째" + nRecordsPerPage);
		
		if (session.getAttribute("al_infoData") != null) {
			al_infoData = (List<InfoData>) session.getAttribute("al_infoData");

			pagingInfo = (PagingInfo) session.getAttribute("pagingInfo");

			System.out.print(nRecordsPerPage);
			if (nRecordsPerPage == null) {
				nRecordsPerPage = pagingInfo.getnRecordsPerPage();
			}
		} else {
			nRecordsPerPage = 5;
			Connection con = Connector.getConnection();

			String countSQL = "select count(dog_no) as totalCounts from dog_info";
			String selectSQL = "select dog_no,name,birth,species,tel,created_date from dog_info";

			try {
				PreparedStatement ps = con.prepareStatement(countSQL);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					pagingInfo.setnTotalRecords(rs.getInt("totalCounts"));
				}

				ps = con.prepareStatement(selectSQL);
				rs = ps.executeQuery();
				while (rs.next()) {
					InfoData infoData = new InfoData();
					infoData.setDog_no(rs.getString("dog_no"));
					infoData.setName(rs.getString("name"));
					infoData.setSpecies(rs.getString("species"));
					infoData.setBirth(rs.getString("birth"));
					infoData.setTel(rs.getString("tel"));
					infoData.setCreated_date(rs.getTimestamp("created_date"));

					al_infoData.add(infoData);
				}

				model.addAttribute("al_infoData", al_infoData);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 페이징넣기
		// 총 페이지 구함
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
		
		System.out.print(fromIndex);
		System.out.print(toIndex);

		List<InfoData> al_sub_infoData = al_infoData.subList(fromIndex, toIndex);

		model.addAttribute("al_sub_infoData", al_sub_infoData);
		model.addAttribute(""
				+ "pagingInfo", pagingInfo);

		model.addAttribute("currentPage", pagingInfo.getnCurrentPage());
		model.addAttribute("totalPage", pagingInfo.getnTotalPage());
		model.addAttribute("recordsPerPage", pagingInfo.getnRecordsPerPage());
		model.addAttribute("totalCounts", pagingInfo.getnTotalRecords());

		return "info/info_all";
	}

}
