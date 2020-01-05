package com.example.demo.repository;

import com.example.demo.model.Level;
import com.example.demo.model.Resource;
import com.example.demo.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    Resource findResourceById(Long id);

    Resource[] findAllByLevel(Level level);

    Resource[] findResourceByType(Type type);

    @Query("SELECT r FROM Resource r WHERE r.level = ?1 AND r.type = ?2 AND r.id NOT IN ?3")
    List<Resource> findByLevelAndType(Level level, Type type, List<Long> id);

    @Query("SELECT r FROM Resource r WHERE r.level = ?1 AND r.type = ?2")
    List<Resource> findByLevelAndType(Level level, Type type);


    Integer countByLevel(Level level);
}
