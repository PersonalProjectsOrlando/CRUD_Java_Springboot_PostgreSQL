package edu.oralbama.crudProductos.business;

import edu.oralbama.crudProductos.models.ProductoEntity;
import edu.oralbama.crudProductos.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductManager implements ProductManagerInterface{
    @Autowired
    private ProductRepository repositorio;

    @Override
    public List<ProductoEntity> getListaProductos() {
        return repositorio.findAll();
    }

    @Override
    public ProductoEntity getIdProducto(int idProduct) throws Exception {
        Optional<ProductoEntity> productBD = repositorio.findById(idProduct);
        if(productBD.isPresent()){
            return productBD.get();
        }

        throw new Exception("!!ProductoEntity No existe!!");
    }


    public ProductoEntity getProductPrice(String price) {
        return null;
    }

    @Override
    public String setProducto(ProductoEntity productoEntity) throws Exception {
        try{
            getIdProducto(productoEntity.getIdProduct());    //Verifica que el product exista
        }catch (Exception e){
            repositorio.save(productoEntity);
            return "ProductoEntity creado";
        }
        throw  new Exception("ProductoEntity existente");

    }


    @Override
    public ProductoEntity updateProduct(ProductoEntity productUpdate, int id) throws Exception {
        ProductoEntity productoEntityBd = getIdProducto(id);
        try {
            if (productUpdate.getName() != null && !productUpdate.getName().equals("")) {
                productoEntityBd.setName(productUpdate.getName());
            }
            if (productUpdate.getDescription() != null && !productUpdate.getDescription().equals("")) {
                productoEntityBd.setDescription(productUpdate.getDescription());
            }
            if (productUpdate.getPrice() != null && !productUpdate.getPrice().equals("")) {
                productoEntityBd.setPrice(productUpdate.getPrice());
            }
            if (productUpdate.getStock() != 0) {
                productoEntityBd.setStock(productUpdate.getStock());
            }
            return repositorio.save(productoEntityBd);
        } catch (Exception e) {
            throw new Exception("ProductoEntity no existe, fallo actualización de datos");
        }
    }


    @Override
    public String deleteProduct(int id) {
        repositorio.deleteById(id);
        return "ProductoEntity eliminado";
    }
}
