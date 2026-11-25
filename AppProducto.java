package lab10;

import javax.swing.*;
import java.awt.*;

public class AppProducto extends JFrame {
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtStock;
    private JTextField txtCategoria;
    private JLabel lblResultado;

    private Producto producto;

    public AppProducto() {
        setTitle("Gestión de Productos");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        producto = new Producto("", 0, 0, "");

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        txtNombre = new JTextField();
        txtPrecio = new JTextField();
        txtStock = new JTextField();
        txtCategoria = new JTextField();

        panel.add(new JLabel("Nombre del producto:"));
        panel.add(txtNombre);

        panel.add(new JLabel("Precio:"));
        panel.add(txtPrecio);

        panel.add(new JLabel("Cantidad en stock:"));
        panel.add(txtStock);

        panel.add(new JLabel("Categoría:"));
        panel.add(txtCategoria);

        JButton btnActualizar = new JButton("Actualizar Producto");
        panel.add(btnActualizar);

        lblResultado = new JLabel("Ingrese los datos del producto");
        lblResultado.setVerticalAlignment(SwingConstants.TOP);

        add(panel, BorderLayout.NORTH);
        add(lblResultado, BorderLayout.CENTER);

        btnActualizar.addActionListener(e -> actualizarProducto());
    }

    private void actualizarProducto() {
        try {
            String nombre = txtNombre.getText().trim();
            double precio = Double.parseDouble(txtPrecio.getText());
            int stock = Integer.parseInt(txtStock.getText());
            String categoria = txtCategoria.getText().trim();

            if (nombre.isEmpty() || categoria.isEmpty() || precio < 0 || stock < 0) {
                throw new IllegalArgumentException();
            }

            producto.actualizarProducto(nombre, precio, stock, categoria);

            lblResultado.setText("<html>"+ producto.mostrarInformacion().replace("\n", "<br>")+ "</html>");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,"Ingrese valores numéricos válidos para precio y stock.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this,"Complete todos los campos y utilice valores no negativos.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AppProducto().setVisible(true);
        });
    }
}