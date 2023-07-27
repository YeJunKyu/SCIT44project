package net.softsociety.exam.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.exam.domain.Board;
import net.softsociety.exam.domain.Reply;
import net.softsociety.exam.service.BoardService;

/**
 * 상품게시판 관련 콘트롤러
 */
@Slf4j
@RequestMapping("board")
@Controller
public class BoardController {
	
	@Autowired
	BoardService service;
	
	@GetMapping("selectAllGet")
	public String selectAll(Model model,
			Board board
			,@RequestParam(name="page",defaultValue = "1") int page)
	{	log.debug("검색대상:{}",board);
		
//		PageNavigator navi = service.getPageNavigator(pagePerGroup,countPerPage, page, type, searchWord);
		
		ArrayList<Board> list = service.selectAll(board);
		
		log.debug("{}",list);
//		model.addAttribute("list",list);
//		model.addAttribute("navi",navi);
//		model.addAttribute("type", type);
	    model.addAttribute("list", list);
		return "boardView/board";
	}
	
	@GetMapping("selectSearchGet")
	public String selectSearchA() {
		return "boardView/boardsearch";
	}
	
//	판매정보검색글
	@ResponseBody
	@GetMapping("selectSearchGetA")
	public ArrayList<Board> selectSearch(String category)
	{	
		
//		PageNavigator navi = service.getPageNavigator(pagePerGroup,countPerPage, page, type, searchWord);
		
		ArrayList<Board> list = service.selectSearch(category);
		
		log.debug("{}",list);
//		
		return list;
	}
	
	@ResponseBody
	@GetMapping("selectAll")
	public ArrayList<Board> selectAllB(){
		
		return service.selectAllB();
	}
	
	
	@ResponseBody
	@GetMapping("selectSearchGetB")
	public ArrayList<Board> selectSearchB(String category,
			String searchWord)
	{	
		
//		PageNavigator navi = service.getPageNavigator(pagePerGroup,countPerPage, page, type, searchWord);
		log.debug("b카테:{},검색어:{}",category,searchWord);
		ArrayList<Board> list = service.selectSearchB(category,searchWord);
		
		log.debug("{}",list);
//		
		return list;
	}
	
	//글쓰기
		@GetMapping("writeGet")
		public String write() {
			
			
			return "boardView/write";
		}
		
		@PostMapping("writePost")
		public String write(@AuthenticationPrincipal UserDetails user
				,
				Board board
				) {
			
			
			
			log.debug("저장할글정보:{}",board);
			String memberId = user.getUsername();
			board.setMemberid(memberId);
			log.debug("writePost:{}",board);
			int n = service.write(board);
			log.debug("writePost:{}",board);
			return "redirect:/board/selectAllGet";
		}
		
		
		//해당링크 글보여주기
		@GetMapping("readGet")
		public String read(@RequestParam(name="boardnum",
		defaultValue = "0")int boardnum,
				Board board,
				Reply reply,
				Model model) {
			board = service.selectOne(boardnum);
			log.debug("replyfirst:{}",reply);
//			ArrayList<Reply> replylist = service.selectReply(board.getBoardnum());
			
//			log.debug("replymiddle:{}",replylist);
			if(board == null) {
				return "redirect:/board/selectAllGet";
			}
			model.addAttribute("list",board);
//			model.addAttribute("reply",replylist);
//			log.debug("replylast:{}",replylist);
			return "boardView/readForm";
		}
		
		@PostMapping("buyPost")
		public String buy(@RequestParam(name="boardnum",
				defaultValue = "0")int boardnum,
				@AuthenticationPrincipal UserDetails user
				) {
			log.debug("구매전번호:{}",boardnum);
			String memberId = user.getUsername();
			Board board = new Board();
			board.setMemberid(memberId);
			board.setBoardnum(boardnum);
			log.debug("구매전:{}",board);
			int n = service.buy(board);
			log.debug("구매후:{}",board);
			return "redirect:/board/selectAllGet";
		}
		
//		글삭제
		@PostMapping("deletePost")
		public String delete(@RequestParam(name="boardnum",
				defaultValue = "0") int boardnum,
				@AuthenticationPrincipal UserDetails user) {
			Board board = service.selectOne(boardnum);
			log.debug("삭제first:{}",board);
//			board.setBoardnum(boardnum);
			board.setMemberid(user.getUsername());
			log.debug("삭제:{}",boardnum);
			
			int n = service.delete(board);
			log.debug("삭제:{}",board);
			return "redirect:/board/selectAllGet";
		}
		
		
		
}
