package com.dev.market.persistence.entity;

//
//import lombok.Getter;
//import lombok.Setter;

import javax.persistence.*;
import java.util.List;

//@Getter
//@Setter
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    private String id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "celular")
    private Long celular;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras;

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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Long getCelular() {
        return celular;
    }

    public void setCelular(Long celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }
}
