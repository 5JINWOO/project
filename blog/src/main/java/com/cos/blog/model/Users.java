package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@SequenceGenerator(
		name ="USER_SEQ_GENERATOR"
		, sequenceName ="USER_SEQ"
		, initialValue = 1
		, allocationSize =1
	)
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity//User 클래스가 자동으로 DB에 테이블이 생성이 된다.
@DynamicInsert //insert시에 null 인필드제외
public class Users {
	@Id //primary key
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_SEQ_GENERATOR")
	//프로젝트에 연결된 DB의 넘버링 전략을 사용
	private int id;
	
	@Column(nullable=false ,length=100, unique =true)
	private String username;
	
	@Column(nullable=false ,length=100)
	private String password;
	
	@Column(nullable=false ,length=50)
	private String email;
	
	//@ColumDefault("'user'")
	@Enumerated(EnumType.STRING)
	private RoleType roles;//Enum 을 쓰는게 좋다. // 도메인을 사용해야되서.
	//예 admin, user, manager(권한) 셋 중 하나만
	
	private String oauth; //kakao , google 
	
	@CreationTimestamp
	private Timestamp createDate;
}
