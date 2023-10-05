package kr.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.entity.Board;
import kr.spring.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper mapper;
	
	@Override
	public List<Board> getList() {
		// 게시글 전체목록 가져오기 기능 
		List<Board> list = mapper.getList();
		return list;
	}

	
	
	
	
	
	
}
