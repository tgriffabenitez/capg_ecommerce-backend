
## Plataforma E-Commerce Back-End

Este repositorio contiene el proyecto de E-Commerce para la venta de productos semi-personalizados desarrollado como parte de una academia de Java patrocinada por la Universidad Tecnologica Nacional - FRBA y Capgemini. Durante esta academia, se abordaron temas relacionados con el desarrollo de aplicaciones web utilizando: Java, Spring boot, MYSQL, Hibernate, Angular y Bootstrap. Se llevó a cabo este proyecto como una aplicación práctica de lo aprendido.

Este proyecto esta diseñado y desarrollado con una arquitectura web, con dos componentes
- FrontEnd: proyecto Front usado Angular2.
- BackEnd: proyecto API REST con persistencia.

Se espera que este proyecto sirva como un ejemplo de las capacidades adquiridas durante la academia y como una referencia para futuros proyectos similares.

## Características

- Los gestores de la plataforma pueden cargar productos base que pueden ser personalizados y comercializados, como remeras, gorras, fundas para smartphones, zapatillas, entre otros.

- Los vendedores, que son clientes de los gestores, pueden personalizar cada uno de los productos que tienen a disposición, agregándole imágenes o frases para que sean estampadas en las áreas predefinidas.

- Los compradores son considerados “esporádicos”, es decir, que no son frecuentes y no necesitan generarse una cuenta en el sistema para poder realizar una compra.
    
- La plataforma contempla el concepto de “carrito de compra”, es decir, que el comprador puede ir seleccionando todos los productos que quiera y luego comprarlos todos juntos.
    
- Una vez que el cliente selecciona el o los productos personalizados, el sistema calcula el total de la compra y genera una Factura Electrónica a partir de un Servicio Externo.

### Pantallas del comprador

- Login
- Proceso de compra
- Listado de productos - Buscador
- Agregar al carrito
- Confirmar Compra
- Mostrar resultado de Compra
- Ver el listado de las compras realizadas

### ABM de Productos
- ABM de productos base
- ABM de productos personalizados

## Consideraciones para la entrega

- Para probar las API se proporciona un archivo Postman con los requests/JSON ya armados.

## Documentación de las APIs

Para facilitar la comprensión y el uso de nuestras APIs, hemos implementado la documentación utilizando Swagger. Esto permite ver la información de los endpoints disponibles, los parámetros de entrada y salida, y probar las funcionalidades directamente desde la documentación.

Para acceder a la documentación, una vez levantado el servidor, ingresar a la url "http://localhost:puerto/swagger-ui.html" en su navegador web.

En la documentación se podrán encontrar los endpoints para realizar las operaciones CRUD de Productos Base y Productos Personalizados, así como los endpoints para las funcionalidades de compra y carrito de compra.


## Diagrama de Entidad-Relación de la Base de Datos
Incluimos un diagrama de entidad-relación de nuestra base de datos para facilitar la comprensión de la estructura de datos utilizada en este proyecto.

![2023-01-26_09-46](https://user-images.githubusercontent.com/64086261/214839273-2113e6e8-0fb1-4710-ac46-0a38a9293fcf.png)

En este diagrama se pueden observar las entidades principales utilizadas en el sistema, como Productos Base, Productos Personalizados, Características de Personalización, Vendedores y Compradores, así como las relaciones entre ellas. Cada entidad tiene atributos específicos que se detallan en la descripción del diagrama.


## Nota
No se han implementado las pantallas de registrar usuario y pantallas para la gestión de productos base y personalizados, ya que solo se requiere el soporte de la API.

