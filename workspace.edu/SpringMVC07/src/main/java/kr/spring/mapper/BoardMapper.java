package kr.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.spirng.entity.Board;

@Mapper
public interface BoardMapper {

	public List<Board> getList();
	
	
	
	
	
	
}
