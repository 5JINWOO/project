package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SequenceGenerator(
		name ="USER_SEQ_GENERATOR3"
		, sequenceName ="USER_SEQ3"
		, initialValue = 1
		, allocationSize =1
	)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {
	@Id //primary key
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_SEQ_GENERATOR3")
	//프로젝트에 연결된 DB의 넘버링 전략을 사용
	private int id;
	
	@Column(nullable=false ,length=200)
	private String content;
	
	@ManyToOne// 여러개의 답변은 하나의 게시글에 존재
	@JoinColumn(name="boardsId")
	private Boards boards;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private Users user;
	
	@CreationTimestamp
	private Timestamp createDate;
}
