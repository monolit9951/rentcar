SELECT *, DATE_FORMAT(date,  '%d %M %Y') AS `date` FROM
table WHERE start='$search' AND end = '$search1' AND date BETWEEN curdate() AND curdate() + interval 2 month ORDER BY date DESC

SELECT *FROM orders WHERE borningDate BETWEEN '1980-01-01' AND '1997-01-01' AND reason IS NOT NULL  ORDER BY price DESC;
SELECT users.id , orders.price FROM users INNER JOIN orders ON users.id=orders.userId AND orders.price>100 ORDER BY orders.price ASC;
-- find all users who bought
