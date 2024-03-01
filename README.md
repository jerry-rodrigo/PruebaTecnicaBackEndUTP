# Note Controller API

This API provides endpoints for managing notes.

## Endpoints

### Listar notas

- **URL:** `/api/note/listar`
- **Method:** GET
- **Description:** Retrieves all notes with the associated username.
- **Authorization:** Requires authentication.
- **Roles:** All authenticated users can access this endpoint.
- **Response:** Returns a list of notes with the username associated.

### Obtener nota por ID

- **URL:** `/api/note/listarId/{id}`
- **Method:** GET
- **Description:** Retrieves a note by its ID with the associated username.
- **Authorization:** Requires authentication.
- **Roles:** All authenticated users can access this endpoint.
- **Parameters:**
    - `{id}`: The ID of the note to retrieve.
- **Response:** Returns the note with the specified ID and the associated username.

### Crear nota

- **URL:** `/api/note/crear`
- **Method:** POST
- **Description:** Creates a new note and sets the current username and submission date.
- **Authorization:** Requires authentication.
- **Roles:** Only users with the "ADMIN" role can access this endpoint.
- **Request Body:** The note object to create.
- **Response:** Returns the created note.

### Actualizar nota

- **URL:** `/api/note/actualizar/{id}`
- **Method:** PUT
- **Description:** Updates an existing note by its ID and sets the current username.
- **Authorization:** Requires authentication.
- **Roles:** Only users with the "ADMIN" role can access this endpoint.
- **Parameters:**
    - `{id}`: The ID of the note to update.
- **Request Body:** The updated information of the note.
- **Response:** Returns the updated note.

### Eliminar nota

- **URL:** `/api/note/eliminar/{id}`
- **Method:** DELETE
- **Description:** Deletes a note by its ID.
- **Authorization:** Requires authentication.
- **Roles:** Only users with the "ADMIN" role can access this endpoint.
- **Parameters:**
    - `{id}`: The ID of the note to delete.
- **Response:** No content.

## Authentication API

This API provides endpoints for user authentication and registration.

### Register User

- **URL:** `/api/auth/register`
- **Method:** POST
- **Description:** Registers a user with the "user" role.
- **Request Body:** The user registration data.
- **Response:** Returns a success message if the registration is successful.

### Register Admin

- **URL:** `/api/auth/registerAdm`
- **Method:** POST
- **Description:** Registers a user with the "admin" role.
- **Authorization:** Requires authentication.
- **Roles:** Only users with the "ADMIN" role can access this endpoint.
- **Request Body:** The admin registration data.
- **Response:** Returns a success message if the registration is successful.

### Login

- **URL:** `/api/auth/login`
- **Method:** POST
- **Description:** Authenticates a user and generates a JWT token.
- **Request Body:** The user login credentials.
- **Response:** Returns the JWT token if the authentication is successful.

## Security Configuration

This section provides information on the security configuration of the API.

### Security Configuration Class

- **Class:** `SecurityConfig`
- **Description:** Configures the security settings for the API.
- **Configuration:**
    - Disables CSRF protection.
    - Configures a custom authentication entry point for unauthorized access handling.
    - Configures session management to be stateless.
    - Defines authorization rules for different endpoints based on roles.
    - Adds a JWT authentication filter to the filter chain.
    - Allows access to Swagger UI and API documentation endpoints without authentication.

### Password Encoding

- **Class:** `PasswordEncoder`
- **Description:** Configures the password encoder used for user authentication.

### Authentication Manager

- **Class:** `AuthenticationManager`
- **Description:** Configures the authentication manager used for user authentication.

## Dependencies

This section lists the dependencies used in the API.

- `spring-boot-starter-web`
- `spring-boot-starter-security`
- `spring-boot-starter-data-jpa`
- `jjwt-api`
- `jjwt-impl`
- `jjwt-jackson`
- `spring-boot-starter-validation`
- `spring-boot-configuration-processor`
- `spring-boot-starter-test` (for testing purposes)
- `h2-database` (for testing purposes)

Please refer to the project's `pom.xml` file for the specific versions of the dependencies used.

## How to Run

To run the API, follow these steps:

1. Ensure that you have Java and Maven installed on your system.
2. Clone the project repository.
3. Open a terminal and navigate to the project's root directory.
4. Run the command: `mvn spring-boot:run`.
5. The API will start running# Note Controller API

