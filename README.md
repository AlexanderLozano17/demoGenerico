# 🚀 Spring Boot Generic Project

Este proyecto es una aplicación desarrollada con **Java** y **Spring Boot**, utilizando **Programación Genérica** para crear una arquitectura modular, flexible y reutilizable. Implementa **CRUD genérico**, manejo de excepciones y seguridad, facilitando el desarrollo de aplicaciones escalables.

## 🛠️ Tecnologías Utilizadas

- **Java 8+**
- **Spring Boot**
- **Spring Data JPA** (Hibernate)
- **Spring Security** (JWT)
- **Swagger / OpenAPI**
- **JUnit y Mockito**
- **Maven / Gradle**
- **Docker** (Opcional)

## 📂 Estructura del Proyecto

```
📦 src/main/java/com/empresa/proyecto/
├── controller/  -> Controladores genéricos REST  
├── service/     -> Servicios con lógica de negocio genérica  
├── repository/  -> Repositorios JPA genéricos  
├── model/       -> Entidades y DTOs  
├── config/      -> Configuraciones de seguridad y CORS  
├── exception/   -> Manejo centralizado de errores  
└── util/        -> Clases auxiliares  
```

## ✨ Programación Genérica en el Proyecto

Se ha implementado un **CRUD Genérico** con clases abstractas y tipos parametrizados `<T, ID>`:

```java
public interface GenericRepository<T, ID> extends JpaRepository<T, ID> {}

@Service
public abstract class GenericService<T, ID> {
    @Autowired private GenericRepository<T, ID> repository;
    
    public List<T> findAll() { return repository.findAll(); }
    public Optional<T> findById(ID id) { return repository.findById(id); }
    public T save(T entity) { return repository.save(entity); }
    public void deleteById(ID id) { repository.deleteById(id); }
}
```

Los **controladores REST** heredan de una base genérica:

```java
@RestController
@RequestMapping("/api/generic")
public class GenericController<T, ID> {
    @Autowired private GenericService<T, ID> service;

    @GetMapping public List<T> getAll() { return service.findAll(); }
    @PostMapping public T create(@RequestBody T entity) { return service.save(entity); }
}
```

### ✅ **Ventajas de esta Arquitectura**
✔ **Menos código repetitivo**  
✔ **Alta reutilización**  
✔ **Mayor escalabilidad**  

## 🚀 Instalación y Ejecución

1️⃣ **Clonar el repositorio**  
```sh
git clone https://github.com/usuario/proyecto.git
cd proyecto
```

2️⃣ **Configurar la base de datos** en `application.properties`  
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mi_base
spring.datasource.username=root
spring.datasource.password=admin
```

3️⃣ **Ejecutar el proyecto**  
```sh
mvn spring-boot:run
```

4️⃣ **Acceder a la API**  
📌 `http://localhost:8080/api/generic`

5️⃣ **Ver la documentación Swagger**  
📌 `http://localhost:8080/swagger-ui.html`

---

## 🧪 Pruebas

Ejecutar pruebas unitarias con:
```sh
mvn test
```

---

## 📜 Licencia
Este proyecto está bajo la licencia **MIT**.  

---

🚀 **¡Listo para usar y escalar!**

