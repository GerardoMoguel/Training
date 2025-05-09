 -- Crear tabla Restaurante
CREATE TABLE Restaurante (
    Id_restaurante INT PRIMARY KEY,
    nombre VARCHAR(100),
    C_P VARCHAR(10),
    calle VARCHAR(100),
    numero VARCHAR(10),
    estado VARCHAR(100)
);

-- Crear tabla Cuenta
CREATE TABLE Cuenta (
    Id_venta INT PRIMARY KEY,
    fecha DATE,
    total DECIMAL(10, 2),
    Id_restaurante INT,
    FOREIGN KEY (Id_restaurante) REFERENCES Restaurante(Id_restaurante)
);


-- Crear tabla Platillo
CREATE TABLE Platillo (
    Id_platillo INT PRIMARY KEY,
    nombre VARCHAR(100),
    precio DECIMAL(10, 2),
    descripcion VARCHAR(255),
    Id_restaurante INT,
    FOREIGN KEY (Id_restaurante) REFERENCES Restaurante(Id_restaurante),
);

CREATE TABLE PlatilloCuenta (
	Id_platillo INT,
	Id_venta INT,
	PRIMARY KEY(Id_platillo,Id_venta),
	FOREIGN KEY (Id_platillo) REFERENCES Platillo(Id_platillo),
	FOREIGN KEY (Id_venta) REFERENCES Cuenta(Id_venta), 
	cantidad INT,
	);

-- Crear tabla Ingrediente
CREATE TABLE Ingrediente (
    Id_ingrediente INT PRIMARY KEY,
    nombre VARCHAR(100),
    origen VARCHAR(100),
    precio DECIMAL(10, 2),
    cantidad INT,
    Id_restaurante INT,
    FOREIGN KEY (Id_restaurante) REFERENCES Restaurante(Id_restaurante)
);



-- Crear tabla PlatilloIngrediente
CREATE TABLE PlatilloIngrediente (
    Id_platillo INT,
    Id_ingrediente INT,
    cantidad INT,
    PRIMARY KEY (Id_platillo, Id_ingrediente),
    FOREIGN KEY (Id_platillo) REFERENCES Platillo(Id_platillo),
    FOREIGN KEY (Id_ingrediente) REFERENCES Ingrediente(Id_ingrediente)
);