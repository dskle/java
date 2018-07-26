package com.mycom.myapp;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.mycom.myapp.board.BoardData;
import com.mycom.myapp.board.BoardDataView;
import com.mycom.myapp.connector.Connector;
import com.mycom.myapp.connector.PagingInfo;

@Controller
@SessionAttributes({ "al_boardDataView", "pagingInfo" })
public class BoardController {
	// 글목록
	@GetMapping(value = "board/board_list")
	public String board_list(@RequestParam(value = "separation", defaultValue = "first") String separation,
			@RequestParam(value = "recordsPerPage", required = false) Integer nRecordsPerPage, Model model,
			HttpSession session) {

		// 값을 넣기 위한 List 생성
		List<BoardDataView> al_boardDataView = new ArrayList<BoardDataView>();

		// 페이징
		PagingInfo pagingInfo = new PagingInfo();

		System.out.print("dd");
		System.out.print(al_boardDataView);

		if (session.getAttribute("al_boardDataView") != null) {
			System.out.print("grg");
			al_boardDataView = (List<BoardDataView>) session.getAttribute("al_boardDataView");
			pagingInfo = (PagingInfo) session.getAttribute("pagingInfo");

			if (nRecordsPerPage == null) {
				nRecordsPerPage = pagingInfo.getnRecordsPerPage();
			}
		} else {
			System.out.print("sdfsd");
			nRecordsPerPage = 5;
			Connection con = Connector.getConnection();

			String countSQL = "select count(id) as totalCounts from board";
			String selectSQL = "select id,title,creator,message,attached_file,created_date from board  order by created_date desc";

			try {
				PreparedStatement ps = con.prepareStatement(countSQL);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					pagingInfo.setnTotalRecords(rs.getInt("totalCounts"));
				}

				ps = con.prepareStatement(selectSQL);
				rs = ps.executeQuery();
				while (rs.next()) {
					BoardDataView boardData = new BoardDataView();
					boardData.setId(rs.getInt("id"));
					boardData.setTitle(rs.getString("title"));
					boardData.setCreator(rs.getString("creator"));
					boardData.setMessage(rs.getString("message"));
					boardData.setAttached_file(rs.getString("attached_file"));
					boardData.setCreated_date(rs.getTimestamp("created_date"));

					al_boardDataView.add(boardData);
				}

				model.addAttribute("al_boardDataView", al_boardDataView);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 페이징 넣기
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

		List<BoardDataView> al_sub_boardDataView = al_boardDataView.subList(fromIndex, toIndex);

		model.addAttribute("al_sub_boardDataView", al_sub_boardDataView);
		model.addAttribute("" + "pagingInfo", pagingInfo);

		model.addAttribute("currentPage", pagingInfo.getnCurrentPage());
		model.addAttribute("totalPage", pagingInfo.getnTotalPage());
		model.addAttribute("recordsPerPage", pagingInfo.getnRecordsPerPage());
		model.addAttribute("totalCounts", pagingInfo.getnTotalRecords());

		return "board/board_list";

	}

	// 글쓰기
	@GetMapping(value = "board/board_write")
	public String board_write() {

		return "board/board_write";

	}

	// 글쓰기프로세스
	@PostMapping(value = "board/board_write_process")
	public String board_write_process(@RequestParam("attached_file") MultipartFile attached_file,
			@RequestParam("title") String title, @RequestParam("message") String message,
			@RequestParam("creator") String creator, HttpServletRequest request, HttpServletResponse response,
			Model model) throws IllegalStateException, IOException {

		// 폴더로 사진넣기
		String path = request.getRealPath("/upload/");
		System.out.println(path);
		File file = new File(path + "/" + attached_file.getOriginalFilename());
		attached_file.transferTo(file);

		// DB로 넣기
		Connection con = Connector.getConnection();
		String insertSQL = "insert into board(title,message,attached_file,creator,created_date) value(?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(insertSQL);
			ps.setString(1, title);
			ps.setString(2, message);
			ps.setString(3, attached_file.getOriginalFilename());
			ps.setString(4, creator);
			Timestamp ts = new Timestamp(Calendar.getInstance().getTime().getTime());
			ps.setTimestamp(5, ts);

			if (1 == ps.executeUpdate()) {
				System.out.println("정상등록");
				String selectSQL = "select id, creator,title, message, attached_file,created_date from board order by created_date desc limit 1";
				try {
					ps = con.prepareStatement(selectSQL);
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						BoardData board = new BoardData();
						board.setId(rs.getInt("id"));
						board.setCreator(rs.getString("creator"));
						board.setTitle(rs.getString("title"));
						board.setMessage(rs.getString("message"));
						String path1 = request.getRealPath("/upload/");
						board.setAttached_file(rs.getString("attached_file"));
						board.setCreated_date(rs.getTimestamp("created_date"));

						model.addAttribute("upload", path1);
						model.addAttribute("board", board);

						return "board/board_view";

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "board/board_view";

	}

	// 글보기
	@GetMapping(value = "/board/board_view/{id}")
	public String board_view(@PathVariable("id") int id, HttpServletRequest request, Model model) {
		Connection con = Connector.getConnection();
		String selectSQL = "select id, creator,title, message, attached_file,created_date from board where id=?";
		
		try {
			PreparedStatement ps = con.prepareStatement(selectSQL);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				BoardData board = new BoardData();
				board.setId(rs.getInt("Id"));
				board.setCreator(rs.getString("creator"));
				board.setTitle(rs.getString("title"));
				board.setMessage(rs.getString("message"));
				String path1 = request.getRealPath("/upload/");
				board.setAttached_file(rs.getString("attached_file"));
				board.setCreated_date(rs.getTimestamp("created_date"));

				model.addAttribute("upload", path1);
				model.addAttribute("board", board);

				return "board/board_view";

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
