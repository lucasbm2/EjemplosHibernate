package com.example.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Entity
public class Producto {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column (columnDefinition = "CHAR(36)")
    private String id;

    @Column (name = "nombre_empleado", nullable = false,  length = 100)
    private String nombre;

    @Column (nullable = false)
    private int cantidad;

    @Column (name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column (name = "fecha_creacion",nullable = false)
    private Date fechaCreacion;

    @Column (length = 255)
    private String descripcion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = BigDecimal.valueOf(precioUnitario);
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Producto(String id, String nombre, int cantidad, BigDecimal precioUnitario, Date fechaCreacion, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.fechaCreacion = fechaCreacion;
        this.descripcion = descripcion;
    }

    public Producto(String nombre, int cantidad, BigDecimal precioUnitario, Date fechaCreacion, String descripcion) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.fechaCreacion = fechaCreacion;
        this.descripcion = descripcion;
    }


    public Producto() {
        nombre="";
        cantidad=0;
        precioUnitario=BigDecimal.valueOf(0);
        fechaCreacion = Date.valueOf(LocalDate.now());
    }

    @Override
    public String toString() {
        return "Producto: |" + " ID: " + id +" -  NOMBRE: " + nombre + " - CANTIDAD: " + cantidad + " - PRECIO UNITARIO: " + precioUnitario + " - FECHA CREACION: " + fechaCreacion + " - DESCRIPCION: " + descripcion;
    }

}
