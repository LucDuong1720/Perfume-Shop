package service;

import model.OrderItem;

import java.util.List;

public interface IOrderItemService {
    List<OrderItem> findByOrderId(long orderId);
    List<OrderItem> getAllOrderItems();

    void add(OrderItem newOrderItem);

    void update(OrderItem newOrderItem);

    OrderItem findById(long id);
}
