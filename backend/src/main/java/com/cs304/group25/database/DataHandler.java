package com.cs304.group25.database;

import com.cs304.group25.model.*;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface DataHandler {

    @Insert({"INSERT INTO Customer(address,name,phoneNumber,email,postCode) " +
            "VALUES (#{address},#{name},#{phoneNumber},#{email},#{postCode})"})
    int insertCustomer(Customer customer);

    @Insert({"INSERT INTO `Order`(restaurantId,customerId,notes,status,totalPrice) " +
            "VALUES (#{restaurantId},#{customerId},#{notes},#{status},#{totalPrice})"})
    int insertOrder(Order order);

    @Insert({"INSERT INTO Delivery(orderId,eta) " +
            "VALUES (#{orderId},#{eta})"})
    int insertDelivery(Delivery delivery);

    @Insert({"INSERT INTO PickUp(orderId) " +
            "VALUES (#{orderId})"})
    int insertPickUp(PickUp pickUp);

    @Insert({"INSERT INTO Deliverer(licenseNum,carPlate,phoneNumber,name) " +
            "VALUES (#{licenseNum},#{carPlate},#{phoneNumber},#{name})"})
    int insertDeliverer(Deliverer deliverer);

    @Insert({"INSERT INTO Restaurant(name,category,address,postCode,operatingHours) " +
            "VALUES (#{name},#{category},#{address},#{postCode},#{operatingHours})"})
    int insertRestaurant(Restaurant restaurant);

    @Insert({"INSERT INTO Menu(restaurantId, type)" +
            "VALUES (#{restaurantId},#{type})"})
    int insertMenu(Menu menu);

    @Insert({"INSERT INTO Food(menuId,price,description,name)" +
            "VALUES (#{menuId},#{price},#{description},#{name})"})
    int insertFood(Food food);

    @Insert({"INSERT INTO OrderDetail(orderId,foodId,quantity)" +
            "VALUES (#{orderId},#{foodId},#{quantity})"})
    int insertOrderDetail(OrderDetail orderDetail);

    @Insert({"INSERT INTO Review(customerId,delivererId,restaurantId,comment,rating)" +
            "VALUES (#{customerId},#{delivererId},#{restaurantId},#{comment},#{rating})"})
    int insertReview(Review review);

    @Insert({"INSERT INTO Payment(cardId,customerId,cardType,expiredDate,cardHolderName,securityCode)" +
            "VALUES (#{cardId},#{customerId},#{cardType},#{expireDate},#{cardHolderName},#{securityCode})"})
    int insertPayment(Payment payment);

    @Select("SELECT * FROM Restaurant")
    List<Restaurant> getAllRestaurants();

    @Select("SELECT * FROM Customer")
    List<Customer> getAllCustomers();

    @Select("SELECT * FROM Deliverer")
    List<Deliverer> getAllDeliverers();

    @Select("SELECT * FROM Payment")
    List<Payment> getAllPayments();

    @Select("SELECT * FROM Review")
    List<Review> getAllReviews();

    @Select("SELECT * FROM Review WHERE #{id} = Review.restaurantId")
    List<Review> getMatchingRestaurantReviews(Integer id);

    @Update("Update Customer SET address = #{newAddress} WHERE customerId = #{customer.customerId}" )
    int updateCustomerAddress(Customer customer, String newAddress);

    @Select("SELECT R.restaurantId, R.name, R.operatingHours FROM Restaurant R WHERE #{cat} = R.category")
    List<Restaurant.RestaurantCol> filterByCategory(String cat);

    @Select("SELECT * FROM Restaurant INNER JOIN Review ON Restaurant.restaurantId = Review.restaurantId WHERE rating >= #{rating}")
    List<Restaurant> filterByRating(int rating);

    @Select("SELECT R.restaurantID, R.name, Food.foodName, Food.price, Food.description FROM Restaurant R INNER JOIN Menu ON R.restaurantId= Menu.restaurantId INNER JOIN Food ON Food.menuId = Menu.menuId WHERE Menu.type = #{menuType}")
    List<RestaurantMenuItems> getRestaurantMenuItems(String menuType);

    @Delete("DELETE FROM OrderDetail WHERE OrderDetail.orderDetailId = #{id} AND #{order.orderId} = OrderDetail.orderId")
    void deleteOrderDetails(Order order, Integer id);

    @Select("SELECT LAST_INSERT_ID()")
    Integer lastPrimaryId();

    // Aggregation 1
    @Select("SELECT rest.Name, AVG(r.rating) FROM Restaurant rest, Review r " +
            "WHERE rest.RestaurantID = r.RestaurantID" +
            "GROUP BY rest.Name ORDER BY rest.Name DESC")
    List<Restaurant> showAvgRating(int rating);

    // Aggregation 2 will return nulls other than the name
    @Select("SELECT r.name FROM Restaurant r, `Order` o, Customer c " +
            "WHERE c.customerId = o.customerId AND o.RestaurantID = r.RestaurantID " +
            "GROUP BY r.Name HAVING COUNT(*) > 1")
    List<Restaurant> getRestaurantOrders();

}
