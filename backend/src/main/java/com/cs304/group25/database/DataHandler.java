package com.cs304.group25.database;

import com.cs304.group25.model.Restaurant;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DataHandler {
    @Insert("INSERT INTO Restaurant(Name,Category,Address,PostCode,OperatingHours) " +
            "VALUES (#{Name},#{Category},#{Address},#{PostCode},#{OperatingHours})")
    Integer insertRestaurant(Restaurant restaurant);

    @Select("SELECT * FROM Restaurant")
    List<Restaurant> getAllRestaurants();

}
