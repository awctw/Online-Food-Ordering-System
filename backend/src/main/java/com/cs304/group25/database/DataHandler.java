package com.cs304.group25.database;

import com.cs304.group25.model.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DataHandler {

    @Insert({"INSERT INTO Customer(Address,Name,PhoneNumber,Email,PostCode)" +
            "VALUES (#{Address},#{Name},#{PhoneNumber},#{Email},#{PostCode})"})
    int insertCustomer(Customer customer);

    @Insert({"INSERT INTO Order(RestaurantID,CustomerID,Notes,Status,TotalPrice) " +
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

    @Insert({"INSERT INTO Menu(RestaurantID) " +
            "VALUES (#{RestaurantID})"})
    int insertMenu(Menu menu);

    @Insert({"INSERT INTO Food(MenuID,Price,Description,Name) " +
            "VALUES (#{MenuID},#{Price},#{Description},#{Name})"})
    int insertFood(Food food);

    @Insert({"INSERT INTO OrderDetail(OrderID,FoodID,Quantity) " +
            "VALUES (#{OrderID},#{FoodID},#{Quantity})"})
    int insertOrderDetail(OrderDetail orderDetail);

    @Insert({"INSERT INTO Review(CustomerID,DelivererID,RestaurantID,Comment,Rating)" +
            "VALUES (#{CustomerID},#{DelivererID},#{RestaurantID},#{Comment},#{Rating})"})
    int insertReview(Review review);

    @Insert({"INSERT INTO Payment(CustomerID,CardType,ExpiredDate,CardHolderName,SecurityCode)" +
            "VALUES (#{CustomerID},#{CardType},#{ExpiredDate},#{CardHolderName},#{SecurityCode})"})
    int insertPayment(Payment payment);

    @Update({"UPDATE Customer SET Customer.Address = #{uAddress} WHERE #{id} = Customer.CustomerID AND #{old} = Customer.Address"})
    void updateAddress(String old, String uAddress, int id);

    @Select({"SELECT Customer.Address FROM Customer WHERE Customer.Address = #{uAddress}"})
    String getNewCustomerAddress(String uAddress);

    @Select("SELECT * FROM Restaurant")
    List<Restaurant> getAllRestaurants();

    @Select("SELECT * FROM Customer")
    List<Customer> getAllCustomers();

    @Select("SELECT * FROM Deliverer")
    List<Deliverer> getAllDeliverers();

    @Select("SELECT * FROM Review")
    List<Review> getAllReviews();

    @Select("SELECT * FROM Review WHERE #{id} = Review.RestaurantID")
    List<Review> getMatchingRestaurantReviews(int id);

    @Update("Update Customer SET Address=#{address},Name=#{name},PhoneNumber=#{phoneNumber}"+
            ",Email=#{email},PostCode=#{postcode} WHERE CustomerID = #{customerId}" )
    int updateCustomer(Customer customer);

}
