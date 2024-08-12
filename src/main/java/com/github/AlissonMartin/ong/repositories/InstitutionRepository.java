package com.github.AlissonMartin.ong.repositories;

import com.github.AlissonMartin.ong.models.Institution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InstitutionRepository extends JpaRepository<Institution, Integer> {

  @Query("SELECT i FROM Institution i WHERE (:search IS NULL OR i.name LIKE %:search%)")
  Page<Institution> findInstitutionsWithFilters(@Param("search") String search, Pageable pageable);
}
