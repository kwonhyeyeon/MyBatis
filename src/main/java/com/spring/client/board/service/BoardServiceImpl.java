package com.spring.client.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.client.board.dao.BoardDao;
import com.spring.client.board.vo.BoardVO;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao boardDao;
	
	
	// 글 목록 구현
	@Override
	public List<BoardVO> boardList() {
		// TODO Auto-generated method stub

		List<BoardVO> myList = null;
		myList = boardDao.boardList();
		return myList;
	}
	
	// 글 목록수 아직 미구현 - 페이징처리에서 할듯?
	/*
	public int boardListCnt(BoardVO bvo){
		return boardDao.boardListCnt(bvo);
	} */
	 
	// 글입력 구현
	@Override
	public int boardInsert(BoardVO bvo) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = boardDao.boardInsert(bvo);
		}catch(Exception e) {
			e.printStackTrace();
			result = 0;
		}
		return result;
		
	}
	// 글삭제 구현
	@Override
	public int boardDelete(int b_num) {
		// TODO Auto-generated method stub

		int result = 0;
		try {
			result = boardDao.boardDelete(b_num);
		}catch(Exception e) {
			e.printStackTrace();
			result = 0;
		}
		return result;
		
	}
	// 글 상세 구현
	   @Override
	   public BoardVO boardDetail(BoardVO bvo) {
	      BoardVO detail = null;
	      detail = boardDao.boardDetail(bvo);
	      
	      return detail;
	   }

	   // 비밀번호 확인 구현
	   @Override
	   public int pwdConfirm(BoardVO bvo) {
	      int result = 0;
	      result = boardDao.pwdConfirm(bvo);
	      
	      return result;
	   }

	   // 글 수정 구현
	   @Override
	   public int boardUpdate(BoardVO bvo) {
	      int result = 0;
	      
	      try {
	         result = boardDao.boardUpdate(bvo);
	      } catch (Exception e) {
	         e.printStackTrace();
	         result = 0;
	      }
	      
	      return result;
	   }
	
}
