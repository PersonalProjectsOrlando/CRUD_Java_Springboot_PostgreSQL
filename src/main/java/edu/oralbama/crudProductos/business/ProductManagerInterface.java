package edu.oralbama.crudProductos.business;

import edu.oralbama.crudProductos.models.ProductoEntity;

import java.util.List;

public interface ProductManagerInterface {
    public List<ProductoEntity> getListaProductos();
    public ProductoEntity getIdProducto(int id) throws Exception;

    public String setProducto(ProductoEntity productoEntity) throws Exception;

    public ProductoEntity updateProduct(ProductoEntity productUpdate, int id) throws Exception;
    public String deleteProduct(int id);
}
