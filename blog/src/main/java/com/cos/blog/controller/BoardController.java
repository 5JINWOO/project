package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.cos.blog.model.Boards;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardRepository boardRepository;
	
	@GetMapping({"","/"})
	public String index() {
		return "index";
	}
	
	
	
	@GetMapping({"/board/list"})
	public String boardList(Model model,@PageableDefault(page=0, size=5, sort="id",direction = Sort.Direction.DESC) Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String searchText) {
		
//		Page <Boards> list = boardService.글목록(pageable);
//		
//		model.addAttribute("boards",list);
		Page<Boards> boardsEntity = boardRepository.findByTitleOrContent(searchText, pageable);
	 	int startPage = Math.max(1, boardsEntity.getPageable().getPageNumber() - 6);
	 	int endPage = Math.min(boardsEntity.getTotalPages(), boardsEntity.getPageable().getPageNumber() + 6);
	 	int nowPage = boardsEntity.getPageable().getPageNumber() + 1;
	 	model.addAttribute("startPage", startPage);
	 	model.addAttribute("endPage", endPage);
	 	model.addAttribute("nowPage", nowPage);
	 	
	 	model.addAttribute("boardsEntity", boardService.게시글목록보기(pageable, searchText));
	 	
		
		return "board/list";
	}
	
	@GetMapping({"/board/Form"})
	public String saveForm() {
		return "board/saveForm";
	}
	
	//글상세보기
	@GetMapping("/board/{id}")
	public String findByID(@PathVariable int id, Model model) {
		model.addAttribute("board",boardService.글상세보기(id));
		return "board/detail";
	}
	
	//글 수정하기
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board",boardService.글상세보기(id));
		return "board/updateForm";
	}
	
	
}
