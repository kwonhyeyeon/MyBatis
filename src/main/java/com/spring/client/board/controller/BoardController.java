package com.spring.client.board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.client.board.service.BoardService;
import com.spring.client.board.vo.BoardVO;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	// 로그를 굳이 쓰고싶다면..
	private Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	// 글목록
	@RequestMapping(value = "/boardList.do", method = RequestMethod.GET)
	public String boardList(Model model) {
		log.info("boardList 호출 성공");
		
		List<BoardVO> boardList = boardService.boardList();
		model.addAttribute("boardList", boardList);
	// 	model.addAttribute("data", bvo);
		
		return "board/boardList";
	}
	
	// 글쓰기폼 출력
	@RequestMapping(value = "/writeForm.do")
	public String writeForm() {
		System.out.println("writeForm 호출 성공");
		return "board/writeForm";
	}
	
	// 글쓰기 구현
	@RequestMapping(value = "/boardInsert.do", method = RequestMethod.POST)
	public String boardInsert(@ModelAttribute BoardVO bvo, Model model) {
		log.info("boardInsert 호출 성공");
		
		int result = 0;
		String url = "";
		
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
	public String boardDelete(@ModelAttribute BoardVO bvo) {
		log.info("boardDelete 호출 성공");
		
		// 아래 변수에는 입력 성공에 대한 상태값을 담는다. (1 or 0)
		int result = 0;
		String url = "";
		
		result = boardService.boardDelete(bvo.getB_num());
		
		if(result == 1) {
			url="/board/boardList.do";
		}else {
			url = "/board/boardDetail.do?b_num="+bvo.getB_num();
		}
		
		return "redirect:"+url;
	}
	// 글 상세보기 구현
	   @RequestMapping(value="/boardDetail.do", method = RequestMethod.GET)
	   public String boardDetail(@ModelAttribute BoardVO bvo, Model model) {
	      
	      System.out.println("boardDetail 호출 성공");
	      System.out.println("b_num = " + bvo.getB_num());
	      
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
	   @RequestMapping(value="/pwdConfirm.do", method = RequestMethod.POST, produces = "text/plain; charset=utf-8")
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
	   @RequestMapping(value="/boardUpdate.do", method = RequestMethod.POST)
	   public String boardUpdate(@ModelAttribute BoardVO bvo) {
	      
	      System.out.println("boardUpdate 호출 성공");
	      
	      int result = 0;
	      String url = "";
	      
	      result = boardService.boardUpdate(bvo);
	      
	      if(result == 1) {
	         // url = "/board/boardList.do"; // 수정 후 목록으로 이동
	         
	         // 수정 후 상세 페이지로 이동
	         url = "/board/boardDetail.do?b_num=" + bvo.getB_num();
	      } else {
	         // 해당 글의 수정 폼
	         url = "/board/updateForm.do?b_num=" + bvo.getB_num();
	      }
	      
	      return "redirect:" + url;
	      
	   }
}
