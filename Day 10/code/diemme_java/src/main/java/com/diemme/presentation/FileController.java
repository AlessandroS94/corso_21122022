package com.diemme.presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diemme.business.BusinessException;
import com.diemme.business.FileLayoutService;
import com.diemme.business.LayoutService;
import com.diemme.component.PageModel;
import com.diemme.domain.mysql.FileLayout;
import com.diemme.domain.mysql.Layout;

@Controller
public class FileController {

	@Autowired
	private FileLayoutService fileService;

	@Autowired
	private LayoutService layoutService;
	@Autowired
	private PageModel pageModel;

	@SuppressWarnings("static-access")
	@GetMapping("/layoutVisione")
	public String manageLayouts(Model model, Long idLayout) throws BusinessException {

		pageModel.setSIZE(1);
		pageModel.initPageAndSize();

		List<Long> idFiles = new ArrayList<Long>();
		Layout layout = new Layout();
		try {

			layout = layoutService.getLayout(idLayout);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}

		for (FileLayout file : layout.getFileLayouts()) {
			idFiles.add(file.getId());
		}

		Page<FileLayout> files = fileService.getAllFileslayout(pageModel.getPAGE(), pageModel.getSIZE(), idFiles);

		model.addAttribute("idLayout", idLayout);
		model.addAttribute("files", files);
		pageModel.resetPAGE();
		return "/backoffice/layoutDashboard/manageFileLayout.html";

	}

	@GetMapping("/layout/image/{id}")
	@ResponseBody
	public byte[] getImage(@PathVariable Long id) throws BusinessException, IOException {
		FileLayout file = new FileLayout();
		try {
		file = fileService.getFileLayout(id);
		
		} catch (DataAccessException e) {
			e.printStackTrace();

		}
		byte[] imageFile = file.getContentImg();
		return imageFile;
	}

}
