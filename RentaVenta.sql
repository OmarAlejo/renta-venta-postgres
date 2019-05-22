-- Database: "Renta-Venta"

-- DROP DATABASE "Renta-Venta";

CREATE DATABASE "Renta-Venta"
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Spanish_Mexico.1252'
       LC_CTYPE = 'Spanish_Mexico.1252'
       CONNECTION LIMIT = -1;

CREATE SCHEMA Empleado;
CREATE SCHEMA Servicios;

CREATE TABLE Empleado.Tipo
(
	IdTipo BIGSERIAL NOT NULL,
	Tipo VARCHAR(50) NOT NULL,
	Sueldo REAL NOT NULL,

	CONSTRAINT PK_Tipo PRIMARY KEY (IdTipo)
)

CREATE TABLE Empleado.Empleado
(
	IdEmpleado BIGSERIAL NOT NULL,
	IdTipo BIGINT NOT NULL,
	PrimerNombre VARCHAR(30) NOT NULL,
	SegundoNombre VARCHAR(30),
	ApellidoPaterno VARCHAR(30) NOT NULL,
	ApellidoMaterno VARCHAR(30) NOT NULL,
	Domicilio VARCHAR(200) NOT NULL,
	Telefono VARCHAR(80) NOT NULL,

	CONSTRAINT PK_Empleado PRIMARY KEY (IdEmpleado),
	CONSTRAINT FK_Tipo FOREIGN KEY (IdTipo) REFERENCES Empleado.Tipo(IdTipo)
)

CREATE TABLE Servicios.Vehiculo
(
	IdVehiculo BIGSERIAL  NOT NULL,
	Placas VARCHAR(20) NOT NULL,
	Marca VARCHAR(80),
	Modelo VARCHAR(80) NOT NULL,
	Año VARCHAR(80) NOT NULL,
	Descripcion VARCHAR(200) NOT NULL,
	NumSeguro VARCHAR(80) NOT NULL,
	Vendido BOOLEAN NOT NULL,
	PrecioVenta FLOAT NOT NULL,
	Disponible BOOLEAN,
	PrecioRenta FLOAT NOT NULL,

	CONSTRAINT PK_Vehiculo PRIMARY KEY (IdVehiculo)
)

ALTER TABLE Servicios.Vehiculo
ALTER COLUMN Disponible TYPE boolean USING Disponible::boolean

CREATE TABLE Servicios.Cliente
(
	IdCliente BIGSERIAL  NOT NULL,
	PrimerNombre VARCHAR(30) NOT NULL,
	SegundoNombre VARCHAR(30),
	ApellidoPaterno VARCHAR(30) NOT NULL,
	ApellidoMaterno VARCHAR(30) NOT NULL,
	Domicilio VARCHAR(200) NOT NULL,
	Telefono VARCHAR(80) NOT NULL,
	Email VARCHAR(200) NOT NULL,
	NumTarjeta VARCHAR(16),

	CONSTRAINT PK_Cliente PRIMARY KEY (IdCliente)
)

CREATE TABLE Servicios.Venta
(
	IdVenta BIGSERIAL  NOT NULL,
	IdVehiculo BIGINT NOT NULL,
	IdCliente BIGINT NOT NULL,
	IdEmpleado BIGINT NOT NULL,
	FechaVenta DATE NOT NULL,

	CONSTRAINT PK_Venta PRIMARY KEY (IdVenta),
	CONSTRAINT FK_Vehiculo1 FOREIGN KEY(IdVehiculo) REFERENCES Servicios.Vehiculo(IdVehiculo),
	CONSTRAINT FK_Cliente1 FOREIGN KEY(IdCliente) REFERENCES Servicios.Cliente(IdCliente),
	CONSTRAINT FK_Empleado1 FOREIGN KEY(IdEmpleado) REFERENCES Empleado.Empleado(IdEmpleado)
)

