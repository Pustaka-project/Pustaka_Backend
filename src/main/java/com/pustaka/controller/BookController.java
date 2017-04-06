package com.pustaka.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pustaka.dto.BookDTO;
import com.pustaka.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
 
	@Autowired
    BookService bookService;
 
    @RequestMapping(value = "/addBook/{userId}", method = RequestMethod.POST)
    public BookDTO addBook(@RequestBody BookDTO book, HttpServletRequest request,
			HttpServletResponse response,@PathVariable("userId") long userId ){
    	BookDTO bookDTO = null;
    	try {
    		bookDTO = bookService.addBook(book, userId);
    	}
    	 catch (Exception e) {
 			e.printStackTrace();
 		}
		return bookDTO;
    }
  
    @RequestMapping(value = "/deleteBook/{bookId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void removeBook(@PathVariable("bookId") long bookId) 
    {
      bookService.removeBook(bookId);  
    } 
    
    @RequestMapping(value = "/getbooklist/{userId}/{categoryId}/{pagenumber}", method = RequestMethod.GET)
	public List<BookDTO> getBookList( HttpServletRequest request,
			HttpServletResponse response,@PathVariable("userId") long userId, @PathVariable("categoryId") long categoryId, @PathVariable("pagenumber") int pagenumber) {
         
		List<BookDTO> result=null;
		
		try {
			result = bookService.getBookByUserId(categoryId, userId, pagenumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
    }
}