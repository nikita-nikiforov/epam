USE Labor_SQL;

SELECT * FROM Product;

-- (Використання підзапитів у конструкції WHERE з IN, ANY, ALL)
-- 1
SELECT maker FROM Product
	WHERE type = 'PC' 
    AND maker NOT IN (SELECT DISTINCT maker FROM Product WHERE type = 'Laptop')
    GROUP BY maker;

-- 2
SELECT maker FROM Product
	WHERE type = 'PC'
    AND maker <> ALL (SELECT DISTINCT maker FROM Product WHERE type = 'Laptop')
    GROUP BY maker;

-- 3
SELECT maker FROM Product
	WHERE type = 'PC'
    AND maker <> ANY (SELECT DISTINCT maker FROM Product WHERE type = 'Laptop')
    GROUP BY maker;

-- 4
SELECT maker FROM Product
	WHERE type = 'PC'
    AND maker IN (SELECT DISTINCT maker FROM Product WHERE type = 'Laptop')
    GROUP BY maker;
    
-- 5 WRONG
SELECT maker FROM Product P1
	WHERE type = 'PC'
    AND maker = ALL (SELECT DISTINCT maker FROM Product P2 WHERE type = 'Laptop' AND P2.maker = P1.maker)
    GROUP BY maker;

-- 6
SELECT maker FROM Product
	WHERE type = 'PC'
	AND maker = ANY (SELECT DISTINCT maker FROM Product WHERE type = 'Laptop')
    GROUP BY maker;
    
-- 7
SELECT maker FROM Product P
	LEFT JOIN PC USING(model)
    WHERE P.model IN (SELECT model FROM PC)
    GROUP BY maker;

-- 8 TODO
SELECT country, class FROM Classes;

-- 9
SELECT * FROM Ships S
	JOIN Outcomes O ON O.ship = S.name
    JOIN Battles B ON B.name = O.battle
    WHERE result = 'damaged'
    AND ship IN (SELECT ship FROM Battles
				JOIN Outcomes ON Outcomes.battle = Battles.name
                WHERE date > B.date);
                
-- 10
SELECT COUNT(*) Available_Computers FROM Product
	WHERE maker = 'A' AND type IN ('PC', 'Laptop');
    
-- 11
SELECT maker FROM Product
	WHERE type = 'PC' AND model NOT IN (SELECT model FROM PC)
    GROUP BY maker;

-- 12
SELECT model, price FROM Product
	JOIN Laptop USING (model)
    WHERE price > ANY (SELECT price FROM PC);
    
-- Використання підзапитів з лог. операцією EXISTS
-- 1
SELECT maker FROM Product P
	WHERE EXISTS (SELECT * FROM PC
					WHERE PC.model = P.model)
    GROUP BY maker;
    
-- 2
SELECT maker FROM Product P
	WHERE EXISTS (SELECT * FROM PC 
					WHERE speed >= 750
                    AND PC.model = P.model)
	GROUP BY maker;

-- 3
SELECT maker FROM Product P
	WHERE EXISTS (SELECT * FROM PC
					WHERE PC.model = P.model AND speed >= 750)
	AND EXISTS (SELECT * FROM Laptop L
				JOIN Product ON Product.model = L.model
				WHERE speed >=750
                AND Product.maker = P.maker);

-- 4 

-- 5
SELECT name, launched, displacement FROM Classes
	JOIN Ships S USING (class)
	WHERE displacement >= 35000
    AND S.launched > 1922;
    
-- 6
SELECT * FROM Outcomes O
	WHERE result = 'sunk'
    AND (EXISTS (SELECT * FROM Ships S
				WHERE S.name = O.ship)
		OR EXISTS (SELECT * FROM Classes C
					WHERE C.class = O.ship));
                    
-- 7
SELECT maker FROM Product P
	WHERE type = 'Laptop'
    AND EXISTS (SELECT * FROM Product P1
				WHERE P1.maker = P.maker
                AND type = 'Printer')
	GROUP BY maker;
                
-- 8
SELECT maker FROM Product P
	WHERE type = 'Laptop'
    AND NOT EXISTS (SELECT * FROM Product P1
				WHERE P1.maker = P.maker
                AND type = 'Printer')
	GROUP BY maker;


-- Конкатенація стрічок, мат. обчислення, робота з датами
-- 1
SELECT CONCAT('Середня ціна = ', CAST(AVG(price) AS CHAR)) AS Result FROM Laptop;

-- 2
SELECT CONCAT('code: ', code) AS code, CONCAT('model: ', model) AS model,
		CONCAT('speed: ', speed) AS speed, CONCAT('ram: ', ram) AS ram,
        CONCAT('hd: ', hd) AS hd, CONCAT('cd: ', cd) AS cd, 
        CONCAT('price: ', price) AS price FROM PC;
        
-- 3
SELECT DATE_FORMAT(date, '%Y.%m.%d') AS date FROM Income;

-- 4
SELECT ship, battle, CASE result
						WHEN 'sunk' THEN 'затонув'
						WHEN 'OK' THEN 'ОК'
						WHEN 'damaged' THEN 'пошкоджений'
					END AS result FROM Outcomes;

-- 5
SELECT trip_no, date, ID_psg, CONCAT('row: ', SUBSTR(place, 1, 1)) AS row,
		CONCAT('place: ', SUBSTR(place, 2, 1)) as place FROM Pass_in_trip;

-- 6 
SELECT trip_no, plane, CONCAT('From ', town_from, ' to ', town_to) AS route, time_out, time_in FROM Trip;


-- Статистичні функції та робота з групами
-- 1
SELECT model, price FROM Printer
	WHERE price = (SELECT MAX(price) FROM Printer);
    
