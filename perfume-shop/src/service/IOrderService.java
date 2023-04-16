package service;

import model.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getAllOrders();

    void add(Order order);

    Order findById(long orderId);

    void update(Order newOrder);
}
