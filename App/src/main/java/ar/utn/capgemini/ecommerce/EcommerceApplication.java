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


    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }


    @Bean
    public CommandLineRunner init() {
        LOG.info("Cargando datos en la base de datos");

        return (args) -> {

            TipoPersonalizacion tipoPersonalizacion1 = new TipoPersonalizacion("Imagen");
            TipoPersonalizacion tipoPersonalizacion2 = new TipoPersonalizacion("Texto");
            TipoPersonalizacion tipoPersonalizacion3 = new TipoPersonalizacion("Emoji");

            tipoPersonalizacionRepository.save(tipoPersonalizacion1);
            tipoPersonalizacionRepository.save(tipoPersonalizacion2);
            tipoPersonalizacionRepository.save(tipoPersonalizacion3);

            AreaPersonalizacion areaPersonalizacion1 = new AreaPersonalizacion("Frente");
            AreaPersonalizacion areaPersonalizacion2 = new AreaPersonalizacion("Espalda");
            AreaPersonalizacion areaPersonalizacion3 = new AreaPersonalizacion("Pierna");

            areaPersonalizacionRepository.save(areaPersonalizacion1);
            areaPersonalizacionRepository.save(areaPersonalizacion2);
            areaPersonalizacionRepository.save(areaPersonalizacion3);

            Categoria categoria1 = new Categoria("Remeras");
            Categoria categoria2 = new Categoria("Camperas");
            Categoria categoria3 = new Categoria("Buzos");
            Categoria categoria4 = new Categoria("Pantalones");

            cagoriaRepository.save(categoria1);
            cagoriaRepository.save(categoria2);
            cagoriaRepository.save(categoria3);
            cagoriaRepository.save(categoria4);

            PosiblePersonalizacion imagenFrente1 = new PosiblePersonalizacion(tipoPersonalizacion1, areaPersonalizacion1); // imagen - frente
            PosiblePersonalizacion imagenFrente2 = new PosiblePersonalizacion(tipoPersonalizacion1, areaPersonalizacion1); // imagen - frente
            PosiblePersonalizacion imagenFrente3 = new PosiblePersonalizacion(tipoPersonalizacion1, areaPersonalizacion1); // imagen - frente

            posiblePersonalizacionRepository.save(imagenFrente1);
            posiblePersonalizacionRepository.save(imagenFrente2);
            posiblePersonalizacionRepository.save(imagenFrente3);

            PosiblePersonalizacion imagenEspalda1 = new PosiblePersonalizacion(tipoPersonalizacion1, areaPersonalizacion2); // imagen - espalda
            PosiblePersonalizacion imagenEspalda2 = new PosiblePersonalizacion(tipoPersonalizacion1, areaPersonalizacion2); // imagen - espalda
            PosiblePersonalizacion imagenEspalda3 = new PosiblePersonalizacion(tipoPersonalizacion1, areaPersonalizacion2); // imagen - espalda

            posiblePersonalizacionRepository.save(imagenEspalda1);
            posiblePersonalizacionRepository.save(imagenEspalda2);
            posiblePersonalizacionRepository.save(imagenEspalda3);

            PosiblePersonalizacion textoFrente1 = new PosiblePersonalizacion(tipoPersonalizacion2, areaPersonalizacion1); // texto - frente
            PosiblePersonalizacion textoFrente2 = new PosiblePersonalizacion(tipoPersonalizacion2, areaPersonalizacion1); // texto - frente
            PosiblePersonalizacion textoFrente3 = new PosiblePersonalizacion(tipoPersonalizacion2, areaPersonalizacion1); // texto - frente

            posiblePersonalizacionRepository.save(textoFrente1);
            posiblePersonalizacionRepository.save(textoFrente2);
            posiblePersonalizacionRepository.save(textoFrente3);

            PosiblePersonalizacion textoEspalda1 = new PosiblePersonalizacion(tipoPersonalizacion2, areaPersonalizacion2); // texto - espalda
            PosiblePersonalizacion textoEspalda2 = new PosiblePersonalizacion(tipoPersonalizacion2, areaPersonalizacion2); // texto - espalda
            PosiblePersonalizacion textoEspalda3 = new PosiblePersonalizacion(tipoPersonalizacion2, areaPersonalizacion2); // texto - espalda

            posiblePersonalizacionRepository.save(textoEspalda1);
            posiblePersonalizacionRepository.save(textoEspalda2);
            posiblePersonalizacionRepository.save(textoEspalda3);

            PosiblePersonalizacion emojiFrente1 = new PosiblePersonalizacion(tipoPersonalizacion3, areaPersonalizacion1); // emoji - frente
            PosiblePersonalizacion emojiFrente2 = new PosiblePersonalizacion(tipoPersonalizacion3, areaPersonalizacion1); // emoji - frente
            PosiblePersonalizacion emojiFrente3 = new PosiblePersonalizacion(tipoPersonalizacion3, areaPersonalizacion1); // emoji - frente

            posiblePersonalizacionRepository.save(emojiFrente1);
            posiblePersonalizacionRepository.save(emojiFrente2);
            posiblePersonalizacionRepository.save(emojiFrente3);

            PosiblePersonalizacion emojiEspalda1 = new PosiblePersonalizacion(tipoPersonalizacion3, areaPersonalizacion2); // emoji - espalda
            PosiblePersonalizacion emojiEspalda2 = new PosiblePersonalizacion(tipoPersonalizacion3, areaPersonalizacion2); // emoji - espalda
            PosiblePersonalizacion emojiEspalda3 = new PosiblePersonalizacion(tipoPersonalizacion3, areaPersonalizacion2); // emoji - espalda

            posiblePersonalizacionRepository.save(emojiEspalda1);
            posiblePersonalizacionRepository.save(emojiEspalda2);
            posiblePersonalizacionRepository.save(emojiEspalda3);

            PosiblePersonalizacion emojiPierna1 = new PosiblePersonalizacion(tipoPersonalizacion3, areaPersonalizacion3); // emoji - pierna
            posiblePersonalizacionRepository.save(emojiPierna1);

            ProductoBase productoBase1 = new ProductoBase("Remera", 2000.0, 3, "url-1", categoria1);
            productoBase1.agregarPosiblePersonalizacion(imagenFrente1);
            productoBase1.agregarPosiblePersonalizacion(imagenEspalda1);
            productoBase1.agregarPosiblePersonalizacion(textoFrente1);
            productoBase1.agregarPosiblePersonalizacion(textoEspalda1);
            productoBase1.agregarPosiblePersonalizacion(emojiFrente1);
            productoBase1.agregarPosiblePersonalizacion(emojiEspalda1);
            productoBaseRepository.save(productoBase1);

            ProductoBase productoBase2 = new ProductoBase("Campera", 3000.0, 2, "url-2", categoria2);
            productoBase2.agregarPosiblePersonalizacion(imagenFrente2);
            productoBase2.agregarPosiblePersonalizacion(imagenEspalda2);
            productoBase2.agregarPosiblePersonalizacion(textoFrente2);
            productoBase2.agregarPosiblePersonalizacion(textoEspalda2);
            productoBase2.agregarPosiblePersonalizacion(emojiFrente2);
            productoBase2.agregarPosiblePersonalizacion(emojiEspalda2);
            productoBaseRepository.save(productoBase2);

            ProductoBase productoBase3 = new ProductoBase("Buzo", 2500.0, 5, "url-3", categoria3);
            productoBase3.agregarPosiblePersonalizacion(imagenFrente3);
            productoBase3.agregarPosiblePersonalizacion(imagenEspalda3);
            productoBase3.agregarPosiblePersonalizacion(textoFrente3);
            productoBase3.agregarPosiblePersonalizacion(textoEspalda3);
            productoBase3.agregarPosiblePersonalizacion(emojiFrente3);
            productoBase3.agregarPosiblePersonalizacion(emojiEspalda3);
            productoBaseRepository.save(productoBase3);

            ProductoBase productoBase4 = new ProductoBase("Pantalon", 1500.0, 10, "url-4", categoria4);
            productoBase4.agregarPosiblePersonalizacion(emojiPierna1);
            productoBaseRepository.save(productoBase4);

            MetodoDePago metodoDePago1 = new MetodoDePago(PAGO.EFECTIVO);
            MetodoDePago metodoDePago2 = new MetodoDePago(PAGO.CREDITO_VISA);
            MetodoDePago metodoDePago3 = new MetodoDePago(PAGO.CREDITO_MASTERCARD);
            MetodoDePago metodoDePago4 = new MetodoDePago(PAGO.DEBITO_VISA);
            MetodoDePago metodoDePago5 = new MetodoDePago(PAGO.DEBITO_MASTERCARD);

            metodoDePagoRepository.save(metodoDePago1);
            metodoDePagoRepository.save(metodoDePago2);
            metodoDePagoRepository.save(metodoDePago3);
            metodoDePagoRepository.save(metodoDePago4);
            metodoDePagoRepository.save(metodoDePago5);

            Vendedor vendedor1 = new Vendedor("Julian", "Alvarez", "Minter", "jalvarez1@gmail.com");
            vendedor1.agregarMetodoDePago(metodoDePago1);
            vendedor1.agregarMetodoDePago(metodoDePago2);
            vendedor1.agregarMetodoDePago(metodoDePago3);
            vendedor1.agregarMetodoDePago(metodoDePago4);
            vendedor1.agregarMetodoDePago(metodoDePago5);
            vendedorRepository.save(vendedor1);

            Vendedor vendedor2 = new Vendedor("Camila", "Leone", "Heels", "cleone@gmail.com");
            vendedor2.agregarMetodoDePago(metodoDePago1);
            vendedor2.agregarMetodoDePago(metodoDePago2);
            vendedor2.agregarMetodoDePago(metodoDePago4);
            vendedorRepository.save(vendedor2);

            Vendedor vendedor3 = new Vendedor("Martina", "Lopez", "Rankle", "mlopez@gmail.com");
            vendedor3.agregarMetodoDePago(metodoDePago1);
            vendedor3.agregarMetodoDePago(metodoDePago3);
            vendedor3.agregarMetodoDePago(metodoDePago4);
            vendedorRepository.save(vendedor3);

            Vendedor vendedor4 = new Vendedor("Mateo", "Agostini-4", "Olokuti", "magostini@gmail.com");
            vendedor4.agregarMetodoDePago(metodoDePago1);
            vendedor4.agregarMetodoDePago(metodoDePago5);
            vendedorRepository.save(vendedor4);

            Vendedor vendedor5 = new Vendedor("Martin", "Sanchez", "Semavru", "msanchez@gmail.com");
            vendedor5.agregarMetodoDePago(metodoDePago2);
            vendedor5.agregarMetodoDePago(metodoDePago5);
            vendedorRepository.save(vendedor5);

            PersonalizacionConcreta remeraImagenFrente1 = new PersonalizacionConcreta("Remera-imagen-frente-1", 2000.0, imagenFrente1);
            PersonalizacionConcreta remeraImagenFrente2 = new PersonalizacionConcreta("Remera-imagen-frente-2", 2000.0, imagenFrente2);
            PersonalizacionConcreta remeraImagenFrente3 = new PersonalizacionConcreta("Remera-imagen-frente-3", 2000.0, imagenFrente3);

            personalizacionConcretaRepository.save(remeraImagenFrente1);
            personalizacionConcretaRepository.save(remeraImagenFrente2);
            personalizacionConcretaRepository.save(remeraImagenFrente3);

            PersonalizacionConcreta remeraImagenEspalda1 = new PersonalizacionConcreta("Remera-imagen-espalda-1", 2000.0, imagenEspalda1);
            PersonalizacionConcreta remeraImagenEspalda2 = new PersonalizacionConcreta("Remera-imagen-espalda-2", 2000.0, imagenEspalda2);
            PersonalizacionConcreta remeraImagenEspalda3 = new PersonalizacionConcreta("Remera-imagen-espalda-3", 2000.0, imagenEspalda3);

            personalizacionConcretaRepository.save(remeraImagenEspalda1);
            personalizacionConcretaRepository.save(remeraImagenEspalda2);
            personalizacionConcretaRepository.save(remeraImagenEspalda3);

            PersonalizacionConcreta remeraTextoFrente1 = new PersonalizacionConcreta("Remera-text-frente-1", 2000.0, textoFrente1);
            PersonalizacionConcreta remeraTextoFrente2 = new PersonalizacionConcreta("Remera-text-frente-2", 2000.0, textoFrente2);
            PersonalizacionConcreta remeraTextoFrente3 = new PersonalizacionConcreta("Remera-text-frente-3", 2000.0, textoFrente3);

            personalizacionConcretaRepository.save(remeraTextoFrente1);
            personalizacionConcretaRepository.save(remeraTextoFrente2);
            personalizacionConcretaRepository.save(remeraTextoFrente3);

            PersonalizacionConcreta remeraTextoEspalda1 = new PersonalizacionConcreta("Remera-text-espalda-1", 2000.0, textoEspalda1);
            PersonalizacionConcreta remeraTextoEspalda2 = new PersonalizacionConcreta("Remera-text-espalda-2", 2000.0, textoEspalda2);
            PersonalizacionConcreta remeraTextoEspalda3 = new PersonalizacionConcreta("Remera-text-espalda-3", 2000.0, textoEspalda3);

            personalizacionConcretaRepository.save(remeraTextoEspalda1);
            personalizacionConcretaRepository.save(remeraTextoEspalda2);
            personalizacionConcretaRepository.save(remeraTextoEspalda3);

            PersonalizacionConcreta remeraEmojiFrente1 = new PersonalizacionConcreta("Remera-emoji-frente-1", 2000.0, emojiFrente1);
            PersonalizacionConcreta remeraEmojiFrente2 = new PersonalizacionConcreta("Remera-emoji-frente-2", 2000.0, emojiFrente2);
            PersonalizacionConcreta remeraEmojiFrente3 = new PersonalizacionConcreta("Remera-emoji-frente-3", 2000.0, emojiFrente3);

            personalizacionConcretaRepository.save(remeraEmojiFrente1);
            personalizacionConcretaRepository.save(remeraEmojiFrente2);
            personalizacionConcretaRepository.save(remeraEmojiFrente3);

            PersonalizacionConcreta remeraEmojiEspalda1 = new PersonalizacionConcreta("Remera-emoji-espalda-1", 2000.0, emojiEspalda1);
            PersonalizacionConcreta remeraEmojiEspalda2 = new PersonalizacionConcreta("Remera-emoji-espalda-2", 2000.0, emojiEspalda2);
            PersonalizacionConcreta remeraEmojiEspalda3 = new PersonalizacionConcreta("Remera-emoji-espalda-3", 2000.0, emojiEspalda3);

            personalizacionConcretaRepository.save(remeraEmojiEspalda1);
            personalizacionConcretaRepository.save(remeraEmojiEspalda2);
            personalizacionConcretaRepository.save(remeraEmojiEspalda3);

            PersonalizacionConcreta camperaImagenFrente1 = new PersonalizacionConcreta("Campera-imagen-frente-1", 2000.0, imagenFrente1);
            PersonalizacionConcreta camperaImagenFrente2 = new PersonalizacionConcreta("Campera-imagen-frente-2", 2000.0, imagenFrente2);
            PersonalizacionConcreta camperaImagenFrente3 = new PersonalizacionConcreta("Campera-imagen-frente-3", 2000.0, imagenFrente3);

            personalizacionConcretaRepository.save(camperaImagenFrente1);
            personalizacionConcretaRepository.save(camperaImagenFrente2);
            personalizacionConcretaRepository.save(camperaImagenFrente3);

            PersonalizacionConcreta camperaImagenEspalda1 = new PersonalizacionConcreta("Campera-imagen-espalda-1", 2000.0, imagenEspalda1);
            PersonalizacionConcreta camperaImagenEspalda2 = new PersonalizacionConcreta("Campera-imagen-espalda-2", 2000.0, imagenEspalda2);
            PersonalizacionConcreta camperaImagenEspalda3 = new PersonalizacionConcreta("Campera-imagen-espalda-3", 2000.0, imagenEspalda3);

            personalizacionConcretaRepository.save(camperaImagenEspalda1);
            personalizacionConcretaRepository.save(camperaImagenEspalda2);
            personalizacionConcretaRepository.save(camperaImagenEspalda3);

            PersonalizacionConcreta camperaTextoFrente1 = new PersonalizacionConcreta("Campera-texto-frente-1", 2000.0, textoFrente1);
            PersonalizacionConcreta camperaTextoFrente2 = new PersonalizacionConcreta("Campera-texto-frente-2", 2000.0, textoFrente2);
            PersonalizacionConcreta camperaTextoFrente3 = new PersonalizacionConcreta("Campera-texto-frente-3", 2000.0, textoFrente3);

            personalizacionConcretaRepository.save(camperaTextoFrente1);
            personalizacionConcretaRepository.save(camperaTextoFrente2);
            personalizacionConcretaRepository.save(camperaTextoFrente3);

            PersonalizacionConcreta camperaTextoEspalda1 = new PersonalizacionConcreta("Campera-texto-espalda-1", 2000.0, textoEspalda1);
            PersonalizacionConcreta camperaTextoEspalda2 = new PersonalizacionConcreta("Campera-texto-espalda-2", 2000.0, textoEspalda2);
            PersonalizacionConcreta camperaTextoEspalda3 = new PersonalizacionConcreta("Campera-texto-espalda-3", 2000.0, textoEspalda3);

            personalizacionConcretaRepository.save(camperaTextoEspalda1);
            personalizacionConcretaRepository.save(camperaTextoEspalda2);
            personalizacionConcretaRepository.save(camperaTextoEspalda3);

            PersonalizacionConcreta camperaEmojiFrente1 = new PersonalizacionConcreta("Campera-emoji-frente-1", 2000.0, emojiFrente1);
            PersonalizacionConcreta camperaEmojiFrente2 = new PersonalizacionConcreta("Campera-emoji-frente-2", 2000.0, emojiFrente2);
            PersonalizacionConcreta camperaEmojiFrente3 = new PersonalizacionConcreta("Campera-emoji-frente-3", 2000.0, emojiFrente3);

            personalizacionConcretaRepository.save(camperaEmojiFrente1);
            personalizacionConcretaRepository.save(camperaEmojiFrente2);
            personalizacionConcretaRepository.save(camperaEmojiFrente3);

            PersonalizacionConcreta camperaEmojiEspalda1 = new PersonalizacionConcreta("Campera-emoji-espalda-1", 2000.0, emojiEspalda1);
            PersonalizacionConcreta camperaEmojiEspalda2 = new PersonalizacionConcreta("Campera-emoji-espalda-2", 2000.0, emojiEspalda2);
            PersonalizacionConcreta camperaEmojiEspalda3 = new PersonalizacionConcreta("Campera-emoji-espalda-3", 2000.0, emojiEspalda3);

            personalizacionConcretaRepository.save(camperaEmojiEspalda1);
            personalizacionConcretaRepository.save(camperaEmojiEspalda2);
            personalizacionConcretaRepository.save(camperaEmojiEspalda3);

            PersonalizacionConcreta buzoImagenFrente1 = new PersonalizacionConcreta("Buzo-imagen-frente-1", 2000.0, imagenFrente1);
            PersonalizacionConcreta buzoImagenFrente2 = new PersonalizacionConcreta("Buzo-imagen-frente-2", 2000.0, imagenFrente2);
            PersonalizacionConcreta buzoImagenFrente3 = new PersonalizacionConcreta("Buzo-imagen-frente-3", 2000.0, imagenFrente3);

            personalizacionConcretaRepository.save(buzoImagenFrente1);
            personalizacionConcretaRepository.save(buzoImagenFrente2);
            personalizacionConcretaRepository.save(buzoImagenFrente3);

            PersonalizacionConcreta buzoImagenEspalda1 = new PersonalizacionConcreta("Buzo-imagen-espalda-1", 2000.0, imagenEspalda1);
            PersonalizacionConcreta buzoImagenEspalda2 = new PersonalizacionConcreta("Buzo-imagen-espalda-2", 2000.0, imagenEspalda2);
            PersonalizacionConcreta buzoImagenEspalda3 = new PersonalizacionConcreta("Buzo-imagen-espalda-3", 2000.0, imagenEspalda3);

            personalizacionConcretaRepository.save(buzoImagenEspalda1);
            personalizacionConcretaRepository.save(buzoImagenEspalda2);
            personalizacionConcretaRepository.save(buzoImagenEspalda3);

            PersonalizacionConcreta buzoTextoFrente1 = new PersonalizacionConcreta("Buzo-texto-frente-1", 2000.0, textoFrente1);
            PersonalizacionConcreta buzoTextoFrente2 = new PersonalizacionConcreta("Buzo-texto-frente-2", 2000.0, textoFrente2);
            PersonalizacionConcreta buzoTextoFrente3 = new PersonalizacionConcreta("Buzo-texto-frente-3", 2000.0, textoFrente3);

            personalizacionConcretaRepository.save(buzoTextoFrente1);
            personalizacionConcretaRepository.save(buzoTextoFrente2);
            personalizacionConcretaRepository.save(buzoTextoFrente3);

            PersonalizacionConcreta buzoTextoEspalda1 = new PersonalizacionConcreta("Buzo-texto-espalda-1", 2000.0, textoEspalda1);
            PersonalizacionConcreta buzoTextoEspalda2 = new PersonalizacionConcreta("Buzo-texto-espalda-2", 2000.0, textoEspalda2);
            PersonalizacionConcreta buzoTextoEspalda3 = new PersonalizacionConcreta("Buzo-texto-espalda-3", 2000.0, textoEspalda3);

            personalizacionConcretaRepository.save(buzoTextoEspalda1);
            personalizacionConcretaRepository.save(buzoTextoEspalda2);
            personalizacionConcretaRepository.save(buzoTextoEspalda3);

            PersonalizacionConcreta buzoEmojiFrente1 = new PersonalizacionConcreta("Buzo-emoji-frente-1", 2000.0, emojiFrente1);
            PersonalizacionConcreta buzoEmojiFrente2 = new PersonalizacionConcreta("Buzo-emoji-frente-2", 2000.0, emojiFrente2);
            PersonalizacionConcreta buzoEmojiFrente3 = new PersonalizacionConcreta("Buzo-emoji-frente-3", 2000.0, emojiFrente3);

            personalizacionConcretaRepository.save(buzoEmojiFrente1);
            personalizacionConcretaRepository.save(buzoEmojiFrente2);
            personalizacionConcretaRepository.save(buzoEmojiFrente3);

            PersonalizacionConcreta buzoEmojiEspalda1 = new PersonalizacionConcreta("Buzo-emoji-espalda-1", 2000.0, emojiEspalda1);
            PersonalizacionConcreta buzoEmojiEspalda2 = new PersonalizacionConcreta("Buzo-emoji-espalda-2", 2000.0, emojiEspalda2);
            PersonalizacionConcreta buzoEmojiEspalda3 = new PersonalizacionConcreta("Buzo-emoji-espalda-3", 2000.0, emojiEspalda3);

            personalizacionConcretaRepository.save(buzoEmojiEspalda1);
            personalizacionConcretaRepository.save(buzoEmojiEspalda2);
            personalizacionConcretaRepository.save(buzoEmojiEspalda3);

            PersonalizacionConcreta pantalonEmoji1 = new PersonalizacionConcreta("Pantalon-emoji-1", 2000.0, emojiPierna1);
            personalizacionConcretaRepository.save(pantalonEmoji1);

            ProductoPersonalizado productoPersonalizado1 = new ProductoPersonalizado("url-1", productoBase1, vendedor1);
            productoPersonalizado1.agregarPersonalizacionConcreta(remeraImagenFrente1);
            productoPersonalizado1.agregarPersonalizacionConcreta(remeraImagenFrente2);
            productoPersonalizado1.agregarPersonalizacionConcreta(remeraImagenFrente3);
            productoPersonalizado1.agregarPersonalizacionConcreta(remeraImagenEspalda1);
            productoPersonalizado1.agregarPersonalizacionConcreta(remeraImagenEspalda2);
            productoPersonalizado1.agregarPersonalizacionConcreta(remeraImagenEspalda3);
            productoPersonalizado1.agregarPersonalizacionConcreta(remeraTextoFrente1);
            productoPersonalizado1.agregarPersonalizacionConcreta(remeraTextoFrente2);
            productoPersonalizado1.agregarPersonalizacionConcreta(remeraTextoFrente3);
            productoPersonalizado1.agregarPersonalizacionConcreta(remeraTextoEspalda1);
            productoPersonalizado1.agregarPersonalizacionConcreta(remeraTextoEspalda2);
            productoPersonalizado1.agregarPersonalizacionConcreta(remeraTextoEspalda3);
            productoPersonalizado1.agregarPersonalizacionConcreta(remeraEmojiFrente1);
            productoPersonalizado1.agregarPersonalizacionConcreta(remeraEmojiFrente2);
            productoPersonalizado1.agregarPersonalizacionConcreta(remeraEmojiFrente3);
            productoPersonalizado1.agregarPersonalizacionConcreta(remeraEmojiEspalda1);
            productoPersonalizado1.agregarPersonalizacionConcreta(remeraEmojiEspalda2);
            productoPersonalizado1.agregarPersonalizacionConcreta(remeraEmojiEspalda3);
            productoPersonalizadoRepository.save(productoPersonalizado1);

            ProductoPersonalizado productoPersonalizado2 = new ProductoPersonalizado("url-2", productoBase2, vendedor2);
            productoPersonalizado2.agregarPersonalizacionConcreta(camperaImagenFrente1);
            productoPersonalizado2.agregarPersonalizacionConcreta(camperaImagenFrente2);
            productoPersonalizado2.agregarPersonalizacionConcreta(camperaImagenFrente3);
            productoPersonalizado2.agregarPersonalizacionConcreta(camperaImagenEspalda1);
            productoPersonalizado2.agregarPersonalizacionConcreta(camperaImagenEspalda2);
            productoPersonalizado2.agregarPersonalizacionConcreta(camperaImagenEspalda3);
            productoPersonalizado2.agregarPersonalizacionConcreta(camperaTextoFrente1);
            productoPersonalizado2.agregarPersonalizacionConcreta(camperaTextoFrente2);
            productoPersonalizado2.agregarPersonalizacionConcreta(camperaTextoFrente3);
            productoPersonalizado2.agregarPersonalizacionConcreta(camperaTextoEspalda1);
            productoPersonalizado2.agregarPersonalizacionConcreta(camperaTextoEspalda2);
            productoPersonalizado2.agregarPersonalizacionConcreta(camperaTextoEspalda3);
            productoPersonalizado2.agregarPersonalizacionConcreta(camperaEmojiFrente1);
            productoPersonalizado2.agregarPersonalizacionConcreta(camperaEmojiFrente2);
            productoPersonalizado2.agregarPersonalizacionConcreta(camperaEmojiFrente3);
            productoPersonalizado2.agregarPersonalizacionConcreta(camperaEmojiEspalda1);
            productoPersonalizado2.agregarPersonalizacionConcreta(camperaEmojiEspalda2);
            productoPersonalizado2.agregarPersonalizacionConcreta(camperaEmojiEspalda3);
            productoPersonalizadoRepository.save(productoPersonalizado2);

            ProductoPersonalizado productoPersonalizado3 = new ProductoPersonalizado("url-3", productoBase3, vendedor3);
            productoPersonalizado3.agregarPersonalizacionConcreta(buzoImagenFrente1);
            productoPersonalizado3.agregarPersonalizacionConcreta(buzoImagenFrente2);
            productoPersonalizado3.agregarPersonalizacionConcreta(buzoImagenFrente3);
            productoPersonalizado3.agregarPersonalizacionConcreta(buzoImagenEspalda1);
            productoPersonalizado3.agregarPersonalizacionConcreta(buzoImagenEspalda2);
            productoPersonalizado3.agregarPersonalizacionConcreta(buzoImagenEspalda3);
            productoPersonalizado3.agregarPersonalizacionConcreta(buzoTextoFrente1);
            productoPersonalizado3.agregarPersonalizacionConcreta(buzoTextoFrente2);
            productoPersonalizado3.agregarPersonalizacionConcreta(buzoTextoFrente3);
            productoPersonalizado3.agregarPersonalizacionConcreta(buzoTextoEspalda1);
            productoPersonalizado3.agregarPersonalizacionConcreta(buzoTextoEspalda2);
            productoPersonalizado3.agregarPersonalizacionConcreta(buzoTextoEspalda3);
            productoPersonalizado3.agregarPersonalizacionConcreta(buzoEmojiFrente1);
            productoPersonalizado3.agregarPersonalizacionConcreta(buzoEmojiFrente2);
            productoPersonalizado3.agregarPersonalizacionConcreta(buzoEmojiFrente3);
            productoPersonalizado3.agregarPersonalizacionConcreta(buzoEmojiEspalda1);
            productoPersonalizado3.agregarPersonalizacionConcreta(buzoEmojiEspalda2);
            productoPersonalizado3.agregarPersonalizacionConcreta(buzoEmojiEspalda3);
            productoPersonalizadoRepository.save(productoPersonalizado3);

            ProductoPersonalizado productoPersonalizado4 = new ProductoPersonalizado("url-4", productoBase4, vendedor4);
            productoPersonalizado4.agregarPersonalizacionConcreta(pantalonEmoji1);
            productoPersonalizadoRepository.save(productoPersonalizado4);

            Publicacion publicacion1 = new Publicacion("Remera-1", "Remera-star-wars-1", categoria1, productoPersonalizado1, "https://static.wixstatic.com/media/5854b3_09a41c6e92b34befa559011ef3a5a9b1~mv2.jpg/v1/fill/w_890,h_890,al_c,q_85/5854b3_09a41c6e92b34befa559011ef3a5a9b1~mv2.jpg", vendedor1);
            Publicacion publicacion2 = new Publicacion("Remera-2", "Remera-star-wars-2", categoria1, productoPersonalizado1, "https://http2.mlstatic.com/D_NQ_NP_894525-MLA25457281799_032017-W.jpg", vendedor1);
            Publicacion publicacion3 = new Publicacion("Remera-3", "Remera-bbt-1", categoria1, productoPersonalizado1, "http://3.bp.blogspot.com/-Jxs8n50QIJY/UQ_yfliopOI/AAAAAAAAEjY/Kp7P9Zb0JiI/s1600/remeras-color-animal-the-big-bang-tehory-4.png", vendedor2);
            Publicacion publicacion4 = new Publicacion("Remera-4", "Remera-ferrari-1", categoria1, productoPersonalizado1, "https://sporting.vteximg.com.br/arquivos/ids/181885-1500-1500/1076705.jpg?v=636743585146670000", vendedor3);
            Publicacion publicacion5 = new Publicacion("Remera-5", "Remera-ferrari-2", categoria1, productoPersonalizado1, "https://sporting.vteximg.com.br/arquivos/ids/181885-1500-1500/1076705.jpg?v=636743585146670000", vendedor4);
            Publicacion publicacion6 = new Publicacion("Buzo-1", "Buzo-adidas-1", categoria3, productoPersonalizado3, "https://www.digitalsport.com.ar/files/products/5aa985af73cca-437094-1200x1200.jpg", vendedor1);
            Publicacion publicacion7 = new Publicacion("Buzo-2", "Buzo-adidas-2", categoria3, productoPersonalizado3, "https://www.stockcenter.com.ar/on/demandware.static/-/Sites-dabra-catalog/default/dw8767328e/products/AD_GD5477/AD_GD5477-1.JPG", vendedor1);
            Publicacion publicacion8 = new Publicacion("Buzo-3", "Buzo-nike-1", categoria3, productoPersonalizado3, "https://www.moovbydexter.com.ar/on/demandware.static/-/Sites-dabra-catalog/default/dwccadf8c5/products/NI_CK2852-010/NI_CK2852-010-1.JPG", vendedor2);
            Publicacion publicacion9 = new Publicacion("Buzo-4", "Buzo-nike-2", categoria3, productoPersonalizado3, "https://http2.mlstatic.com/buzo-nike-sb-D_NQ_NP_565521-MLA20814538350_072016-F.jpg", vendedor3);
            Publicacion publicacion10 = new Publicacion("Buzo-5", "Buzo-new-york-1", categoria3, productoPersonalizado3, "https://ver.rosario.gob.ar/media/cache/5c/66/5c66c0b1fb6b83d4eb6c685f1061da3b.png", vendedor4);
            Publicacion publicacion11 = new Publicacion("Campera-1", "Campera-emoji-1", categoria2, productoPersonalizado2, "https://cdn.shopify.com/s/files/1/2511/9944/products/front_3_cf99a61a-1f57-4d61-a281-ee39a135db21_2000x.jpg?v=1590896198", vendedor1);
            Publicacion publicacion12 = new Publicacion("Campera-2", "Campera-emoji-2", categoria2, productoPersonalizado2, "https://i.pinimg.com/originals/d6/9d/84/d69d842733d4354209b35bbfe4128611.jpg", vendedor1);
            Publicacion publicacion13 = new Publicacion("Campera-3", "Campera-seleccion-1", categoria2, productoPersonalizado2, "https://essential.vteximg.com.br/arquivos/ids/164064-1000-1000/262-6598_1.jpg?v=636634425722200000", vendedor2);
            Publicacion publicacion14 = new Publicacion("Campera-4", "Campera-colorida-1", categoria2, productoPersonalizado2, "https://2.bp.blogspot.com/_2kWfAkKKNo0/TQIUTWtYW1I/AAAAAAAAAAc/UntCxkv3zSk/S374/0340976B.jpg", vendedor3);
            Publicacion publicacion15 = new Publicacion("Campera-5", "Campera-colorida-2", categoria2, productoPersonalizado2, "https://www.digitalsport.com.ar/files/products/5b7ea81b92aa4-452696-500x500.jpg", vendedor4);
            Publicacion publicacion16 = new Publicacion("Pantalones-1", "jean-colorido-1", categoria4, productoPersonalizado4, "https://ae01.alicdn.com/kf/HTB1E3uvLpXXXXcLXFXXq6xXFXXXc/BKXRH-Colorido-Jean-vaqueros-de-Las-Mujeres-Mujeres-del-Resorte-de-la-Tendencia-Nacional-Bordado-de.jpg", vendedor1);
            Publicacion publicacion17 = new Publicacion("Pantalones-2", "jean-gastado-1", categoria4, productoPersonalizado4, "https://cdn.shopify.com/s/files/1/0290/8559/7780/products/JEANSJV1CELESTE_2048x2048.png?v=1599587339", vendedor1);
            Publicacion publicacion18 = new Publicacion("Bermuda-1", "bermuda-imagen-1", categoria4, productoPersonalizado4, "https://www.alegriademontar.com.br/wp-content/uploads/2020/07/5-12.jpg", vendedor2);
            Publicacion publicacion19 = new Publicacion("bermuda-2", "bermuda-fotos-1", categoria4, productoPersonalizado4, "https://www.alegriademontar.com.br/wp-content/uploads/2020/11/BERMUDA-FEM-PRETO.jpg", vendedor3);

            publicacionRepository.save(publicacion1);
            publicacionRepository.save(publicacion2);
            publicacionRepository.save(publicacion3);
            publicacionRepository.save(publicacion4);
            publicacionRepository.save(publicacion5);
            publicacionRepository.save(publicacion6);
            publicacionRepository.save(publicacion7);
            publicacionRepository.save(publicacion8);
            publicacionRepository.save(publicacion9);
            publicacionRepository.save(publicacion10);
            publicacionRepository.save(publicacion11);
            publicacionRepository.save(publicacion12);
            publicacionRepository.save(publicacion13);
            publicacionRepository.save(publicacion14);
            publicacionRepository.save(publicacion15);
            publicacionRepository.save(publicacion16);
            publicacionRepository.save(publicacion17);
            publicacionRepository.save(publicacion18);
            publicacionRepository.save(publicacion19);

            // creo un cliente para la demostracion del login
            Cliente cliente1 = new Cliente("Tomas", "Griffa", "t@gmail.com", "112345678", "1234", "Calle falsa", "123", "1", "A");
            Cliente cliente2 = new Cliente("Lionel", "Messi", "lm@gmail.com", "99999999", "1234", "Paris", "123", "45", "B");

            clienteRepository.save(cliente1);
            clienteRepository.save(cliente2);

        };
    }
}
