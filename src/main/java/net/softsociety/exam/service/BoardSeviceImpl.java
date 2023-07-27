package net.softsociety.exam.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.softsociety.exam.dao.BoardDAO;
import net.softsociety.exam.domain.Board;
import net.softsociety.exam.domain.Reply;


@Transactional
@Service
public class BoardSeviceImpl implements BoardService {
	
	@Autowired
	BoardDAO dao;
	
	@Override
	public ArrayList<Board> selectAll(Board board) {
		ArrayList<Board> list = dao.selectAll(board);
		return list;
	}

	@Override
	public int write(Board board) {
		int n = dao.write(board);
		return n;
	}

	@Override
	public Board selectOne(int boardnum) {
		Board board = dao.selectOne(boardnum);
		return board;
	}

	@Override
	public int buy(Board board) {
		int n = dao.buy(board);
		return n;
	}

	@Override
	public int delete(Board board) {
		int n = dao.delete(board);
		return n;
	}

	@Override
	public ArrayList<Board> selectSearch(String category) {
		ArrayList<Board> list = dao.selectSearch(category);
		return list;
	}

	@Override
	public ArrayList<Board> selectAllB() {
		// TODO Auto-generated method stub
		return dao.selectAllB();
	}

	@Override
	public ArrayList<Board> selectSearchB(String category, String searchWord) {
		HashMap<String, String> map = new HashMap<>();
		map.put("category", category);
		map.put("searchWord", searchWord);
		
		ArrayList<Board> list = dao.selectSearchB(map);
		return list;
	}

	

}
