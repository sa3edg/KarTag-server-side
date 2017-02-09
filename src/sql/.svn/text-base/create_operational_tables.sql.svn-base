
DROP TABLE IF EXISTS `KARTAG`.`users`;
CREATE TABLE `KARTAG`.`users` (
                   uid  BIGINT NOT NULL ,
                   user_name    text NOT NULL,
                   name    text default NULL,
                   first_name   text default NULL,
                   middle_name   text default NULL,
                   last_name   text default NULL,
                   link   text default NULL,
                   birthday   varchar(200) default NULL,
                   gender     varchar(50) default NULL,
                   location_id int(30) default 0,
                   status     varchar(50) default NULL,
                   PRIMARY KEY  (uid)
                  ) ENGINE=MyISAM DEFAULT CHARSET=utf8;
                  
DROP TABLE IF EXISTS `KARTAG`.`locations`;
CREATE TABLE `KARTAG`.`locations` (
                   location_id int(30) NOT NULL,
                   uid BIGINT NOT NULL ,
                   street   text default NULL,
                   city   text default NULL,
                   state   text default NULL,
                   country   text default NULL,
                   zip_code   text default NULL,
                   latitude text  default NULL,
                   longitude text default NULL ,
                   PRIMARY KEY  (`location_id`,`uid`)
                 ) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `KARTAG`.`messages`;
CREATE TABLE `KARTAG`.`messages` (
                   message_id int(30) NOT NULL AUTO_INCREMENT,
                   from_uid BIGINT NOT NULL ,
                   to_uid BIGINT NOT NULL ,
                   from_name   text NOT NULL,
                   to_name   text NOT NULL,
                   message   text NOT NULL,
                   message_time DATETIME NOT NULL,
                   status   varchar(50) default NULL,
                   PRIMARY KEY  (message_id)
                 ) ENGINE=MyISAM DEFAULT CHARSET=utf8;
                 
DROP TABLE IF EXISTS `KARTAG`.`replies`;
CREATE TABLE `KARTAG`.`replies` (
                   reply_id int(30) NOT NULL AUTO_INCREMENT,
                   message_id int(30) NOT NULL,
                   from_uid BIGINT NOT NULL ,
                   to_uid BIGINT NOT NULL ,
                   from_name   text NOT NULL,
                   to_name   text NOT NULL,
                   message_text   text NOT NULL,
                   message_time DATETIME NOT NULL,
                   status   varchar(50) default NULL,
                   PRIMARY KEY  (reply_id)
                 ) ENGINE=MyISAM DEFAULT CHARSET=utf8;
                 
DROP TABLE IF EXISTS `KARTAG`.`notifications`;
CREATE TABLE `KARTAG`.`notifications` (
                   notification_id int(30) NOT NULL AUTO_INCREMENT,
                   trip_id int(30) NOT NULL,
                   from_uid BIGINT NOT NULL ,
                   to_uid BIGINT NOT NULL ,
                   from_name   text NOT NULL,
                   from_pool_name   text NOT NULL,
                   to_pool_name   text NOT NULL,
                   trip_time DATETIME NOT NULL,
                   notification_time DATETIME NOT NULL,
                   notification_type   varchar(50) default NULL,
                   status   varchar(50) default NULL,
                   PRIMARY KEY  (notification_id)
                 ) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `KARTAG`.`user_updates`;
CREATE TABLE `KARTAG`.`user_updates` (
                   uid  BIGINT NOT NULL ,
                   notifications_time DATETIME default NULL,
                   messages_time DATETIME default NULL,
                   replies_time DATETIME default NULL,
                   PRIMARY KEY  (uid)
                 ) ENGINE=MyISAM DEFAULT CHARSET=utf8;
                 
DROP TABLE IF EXISTS `KARTAG`.`feedback`;
CREATE TABLE `KARTAG`.`feedback` (
                   feedback_id int(30) NOT NULL AUTO_INCREMENT,
                   uid  BIGINT NOT NULL ,
                   feedback_type varchar(50) NOT NULL,
                   feedback_text   text default NULL,
                   PRIMARY KEY  (feedback_id)
                 ) ENGINE=MyISAM DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `KARTAG`.`trips`;
