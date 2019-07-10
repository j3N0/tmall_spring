package com.example.tmall_springboot.services.jpa;

import com.example.tmall_springboot.domains.Product;
import com.example.tmall_springboot.domains.Review;
import com.example.tmall_springboot.repositories.ReviewRepository;
import com.example.tmall_springboot.services.ProductService;
import com.example.tmall_springboot.services.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewJpaService implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductService productService;

    public ReviewJpaService(ReviewRepository reviewRepository, ProductService productService) {
        this.reviewRepository = reviewRepository;
        this.productService = productService;
    }

    @Override
    public Review add(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> list(Product product) {
        return reviewRepository.findByProductOrderByIdDesc(product);
    }

    @Override
    public int getCount(Product product) {
        return reviewRepository.countByProduct(product);
    }
}