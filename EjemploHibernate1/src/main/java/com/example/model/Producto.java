package com.example.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "nombre_empleado", nullable = false,  length = 100)
    private String nombre;

    @Column (nullable = false)
    private int cantidad;

    @Column (name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private double precioUnitario;

    @Column (name = "fecha_creacion",nullable = false)
    private Date fechaCreacion;

    @Column (length = 255)
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
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

    public Producto( String nombre, int cantidad, double precioUnitario, Date fechaCreacion, String descripcion) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.fechaCreacion = fechaCreacion;
        this.descripcion = descripcion;
    }

    public Producto() {
    }

    @Override
    public String toString() {
        return "Producto: |" + " ID: " + id +"-  NOMBRE: " + nombre + "- CANTIDAD: " + cantidad + "- PRECIO UNITARIO: " + precioUnitario + "- FECHA CREACION: " + fechaCreacion + "- DESCRIPCION: " + descripcion;
    }
}