CREATE TABLE `KARTAG`.`trips` (
                   trip_id int(20) NOT NULL AUTO_INCREMENT,
                   uid BIGINT NOT NULL ,
                   trip_time    DATETIME NOT NULL,
                   trip_type     varchar(50) NOT NULL,
                   from_id   int NOT NULL,
                   to_id     int NOT NULL,
                   available_seats     int(3) NOT NULL,
                   user_count     int(3) default 0,
                   smoking_allowed     TINYINT default 1,
                   friends_only     TINYINT default 0,
                   women_only     TINYINT default 0,
                   status     varchar(50) default NULL,
                   comment   text default NULL,
                   rate   int default 0,
                   PRIMARY KEY  (trip_id)
                  ) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
                  
DROP TABLE IF EXISTS `KARTAG`.`user_trips`;
CREATE TABLE `KARTAG`.`user_trips` (
                   trip_id int(20) NOT NULL ,
                   uid BIGINT NOT NULL ,
                   trip_type  varchar(50) NOT NULL,
                   trip_status  varchar(50) NOT NULL,
                   PRIMARY KEY (`trip_id`,`uid`,`trip_type`)
                  ) ENGINE=MyISAM DEFAULT CHARSET=utf8;
                  
DROP TABLE IF EXISTS `KARTAG`.`trip_pools`;
CREATE TABLE `KARTAG`.`trip_pools` (
                   trip_id int(20) NOT NULL ,
                   pool_id int(20) NOT NULL ,
                   PRIMARY KEY (`trip_id`,`pool_id`)
                  ) ENGINE=MyISAM DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `KARTAG`.`pools`;
CREATE TABLE  `KARTAG`.`pools` (
  `pool_id` int(20) NOT NULL AUTO_INCREMENT,
  `pool_name` varchar(200) NOT NULL,
  PRIMARY KEY (`pool_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `KARTAG`.`users_channels`;
CREATE TABLE  `KARTAG`.`users_channels` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `channel_id` int(20) NOT NULL,
  `user_id` varchar(14) NOT NULL,
  `channel_name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `KARTAG`.`system_users`;
CREATE TABLE  `KARTAG`.`system_users` (
  `user_name` varchar(15) NOT NULL,
  `user_pass` varchar(50) NOT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `KARTAG`.`user_roles`;
CREATE TABLE  `KARTAG`.`user_roles` (
  `user_name` varchar(15) NOT NULL,
  `role_name` varchar(15) NOT NULL,
  PRIMARY KEY (`user_name`,`role_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TRIGGER IF EXISTS `KARTAG`.`ADD_TRIP_TRIGGER`;
delimiter ;;
CREATE TRIGGER `KARTAG`.`ADD_TRIP_TRIGGER`
AFTER INSERT ON `KARTAG`.`trips`
FOR EACH ROW
BEGIN
 INSERT INTO `KARTAG`.`trip_pools` (trip_id, pool_id)
       VALUES (NEW.trip_id, NEW.from_id);
 INSERT INTO `KARTAG`.`trip_pools` (trip_id, pool_id)
       VALUES (NEW.trip_id, NEW.to_id);
 INSERT INTO `KARTAG`.`user_trips` (trip_id, uid, trip_type, trip_status)
       VALUES (NEW.trip_id, NEW.uid, NEW.trip_type, 'OWNER');
END;;

insert into pools(pool_name) 
   values('Smart Village');
insert  into pools(pool_name) 
   values('CityStars');
insert  into pools(pool_name) 
   values('Carrefour');
insert  into pools(pool_name) 
   values('Mohandeseen');
 insert  into pools(pool_name) 
   values('Downtown');
insert  into pools(pool_name) 
   values('Maadi');
insert  into pools(pool_name) 
   values('Heliopolis');
insert  into pools(pool_name) 
   values('6 October City');
insert  into pools(pool_name) 
   values('AUC old campus');
insert  into pools(pool_name) 
   values('AUC new campus');
insert  into pools(pool_name) 
   values('GUC');
insert  into pools(pool_name) 
   values('Cairo University');
insert  into pools(pool_name) 
   values('	Ain Shams university');
insert  into pools(pool_name) 
   values('Heliopolis University');
insert into pools(pool_name) 
   values('TEDxAUC');
;;