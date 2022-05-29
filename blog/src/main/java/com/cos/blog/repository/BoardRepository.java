package com.cos.blog.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.Boards;

public interface BoardRepository extends JpaRepository<Boards,Integer>{
	
	@Query(value = "SELECT * FROM boards WHERE title LIKE %:searchText% or content LIKE %:searchText%", nativeQuery = true)
 	Page<Boards> findByTitleOrContent(String searchText, Pageable page);

	@Modifying
	@Query("update Boards p set p.count = p.count + 1 where p.id = :id")
	int updateCount(int id);
}
