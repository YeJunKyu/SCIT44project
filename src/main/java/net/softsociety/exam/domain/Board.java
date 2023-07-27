package net.softsociety.exam.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 게시글 정보
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Board {
	int boardnum;
	String memberid;
	String title;
	String contents;
	
	String inputdate;
	String category;
	String soldout;
	String buyerid;
	
}
