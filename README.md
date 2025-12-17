# ApiRestCustomerOrder
# Sistema de Gestión de Órdenes (Integración Mock + Seguridad JWT)

Este proyecto es una API REST desarrollada con **Spring Boot** que simula un sistema de comercio electrónico. Incluye la integración con un servicio externo de inventarios (Mock), validaciones de negocio y un sistema de seguridad completo basado en JSON Web Tokens (JWT).

## 📋 Características Implementadas

### 1. Integración de Inventario (Mock)
- **Endpoint Mock:** `GET /inventory/{sku}` que simula un sistema de almacén externo.
- **Consumo con WebClient:** Comunicación asíncrona y moderna entre el servicio de órdenes y el inventario.
- **Validación de Stock:** Reglas de negocio para impedir ventas sin stock suficiente.
- **Manejo de Errores:** Retorno de **HTTP 409 Conflict** si el stock es insuficiente.

### 2. Seguridad (JWT)
- **Autenticación Stateless:** Implementación de seguridad sin sesiones usando JWT.
- **Endpoint de Login:** Generación de tokens mediante usuario y contraseña.
- **Filtro de Seguridad:** Interceptor `OncePerRequestFilter` para validar el token en cada petición.
- **Manejo de Excepciones 401:** 

---

## 🛠️ Dependencias

El proyecto utiliza **Java 21** y **Maven**. Las dependencias clave en `pom.xml` son:

```xml
<!-- Spring Boot Web (API Rest) -->
<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webmvc</artifactId>
			<exclusions>
				<exclusion>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-starter-logging</artifactId>
				</exclusion>
			</exclusions>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jdbc</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-log4j2</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webmvc-test</artifactId>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>

		<dependency>
			<groupId>commons-codec</groupId>
			<artifactId>commons-codec</artifactId>
		</dependency>

		<dependency>
			<groupId>commons-io</groupId>
			<artifactId>commons-io</artifactId>
			<version>2.16.1</version>
		</dependency>

		<dependency>
			<groupId>com.microsoft.sqlserver</groupId>
			<artifactId>mssql-jdbc</artifactId>
		</dependency>

		<dependency>
			<groupId>com.google.code.gson</groupId>
			<artifactId>gson</artifactId>
		</dependency>

		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-api</artifactId>
			<version>0.11.5</version>
		</dependency>

		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-impl</artifactId>
			<version>0.11.5</version>
			<scope>runtime</scope>
		</dependency>

		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-jackson</artifactId>
			<version>0.11.5</version>
			<scope>runtime</scope>
		</dependency>

		<dependency>
			<groupId>org.junit.jupiter</groupId>
			<artifactId>junit-jupiter-api</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>org.mockito</groupId>
			<artifactId>mockito-core</artifactId>
		</dependency>

		<dependency>
			<groupId>org.jacoco</groupId>
			<artifactId>org.jacoco.core</artifactId>
			<version>0.8.12</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webflux</artifactId>
		</dependency>

	</dependencies>
```

---

## ⚙️ Configuración

El archivo de configuracion esta en el archivo archivo `src/main/resources/ApiCustomerClient.properties` con los siguientes valores:
Este archivo regularmente va fuera del proyecto para tener las canfiguraciones fuera sin necesidad de abrir el codigo, los datos estan en claro, lo ideal es tenerlos encriptados
tener un par de llaves para encriptar y desencriptarlos.

## 🚀 Pasos para Ejecución

1.  **Clonar o Descargar** el proyecto.
2.  **Ejecutar los Scripts SQL
3.  **Levantar el proyecto con el IDE de su preferencia

El servidor iniciará en: `http://localhost:8080`.

---

## 📖 Guía de Uso (Probando con Postman)

### Paso 1: Obtener el Token (Login)
Para acceder a cualquier endpoint protegido, primero necesitas autenticarte.
Estetos usuarios deberian estar guardados en una bd y con sus permisos a su app y su rol
*   **Método:** `POST`
*   **URL:** `http://localhost:8080/auth/token`
*   **Body (JSON):**
    ```json
    {
        "usuario": "admin",
        "clave": "Pftb7b639"
    }
    ```
*   **Respuesta Exitosa (200 OK):**
    ```json
    {
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpdGFsaWthIi..."
    }
    ```
    > **Nota:** Copia el valor del token recibido.

### Paso 2: Consultar Stock (Mock)
Simula la consulta al almacén. Este endpoint puede ser público o privado según tu configuración.
Este no pide autenticar, se consulta sin token
*   **Método:** `GET`
*   **URL:** `http://localhost:8080/api/v1/invetario/MOTO123`

  ### Posibles Respuestas:
1. ** 200 OK:** Retorna el inventario
    ```json
    {
        "sku": "MOTO123",
        "stockDisponible": 100
    }
    ```
    > **Tip:** Productos que inicien con "CASC" no hay inventario (ej. `CASC145289`), el stock regresará 0.
     
### Paso 3: Registrar un cliente (Endpoint Protegido)
Aquí se validará el Token y el Stock simultáneamente.

*   **Método:** `POST`
*   **URL:** `http://localhost:8080/api/v1/customers`
*    **Authorization (Bearer Token):**
    *   Value: `eyJhbGciOiJIUzI1Ni...` (Pega tu token aquí)
*   **Body (JSON):**
    ```json
    {
      "nombre":"Guadalupe Lopez",
      "email":"lupelopez@gmail.com",
      "telefono":"233333333"
    }
    ```
 #### Posibles Respuestas:
