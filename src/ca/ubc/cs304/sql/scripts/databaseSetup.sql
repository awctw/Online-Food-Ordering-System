CREATE TABLE Customer(
                         CustomerID INT AUTO_INCREMENT ,
                         Address VARCHAR(100) NOT NULL ,
                         Name VARCHAR(50)  ,
                         PhoneNumber INT UNIQUE,
                         Email VARCHAR(50) UNIQUE ,
                         PostCode CHAR(6)  ,
                         PRIMARY KEY (CustomerID,Address)
);

CREATE TABLE Restaurant(
                           RestaurantID INT AUTO_INCREMENT ,
                           Name VARCHAR(50)  ,
                           Category VARCHAR(20)  ,
                           Address VARCHAR(50)  ,
                           PostCode CHAR(6)  ,
                           OperatingHours INT  ,
                           PRIMARY KEY (RestaurantID)
);

CREATE TABLE Deliverer(
                          DelivererID INT AUTO_INCREMENT ,
                          LicenseNum CHAR(10) ,
                          CarPlate CHAR(10) ,
                          PhoneNumber INT ,
                          Name VARCHAR(50)  ,
                          PRIMARY KEY (DelivererID,CarPlate)
);

CREATE TABLE `Order`(
                      OrderID INT AUTO_INCREMENT ,
                      RestaurantID INT NOT NULL ,
                      CustomerID INT NOT NULL ,
                      Notes VARCHAR(200)  ,
                      Status VARCHAR(32) NOT NULL ,
                      TotalPrice DECIMAL(10,2) NOT NULL ,
                      PRIMARY KEY (OrderID) ,
                      FOREIGN KEY (RestaurantID) REFERENCES Restaurant (RestaurantID) ,
                      FOREIGN KEY (CustomerID) REFERENCES Customer (CustomerID)
);


CREATE TABLE Delivery(
                         OrderID INT ,
                         DeliveryID INT AUTO_INCREMENT ,
                         DelivererID INT NOT NULL ,
                         ETA DATETIME  ,
                         PRIMARY KEY (DeliveryID) ,
                         FOREIGN KEY (OrderID) REFERENCES `Order` (OrderID) ,
                         FOREIGN KEY (DelivererID) REFERENCES Deliverer (DelivererID)
);

CREATE TABLE PickUp(
                       OrderID INT ,
                       PickUpID INT AUTO_INCREMENT ,
                       PRIMARY KEY (PickUpID) ,
                       FOREIGN KEY (OrderID) REFERENCES `Order` (OrderID)
);

CREATE TABLE Menu(
                     MenuID INT AUTO_INCREMENT ,
                     RestaurantID INT ,
                     Type CHAR(20)  ,
                     PRIMARY KEY (MenuID, RestaurantID) ,
                     FOREIGN KEY (RestaurantID) REFERENCES Restaurant (RestaurantID) ON DELETE CASCADE
);

CREATE TABLE Food(
                     FoodID INT AUTO_INCREMENT ,
                     MenuID INT NOT NULL ,
                     Price DECIMAL(10,2)  ,
                     Name VARCHAR(50)  ,
                     Description VARCHAR(200)  ,
                     PRIMARY KEY (FoodID) ,
                     FOREIGN KEY (MenuID) REFERENCES Menu (MenuID)
);

CREATE TABLE OrderDetail(
                            OrderDetailID INT AUTO_INCREMENT ,
                            OrderID INT ,
                            FoodID INT NOT NULL ,
                            Quantity INT NOT NULL ,
                            PRIMARY KEY (OrderDetailID, OrderID) ,
                            FOREIGN KEY (OrderID) REFERENCES `Order` (OrderID) ON DELETE CASCADE ,
                            FOREIGN KEY (FoodID) REFERENCES Food (FoodID)
);

CREATE TABLE Review(
                       ReviewID INT AUTO_INCREMENT ,
                       CustomerID INT NOT NULL ,
                       RestaurantID INT NOT NULL,
                       DelivererID INT,
                       Comment VARCHAR(1024)  ,
                       Rating INT  ,
                       PRIMARY KEY (ReviewID) ,
                       FOREIGN KEY (CustomerID) REFERENCES Customer (CustomerID) ,
                       FOREIGN KEY (RestaurantID) REFERENCES Restaurant (RestaurantID) ,
                       FOREIGN KEY (DelivererID) REFERENCES Deliverer (DelivererID)
);


CREATE TABLE Payment(
                        CardID VARCHAR(32),
                        CustomerID INT NOT NULL ,
                        CardType CHAR(10)  ,
                        ExpiredDate INT  ,
                        CardHolderName VARCHAR(50)  ,
                        SecurityCode VARCHAR(32)  ,
                        PRIMARY KEY (CardID) ,
                        FOREIGN KEY (CustomerID) REFERENCES Customer (CustomerID) ON DELETE CASCADE
);
