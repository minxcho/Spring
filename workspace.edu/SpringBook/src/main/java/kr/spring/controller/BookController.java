package kr.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.entity.Book;
import kr.spring.mapper.BookMapper;

@Controller
public class BookController {
   
   @Autowired
   private BookMapper mapper; 
   
   @RequestMapping("/") 
   public String home(){
      System.out.println("홈 기능 수행");
      return "main";
   }
   
   

}