-- 2
SELECT * FROM Laptop
	WHERE speed < ANY(SELECT speed FROM PC);
    
-- 3
SELECT maker, price FROM Product
	JOIN Printer USING (model)
	WHERE price = (SELECT MIN(price) FROM Printer);
    
-- 4
SELECT maker FROM Product P1
    WHERE EXISTS (SELECT maker FROM Product P2
					JOIN PC USING (model)
                    WHERE P2.maker = P1.maker
                    GROUP BY maker
                    HAVING COUNT(model) > 2)
	GROUP BY maker;

-- 5
SELECT AVG(hd) FROM PC
	JOIN Product P1 USING (model)
	WHERE EXISTS (SELECT * FROM Product P2
                    WHERE P1.maker = P2.maker
                    AND P2.type = 'Printer');
                    
-- 6
SELECT DISTINCT DATE_FORMAT(time_out, '%Y.%m.%d') AS date, 
					(SELECT COUNT(*) FROM Trip T2
						WHERE DATE_FORMAT(T1.time_out, '%Y.%m.%d') =
						DATE_FORMAT(T2.time_out, '%Y.%m.%d')
                        AND T2.town_from = 'London') AS flies
					FROM Trip T1;
				
-- 7 
SELECT DISTINCT DATE_FORMAT(time_out, '%Y.%m.%d') AS date, (SELECT COUNT(*) FROM Trip T2
																WHERE DATE_FORMAT(T1.time_out, '%Y.%m.%d') =
                                                                DATE_FORMAT(T2.time_out, '%Y.%m.%d')
                                                                AND T2.town_to = 'Moscow') AS flies
												FROM Trip T1;
                                                
-- 8 REDO
SELECT DISTINCT country, COUNT(launched), MAX(launched) FROM Ships S1
	JOIN Classes C1 USING (class)
    GROUP BY country, launched;
    
-- (Підзапити в якості обчислювальних стовпців)
-- 1 
SELECT maker, (SELECT COUNT(*) FROM PC
				JOIN Product P2 USING (model)
				WHERE P2.maker = P1.maker) AS PC,
                (SELECT COUNT(*) FROM Laptop
                JOIN Product P3 USING (model)
                WHERE P3.maker = P1.maker) AS Laptops,
                (SELECT COUNT(*) FROM Printer
                JOIN Product P4 USING (model)
                WHERE P4.maker = P1.maker) AS Printers
                FROM Product P1
                GROUP BY maker;

-- 2
SELECT maker, AVG(screen) AS Average_Screen FROM Product
	JOIN Laptop USING (model)
    GROUP BY maker;
    
-- 3
SELECT maker, MAX(price) FROM Product
	JOIN PC USING (model)
    GROUP BY maker;

-- 4
SELECT maker, MIN(price) FROM Product
	JOIN PC USING (model)
    GROUP BY maker;

-- 5
SELECT speed, AVG(price) FROM PC
	WHERE speed > 600
	GROUP BY speed;

-- 6
SELECT maker, AVG(hd) FROM Product P1
	JOIN PC USING (model)
    WHERE EXISTS (SELECT * FROM Product P2
					WHERE P2.maker = P1.maker
                    AND type = 'Printer')
	GROUP BY maker;
    
-- 7
SELECT ship, (SELECT DISTINCT displacement FROM Classes C1
				WHERE C1.class = S1.class) AS displacement, 
                (SELECT DISTINCT numGuns FROM Classes C1
                WHERE C1.class = S1.class) AS numGuns FROM Ships S1
    JOIN Outcomes ON Outcomes.ship = S1.name
    WHERE battle = 'Guadalcanal';
    
-- CASE
-- 1
SELECT maker, model,  CASE type
					WHEN 'PC' THEN CONCAT('yes (', CAST((SELECT COUNT(*) FROM PC
														JOIN Product P2 USING (model)
														WHERE P2.maker = P1.maker) AS CHAR), ')')
                    ELSE 'no'
				END AS PC
		FROM Product P1;
        
-- 2
SELECT O.point, O.date, inc, `out` FROM Outcome_o O
	JOIN Income_o I USING (point)
    WHERE O.date = I.date;
    
-- 3
SELECT DISTINCT CASE
			WHEN numGuns = 8 THEN S.name
            WHEN type = 'bb' THEN S.name
            WHEN country = 'USA' THEN S.name
            WHEN launched = 1915 THEN S.name
            WHEN class = 'Kongo' THEN S.name
		END AS result
	FROM Classes
    JOIN Ships S USING (class);
    
-- UNION
-- 1
SELECT maker, model, type, price FROM Product
	JOIN PC USING (model)
UNION
SELECT maker, model, type, price FROM Product
	JOIN Laptop USING (model)
UNION
SELECT maker, model, Product.type, price FROM Product
	JOIN Printer USING (model);
    
-- 2
SELECT type, model, MAX(price) FROM Product
	JOIN PC USING (model)
    GROUP BY type, model
UNION
SELECT type, model, MAX(price) FROM Product
	JOIN Laptop USING (model)
    GROUP BY type, model
UNION
SELECT Product.type, model, MAX(price) FROM Product
	JOIN Printer USING (model)
    GROUP BY type, model;
    
-- 3
SELECT AVG(price) AS Average_Price FROM (SELECT * FROM Product
						JOIN PC USING (model)
                        WHERE maker = 'A'
                        UNION
                        SELECT * FROM Product
						JOIN Laptop USING (model)
                        WHERE maker = 'A') A;

-- 4
SELECT S.name, class FROM Ships S
UNION
SELECT ship, class FROM Outcomes
	LEFT JOIN Ships ON Ships.name = Outcomes.ship