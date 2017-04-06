package com.pustaka.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pustaka.dao.BookDAO;
import com.pustaka.dto.BookDTO;
import com.pustaka.entity.BookEntity;
import com.pustaka.entity.CategoryEntity;

@Repository
public class BookDAOImpl implements BookDAO {

	private static final Logger logger = LoggerFactory.getLogger(BookDAOImpl.class);

	@Autowired
	private SessionFactory sessionfactory;

	@Override
	public long addBook(BookEntity book) {
		Session session = this.sessionfactory.getCurrentSession();
		return (long) session.save(book);
	}

	@Override
	public void removeBook(long bookId) {
		Session session = this.sessionfactory.getCurrentSession();
		BookDTO book = (BookDTO) session.load(BookDTO.class, new Long(bookId));
		if (null != book) {
			session.delete(book);
		}
		logger.info("book deleted successfully, book details= " + book);
	}

	@Override
	public List<BookEntity> getBookListByCatogoryId(long catogoryId, int pagenumber, int itemsperpage) {
		Session session = sessionfactory.getCurrentSession();
		Criterion catogoryCriteria = Restrictions.eq("categoryId.categoryid", catogoryId);
		Criterion statusCriteria = Restrictions.eq("status", 1);

		Criteria cr = session.createCriteria(BookEntity.class).add(catogoryCriteria).add(statusCriteria);
		cr.setFirstResult((pagenumber - 1) * itemsperpage);
		cr.setMaxResults(itemsperpage);
		return cr.list();

	}

}
