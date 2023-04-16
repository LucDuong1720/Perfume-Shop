package model;

import service.IModel;

public class OrderItem implements IModel<OrderItem> {
    private long id;
    private double price;
    private int quantity;
    private long orderId;
    private long productId;
    public OrderItem() {}

    public OrderItem(long id, double price, int quantity, long orderId, long productId) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
        this.productId = productId;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s",
                id,
                price,
                quantity,
                productId,
                orderId);
    }

    @Override
    public OrderItem parseData(String line) {
        String[] items = line.split(",");
        OrderItem orderItem = new OrderItem();
        orderItem.setId(Long.parseLong(items[0]));
        orderItem.setPrice(Double.parseDouble(items[1]));
        orderItem.setQuantity(Integer.parseInt(items[2]));
        orderItem.setOrderId(Long.parseLong(items[3]));
        orderItem.setProductId(Long.parseLong(items[4]));

        return orderItem;
    }

    public double getTotal() {
            return this.price * this.quantity;
    }
}