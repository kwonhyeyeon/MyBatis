package com.spring.client.reply.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.client.reply.dao.ReplyDao;
import com.spring.client.reply.vo.ReplyVO;
@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {
	private Logger log = LoggerFactory.getLogger(ReplyServiceImpl.class);
	
	@Autowired
	private ReplyDao replyDao;
	
	// 글목록 구현
	@Override
	public List<ReplyVO> replyList(Integer b_num) {
		// TODO Auto-generated method stub
		List<ReplyVO> myList = null;
		myList =replyDao.replyList(b_num);
		return myList;
	}
	
	
	// 글입력 구현
	@Override
	public int replyInsert(ReplyVO rvo) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = replyDao.replyInsert(rvo);
		}catch(Exception e) {
			e.printStackTrace();
			result = 0;
		}
		
		return result;
	}
	
	//비밀번호 체크
	@Override
	public int pwdConfirm(ReplyVO rvo) {
		// TODO Auto-generated method stub
		int result = 0;
		result = replyDao.pwdConfirm(rvo);
		return result;
	}
	// 글수정 구현
	@Override
	public int replyUpdate(ReplyVO rvo) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = replyDao.replyUpdate(rvo);
		}catch(Exception e) {
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	// 글 삭제 구현
	@Override
	public int replyDelete(int r_num) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = replyDao.replyDelete(r_num);
		}catch(Exception e) {
			e.printStackTrace();
			result =0;
		}
			return result;
	}

}
