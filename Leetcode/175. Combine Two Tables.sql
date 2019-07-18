
Write a SQL query for a report that provides the following information for each person in the Person table, 
regardless if there is an address for each of those people:

FirstName, LastName, City, State




Table: Person

+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| PersonId    | int     |
| FirstName   | varchar |
| LastName    | varchar |
+-------------+---------+
PersonId is the primary key column for this table.


Table: Address

+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| AddressId   | int     |
| PersonId    | int     |
| City        | varchar |
| State       | varchar |
+-------------+---------+
AddressId is the primary key column for this table.




SELECT p.FirstName, p.LastName, addr.City, addr.State 
FROM Person p LEFT JOIN Address addr 
ON p.PersonId = addr.PersonId;



regardless if there is an address for each of those people:

这句话是关键 就是 人物表里有的人 那么他的 姓名都要拿出来 不管地址表有没有对应的 所以用 LEFT JOIN






