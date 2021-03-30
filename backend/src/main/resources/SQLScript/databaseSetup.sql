CREATE TABLE Customer(
                         customerId INT AUTO_INCREMENT ,
                         address VARCHAR(100) NOT NULL ,
                         cName VARCHAR(50)  ,
                         phoneNumber CHAR(10) UNIQUE,
                         email VARCHAR(50) UNIQUE ,
                         postCode CHAR(6)  ,
                         PRIMARY KEY (customerId,address)
);

CREATE TABLE Restaurant(
                           restaurantId INT AUTO_INCREMENT ,
                           rName VARCHAR(50)  ,
                           category VARCHAR(20)  ,
                           address VARCHAR(50)  ,
                           postCode CHAR(6)  ,
                           operatingHours VARCHAR(20)  ,
                           PRIMARY KEY (restaurantId)
);

CREATE TABLE Deliverer(
                          delivererId INT AUTO_INCREMENT ,
                          licenseNum CHAR(10) ,
                          carPlate CHAR(10) ,
                          phoneNumber CHAR(10) ,
                          dName VARCHAR(50)  ,
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
                         FOREIGN KEY (orderId) REFERENCES `Order` (orderId) ON DELETE CASCADE,
                         FOREIGN KEY (delivererId) REFERENCES Deliverer (delivererId)
);

