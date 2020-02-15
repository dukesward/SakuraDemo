-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Feb 14, 2020 at 12:02 PM
-- Server version: 10.4.10-MariaDB
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kingdom`
--

-- --------------------------------------------------------

--
-- Table structure for table `character_attribute`
--

DROP TABLE IF EXISTS `character_attribute`;
CREATE TABLE IF NOT EXISTS `character_attribute` (
  `character_id` varchar(40) NOT NULL,
  `max_hp` int(11) NOT NULL DEFAULT 1,
  `max_sp` int(11) NOT NULL DEFAULT 1,
  `sp_type` varchar(25) NOT NULL,
  `strength` int(11) NOT NULL DEFAULT 1,
  `constitution` int(11) NOT NULL DEFAULT 1,
  `dexterity` int(11) NOT NULL DEFAULT 1,
  `intelligence` int(11) NOT NULL DEFAULT 1,
  `faith` int(11) NOT NULL DEFAULT 1,
  `luck` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`character_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `character_attribute`
--

INSERT INTO `character_attribute` (`character_id`, `max_hp`, `max_sp`, `sp_type`, `strength`, `constitution`, `dexterity`, `intelligence`, `faith`, `luck`) VALUES
('adf34460-1353-11ea-9302-704d7b4812f9', 30, 50, 'energy', 3, 3, 1, 1, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `character_class`
--

DROP TABLE IF EXISTS `character_class`;
CREATE TABLE IF NOT EXISTS `character_class` (
  `class_id` varchar(40) NOT NULL,
  `class_name` varchar(25) NOT NULL,
  `class_name_en` varchar(25) NOT NULL,
  `class_level` varchar(10) NOT NULL,
  `dominant_attr` varchar(3) NOT NULL,
  `nature_id` varchar(40) NOT NULL,
  PRIMARY KEY (`class_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `character_class`
--

INSERT INTO `character_class` (`class_id`, `class_name`, `class_name_en`, `class_level`, `dominant_attr`, `nature_id`) VALUES
('7835aa2f-401e-11ea-bd50-1c872cca58fe', '剑士', 'sword_man', 'copper', 'STR', '');

-- --------------------------------------------------------

--
-- Table structure for table `character_pool`
--

DROP TABLE IF EXISTS `character_pool`;
CREATE TABLE IF NOT EXISTS `character_pool` (
  `uuid` varchar(40) CHARACTER SET utf8 NOT NULL,
  `user_id` varchar(40) CHARACTER SET utf8 NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 NOT NULL,
  `name_en` varchar(40) CHARACTER SET utf8 NOT NULL,
  `status` varchar(5) CHARACTER SET utf8 NOT NULL,
  `race` varchar(20) CHARACTER SET utf8 NOT NULL,
  `gender` varchar(2) CHARACTER SET utf8 NOT NULL,
  `level` int(11) NOT NULL DEFAULT 1,
  `create_date` date NOT NULL,
  `class_profile` varchar(40) NOT NULL,
  `talent_id` varchar(40) NOT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `character_pool`
--

INSERT INTO `character_pool` (`uuid`, `user_id`, `name`, `name_en`, `status`, `race`, `gender`, `level`, `create_date`, `class_profile`, `talent_id`) VALUES
('adf34460-1353-11ea-9302-704d7b4812f9', '38576df2-1404-11ea-9302-704d7b4812f9', '奥斯特罗姆', 'Austrom', 'M', 'Viotana', 'M', 1, '2019-11-30', '7835aa2f-401e-11ea-bd50-1c872cca58fe', '2fb97f70-41d2-11ea-a0e2-1c872cca58fe');

-- --------------------------------------------------------

--
-- Table structure for table `character_talent`
--

DROP TABLE IF EXISTS `character_talent`;
CREATE TABLE IF NOT EXISTS `character_talent` (
  `talent_id` varchar(40) NOT NULL,
  `name` varchar(25) NOT NULL,
  `effect_group_id` varchar(40) NOT NULL,
  PRIMARY KEY (`talent_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `character_talent`
--

INSERT INTO `character_talent` (`talent_id`, `name`, `effect_group_id`) VALUES
('2fb97f70-41d2-11ea-a0e2-1c872cca58fe', '强韧', '1');

-- --------------------------------------------------------

--
-- Table structure for table `equip_pool`
--

DROP TABLE IF EXISTS `equip_pool`;
CREATE TABLE IF NOT EXISTS `equip_pool` (
  `equip_id` varchar(40) CHARACTER SET utf8 NOT NULL,
  `subtype` varchar(15) CHARACTER SET utf8 NOT NULL,
  `type` varchar(15) CHARACTER SET utf8 NOT NULL,
  `price` int(11) NOT NULL DEFAULT 0,
  `quality` varchar(1) CHARACTER SET utf8 NOT NULL,
  `quality_level` int(11) NOT NULL DEFAULT 1,
  `attr_type` varchar(1) NOT NULL DEFAULT 'F',
  `attrs` varchar(80) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`equip_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `equip_pool`
--

INSERT INTO `equip_pool` (`equip_id`, `subtype`, `type`, `price`, `quality`, `quality_level`, `attr_type`, `attrs`) VALUES
('765a2f35-2fce-11ea-9d9f-704d7b4812f9', 'belt', 'accessory', 8, 'N', 2, 'F', 'A=4');

-- --------------------------------------------------------

--
-- Table structure for table `event_pool`
--

DROP TABLE IF EXISTS `event_pool`;
CREATE TABLE IF NOT EXISTS `event_pool` (
  `event_id` varchar(40) CHARACTER SET utf8 NOT NULL,
  `title` varchar(50) CHARACTER SET utf8 NOT NULL,
  `type` varchar(50) CHARACTER SET utf8 NOT NULL,
  `create_date` date NOT NULL,
  `priority` int(11) NOT NULL DEFAULT 0,
  `repetitive` int(11) NOT NULL DEFAULT 1,
  `definition_id` varchar(40) CHARACTER SET utf8 NOT NULL,
  `quest_id` varchar(40) CHARACTER SET utf8 NOT NULL,
  `offer_id` varchar(40) NOT NULL,
  `publish_condition` int(11) DEFAULT NULL,
  `repeat_condition` int(11) NOT NULL,
  `location_condition` varchar(40) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`event_id`),
  UNIQUE KEY `event_title` (`title`),
  KEY `title` (`title`),
  KEY `event_id` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `event_pool`
--

INSERT INTO `event_pool` (`event_id`, `title`, `type`, `create_date`, `priority`, `repetitive`, `definition_id`, `quest_id`, `offer_id`, `publish_condition`, `repeat_condition`, `location_condition`) VALUES
('48986dd4-1510-11ea-9302-704d7b4812f9', '醒来', 'pub-ann', '2019-11-30', 0, 1, 'event_pub_ann_1', '945c6424-2e41-11ea-9d9f-704d7b4812f9', '', 10, 0, '9638747f-150e-11ea-9302-704d7b4812f9'),
('ae90138b-2fac-11ea-9d9f-704d7b4812f9', '新手礼包', 'pub-offer', '2020-01-05', 0, 1, 'event_pub_offer_1', '', '14e26428-2fc9-11ea-9d9f-704d7b4812f9', 10, 0, '0');

-- --------------------------------------------------------

--
-- Table structure for table `event_publish_condition`
--

DROP TABLE IF EXISTS `event_publish_condition`;
CREATE TABLE IF NOT EXISTS `event_publish_condition` (
  `code` int(10) NOT NULL,
  `topic` varchar(50) CHARACTER SET utf8 NOT NULL,
  `script_id` int(11) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `event_publish_condition`
--

INSERT INTO `event_publish_condition` (`code`, `topic`, `script_id`) VALUES
(10, 'novice_only', 76);

-- --------------------------------------------------------

--
-- Table structure for table `inventory_item`
--

DROP TABLE IF EXISTS `inventory_item`;
CREATE TABLE IF NOT EXISTS `inventory_item` (
  `inventory_id` varchar(40) CHARACTER SET utf8 NOT NULL,
  `item_id` varchar(40) CHARACTER SET utf8 NOT NULL,
  `create_date` date NOT NULL,
  `stack_amount` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`inventory_id`,`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `inventory_item`
--

INSERT INTO `inventory_item` (`inventory_id`, `item_id`, `create_date`, `stack_amount`) VALUES
('18a699f3-3799-11ea-9d9f-704d7b4812f9', '1d43aafb-2fd2-11ea-9d9f-704d7b4812f9', '2020-01-19', 1);

-- --------------------------------------------------------

--
-- Table structure for table `item_pool`
--

DROP TABLE IF EXISTS `item_pool`;
CREATE TABLE IF NOT EXISTS `item_pool` (
  `item_id` varchar(40) CHARACTER SET utf8 NOT NULL,
  `name` varchar(40) CHARACTER SET utf8 NOT NULL,
  `type` varchar(25) CHARACTER SET utf8 NOT NULL,
  `info_id` varchar(40) NOT NULL,
  `stack_limit` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`item_id`),
  UNIQUE KEY `info_id` (`info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `item_pool`
--

INSERT INTO `item_pool` (`item_id`, `name`, `type`, `info_id`, `stack_limit`) VALUES
('1d43aafb-2fd2-11ea-9d9f-704d7b4812f9', '皮革束带', 'equipment', '765a2f35-2fce-11ea-9d9f-704d7b4812f9', 1),
('53bb0400-2f14-11ea-9d9f-704d7b4812f9', '钱币', 'money', '0', 1);

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
CREATE TABLE IF NOT EXISTS `location` (
  `uuid` varchar(40) CHARACTER SET utf8 NOT NULL,
  `name` varchar(25) CHARACTER SET utf8 NOT NULL,
  `location_id` varchar(25) CHARACTER SET utf8 NOT NULL,
  `parent` varchar(40) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `location_id` (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `location`
--

INSERT INTO `location` (`uuid`, `name`, `location_id`, `parent`) VALUES
('216aef33-150f-11ea-9302-704d7b4812f9', '劫难堡', 'fort_nugras', '216aef33-150f-11ea-9302-704d7b4812f9'),
('9638747f-150e-11ea-9302-704d7b4812f9', '阿格诺恩地牢', 'agnorn_dungeon', '216aef33-150f-11ea-9302-704d7b4812f9');

-- --------------------------------------------------------

--
-- Table structure for table `objective_pool`
--

DROP TABLE IF EXISTS `objective_pool`;
CREATE TABLE IF NOT EXISTS `objective_pool` (
  `objective_id` varchar(40) CHARACTER SET utf8 NOT NULL,
  `difficulty_level` int(11) NOT NULL DEFAULT 0,
  `objective_type` varchar(20) CHARACTER SET utf8 NOT NULL,
  `series_id` varchar(5) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`objective_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `objective_pool`
--

INSERT INTO `objective_pool` (`objective_id`, `difficulty_level`, `objective_type`, `series_id`) VALUES
('be99f539-2edc-11ea-9d9f-704d7b4812f9', 0, 'survive', 'SV1');

-- --------------------------------------------------------

--
-- Table structure for table `offer_pool`
--

DROP TABLE IF EXISTS `offer_pool`;
CREATE TABLE IF NOT EXISTS `offer_pool` (
  `offer_id` varchar(40) CHARACTER SET utf8 NOT NULL,
  `offer_type` varchar(25) CHARACTER SET utf8 NOT NULL,
  `reward_id` varchar(40) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`offer_id`,`reward_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `offer_pool`
--

INSERT INTO `offer_pool` (`offer_id`, `offer_type`, `reward_id`) VALUES
('14e26428-2fc9-11ea-9d9f-704d7b4812f9', 'event_gift', '5108de05-2fc9-11ea-9d9f-704d7b4812f9'),
('979ba892-442a-11ea-84b4-1c872cca58fe', 'event_scene', '');

-- --------------------------------------------------------

--
-- Table structure for table `quest_objective`
--

DROP TABLE IF EXISTS `quest_objective`;
CREATE TABLE IF NOT EXISTS `quest_objective` (
  `quest_id` varchar(40) CHARACTER SET utf8 NOT NULL,
  `objective_id` varchar(40) CHARACTER SET utf8 NOT NULL,
  `reward_id` varchar(40) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`quest_id`,`objective_id`,`reward_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `quest_objective`
--

INSERT INTO `quest_objective` (`quest_id`, `objective_id`, `reward_id`) VALUES
('945c6424-2e41-11ea-9d9f-704d7b4812f9', 'be99f539-2edc-11ea-9d9f-704d7b4812f9', '1035f03f-2eda-11ea-9d9f-704d7b4812f9');

-- --------------------------------------------------------

--
-- Table structure for table `quest_pool`
--

DROP TABLE IF EXISTS `quest_pool`;
CREATE TABLE IF NOT EXISTS `quest_pool` (
  `quest_id` varchar(40) CHARACTER SET utf8 NOT NULL,
  `require_level` int(11) NOT NULL DEFAULT 1,
  `objective_number` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`quest_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `quest_pool`
--

INSERT INTO `quest_pool` (`quest_id`, `require_level`, `objective_number`) VALUES
('945c6424-2e41-11ea-9d9f-704d7b4812f9', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `reward_pool`
--

DROP TABLE IF EXISTS `reward_pool`;
CREATE TABLE IF NOT EXISTS `reward_pool` (
  `reward_id` varchar(40) CHARACTER SET utf8 NOT NULL,
  `reward_type` varchar(20) CHARACTER SET utf8 NOT NULL,
  `amount_type` varchar(2) CHARACTER SET utf8 NOT NULL,
  `factor` int(11) NOT NULL DEFAULT 1,
  `item_id` varchar(40) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`reward_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `reward_pool`
--

INSERT INTO `reward_pool` (`reward_id`, `reward_type`, `amount_type`, `factor`, `item_id`) VALUES
('1035f03f-2eda-11ea-9d9f-704d7b4812f9', 'experience', 'F', 20, '0'),
('5108de05-2fc9-11ea-9d9f-704d7b4812f9', 'equipment', 'F', 1, '1d43aafb-2fd2-11ea-9d9f-704d7b4812f9'),
('b1acdf22-442a-11ea-84b4-1c872cca58fe', 'equipment', 'F', 1, '2');

-- --------------------------------------------------------

--
-- Table structure for table `user_event_record`
--

DROP TABLE IF EXISTS `user_event_record`;
CREATE TABLE IF NOT EXISTS `user_event_record` (
  `event_id` varchar(40) CHARACTER SET utf8 NOT NULL,
  `user_id` varchar(40) CHARACTER SET utf8 NOT NULL,
  `publish_count` int(11) NOT NULL DEFAULT 0,
  `update_time` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`event_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_event_record`
--

INSERT INTO `user_event_record` (`event_id`, `user_id`, `publish_count`, `update_time`) VALUES
('48986dd4-1510-11ea-9302-704d7b4812f9', '38576df2-1404-11ea-9302-704d7b4812f9', 1, NULL),
('ae90138b-2fac-11ea-9d9f-704d7b4812f9', '38576df2-1404-11ea-9302-704d7b4812f9', 1, '2020-01-19 07:42:37');

-- --------------------------------------------------------

--
-- Table structure for table `user_inventory`
--

DROP TABLE IF EXISTS `user_inventory`;
CREATE TABLE IF NOT EXISTS `user_inventory` (
  `user_id` varchar(40) CHARACTER SET utf8 NOT NULL,
  `inventory_id` varchar(40) CHARACTER SET utf8 NOT NULL,
  `stack_limit` int(11) NOT NULL DEFAULT 1,
  `stack_volume` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_inventory`
--

INSERT INTO `user_inventory` (`user_id`, `inventory_id`, `stack_limit`, `stack_volume`) VALUES
('38576df2-1404-11ea-9302-704d7b4812f9', '18a699f3-3799-11ea-9d9f-704d7b4812f9', 16, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user_profile`
--

DROP TABLE IF EXISTS `user_profile`;
CREATE TABLE IF NOT EXISTS `user_profile` (
  `uuid` varchar(40) CHARACTER SET utf8 NOT NULL,
  `name` varchar(25) CHARACTER SET utf8 NOT NULL,
  `characters` int(11) NOT NULL DEFAULT 1,
  `location_id` varchar(40) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_profile`
--

INSERT INTO `user_profile` (`uuid`, `name`, `characters`, `location_id`) VALUES
('38576df2-1404-11ea-9302-704d7b4812f9', 'admin', 1, '9638747f-150e-11ea-9302-704d7b4812f9');

-- --------------------------------------------------------

--
-- Table structure for table `user_quest_record`
--

DROP TABLE IF EXISTS `user_quest_record`;
CREATE TABLE IF NOT EXISTS `user_quest_record` (
  `user_id` varchar(40) CHARACTER SET utf8 NOT NULL,
  `quest_id` varchar(40) CHARACTER SET utf8 NOT NULL,
  `objective_id` varchar(40) CHARACTER SET utf8 NOT NULL,
  `task_count` int(11) NOT NULL DEFAULT 1,
  `task_progress` int(11) NOT NULL DEFAULT 0,
  `status` int(11) NOT NULL DEFAULT 0,
  `create_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `update_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `expire_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`user_id`,`quest_id`,`objective_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_quest_record`
--

INSERT INTO `user_quest_record` (`user_id`, `quest_id`, `objective_id`, `task_count`, `task_progress`, `status`, `create_time`, `update_time`, `expire_time`) VALUES
('38576df2-1404-11ea-9302-704d7b4812f9', '945c6424-2e41-11ea-9d9f-704d7b4812f9', 'be99f539-2edc-11ea-9d9f-704d7b4812f9', 1, 0, 0, '2020-01-05 18:16:59', '2020-01-05 18:16:59', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `user_session`
--

DROP TABLE IF EXISTS `user_session`;
CREATE TABLE IF NOT EXISTS `user_session` (
  `user_id` varchar(40) NOT NULL,
  `session_id` varchar(40) NOT NULL,
  `validate_time` int(11) NOT NULL DEFAULT 0,
  `create_time` date NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_session`
--

INSERT INTO `user_session` (`user_id`, `session_id`, `validate_time`, `create_time`) VALUES
('38576df2-1404-11ea-9302-704d7b4812f9', 'da39a3ee5e6b4b0d3255bfef95601890afd80709', 0, '2020-02-05');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
