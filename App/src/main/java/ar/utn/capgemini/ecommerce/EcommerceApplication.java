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

import java.util.ArrayList;
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
            TipoPersonalizacion tipo1 = tipoPersonalizacionRepository.save(new TipoPersonalizacion("logo-star-wars"));
            TipoPersonalizacion tipo2 = tipoPersonalizacionRepository.save(new TipoPersonalizacion("logo-ferrari"));
            TipoPersonalizacion tipo3 = tipoPersonalizacionRepository.save(new TipoPersonalizacion("degrade"));
            TipoPersonalizacion tipo4 = tipoPersonalizacionRepository.save(new TipoPersonalizacion("emoji"));

            AreaPersonalizacion area1 = areaPersonalizacionRepository.save(new AreaPersonalizacion("espalda"));
            AreaPersonalizacion area2 = areaPersonalizacionRepository.save(new AreaPersonalizacion("pecho"));
            AreaPersonalizacion area3 = areaPersonalizacionRepository.save(new AreaPersonalizacion("cuello"));
            AreaPersonalizacion area4 = areaPersonalizacionRepository.save(new AreaPersonalizacion("capucha"));

            Categoria categoria1 = cagoriaRepository.save(new Categoria("Remeras"));
            Categoria categoria2 = cagoriaRepository.save(new Categoria("Buzos"));
            Categoria categoria3 = cagoriaRepository.save(new Categoria("Pantalones"));
            Categoria categoria4 = cagoriaRepository.save(new Categoria("Camperas"));


            PosiblePersonalizacion posible1 = posiblePersonalizacionRepository.save(new PosiblePersonalizacion(tipo1, area1));
            List<PosiblePersonalizacion> posibles1 = new ArrayList<>();
            posibles1.add(posible1);

            PosiblePersonalizacion posible2 = posiblePersonalizacionRepository.save(new PosiblePersonalizacion(tipo2, area2));
            List<PosiblePersonalizacion> posibles2 = new ArrayList<>();
            posibles2.add(posible2);

            PosiblePersonalizacion posible3 = posiblePersonalizacionRepository.save(new PosiblePersonalizacion(tipo3, area3));
            List<PosiblePersonalizacion> posibles3 = new ArrayList<>();
            posibles3.add(posible3);

            PosiblePersonalizacion posible4 = posiblePersonalizacionRepository.save(new PosiblePersonalizacion(tipo4, area4));
            List<PosiblePersonalizacion> posibles4 = new ArrayList<>();
            posibles4.add(posible4);

            posibles1.add(posible1);

            ProductoBase base1 = productoBaseRepository.save(new ProductoBase("Remera", 2000.0, 3, "url-1", categoria1));
            base1.setPosiblesPersonalizaciones(posibles1);
            productoBaseRepository.save(base1);

            ProductoBase base2 = productoBaseRepository.save(new ProductoBase("Pantalon", 3000.0, 3, "url-2", categoria2));
            base2.setPosiblesPersonalizaciones(posibles2);
            productoBaseRepository.save(base2);

            ProductoBase base3 = productoBaseRepository.save(new ProductoBase("Buzo", 8000.0, 3, "url-3", categoria3));
            base3.setPosiblesPersonalizaciones(posibles3);
            productoBaseRepository.save(base3);

            ProductoBase base4 = productoBaseRepository.save(new ProductoBase("Campera", 3000.0, 3, "url-4", categoria4));
            base4.setPosiblesPersonalizaciones(posibles4);
            productoBaseRepository.save(base4);


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

            Vendedor vendedor1 = vendedorRepository.save(new Vendedor("Julian", "Alvarez", "Minter", "jalvarez1@gmail.com"));
            vendedor1.setMetodosDePago(metodoPago1);
            vendedorRepository.save(vendedor1);

            Vendedor vendedor2 = vendedorRepository.save(new Vendedor("Camila", "Leone", "Heels", "cleone@gmail.com"));
            vendedor2.setMetodosDePago(metodoPago2);
            vendedorRepository.save(vendedor2);

            Vendedor vendedor3 = vendedorRepository.save(new Vendedor("Martina", "Lopez", "Rankle", "mlopez@gmail.com"));
            vendedor3.setMetodosDePago(metodoPago3);
            vendedorRepository.save(vendedor3);

            Vendedor vendedor4 = vendedorRepository.save(new Vendedor("Mateo", "Agostini-4", "Olokuti", "magostini@gmail.com"));
            vendedor4.setMetodosDePago(metodoPago4);
            vendedorRepository.save(vendedor4);

            Vendedor vendedor5 = vendedorRepository.save(new Vendedor("Martin", "Sanchez", "Semavru", "msanchez@gmail.com"));
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

            publicacionRepository.save(new Publicacion("Remera-1", "Remera-star-wars-1", categoria1, personalizado1, "https://static.wixstatic.com/media/5854b3_09a41c6e92b34befa559011ef3a5a9b1~mv2.jpg/v1/fill/w_890,h_890,al_c,q_85/5854b3_09a41c6e92b34befa559011ef3a5a9b1~mv2.jpg", vendedor1));
            publicacionRepository.save(new Publicacion("Remera-2", "Remera-star-wars-2", categoria1, personalizado2, "https://http2.mlstatic.com/D_NQ_NP_894525-MLA25457281799_032017-W.jpg", vendedor1));
            publicacionRepository.save(new Publicacion("Remera-3", "Remera-bbt-1", categoria1, personalizado3, "http://3.bp.blogspot.com/-Jxs8n50QIJY/UQ_yfliopOI/AAAAAAAAEjY/Kp7P9Zb0JiI/s1600/remeras-color-animal-the-big-bang-tehory-4.png", vendedor2));
            publicacionRepository.save(new Publicacion("Remera-4", "Remera-ferrari-1", categoria1, personalizado4, "https://sporting.vteximg.com.br/arquivos/ids/181885-1500-1500/1076705.jpg?v=636743585146670000", vendedor3));
            publicacionRepository.save(new Publicacion("Remera-5", "Remera-ferrari-2", categoria1, personalizado4, "https://sporting.vteximg.com.br/arquivos/ids/181885-1500-1500/1076705.jpg?v=636743585146670000", vendedor4));

            publicacionRepository.save(new Publicacion("Buzo-1", "Buzo-addidas-1", categoria2, personalizado1, "https://www.digitalsport.com.ar/files/products/5aa985af73cca-437094-1200x1200.jpg", vendedor1));
            publicacionRepository.save(new Publicacion("Buzo-2", "Buzo-addidas-2", categoria2, personalizado2, "https://www.stockcenter.com.ar/on/demandware.static/-/Sites-dabra-catalog/default/dw8767328e/products/AD_GD5477/AD_GD5477-1.JPG", vendedor1));
            publicacionRepository.save(new Publicacion("Buzo-3", "Buzo-nike-1", categoria2, personalizado3, "https://www.moovbydexter.com.ar/on/demandware.static/-/Sites-dabra-catalog/default/dwccadf8c5/products/NI_CK2852-010/NI_CK2852-010-1.JPG", vendedor2));
            publicacionRepository.save(new Publicacion("Buzo-4", "Buzo-nike-2", categoria2, personalizado4, "https://http2.mlstatic.com/buzo-nike-sb-D_NQ_NP_565521-MLA20814538350_072016-F.jpg", vendedor3));
            publicacionRepository.save(new Publicacion("Buzo-5", "Buzo-new-york-1", categoria2, personalizado4, "https://ver.rosario.gob.ar/media/cache/5c/66/5c66c0b1fb6b83d4eb6c685f1061da3b.png", vendedor4));

            publicacionRepository.save(new Publicacion("Campera-1", "Campera-emoji-1", categoria4, personalizado1, "https://cdn.shopify.com/s/files/1/2511/9944/products/front_3_cf99a61a-1f57-4d61-a281-ee39a135db21_2000x.jpg?v=1590896198", vendedor1));
            publicacionRepository.save(new Publicacion("Campera-2", "Campera-emoji-2", categoria4, personalizado2, "https://i.pinimg.com/originals/d6/9d/84/d69d842733d4354209b35bbfe4128611.jpg", vendedor1));
            publicacionRepository.save(new Publicacion("Campera-3", "Campera-seleccion-1", categoria4, personalizado3, "https://essential.vteximg.com.br/arquivos/ids/164064-1000-1000/262-6598_1.jpg?v=636634425722200000", vendedor2));
            publicacionRepository.save(new Publicacion("Campera-4", "Campera-colorida-1", categoria4, personalizado4, "https://2.bp.blogspot.com/_2kWfAkKKNo0/TQIUTWtYW1I/AAAAAAAAAAc/UntCxkv3zSk/S374/0340976B.jpg", vendedor3));
            publicacionRepository.save(new Publicacion("Campera-5", "Campera-colorida-2", categoria4, personalizado4, "https://www.digitalsport.com.ar/files/products/5b7ea81b92aa4-452696-500x500.jpg", vendedor4));

            publicacionRepository.save(new Publicacion("Pantalones-1", "jean-colorido-1", categoria3, personalizado4, "https://ae01.alicdn.com/kf/HTB1E3uvLpXXXXcLXFXXq6xXFXXXc/BKXRH-Colorido-Jean-vaqueros-de-Las-Mujeres-Mujeres-del-Resorte-de-la-Tendencia-Nacional-Bordado-de.jpg", vendedor1));
            publicacionRepository.save(new Publicacion("Pantalones-2", "jean-gastado-1", categoria3, personalizado4, "https://cdn.shopify.com/s/files/1/0290/8559/7780/products/JEANSJV1CELESTE_2048x2048.png?v=1599587339", vendedor1));
            publicacionRepository.save(new Publicacion("Bermuda-1", "bermuda-imagen-1", categoria3, personalizado4, "https://www.alegriademontar.com.br/wp-content/uploads/2020/07/5-12.jpg", vendedor2));
            publicacionRepository.save(new Publicacion("bermuda-2", "bermuda-fotos-1", categoria3, personalizado4, "https://www.alegriademontar.com.br/wp-content/uploads/2020/11/BERMUDA-FEM-PRETO.jpg", vendedor3));

            // creo un cliente para la demostracion del login
            clienteRepository.save(new Cliente("Tomas Martin", "Griffa Benitez", "t@gmail.com", "112345678", "1234", "Calle falsa", "123", "1", "A"));
            clienteRepository.save(new Cliente("Lionel", "Messi", "lm@gmail.com", "99999999", "1234", "Paris", "123", "45", "B"));

        };
    }
}
