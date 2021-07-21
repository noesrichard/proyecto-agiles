/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author carri
 */
public class Usuario {
	
	String cedula, nombre, apellido; 
	Activo [] activos; 

	public Usuario(String cedula, String nombre, String apellido) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	public Usuario(String cedula, String nombre, String apellido,Activo [] activos) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.activos = activos; 
	}

	public String getCedula() {
		return cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

}
