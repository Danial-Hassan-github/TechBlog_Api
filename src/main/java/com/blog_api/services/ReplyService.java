package com.blog_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.blog_api.entities.Reply;
import com.blog_api.repositories.ReplyRepository;

@Service
public class ReplyService {

	@Autowired
	public static ApplicationContext applicationContext;
	ReplyRepository replyRepository;
	public void addReply(Reply reply) {
		replyRepository=applicationContext.getBean(ReplyRepository.class);
		replyRepository.save(reply);
	}
	
	public void updateReply(Reply reply) {
		replyRepository=applicationContext.getBean(ReplyRepository.class);
		replyRepository.save(reply);
	}
	
	public void deleteReply(int id) {
		replyRepository=applicationContext.getBean(ReplyRepository.class);
		replyRepository.deleteById(id);
	}
	
}