This API provides endpoints for managing notes.

## Endpoints

### List Notes

- **URL:** `/api/note/list`
- **Method:** GET
- **Description:** Retrieves all notes.
- **Authorization:** Requires authentication.
- **Roles:** All authenticated users can access this endpoint.
- **Response:** Returns a list of notes.

### Get Note by ID

- **URL:** `/api/note/{id}`
- **Method:** GET
- **Description:** Retrieves a note by its ID.
- **Authorization:** Requires authentication.
- **Roles:** All authenticated users can access this endpoint.
- **Parameters:**
    - `{id}`: The ID of the note to retrieve.
- **Response:** Returns the note with the specified ID.

### Create Note

- **URL:** `/api/note/create`
- **Method:** POST
- **Description:** Creates a new note.
- **Authorization:** Requires authentication.
- **Roles:** Only users with the "ADMIN" role can access this endpoint.
- **Request Body:** The note object to create.
- **Response:** Returns the created note.

### Update Note

- **URL:** `/api/note/update/{id}`
- **Method:** PUT
- **Description:** Updates an existing note by its ID.
- **Authorization:** Requires authentication.
- **Roles:** Only users with the "ADMIN" role can access this endpoint.
- **Parameters:**
    - `{id}`: The ID of the note to update.
- **Request Body:** The updated information of the note.
- **Response:** Returns the updated note.

### Delete Note

- **URL:** `/api/note/delete/{id}`
- **Method:** DELETE
- **Description:** Deletes a note by its ID.
- **Authorization:** Requires authentication.
- **Roles:** Only users with the "ADMIN" role can access this endpoint.
- **Parameters:**
    - `{id}`: The ID of the note to delete.
- **Response:** No content.

## Authentication API

This API provides endpoints for user authentication and registration.

### Register User

- **URL:** `/api/auth/register`
- **Method:** POST
- **Description:** Registers a new user.
- **Request Body:** The user registration data.
- **Response:** Returns a success message if the registration is successful.

### Login

- **URL:** `/api/auth/login`
- **Method:** POST
- **Description:** Authenticates a user and generates a JWT token.
- **Request Body:** The user login credentials.
- **Response:** Returns the JWT token if the authentication is successful.

## Security Configuration

This section provides information on the security configuration of the API.

### Security Configuration Class

- **Class:** `SecurityConfig`
- **Description:** Configures the security settings for the API.
- **Configuration:**
    - Disables CSRF protection.
    - Configures a custom authentication entry point for unauthorized access handling.
    - Configures session management to be stateless.
    - Defines authorization rules for different endpoints based on roles.
    - Adds a JWT authentication filter to the filter chain.
    - Allows access to Swagger UI and API documentation endpoints without authentication.

### Password Encoding

- **Class:** `PasswordEncoder`
- **Description:** Configures the password encoder used for user authentication.

### Authentication Manager

- **Class:** `AuthenticationManager`
- **Description:** Configures the authentication manager used for user authentication.

## Dependencies

This section lists the dependencies used in the API.

- `spring-boot-starter-web`
- `spring-boot-starter-security`
- `spring-boot-starter-data-jpa`
- `jjwt-api`
- `jjwt-impl`
- `jjwt-jackson`
- `spring-boot-starter-validation`
- `spring-boot-configuration-processor`
- `spring-boot-starter-test` (for testing purposes)
- `h2-database` (for testing purposes)

Please refer to the project's `pom.xml` file for the specific versions of the dependencies used.

## How to Run

To run the API, follow these steps:

1. Ensure that you have Java and Maven installed on your system.
2. Clone the project repository.
3. Open a terminal and navigate to the project's root directory.
4. Run the command: `mvn spring-boot:run`.
5. The API will start running.

Aquí tienes los pasos que puedes agregar a tu README.md para generar un JAR, construir un contenedor Docker y algunos ejemplos de comandos CURL para acceder a los endpoints:

## Generar un JAR y construir un contenedor Docker

1. Para generar un JAR, ejecuta el siguiente comando en la terminal:

   ```
   mvn clean package
   ```

   Esto generará un archivo JAR en la carpeta `target` del proyecto.

