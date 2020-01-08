package com.horacewu.waimai.repository;

import com.horacewu.waimai.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

}
