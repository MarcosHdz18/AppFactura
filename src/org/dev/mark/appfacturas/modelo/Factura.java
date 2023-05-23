package org.dev.mark.appfacturas.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Factura {

    private int folio;

    private String descripcion;

    private Date fecha;

    private Cliente cliente;

    private ItemFactura[] items;

    private int indiceItems;

    private static final int MAX_ITEMS = 12;
    private static int ultimoFolio;

    public Factura(String descripcion, Cliente cliente) {
        this.descripcion = descripcion;
        this.cliente = cliente;
        this.items = new ItemFactura[MAX_ITEMS];
        this.folio = ++ultimoFolio;
        this.fecha = new Date();
    }

    public int getFolio() {
        return folio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ItemFactura[] getItems() {
        return items;
    }

    public void setItems(ItemFactura[] items) {
        this.items = items;
    }

    public void addItemFactura(ItemFactura item) {

        if (indiceItems < MAX_ITEMS) {
            this.items[indiceItems++] = item;
        }

    }

    public float calcularTotal() {
        float total = 0.0f;

        for (ItemFactura item : this.items) {
            if (!(item instanceof ItemFactura)) {
                continue;
            }
            total += item.calcularImporte();
        }
        return total;
    }

    public String generarDetalle() {
        StringBuilder sb = new StringBuilder("Factura No: ");
        sb.append(folio).append("\nCliente: ").append(this.cliente.getNombre())
                .append(this.cliente.getApellidoPaterno())
                .append("\t RFC: ").append(this.cliente.getRFC())
                .append("\nDescripción: ").append(this.descripcion)
                .append("\n")
                .append("\n")
                .append("\n#\tNombre\t$\tCant.\tTotal\n");

        SimpleDateFormat df = new SimpleDateFormat("dd 'de' MMMM, yyyy");

        sb.append("Fecha de emisión: ").append(df.format(this.fecha)).append("\n");

        for (ItemFactura itemFactura: this.items) {
            if (itemFactura == null) {
                continue;
            }
            sb.append(itemFactura.getProducto().getCodigo()).append("\t")
                    .append(itemFactura.getProducto().getNombre())
                    .append("\t")
                    .append(itemFactura.getProducto().getPrecio())
                    .append("\t")
                    .append(itemFactura.getCantidad())
                    .append("\t")
                    .append(itemFactura.calcularImporte())
                    .append("\n");
        }

        sb.append("\nTotal de Factura: $").append(calcularTotal());

        return sb.toString();
    }
}
