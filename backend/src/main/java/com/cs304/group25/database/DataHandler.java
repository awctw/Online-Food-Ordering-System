package com.cs304.group25.database;

import com.cs304.group25.model.*;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Objects;

@Mapper
public interface DataHandler {

    @Insert({"INSERT INTO Customer(Address,Name,PhoneNumber,Email,PostCode)" +
            "VALUES (#{address},#{name},#{phoneNumber},#{email},#{postCode})"})
    int insertCustomer(Customer customer);

    @Insert({"INSERT INTO 'Order'(RestaurantID,CustomerID,Notes,Status,TotalPrice)" +
            "VALUES (#{RestaurantID},#{CustomerID},#{Notes},#{Status},#{TotalPrice})"})
    int insertOrder(Order order);

    @Insert({"INSERT INTO Delivery(OrderID,DelivererID,ETA) " +
            "VALUES (#{OrderID},#{DelivererID})"})
    int insertDelivery(Delivery delivery);

    @Insert({"INSERT INTO PickUp(OrderID) " +
            "VALUES (#{OrderID})"})
    int insertPickUp(PickUp pickUp);

    @Insert({"INSERT INTO Deliverer(LicenseNum,CarPlate,PhoneNumber,Name) " +
            "VALUES (#{LicenseNum},#{CarPlate},#{PhoneNumber},#{Name})"})
    int insertDeliverer(Deliverer deliverer);

    @Insert({"INSERT INTO Restaurant(Name,Category,Address,PostCode,OperatingHours) " +
            "VALUES (#{Name},#{Category},#{Address},#{PostCode},#{OperatingHours})"})
    int insertRestaurant(Restaurant restaurant);

    @Insert({"INSERT INTO Menu(RestaurantID, Type)" +
            "VALUES (#{RestaurantID},#{Type})"})
    int insertMenu(Menu menu);

    @Insert({"INSERT INTO Food(MenuID,Price,Description,Name)" +
            "VALUES (#{MenuID},#{Price},#{Description},#{Name})"})
    int insertFood(Food food);

    @Insert({"INSERT INTO OrderDetail(OrderIDDetail,FoodID,Quantity)" +
            "VALUES (#{OrderIDDetail},#{FoodID},#{Quantity})"})
    int insertOrderDetail(OrderDetail orderDetail);

    @Insert({"INSERT INTO Review(CustomerID,DelivererID,RestaurantID,Comment,Rating)" +
            "VALUES (#{CustomerID},#{DelivererID},#{RestaurantID},#{Comment},#{Rating})"})
    int insertReview(Review review);

    @Insert({"INSERT INTO Payment(CardID,CustomerID,CardType,ExpiredDate,CardHolderName,SecurityCode)" +
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

    @Select("SELECT * FROM Review WHERE #{id} = Review.RestaurantID")
    List<Review> getMatchingRestaurantReviews(Integer id);

    @Update("Update Customer SET Address = #{newAddress} WHERE CustomerID = #{customer.customerId}" )
    int updateCustomerAddress(Customer customer, String newAddress);

    @Select("SELECT R.restaurantID, R.name, R.operatingHours FROM Restaurant R WHERE #{cat} = R.Category")
    List<Restaurant.RestaurantCol> filterByCategory(String cat);

    @Select("SELECT * FROM Restaurant INNER JOIN Review ON Restaurant.RestaurantID = Review.RestaurantID WHERE Rating >= #{rating}")
    List<Restaurant> filterByRating(int rating);

    @Select("SELECT R.RestaurantID, R.Name, Food.foodName, Food.Price, Food.Description FROM Restaurant R INNER JOIN Menu ON R.RestaurantID = Menu.RestaurantID INNER JOIN Food ON Food.MenuID = Menu.MenuID WHERE Menu.Type = #{menuType}")
    List<RestaurantMenuItems> getRestaurantMenuItems(String menuType);

    @Delete("DELETE FROM OrderDetail WHERE OrderDetail.OrderDetailID = #{id} AND #{order.orderId} = OrderDetail.OrderIDDetail")
    void deleteOrderDetails(Order order, Integer id);

    @Select("SELECT rest.Name, AVG(r.rating) FROM Restaurant rest" +
            "INNER JOIN Review r ON rest.RestaurantID = r.RestaurantID" +
            "GROUP BY rest.Name ORDER BY rest.Name DESC")
    List<Restaurant> showAvgRating(int rating);

    @Select("SELECT r.Name FROM Restaurant r, Order o, Customer c" +
            "WHERE c.CustomerID = o.CustomerID AND r.RestaurantID = o.RestaurantID" +
            "GROUP BY r.Name HAVING COUNT(*) > 1")
    List<Restaurant> getRestaurant(Restaurant restaurant);
}