1.  ** 201 Created:** Si el token es válido, los datos son validos y tienen el formato correcto, y el correo no es repetido.
   ```json
    {
      "folio": "LYV202512171043222678",
      "resultado": "4"
   }
```
2.  ** 400 Bad Request:** Si los datos no son validos o no tienen el formato correcto.
```json
    {
      "folio": "LYV202512171048479152",
      "resultado": "El formato de email del cliente no es válido"
    }
```
3. 401 Unauthorized:** Si el token no es valido o expiro
   
4. 409 Confict:** Si correo es repetido
 ```json
    {
      "folio": "LYV202512171051004406",
      "resultado": "El correo electrónico ya se encuentra registrado."
    }
   ```
### Paso 4: Crear una Orden (Endpoint Protegido)
Aquí se validará el Token y el Stock simultáneamente.

*   **Método:** `POST`
*   **URL:** `http://localhost:8080/orders`
*   **Authorization (Bearer Token):**
    *   Value: `eyJhbGciOiJIUzI1Ni...` (Pega tu token aquí)
*   **Body (JSON):**
    ```json
    {
      "customerId": "1",
      "ordenes": [
        {
          "sku": "CASC12345678",
          "cantidad": "1",
          "precioUnitario": "28000.00"
        },
        {
          "sku": "MOTO12881257",
          "cantidad": "2",
          "precioUnitario": "850.50"
        }
          ]
    }
    ```

#### Posibles Respuestas:
1.  ** 201 Created:** Si el token es válido, los datos son validos y tienen el formato correcto.
   ```json
    {
      "folio": "LYV202512171043222678",
      "resultado": "1001"
   }
```

2.  ** 400 Bad Request:** Si algun dato esta mal
   ```json
    {
      "folio": "LYV202512171109156103",
      "resultado": "El formato del SKU no es válido (debe iniciar con 4 letras seguido de números) en la orden número 1"
    }
```
4.  **401 Unauthorized:** Si no envías el Authorization o el token expiró.
5.  **404 Not Found:** Si el id del cliente o algun producto no existe.
   ```json
    {
      "folio": "LYV202512171104530101",
      "resultado": "El Cliente especificado no existe."
    }
```    
4.  **409 Conflict:** Si pides más cantidad de la que hay en stock.
    ```json
    {
      "folio": "LYV202512171009050230",
      "resultado": "No hay stock suficiente para el producto con SKU: CASC12345678"
    }
    ```
### Paso 5: Busqueda de Cliente por id (Endpoint Protegido)
Busca el cliente por Id

*   **Método:** `GET`
*   **URL:** `http://localhost:8080/api/v1/customers/2`
*   **Authorization (Bearer Token):**
    *   Value: `eyJhbGciOiJIUzI1Ni...` (Pega tu token aquí)

#### Posibles Respuestas:
1.  ** 200 OK:** Si el token es válido, los datos son validos y tienen el formato correcto.
   ```json
    {
      {
        "folio": "LYV202512171116410137",
          "resultado": {
            "nombreCompleto": "Pedro Perez",
            "correoElectronico": "pedritodev@gmail.com",
            "telefono": "2333333333"
            }
        }
   }
```

2.  ** 400 Bad Request:** Si algun dato esta mal
   ```json
    {
      "folio": "LYV202512171118172158",
      "resultado": "El ID de cliente debe ser un numero entero valido"
    }
```
4.  **401 Unauthorized:** Si no envías el Authorization o el token expiró.
5.  **404 Not Found:** Si el id del cliente no existe.
   ```json
    {
      "folio": "LYV202512171118410643",
      "resultado": "No se encontraron datos para el ID de cliente proporcionado"
    }
```    

### Paso 6: Busqueda Orden por id de orden (Endpoint Protegido)
Busca el Orden por Id

*   **Método:** `GET`
*   **URL:** `http://localhost:8080/api/v1/ordenes/orders/1001`
*   **Authorization (Bearer Token):**
    *   Value: `eyJhbGciOiJIUzI1Ni...` (Pega tu token aquí)

#### Posibles Respuestas:
1.  ** 200 OK:** Si el token es válido, los datos son validos y tienen el formato correcto.
   ```json
    {
    "folio": "LYV202512171112566976",
    "resultado": {
        "folio": 1001,
        "fecha": "2025-12-16T21:10:18.41",
        "subtotal": 29701.0,
        "iva": 4752.16,
        "total": 34453.16,
        "cliente": {
            "nombreCompleto": "Pedro Perez",
            "correoElectronico": "pedritodev@gmail.com",
            "telefono": "2333x333333",
            "idCliente": "2"
        },
        "productos": [
            {
                "cantidad": "1",
                "precioUnitario": "28000.0",
                "importe": "28000.0"
            },
            {
                "cantidad": "2",
                "precioUnitario": "850.5",
                "importe": "1701.0"
            }
        ]
    }
  }
```

2.  ** 400 Bad Request:** Si algun dato esta mal
   ```json
    {
      "folio": "LYV202512171118172158",
      "resultado": "El ID de la orden debe ser un numero entero valido"
    }
```
4.  **401 Unauthorized:** Si no envías el Authorization o el token expiró.
5.  **404 Not Found:** Si el id de la orden no existe.
   ```json
    {
      "folio": "LYV202512171112242196",
      "resultado": "No se encontraron datos para el ID de orden proporcionado"
    }
```
---

## 🧪 Testing y Coverage

El proyecto incluye pruebas unitarias con **JUnit 5** y **Mockito**.

