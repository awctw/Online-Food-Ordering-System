package com.cs304.group25.service;

import com.cs304.group25.database.DataHandler;
import com.cs304.group25.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import java.util.Date;
import java.util.List;

@Service
public class FoodService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DataHandler dataHandler;

    public List<Restaurant> getAllRestaurant() {
        return dataHandler.getAllRestaurants();
    }

    public List<Customer> getAllCustomer() {
        return dataHandler.getAllCustomers();
    }

    public List<Deliverer> getAllDeliverer() {
        return dataHandler.getAllDeliverers();
    }

    public List<ReviewDetails> getAllReviews() {
        List<ReviewDetails> allDeliveryReviews = dataHandler.getAllDeliveryReviews();
        List<ReviewDetails> allPickUpReviews = dataHandler.getAllPickUpReviews();
        for (ReviewDetails r : allPickUpReviews) {
            r.setDelivererName("/");
        }
        List<ReviewDetails> newlist = new ArrayList<ReviewDetails>(allDeliveryReviews);
        newlist.addAll(allPickUpReviews);
        return newlist;
    }

    public List<Payment> getAllPayments() {
        return dataHandler.getAllPayments();
    }

    public List<Review> getMatchingReview(Integer id) {
        return dataHandler.getMatchingRestaurantReviews(id);
    }

    public Integer insertCustomer(Customer customer) {
        logger.info("insert new customer", customer);
        int count = dataHandler.insertCustomer(customer);
        if (count != 1) {
           logger.error("fail to insert new customer");
        }
        int primaryID = lastPrimaryId();
        return count;
    }

    public Integer lastPrimaryId(){
        return dataHandler.lastPrimaryId();
    }

    public Integer updateCustomerAddress(Customer customer, String address) {
        logger.info("update customer address" + customer);
        return dataHandler.updateCustomerAddress(customer, address);
    }

    public Integer insertPayment(Payment payment) {
        return dataHandler.insertPayment(payment);
    }

    public Integer insertRestaurant(Restaurant restaurant) {
        return dataHandler.insertRestaurant(restaurant);
    }

    public Integer insertReview(Review review) { return dataHandler.insertReview(review); }

    public Integer insertDeliverer(Deliverer deliverer) { return dataHandler.insertDeliverer(deliverer); }

    public List<Restaurant.RestaurantCol> filterByCategory(String category) {
        return dataHandler.filterByCategory(category);
    }

    public List<Review.ReviewSingle> filterByRating(int rating) {
        return dataHandler.filterByRating(rating);
    }

    public List<RestaurantMenuItems> getRestaurantMenuItems(String menuType, int restaurantId) {
        return dataHandler.getRestaurantMenuItems(menuType, restaurantId);
    }

    public void deleteOrderDetails(Order order, Integer id){
         dataHandler.deleteOrderDetails(order, id);
    }

    public List<Restaurant.RestaurantTop> showRestaurantRanking() {
        return dataHandler.showRestaurantRanking();
    }

    public List<String> getRestaurantOrders(int customerId) {
        return dataHandler.getRestaurantOrders(customerId);
    }

    public List<String> getCheapRestaurant() { return dataHandler.getCheapRestaurant(); }

    public List<Customer> getVipCustomer() { return dataHandler.getVipCustomer(); }

    public Integer insertOrder(TotalOrder totalOrder) {
        logger.info("insert total order" + totalOrder);
        //insert order into Order table

        System.out.println(totalOrder.getOrder());
        int count = dataHandler.insertOrder(totalOrder.getOrder());
        if (count != 1) {
            logger.error("fail to insert new order");
        }
        int orderID = dataHandler.lastPrimaryId();

        List<OrderDetail> list = totalOrder.getOrderDetailList();
        for (OrderDetail orderDetail : list) {
            // set orderID to orderDetail
            orderDetail.setOrderId(orderID);
            // insert into orderDetail
            dataHandler.insertOrderDetail(orderDetail);
        }

        // insert into PickUp or Delivery table
        if (totalOrder.isPickUp()) {
            PickUp pickUp = new PickUp(orderID);
            dataHandler.insertPickUp(pickUp);
        } else {
            // I simplify the logic here.. choose a random deliverer and set eta to current time
            Date date = new Date();
            Delivery delivery = new Delivery(orderID, ThreadLocalRandom.current().nextInt(1,11), date);
            dataHandler.insertDelivery(delivery);
        }
        return 1;
    }

    public List<RestaurantAllFood> getAllFoodFromARestaurant(Integer restaurantId) {
        logger.info("getAllFoodFromARestaurant " + restaurantId);
        return dataHandler.getAllFoodFromARestaurant(restaurantId);
    }

    public List<OrderHistory> getHistoryOrder(Integer customerId) {
        logger.info("getHistoryOrder " + customerId);
        List<OrderHistory> historyDeliveryOrder = dataHandler.getHistoryDeliveryOrder(customerId);
        List<OrderHistory> historyPickUpOrder = dataHandler.getHistoryPickUpOrder(customerId);
        for(OrderHistory o : historyDeliveryOrder) {
            o.setType("Delivery");
        }
        for(OrderHistory o : historyPickUpOrder) {
            o.setType("PickUp");
            o.setDelivererName("/");
        }
        List<OrderHistory> newlist = new ArrayList<OrderHistory>(historyDeliveryOrder);
        newlist.addAll(historyPickUpOrder);
        return newlist;
    }

    public Integer deleteOrder(Integer orderId) {
        logger.info("deleteOrder " + orderId);
        return dataHandler.deleteOrder(orderId);
    }
}
