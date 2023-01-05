package com.actually.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.actually.model.DataModelOne;

public interface DataRepo extends JpaRepository<DataModelOne, Integer>{

}
