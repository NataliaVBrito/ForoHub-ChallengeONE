🧠 ForoHub - Challenge Back End de Alura LATAM

---

Este foro simula el funcionamiento interno del foro de Alura, permitiendo gestionar tópicos de discusión relacionados con cursos y resolver dudas entre estudiantes y profesores.

El desafío propuesto: **construir una API REST funcional** que represente el backend del foro.  
Usando **Java + Spring Boot**, implementamos el manejo de tópicos, autenticación y persistencia de datos.

---

## 🎯 Objetivos

La API permite realizar un CRUD completo sobre los **tópicos** del foro:

✅ Crear un nuevo tópico  
✅ Listar todos los tópicos existentes  
✅ Consultar un tópico por ID  
✅ Actualizar un tópico  
✅ Eliminar un tópico

Además, cuenta con:
- Autenticación para restringir el acceso
- Validaciones de negocio
- Uso de DTOs para separación de capas
- Acceso a datos a través de JPA/Hibernate
- Arquitectura limpia basada en capas

---

## ⚙️ Tecnologías Usadas

| Herramienta         | Descripción                             |
|---------------------|-----------------------------------------|
| Java 17             | Lenguaje de programación principal      |
| Spring Boot         | Framework backend                       |
| Spring Data JPA     | Persistencia con Hibernate              |
| Spring Security     | Seguridad, login y acceso restringido   |
| H2 / MySQL          | Base de datos (configurable)            |
| ModelMapper         | Conversión de entidades a DTO           |
| Lombok              | Anotaciones para reducir boilerplate    |
| Trello              | Gestión ágil del desarrollo             |

---

## 🔐 Seguridad

La API incluye **autenticación basada en formularios** utilizando Spring Security.  
Para acceder a los endpoints protegidos es necesario autenticarse vía `POST /login`.

---

## 🧪 Endpoints disponibles

| Método | Endpoint            | Descripción                     |
|--------|----------------------|---------------------------------|
| POST   | `/topicos/crear`     | Crear un nuevo tópico           |
| GET    | `/topicos`           | Listar todos los tópicos        |
| GET    | `/topicos/{id}`      | Obtener un tópico por ID        |
| PUT    | `/topicos/{id}`      | Actualizar un tópico existente  |
| DELETE | `/topicos/{id}`      | Eliminar un tópico              |

Todos los endpoints están diseñados siguiendo buenas prácticas REST.
