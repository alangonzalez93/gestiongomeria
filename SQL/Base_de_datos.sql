create database gomeria;

use gomeria;

CREATE  TABLE `gomeria`.`clientes` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(100) NOT NULL ,
  `telefono` VARCHAR(60) NULL ,
  `celular` VARCHAR(60) NULL ,
  `direccion` VARCHAR(120) NULL ,
  `ciudad` VARCHAR(60) NULL ,
  `zona` VARCHAR(30) NULL ,
  `email` VARCHAR(60) NULL ,
  `razon` VARCHAR(40) NULL ,
  `cuit` VARCHAR (20) NULL,
  `tipo` VARCHAR(40) NULL ,
  `vendidos` INT(10) NULL ,

	PRIMARY KEY (`id`) );

CREATE  TABLE `gomeria`.`proveedors` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(100) NOT NULL ,
  `direccion` VARCHAR(100) NULL ,
  `telefono` VARCHAR(45) NULL ,
  `celular` VARCHAR(45) NULL ,
  `email` VARCHAR(45) NULL ,
  `ciudad` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) );

CREATE  TABLE `gomeria`.`articulos` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `disenio` VARCHAR(50) NOT NULL ,
  `medida` VARCHAR(50) NULL ,
  `marca` VARCHAR(30) NULL ,
  `stock` INT NULL DEFAULT 0 ,
  `precio_compra` FLOAT NULL DEFAULT 0 ,
  `precio_venta` FLOAT NULL DEFAULT 0 ,
  `descripcion` VARCHAR(200) NULL ,
  `tipo` VARCHAR(30) NULL ,
  `vehiculo` VARCHAR(30) NULL ,
  `vendidos` INT NULL DEFAULT 0,
  PRIMARY KEY (`id`) );


create table usuarios (
	id integer not null auto_increment,
	nombre varchar(50) default 'gomeria',
	pass varchar(50) default 'gomeria',
	primary key(id));

CREATE  TABLE `gomeria`.`emails` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `email` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) );


CREATE TABLE envios (
  id integer NOT NULL AUTO_INCREMENT,
  fecha DATE NOT NULL ,
  enviado INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`,`fecha`) );

create table gomeria.ventas (
    id integer not null auto_increment,
    monto float,
    descripcion varchar(200),
    cliente_id integer,
    fecha date not null,
    numero_cheque varchar(10),
    forma_pago varchar(20),
    PRIMARY KEY (`id`) );

create table gomeria.compras (
    id integer not null auto_increment,
    monto float,
    descripcion varchar(200),
    proveedor_id integer,
    fecha date not null,
    PRIMARY KEY (`id`) );


create table articulos_ventas (
    id integer not null auto_increment,
    venta_id integer,
    articulo_id integer,
    cantidad integer not null,
    precio_final integer,
	  primary key(id) );


create table articulos_compras (
    id integer not null auto_increment,
    compra_id integer,
    articulo_id integer,
    cantidad float not null,
    primary key(id));

CREATE  TABLE `gomeria`.`pagos` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `fecha` DATE NULL DEFAULT NULL,
  `fecha_pago` DATE NULL DEFAULT NULL,
  `estado` VARCHAR(10),
  `monto` FLOAT NULL ,
  `compra_id` INT NULL ,
	PRIMARY KEY (`id`) );

CREATE  TABLE `gomeria`.`cobros` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `fecha` DATE NULL DEFAULT NULL,
  `fecha_pago` DATE NULL DEFAULT NULL,
  `tipo` VARCHAR(15),
  `monto` FLOAT NULL,
  `venta_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`) );
