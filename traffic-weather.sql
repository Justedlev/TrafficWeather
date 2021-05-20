CREATE DATABASE IF NOT EXISTS `traffic weather`;

USE `traffic weather`;

CREATE TABLE IF NOT EXISTS traffic(
object_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
id TINYTEXT NOT NULL,
last_heartbeat BIGINT NOT NULL,
device_longitude DECIMAL(10,6) NOT NULL,
device_latitude DECIMAL(10,6) NOT NULL,
device_height INT,
enabled BOOLEAN NOT NULL DEFAULT false,
connected BOOLEAN NOT NULL DEFAULT false,
PRIMARY KEY (object_id));

INSERT INTO `traffic` VALUES 
(1, 'USA-027', 1581948022026, -74.005974, 40.712776, null, true, true);