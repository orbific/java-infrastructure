package com.riddlefox.greeting;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EntryRepository extends CrudRepository<Entry, Long> {

    List<Entry> findAll();
    Entry save(Entry e);
}
