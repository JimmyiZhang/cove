-- --------------------------------------------------------
-- 主机:                           192.168.168.40
-- 服务器版本:                        5.7.22-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 cove_ms 的数据库结构
CREATE DATABASE IF NOT EXISTS `cove_ms` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `cove_ms`;

-- 导出  表 cove_ms.user 结构
CREATE TABLE IF NOT EXISTS `account` (
  `id` bigint(20) unsigned NOT NULL COMMENT '编号',
  `user_name` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `salt` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '加盐',
  `expired_time` datetime NOT NULL COMMENT '过期时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='账号信息';

-- 数据导出被取消选择。
-- 导出  表 cove_ms.company 结构
CREATE TABLE IF NOT EXISTS `company` (
  `id` bigint(20) unsigned NOT NULL COMMENT '编号',
  `name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公司名称',
  `income_fee` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '收入费率',
  `toll_fee` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '路桥费率',
  `fuel_fee` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '燃油费率',
  `driver_fee` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '司机费用',
  `speed_warning` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '预警时速',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='公司';

-- 数据导出被取消选择。
-- 导出  表 cove_ms.company_trailer_loading 结构
CREATE TABLE IF NOT EXISTS `company_trailer_loading` (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `place` tinyint(4) NOT NULL COMMENT '板车位数',
  `heavy` tinyint(4) NOT NULL COMMENT '重载位数',
  `company_id` bigint(20) NOT NULL COMMENT '公司编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='公司板车载重设置';

-- 数据导出被取消选择。
-- 导出  表 cove_ms.employee 结构
CREATE TABLE IF NOT EXISTS `employee` (
  `id` bigint(20) unsigned NOT NULL COMMENT '编号',
  `name` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `phone` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '电话',
  `company_id` bigint(20) unsigned NOT NULL COMMENT '公司编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='员工';

-- 数据导出被取消选择。
-- 导出  表 cove_ms.employee_0 结构
CREATE TABLE IF NOT EXISTS `employee_0` (
  `id` bigint(20) unsigned NOT NULL COMMENT '编号',
  `name` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `phone` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '电话',
  `company_id` bigint(20) unsigned NOT NULL COMMENT '公司编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='员工';

-- 数据导出被取消选择。
-- 导出  表 cove_ms.employee_1 结构
CREATE TABLE IF NOT EXISTS `employee_1` (
  `id` bigint(20) unsigned NOT NULL COMMENT '编号',
  `name` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `phone` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '电话',
  `company_id` bigint(20) unsigned NOT NULL COMMENT '公司编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='员工';

-- 数据导出被取消选择。
-- 导出  表 cove_ms.comment 结构
CREATE TABLE IF NOT EXISTS `comment` (
  `id` bigint(20) unsigned NOT NULL COMMENT '编码',
  `receiver_id` bigint(20) unsigned NOT NULL COMMENT '接受者',
  `resource_id` bigint(20) unsigned NOT NULL COMMENT '来源',
  `is_read` bit(1) NOT NULL COMMENT '是否已读',
  `read_time` datetime NOT NULL COMMENT '已读时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='消息';

-- 数据导出被取消选择。
-- 导出  表 cove_ms.message_2019 结构
CREATE TABLE IF NOT EXISTS `message_2019` (
  `id` bigint(20) unsigned NOT NULL COMMENT '编码',
  `receiver_id` bigint(20) unsigned NOT NULL COMMENT '接受者',
  `resource_id` bigint(20) unsigned NOT NULL COMMENT '来源',
  `is_read` bit(1) NOT NULL COMMENT '是否已读',
  `read_time` datetime NOT NULL COMMENT '已读时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='消息';

-- 数据导出被取消选择。
-- 导出  表 cove_ms.message_2020 结构
CREATE TABLE IF NOT EXISTS `message_2020` (
  `id` bigint(20) unsigned NOT NULL COMMENT '编码',
  `receiver_id` bigint(20) unsigned NOT NULL COMMENT '接受者',
  `resource_id` bigint(20) unsigned NOT NULL COMMENT '来源',
  `is_read` bit(1) NOT NULL COMMENT '是否已读',
  `read_time` datetime NOT NULL COMMENT '已读时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='消息';

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
