package service;

import model.OrderItem;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderItemService implements IOrderItemService {
    public final String pathOrderItems = "D:/lucduong1720/Perfume-Shop/perfume-shop/data/order_items.csv";
    private static OrderItemService instance;
    public static OrderItemService getInstance() {
        if (instance == null)
            instance = new OrderItemService();
        return instance;
    }

    @Override
    public List<OrderItem> findByOrderId(long orderId) {
        List<OrderItem> orderItems = getAllOrderItems();
        List<OrderItem> orderItemsFind = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getOrderId() == orderId) {
                orderItemsFind.add(orderItem);
            }
        }
        if (orderItemsFind.isEmpty()) {
            return null;
        }
        return orderItemsFind;
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return FileUtils.readData(pathOrderItems, OrderItem.class);
    }

    @Override
    public void add(OrderItem newOrderItem) {
        List<OrderItem> orderItems = getAllOrderItems();
        orderItems.add(newOrderItem);
        FileUtils.writeDataToFile(pathOrderItems, orderItems);
    }

    @Override
    public void update(OrderItem newOrderItem) {
        List<OrderItem> orderItems = getAllOrderItems();
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getId() == newOrderItem.getId()) {
                orderItem.setPrice(newOrderItem.getPrice());
                orderItem.setQuantity(newOrderItem.getQuantity());
                orderItem.setProductId(newOrderItem.getProductId());
                orderItem.setOrderId(newOrderItem.getOrderId());
                FileUtils.writeDataToFile(pathOrderItems, orderItems);
                break;
            }
        }
    }

    @Override
    public OrderItem findById(long id) {
        List<OrderItem> orderItems = getAllOrderItems();
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getId() == id) {
                return orderItem;
            }
        }
        return null;
    }
}
