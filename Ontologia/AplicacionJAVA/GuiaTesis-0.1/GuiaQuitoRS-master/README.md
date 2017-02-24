Guía de Quito
======

## Dependencias
- Maven
- Wildfly

## Configuración
- Definir la variable $WILDFLY_HOME con la ruta del wildfly.
- Ejecutar `mvn clean install wildfly:deploy` para compilar y desplegar el proyecto.
- **Nota:** Crear un datasource y desplegar el driver JDBC en la instancia de wildfly.


        <datasource jndi-name="java:jboss/datasources/GuiaQuitoDS" pool-name="GuiaQuitoDS" enabled="true" use-java-context="true">
            <connection-url>jdbc:postgresql://localhost:5432/guia-quito</connection-url>
            <driver-class>org.postgresql.Driver</driver-class>
            <driver>postgresql-9.2-1004.jdbc4.jar</driver>
            <security>
                <user-name>postgres</user-name>
                <password>1234</password>
            </security>
        </datasource>
