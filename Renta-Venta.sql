-- Database: "Renta-Venta"

-- DROP DATABASE "Renta-Venta";

CREATE DATABASE "Renta-Venta"
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Spanish_Mexico.1252'
       LC_CTYPE = 'Spanish_Mexico.1252'
       CONNECTION LIMIT = -1;

CREATE SCHEMA Empleado
CREATE SCHEMA Servicios

CREATE TABLE Empleado.Tipo
(
	IdTipo BIGSERIAL NOT NULL,
	Tipo VARCHAR(50) NOT NULL,
	Sueldo DOUBLE NOT NULL,

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