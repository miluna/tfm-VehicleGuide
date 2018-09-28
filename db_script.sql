-- MySQL Script generated by MySQL Workbench
-- Sat Sep 29 01:19:18 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema vehicle_guide
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema vehicle_guide
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `vehicle_guide` DEFAULT CHARACTER SET utf8 ;
USE `vehicle_guide` ;

-- -----------------------------------------------------
-- Table `vehicle_guide`.`brands`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vehicle_guide`.`brands` (
  `idBrand` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idBrand`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vehicle_guide`.`vehicleSegments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vehicle_guide`.`vehicleSegments` (
  `idVehicleSegment` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(128) NULL,
  PRIMARY KEY (`idVehicleSegment`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vehicle_guide`.`vehicles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vehicle_guide`.`vehicles` (
  `idVehicle` INT NOT NULL AUTO_INCREMENT,
  `model` VARCHAR(45) NOT NULL,
  `date` DATE NULL,
  `weight` INT NULL,
  `doors` INT NULL,
  `basePrice` DECIMAL(10,2) NULL,
  `description` VARCHAR(255) NULL,
  `idBrand` INT NOT NULL,
  `idVehicleSegment` INT NOT NULL,
  PRIMARY KEY (`idVehicle`),
  INDEX `fk_vehicles_brands_idx` (`idBrand` ASC),
  INDEX `fk_vehicles_vehicleSegments1_idx` (`idVehicleSegment` ASC),
  CONSTRAINT `fk_vehicles_brands`
    FOREIGN KEY (`idBrand`)
    REFERENCES `vehicle_guide`.`brands` (`idBrand`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_vehicles_vehicleSegments1`
    FOREIGN KEY (`idVehicleSegment`)
    REFERENCES `vehicle_guide`.`vehicleSegments` (`idVehicleSegment`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vehicle_guide`.`energyCertificates`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vehicle_guide`.`energyCertificates` (
  `idEnergyCertificate` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(128) NULL,
  PRIMARY KEY (`idEnergyCertificate`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vehicle_guide`.`engines`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vehicle_guide`.`engines` (
  `idEngine` INT NOT NULL AUTO_INCREMENT,
  `cylinders` INT NULL,
  `displacement` INT NOT NULL,
  `horsepower` INT NOT NULL,
  `hasTurbo` TINYINT NULL,
  `type` VARCHAR(45) NOT NULL,
  `pollution` DECIMAL(10,2) NULL,
  `autonomy` INT NULL,
  `idEnergyCertificate` INT NOT NULL,
  PRIMARY KEY (`idEngine`, `idEnergyCertificate`),
  INDEX `fk_engines_energyCertificates1_idx` (`idEnergyCertificate` ASC),
  CONSTRAINT `fk_engines_energyCertificates1`
    FOREIGN KEY (`idEnergyCertificate`)
    REFERENCES `vehicle_guide`.`energyCertificates` (`idEnergyCertificate`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vehicle_guide`.`userRoles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vehicle_guide`.`userRoles` (
  `idUserRole` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `canReviewVehicles` TINYINT NULL,
  `canEditPosts` TINYINT NULL,
  `canComment` VARCHAR(45) NULL,
  PRIMARY KEY (`idUserRole`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vehicle_guide`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vehicle_guide`.`users` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(64) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `idUserRole` INT NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `fk_users_userRoles1_idx` (`idUserRole` ASC),
  CONSTRAINT `fk_users_userRoles1`
    FOREIGN KEY (`idUserRole`)
    REFERENCES `vehicle_guide`.`userRoles` (`idUserRole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vehicle_guide`.`vehicles_have_engines`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vehicle_guide`.`vehicles_have_engines` (
  `vehicles_idVehicle` INT NOT NULL,
  `engines_idEngine` INT NOT NULL,
  PRIMARY KEY (`vehicles_idVehicle`, `engines_idEngine`),
  INDEX `fk_vehicles_has_engines_engines1_idx` (`engines_idEngine` ASC),
  INDEX `fk_vehicles_has_engines_vehicles1_idx` (`vehicles_idVehicle` ASC),
  CONSTRAINT `fk_vehicles_has_engines_vehicles1`
    FOREIGN KEY (`vehicles_idVehicle`)
    REFERENCES `vehicle_guide`.`vehicles` (`idVehicle`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_vehicles_has_engines_engines1`
    FOREIGN KEY (`engines_idEngine`)
    REFERENCES `vehicle_guide`.`engines` (`idEngine`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
