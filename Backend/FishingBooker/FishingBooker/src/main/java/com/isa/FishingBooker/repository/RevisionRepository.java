package com.isa.FishingBooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.FishingBooker.model.Revision;

@Repository
public interface RevisionRepository extends JpaRepository<Revision, Integer>{

}
