CREATE TABLE Customer(
                         customerId INT AUTO_INCREMENT ,
                         address VARCHAR(100) NOT NULL ,
                         name VARCHAR(50)  ,
                         phoneNumber INT UNIQUE,
                         email VARCHAR(50) UNIQUE ,
                         postCode CHAR(6)  ,
                         PRIMARY KEY (customerId,address)
);

CREATE TABLE Restaurant(
                           restaurantId INT AUTO_INCREMENT ,
                           name VARCHAR(50)  ,
                           category VARCHAR(20)  ,
                           address VARCHAR(50)  ,
                           postCode CHAR(6)  ,
                           operatingHours INT  ,
                           PRIMARY KEY (restaurantId)
);

CREATE TABLE Deliverer(
                          delivererId INT AUTO_INCREMENT ,
                          licenseNum CHAR(10) ,
                          carPlate CHAR(10) ,
                          phoneNumber INT ,
                          name VARCHAR(50)  ,
                          PRIMARY KEY (delivererId,carPlate)
);

CREATE TABLE `Order`(
                        orderId INT AUTO_INCREMENT ,
                        restaurantId INT NOT NULL ,
                        customerId INT NOT NULL ,
                        notes VARCHAR(200)  ,
                        status VARCHAR(32) NOT NULL ,
                        totalPrice DECIMAL(10,2) NOT NULL ,
                        PRIMARY KEY (orderId) ,
                        FOREIGN KEY (restaurantId) REFERENCES Restaurant (restaurantId) ,
                        FOREIGN KEY (customerId) REFERENCES Customer (customerId)
);


CREATE TABLE Delivery(
                         orderId INT ,
                         deliveryId INT AUTO_INCREMENT ,
                         delivererId INT NOT NULL ,
                         eta DATETIME  ,
                         PRIMARY KEY (deliveryId) ,
                         FOREIGN KEY (orderId) REFERENCES `Order` (orderId) ,
                         FOREIGN KEY (delivererId) REFERENCES Deliverer (delivererId)
);

CREATE TABLE PickUp(
                       orderId INT ,
                       pickUpId INT AUTO_INCREMENT ,
                       PRIMARY KEY (pickUpId) ,
                       FOREIGN KEY (orderId) REFERENCES `Order` (orderId)
);

CREATE TABLE Menu(
                     menuId INT AUTO_INCREMENT ,
                     restaurantId INT ,
                     type CHAR(20)  ,
                     PRIMARY KEY (menuId, restaurantId) ,
                     FOREIGN KEY (restaurantId) REFERENCES Restaurant (restaurantId) ON DELETE CASCADE
);

CREATE TABLE Food(
                     foodId INT AUTO_INCREMENT ,
                     menuId INT NOT NULL ,
                     price DECIMAL(10,2)  ,
                     name VARCHAR(50)  ,
                     description VARCHAR(200)  ,
                     PRIMARY KEY (foodId) ,
                     FOREIGN KEY (menuId) REFERENCES Menu (menuId)
);

CREATE TABLE OrderDetail(
                            orderDetailId INT AUTO_INCREMENT ,
                            orderId INT ,
                            foodId INT NOT NULL ,
                            quantity INT NOT NULL ,
                            PRIMARY KEY (orderDetailId, orderId) ,
                            FOREIGN KEY (orderId) REFERENCES `Order` (orderId) ON DELETE CASCADE ,
                            FOREIGN KEY (foodId) REFERENCES Food (foodId)
);

CREATE TABLE Review(
                       reviewId INT AUTO_INCREMENT ,
                       customerId INT NOT NULL ,
                       restaurantId INT NOT NULL,
                       delivererId INT,
                       comment VARCHAR(1024)  ,
                       rating INT  ,
                       PRIMARY KEY (reviewId) ,
                       FOREIGN KEY (customerId) REFERENCES Customer (customerId) ,
                       FOREIGN KEY (restaurantId) REFERENCES Restaurant (restaurantId) ,
                       FOREIGN KEY (delivererId) REFERENCES Deliverer (delivererId)
);


CREATE TABLE Payment(
                        cardId VARCHAR(32),
                        customerId INT NOT NULL ,
                        cardType CHAR(10)  ,
                        expiredDate INT  ,
                        cardHolderName VARCHAR(50)  ,
                        securityCode VARCHAR(32)  ,
                        PRIMARY KEY (cardId) ,
                        FOREIGN KEY (customerId) REFERENCES Customer (customerId) ON DELETE CASCADE
);

INSERT INTO Restaurant(name,category,address,postCode,operatingHours)
VALUES ('Chipotle','Mexican','818 Howe St','V6Z 1N4','9-10');

INSERT INTO Restaurant(name,category,address,postCode,operatingHours)
VALUES ('Burger King','Fast Food','821 Granville St','V6Z 1K9','9-9:30');

INSERT INTO Restaurant(name,category,address,postCode,operatingHours)
VALUES ('Chatime','Bubble Tea','2740 E Hastings St','V5K 1Z9','12-12');

INSERT INTO Menu(restaurantId, type)
VALUES (1,'Dinner');

INSERT INTO Menu(restaurantId, type)
VALUES (1,'Lunch');

INSERT INTO Menu(restaurantId, type)
VALUES (2,'Breakfast');

INSERT INTO Menu(restaurantId, type)
VALUES (3,'All');

INSERT INTO Food(menuId,price,description,name)
VALUES (1,19.99,'Flour Tortilla','Burrito');

INSERT INTO Food(menuId,price,description,name)
VALUES (1,19.99,'Just like a burrito','Burrito Bowl');

INSERT INTO Food(menuId,price,description,name)
VALUES (2,19.99,'Just like a burrito','Burrito Bowl');

INSERT INTO Food(menuId,price,description,name)
VALUES (3,9.99,'100% Sirloin beef','Hamburger');

INSERT INTO Food(menuId,price,description,name)
VALUES (4,5.99,'Cold or Hot','Jasmine Green Milk Tea');

