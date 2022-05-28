package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Boards;
import com.cos.blog.model.Reply;
import com.cos.blog.model.Users;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void 글쓰기(Boards board,Users user) {
		board.setCount(0);
		board.setUsers(user);
		boardRepository.save(board);
	}
	@Transactional()
	public Page<Boards> 게시글목록보기(Pageable page, String searchText) {
	 	return boardRepository.findByTitleOrContent(searchText, page);
	 }
//	@Transactional(readOnly=true)
//	public Page<Boards> 글목록(Pageable pageable){
//		return boardRepository.findAll(pageable);
//	}
//	
	@Transactional(readOnly=true)
	public Boards 글상세보기(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
				});
	}
	
	@Transactional
	public void 글삭제하기(int id) {
		boardRepository.deleteById(id);
	}
	
	@Transactional
	public void 댓글삭제하기(int id) {
		replyRepository.deleteById(id);
	}
	
	@Transactional
	public void 글수정하기(int id,Boards requestBoard) {
		Boards board= boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다.");
		});
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		//해당함수로 종료시 트랜젝션이 종료된다.
		//이때 더티체킹 - 자동업데이트가 된다.(commit)
	}
	@Transactional
	public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {
		
		Users user = userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(() -> {
			return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다.");
		});
		
		Boards board = boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(() -> {
			return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다.");
		});
		
		Reply reply =Reply.builder()
				.user(user)
				.boards(board)
				.content(replySaveRequestDto.getContent())
				.build();
		
		replyRepository.save(reply);
		
	}
	
	
	
//	@Transactional // dto 쓰지않고 처리
//	public void 댓글쓰기(Users user,int boardId, Reply requestReply) {
//		Boards board = boardRepository.findById(boardId).orElseThrow(() -> {
//			return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다.");
//		});
//		
//		requestReply.setUser(user);
//		requestReply.setBoards(board);
//		
//		replyRepository.save(requestReply);
//	}
}
