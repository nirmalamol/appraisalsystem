INSERT INTO employee (empid, name,currentctc,empdoj,ISHRDISCUSSION_DONE,IS_MANAGER_DISCUSSION_DONE,IS_ON_NOTICE,MANAGERID)
    VALUES (1, 'Rajesh',1500000,'2022-01-01T04:30:49.493',true,true,false,2);
INSERT INTO employee (empid, name,currentctc,empdoj,ISHRDISCUSSION_DONE,IS_MANAGER_DISCUSSION_DONE,IS_ON_NOTICE,MANAGERID)
    VALUES (2, 'Ganesh',2000000,'2020-03-14T04:30:49.493',false,false,false,0);
INSERT INTO employee (empid, name,currentctc,empdoj,ISHRDISCUSSION_DONE,IS_MANAGER_DISCUSSION_DONE,IS_ON_NOTICE,MANAGERID)
    VALUES (3, 'Tejashree',600000,'2021-04-05T04:30:49.493',true,false,false,2);
INSERT INTO employee (empid, name,currentctc,empdoj,ISHRDISCUSSION_DONE,IS_MANAGER_DISCUSSION_DONE,IS_ON_NOTICE,MANAGERID)
    VALUES (4, 'Vivek',400000,'2023-07-01T04:30:49.493',true,true,false,2);
INSERT INTO employee (empid, name,currentctc,empdoj,ISHRDISCUSSION_DONE,IS_MANAGER_DISCUSSION_DONE,IS_ON_NOTICE,MANAGERID)
    VALUES (5, 'Reshma',300000,'2014-04-29T04:30:49.493',false,false,false,0);
INSERT INTO employee (empid, name,currentctc,empdoj,ISHRDISCUSSION_DONE,IS_MANAGER_DISCUSSION_DONE,IS_ON_NOTICE,MANAGERID)
    VALUES (6, 'Pragati',200000,'2022-08-10T04:30:49.493',true,true,false,5);
INSERT INTO employee (empid, name,currentctc,empdoj,ISHRDISCUSSION_DONE,IS_MANAGER_DISCUSSION_DONE,IS_ON_NOTICE,MANAGERID)
    VALUES (7, 'Venkatesh',800000,'2019-01-18T04:30:49.493',false,false,false,0);
INSERT INTO employee (empid, name,currentctc,empdoj,ISHRDISCUSSION_DONE,IS_MANAGER_DISCUSSION_DONE,IS_ON_NOTICE,MANAGERID)
    VALUES (8, 'Mahesh',300000,'2020-11-12T04:30:49.493',true,true,false,7);
INSERT INTO employee (empid, name,currentctc,empdoj,ISHRDISCUSSION_DONE,IS_MANAGER_DISCUSSION_DONE,IS_ON_NOTICE,MANAGERID)
    VALUES (9, 'Kalyani',350000,'2021-01-04T04:30:49.493',true,true,true,7);

INSERT INTO appraisal_details(empid,is_exceptionalemp,bonus,starrating,percent_hike,comments,oldCTC,training_required,pip,updatedcTC,effectivedate,createddate)
    VALUES (1, false,0,5,10,'',1500000,false,false,2000000,'2022-01-01T04:30:49.493','2022-01-01T04:30:49.493');