CREATE TABLE Servicios.Renta
(
	IdRenta BIGSERIAL NOT NULL,
	IdVehiculo BIGINT NOT NULL,
	IdCliente BIGINT NOT NULL,
	IdEmpleado BIGINT NOT NULL,
	DiaPrestamo DATE,
	DiaDevolucion DATE NOT NULL,
	Total FLOAT,

	CONSTRAINT PK_Renta PRIMARY KEY (IdRenta),
	CONSTRAINT FK_Vehiculo2 FOREIGN KEY(IdVehiculo) REFERENCES Servicios.Vehiculo(IdVehiculo),
	CONSTRAINT FK_Cliente2 FOREIGN KEY(IdCliente) REFERENCES Servicios.Cliente(IdCliente),
	CONSTRAINT FK_Empleado2 FOREIGN KEY(IdEmpleado) REFERENCES Empleado.Empleado(IdEmpleado)
)

CREATE TABLE Servicios.Entrega
(
	IdEntrega BIGSERIAL NOT NULL,
	IdRenta BIGINT NOT NULL,
	IdEmpleado BIGINT NOT NULL,
	FechaEntrega DATE NOT NULL,
	Cargos FLOAT,
	DescripcionMantenimiento VARCHAR(100),
	Costo FLOAT,

	CONSTRAINT PK_Entrega PRIMARY KEY (IdEntrega),
	CONSTRAINT FK_Renta FOREIGN KEY(IdRenta) REFERENCES Servicios.Renta(IdRenta),
	CONSTRAINT FK_Empleado3 FOREIGN KEY(IdEmpleado) REFERENCES Empleado.Empleado(IdEmpleado)
)

DROP TABLE Servicios.Vehiculo
DROP TABLE Servicios.Renta
DROP TABLE Servicios.Venta
DROP TABLE Servicios.Entrega

SELECT * FROM Servicios.Vehiculo

INSERT INTO Servicios.Vehiculo(Placas, Marca, Modelo, Año, Descripcion, NumSeguro, Vendido, PrecioVenta, Disponible, PrecioRenta) VALUES('123', '123', '123', '123', '123', '123', false, 123, true, 123)
INSERT INTO Empleado.Tipo (Tipo, Sueldo) VALUES ('Gerente', 10000);

INSERT INTO Servicios.Cliente (PrimerNombre, SegundoNombre, ApellidoPaterno, ApellidoMaterno, Domicilio, Telefono, Email, NumTarjeta)
VALUES ('Jorge', 'Hernan', 'Casillas', 'Madero', 'Mi casa', '4813919369', 'hernancasillas@gmail.com', '12345678912345');

INSERT INTO Empleado.Empleado (IdTipo, PrimerNombre, SegundoNombre, ApellidoPaterno, ApellidoMaterno, Domicilio, Telefono)
VALUES(1, 'Jose', '', 'Madero', 'Vizcaino', 'Mty', '123456890');

INSERT INTO Servicios.Renta (IdVehiculo, IdCliente, IdEmpleado, DiaPrestamo, DiaDevolucion, Total)
VALUES (4, 1, 1, '03/06/2019', '03/09/2019', 2000)

INSERT INTO Servicios.Venta (IdVehiculo, IdCliente, IdEmpleado, FechaVenta)
VALUES (1, 1, 1, '03/09/2019')

INSERT INTO Servicios.Entrega (IdRenta, IdEmpleado, FechaEntrega, Cargos, DescripcionMantenimiento, Costo)
VALUES (21, 1, '03/11/2019', 0, 'Bien', 1000) 

UPDATE Servicios.Vehiculo SET Vendido = false WHERE idvehiculo = 2
TRUNCATE Servicios.Entrega

select * from servicios.renta
select * from servicios.venta
select * from servicios.Vehiculo
select * from empleado.empleado
select * from empleado.tipo
select * from servicios.Entrega
select * from servicios.cliente


--Trigger que modifica la disponibilidad del vehiculo en una renta--
CREATE OR REPLACE FUNCTION ModificaDisponibilidadRenta() RETURNS trigger AS $ModificaDisponibilidadRentaTr2$
BEGIN
	UPDATE Servicios.Vehiculo
	SET  Disponible = false
	WHERE IdVehiculo = NEW.IdVehiculo;
	RETURN NULL;
END;
$ModificaDisponibilidadRentaTr2$ LANGUAGE plpgsql;

DROP FUNCTION ModificaDispomibilidadRenta() CASCADE

CREATE TRIGGER ModificaDisponibilidadRentaTr2
AFTER INSERT OR UPDATE ON Servicios.Renta
FOR EACH ROW EXECUTE PROCEDURE ModificaDisponibilidadRenta();

