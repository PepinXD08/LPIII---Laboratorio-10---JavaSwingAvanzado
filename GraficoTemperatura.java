package lab10;

import javax.swing.*;
import java.awt.*;

public class GraficoTemperatura extends JPanel {
    private double[] temperaturas;
    private String[] dias = {
        "Lun", "Mar", "Mie", "Jue", "Vie", "Sab", "Dom"
    };

    public GraficoTemperatura() {
        temperaturas = new double[7];
        setBackground(Color.WHITE);
    }

    public void actualizarGrafico(double[] datos) {
        temperaturas = datos.clone();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int ancho = getWidth();
        int alto = getHeight();

        int margenIzquierdo = 50;
        int margenInferior = 40;
        int margenSuperior = 30;
        int margenDerecho = 20;

        int graficoAncho = ancho - margenIzquierdo - margenDerecho;
        int graficoAlto = alto - margenSuperior - margenInferior;

        g2.setColor(Color.BLACK);

        // Dibujar ejes
        g2.drawLine(margenIzquierdo, margenSuperior, margenIzquierdo, alto - margenInferior);
        g2.drawLine(margenIzquierdo, alto - margenInferior, ancho - margenDerecho, alto - margenInferior);

        // Título
        g2.setFont(new Font("Arial", Font.BOLD, 16));
        g2.drawString("Temperaturas de la semana", margenIzquierdo, 20);

        // Calcular temperatura mínima y máxima
        double min = temperaturas[0];
        double max = temperaturas[0];

        for (double t : temperaturas) {
            min = Math.min(min, t);
            max = Math.max(max, t);
        }

        // Evitar división entre cero
        if (max == min) {
            min -= 5;
            max += 5;
        } else {
            double margen = (max - min) * 0.15;
            min -= margen;
            max += margen;
        }

        int[] puntosX = new int[7];
        int[] puntosY = new int[7];

        // Calcular posiciones de los puntos
        for (int i = 0; i < 7; i++) {
            puntosX[i] = margenIzquierdo + i * graficoAncho / 6;
            puntosY[i] = alto - margenInferior - (int) ((temperaturas[i] - min) / (max - min) * graficoAlto);
        }

        // Dibujar líneas
        g2.setColor(Color.BLUE);
        g2.setStroke(new BasicStroke(2));

        for (int i = 0; i < 6; i++) {
            g2.drawLine(puntosX[i], puntosY[i], puntosX[i + 1], puntosY[i + 1]);
        }

        // Dibujar puntos y etiquetas
        for (int i = 0; i < 7; i++) {
            g2.setColor(Color.RED);
            g2.fillOval(puntosX[i] - 5, puntosY[i] - 5, 10, 10);

            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Arial", Font.PLAIN, 11));

            g2.drawString(dias[i], puntosX[i] - 10, alto - 15);

            g2.drawString(String.format("%.1f°C", temperaturas[i]), puntosX[i] - 15, puntosY[i] - 10
            );
        }
    }
}