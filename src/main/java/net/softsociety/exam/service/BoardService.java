package net.softsociety.exam.service;

import java.util.ArrayList;

import net.softsociety.exam.domain.Board;
import net.softsociety.exam.domain.Reply;

public interface BoardService {

	ArrayList<Board> selectAll(Board board);

	int write(Board board);

	Board selectOne(int boardnum);

	int buy(Board board);

	int delete(Board board);

	ArrayList<Board> selectSearch(String category);

	ArrayList<Board> selectAllB();

	ArrayList<Board> selectSearchB(String category, String searchWord);



}
