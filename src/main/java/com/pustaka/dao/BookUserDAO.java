package com.pustaka.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pustaka.entity.BookUserEntity;

@Repository
public interface BookUserDAO 
{
	public long addMappingforBookUser(BookUserEntity bookUser);
	public List<Long> getUserBookMappingByUserId(long userId);
	

}
