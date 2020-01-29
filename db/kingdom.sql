-- phpMyAdmin SQL Dump
-- version 4.4.15.9
-- https://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: Jan 24, 2020 at 01:28 AM
-- Server version: 8.0.12
-- PHP Version: 7.1.11


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

CREATE TABLE IF NOT EXISTS `character_attribute` (
  `character_id` varchar(40) NOT NULL,
  `max_hp` int(11) NOT NULL DEFAULT '1',
  `max_sp` int(11) NOT NULL DEFAULT '1',
  `sp_type` varchar(25) NOT NULL,
  `strength` int(11) NOT NULL DEFAULT '1',
  `constitution` int(11) NOT NULL DEFAULT '1',
  `dexterity` int(11) NOT NULL DEFAULT '1',
  `intelligence` int(11) NOT NULL DEFAULT '1',
  `wisdom` int(11) NOT NULL DEFAULT '1',
  `luck` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `character_attribute`
--

-- --------------------------------------------------------

--
-- Table structure for table `character_pool`
--

CREATE TABLE IF NOT EXISTS `character_pool` (
  `uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name_en` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `race` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `level` int(11) NOT NULL DEFAULT '1',
  `create_date` date NOT NULL,
  `class_profile` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `character_pool`
--

INSERT INTO `character_pool` (`uuid`, `user_id`, `name`, `name_en`, `status`, `race`, `gender`, `level`, `create_date`, `class_profile`) VALUES
('adf34460-1353-11ea-9302-704d7b4812f9', '38576df2-1404-11ea-9302-704d7b4812f9', '奥斯特罗姆', 'Austrom', 'M', 'Viotana', 'M', 1, '2019-11-30', 1);

-- --------------------------------------------------------

--
-- Table structure for table `equip_pool`
--

CREATE TABLE IF NOT EXISTS `equip_pool` (
  `equip_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `subtype` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` int(11) NOT NULL DEFAULT '0',
  `quality` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `quality_level` int(11) NOT NULL DEFAULT '1',
  `attr_type` varchar(1) NOT NULL DEFAULT 'F',
  `attrs` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `equip_pool`
--

INSERT INTO `equip_pool` (`equip_id`, `subtype`, `type`, `price`, `quality`, `quality_level`, `attr_type`, `attrs`) VALUES
('765a2f35-2fce-11ea-9d9f-704d7b4812f9', 'belt', 'accessory', 8, 'N', 2, 'F', 'A=4');

-- --------------------------------------------------------

--
-- Table structure for table `event_pool`
--

CREATE TABLE IF NOT EXISTS `event_pool` (
  `event_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` date NOT NULL,
  `repetitive` int(11) NOT NULL DEFAULT '1',
  `definition_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `quest_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `offer_id` varchar(40) NOT NULL,
  `publish_condition` int(11) DEFAULT NULL,
  `repeat_condition` int(11) NOT NULL,
  `location_condition` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `event_pool`
--

INSERT INTO `event_pool` (`event_id`, `title`, `type`, `create_date`, `repetitive`, `definition_id`, `quest_id`, `offer_id`, `publish_condition`, `repeat_condition`, `location_condition`) VALUES
('48986dd4-1510-11ea-9302-704d7b4812f9', '醒来', 'pub-ann', '2019-11-30', 1, 'event_pub_ann_1', '945c6424-2e41-11ea-9d9f-704d7b4812f9', '', 10, 0, '9638747f-150e-11ea-9302-704d7b4812f9'),
('ae90138b-2fac-11ea-9d9f-704d7b4812f9', '新手礼包', 'pub-offer', '2020-01-05', 1, 'event_pub_offer_1', '', '14e26428-2fc9-11ea-9d9f-704d7b4812f9', 10, 0, '0');

-- --------------------------------------------------------

--
-- Table structure for table `event_publish_condition`
--

CREATE TABLE IF NOT EXISTS `event_publish_condition` (
  `code` int(10) NOT NULL,
  `topic` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `script_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `event_publish_condition`
--

INSERT INTO `event_publish_condition` (`code`, `topic`, `script_id`) VALUES
(10, 'novice_only', 76);

-- --------------------------------------------------------

--
-- Table structure for table `inventory_item`
--

CREATE TABLE IF NOT EXISTS `inventory_item` (
  `inventory_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `item_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` date NOT NULL,
  `stack_amount` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inventory_item`
--

INSERT INTO `inventory_item` (`inventory_id`, `item_id`, `create_date`, `stack_amount`) VALUES
('18a699f3-3799-11ea-9d9f-704d7b4812f9', '1d43aafb-2fd2-11ea-9d9f-704d7b4812f9', '2020-01-19', 1);

-- --------------------------------------------------------

--
-- Table structure for table `item_pool`
--

CREATE TABLE IF NOT EXISTS `item_pool` (
  `item_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `info_id` varchar(40) NOT NULL,
  `stack_limit` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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

CREATE TABLE IF NOT EXISTS `location` (
  `uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `location_id` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parent` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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

CREATE TABLE IF NOT EXISTS `objective_pool` (
  `objective_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `difficulty_level` int(11) NOT NULL DEFAULT '0',
  `objective_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `series_id` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `objective_pool`
--

INSERT INTO `objective_pool` (`objective_id`, `difficulty_level`, `objective_type`, `series_id`) VALUES
('be99f539-2edc-11ea-9d9f-704d7b4812f9', 0, 'survive', 'SV1');

-- --------------------------------------------------------

--
-- Table structure for table `offer_pool`
--

CREATE TABLE IF NOT EXISTS `offer_pool` (
  `offer_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `offer_type` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `reward_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `offer_pool`
--

INSERT INTO `offer_pool` (`offer_id`, `offer_type`, `reward_id`) VALUES
('14e26428-2fc9-11ea-9d9f-704d7b4812f9', 'event_gift', '5108de05-2fc9-11ea-9d9f-704d7b4812f9');

-- --------------------------------------------------------

--
-- Table structure for table `quest_objective`
--

CREATE TABLE IF NOT EXISTS `quest_objective` (
  `quest_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `objective_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `reward_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `quest_objective`
--

INSERT INTO `quest_objective` (`quest_id`, `objective_id`, `reward_id`) VALUES
('945c6424-2e41-11ea-9d9f-704d7b4812f9', 'be99f539-2edc-11ea-9d9f-704d7b4812f9', '1035f03f-2eda-11ea-9d9f-704d7b4812f9');

-- --------------------------------------------------------

--
-- Table structure for table `quest_pool`
--

CREATE TABLE IF NOT EXISTS `quest_pool` (
  `quest_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `require_level` int(11) NOT NULL DEFAULT '1',
  `objective_number` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `quest_pool`
--

INSERT INTO `quest_pool` (`quest_id`, `require_level`, `objective_number`) VALUES
('945c6424-2e41-11ea-9d9f-704d7b4812f9', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `reward_pool`
--

CREATE TABLE IF NOT EXISTS `reward_pool` (
  `reward_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `reward_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `amount_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `factor` int(11) NOT NULL DEFAULT '1',
  `item_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reward_pool`
--

INSERT INTO `reward_pool` (`reward_id`, `reward_type`, `amount_type`, `factor`, `item_id`) VALUES
('1035f03f-2eda-11ea-9d9f-704d7b4812f9', 'experience', 'F', 20, '0'),
('5108de05-2fc9-11ea-9d9f-704d7b4812f9', 'equipment', 'F', 1, '1d43aafb-2fd2-11ea-9d9f-704d7b4812f9');

-- --------------------------------------------------------

--
-- Table structure for table `user_event_record`
--

CREATE TABLE IF NOT EXISTS `user_event_record` (
  `event_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `publish_count` int(11) NOT NULL DEFAULT '0',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_event_record`
--

INSERT INTO `user_event_record` (`event_id`, `user_id`, `publish_count`, `update_time`) VALUES
('ae90138b-2fac-11ea-9d9f-704d7b4812f9', '38576df2-1404-11ea-9302-704d7b4812f9', 1, '2020-01-19 03:42:37');

-- --------------------------------------------------------

--
-- Table structure for table `user_inventory`
--

CREATE TABLE IF NOT EXISTS `user_inventory` (
  `user_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `inventory_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `stack_limit` int(11) NOT NULL DEFAULT '1',
  `stack_volume` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_inventory`
--

INSERT INTO `user_inventory` (`user_id`, `inventory_id`, `stack_limit`, `stack_volume`) VALUES
('38576df2-1404-11ea-9302-704d7b4812f9', '18a699f3-3799-11ea-9d9f-704d7b4812f9', 16, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user_profile`
--

CREATE TABLE IF NOT EXISTS `user_profile` (
  `uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `characters` int(11) NOT NULL DEFAULT '1',
  `location_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_profile`
--

INSERT INTO `user_profile` (`uuid`, `name`, `characters`, `location_id`) VALUES
('38576df2-1404-11ea-9302-704d7b4812f9', 'admin', 1, '9638747f-150e-11ea-9302-704d7b4812f9');

-- --------------------------------------------------------

--
-- Table structure for table `user_quest_record`
--

CREATE TABLE IF NOT EXISTS `user_quest_record` (
  `user_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `quest_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `objective_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `task_count` int(11) NOT NULL DEFAULT '1',
  `task_progress` int(11) NOT NULL DEFAULT '0',
  `status` int(11) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `expire_time` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_quest_record`
--

INSERT INTO `user_quest_record` (`user_id`, `quest_id`, `objective_id`, `task_count`, `task_progress`, `status`, `create_time`, `update_time`, `expire_time`) VALUES
('38576df2-1404-11ea-9302-704d7b4812f9', '945c6424-2e41-11ea-9d9f-704d7b4812f9', 'be99f539-2edc-11ea-9d9f-704d7b4812f9', 1, 0, 0, '2020-01-05 14:16:59', '2020-01-05 14:16:59', '0000-00-00 00:00:00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `character_attribute`
--
ALTER TABLE `character_attribute`
  ADD PRIMARY KEY (`character_id`);

--
-- Indexes for table `character_pool`
--
ALTER TABLE `character_pool`
  ADD PRIMARY KEY (`uuid`);

--
-- Indexes for table `equip_pool`
--
ALTER TABLE `equip_pool`
  ADD PRIMARY KEY (`equip_id`);

--
-- Indexes for table `event_pool`
--
ALTER TABLE `event_pool`
  ADD PRIMARY KEY (`event_id`),
  ADD UNIQUE KEY `event_title` (`title`),
  ADD KEY `title` (`title`),
  ADD KEY `event_id` (`event_id`);

--
-- Indexes for table `event_publish_condition`
--
ALTER TABLE `event_publish_condition`
  ADD PRIMARY KEY (`code`);

--
-- Indexes for table `inventory_item`
--
ALTER TABLE `inventory_item`
  ADD PRIMARY KEY (`inventory_id`,`item_id`);

--
-- Indexes for table `item_pool`
--
ALTER TABLE `item_pool`
  ADD PRIMARY KEY (`item_id`),
  ADD UNIQUE KEY `info_id` (`info_id`);

--
-- Indexes for table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`uuid`),
  ADD UNIQUE KEY `location_id` (`location_id`);

--
-- Indexes for table `objective_pool`
--
ALTER TABLE `objective_pool`
  ADD PRIMARY KEY (`objective_id`);

--
-- Indexes for table `offer_pool`
--
ALTER TABLE `offer_pool`
  ADD PRIMARY KEY (`offer_id`,`reward_id`);

--
-- Indexes for table `quest_objective`
--
ALTER TABLE `quest_objective`
  ADD PRIMARY KEY (`quest_id`,`objective_id`,`reward_id`);

--
-- Indexes for table `quest_pool`
--
ALTER TABLE `quest_pool`
  ADD PRIMARY KEY (`quest_id`);

--
-- Indexes for table `reward_pool`
--
ALTER TABLE `reward_pool`
  ADD PRIMARY KEY (`reward_id`);

--
-- Indexes for table `user_event_record`
--
ALTER TABLE `user_event_record`
  ADD PRIMARY KEY (`event_id`,`user_id`);

--
-- Indexes for table `user_inventory`
--
ALTER TABLE `user_inventory`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `user_profile`
--
ALTER TABLE `user_profile`
  ADD PRIMARY KEY (`uuid`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `user_quest_record`
--
ALTER TABLE `user_quest_record`
  ADD PRIMARY KEY (`user_id`,`quest_id`,`objective_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
