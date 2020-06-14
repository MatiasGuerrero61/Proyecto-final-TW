package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Filtro;
import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.modelo.Tienda;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCarrito;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioTienda;

@Controller
@RequestMapping(value="/tienda")
public class ControladorTienda {
	
	private ServicioLogin servicioLogin;
	private ServicioTienda servicioTienda;	
	private ServicioCarrito servicioCarrito;
	
	@Autowired
	public ControladorTienda(ServicioLogin servicioLogin, 
							ServicioTienda servicioTienda, 
							ServicioCarrito servicioCarrito ){
		this.servicioLogin = servicioLogin;
		this.servicioTienda = servicioTienda;
		this.servicioCarrito = servicioCarrito;
	}
	
	 
	@RequestMapping(path = "/todas", method = RequestMethod.GET)
	public ModelAndView irAListarTiendas() {
		ModelMap modelo = new ModelMap();
		List<Tienda> tiendas = servicioTienda.listarTiendas();
		if(!tiendas.isEmpty()) {
			modelo.put("tiendas",tiendas);
		}
		else {
			modelo.put("msj","No hay tiendas disponibles");
		}	
		 
		return new ModelAndView("tienda/listar-tiendas",modelo);
	}
	
	@RequestMapping(path = "/ver/{id}", method = RequestMethod.GET)
    public ModelAndView irATienda(@PathVariable("id") String id,
    		@RequestParam(value="buscador", required=false) String buscador,
    		@RequestParam(value="categoria", required=false) String categoria, 
    		@RequestParam(value="min", required=false) String min, 
    		@RequestParam(value="max", required=false) String max,
    		@RequestParam(value="orden", required=false) String orden,
    		HttpServletRequest request) {
		
		
		System.out.println("orden: " + orden);
        ModelMap modelo = new ModelMap();

        try {
        	Long idTienda = Long.parseLong(id);
            Tienda tienda = servicioTienda.buscarTiendaPorId(idTienda);

			List<Producto> categorias = servicioTienda.listarCategorias(tienda);
			modelo.put("categorias", categorias);

            if (tienda != null) {
            	modelo.put("tienda", tienda);
            	
            	Usuario usuario = servicioLogin.obtenerUsuarioConectado(request);
                if (usuario != null) {
                	if(servicioCarrito.tengoCarritoActivo(usuario)) {
                		if(servicioCarrito.obtenerIdDeTienda() != idTienda) {
                			servicioCarrito.destruirCarrito();
                		}
                		if(servicioCarrito.tengoItems()) {
                			List<Item> items = servicioCarrito.listarItems();
                			modelo.put("itemsCarrito", items);
                		}                		
                	}
                }
                
            	Filtro filtros = new Filtro();
            	if(buscador != null) {filtros.setNombre(buscador);}
            	if(categoria != null) {filtros.setCategoria(categoria);}
            	if(min != null) {filtros.setPrecioMin(min);}
            	if(max != null) {filtros.setPrecioMax(max);}
            	if(orden != null) {filtros.setOrder(orden);}
            	List<Producto> productos = servicioTienda.filtrarProductos(filtros, tienda);
            	if(!productos.isEmpty()) {
            		 modelo.put("productos", productos);
            		 modelo.put("filtros", filtros);
            	}
            	else {
            		modelo.put("msj", "No hay productos disponibles");
            		modelo.put("filtros", filtros);
            	}
            } else {
                modelo.put("msj", "Tienda no encontrada");
            }
        } catch (Exception e) {
            modelo.put("msj", e.getClass());
        } 
        
        return new ModelAndView("tienda/tienda", modelo);
    }

	@RequestMapping(value = "/cargar-carrito", method = RequestMethod.POST)
	 public ModelAndView cargarCarrito(@RequestParam("idTienda") Long idTienda,
			 						@RequestParam("idProducto") Long idProducto,
			 						@RequestParam("cantidad") Integer cantidad,
			 						HttpServletRequest request) {
		
		Usuario usuario = servicioLogin.obtenerUsuarioConectado(request);
        if (usuario != null) {
        	if(!servicioCarrito.tengoCarritoActivo(usuario)) {
        		servicioCarrito.generarCarritoVacio(usuario);
        	}
        	Producto producto = servicioTienda.obtenerProducto(idProducto);
        	System.out.println("producto para el carrito:");
    		System.out.println(producto);
        	servicioCarrito.cargarItem(producto, cantidad);
        		List<Item> items = servicioCarrito.listarItems();
        		System.out.println("items en carrito:");
        		System.out.println(items);
        }
        else {
        	return new ModelAndView("redirect:../login");
        }
		return new ModelAndView("redirect:ver/"+idTienda);
	}
	
	@RequestMapping(value = "/destruir-carrito", method = RequestMethod.POST)
	 public ModelAndView destruirCarrito(@RequestParam("idTienda") Long idTienda,
			 						HttpServletRequest request) {
		
		Usuario usuario = servicioLogin.obtenerUsuarioConectado(request);
       if (usuario != null) {
       	servicioCarrito.destruirCarrito();
       }
       else {
       	return new ModelAndView("redirect:../login");
       }
		return new ModelAndView("redirect:ver/"+idTienda);
	}
	
	@RequestMapping(value = "/eliminar-producto-de-carrito", method = RequestMethod.POST)
	 public ModelAndView eliminarProductoDeCarrito(@RequestParam("idTienda") Long idTienda,
			 						@RequestParam("idProducto") Long idProducto,
			 						HttpServletRequest request) {
		
		Usuario usuario = servicioLogin.obtenerUsuarioConectado(request);
      if (usuario != null) {
      	servicioCarrito.eliminarProductoDeCarrito(idProducto);
      }
      else {
      	return new ModelAndView("redirect:../login");
      }
		return new ModelAndView("redirect:ver/"+idTienda);
	}
	
}
