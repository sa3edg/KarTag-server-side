#add countries table
DROP TABLE IF EXISTS countries;
CREATE TABLE  countries (
  `country_id` int(20) NOT NULL AUTO_INCREMENT,
  `country_name` varchar(200) NOT NULL,
  PRIMARY KEY (`country_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
#end of add country table

#add communities table
DROP TABLE IF EXISTS communities;
CREATE TABLE  communities (
  `community_id` int(20) NOT NULL AUTO_INCREMENT,
  `country_id` int(20) NOT NULL,
  `community_name` varchar(200) NOT NULL,
  `effective_start_date` date NOT NULL,
  `effective_end_date` date NOT NULL,
  `dns_server` varchar(200) NOT NULL,
  PRIMARY KEY (`community_id`,`country_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
#end of add communities table

#add egypt to countried table
insert into countries(country_name) 
   values('Egypt');
#end of add egypt to countried table

#start add new fields
ALTER TABLE users ADD (country_id int(20) NOT NULL DEFAULT '11', source varchar(50) default NULL, 
                       device_id text default NULL , reg_time TIMESTAMP NOT NULL DEFAULT NOW() ,
                       `community_id` int(20) default 0 , `community_email` varchar(100) default NULL , 
                       `community_password` varchar(50) default NULL, 
                       `email` varchar(100) default NULL );
ALTER TABLE pools ADD country_id int(20) NOT NULL DEFAULT '11';
ALTER TABLE trips ADD community_id int(20) default 0;
ALTER TABLE trip_pools ADD trip_time TIMESTAMP NOT NULL DEFAULT NOW();
ALTER TABLE user_trips ADD trip_time TIMESTAMP NOT NULL DEFAULT NOW();
#nd of add new fields

#start update new fields with default time value
UPDATE pools SET country_id = '11';
UPDATE users SET reg_time = '2013-01-01 00:00:00' WHERE reg_time = '0000-00-00 00:00:00';
UPDATE trip_pools SET trip_time = '2013-01-01 00:00:00' WHERE trip_time = '0000-00-00 00:00:00';
UPDATE user_trips SET trip_time = '2013-01-01 00:00:00' WHERE trip_time = '0000-00-00 00:00:00';
#end update new fields with default time value

#add indecies for tables
CREATE UNIQUE INDEX messages_index ON messages (message_id);
CREATE UNIQUE INDEX reply_index ON replies (reply_id);
CREATE UNIQUE INDEX notification_index  ON notifications (notification_id);
CREATE UNIQUE INDEX user_updates_index ON user_updates (uid);
CREATE UNIQUE INDEX trips_index ON trips (trip_id);
CREATE UNIQUE INDEX user_trips_index  ON user_trips (trip_id,uid,trip_type);
CREATE UNIQUE INDEX pools_trips_index  ON trip_pools (trip_id,pool_id);
#end indecies

#start add system admin user
insert ignore  into system_users(user_name,user_pass) 
   values('admin',md5('admin22@'));
insert ignore  into user_roles(user_name,role_name) 
   values('admin','ADMIN');
#end add system admin user
