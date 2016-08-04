-- Motor: MySQL 5.7.12

DROP DATABASE IF EXISTS `agenda`;
CREATE DATABASE `agenda`;
USE agenda;

-- Entrega 15/03 INI - Dario Rick

CREATE TABLE `tipo_personas` 
(
  `idTipoPersona` int(11) NOT NULL AUTO_INCREMENT,
  `Tipo` varchar(20),
  `Fecha_Baja` DATE, -- Entrega 15/03 FIN - Dario Rick
  PRIMARY KEY (`idTipoPersona`)
);

 CREATE TABLE `localidades` 
(
  `idLocalidad` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre_Localidad` varchar(20),
  `Fecha_Baja` DATE, -- Entrega 15/03 FIN - Dario Rick
  PRIMARY KEY (`idLocalidad`)
);

CREATE TABLE `domicilios` 
(
  `idDomicilio` int(11) NOT NULL AUTO_INCREMENT,
  `Calle` varchar(20),
  `Altura` int(11),
  `Piso` int(11),
  `Depto` varchar(20),
  `idLocalidad` int(11),
  CHECK (`Altura`>0),
  PRIMARY KEY (`idDomicilio`),
  FOREIGN KEY (`idLocalidad`) REFERENCES `localidades` (`idLocalidad`)
);

-- Entrega 15/03 FIN - Dario Rick

CREATE TABLE `personas` 
(
  `idPersona` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Telefono` varchar(20) NOT NULL,
   PRIMARY KEY (`idPersona`),
   -- Entrega 15/03 INI - Dario Rick
  `idDomicilio` int(11),
  `Email` varchar(60),
  `Fecha_Nacimiento` DATE,
  `idTipoPersona` int(11),
  `Fecha_Baja` DATE, -- Entrega 15/03 FIN - Dario Rick
  FOREIGN KEY (`idTipoPersona`) REFERENCES `tipo_personas`  (`idTipoPersona`), 
  FOREIGN KEY (`idDomicilio`) REFERENCES `domicilios`   (`idDomicilio`)
   -- Entrega 15/03 FIN - Dario Rick
);

DELIMITER $$
/*
SP: getPersonas
Descripción: Devuelve todas las personas que no esten dadas de baja junto con sus datos
*/
CREATE PROCEDURE getPersonas () 
BEGIN

SELECT p.idPersona,p.Nombre,p.Telefono,dom.idDomicilio,dom.Calle,dom.Altura,dom.Piso,
		dom.Depto,loc.idLocalidad,loc.Nombre_Localidad,p.Email,p.Fecha_Nacimiento,tip.idTipoPersona,tip.Tipo
FROM 
	personas p
LEFT OUTER JOIN
	domicilios dom
ON
	p.idDomicilio = dom.idDomicilio
LEFT OUTER JOIN
	tipo_personas tip
ON
	p.idTipoPersona = tip.idTipoPersona
LEFT OUTER JOIN
	localidades loc
ON 
	dom.idLocalidad = loc.idLocalidad
WHERE 
	p.Fecha_Baja is null;
END $$
DELIMITER ;

DELIMITER $$
/*
SP: insertarLocalidad
Descripción: Inserta una nueva localidad. Devuelve el ID generado
*/
CREATE PROCEDURE insertarLocalidad (
IN Nombre_Localidad varchar(20),
OUT retIdLocalidad int(11)
) 
BEGIN

INSERT INTO `localidades`(
Nombre_Localidad
)
VALUES(
Nombre_Localidad
);

SELECT MAX(idLocalidad) 
INTO retIdLocalidad
FROM `localidades`;

END $$
DELIMITER ;

DELIMITER $$
/*
SP: eliminarLocalidad
Descripción: Elimina una localidad. Devuelve la fecha de baja
*/
CREATE PROCEDURE eliminarLocalidad (
IN IdLocalidad int(11),
OUT retFecha_Baja DATE
) 
BEGIN

UPDATE `localidades`
SET Fecha_Baja = NOW()
WHERE idLocalidad = IdLocalidad;

SELECT Fecha_Baja 
INTO retFecha_Baja
FROM `localidades`
WHERE idLocalidad = IdLocalidad;

END $$
DELIMITER ;

DELIMITER $$
/*
SP: insertarTipoPersona
Descripción: Inserta un nuevo tipo de persona. Devuelve el ID generado
*/
CREATE PROCEDURE insertarTipoPersona (
IN Tipo varchar(20),
OUT retIdTipoPersona int(11)
) 
BEGIN

INSERT INTO `tipo_personas`(
Tipo
)
VALUES(
Tipo
);

SELECT MAX(idTipoPersona) 
INTO retIdTipoPersona 
FROM `tipo_personas`;

END $$
DELIMITER ;

DELIMITER $$
/*
SP: eliminarTipoPersona
Descripción: Elimina una persona. Devuelve la fecha de baja
*/
CREATE PROCEDURE eliminarTipoPersona (
IN IdTipoPersona int(11),
OUT retFecha_Baja DATE
) 
BEGIN

UPDATE `tipo_personas`
SET Fecha_Baja = NOW()
WHERE idTipoPersona = IdTipoPersona;

SELECT Fecha_Baja 
INTO retFecha_Baja
FROM `tipo_personas`
WHERE idTipoPersona = IdTipoPersona;

END $$
DELIMITER ;


DELIMITER $$
/*
SP: insertarDomicilio
Descripción: Inserta los datos del domicilio. Devuelve el ID generado
*/
CREATE PROCEDURE insertarDomicilio (
IN Calle varchar(20), 
IN Altura int(11), 
IN Piso int(11), 
IN Depto varchar(20),
IN idLocalidad int(11),
OUT retIdDomicilio int(11)
) 
BEGIN

INSERT INTO `domicilios`(
Calle, 
Altura, 
Piso, 
Depto,
idLocalidad
)
VALUES(
Calle, 
Altura, 
Piso, 
Depto,
idLocalidad
);

SELECT MAX(idDomicilio) 
INTO retIdDomicilio
FROM `domicilios`;

END $$
DELIMITER ;

DELIMITER $$
/*
SP: insertarPersona
Descripción: Inserta los datos de la persona. Devuelve el ID generado
*/
CREATE PROCEDURE insertarPersona (
IN Nombre varchar(45), 
IN Telefono varchar(20), 
IN idDomicilio int(11), 
IN Email varchar(60), 
IN Fecha_Nacimiento DATE,  
IN idTipoPersona int(11),
OUT retIdPersona int(11)
) 
BEGIN

INSERT INTO `personas`(
Nombre, 
Telefono, 
idDomicilio, 
Email, 
Fecha_Nacimiento, 
idTipoPersona
)
VALUES(
Nombre, 
Telefono, 
idDomicilio, 
Email,
Fecha_Nacimiento,
idTipoPersona
);

SELECT MAX(idPersona) 
INTO retIdPersona 
FROM `personas`;

END $$
DELIMITER ;

SET SQL_SAFE_UPDATES = 0;