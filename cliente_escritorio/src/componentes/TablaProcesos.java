/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import gestor.Gestor;
import interfaces.IntDetalleProceso;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;

/**
 *
 * @author carri
 */
public class TablaProcesos extends JTable {

	ModeloTabla modelo;
	Gestor gestor;
	String[] titulos;

	public TablaProcesos() {
		this.modelo = new ModeloTabla();
		this.gestor = Gestor._getGestor();
		this.titulos = new String[]{"ID PROCESO", "NOMBRE PROCESO", "FECHA CREACION PROCESO", "ESTADO PROCESO"};
		accionClick(); 
	}

	public void cargarTabla() {
		String[][] procesos = gestor.getProcesos();
		this.modelo = new ModeloTabla(procesos, this.titulos);
		this.setModel(modelo);
	}

	public void accionClick() {
		TablaProcesos tabla = this; 
		tabla.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					String idProceso = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
					IntDetalleProceso intDetalleProceso = new IntDetalleProceso(idProceso);
					intDetalleProceso.setVisible(true);
				}
			}
		});
	}

}