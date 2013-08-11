/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : snack

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2012-11-14 18:50:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `monedas`
-- ----------------------------
DROP TABLE IF EXISTS `monedas`;
CREATE TABLE `monedas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `valor` int(6) DEFAULT NULL,
  `cantidad` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of monedas
-- ----------------------------
INSERT INTO `monedas` VALUES ('1', '50', '27');
INSERT INTO `monedas` VALUES ('2', '100', '24');
INSERT INTO `monedas` VALUES ('3', '200', '20');
INSERT INTO `monedas` VALUES ('4', '500', '35');
INSERT INTO `monedas` VALUES ('5', '1000', '18');
INSERT INTO `monedas` VALUES ('6', '2000', '10');
INSERT INTO `monedas` VALUES ('7', '5000', '12');
INSERT INTO `monedas` VALUES ('8', '10000', '1');

-- ----------------------------
-- Table structure for `productos`
-- ----------------------------
DROP TABLE IF EXISTS `productos`;
CREATE TABLE `productos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  `valor` int(5) DEFAULT NULL,
  `cantidad` int(3) DEFAULT NULL,
  `posicion` int(3) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of productos
-- ----------------------------
INSERT INTO `productos` VALUES ('1', 'Pepsi', '600', '9', '6');
INSERT INTO `productos` VALUES ('2', 'Ponque', '500', '10', '2');
INSERT INTO `productos` VALUES ('3', 'Chitos', '400', '8', '0');
INSERT INTO `productos` VALUES ('4', 'Chicles', '100', '6', '0');
INSERT INTO `productos` VALUES ('5', 'Doritos', '450', '9', '5');
INSERT INTO `productos` VALUES ('6', 'Nestea', '850', '5', '0');
INSERT INTO `productos` VALUES ('7', 'Papas', '700', '7', '0');
INSERT INTO `productos` VALUES ('8', 'papas', '800', '7', '1');

-- ----------------------------
-- Table structure for `usuarios`
-- ----------------------------
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(40) DEFAULT NULL,
  `usuario` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of usuarios
-- ----------------------------
