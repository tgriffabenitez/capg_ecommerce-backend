package ar.utn.capgemini.ecommerce;

import ar.utn.capgemini.ecommerce.model.entities.*;
import ar.utn.capgemini.ecommerce.model.utils.ESTADO;
import ar.utn.capgemini.ecommerce.model.utils.PAGO;
import ar.utn.capgemini.ecommerce.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class EcommerceApplication {

    private static final Logger LOG = LoggerFactory.getLogger(EcommerceApplication.class);

    @Autowired
    public AreaPersonalizacionRepository areaPersonalizacionRepository;
    @Autowired
    public TipoPersonalizacionRepository tipoPersonalizacionRepository;
    @Autowired
    public CategoriaRepository cagoriaRepository;
    @Autowired
    public VendedorRepository vendedorRepository;
    @Autowired
    public MetodoDePagoRepository metodoDePagoRepository;
    @Autowired
    public PersonalizacionConcretaRepository personalizacionConcretaRepository;
    @Autowired
    public ProductoPersonalizadoRepository productoPersonalizadoRepository;
    @Autowired
    public PosiblePersonalizacionRepository posiblePersonalizacionRepository;
    @Autowired
    public ProductoBaseRepository productoBaseRepository;
    @Autowired
    public PublicacionRepository publicacionRepository;
    @Autowired
    public ClienteRepository clienteRepository;
    @Autowired
    public PublicacionCarritoRepository carritoRepository;


    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }


    @Bean
    public CommandLineRunner init() {
        LOG.info("Cargando datos en la base de datos");

        return (args) -> {
            TipoPersonalizacion tipo1 = tipoPersonalizacionRepository.save(new TipoPersonalizacion("Logo UTN", LocalDateTime.now()));
            TipoPersonalizacion tipo2 = tipoPersonalizacionRepository.save(new TipoPersonalizacion("Logo Ferrari", LocalDateTime.now()));
            TipoPersonalizacion tipo3 = tipoPersonalizacionRepository.save(new TipoPersonalizacion("Degrade", LocalDateTime.now()));
            TipoPersonalizacion tipo4 = tipoPersonalizacionRepository.save(new TipoPersonalizacion("Emoji", LocalDateTime.now()));
            TipoPersonalizacion tipo5 = tipoPersonalizacionRepository.save(new TipoPersonalizacion("Texto ABC", LocalDateTime.now()));

            AreaPersonalizacion area1 = areaPersonalizacionRepository.save(new AreaPersonalizacion("Espalda", LocalDateTime.now()));
            AreaPersonalizacion area2 = areaPersonalizacionRepository.save(new AreaPersonalizacion("Dorso", LocalDateTime.now()));
            AreaPersonalizacion area3 = areaPersonalizacionRepository.save(new AreaPersonalizacion("Cuello", LocalDateTime.now()));
            AreaPersonalizacion area4 = areaPersonalizacionRepository.save(new AreaPersonalizacion("Capucha", LocalDateTime.now()));
            AreaPersonalizacion area5 = areaPersonalizacionRepository.save(new AreaPersonalizacion("Piernas", LocalDateTime.now()));

            Categoria categoria1 = cagoriaRepository.save(new Categoria("Remera", LocalDateTime.now()));
            Categoria categoria2 = cagoriaRepository.save(new Categoria("Buzo", LocalDateTime.now()));
            Categoria categoria3 = cagoriaRepository.save(new Categoria("Campera", LocalDateTime.now()));
            Categoria categoria4 = cagoriaRepository.save(new Categoria("Pantalones", LocalDateTime.now()));
            Categoria categoria5 = cagoriaRepository.save(new Categoria("Zapatos", LocalDateTime.now()));

            PosiblePersonalizacion posible1 = posiblePersonalizacionRepository.save(new PosiblePersonalizacion(tipo1, area1, LocalDateTime.now()));
            List<PosiblePersonalizacion> posibles1 = Collections.singletonList(posible1);
            PosiblePersonalizacion posible2 = posiblePersonalizacionRepository.save(new PosiblePersonalizacion(tipo2, area2, LocalDateTime.now()));
            List<PosiblePersonalizacion> posibles2 = Collections.singletonList(posible2);
            PosiblePersonalizacion posible3 = posiblePersonalizacionRepository.save(new PosiblePersonalizacion(tipo3, area3, LocalDateTime.now()));
            List<PosiblePersonalizacion> posibles3 = Collections.singletonList(posible3);
            PosiblePersonalizacion posible4 = posiblePersonalizacionRepository.save(new PosiblePersonalizacion(tipo4, area4, LocalDateTime.now()));
            List<PosiblePersonalizacion> posibles4 = Collections.singletonList(posible4);
            PosiblePersonalizacion posible5 = posiblePersonalizacionRepository.save(new PosiblePersonalizacion(tipo5, area5, LocalDateTime.now()));
            List<PosiblePersonalizacion> posibles5 = Collections.singletonList(posible5);

            ProductoBase base1 = productoBaseRepository.save(new ProductoBase("Remera", BigDecimal.valueOf(1500.55), 3, "url 1", categoria1, LocalDateTime.now()));
            base1.setPosiblesPersonalizaciones(posibles1);
            productoBaseRepository.save(base1);

            ProductoBase base2 = productoBaseRepository.save(new ProductoBase("Pantalon", BigDecimal.valueOf(1500.55), 3, "url 2", categoria2, LocalDateTime.now()));
            base2.setPosiblesPersonalizaciones(posibles2);
            productoBaseRepository.save(base2);

            ProductoBase base3 = productoBaseRepository.save(new ProductoBase("Buzo", BigDecimal.valueOf(1500.55), 3, "url 3", categoria3, LocalDateTime.now()));
            base3.setPosiblesPersonalizaciones(posibles3);
            productoBaseRepository.save(base3);

            ProductoBase base4 = productoBaseRepository.save(new ProductoBase("Campera", BigDecimal.valueOf(1500.55), 3, "url 4", categoria4, LocalDateTime.now()));
            base4.setPosiblesPersonalizaciones(posibles4);
            productoBaseRepository.save(base4);

            ProductoBase base5 = productoBaseRepository.save(new ProductoBase("Zapatos", BigDecimal.valueOf(1500.55), 3, "url 5", categoria5, LocalDateTime.now()));
            base5.setPosiblesPersonalizaciones(posibles5);
            productoBaseRepository.save(base5);

            MetodoDePago metodo1 = metodoDePagoRepository.save(new MetodoDePago(PAGO.EFECTIVO));
            metodo1.setFormaDePago(PAGO.EFECTIVO);
            List<MetodoDePago> metodoPago1 = Collections.singletonList(metodo1);

            MetodoDePago metodo2 = metodoDePagoRepository.save(new MetodoDePago(PAGO.CREDITO_MASTERCARD));
            metodo2.setFormaDePago(PAGO.CREDITO_VISA);
            List<MetodoDePago> metodoPago2 = Collections.singletonList(metodo2);

            MetodoDePago metodo3 = metodoDePagoRepository.save(new MetodoDePago(PAGO.CREDITO_VISA));
            metodo3.setFormaDePago(PAGO.DEBITO_VISA);
            List<MetodoDePago> metodoPago3 = Collections.singletonList(metodo3);

            MetodoDePago metodo4 = metodoDePagoRepository.save(new MetodoDePago(PAGO.DEBITO_VISA));
            metodo4.setFormaDePago(PAGO.DEBITO_MASTERCARD);
            List<MetodoDePago> metodoPago4 = Collections.singletonList(metodo4);

            MetodoDePago metodo5 = metodoDePagoRepository.save(new MetodoDePago(PAGO.DEBITO_MASTERCARD));
            metodo5.setFormaDePago(PAGO.CREDITO_MASTERCARD);
            List<MetodoDePago> metodoPago5 = Collections.singletonList(metodo5);

            Vendedor vendedor1 = vendedorRepository.save(new Vendedor("Nombre 1", "Apellido 1", "Tienda 1", "Email1@gmail.com"));
            vendedor1.setMetodosDePago(metodoPago1);
            vendedorRepository.save(vendedor1);

            Vendedor vendedor2 = vendedorRepository.save(new Vendedor("Nombre 2", "Apellido 2", "Tienda 2", "Email2@gmail.com"));
            vendedor2.setMetodosDePago(metodoPago2);
            vendedorRepository.save(vendedor2);

            Vendedor vendedor3 = vendedorRepository.save(new Vendedor("Nombre 3", "Apellido 3", "Tienda 3", "Email3@gmail.com"));
            vendedor3.setMetodosDePago(metodoPago3);
            vendedorRepository.save(vendedor3);

            Vendedor vendedor4 = vendedorRepository.save(new Vendedor("Nombre 4", "Apellido 4", "Tienda 4", "Email4@gmail.com"));
            vendedor4.setMetodosDePago(metodoPago4);
            vendedorRepository.save(vendedor4);

            Vendedor vendedor5 = vendedorRepository.save(new Vendedor("Nombre 5", "Apellido 5", "Tienda 5", "Email5@gmail.com"));
            vendedor5.setMetodosDePago(metodoPago5);
            vendedorRepository.save(vendedor5);

            PersonalizacionConcreta concreta1 = personalizacionConcretaRepository.save(new PersonalizacionConcreta("Detalle 1", BigDecimal.valueOf(1500.55), posible1, LocalDateTime.now()));
            List<PersonalizacionConcreta> concretas1 = Collections.singletonList(concreta1);

            PersonalizacionConcreta concreta2 = personalizacionConcretaRepository.save(new PersonalizacionConcreta("Detalle 2", BigDecimal.valueOf(1500.55), posible2, LocalDateTime.now()));
            List<PersonalizacionConcreta> concretas2 = Collections.singletonList(concreta2);

            PersonalizacionConcreta concreta3 = personalizacionConcretaRepository.save(new PersonalizacionConcreta("Detalle 3", BigDecimal.valueOf(1500.55), posible3, LocalDateTime.now()));
            List<PersonalizacionConcreta> concretas3 = Collections.singletonList(concreta3);

            PersonalizacionConcreta concreta4 = personalizacionConcretaRepository.save(new PersonalizacionConcreta("Detalle 4", BigDecimal.valueOf(1500.55), posible4, LocalDateTime.now()));
            List<PersonalizacionConcreta> concretas4 = Collections.singletonList(concreta4);

            PersonalizacionConcreta concreta5 = personalizacionConcretaRepository.save(new PersonalizacionConcreta("Detalle 5", BigDecimal.valueOf(1500.55), posible5, LocalDateTime.now()));
            List<PersonalizacionConcreta> concretas5 = Collections.singletonList(concreta5);

            ProductoPersonalizado personalizado1 = productoPersonalizadoRepository.save(new ProductoPersonalizado("url 1", base1, vendedor1, LocalDateTime.now()));
            personalizado1.setPersonalizacionesConcretas(concretas1);
            productoPersonalizadoRepository.save(personalizado1);

            ProductoPersonalizado personalizado2 = productoPersonalizadoRepository.save(new ProductoPersonalizado("url 2", base2, vendedor2, LocalDateTime.now()));
            personalizado2.setPersonalizacionesConcretas(concretas2);
            productoPersonalizadoRepository.save(personalizado2);

            ProductoPersonalizado personalizado3 = productoPersonalizadoRepository.save(new ProductoPersonalizado("url 3", base3, vendedor3, LocalDateTime.now()));
            personalizado3.setPersonalizacionesConcretas(concretas3);
            productoPersonalizadoRepository.save(personalizado3);

            ProductoPersonalizado personalizado4 = productoPersonalizadoRepository.save(new ProductoPersonalizado("url 4", base4, vendedor4, LocalDateTime.now()));
            personalizado4.setPersonalizacionesConcretas(concretas4);
            productoPersonalizadoRepository.save(personalizado4);

            ProductoPersonalizado personalizado5 = productoPersonalizadoRepository.save(new ProductoPersonalizado("url 5", base5, vendedor5, LocalDateTime.now()));
            personalizado5.setPersonalizacionesConcretas(concretas5);
            productoPersonalizadoRepository.save(personalizado5);

            Publicacion publicacion1 = publicacionRepository.save(new Publicacion(ESTADO.ACTIVO, LocalDateTime.now(), personalizado1));
            Publicacion publicacion2 = publicacionRepository.save(new Publicacion(ESTADO.PAUSADO, LocalDateTime.now(), personalizado2));
            Publicacion publicacion3 = publicacionRepository.save(new Publicacion(ESTADO.CANCELADO, LocalDateTime.now(), personalizado3));
            Publicacion publicacion4 = publicacionRepository.save(new Publicacion(ESTADO.ACTIVO, LocalDateTime.now(), personalizado4));
            Publicacion publicacion5 = publicacionRepository.save(new Publicacion(ESTADO.PAUSADO, LocalDateTime.now(), personalizado5));

            Cliente cliente1 = clienteRepository.save(new Cliente("Nombre 1", "Apellido 1", "cliente1@gmail.com", "12345678", "contrasenia1", "Rosario", "402", "4", "B", LocalDateTime.now()));
            Cliente cliente2 = clienteRepository.save(new Cliente("Nombre 2", "Apellido 2", "cliente2@gmail.com", "12345678", "contrasenia2", "Av. Rivadavia", "6900", "3", "3", LocalDateTime.now()));
            Cliente cliente3 = clienteRepository.save(new Cliente("Nombre 3", "Apellido 3", "cliente3@gmail.com", "12345678", "contrasenia3", "Av. San Juan", "1234", "15", "A", LocalDateTime.now()));
            Cliente cliente4 = clienteRepository.save(new Cliente("Nombre 4", "Apellido 4", "cliente4@gmail.com", "12345678", "contrasenia4", "Viamonte", "723", "7", "2", LocalDateTime.now()));
            Cliente cliente5 = clienteRepository.save(new Cliente("Nombre 5", "Apellido 5", "cliente5@gmail.com", "12345678", "contrasenia5", "Uriburur", "865", "1", "C", LocalDateTime.now()));


            PublicacionPorCarrito carrito1 = carritoRepository.save(new PublicacionPorCarrito(5, publicacion1));
            PublicacionPorCarrito carrito2 = carritoRepository.save(new PublicacionPorCarrito(2, publicacion2));
            PublicacionPorCarrito carrito3 = carritoRepository.save(new PublicacionPorCarrito(3, publicacion3));
            PublicacionPorCarrito carrito4 = carritoRepository.save(new PublicacionPorCarrito(4, publicacion4));
            PublicacionPorCarrito carrito5 = carritoRepository.save(new PublicacionPorCarrito(5, publicacion5));
        };
    }
}
