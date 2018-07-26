package com.mycom.myapp.connector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownloadController {
	// 파일 저장 view페이지가 없기때문에 void
	@GetMapping("/download/{attached_file:.+}")
	public void download(@PathVariable String attached_file, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		System.out.println(attached_file);
		String path = request.getRealPath("upload");

		// file로 조합
		File file = new File(path, attached_file);

		InputStream in = new FileInputStream(file);

		response.setContentType("image/jpeg");
		response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
		response.setHeader("Content-Length", String.valueOf(file.length()));

		FileCopyUtils.copy(in, response.getOutputStream());
	}

}
