SELECT * FROM usertable;

SELECT * FROM usertable WHERE roleID = 1;

#两表通过外键关联
SELECT * FROM usertable A, role B WHERE A.roleID = B.roleID; 

#LEFT JOIN 使用 主表中有而副表中没有，那么副表中全部接null到主表
#SELECT * FROM 主表 LEFT JOIN 副表 ON 条件
SELECT * FROM usertable A LEFT JOIN role B ON A.roleID = B.roleID;

#筛选下我需要的字段
SELECT A.userID, A.userMail, A.userName, A.userPassword, B.roleID, B.roleName FROM usertable A LEFT JOIN role B ON A.roleID = B.roleID;

#有中间表， 两层LEFT JOIN
SELECT * FROM usertable A LEFT JOIN userrole B ON A.userID = B.userID LEFT JOIN role C ON B.roleID = C.roleID WHERE B.IsEffective = 1;

#GROUP_CONCAT(需要被整合的字段)
SELECT A.userID, B.roleID, group_concat(B.moduleID) AS moduleIDs FROM usertable A, rolemodule B WHERE A.roleID = B.roleID AND B.isEffective = 1 AND A.userID = 2;

#取出user对应的module名称
SELECT A.userID, B.roleID, C.moduleName FROM usertable A, rolemodule B, module C WHERE A.roleID = B.roleID AND B.isEffective = 1 AND A.userID = 2 AND B.moduleID = C.moduleID;

#取出user对应的module名称 并 整合
SELECT A.userID, B.roleID, GROUP_CONCAT(moduleName) FROM usertable A, rolemodule B, module C WHERE A.roleID = B.roleID AND B.IsEffective = 1 AND
A.userID = 1 AND C.moduleID = B.moduleID;