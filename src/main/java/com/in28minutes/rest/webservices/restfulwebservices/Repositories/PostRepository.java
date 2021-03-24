package com.in28minutes.rest.webservices.restfulwebservices.Repositories;

import com.in28minutes.rest.webservices.restfulwebservices.Beans.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
