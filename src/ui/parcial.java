package ui;
import java.util.Scanner;

public class parcial {

    public static Scanner reader;
    public static double[] precios;
    public static int[] unidades;

    public static void main(String[] args) {

        inicializarGlobales();
        menu();
    }

    /**
     * Descripción: Este método se encarga de iniciar las variables globales
     * pre: El Scanner reader debe estar declarado
     * pos: El Scanner reader queda inicializado
    */
    public static void inicializarGlobales() {
        reader = new Scanner(System.in);
    }

    /**
     * Descripción: Este método se encarga de desplegar el menú al usuario y mostrar los mensajes de resultado para cada funcionalidad
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar inicializado
    */
    public static void menu() {

        System.out.println("Bienvenido a BurgerTown!");

        establecerCantidadVendida();

        boolean salir = false;

        do {

            System.out.println("\nMenú Principal:");
            System.out.println("1. Solicitar precios ($) y cantidades de cada plato vendido en el día");
            System.out.println("2. Calcular la cantidad total de platos vendidos en el día");
            System.out.println("3. Calcular el precio promedio de los platos vendidos en el día");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el día");
            System.out.println("5. Consultar el número de platos que en el día han superado un límite mínimo de ventas");
            System.out.println("6. Salir");
            System.out.println("\nDigite la opción a ejecutar:");
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos();
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de platos vendidos en el día fue de: " + calcularTotalPlatosVendidos());
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de los platos vendidos en el día fue de: " + calcularPrecioPromedio());
                    break;
                case 4:
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el día fueron: " + calcularVentasTotales());
                    break;
                case 5:
                    System.out.println("\nDigite el límite mínimo de ventas a analizar:");
                    double limite = reader.nextDouble();
                    System.out.println("\nDe las " + precios.length + " referencias vendidas en el día, " + consultarPlatosSobreLimite(limite) + " superaron el límite mínimo de ventas de " + limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    reader.close();
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (!salir);

    }

    /**
     * Descripción: Este método se encarga de preguntar al usuario el número de platos diferentes 
     * vendidos en el día e inicializa con ese valor los arreglos encargados de almacenar precios y cantidades
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Los arreglos precios y unidades quedan inicializados
     */
    public static void establecerCantidadVendida() {
        System.out.println("\nDigite el número de platos diferentes vendidos en el día:");
        int platos = reader.nextInt();

        precios = new double[platos];
        unidades = new int[platos];
    }

    /**
     * Descripción: Este método solicita al usuario los precios y cantidades de cada plato vendido en el día
     * pre: El Scanner reader debe estar inicializado
     * pos: Los arreglos precios y unidades se llenan con los valores ingresados por el usuario
     */
    public static void solicitarDatos() {
        for (int i = 0; i < precios.length; i++) {
            System.out.println("Ingrese el precio del plato " + (i + 1) + ":");
            precios[i] = reader.nextDouble();
            System.out.println("Ingrese la cantidad de unidades vendidas del plato " + (i + 1) + ":");
            unidades[i] = reader.nextInt();
        }
    }

    /**
     * Descripción: Calcula el total de platos vendidos sumando todas las unidades
     * pre: Los arreglos precios y unidades deben estar inicializados
     * @return La cantidad total de platos vendidos
     */
    public static int calcularTotalPlatosVendidos() {
        int totalPlatos = 0;
        for (int unidadesVendidas : unidades) {
            totalPlatos += unidadesVendidas;
        }
        return totalPlatos;
    }

    /**
     * Descripción: Calcula el precio promedio de los platos vendidos
     * pre: Los arreglos precios y unidades deben estar inicializados
     * @return El precio promedio de los platos vendidos
     */
    public static double calcularPrecioPromedio() {
        double totalVentas = calcularVentasTotales();
        int totalPlatos = calcularTotalPlatosVendidos();
        if (totalPlatos == 0) {
            return 0;
        }
        return totalVentas / totalPlatos;
    }

    /**
     * Descripción: Calcula las ventas totales (dinero recaudado) sumando el precio por la cantidad vendida de cada plato
     * pre: Los arreglos precios y unidades deben estar inicializados
     * @return Las ventas totales del día
     */
    public static double calcularVentasTotales() {
        double totalVentas = 0;
        for (int i = 0; i < precios.length; i++) {
            totalVentas += precios[i] * unidades[i];
        }
        return totalVentas;
    }

    /**
     * Descripción: Consulta cuántos platos vendieron más de un límite mínimo
     * pre: Los arreglos precios y unidades deben estar inicializados
     * @param limite El límite mínimo de ventas
     * @return La cantidad de platos que superaron el límite
     */
    public static int consultarPlatosSobreLimite(double limite) {
        int platosSobreLimite = 0;
        for (int i = 0; i < precios.length; i++) {
            double totalVentasPlato = precios[i] * unidades[i];
            if (totalVentasPlato > limite) {
                platosSobreLimite++;
            }
        }
        return platosSobreLimite;
    }
}