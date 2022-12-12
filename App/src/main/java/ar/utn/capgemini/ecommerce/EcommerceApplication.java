package ar.utn.capgemini.ecommerce;

import ar.utn.capgemini.ecommerce.model.*;
import ar.utn.capgemini.ecommerce.utils.PAGO;
import ar.utn.capgemini.ecommerce.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
    public PublicacionCarritoRepository publicacionPorCarritoRepository;
    @Autowired
    public CarritoRepository carritoRepository;


    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }


    @Bean
    public CommandLineRunner init() {
        LOG.info("Cargando datos en la base de datos");

        return (args) -> {
            TipoPersonalizacion tipo1 = tipoPersonalizacionRepository.save(new TipoPersonalizacion("Logo-UTN"));
            TipoPersonalizacion tipo2 = tipoPersonalizacionRepository.save(new TipoPersonalizacion("Logo-Ferrari"));
            TipoPersonalizacion tipo3 = tipoPersonalizacionRepository.save(new TipoPersonalizacion("Degrade"));
            TipoPersonalizacion tipo4 = tipoPersonalizacionRepository.save(new TipoPersonalizacion("Emoji"));
            TipoPersonalizacion tipo5 = tipoPersonalizacionRepository.save(new TipoPersonalizacion("Texto-ABC"));

            AreaPersonalizacion area1 = areaPersonalizacionRepository.save(new AreaPersonalizacion("Espalda"));
            AreaPersonalizacion area2 = areaPersonalizacionRepository.save(new AreaPersonalizacion("Dorso"));
            AreaPersonalizacion area3 = areaPersonalizacionRepository.save(new AreaPersonalizacion("Cuello"));
            AreaPersonalizacion area4 = areaPersonalizacionRepository.save(new AreaPersonalizacion("Capucha"));
            AreaPersonalizacion area5 = areaPersonalizacionRepository.save(new AreaPersonalizacion("Piernas"));

            Categoria categoria1 = cagoriaRepository.save(new Categoria("Remera"));
            Categoria categoria2 = cagoriaRepository.save(new Categoria("Buzo"));
            Categoria categoria3 = cagoriaRepository.save(new Categoria("Campera"));
            Categoria categoria4 = cagoriaRepository.save(new Categoria("Pantalones"));
            Categoria categoria5 = cagoriaRepository.save(new Categoria("Zapatos"));

            PosiblePersonalizacion posible1 = posiblePersonalizacionRepository.save(new PosiblePersonalizacion(tipo1, area1));
            List<PosiblePersonalizacion> posibles1 = Collections.singletonList(posible1);
            PosiblePersonalizacion posible2 = posiblePersonalizacionRepository.save(new PosiblePersonalizacion(tipo2, area2));
            List<PosiblePersonalizacion> posibles2 = Collections.singletonList(posible2);
            PosiblePersonalizacion posible3 = posiblePersonalizacionRepository.save(new PosiblePersonalizacion(tipo3, area3));
            List<PosiblePersonalizacion> posibles3 = Collections.singletonList(posible3);
            PosiblePersonalizacion posible4 = posiblePersonalizacionRepository.save(new PosiblePersonalizacion(tipo4, area4));
            List<PosiblePersonalizacion> posibles4 = Collections.singletonList(posible4);
            PosiblePersonalizacion posible5 = posiblePersonalizacionRepository.save(new PosiblePersonalizacion(tipo5, area5));
            List<PosiblePersonalizacion> posibles5 = Collections.singletonList(posible5);

            ProductoBase base1 = productoBaseRepository.save(new ProductoBase("Remera", 2000.0, 3, "url-1", categoria1));
            base1.setPosiblesPersonalizaciones(posibles1);
            productoBaseRepository.save(base1);

            ProductoBase base2 = productoBaseRepository.save(new ProductoBase("Pantalon", 3000.0, 3, "url-2", categoria2));
            base2.setPosiblesPersonalizaciones(posibles2);
            productoBaseRepository.save(base2);

            ProductoBase base3 = productoBaseRepository.save(new ProductoBase("Buzo", 8000.0, 3, "url-3", categoria3));
            base3.setPosiblesPersonalizaciones(posibles3);
            productoBaseRepository.save(base3);

            ProductoBase base4 = productoBaseRepository.save(new ProductoBase("Campera", 50000.0, 3, "url-4", categoria4));
            base4.setPosiblesPersonalizaciones(posibles4);
            productoBaseRepository.save(base4);

            ProductoBase base5 = productoBaseRepository.save(new ProductoBase("Zapatos", 15000.0, 3, "url-5", categoria5));
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

            Vendedor vendedor1 = vendedorRepository.save(new Vendedor("Nombre-1", "Apellido-1", "Tienda-1", "Email1@gmail.com"));
            vendedor1.setMetodosDePago(metodoPago1);
            vendedorRepository.save(vendedor1);

            Vendedor vendedor2 = vendedorRepository.save(new Vendedor("Nombre-2", "Apellido-2", "Tienda-2", "Email2@gmail.com"));
            vendedor2.setMetodosDePago(metodoPago2);
            vendedorRepository.save(vendedor2);

            Vendedor vendedor3 = vendedorRepository.save(new Vendedor("Nombre-3", "Apellido-3", "Tienda-3", "Email3@gmail.com"));
            vendedor3.setMetodosDePago(metodoPago3);
            vendedorRepository.save(vendedor3);

            Vendedor vendedor4 = vendedorRepository.save(new Vendedor("Nombre-4", "Apellido-4", "Tienda-4", "Email4@gmail.com"));
            vendedor4.setMetodosDePago(metodoPago4);
            vendedorRepository.save(vendedor4);

            Vendedor vendedor5 = vendedorRepository.save(new Vendedor("Nombre-5", "Apellido-5", "Tienda-5", "Email5@gmail.com"));
            vendedor5.setMetodosDePago(metodoPago5);
            vendedorRepository.save(vendedor5);

            PersonalizacionConcreta concreta1 = personalizacionConcretaRepository.save(new PersonalizacionConcreta("Detalle 1", 2000.0, posible1));
            List<PersonalizacionConcreta> concretas1 = Collections.singletonList(concreta1);

            PersonalizacionConcreta concreta2 = personalizacionConcretaRepository.save(new PersonalizacionConcreta("Detalle 2", 2000.0, posible2));
            List<PersonalizacionConcreta> concretas2 = Collections.singletonList(concreta2);

            PersonalizacionConcreta concreta3 = personalizacionConcretaRepository.save(new PersonalizacionConcreta("Detalle 3", 3000.0, posible3));
            List<PersonalizacionConcreta> concretas3 = Collections.singletonList(concreta3);

            PersonalizacionConcreta concreta4 = personalizacionConcretaRepository.save(new PersonalizacionConcreta("Detalle 4", 1500.0, posible4));
            List<PersonalizacionConcreta> concretas4 = Collections.singletonList(concreta4);

            PersonalizacionConcreta concreta5 = personalizacionConcretaRepository.save(new PersonalizacionConcreta("Detalle 5", 2500.0, posible5));
            List<PersonalizacionConcreta> concretas5 = Collections.singletonList(concreta5);

            ProductoPersonalizado personalizado1 = productoPersonalizadoRepository.save(new ProductoPersonalizado("url-1", base1, vendedor1));
            personalizado1.setPersonalizacionesConcretas(concretas1);
            productoPersonalizadoRepository.save(personalizado1);

            ProductoPersonalizado personalizado2 = productoPersonalizadoRepository.save(new ProductoPersonalizado("url-2", base2, vendedor2));
            personalizado2.setPersonalizacionesConcretas(concretas2);
            productoPersonalizadoRepository.save(personalizado2);

            ProductoPersonalizado personalizado3 = productoPersonalizadoRepository.save(new ProductoPersonalizado("url-3", base3, vendedor3));
            personalizado3.setPersonalizacionesConcretas(concretas3);
            productoPersonalizadoRepository.save(personalizado3);

            ProductoPersonalizado personalizado4 = productoPersonalizadoRepository.save(new ProductoPersonalizado("url-4", base4, vendedor4));
            personalizado4.setPersonalizacionesConcretas(concretas4);
            productoPersonalizadoRepository.save(personalizado4);

            ProductoPersonalizado personalizado5 = productoPersonalizadoRepository.save(new ProductoPersonalizado("url-5", base5, vendedor5));
            personalizado5.setPersonalizacionesConcretas(concretas5);
            productoPersonalizadoRepository.save(personalizado5);

            publicacionRepository.save(new Publicacion("Titulo-1", "Descripcion-1", personalizado1, vendedor1));
            publicacionRepository.save(new Publicacion("Titulo-2", "Descripcion-2", personalizado2, vendedor2));
            publicacionRepository.save(new Publicacion("Titulo-3", "Descripcion-3", personalizado3, vendedor3));
            publicacionRepository.save(new Publicacion("Titulo-4", "Descripcion-4", personalizado4, vendedor4));
            publicacionRepository.save(new Publicacion("Titulo-5", "Descripcion-5", personalizado5, vendedor5));
        };
    }
}
