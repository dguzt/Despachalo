# Persistencia de Despáchalo PE
## 1. Setup de la base de datos
Se crea la base de datos en PostgreSQL con los siguientes comandos SQL:
```postgresql
CREATE DATABASE despachalo;
CREATE USER despachalo WITH ENCRYPTED PASSWORD 'despachalo';
GRANT ALL PRIVILEGES ON DATABASE despachalo TO despachalo;
```

El proyecto usa valores UUID en distintas tablas. Es necesario instalar
la extensión de UUID para PostgreSQL.
```postgresql
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
```
