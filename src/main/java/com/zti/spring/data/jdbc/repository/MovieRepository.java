package com.zti.spring.data.jdbc.repository;

import com.zti.spring.data.jdbc.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public
interface MovieRepository extends CrudRepository<Movie, Long> {

}