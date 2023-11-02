# Libros - Spring Boot and Thymeleaf Web App with Language Switch

### Descripción:
Este proyecto representa mi trabajo en una actividad de clase donde desarrollé una aplicación web utilizando Spring Boot y Thymeleaf para gestionar una lista de libros. La característica principal de esta aplicación es la capacidad de cambiar entre inglés y español utilizando cookies, lo que permite una experiencia de usuario personalizada.

### Pasos de Implementación:

**1. Configuración del Entorno:**
- Configuré mi entorno de desarrollo utilizando Spring-Tool para eclipse y Thymeleaf.

**2. Creación del Proyecto:**
- Inicié un nuevo proyecto Spring Boot y definí la entidad "Libro" con atributos como título, autor etc...

**3. Implementación de la Gestión de Libros:**
- Desarrollé un controlador Spring MVC que maneja las operaciones CRUD para la lista de libros.
- Utilicé Thymeleaf para crear plantillas HTML que permiten a los usuarios ver, agregar, editar y eliminar libros.

**4. Integración de Cookies para Cambio de Idioma:**
- Implementé un mecanismo de cambio de idioma utilizando cookies a través de la biblioteca `javax.servlet.http.Cookie`.
- Diseñé un botón en la interfaz de usuario que permite a los usuarios cambiar entre inglés y español.
- Almacené los textos en inglés y español en archivos de propiedades para su fácil internacionalización.

**5. Pruebas y Depuración:**
- Realicé pruebas exhaustivas para garantizar que la aplicación funcionara correctamente.
- Verifiqué que el cambio de idioma se aplicara de manera efectiva a través de las cookies.

**6. Presentación y Discusión:**
- En la conclusión de la actividad, presenté mi proyecto a la clase y participé en una discusión sobre las lecciones aprendidas y los desafíos superados.

### Aprendizajes y Desafíos:
Esta actividad me permitió adquirir habilidades en el desarrollo web con Spring Boot y Thymeleaf. Aprendí sobre la gestión de cookies para la personalización de la experiencia del usuario, así como la internacionalización de una aplicación. Aún debo refactorizar y mejorar el código para implementar más funcionalidades.
