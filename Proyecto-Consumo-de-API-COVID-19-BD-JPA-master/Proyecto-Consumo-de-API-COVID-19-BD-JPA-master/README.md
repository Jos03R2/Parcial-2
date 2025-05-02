## Descripción del Proyecto
**CovidStatsApp** es una aplicación Java de consola que consume una API externa de estadísticas de COVID-19, procesa los datos obtenidos y los almacena en una base de datos MySQL.  
El proyecto fue diseñado para aplicar **Programación Orientada a Objetos (POO)**, trabajar con **bases de datos** mediante **JPA**, realizar **tareas concurrentes** usando **multithreading**, y seguir una **arquitectura en capas** con buenas prácticas de diseño.

---

##  Objetivos Principales

- Aplicar principios fundamentales de la **Programación Orientada a Objetos** (POO).
- Utilizar tecnologías como **JPA (Jakarta Persistence API)**, **HTTPClient** y **MySQL**.
- Implementar el concepto de **multithreading** para ejecutar tareas concurrentes.
- Consumir **servicios RESTful** (API externa) y procesar los datos recibidos.
- Seguir una **arquitectura en capas** basada en buenas prácticas de desarrollo de software.

---

##  Estructura del Proyecto

```plaintext
com.mycompany.covidstatsapp
├── config
│   └── EntityManagerFactoryProvider.java
├── model
│   ├── Province.java
│   ├── Region.java
│   └── Report.java
├── repository
│   ├── ProvinceRepository.java
│   ├── RegionRepository.java
│   └── ReportRepository.java
├── service
│   ├── ProvinceService.java
│   ├── RegionService.java
│   └── ReportService.java
├── thread
│   └── DataCollectorThread.java
└── CovidStatsApp.java
```

---

##  Tecnologías Usadas

| Tecnología           | Uso principal                                                  |
|----------------------|-----------------------------------------------------------------|
| **Java SE**           | Lógica principal, multithreading, consumo de APIs              |
| **JPA (Hibernate)**   | Persistencia de datos en la base de datos MySQL                 |
| **HTTPClient**        | Consumo de API externa (servicios REST)                         |
| **MySQL**             | Base de datos para almacenar provincias, regiones y reportes   |
| **Maven**             | Gestión de dependencias y empaquetado del proyecto             |

---

##  Explicación de Componentes

- **CovidStatsApp.java**  
  Clase principal que configura el `EntityManager`, inicializa servicios, y arranca el hilo `DataCollectorThread` que recolecta y guarda datos automáticamente.

- **EntityManagerFactoryProvider.java**  
  Configura y proporciona acceso a la base de datos a través de `EntityManager`. Se conecta a MySQL utilizando datos de `application.properties`.

- **Modelos (`Province`, `Region`, `Report`)**  
  Entidades mapeadas a tablas en la base de datos. Cada clase representa un objeto real del dominio.

- **Repositories**  
  Clases responsables de las operaciones CRUD con las entidades.

- **Servicios (`Services`)**  
  Capa que intermedia entre el hilo y los repositorios para aplicar lógica de negocio.

- **Hilo `DataCollectorThread`**  
  Clase que implementa multithreading. Se encarga de solicitar la información al API REST, procesarla y almacenarla de manera concurrente.

---

##  Aplicación de Multithreading

Se usa un **hilo (`Thread`)** para:
- Ejecutar la tarea de recolección de datos sin bloquear el flujo principal.
- Permitir que la descarga y guardado de datos se realicen de forma más eficiente y escalable.

---

##  Consumo de Servicios REST

La aplicación se conecta a una **API pública** mediante el cliente HTTP de Java (`HTTPClient`) para:
- Obtener datos de COVID-19 por región y provincia.
- Extraer campos relevantes como número de casos confirmados, muertes, recuperados, etc.
- Procesar y almacenar esta información en la base de datos.

---

##  Arquitectura en Capas

Se aplicó una separación clara en **capas**:
- **Configuración** (`config`)
- **Modelado de datos** (`model`)
- **Acceso a datos** (`repository`)
- **Servicios de negocio** (`service`)
- **Hilos concurrentes** (`thread`)
- **Ejecución de la aplicación** (`CovidStatsApp.java`)

Esto permite:
- Mejor mantenibilidad.
- Facilita realizar pruebas unitarias.
- Promueve un desarrollo más limpio y profesional.

---

##  Requisitos Previos

- **Java JDK 17+**
- **Maven 3.8+**
- **Servidor MySQL** (Base de datos `covid_db` creada previamente)
- **Archivo `application.properties`** configurado correctamente:

```properties
db.url=jdbc:mysql://localhost:3306/covid_db
db.user=root
db.password=1234
```

---

##  Instrucciones para Ejecutar

1. **Clonar o descargar** el proyecto.
2. Configurar la conexión a la base de datos en el archivo `application.properties`.
3. **Compilar y empaquetar** usando Maven:

   ```bash
   mvn clean package
   ```

4. **Ejecutar la aplicación**:

   ```bash
   java -jar target/covidstatsapp-1.0-SNAPSHOT.jar
   ```

