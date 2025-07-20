package com.example.userbehaviormanagement.services;

import com.example.userbehaviormanagement.entities.Pagination;
import com.example.userbehaviormanagement.entities.dto.ProductDTO;

public interface ProductService {
    Pagination<ProductDTO> getAllProducts(int page, int size);

    ProductDTO getProductById(String id);

    ProductDTO createProduct(ProductDTO productDTO);

    ProductDTO updateProduct(String id, ProductDTO productDTO);

    void deleteProduct(String id);

    ProductDTO updateTotalRating(String productId);
}
