# Proyecto de Aplicación Spring Boot

## Descripción
Esta es una aplicación desarrollada en **Java 17** con **Spring Boot**, utilizando **Maven** como herramienta de construcción. Está configurada para conectarse a una base de datos **MySQL** en un entorno de pruebas.

## Requisitos
- **Java 17**
- **Maven 3.x**
- **MySQL 5.7** o superior
- **Git** (opcional, si se descarga desde un repositorio)

## Configuración de la base de datos
Antes de ejecutar la aplicación, es necesario asegurarse de que la base de datos MySQL esté disponible y configurada correctamente:

1. Crear una base de datos con las siguientes características:
    - **Nombre**: `db_besy`
    - **Usuario**: `ema`
    - **Contraseña**: `12345`

2. El usuario debe tener todos los permisos sobre la base de datos, ya que es un entorno de pruebas.

## Instalación y Ejecución

1. Clonar el repositorio:
   ```bash
   git clone <https://github.com/AmayaSantos/Agenda>
   cd Agenda
   ```
2. Compilar la aplicación con Maven: 
    ```bash
    mvn clean install
   ```
3. Ejecutar la aplicación: 
    ```bash
    mvn spring-boot:run
    ```
   