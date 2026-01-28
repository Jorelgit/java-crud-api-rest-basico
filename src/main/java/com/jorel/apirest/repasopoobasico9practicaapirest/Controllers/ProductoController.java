package com.jorel.apirest.repasopoobasico9practicaapirest.Controllers;

import com.jorel.apirest.repasopoobasico9practicaapirest.Entities.Producto;
import com.jorel.apirest.repasopoobasico9practicaapirest.Repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController     // Anotacion para indicar que es un controlador Rest
@RequestMapping("/productos")    // Anotacion para indicar a donde va a apuntar la url
public class ProductoController {

    // Se expone a continuacion el resto de la informacion

    // Lo primero que se hace es inyectar una instancia del repositorio, lo cual lo puedo hacer
    // haciendo uso de la etiqueta Autowired, con lo cual nos ayuda a hacer la inyeccion de la instancia
    // de dicho repositorio

    @Autowired
    private ProductoRepository productoRepository;



    // A continuacion se implementan los metodos HTTP


    // GetMapping para recuperar o hacer un get de los productos
    @GetMapping
    public List<Producto> getAllProductos() {
        // Al Jpa ya traer lo necesario para poder hacer las conexiones con la base de datos, al poner
        // productoRepository.findAll() ya me permite recuperar los elementos de la base de datos
        return productoRepository.findAll();
    }



    // GetMapping. Este es el GetMapping individual que va a buscar un producto de forma individual por su Id,
    // es decir, teniendo como parametro el Id de un producto

    @GetMapping("/{id}")
    public Producto getProductById(@PathVariable Long id) {

        // Busca el id, si lo encuentra lo devuelve, sino, se lanza un error, el cual se ejecuta con una
        // funcion flecha
        return productoRepository.findById(id).
                orElseThrow(() -> new RuntimeException("No se encontro el producto con el id: " + id));
    }



    // PostMapping, para crear nuevos produtos o agregar recursos

    // En este caso nos va a devolver un producto, es decir es un metodo del tipo Producto. Se va a llamar en este
    // caso createProducto. Y se le tiene que pasar un Body (que es como el cuerpo del Json, el cual tiene que tener
    // obligatoriamente como caracteristicas los atributos establecidos en la clase Producto (es decir un nombre y
    // un precio en este caso). Por lo que ese Body, llamado tambien RequestBody, es una peticion para recibir el Body del
    // Json, el cual va a tener un producto, es decir, se va a recibir un RequestBody de tipo Producto (Ques es
    // donde estan las caracteristicas o atributos del Producto))
    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }



    // PutMapping, para hacer la actualizacion

    // Se recibe a traves de la url el Id del producto que se quiere modificar. Esto se hace a traves de un
    // PathVariable, al que se le indica el tipo de dato y el nombre del dato que se va a recibir. Ademas
    // tambien se recibe el RequestBody, ya que se va a modificar el producto como tal
    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto producto) {

        // Aqui se hacen dos cosas a continuacion, tiene que buscar el Id que se ha ingresado para ver si
        // este existe o no, si no esxiste lanza un error, y si existe lo modifica

        Producto productoActualizado = productoRepository.findById(id).
                orElseThrow(() -> new RuntimeException("No se encontro el producto con el id: " + id));

        productoActualizado.setNombre(producto.getNombre());
        productoActualizado.setPrecio(producto.getPrecio());

        return productoRepository.save(productoActualizado);

    }



    // DeleteMapping, para borrar un producto

    // Aqui puedo devolver el producto como tal ya eliminado, pero tambien (como en este caso) un String
    // que indique que se ha eliminado el producto
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {

        Producto borrarProducto = productoRepository.findById(id).
                orElseThrow(() -> new RuntimeException("No se encontro el producto con el id: " + id));

        productoRepository.delete(borrarProducto);

        return "El producto con el ID " + id + " ha sido eliminado";
    }




}
