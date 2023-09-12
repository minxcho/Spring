package kr.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import kr.spring.entity.Board;

@Mapper // MyBatis가 interface를 찾기위해 달아주는 부분
public interface BoardMapper {
	
	
	public List<Board> getLists(); // 게시글 전체보기 기능
	
	
	public void boardInsert(Board board);


	public Board boardContent(int idx);


	public void boardDelete(int idx);


	public void boardUpdate(Board board);


	public void boardCount(int idx);


	
}
