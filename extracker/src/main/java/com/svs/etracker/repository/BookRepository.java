package com.svs.etracker.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.svs.etracker.model.Book;
import com.svs.etracker.model.Category;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

}

