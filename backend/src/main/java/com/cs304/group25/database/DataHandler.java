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

    @Insert({"INSERT INTO Delivery(orderId,delivererId,eta) " +
            "VALUES (#{orderId},#{delivererId},#{eta})"})
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

    @Select("SELECT R1.reviewId, C.name customerName, R2.name restaurantName, D.name delivererName, R1.comment, R1.rating " +
            "FROM Review R1, Customer C, Restaurant R2, Deliverer D " +
            "WHERE R1.customerId = C.customerId and R1.restaurantId = R2.restaurantId and R1.delivererId = D.delivererId " +
            "ORDER BY R1.reviewId ASC")
    List<ReviewDetails> getAllDeliveryReviews();

    @Select("SELECT R1.reviewId, C.name customerName, R2.name restaurantName, R1.comment, R1.rating " +
            "FROM Review R1, Customer C, Restaurant R2 " +
            "WHERE R1.customerId = C.customerId and R1.restaurantId = R2.restaurantId and R1.delivererId IS NULL " +
            "ORDER BY R1.reviewId ASC")
    List<ReviewDetails> getAllPickUpReviews();

    @Select("SELECT * FROM Review WHERE #{id} = Review.restaurantId")
    List<Review> getMatchingRestaurantReviews(Integer id);

    @Update("Update Customer SET address = #{newAddress} WHERE customerId = #{customer.customerId}" )
    int updateCustomerAddress(Customer customer, String newAddress);

    @Select("SELECT R.name, R.address, R.category FROM Restaurant R WHERE #{cat} = R.category")
    List<Restaurant.RestaurantCol> filterByCategory(String cat);

/*    @Select("SELECT Restaurant.name, Review.comment, Review.rating " +
            "FROM Restaurant INNER JOIN Review ON Restaurant.restaurantId = Review.restaurantId " +
            "WHERE rating >= #{rating}")
    List<RestaurantReviews> filterByRating(int rating);*/

    @Select("SELECT R1.reviewId, C.name customerName, R2.name restaurantName, D.name delivererName, R1.comment, R1.rating " +
            "FROM Review R1, Customer C, Restaurant R2, Deliverer D " +
            "WHERE R1.customerId = C.customerId AND " +
            "R1.restaurantId = R2.restaurantId AND " +
            "R1.delivererId = D.delivererId AND " +
            "R1.rating >= #{rating}")
    List<ReviewDetails> filterByRating(int rating);

    @Select("SELECT Food.foodId, R.name name, Food.name foodName, Food.price, Food.description " +
            "FROM Restaurant R INNER JOIN Menu ON R.restaurantId= Menu.restaurantId " +
            "INNER JOIN Food ON Food.menuId = Menu.menuId " +
            "WHERE Menu.type = #{menuType} AND R.restaurantId = #{restaurantId}")
    List<RestaurantMenuItems> getRestaurantMenuItems(String menuType, int restaurantId);

    @Delete("DELETE FROM OrderDetail WHERE OrderDetail.orderDetailId = #{id} AND #{order.orderId} = OrderDetail.orderId")
    void deleteOrderDetails(Order order, Integer id);

    @Select("SELECT LAST_INSERT_ID()")
    Integer lastPrimaryId();

    // Aggregation 1
//    @Select("SELECT rest.Name, AVG(r.rating) FROM Restaurant rest INNER JOIN Review r " +
//            "ON rest.RestaurantID = r.RestaurantID " +
//            "WHERE r.rating >= 3 " +
//           // "WHERE AVG(r.rating) >= 3 " +
//            "GROUP BY rest.Name ORDER BY rest.Name DESC")
//    List<Restaurant> showAvgRating();

    // Aggregation 1
    @Select("SELECT rest.name FROM Restaurant rest, Review r " +
            "WHERE rest.restaurantId = r.restaurantId " +
            "GROUP BY rest.name ORDER BY AVG(r.rating) DESC")
    List<Restaurant.RestaurantTop> showRestaurantRanking();


    // Aggregation 2
    @Select("SELECT r.name FROM Restaurant r, `Order` o, Customer c " +
            "WHERE o.customerId = #{customerId} AND o.restaurantId = r.restaurantId AND c.customerID = #{customerId} " +
            "GROUP BY r.name HAVING COUNT(*) > 1")
    List<String> getRestaurantOrders(int customerId);

    //Still have error in it
//    @Select("SELECT r.name, AVG(f1.price) FROM Restaurant r, Menu m, Food f1 " +
//            "WHERE r.restaurantId = m.restaurantId AND " +
//                  "m.menuId = f1.menuId " +
//            "GROUP BY r.name HAVING AVG(f1.price) <= all " +
//                                                        ("SELECT AVG(f2.price) " +
//                                                        "FROM Restaurant r, Menu m, Food f2 " +
//                                                        "GROUP BY r.name"))
//    List<Restaurant> getCheapRestaurant();
    @Select("SELECT r1.name FROM Restaurant r1, Menu m1, Food f1 " +
            "WHERE r1.restaurantId = m1.restaurantId AND " +
                  "m1.menuId = f1.menuId " +
            "GROUP BY r1.name HAVING AVG(f1.price) <= all (SELECT AVG(f2.price) " +
                                                          "FROM Restaurant r2, Menu m2, Food f2 " +
                                                          "WHERE r2.restaurantId = m2.restaurantId AND " +
                                                                "m2.menuId = f2.menuId " +
                                                          "GROUP BY r2.name)")
    List<String> getCheapRestaurant();

    //    @Select("SELECT c.name FROM Customer c " +
//         "WHERE NOT EXISTS " +
//               "(SELECT o.restaurantId FROM `Order` o " +
//               "WHERE o.customerId = c.customerId)")

    @Select("SELECT * FROM Customer c " +
            "WHERE NOT EXISTS " +
            "(SELECT r.restaurantId FROM Restaurant r " +
            "WHERE NOT EXISTS " +
            "(SELECT o.orderId FROM `Order` o " +
            "WHERE r.restaurantId = o.restaurantId AND o.customerId = c.customerId))")
    List<Customer> getVipCustomer();


    @Select("SELECT M.type, F.foodId, price, name, description " +
            "FROM Food F, Menu M " +
            "WHERE M.restaurantId = #{restaurantId} and M.menuId = F.menuId")
    List<RestaurantAllFood> getAllFoodFromARestaurant(Integer restaurantId);

    @Select("SELECT O.orderId orderId, O.restaurantId restaurantId, O.customerId, O.notes, " +
            "O.status, O.totalPrice, R.name restaurantName, D1.delivererId, D2.name delivererName " +
            "FROM `Order` O, Restaurant R, Delivery D1, Deliverer D2 " +
            "WHERE O.customerId = #{customerId} and O.restaurantId = R.restaurantId and O.orderId = D1.orderId and D2.delivererId = D1.delivererId ")
    List<OrderHistory> getHistoryDeliveryOrder(Integer customerId);

    @Select("SELECT O.orderId orderId, O.restaurantId restaurantId, O.customerId, O.notes, " +
            "O.status, O.totalPrice, R.name restaurantName " +
            "FROM `Order` O, Restaurant R, PickUp P " +
            "WHERE O.customerId = #{customerId} and O.restaurantId = R.restaurantId and O.orderId = P.orderId")
    List<OrderHistory> getHistoryPickUpOrder(Integer customerId);


    @Delete("DELETE FROM `Order` O " +
            "WHERE O.orderId = #{orderId}")
    Integer deleteOrder(Integer orderId);
}
