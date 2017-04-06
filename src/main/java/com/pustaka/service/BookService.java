package com.pustaka.service;
import java.util.List;

import com.pustaka.dto.BookDTO;


public interface BookService 
{
	public BookDTO addBook(BookDTO book, long userId);
	public void removeBook(long bookId);
    public List<BookDTO> getBookByUserId(long categoryId,long userId,int pagenumber);
}
