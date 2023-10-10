package kr.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import kr.spring.entity.Book;

@Mapper // MyBatis가 interface를 찾기위해 달아주는 부분
public interface BookMapper {
	
	
	public List<Book> getLists(); // 게시글 전체보기 기능
	
	
	public void boardInsert(Book book);

	
	public void boardDelete(int num);


	public void boardUpdate(Book book);


	
}
