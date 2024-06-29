package com.dekankilic.product.service;

import com.dekankilic.product.dto.ProductPurchaseRequest;
import com.dekankilic.product.dto.ProductPurchaseResponse;
import com.dekankilic.product.dto.ProductRequest;
import com.dekankilic.product.dto.ProductResponse;
import com.dekankilic.product.exception.ProductPurchaseException;
import com.dekankilic.product.exception.ResourceNotFoundException;
import com.dekankilic.product.mapper.ProductMapper;
import com.dekankilic.product.model.Product;
import com.dekankilic.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper mapper;


    public Integer createProduct(ProductRequest request) {
        var product = mapper.toProduct(request);
        return productRepository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        List<Integer> productIds = request.stream().map(ProductPurchaseRequest::productId).toList();
        List<Product> storedProducts = productRepository.findAllByIdInOrderById(productIds);


        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exits");
        }
        List<ProductPurchaseRequest> storedRequest = request.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId)).toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        for (int i = 0 ; i < storedProducts.size() ; i++){
            Product product = storedProducts.get(i);
            ProductPurchaseRequest productRequest = storedRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity()){
               throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productRequest.productId());
            }

            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(mapper.toProductPurchaseResponse(product, productRequest.quantity()));

        }

        return purchasedProducts;
    }

    public ProductResponse findById(Integer id) {
        return productRepository.findById(id).map(mapper::toProductResponse).orElseThrow(() -> new ResourceNotFoundException("Product", "productId", id.toString()));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream().map(mapper::toProductResponse).collect(Collectors.toList());
    }
}
