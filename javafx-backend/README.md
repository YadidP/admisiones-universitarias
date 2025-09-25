# JavaFX Backend (Admission System)

Instrucciones rápidas para desarrolladores:

- Base URL de la API: `http://localhost:8080/api`
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`
- Guía de integración frontend: `http://localhost:8080/docs/frontend-integration.html`

Requisitos:
- Java 21 (recomendado). Asegúrate que `mvn -v` muestre Java 21.

Ejecutar la API (desde el directorio `javafx-backend`):

```powershell
# establecer JAVA_HOME si es necesario (ajusta la ruta a tu instalación)
$env:JAVA_HOME = 'C:\Program Files\Java\jdk-21'
$env:Path = "$env:JAVA_HOME\bin;" + $env:Path

mvn -DskipTests spring-boot:run
```

Endpoints útiles:
- `GET /api/users` - listar usuarios
- `GET /api/users/username/{username}` - buscar usuario por username (usado por el frontend para login básico)
- `POST /api/postulantes` - crear postulante
- `GET /api/postulantes/{id}` - obtener postulante por id
- `GET /api/postulantes/ci/{ci}` - obtener postulante por CI
- `GET /api/postulantes/{id}/documentos` - listar documentos
- `POST /api/postulantes/{id}/documentos/{docId}/upload` - subir archivo (binary)
- `GET /api/postulantes/{id}/resultado` - obtener resultado JSON
- `GET /api/postulantes/{id}/resultado/pdf` - descargar PDF

Notas y próximos pasos recomendados:
- Implementar autenticación real (login con password + JWT) para producción.
- Validar y proteger endpoints de subida de archivos.
- Añadir pruebas E2E que recorran los principales user stories.

Documentación generada con Springdoc (Swagger). Accede a la UI para ver ejemplos de request/responses.

Ejemplos rápidos con curl:

```bash
# List users
curl -sS http://localhost:8080/api/users | jq

# Get user by username
curl -sS http://localhost:8080/api/users/username/admin | jq

# Create postulante
curl -XPOST -H "Content-Type: application/json" -d '{"ci":"1234567","nombres":"Juan","apellidos":"Perez","email":"juan@example.com"}' http://localhost:8080/api/postulantes | jq
```
