package org.dev.mark.appfacturas;

import org.dev.mark.appfacturas.modelo.Cliente;
import org.dev.mark.appfacturas.modelo.Factura;
import org.dev.mark.appfacturas.modelo.ItemFactura;
import org.dev.mark.appfacturas.modelo.Producto;

import java.util.Scanner;

public class SistemaFacturacion {

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.setRFC("HEGM901118QK8");
        cliente.setNombre("Marcos Salvador");
        cliente.setApellidoPaterno("Hernández");

        Scanner teclado = new Scanner(System.in);

        System.out.println("Ingrese una descripción de la factura");

        String desc = teclado.nextLine();

        Factura factura = new Factura(desc, cliente);

        Producto producto;
        String nombre;
        float precio;
        int cantidad;

        System.out.println();

        for (int i = 0; i < 3; i++) {
            producto = new Producto();
            System.out.print("Ingrese producto " + producto.getCodigo() + ": ");
            nombre = teclado.next();
            producto.setNombre(nombre);

            System.out.print("Ingrese el precio: ");
            precio = teclado.nextFloat();
            producto.setPrecio(precio);

            System.out.print("Ingrese la cantidad: ");
            cantidad = teclado.nextInt();

            ItemFactura item = new ItemFactura(cantidad, producto);
            factura.addItemFactura(item);

            System.out.println();
        }

        System.out.println(factura.generarDetalle());
    }

}
