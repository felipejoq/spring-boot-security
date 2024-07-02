# App Spring Boot + Security

Esta aplicación pone a prueba los conocimientos sobre Spring Boot + Spring Security
además de otras cosas como Spring Data JPA, Rest, etc.

## ⚙️ Cómo ejecutar la aplicación

### 📋 Pre-requisitos
- Construir la Base de Datos:
El archivo para la construcción de la base de datos puede ser consultado en: [db_schema.sql](sql/db_schema.sql)

- Debe tener un servidor de base de datos MySQL o MariaDB en ejecución.
- Debe tener Java 17 instalado en su máquina.
- Debe tener Maven instalado en su máquina.
- Debe tener Git instalado en su máquina.

1. Clona el repositorio en tu máquina local usando `git clone https://github.com/felipejoq/vwallet.git`.
2. Navega al directorio del proyecto usando `cd vwallet`.
3. Ejecuta `mvn clean install` para construir el proyecto.
4. Ejecuta `mvn spring-boot:run` para iniciar la aplicación.

La aplicación se ejecutará en `http://localhost:8080`. Y tiene los siguientes servicios creados:

- POST `/login` - Iniciar sesión.
Necesita un body:
```json
{
    "username": "felipe",
    "password": "123123"
}
```

- POST `/user/register` - Registrar un nuevo usuario.
Necesita un body:
```json
{
  "name": "Leo",
  "password": "123123",
  "email": "leo@example.com"
}
```

- GET `/user?page=0&size=3&sort=id,desc` - Obtener lista de usuarios paginados.
Requiere autenticación.

- GET `/bank-account` - Obtener lista de cuentas bancarias.
Requiere autenticación y autorización.

- POST `/transaction` - Obtener lista de transacciones.
Requiere autenticación y autorización.

- POST `/deposit` - Realizar un depósito.
  Requiere autenticación y autorización.

- POST `/withdraw` - Realizar un retiro.
  Requiere autenticación y autorización.

- POST `/transfer` - Realizar una transferencia.
  Requiere autenticación y autorización.

### 👨‍💻 Configuración de la base de datos

El archivo SQL se encuentra en `src/main/resources/db.sql`. Puede importar este archivo en su servidor de base de datos para crear la base de datos y las tablas necesarias.

La aplicación utiliza MariaDB como base de datos. Puede cambiar la configuración de la base de datos en el archivo `application.properties` ubicado en `src/main/resources`.

```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/vwallet
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.jpa.hibernate.ddl-auto=validate
```
## ℹ️ Usuarios de prueba
| nombre | correo             | password |
|--------|--------------------|----------|
| felipe | felipe@example.com | 123123   |
| maria  | maria@example.com  | 123123   |
| pedro  | pedro@example.com  | 123123   |
| ana    | ana@example.com    | 123123   |
| elena  | elena@example.com  | 123123   |