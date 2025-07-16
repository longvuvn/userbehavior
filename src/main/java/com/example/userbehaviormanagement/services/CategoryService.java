package com.example.userbehaviormanagement.services;


import com.example.userbehaviormanagement.entities.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
}
