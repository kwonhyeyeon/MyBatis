package com.spring.client.board.service;

import java.util.List;

import com.spring.client.board.vo.BoardVO;

public interface BoardService {
	public List<BoardVO> boardList(); // 글목록

	public int boardInsert(BoardVO bvo); // 글입력

	public int boardDelete(int b_num); // 글삭제
	// 글 상세정보

	public BoardVO boardDetail(BoardVO bvo);

	// 비밀번호 확인
	public int pwdConfirm(BoardVO bvo);

	// 글 수정
	public int boardUpdate(BoardVO bvo);
}