--Trigger que modifica el estado vendido en una venta--
CREATE OR REPLACE FUNCTION ModificaDisponibilidadVenta() RETURNS trigger AS $ModificaDisponibilidadVentaTr$
BEGIN
	UPDATE Servicios.Vehiculo
	SET  Vendido = true, Disponible = false
	WHERE IdVehiculo = NEW.IdVehiculo;
	RETURN NULL;
END;
$ModificaDisponibilidadVentaTr$ LANGUAGE plpgsql;

DROP FUNCTION ModificaDispomibilidadVenta() CASCADE

CREATE TRIGGER ModificaDisponibilidadVentaTr
AFTER INSERT OR UPDATE ON Servicios.Venta
FOR EACH ROW EXECUTE PROCEDURE ModificaDisponibilidadVenta();


--Trigger que suma los costos de entrega a la renta--
CREATE OR REPLACE FUNCTION SumaCostoEntrega() RETURNS trigger AS $Tr_SumaCostoEntrega$
BEGIN
	UPDATE Servicios.Renta
	SET Total = Total + NEW.Costo
	WHERE IdRenta = NEW.IdRenta;
	RETURN NULL;
END;
$Tr_SumaCostoEntrega$ LANGUAGE plpgsql;

DROP FUNCTION SumaCostoEntrega() CASCADE

CREATE TRIGGER Tr_SumaCostoEntrega
AFTER INSERT ON Servicios.Entrega
FOR EACH ROW EXECUTE PROCEDURE SumaCostoEntrega();

--Trigger que modifica la disponibilidad de un vehículo al realizar una entrega--
CREATE TRIGGER Tr_ModificaDispEntrega
ON Servicios.Entrega
FOR INSERT
AS BEGIN
DECLARE @idRenta BIGINT
SELECT @idRenta = IdRenta FROM inserted
UPDATE Servicios.Vehiculo SET Servicios.Vehiculo.Disponible = 1 FROM Servicios.Vehiculo 
INNER JOIN Servicios.Renta ON Servicios.Renta.IdVehiculo = Servicios.Vehiculo.IdVehiculo
WHERE IdRenta = @idRenta
END

CREATE OR REPLACE FUNCTION ModificaDispEntrega() RETURNS trigger AS $Tr_ModificaDispEntrega$
BEGIN
	UPDATE Servicios.Vehiculo V SET Disponible = true FROM Servicios.Renta R WHERE R.IdVehiculo = V.IdVehiculo AND R.IdRenta = @idRenta
	RETURN NULL;
END;
$Tr_ModificaDispEntrega$ LANGUAGE plpgsql;

UPDATE Servicios.Vehiculo SET Marca = 'Ferrari' FROM Servicios.Vehiculo V INNER JOIN Servicios.Renta R ON V.IdVehiculo = R.IdVehiculo WHERE IdRenta = 7 AND R.IdVehiculo = 2
SELECT * FROM Servicios.Vehiculo V INNER JOIN Servicios.Renta R ON V.IdVehiculo = R.IdVehiculo WHERE IdRenta = 7 AND R.IdVehiculo = 2
SELECT * FROM Servicios.Vehiculo
SELECT * FROM Servicios.Renta
UPDATE Servicios.Vehiculo V SET Disponible = true FROM Servicios.Renta R WHERE R.IdVehiculo = V.IdVehiculo AND R.IdRenta = 23
FROM Servicios.Renta R INNER JOIN Servicios.Vehiculo V ON R.IdVehiculo = V.IdVehiculo WHERE R.IdRenta = 23
SELECT * FROM Servicios.Renta R INNER JOIN Servicios.Vehiculo V ON R.IdVehiculo = V.IdVehiculo WHERE R.IdRenta = 23
DROP FUNCTION ModificaDispEntrega() CASCADE

CREATE TRIGGER Tr_ModificaDispEntrega
AFTER INSERT ON Servicios.Entrega
FOR EACH ROW EXECUTE PROCEDURE ModificaDispEntrega();

