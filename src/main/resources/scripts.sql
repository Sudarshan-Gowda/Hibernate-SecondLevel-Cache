CREATE TABLE `address` (
  `address_line1` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  `emp_id` bigint(20) NOT NULL,
  PRIMARY KEY (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `employee` (
  `emp_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(255) DEFAULT NULL,
  `emp_salary` double DEFAULT NULL,
  PRIMARY KEY (`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*
-- Query: SELECT * FROM research.employee
LIMIT 0, 1000

-- Date: 2020-02-26 11:15
*/
INSERT INTO `employee` (`emp_id`,`emp_name`,`emp_salary`) VALUES (1,'John',10000);
INSERT INTO `employee` (`emp_id`,`emp_name`,`emp_salary`) VALUES (2,'Chris',20000);


/*
-- Query: SELECT * FROM research.address
LIMIT 0, 1000

-- Date: 2020-02-26 11:14
*/
INSERT INTO `address` (`emp_id`,`address_line1`,`city`,`zipcode`) VALUES (1,'E City','Bangalore','560006');
INSERT INTO `address` (`emp_id`,`address_line1`,`city`,`zipcode`) VALUES (2,'W Filed','Bangalore','560060');
