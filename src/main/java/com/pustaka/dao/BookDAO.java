package com.pustaka.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pustaka.entity.BookEntity;

@Repository
public interface BookDAO {
	public long addBook(BookEntity book);

	public void removeBook(long bookId);
	
	public List<BookEntity> getBookListByCatogoryId(long catogoryId, int pagenumber, int itemsperpage);
}


