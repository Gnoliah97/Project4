package com.sem4project.sem4.service.impl;

import com.sem4project.sem4.dto.dtomodel.ProductDto;
import com.sem4project.sem4.entity.Category;
import com.sem4project.sem4.entity.District;
import com.sem4project.sem4.entity.Product;
import com.sem4project.sem4.entity.Province;
import com.sem4project.sem4.exception.ResourceNotFoundException;
import com.sem4project.sem4.exception.UpdateResourceException;
import com.sem4project.sem4.mapper.ProductMapper;
import com.sem4project.sem4.repository.ProductRepository;
import com.sem4project.sem4.service.ProductService;
import com.sem4project.sem4.service.utils.ServiceUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    @Override
    public ProductDto getById(UUID id) {
        try {
            Product product = productRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            return productMapper.toDto(product);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Product with id = " + id + " not found");
        }
    }

    @Override
    public List<ProductDto> getAll(Boolean isDisable, Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        try {
            if(isDisable != null && isDisable){
                return getAllAvailable(pageNumber, pageSize, sortBy, sortType);
            }
            List<Product> products = ServiceUtil.getAll(productRepository, isDisable, pageNumber, pageSize, sortBy, sortType);

            return products.stream().map(productMapper::toDto).toList();
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Get products failed");
        }
    }

    @Override
    public List<ProductDto> getAllAvailable(Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        try {
            List<Product> products = ServiceUtil.getAllAvailable(productRepository, pageNumber, pageSize, sortBy, sortType);
            return productMapper.toListDto(products);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Get products failed");
        }
    }

    @Override
    public ProductDto create(ProductDto productDto) {
        try {
            Product product = productMapper.toEntity(productDto);
            Product createdProduct = productRepository.save(product);
//            productRepository.refresh(createdProduct);
            return productMapper.toDto(createdProduct);
        } catch (OptimisticEntityLockException e) {
            throw new UpdateResourceException("Can not create new product");
        }
    }

    @Override
    public ProductDto update(UUID id, ProductDto productDto) {
        try {
            Product product = productRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            productMapper.transferToEntity(product, productDto);
            Product updatedProduct = productRepository.save(product);
//            productRepository.refresh(updatedProduct);
            return productMapper.toDto(updatedProduct);
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Product with id = " + id + " not found");
        } catch (OptimisticEntityLockException ex){
            throw new UpdateResourceException("Update product failed");
        }
    }

    @Override
    public void updateDisable(UUID id) {
        try {
            Product product = productRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            product.setDisable(!product.isDisable());
            productRepository.save(product);
        } catch (IllegalArgumentException | EntityNotFoundException ex) {
            throw new ResourceNotFoundException("Product with id = " + id + " not found");
        } catch (OptimisticEntityLockException e) {
            throw new UpdateResourceException("Can not update product");
        }
    }
}