2. Para construir la imagen del contenedor Docker, ejecuta el siguiente comando:

   ```
   docker build -t desafiotecnico-utp:1.0 .
   ```

   Este comando construirá una imagen del contenedor con el nombre `desafiotecnico-utp` y la etiqueta `1.0`. Asegúrate de incluir el punto `.` al final del comando para indicar que el archivo Dockerfile se encuentra en el directorio actual.

3. Para iniciar el contenedor, ejecuta el siguiente comando:

   ```
   docker run -p 8080:8080 desafiotecnico-utp:1.0
   ```

   Este comando iniciará el contenedor y mapeará el puerto `8080` del contenedor al puerto `8080` de tu máquina local. Puedes cambiar el número de puerto según tus necesidades.

4. Para detener el contenedor, primero necesitas obtener el ID o el nombre del contenedor en ejecución. Ejecuta el siguiente comando para obtener una lista de todos los contenedores en ejecución:

   ```
   docker ps
   ```

   Busca el ID o el nombre del contenedor que deseas detener.

5. Una vez que hayas obtenido el ID o el nombre del contenedor, ejecuta el siguiente comando para detenerlo:

   ```
   docker stop <ID o nombre del contenedor>
   ```

   Reemplaza `<ID o nombre del contenedor>` con el ID o el nombre correspondiente.

## Ejemplos de comandos CURL para endpoints

Aquí tienes algunos ejemplos de comandos CURL para acceder a los endpoints de tu aplicación:

1. Registrar un administrador:

   ```
   curl --location --request POST 'http://localhost:8080/api/auth/registerAdm' \--header 'Content-Type: application/json' \--header 'Cookie: JSESSIONID=1A45F4DA74396A6EF362BCAAEEFDD778' \--data-raw '{ "username": "Jerry", "password": "123"}'
   ```

2. Realizamos el Login con el usuario administrador creado anteriormente:

   ```
   curl --location --request POST 'http://localhost:8080/api/auth/login' \--header 'Content-Type: application/json' \--header 'Cookie: JSESSIONID=1A45F4DA74396A6EF362BCAAEEFDD778' \--data-raw '{ "username": "Jerry","password": "123"}'
   ```

   Reemplaza `{id}` con el ID del registro que deseas obtener.

3. Crear un nuevo registro:

   ```
   curl --location --request POST 'http://localhost:8080/api/note/crear' \--header 'Content-Type: application/json' \--header 'Cookie: JSESSIONID=1A45F4DA74396A6EF362BCAAEEFDD778' \--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSb2RyaWdvIiwiaWF0IjoxNzA5MjI2NTE3LCJleHAiOjE3MDkyMjY4MTZ9.vnCO2p8ZmW1wc4811PXYoPUKHCBYj8VMmKmOgfVkyHZj4jyiQCZ9WtTFXCPHRz5hn1CXVZDcD9TfuvWOHAQI2g' \--data-raw '{ "title": "Prueba", "content": "Probando servicio"}'
   ```

   Asegúrate de reemplazar `"campo1":"valor1","campo2":"valor2"` con los valores correspondientes para el nuevo registro.

4. Actualizar un registro existente por ID:

   ```
   curl --location --request PUT 'http://localhost:8080/api/note/actualizar/1' \--header 'Content-Type: application/json' \--header 'Cookie: JSESSIONID=1A45F4DA74396A6EF362BCAAEEFDD778' \--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSb2RyaWdvIiwiaWF0IjoxNzA5MjI2NTE3LCJleHAiOjE3MDkyMjY4MTZ9.vnCO2p8ZmW1wc4811PXYoPUKHCBYj8VMmKmOgfVkyHZj4jyiQCZ9WtTFXCPHRz5hn1CXVZDcD9TfuvWOHAQI2g' \--data-raw '{ "title": "Prueba de la actualización", "content": "Probando servicio"}'
   ```

   Reemplaza `{id}` con el ID del registro que deseas actualizar y `"campo1":"nuevoValor1","campo2":"nuevoValor2"` con los nuevos valores para el registro.

5. Eliminar un registro por ID:

   ```
   curl -X DELETE http://localhost:8080/api/registros/{id}
   ```

   Reemplaza `{id}` con el ID del registro que deseas eliminar.

Recuerda reemplazar `localhost:8080` con la dirección y el puerto correctos en caso de que tu aplicación se esté ejecutando en un entorno diferente.

¡Asegúrate de tener Docker y Maven instalados y configurados correctamente antes de ejecutar estos comandos!