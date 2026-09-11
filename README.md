# Base Student

Proyecto base de una aplicacion Spring Boot para gestionar informacion de estudiantes. Actualmente incluye la configuracion inicial de la aplicacion, el modelo JPA de estudiante y su DTO de transferencia. La capa REST todavia no esta implementada.

## Estado actual

- Aplicacion Spring Boot con clase de arranque funcional.
- Modelo `StudentModel` mapeado a la tabla `student`.
- DTO `StudentDto` para transportar datos de estudiantes.
- Conversion entre entidad y DTO mediante `toDto()` y `toModel()`.
- Dependencia de PostgreSQL incluida para ejecuciones con base de datos.
- Prueba de contexto Spring Boot incluida.
- Controladores, repositorios y servicios pendientes de implementar.

## Tecnologias

- Java 26
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven Wrapper
- JUnit 5 y Spring Boot Test

## Requisitos

- JDK 26 configurado en `JAVA_HOME`.
- PostgreSQL instalado si se desea conectar una base de datos.
- Maven no es obligatorio porque el proyecto incluye Maven Wrapper.

Comprobar las versiones instaladas:

```bash
java -version
./mvnw -version
```

En Windows PowerShell se puede usar:

```powershell
java -version
./mvnw.cmd -version
```

## Instalacion y compilacion

Clonar o abrir el proyecto y ejecutar:

```bash
./mvnw clean compile
```

En Windows:

```powershell
./mvnw.cmd clean compile
```

Para ejecutar las pruebas:

```bash
./mvnw test
```

Para iniciar la aplicacion:

```bash
./mvnw spring-boot:run
```

En Windows:

```powershell
./mvnw.cmd spring-boot:run
```

La clase principal es `com.example.base_student.BaseStudentApplication`.

## Configuracion

La configuracion general se encuentra en:

- `src/main/resources/application.properties`
- `src/main/resources/application-dev.properties`

La aplicacion se identifica como `base_student`.

La configuracion de desarrollo actualmente apunta a:

| Propiedad | Valor actual |
|---|---|
| Motor | PostgreSQL |
| URL | `jdbc:postgresql://localhost:5432/base_student` |
| Usuario | `postgres` |
| Contrasena | `postgres` |
| Driver | `org.postgresql.Driver` |

Antes de usar una base de datos local, crear la base de datos:

```sql
CREATE DATABASE base_student;
```

### Nota sobre la configuracion de PostgreSQL

Las propiedades actuales usan el prefijo `spring.database.*`. Para que Spring Boot configure automaticamente el datasource de JPA, normalmente deben declararse con el prefijo `spring.datasource.*`, por ejemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/base_student
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
```

No se han cambiado esas propiedades en este README; la correccion debe hacerse en `application-dev.properties` cuando se vaya a activar la conexion real con PostgreSQL.

## Modelo de datos

`StudentModel` representa la tabla `student` y contiene los siguientes campos:

| Campo Java | Columna SQL | Tipo | Descripcion |
|---|---|---|---|
| `id` | `id` | `Integer` | Identificador autogenerado |
| `name` | `name` | `String` | Nombre del estudiante, hasta 80 caracteres |
| `lastName` | `last_name` | `String` | Apellido del estudiante, hasta 80 caracteres |
| `email` | `email` | `String` | Correo electronico, hasta 80 caracteres |
| `phone` | `phone` | `String` | Telefono, hasta 80 caracteres |

La entidad usa `GenerationType.IDENTITY` para generar el identificador desde la base de datos.

## DTO y conversiones

`StudentDto` tiene los mismos datos que la entidad. Su campo `id` esta marcado con `@JsonIgnore`, por lo que no se incluye en la serializacion JSON del DTO.

Conversiones disponibles:

```java
StudentDto dto = studentModel.toDto();
StudentModel model = dto.toModel();
```

Ambas clases tambien redefinen `toString()` para serializar el objeto mediante `ObjectMapper`.

## Estructura del proyecto

```text
base_student/
├── pom.xml
├── mvnw
├── mvnw.cmd
├── HELP.md
├── README.md
└── src/
    ├── main/
    │   ├── java/
    │   │   ├── com/example/base_student/
    │   │   │   └── BaseStudentApplication.java
    │   │   └── com/base_alumno/
    │   │       ├── dto/StudentDto.java
    │   │       └── model/StudentModel.java
    │   └── resources/
    │       ├── application.properties
    │       └── application-dev.properties
    └── test/
        └── java/com/example/base_student/
            └── BaseStudentApplicationTests.java
```

## API disponible

Actualmente no existen controladores REST en `src/main/java`. Por tanto, no hay endpoints HTTP de estudiantes disponibles todavía.

Para completar la funcionalidad se recomienda agregar:

1. Un repositorio basado en `JpaRepository<StudentModel, Integer>`.
2. Un servicio para las operaciones de negocio.
3. Un controlador REST, por ejemplo bajo `/students`.
4. Pruebas unitarias y de integracion para las operaciones CRUD.

## Consideraciones de paquetes

La clase principal esta en `com.example.base_student`, mientras que la entidad y el DTO estan en `com.base_alumno`. Spring Boot escanea por defecto el paquete de la clase principal y sus subpaquetes, por lo que `com.base_alumno` no queda incluido automaticamente.

Antes de registrar repositorios o servicios en `com.base_alumno`, se debe elegir una de estas alternativas:

- Mover los paquetes de dominio debajo de `com.example.base_student`.
- Configurar explicitamente el escaneo con `@EntityScan` y `@EnableJpaRepositories`.
- Ampliar el escaneo de componentes con `@ComponentScan` cuando sea necesario.

## Pruebas

La prueba actual es `BaseStudentApplicationTests`, que verifica la carga del contexto Spring Boot:

```bash
./mvnw test
```

En Windows:

```powershell
./mvnw.cmd test
```

## Construccion del artefacto

Para generar el archivo ejecutable:

```bash
./mvnw clean package
```

El resultado se genera en `target/`.

Para ejecutar el JAR generado:

```bash
java -jar target/base_student-0.0.1-SNAPSHOT.jar
```

## Variables y credenciales

Las credenciales incluidas son valores locales de ejemplo. No deben utilizarse en entornos compartidos o de produccion. Se recomienda externalizarlas mediante variables de entorno o configuracion segura antes de desplegar la aplicacion.

## Licencia

No se ha definida una licencia para este proyecto.
