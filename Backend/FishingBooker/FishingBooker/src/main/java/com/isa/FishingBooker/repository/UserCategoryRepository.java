package com.isa.FishingBooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.FishingBooker.model.UserCategory;

public interface UserCategoryRepository extends JpaRepository<UserCategory, Integer>{

}
