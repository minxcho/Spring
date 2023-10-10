package kr.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.spring.entity.Book;
import kr.spring.mapper.BookMapper;

@RequestMapping("/book")
@RestController
public class BookRestController {
   

   @Autowired
   private BookMapper mapper; 
   
   
   @GetMapping("/all")
   public List<Book> bookList() {
      System.out.println("게시글 전체 보기 기능 수행");
      List<Book> list = mapper.getLists();
      System.out.println(list.size());
      return list;
   }
   
   @PostMapping("/new")
   public void boardInsert(Book book) {
      System.out.println("도서 등록 기능 수행");
      mapper.boardInsert(book);
   }
   

   @DeleteMapping("/{num}")
   public void boardDelete(@PathVariable("num") int num) {
      System.out.println("게시글 삭제 기능 수행");
      mapper.boardDelete(num);
   }
   
   @PostMapping("/update")
   public void boardUpdate(@RequestBody Book vo) {
      mapper.boardUpdate(vo);
   }

   
}