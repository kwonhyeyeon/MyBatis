package com.spring.admin.board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.admin.board.service.AdminBoardService;
import com.spring.client.board.vo.BoardVO;
import com.spring.common.excel.ListExcelView;

@Controller
@RequestMapping(value="/admin")
public class AdminBoardController {

	private Logger log = LoggerFactory.getLogger(AdminBoardController.class);

	@Autowired
	private AdminBoardService adminBoardService;

	// 글 목록 구현하기
	@RequestMapping(value="/board/boardList", method = RequestMethod.GET)
	public String boardList(@ModelAttribute BoardVO bvo, Model model) {
		log.info("admin boardList 호출 성공");
		
		// 전체 레코드수 구현
		int total = adminBoardService.boardListCnt(bvo);
		log.info("total = "+total);
		
		List<BoardVO> boardList = adminBoardService.boardList(bvo);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("total", total);
		model.addAttribute("data", bvo);
		
		return "admin/board/boardList";
	}
	
	// 엑셀 다운로드 구현하기
	// 참고 : ListExcelView 클래스에서 맵타입으로 Model에 접근하게 된다
	
	@RequestMapping(value="/board/boardExcel", method=RequestMethod.GET)
	public ModelAndView  boardExcel(@ModelAttribute BoardVO bvo) {
		log.info("boardExcel 호출 성공");
		
		List<BoardVO> boardList = adminBoardService.boardList(bvo);
		
		ModelAndView mv = new ModelAndView(new ListExcelView());
		mv.addObject("list", boardList);
		mv.addObject("template", "board.xlsx");
		mv.addObject("file_name", "board");
		
		return mv;
	}
}
