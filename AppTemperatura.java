package lab10;

import javax.swing.*;
import java.awt.*;

public class AppTemperatura extends JFrame {
    private JTextField[] campos;
    private GraficoTemperatura grafico;

    private String[] dias = {
        "Lunes", "Martes", "Miércoles", "Jueves",
        "Viernes", "Sábado", "Domingo"
    };

    public AppTemperatura() {
        setTitle("Registro de Temperaturas Semanales");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        campos = new JTextField[7];

        JPanel panelDatos = new JPanel(new GridLayout(2, 7, 5, 5));
        panelDatos.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));

        // Crear campos para cada día
        for (int i = 0; i < 7; i++) {
            panelDatos.add(new JLabel(dias[i], SwingConstants.CENTER));
        }

        for (int i = 0; i < 7; i++) {
            campos[i] = new JTextField();
            panelDatos.add(campos[i]);
        }

        // Botón para mostrar el gráfico
        JButton btnMostrar = new JButton("Mostrar Gráfico");

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelDatos, BorderLayout.CENTER);
        panelSuperior.add(btnMostrar, BorderLayout.SOUTH);

        // Panel del gráfico
        grafico = new GraficoTemperatura();

        add(panelSuperior, BorderLayout.NORTH);
        add(grafico, BorderLayout.CENTER);

        btnMostrar.addActionListener(e -> mostrarGrafico());
    }

    private void mostrarGrafico() {
        try {
            double[] temperaturas = new double[7];

            for (int i = 0; i < 7; i++) {
                temperaturas[i] = Double.parseDouble(campos[i].getText().trim());
                if (!Double.isFinite(temperaturas[i])) {
                    throw new NumberFormatException();
                }
            }
            grafico.actualizarGrafico(temperaturas);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese una temperatura numérica válida para cada día.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AppTemperatura().setVisible(true);
        });
    }
}