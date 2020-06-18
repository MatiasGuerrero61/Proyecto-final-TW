package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.excepciones.TiendaNotFoundException;
import ar.edu.unlam.tallerweb1.modelo.Categoria;
import ar.edu.unlam.tallerweb1.modelo.Filtro;
import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.modelo.Tienda;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCarrito;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioTienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ControladorTienda {

    private ServicioLogin servicioLogin;
    private ServicioTienda servicioTienda;
    private ServicioCarrito servicioCarrito;

    @Autowired
    public ControladorTienda(ServicioLogin servicioLogin,
                             ServicioTienda servicioTienda,
                             ServicioCarrito servicioCarrito) {
        this.servicioLogin = servicioLogin;
        this.servicioTienda = servicioTienda;
        this.servicioCarrito = servicioCarrito;
    }


    @RequestMapping(path = "/tiendas", method = RequestMethod.GET)
    public ModelAndView irAListarTiendas() {
        ModelMap modelo = new ModelMap();
        List<Tienda> tiendas = servicioTienda.listarTiendas();
        if (!tiendas.isEmpty()) {
            modelo.put("tiendas", tiendas);
        } else {
            modelo.put("msj", "No hay tiendas disponibles");
        }

        return new ModelAndView("tienda/listar-tiendas", modelo);
    }

    @ExceptionHandler(value = TiendaNotFoundException.class)
    public ModelAndView tiendaNotFoundView() {
        return new ModelAndView("tienda/tienda-not-found");
    }

    @RequestMapping(path = "/tiendas/{id}", method = RequestMethod.GET)
    public ModelAndView getTienda(@PathVariable("id") String id,
                                  @RequestParam(value = "descripcion", required = false) String descripcion,
                                  @RequestParam(value = "id_categoria", required = false) Integer idCategoria,
                                  @RequestParam(value = "min", required = false) Integer min,
                                  @RequestParam(value = "max", required = false) Integer max,
                                  @RequestParam(value = "orden", required = false) String orden,
                                  HttpServletRequest request) {


        System.out.println("orden: " + orden);
        ModelMap modelo = new ModelMap();

        Long idTienda = Long.parseLong(id);
        Tienda tienda = servicioTienda.buscarTiendaPorId(idTienda);

        /*List<Producto> categorias = servicioTienda.listarCategorias(tienda);
        modelo.put("categorias", categorias);*/

        modelo.put("tienda", tienda);

        Usuario usuario = servicioLogin.obtenerUsuarioConectado(request);
        if (usuario != null) {
            if (servicioCarrito.tengoCarritoActivo(usuario)) {
                if (servicioCarrito.obtenerIdDeTienda() != idTienda) {
                    servicioCarrito.destruirCarrito();
                }
                if (servicioCarrito.tengoItems()) {
                    List<Item> items = servicioCarrito.listarItems();
                    modelo.put("itemsCarrito", items);
                }
            }
        }

        Filtro filtro = new Filtro();
        filtro.setDescripcion(descripcion);
        filtro.setIdCategoria(idCategoria);
        filtro.setPrecioMin(min);
        filtro.setPrecioMax(max);
        filtro.setOrder(orden);

        List<Producto> productos = servicioTienda.filtrarProductos(filtro, tienda);
        List<Categoria> categorias = productos.stream().map(Producto::getCategoria).collect(Collectors.toList());

        modelo.put("productos", productos);
        modelo.put("filtros", filtro);
        modelo.put("categorias", categorias);

        return new ModelAndView("tienda/tienda", modelo);
    }

    @RequestMapping(value = "cargar-carrito", method = RequestMethod.POST)
    public ModelAndView cargarCarrito(@RequestParam("idTienda") Long idTienda,
                                      @RequestParam("idProducto") Long idProducto,
                                      @RequestParam("cantidad") Integer cantidad,
                                      HttpServletRequest request) {

        Usuario usuario = servicioLogin.obtenerUsuarioConectado(request);
        if (usuario != null) {
            if (!servicioCarrito.tengoCarritoActivo(usuario)) {
                servicioCarrito.generarCarritoVacio(usuario);
            }
            Producto producto = servicioTienda.obtenerProducto(idProducto);
            System.out.println("producto para el carrito:");
            System.out.println(producto);
            servicioCarrito.cargarItem(producto, cantidad);
            List<Item> items = servicioCarrito.listarItems();
            System.out.println("items en carrito:");
            System.out.println(items);
        } else {
            return new ModelAndView("redirect:../login");
        }
        return new ModelAndView("redirect:tiendas/" + idTienda);
    }

    @RequestMapping(value = "/destruir-carrito", method = RequestMethod.POST)
    public ModelAndView destruirCarrito(@RequestParam("idTienda") Long idTienda,
                                        HttpServletRequest request) {

        Usuario usuario = servicioLogin.obtenerUsuarioConectado(request);
        if (usuario != null) {
            servicioCarrito.destruirCarrito();
        } else {
            return new ModelAndView("redirect:../login");
        }
        return new ModelAndView("redirect:ver/" + idTienda);
    }

    @RequestMapping(value = "/eliminar-producto-de-carrito", method = RequestMethod.POST)
    public ModelAndView eliminarProductoDeCarrito(@RequestParam("idTienda") Long idTienda,
                                                  @RequestParam("idProducto") Long idProducto,
                                                  HttpServletRequest request) {

        Usuario usuario = servicioLogin.obtenerUsuarioConectado(request);
        if (usuario != null) {
            servicioCarrito.eliminarProductoDeCarrito(idProducto);
        } else {
            return new ModelAndView("redirect:../login");
        }
        return new ModelAndView("redirect:ver/" + idTienda);
    }

}
