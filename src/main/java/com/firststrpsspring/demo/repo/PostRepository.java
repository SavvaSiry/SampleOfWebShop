package com.firststrpsspring.demo.repo;

import com.firststrpsspring.demo.entity.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

}