CREATE TABLE PickUp(
                       orderId INT ,
                       pickUpId INT AUTO_INCREMENT ,
                       PRIMARY KEY (pickUpId) ,
                       FOREIGN KEY (orderId) REFERENCES `Order` (orderId) ON DELETE CASCADE
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

INSERT INTO Restaurant(rName,category,address,postCode,operatingHours)
VALUES ('Chipotle','Mexican','818 Howe St','V6Z1N4','9-10');

INSERT INTO Restaurant(rName,category,address,postCode,operatingHours)
VALUES ('Burger King','Fast Food','821 Granville St','V6Z1K9','9-9:30');

INSERT INTO Restaurant(rName,category,address,postCode,operatingHours)
VALUES ('Chatime','Bubble Tea','2740 E Hastings St','V5K1Z9','12-12');

INSERT INTO Restaurant(rName,category,address,postCode,operatingHours)
VALUES ('Hydra','Mediterranean','825 W. Pender St','V6C1K7','8-9');

INSERT INTO Restaurant(rName,category,address,postCode,operatingHours)
VALUES ('Tacofino','Mexican','15 W Cordova St','V6B1C8','11:30-9');

INSERT INTO Menu(restaurantId, type)
VALUES (1,'Dinner');

INSERT INTO Menu(restaurantId, type)
VALUES (1,'Lunch');

INSERT INTO Menu(restaurantId, type)
VALUES (2,'All');

INSERT INTO Menu(restaurantId, type)
VALUES (3,'All');

INSERT INTO Menu(restaurantId, type)
VALUES (4,'Lunch');

INSERT INTO Menu(restaurantId, type)
VALUES (4,'Dinner');

INSERT INTO Menu(restaurantId, type)
VALUES (4,'Dessert');

INSERT INTO Menu(restaurantId, type)
VALUES (5,'All');

/* Chipotle Food Items for Dinner menuType*/

INSERT INTO Food(menuId,price,description,name)
VALUES (1,11.35,'Freshly grilled meat wrapped in a warm flour tortilla','Chicken Burrito');

INSERT INTO Food(menuId,price,description,name)
VALUES (1,11.70,'Freshly grilled meat in a delicious bowl','Steak Burrito Bowl');

/* Chipotle Food Items for Lunch menuType*/

INSERT INTO Food(menuId,price,description,name)
VALUES (2,11.35,'Freshly grilled meat wrapped in a warm flour tortilla','Chicken Burrito');

/* Burger King Food Items for All menuType*/

INSERT INTO Food(menuId,price,description,name)
VALUES (3,11.49,'2 quarter pound flame grilled beef patties','Roadhouse King');

INSERT INTO Food(menuId,price,description,name)
VALUES (3,11.49,'Whopper Sandwich (quarter pound beef) with fries and drink','Whopper Meal');

INSERT INTO Food(menuId,price,description,name)
VALUES (3,8.49,'Little version of Big King XL with fries and drink','Big Kind Jr. Meal');

INSERT INTO Food(menuId,price,description,name)
VALUES (3,3.99,'Served with marinara dipping sauce','Mozzarella Sticks');

INSERT INTO Food(menuId,price,description,name)
VALUES (3,4.49,'Hand spun Oreo shake','Oreo Shake');

/* Chatime Food Items for All menuType*/

INSERT INTO Food(menuId,price,description,name)
VALUES (4,5.99,'Cold Regular Size','Jasmine Green Milk Tea');

INSERT INTO Food(menuId,price,description,name)
VALUES (4,7.09,'Regular Size','Mango Smoothie');

INSERT INTO Food(menuId,price,description,name)
VALUES (4,6.29,'Cold Regular Size','Oolong Milk Tea');

INSERT INTO Food(menuId,price,description,name)
VALUES (4,6.79,'Hot Regular Size','Grass Jelly Roasted Milk Tea');

INSERT INTO Food(menuId,price,description,name)
VALUES (4,7.69,'Cold Regular Size','Thai Milk Tea with Pearls');

/* Hydra Food Items for Lunch menuType*/

INSERT INTO Food(menuId,price,description,name)
VALUES (5,20.00,'Chicken breast/red pepper spread/fries','The Burger');

INSERT INTO Food(menuId,price,description,name)
VALUES (5,48.00,'Marinated with herbs & lemon','Whole Forno Roasted Chicken');

INSERT INTO Food(menuId,price,description,name)
VALUES (5,19.00,'Local greens/feta/chick peas/tomato','The Big Salad');

INSERT INTO Food(menuId,price,description,name)
VALUES (5,17.00,'Chili/shallot/cilantro','Salmon Tartare');

INSERT INTO Food(menuId,price,description,name)
VALUES (5,19.00,'Fresh fire-grilled shrimp','Grilled Shrimp');

/* Hydra Food Items for Dinner menuType*/

INSERT INTO Food(menuId,price,description,name)
VALUES (6,26.00,'Traditional basked casserole','Moussaka');

INSERT INTO Food(menuId,price,description,name)
VALUES (6,36.00,'Seared ahi tuna','Ahi Tuna');

INSERT INTO Food(menuId,price,description,name)
VALUES (6,48.00,'Marinated with herbs & lemon','Whole Forno Roasted Chicken');

INSERT INTO Food(menuId,price,description,name)
VALUES (6,17.00,'Chili/shallot/cilantro','Salmon Tartare');

INSERT INTO Food(menuId,price,description,name)
VALUES (6,19.00,'Fresh fire-grilled shrimp','Grilled Shrimp');

/* Hydra Food Items for Dessert menuType*/

INSERT INTO Food(menuId,price,description,name)
VALUES (7,15.00,'Caramel-chocolate ganache','Bougatsa');

INSERT INTO Food(menuId,price,description,name)
VALUES (7,14.00,'Pistachio ice cream','Ice Cream and Baklava');

INSERT INTO Food(menuId,price,description,name)
VALUES (7,12.00,'Vodka topped with greek yogurt meringue','Pick Me Up');

INSERT INTO Food(menuId,price,description,name)
VALUES (7,30.00,'1oz cognac','Remy Martin XO');

INSERT INTO Food(menuId,price,description,name)
VALUES (7,12.00,'2oz port','Fonseca Bin 27');

/* Tacofino Food Items for All menuType*/

INSERT INTO Food(menuId,price,description,name)
VALUES (8,6.50,'Pacific cod/cabbage/chipotle mayo','Fish Taco');

INSERT INTO Food(menuId,price,description,name)
VALUES (8,13.00,'Spiced rice/cabbage/cilantro','Crispy Chicken Burrito');

INSERT INTO Food(menuId,price,description,name)
VALUES (8,8.50,'Cheddar and monterey jack','Cheese Quesadilla');

INSERT INTO Food(menuId,price,description,name)
VALUES (8,0.50,'Mild tomatillo & cilantro salsa','House Made Salsa Verde');

INSERT INTO Food(menuId,price,description,name)
VALUES (8,7.00,'Strawberry mojito','Kombucha');

INSERT INTO Deliverer(licenseNum, carPlate, phoneNumber, dName)
VALUES ('11223344','LMO34C','7785119923','Oscar');

INSERT INTO Deliverer(licenseNum, carPlate, phoneNumber, dName)
VALUES ('13399455','LM213C','7785219923','John');

INSERT INTO Deliverer(licenseNum, carPlate, phoneNumber, dName)
VALUES ('33445566','VTS123','7785221193','Sam');

INSERT INTO Deliverer(licenseNum, carPlate, phoneNumber, dName)
VALUES ('13579112','T34M4C','7785119923','Max');

INSERT INTO Deliverer(licenseNum, carPlate, phoneNumber, dName)
VALUES ('88776655','LM223C','7785219983','Amy');

INSERT INTO Deliverer(licenseNum, carPlate, phoneNumber, dName)
VALUES ('36363636','VTS125','7785789235','Bob');

INSERT INTO Deliverer(licenseNum, carPlate, phoneNumber, dName)
VALUES ('13131313','LMO38C','7785119865','Cindy');

INSERT INTO Deliverer(licenseNum, carPlate, phoneNumber, dName)
VALUES ('13399895','LM214C','7785219927','David');

INSERT INTO Deliverer(licenseNum, carPlate, phoneNumber, dName)
VALUES ('33445590','VTS123','7782821193','George');

INSERT INTO Deliverer(licenseNum, carPlate, phoneNumber, dName)
VALUES ('33445512','VTS393','7785985493','Jacob');

INSERT INTO Customer(address, cName, phoneNumber, email, postCode)
VALUES ('7388 Kingsway', 'Marry', '7782513635','marry@gmail.com','V3N0G9');

INSERT INTO Customer(address, cName, phoneNumber, email, postCode)
VALUES ('UBC', 'Mark', '7782513335','mark@gmail.com','V6T1Z4');

INSERT INTO Review(customerId, restaurantId, delivererId, comment, rating)
VALUES (1, 1, 1,'Fast delivery and fresh food. Recommend!',5);

INSERT INTO Review(customerId, restaurantId, delivererId, comment, rating)
VALUES (2, 1, 2,'Food arrived cold and stale. Do not recommend!',1);

INSERT INTO Review(customerId, restaurantId, delivererId, comment, rating)
VALUES (1, 2, 4,'Good food and selection, will order again.',4);