package edu.oralbama.crudProductos.controller;

import edu.oralbama.crudProductos.business.ProductManagerInterface;
import edu.oralbama.crudProductos.models.ObjectRequest;
import edu.oralbama.crudProductos.models.ProductoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {
    /*@Autowired
    private ProductManagerList productManager;  // Se usa cpn la estrategia de gestor con ArrayLList*/
    @Autowired
    private ProductManagerInterface productManager;
    ProductoEntity productoEntity = new ProductoEntity();
    @GetMapping("/products")
    public ResponseEntity<List<ProductoEntity>> getProducts(){
        return new ResponseEntity<>(productManager.getListaProductos(), HttpStatus.OK);
    }



    @GetMapping("/productId/{id}")
    public ResponseEntity<Object> getProductNamePath(@PathVariable int id){
        try{
            productoEntity = productManager.getIdProducto(id);
            return new ResponseEntity<>(productoEntity,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //Metodo para crear un productoEntity
    @PostMapping("/productoEntity")
    public ResponseEntity<String> postProduct(@RequestBody ProductoEntity productoEntity){
        try{
            String product = productManager.setProducto(productoEntity);
            return new ResponseEntity<>(product,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/productoEntity/{id}")
    public ResponseEntity<ObjectRequest> patchProduct(@RequestBody ProductoEntity productUpdate, @PathVariable int id){
        try{
            ProductoEntity productBd = productManager.updateProduct(productUpdate, id);
            return new ResponseEntity<>(new ObjectRequest("Actualizacion OK",productUpdate),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ObjectRequest(e.getMessage(),null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/productoEntity/{id}")
    public ResponseEntity<ObjectRequest> deleteProduct(@PathVariable int id){
        try{
            String mensaje = productManager.deleteProduct(id);
            return new ResponseEntity<>(new ObjectRequest(mensaje,null),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ObjectRequest(e.getMessage(),null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
