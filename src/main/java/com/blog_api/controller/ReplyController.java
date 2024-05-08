package com.blog_api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog_api.entities.Reply;
import com.blog_api.services.ReplyService;

@RestController
@RequestMapping("/replies")
@CrossOrigin(origins = "http://localhost:3000")
public class ReplyController {

	ReplyService replyService=new ReplyService();
	
	@PostMapping("/")
	public void addReply(@RequestBody Reply reply) {
		replyService.addReply(reply);
	}
	
	@PutMapping("/")
	public void updateReply(@RequestBody Reply reply) {
		replyService.updateReply(reply);
	}
	
	@DeleteMapping("/{reply_id}")
	public void deleteReply(@PathVariable("reply_id") int id) {
		replyService.deleteReply(id);
	}
}
