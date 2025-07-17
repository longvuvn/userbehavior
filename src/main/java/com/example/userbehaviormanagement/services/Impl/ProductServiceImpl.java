package com.example.userbehaviormanagement.services.Impl;

import com.example.userbehaviormanagement.entities.Category;
import com.example.userbehaviormanagement.entities.Pagination;
import com.example.userbehaviormanagement.entities.Product;
import com.example.userbehaviormanagement.entities.dto.ProductDTO;
import com.example.userbehaviormanagement.enums.ProductStatus;
import com.example.userbehaviormanagement.repositories.CategoryRepository;
import com.example.userbehaviormanagement.repositories.ProductRepository;
import com.example.userbehaviormanagement.repositories.ReviewRepository;
import com.example.userbehaviormanagement.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Pagination<ProductDTO> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageable);

        List<ProductDTO> products = productPage.getContent()
                .stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());

        Pagination<ProductDTO> pagination = new Pagination<>();
        pagination.setSize(productPage.getNumber());
        pagination.setSize(productPage.getSize());
        pagination.setTotalPages(productPage.getTotalPages());
        pagination.setTotalElements(productPage.getTotalElements());
        pagination.setContent(products);
        return pagination;
    }

    @Override
    public ProductDTO getProductById(String id) {
        UUID productId = UUID.fromString(id);
        Optional<Product> productOptional = productRepository.findById(productId);
        return productOptional.map(product -> modelMapper.map(product, ProductDTO.class))
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Category category = categoryRepository.findByName(productDTO.getCategoryName());
        product.setCategory(category);
        product.setStatus(ProductStatus.InStock);
        product.setTotalReviews(0);
        product.setAverageRating(0.0);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        UUID productId = UUID.fromString(id);
        Category category = categoryRepository.findByName(productDTO.getCategoryName());
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        modelMapper.map(productDTO, existingProduct);
        existingProduct.setCategory(category);
        Product updatedProduct = productRepository.save(existingProduct);
        return modelMapper.map(updatedProduct, ProductDTO.class);
    }

    @Override
    public void deleteProduct(String id) {
        UUID productId = UUID.fromString(id);
        Product existingProduct = productRepository.findById(productId)
                        .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        productRepository.delete(existingProduct);
    }

    @Override
    public ProductDTO updateTotalRating(String productId) {
        UUID productUUID = UUID.fromString(productId);
        Product existingProduct = productRepository.findById(productUUID)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productUUID));
       int totalReviews =reviewRepository.countByProductId(productUUID);
       int totalRating = reviewRepository.sumRatingByProductId(productUUID);
       existingProduct.setTotalReviews(totalReviews);
       existingProduct.setAverageRating(totalReviews == 0 ? 0.0 : (double) totalRating / totalReviews);
       Product updatedProduct = productRepository.save(existingProduct);
       return modelMapper.map(updatedProduct, ProductDTO.class);
    }

}
