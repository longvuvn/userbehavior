package com.example.userbehaviormanagement.services.Impl;

import com.example.userbehaviormanagement.entities.Category;
import com.example.userbehaviormanagement.entities.dto.CategoryDTO;
import com.example.userbehaviormanagement.repositories.CategoryRepository;
import com.example.userbehaviormanagement.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList()) ;
    }
}
