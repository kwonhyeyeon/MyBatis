package com.spring.client.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.client.board.service.BoardService;
import com.spring.client.board.vo.BoardVO;
import com.spring.common.file.FileUploadUtil;
import com.spring.common.page.Paging;
import com.spring.common.util.Util;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	// 로그를 굳이 쓰고싶다면..
	private Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	// 글목록
	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
	public String boardList(@ModelAttribute BoardVO bvo, Model model) {
		log.info("boardList 호출 성공");
		
		
		// 페이지 설정
		Paging.setPage(bvo);
		// 전체 레코드수 구현
		int total = boardService.boardListCnt(bvo);
		log.info("total : " + total);
		
		// 글번호 재설정
		int count = total - (Util.nvl(bvo.getPage()) - 1) * Util.nvl(bvo.getPageSize());
		log.info("count : " + count);
		
		List<BoardVO> boardList = boardService.boardList(bvo);
		model.addAttribute("boardList", boardList);
	 	model.addAttribute("data", bvo);
	 	// 모델에 페이지 관련 데이터 추가
	 	model.addAttribute("count", count);
	 	model.addAttribute("total", total);
		
		return "board/boardList";
	}
	
	// 글쓰기폼 출력
	@RequestMapping(value = "/writeForm")
	public String writeForm(HttpSession session) {
		System.out.println("writeForm 호출 성공");
		return "board/writeForm";
	}
	
	// 글쓰기 구현
	@RequestMapping(value = "/boardInsert", method = RequestMethod.POST)
	public String boardInsert(@ModelAttribute BoardVO bvo, Model model, HttpServletRequest request)throws IllegalStateException, IOException {
		log.info("boardInsert 호출 성공");
		
		// 확인후 주석처리 하시오
		log.info("fileName : " + bvo.getFile().getOriginalFilename());
		log.info("b_title : " + bvo.getB_title());
		
		int result = 0;
		String url = "";
		
		if(bvo.getFile()!=null) {
			String b_file = FileUploadUtil.fileUpload(bvo.getFile(), request, "board");
			bvo.setB_file(b_file);
		}
		
		result = boardService.boardInsert(bvo);
		if(result == 1) {
			url = "/board/boardList.do";
		}else {
			model.addAttribute("code", 1);
			url = "/board/writeForm.do";
		}
		return "redirect:"+url;
 	}
	
	// 글삭제 구현
	@RequestMapping(value = "/boardDelete.do")
	public String boardDelete(@ModelAttribute BoardVO bvo, HttpServletRequest request) throws IOException {
		log.info("boardDelete 호출 성공");
		log.info("page : " + bvo.getPage());
		log.info("pageSize : "+bvo.getPageSize());
		
		// 아래 변수에는 입력 성공에 대한 상태값을 담는다. (1 or 0)
		int result = 0;
		String url = "";
		
		if(!bvo.getB_file().isEmpty()) {
			FileUploadUtil.fileDelete(bvo.getB_file(), request);
		}
		
		result = boardService.boardDelete(bvo.getB_num());
		
		if(result == 1) {
			url="/board/boardList.do?page="+bvo.getPage()+"&pageSize="+bvo.getPageSize();
		}else {
			url = "/board/boardDetail.do?b_num="+bvo.getB_num()+"&page="+bvo.getPage()+"&pageSize="+bvo.getPageSize();
		}
		
		return "redirect:"+url;
	}
	// 글 상세보기 구현
	   @RequestMapping(value="/boardDetail.do", method = RequestMethod.GET)
	   public String boardDetail(@ModelAttribute BoardVO bvo, Model model) {
	      
		  
	      BoardVO detail = new BoardVO();
	      detail = boardService.boardDetail(bvo);
	      
	      if(detail != null && !(detail.equals(""))) { // 값이 있을 경우
	         // \n => <br>
	         detail.setB_content(detail.getB_content().toString().replaceAll("\n", "<br>"));
	      }
	      model.addAttribute("detail", detail);
	      
	      return "board/boardDetail";
	      
	   }
	   
	   // 비밀번호 확인
	   /* @param b_num
	    * @param b_pwd
	    * @return int로 result 0 또는 1을 리턴할 수도 있고
	    *          String으로 result 값을 "성공" 또는 "실패"를 리턴할 수도 있다
	    * @ResponseBody는 전달된 뷰를 통해서 출력하는 것이 아니라
	    * HTTP ResponseBody에 직접 출력하는 방식
	    * produes 속성은 지정한 미디어 타입과 관련된 응답을 생성하는데 사용한 실제 컨텐트 타입을 보장 */
	   @ResponseBody
	   @RequestMapping(value="/pwdConfirm", method = RequestMethod.POST, produces = "text/plain; charset=utf-8")
	   public String pwdConfirm(@ModelAttribute BoardVO bvo) {
	      
	      System.out.println("pwdConfirm 호출 성공");
	      
	      String value = "";
	      
	      // result에는 입력 성공에 대한 상태값 저장(1 or 0)
	      int result = boardService.pwdConfirm(bvo);
	      
	      if(result == 1) {
	         value = "성공";
	      } else {
	         value = "실패";
	      }
	      
	      System.out.println("result = " + result);
	      
	      return value;
	      // return value + "";
	      
	   }
	   
	   // 글 수정 폼 출력
	   /* @param : b_num
	    * @return : BoardVO */
	   @RequestMapping(value = "/updateForm.do")
	   public String updateForm(@ModelAttribute BoardVO bvo, Model model) {
	      
	      System.out.println("updateForm 호출 성공");
	      System.out.println("b_num = " + bvo.getB_num());
	      
	      BoardVO updateData = new BoardVO();
	      updateData = boardService.boardDetail(bvo);
	      
	      model.addAttribute("updateData", updateData);
	      
	      return "board/updateForm";
	      
	   }
	   
	   // 글 수정 구현
	   /* @param : BoardVO */
	   @RequestMapping(value="/boardUpdate", method = RequestMethod.POST)
	   public String boardUpdate(@ModelAttribute BoardVO bvo, HttpServletRequest request) throws IllegalStateException, IOException {
	      
	      System.out.println("boardUpdate 호출 성공");
	      
	      int result = 0;
	      String url = "";
	      String b_file="";
	      
	      if(!bvo.getFile().isEmpty()) {
	    	  log.info("------file : " + bvo.getFile().getOriginalFilename());
	    	  if(!bvo.getB_file().isEmpty()) {
	    		  FileUploadUtil.fileDelete(bvo.getB_file(), request);
	    	  }
	    	  b_file = FileUploadUtil.fileUpload(bvo.getFile(), request, "board");
	    	  bvo.setB_file(b_file);
	      }else {
	    	  log.info("첨부파일 없음");
	    	  bvo.setB_file("");
	      }
	      result = boardService.boardUpdate(bvo);
	      
	      if(result == 1) {
	         // url = "/board/boardList.do"; // 수정 후 목록으로 이동
	         
	         // 수정 후 상세 페이지로 이동
	         url = "/board/boardDetail.do?b_num=" + bvo.getB_num()+"&page="+bvo.getPage()+"&pageSize="+bvo.getPageSize();
	      }
	      return "redirect:" + url;
	   }
	   @ResponseBody
	   @RequestMapping(value = "/replyCnt.do")
	   public String replyCnt(@RequestParam("b_num") int b_num) {
		   log.info("replyCnt 호출 성공");
		   
		   int result = 0;
		   result = boardService.replyCnt(b_num);
		   log.info("댓글 갯수 : "+result+"");
		   return result+"";
	   }
	   
	   
}
