package lab10;

public class Producto {
    private String nombre;
    private double precio;
    private int cantidadStock;
    private String categoria;

    public Producto(String nombre, double precio, int cantidadStock, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadStock = cantidadStock;
        this.categoria = categoria;
    }

    public void actualizarProducto(String nombre, double precio,
                                   int cantidadStock, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadStock = cantidadStock;
        this.categoria = categoria;
    }

    public String mostrarInformacion() {
        return "PRODUCTO ACTUALIZADO\n"
                + "Nombre: " + nombre + "\n"
                + "Precio: S/ " + String.format("%.2f", precio) + "\n"
                + "Cantidad en stock: " + cantidadStock + "\n"
                + "Categoría: " + categoria;
    }
}