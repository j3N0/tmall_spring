package com.example.tmall_springboot.services.jpa;

import com.example.tmall_springboot.domains.Order;
import com.example.tmall_springboot.domains.OrderItem;
import com.example.tmall_springboot.repositories.OrderItemRepository;
import com.example.tmall_springboot.services.OrderItemService;
import com.example.tmall_springboot.services.ProductImageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemJpaService implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ProductImageService productImageService;

    public OrderItemJpaService(OrderItemRepository orderItemRepository, ProductImageService productImageService) {
        this.orderItemRepository = orderItemRepository;
        this.productImageService = productImageService;
    }

    @Override
    public void fill(List<Order> orders) {
        orders.forEach(this::fill);
    }

    @Override
    public void fill(Order order) {
        List<OrderItem> orderItems = listByOrder(order);
        //Todo refactor to stream()..
        float total = 0;
        int totalNumber = 0;
        for (OrderItem oi :orderItems) {
            total += oi.getNumber() * oi.getProduct().getPromotePrice();
            totalNumber += oi.getNumber();
            productImageService.setFirstProductImage(oi.getProduct());
        }
        order.setTotal(total);
        order.setOrderItems(orderItems);
        order.setTotalNumber(totalNumber);
    }

    @Override
    public List<OrderItem> listByOrder(Order order) {
        return orderItemRepository.findByOrderOrderByIdDesc(order);
    }
}