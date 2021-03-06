package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Boards;
import com.cos.blog.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Boards board,@AuthenticationPrincipal PrincipalDetail principal){
		//@AuthenticationPrincipal << 로그인한 사용자 정보를 받아올수있음
		boardService.글쓰기(board,principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}

	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		boardService.글삭제하기(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@DeleteMapping("/api/board/{id}/reply")
	public ResponseDto<Integer> replyDelete(@PathVariable int id){
		boardService.댓글삭제하기(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id,@RequestBody Boards board){
		boardService.글수정하기(id,board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto){
		
		boardService.댓글쓰기(replySaveRequestDto);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	
	
	
//	//데이터를 받을때 컨트롤러에서 dto를 만들어서 받는게 좋다.
//	//dto 를 사용하지 않는 이유는 작은 프로젝트에서 굳이?
//	@PostMapping("/api/board/{boardId}/reply")
//	public ResponseDto<Integer> replySave(@PathVariable int boardId,@RequestBody Reply reply,@AuthenticationPrincipal PrincipalDetail principal){
//		//@AuthenticationPrincipal << 로그인한 사용자 정보를 받아올수있음
//		
//		
//		boardService.댓글쓰기(principal.getUser(),boardId,reply);
//		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
//	}
}
