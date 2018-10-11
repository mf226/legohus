-- MySQL Workbench Forward Engineering
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema legohouse
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema legohouse
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `legohouse` DEFAULT CHARACTER SET utf8 ;
USE `legohouse` ;

-- -----------------------------------------------------
-- Table `legohouse`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `legohouse`.`users` (
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `role` VARCHAR(25) NOT NULL DEFAULT 'customer',
  PRIMARY KEY (`email`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `legohouse`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `legohouse`.`orders` (
  `order_id` INT(11) NOT NULL AUTO_INCREMENT,
  `email_FK` VARCHAR(100) NOT NULL,
  `length` INT(20) NOT NULL,
  `width` INT(20) NOT NULL,
  `height` INT(20) NOT NULL,
  `shipped` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`order_id`),
  INDEX `email_idx` (`email_FK` ASC),
  CONSTRAINT `email`
    FOREIGN KEY (`email_FK`)
    REFERENCES `legohouse`.`users` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
