package com.spring.client.board.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.client.board.controller.BoardController;
import com.spring.client.board.dao.BoardDao;
import com.spring.client.board.vo.BoardVO;
import com.spring.client.reply.dao.ReplyDao;
import com.spring.client.reply.vo.ReplyVO;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao boardDao;
	private Logger log = LoggerFactory.getLogger(BoardController.class);
	@Autowired
	private ReplyDao replyDao;
	
	// 삭제전 댓글 갯수 구하기
	@Override
	public int replyCnt(int b_num) {
		// TODO Auto-generated method stub
		int result = 0;
		List<ReplyVO> list = replyDao.replyList(b_num);
		if(!list.isEmpty()) {
			result = list.size();
		}else {
			result = 0;
		}
		return result;
	}

	// 글 목록 구현
	@Override
	public List<BoardVO> boardList(BoardVO bvo) {
		// TODO Auto-generated method stub

		List<BoardVO> myList = null;
		// 정렬에 대한 기본값 설정
		if (bvo.getOrder_by() == null)
			bvo.setOrder_by("b_num");
		if (bvo.getOrder_sc() == null)
			bvo.setOrder_sc("DESC");
		myList = boardDao.boardList(bvo);
		return myList;
	}

	// 글입력 구현
	@Override
	public int boardInsert(BoardVO bvo) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = boardDao.boardInsert(bvo);
		} catch (Exception e) {
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
		} catch (Exception e) {
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
	// 페이징 관련 전체글의 갯수를 얻어옴
	@Override
	public int boardListCnt(BoardVO bvo) {
		// TODO Auto-generated method stub
		
		return boardDao.boardListCnt(bvo);
	}

}