--Trigger que multiplica el precio de la renta con el número de días para calcular el total--
CREATE OR REPLACE FUNCTION CalculaTotalRenta() RETURNS trigger AS $Tr_CalculaTotalRenta$
DECLARE idRentaVAR BIGINT := NEW.idRenta;
DECLARE idVehiculoVAR BIGINT := NEW.IdVehiculo;
DECLARE fechaPrestamoVAR DATE := NEW.DiaPrestamo;
DECLARE fechaDevolucionVAR DATE := NEW.DiaDevolucion;
DECLARE precioRentaVAR REAL;
DECLARE diferenciaVAR INT := DATE_PART('day', NEW.DiaDevolucion::timestamp - NEW.DiaPrestamo::timestamp);
BEGIN
	/*RAISE NOTICE 'IDRenta is currently %', idRentaVAR;
	RAISE NOTICE 'idVehiculoVAR is currently %', idVehiculoVAR;
	RAISE NOTICE 'fechaPrestamoVAR is currently %', fechaPrestamoVAR;
	RAISE NOTICE 'fechaDevolucionVAR is currently %', fechaDevolucionVAR;
	RAISE NOTICE 'precioRentaVAR is currently %', precioRentaVAR;
	RAISE NOTICE 'diferenciaVAR is currently %', diferenciaVAR;*/
	SELECT PrecioRenta INTO precioRentaVAR FROM Servicios.Vehiculo WHERE Servicios.Vehiculo.IdVehiculo = idVehiculoVAR;
	UPDATE Servicios.Renta SET Total = precioRentaVAR * diferenciaVAR WHERE IdRenta = IdRentaVAR;
	RETURN NULL;
END;
$Tr_CalculaTotalRenta$ LANGUAGE plpgsql;

DROP FUNCTION CalculaTotalRenta() CASCADE

CREATE TRIGGER Tr_CalculaTotalRenta
AFTER INSERT ON Servicios.Renta
FOR EACH ROW EXECUTE PROCEDURE CalculaTotalRenta();

--Trigger que aumenta 5% en caso de atraso en una renta
CREATE OR REPLACE FUNCTION CalculaAtrasoRenta() RETURNS trigger AS $Tr_CalculaAtrasoRenta$
DECLARE idRentaVAR BIGINT := NEW.IdRenta;
DECLARE costoVAR BIGINT := NEW.Costo;
DECLARE fechaEntregaVAR DATE := NEW.FechaEntrega;
DECLARE diaDevolucionVAR DATE;
DECLARE atrasoVAR INT;
BEGIN
	/*RAISE NOTICE 'IDRenta is currently %', idRentaVAR;
	RAISE NOTICE 'costoVAR is currently %', costoVAR;
	RAISE NOTICE 'fechaEntregaVAR is currently %', fechaEntregaVAR;
	RAISE NOTICE 'diaDevolucionVAR is currently %', diaDevolucionVAR;
	RAISE NOTICE 'atrasoVAR is currently %', atrasoVAR;*/
	SELECT DiaDevolucion INTO diaDevolucionVAR FROM Servicios.Renta WHERE IdRenta = 18;
	SELECT DATE_PART('day', NEW.fechaEntrega::timestamp - diaDevolucionVAR::timestamp) INTO atrasoVAR;

	/*RAISE NOTICE 'diaDevolucionVAR is currently %', diaDevolucionVAR;
	RAISE NOTICE 'atrasoVAR is currently %', atrasoVAR;*/
	IF atrasoVAR > 0 THEN
		UPDATE Servicios.Renta SET Total = Total + ((Total * 5 /100) * atrasoVAR) WHERE idRentaVAR = IdRenta;
	END IF;

	RETURN NULL;
END;
$Tr_CalculaAtrasoRenta$ LANGUAGE plpgsql;

DROP FUNCTION CalculaAtrasoRenta() CASCADE

CREATE TRIGGER Tr_CalculaAtrasoRenta
AFTER INSERT ON Servicios.Entrega
FOR EACH ROW EXECUTE PROCEDURE CalculaAtrasoRenta();

CREATE USER Admin WITH PASSWORD 'root';
GRANT ALL PRIVILEGES ON Servicios.Cliente to Admin;
GRANT ALL PRIVILEGES ON Servicios.Entrega to Admin;
GRANT ALL PRIVILEGES ON Servicios.Renta to Admin;
GRANT ALL PRIVILEGES ON Servicios.Vehiculo to Admin;
GRANT ALL PRIVILEGES ON Servicios.Venta to Admin;
GRANT ALL PRIVILEGES ON Empleado.Empleado to Admin;
GRANT ALL PRIVILEGES ON Empleado.Tipo to Admin;