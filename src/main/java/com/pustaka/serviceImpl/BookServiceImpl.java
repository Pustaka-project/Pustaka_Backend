package com.pustaka.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pustaka.dao.BookDAO;
import com.pustaka.dao.BookUserDAO;
import com.pustaka.dto.BookDTO;
import com.pustaka.entity.BookEntity;
import com.pustaka.entity.BookUserEntity;
import com.pustaka.entity.CategoryEntity;
import com.pustaka.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookDAO bookDao;
	
	@Autowired
	private BookUserDAO bookUserDao;

	@Transactional
	public BookDTO addBook(BookDTO book, long userId) {
		BookEntity bookEntity = new BookEntity();
		bookEntity.setTitle(book.getTitle());
		bookEntity.setDescription(book.getDesc());
		bookEntity.setPrice(book.getPrice());
		bookEntity.setEdition(book.getEdition());
		bookEntity.setStatus(1);

		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.setCategoryId(book.getCategoryId());
		categoryEntity.setCategoryName(book.getCategoryName());

		bookEntity.setCategoryId(categoryEntity);

		long bookId = this.bookDao.addBook(bookEntity);
		long bookUserId = 0;
		if (bookId > 0) {
			BookUserEntity bookUserEntity = new BookUserEntity();
			bookUserEntity.setBookId(bookId);
			bookUserEntity.setStatus(1);
			bookUserEntity.setUserId(userId);

			bookUserId = bookUserDao.addMappingforBookUser(bookUserEntity);

		}
		if (bookUserId > 0 && bookId > 0) {
			book.setMessage("Book has added sucessfully");
			book.setBookId(bookId);
		} else {
			book.setMessage("unsucessfull.. pls check");
		}
		return book;

	}

	@Transactional
	public void removeBook(long bookId) {
		this.bookDao.removeBook(bookId);
	}

	
	@Override
	@Transactional
	public List<BookDTO> getBookByUserId(long categoryId,long userId,int pagenumber) {
		
		List<BookEntity> bookEntityList=bookDao.getBookListByCatogoryId(categoryId,pagenumber, 5);
		List<Long> bookIdList = bookUserDao.getUserBookMappingByUserId(userId);
		List<BookDTO> bookDTOList=new ArrayList<BookDTO>();
		for(BookEntity bookEntity:bookEntityList){
			BookDTO bookDTO=new BookDTO();
			bookDTO.setBookId(bookEntity.getBookId());
			bookDTO.setDesc(bookEntity.getDescription());
			bookDTO.setEdition(bookEntity.getEdition());
			bookDTO.setPrice(bookEntity.getPrice());
			bookDTO.setTitle(bookEntity.getTitle());
			bookDTO.setCategoryName(bookEntity.getCategoryId().getCategoryName());
			bookDTO.setCategoryId(bookEntity.getCategoryId().getCategoryId());
			
			if(bookIdList.contains(bookEntity.getBookId()))
				bookDTO.setBookOwner(1);
			else
				bookDTO.setBookOwner(0);
			
			bookDTOList.add(bookDTO);
		}
		return bookDTOList;
	}
}