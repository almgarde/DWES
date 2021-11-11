CREATE TABLE Empleados (
Nombre VARCHAR(30) NOT NULL,
DNI VARCHAR(9) PRIMARY KEY,
Sexo CHAR(1) CHECK (SEXO = 'M' OR SEXO = 'F'),
Categoria INTEGER(2),
Anyos INTEGER(3) CHECK (ANYOS >=0)
);

INSERT INTO Empleados VALUES ('James Cosling', '32000032G', 'M', 4, 7);
INSERT INTO Empleados VALUES ('Ada Lovelace', '32000031R', 'F', 1, 9);