package com.spring.admin.member.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.admin.member.service.AdminMemberService;
import com.spring.client.member.vo.MemberVO;
/*import com.spring.admin.member.service.MemberVO;*/
import com.spring.common.graph.ChartMake;

@Controller
@RequestMapping(value="/admin")
public class AdminMemberController {
	private Logger log = LoggerFactory.getLogger(AdminMemberController.class);

	@Autowired
	private AdminMemberService adminMemberService;
	
	/***************************************
	 *  회원 리스트 구현하기
	 ***************************************/
	
	@RequestMapping(value="/member/memberList", method = RequestMethod.GET)
	public String memberList(@ModelAttribute MemberVO mvo, Model model, HttpServletRequest request) {
		log.info("memberList 호출 성공");
		
		List<MemberVO> memberList = adminMemberService.memberList(mvo);
		// 연령대별 데이터 
		Map<String,Integer> memberAgeList = adminMemberService.memberAgeList();
		ChartMake.pieChart(request, memberAgeList);
		// 성별 데이터
		Map<String,Integer> memberGenderList = adminMemberService.memberGenderList();
		ChartMake.barChart(request, memberGenderList);
		
		model.addAttribute("memberList", memberList);
		
		return "admin/member/memberList";
	}
}
