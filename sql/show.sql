use parking;    
DELETE FROM locked_users where userId=1;
SELECT * from orders;
SELECT * from accounts;
SELECT * from users;
SELECT * from orders_cars;
SELECT * from cars INNER JOIN orders_cars on cars.id=orders_cars.carId;

SELECT *FROM orders WHERE borningDate BETWEEN '1980-01-01' AND '1997-01-01' AND reason IS NOT NULL  ORDER BY price DESC;
SELECT users.id , orders.price FROM users INNER JOIN orders ON users.id=orders.userId AND orders.price>100 ORDER BY orders.price ASC;
SELECT * from users ORDER BY login DESC;