package kr.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.entity.Board;
import kr.spring.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardRepository boardRepository; // mapper같은 녀석
	
	
	@Override
	public List<Board> getList() {
		List<Board> list = boardRepository.findAll();
		return list;
	}

	@Override
	public void register(Board vo) {
		boardRepository.save(vo);
	}

	@Override
	public Board get(Long idx) {
		Optional<Board> vo = boardRepository.findById(idx);
		return vo.get();
	}

	@Override
	public void delete(Long idx) {
		boardRepository.deleteById(idx); // delete조심
		
		
	}

	@Override
	public void update(Board vo) {
		boardRepository.save(vo); 
		// JPA의 save 기능은 
		// 새로운 pk값 또는 없는 값이 들어온다면 insert 기능을 하고,
		// 기존에 존재하는 pk값이 들어온다면 update 기능을 한다.
	}
	
	
	
	
	
	
}
