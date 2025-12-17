## 🚀 Propuesta Arquitectónica y Seguridad

Actualmente los datos y configuraciones se manejan en un `.properties`, pero todos estan en claro, la idea es que todo eso este encriptado y en otro lado, para que cualquiero configuracion quede fuera y no se tenga que abrir el codigo para modificarlo.

Este enfoque busca mitigar vulnerabilidades  y optimizar el consumo de recursos sin incurrir en costos elevados de infraestructura.

### 1. Gestión de Identidad y Roles (Auth Service)
**Objetivo:** Desacoplar la seguridad del núcleo de negocio para permitir escalabilidad independiente.

*   **Microservicio de Autenticación Dedicado:** Separar la lógica de login en una API independiente (`Auth-API`).
*   **Persistencia RBAC (Role-Based Access Control):** Migrar de usuarios en memoria a una estructura relacional (`TB_USERS`, `TB_ROLES`, `TB_PERMISSIONS`). Esto permite la gestión dinámica de accesos sin necesidad de *redeploy*.
*   **JWT Enriquecido:** El token generado incluirá *claims* de autorización pre-validados, permitiendo que el servicio de órdenes funcione sin consultar BD para validar permisos en cada petición.

### 2. Estrategia de Cifrado y Configuración (Low-Cost Security)
**Objetivo:** Proteger datos sensibles (BD Credentials, API Keys) evitando el texto plano en repositorios, sin depender de *Vaults* externos costosos.

*   **Propiedades Cifradas:** Ninguna contraseña existirá en texto plano en los archivos `application.properties`. Se almacenarán cadenas cifradas.

*   **Librería de Seguridad Compartida (Shared JAR):** Desarrollo de un componente interno (`security-core.jar`) integrado como dependencia en todos los microservicios.
    *   **Responsabilidad Única:** Este componente desencripta las propiedades únicamente al inicio de la aplicación (*Startup*).
    *   **Estandarización:** Garantiza que todos los servicios usen el mismo algoritmo de cifrado (ej. AES-256).
*   **Intercambio Seguro (Session Key IDs):** Para la comunicación entre APIs, se implementará un mecanismo de IDs temporales con vigencia limitada ligados a llaves en memoria, evitando el tráfico de secretos reales por la red.

### 3. Optimización y Calidad de Código (SonarQube Compliance)
**Objetivo:** Reducir Deuda Técnica, vulnerabilidades de seguridad y optimizar el uso de memoria.

*   **Componente de Configuración Singleton:** Implementación de una clase `@Component` que lee y desencripta las properties una única vez.
    *   Uso de *Getters* inmutables para acceder a los valores.
    *   Elimina la inyección repetitiva de `@Value` y lecturas de disco innecesarias.
*   **Centralización de Constantes:** Eliminación de "Magic Strings" y datos *hardcodeados*. Se usarán clases estáticas (`AppConstants`, `BusinessErrors`), reduciendo la creación de objetos en el *Heap* y eliminando *Security Hotspots* en los escaneos de código estático.
*   **Capa de Persistencia Genérica:** Uso del patrón Repository para aislar las consultas SQL de la lógica de negocio.
