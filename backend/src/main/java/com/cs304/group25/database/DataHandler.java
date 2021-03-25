package com.cs304.group25.database;

import com.cs304.group25.model.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Types;
import java.util.List;

@Mapper
public interface DataHandler {
    @Insert("INSERT INTO Restaurant(Name,Category,Address,PostCode,OperatingHours) " +
            "VALUES (#{name},#{category},#{address},#{postCode},#{operatingHours})")
    Integer insertRestaurant(Restaurant restaurant);

    @Select("SELECT * FROM Restaurant")
    List<Restaurant> getAllRestaurants();

    @Select("SELCT * FROM Customer")
    List<Customer> getAllCustomers();

    List<Deliverer> getAllDeliverers();

    List<Review> getAllReviews();

    List<Review> getMatchingRestaurantReviews(int id);

    @Insert("INSERT INTO Customer(Address,Name,PhoneNumber,Email,PostCode)" +
            "VALUES (#{address},#{name},#{phoneNumber},#{email},#{postcode})")
    int insertCustomer(Customer customer);

    @Update("Update Customer SET Address=#{address},Name=#{name},PhoneNumber=#{phoneNumber}"+
            ",Email=#{email},PostCode=#{postcode} WHERE CustomerID = #{customerId}" )
    int updateCustomer(Customer customer);
}
