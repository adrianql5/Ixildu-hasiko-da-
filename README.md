# 🎬 Ixildu-hasiko-da!

**Proyecto de Bases de Datos II**
Sistema de gestión para un cine

## 📝 Descripción del Proyecto

*Ixildu-hasiko-da!* es un proyecto académico orientado al diseño e implementación de una base de datos para la gestión integral de un **cine**. Desde la conceptualización del modelo de datos hasta los scripts funcionales, el sistema está pensado para cubrir las necesidades operativas típicas de un cine moderno: gestión de salas, películas, sesiones, clientes y venta de entradas.

Como homenaje a la cultura vasca, los datos utilizados en la base incluyen nombres y elementos culturales vascos, incluyendo a los jugadores del **Athletic Club de Bilbao** como parte del dataset.

---

## 👥 Autores del Diseño

* Xabier Nóvoa Gómez
* César Poza González
* Adrián Quiroga Linares
* Pablo Mouriño Lorenzo
* Carlos Moldes Peña
* Víctor Fraga Izquierdo

## 🛠️ Implementación

Desarrollada por:

* Adrián Quiroga Linares
* Xabier Nóvoa Gómez
* Víctor Fraga Izquierdo

---

## 📦 Componentes del Proyecto

El proyecto incluye las siguientes fases y entregables:

* 📚 **Modelo Entidad-Relación (E-R)**: Representación conceptual del sistema del cine, incluyendo películas, salas, sesiones, clientes, empleados y ventas.
* 🔄 **Definición de Transacciones**: Operaciones típicas como compra de entradas, cancelaciones, inserción de nuevas películas, etc.
* 📖 **Diccionario de Datos**: Detalle de todas las entidades, atributos, tipos de datos y relaciones.
* 🔗 **Modelo Relacional**: Traducción del modelo E-R al modelo lógico relacional.
* 🎨 **Diseño de Interfaces Gráficas**: Propuestas visuales para el sistema de gestión del cine y la interactuación del usuario.
* 🧾 **Scripts SQL**:

  * Creación de la base de datos (`scriptTablas.sql`)
  * Eliminación de la base de datos (`scriptEliminar.sql`)
  * Inserción de datos iniciales (`scriptInserts.sql`) — con contenido temático vasco y jugadores del Athletic Club

---

## 💻 Cómo ejecutar el proyecto

1. Ejecutar `scriptTablas.sql` en tu SGBD (MySQL, PostgreSQL, etc.) para crear la estructura de la base de datos.
2. Ejecutar `scriptInserts.sql` para poblar la base con los datos iniciales.
3. Para limpiar la base de datos, ejecutar `scriptEliminar.sql`.
4. (Opcional) Usar las vistas gráficas propuestas para imaginar la interfaz de gestión del cine.


