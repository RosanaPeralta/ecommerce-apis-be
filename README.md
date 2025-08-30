# 🛒 ecommerce-apis-be

Backend de **Amancay**, un e‑commerce construido con **Spring Boot** y **MySQL**. Expone APIs para administrar productos, categorías y demás entidades del dominio.

---

## 📚 Tabla de contenidos
- [Sobre el proyecto](#sobre-el-proyecto)
- [Enlaces útiles](#enlaces-útiles)
- [Primeros pasos](#primeros-pasos)
  - [Configurar la base de datos](#configurar-la-base-de-datos)
  - [Configurar la aplicación](#configurar-la-aplicación)
- [Prototipo en figma](#prototipo-figma)

---

## 🧩 Sobre el proyecto
Este repositorio contiene el backend del e‑commerce **Amancay**. Está desarrollado en **Java 17+** usando **Spring Boot 3.x** y persiste datos en **MySQL 8** mediante **JPA/Hibernate**.

---

## 🔗 Enlaces útiles
- 🗂️ **Trello**: <https://trello.com/b/AISqZ52A/aplicaciones-interactivas>
- 🧭 **Repositorio (GitHub)**: <https://github.com/RosanaPeralta/ecommerce-apis-be>
- 📫 **Workspace Postman**: <https://www.postman.com/apis88-8053/apis-workspace/collection/2iy3id1/api-documentation-reference>

---

## 🚀 Primeros pasos 


### Configurar la base de datos
1. Inicia tu servidor MySQL 8.
2. Crea la base de datos **amancay** (si no existe):

```sql
CREATE DATABASE IF NOT EXISTS amancay;
USE amancay;
```

### Configurar la aplicación
Edita el archivo `amancay/src/main/resources/application.properties` con tus credenciales locales:

```properties
# Datasource
spring.datasource.url=jdbc:mysql://localhost:3306/amancay?useSSL=false&serverTimezone=UTC
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Server
server.port=8080

La API quedará disponible (por defecto) en `http://localhost:8080`.
```

---

## 🧪 Prototipo en figma

- <https://www.figma.com/design/Jd1Sk9rtLyLPmGFGQvJTcb/Prototypes-Amancay?node-id=0-1&p=f&t=YjN0LRIeTZfwEPZh-0>