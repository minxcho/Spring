package kr.spring.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor // 없어도 댐. 킹치만 NoArgs는 필수.!
@NoArgsConstructor  // 완전 필수 !  
@Data // getter,setter
public class Board {

	private int idx;
	private String memID;
	private String title;
	private String content;
	private String writer;
	private Date indate;
	private int count;
	
	private int boardGroup;
	private int boardSequence;
	private int boardLevel;
	private int boardAvailable;
	
	
	
	
	
}
