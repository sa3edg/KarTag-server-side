create database KARTAG;
grant all privileges on KARTAG.* to root@localhost;

 
--insert ignore  into trips ( uid , trip_time , trip_type , from_id , to_id , available_seats , user_count , cost , smoking_allowed , friends_only , women_only , status , comment) 
--   values('100005130069174', '2013-02-20 21:36:55' , 'OFFER' , '10' , '11' , '3' , '0' , '20' , '0' , '0' , '0' , 'Open' , 'no comments');
   
--insert ignore  into trips ( uid , trip_time , trip_type , from_id , to_id , available_seats , user_count , cost , smoking_allowed , friends_only , women_only , status , comment) 
--   values('100005130069174', '2013-03-28 21:36:55' , 'REQUEST' , '10' , '11' , '3' , '0' , '20' , '0' , '0' , '0' , 'Open' , 'no comments');

--insert ignore  into trips ( uid , trip_time , trip_type , from_id , to_id , available_seats , user_count , cost , smoking_allowed , friends_only , women_only , status , comment) 
--   values('100005132166273', '2013-02-20 21:36:55' , 'OFFER' , '11' , '10' , '3' , '0' , '20' , '0' , '0' , '0' , 'Open' , 'no comments');
   
--insert ignore  into trips ( uid , trip_time , trip_type , from_id , to_id , available_seats , user_count , cost , smoking_allowed , friends_only , women_only , status , comment) 
--   values('100005132166273', '2013-03-28 21:36:55' , 'REQUEST' , '11' , '10' , '3' , '0' , '20' , '0' , '0' , '0' , 'Open' , 'no comments');

--insert ignore  into users ( uid , user_name , name , first_name , middle_name , last_name , link , birthday , status) 
--   values('100005132166273', 'sa3eddg' , 'Said Gamal' , 'Said' , 'Gamal' , 'Said' , 'www.user.com' , '12-12-2012' , 'Active' );

--insert ignore  into users ( uid , user_name , name , first_name , middle_name , last_name , link , birthday , status) 
--   values('100005130069174', 'AhmedS' , 'Ahmed Saad' , 'Ahmed' , 'Sad' , 'Saad' , 'www.user.com' , '12-12-2012' , 'Active' );

--insert ignore  into system_users(user_name,user_pass) 
--   values('admin',md5('KARTAG@Admin1'));
--insert ignore  into user_roles(user_name,role_name) 
--   values('admin','ADMIN');
insert into user_trips(trip_id, uid , trip_type, trip_status) values ('48' , '100005602240165' , 'REQUEST' , 'REQUEST_SENT');
						  insert into notifications(trip_id, from_uid, to_uid, from_name, from_pool_name, to_pool_name, trip_time, notification_time, notification_type, status)
                          values ('48','100005602240165' , '100005130069174' ,'Said Gamal', 'AUC' , 'SV' , '2013-04-24 00:09:00' ,'2013-04-22 21:36:00', 'JOIN_REQUEST_TYPE' , 'REQUEST_SENT_STATUS');
