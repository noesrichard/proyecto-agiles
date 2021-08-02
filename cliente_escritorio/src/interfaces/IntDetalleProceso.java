/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import componentes.PnlDetalleProceso;
import entidades.Proceso;
import gestor.Conexion;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carri
 */
public class IntDetalleProceso extends javax.swing.JFrame {

	Conexion conexion;
	DefaultTableModel modeloTablaActivos, modeloTablaUsuarios;
	Proceso proceso;
	String idProceso;
	private int contador;

	private static IntDetalleProceso instancia = null;

	public static IntDetalleProceso _getVentana() {
		if (instancia == null) {
			instancia = new IntDetalleProceso();
			instancia.setVisible(true);
		}
		if (instancia.getExtendedState() != 0 || !instancia.isVisible()) {
			instancia.setVisible(true);
			instancia.setState(JFrame.NORMAL);
		}
		instancia.toFront();
		return instancia;
	}

	private IntDetalleProceso() {
		initComponents();
		this.setTitle("Detalles de los Procesos");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.contador = 0;
	}

	public void agregarDetalle(int idProceso) {
		boolean encontro = false;
		if (contador == 0) {
			PnlDetalleProceso detalle = new PnlDetalleProceso(idProceso);
			this.jTbpDetalles.add(detalle);
			this.jTbpDetalles.setTitleAt(this.contador, detalle.getNombreProceso());
			jTbpDetalles.setSelectedIndex(contador);
			this.contador++;
		} else {
			for (int i = 0; i < contador; i++) {
				PnlDetalleProceso panel = (PnlDetalleProceso) jTbpDetalles.getComponent(i);
				if (panel.getProceso().id() == idProceso) {
					panel.actualizarTablas(idProceso);
					jTbpDetalles.setSelectedIndex(i);
					encontro = true;
				}
			}
			if (!encontro) {
				PnlDetalleProceso detalle = new PnlDetalleProceso(idProceso);
				this.jTbpDetalles.add(detalle);
				this.jTbpDetalles.setTitleAt(this.contador, detalle.getNombreProceso());
				jTbpDetalles.setSelectedIndex(contador);
				this.contador++;
			}
		}
	}

	public void ultimaPestana() {
		if (jTbpDetalles.getTabCount() == 0) {
			jTbpDetalles.setSelectedIndex(instancia.jTbpDetalles.getTabCount() - 1);
		} else {
			jTbpDetalles.setSelectedIndex(instancia.jTbpDetalles.getTabCount());
		}
	}

	/**
	 * Creates new form IntDetalleProceso
	 */
	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTbpDetalles = new javax.swing.JTabbedPane();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Cerrar Pestaña");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTbpDetalles, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(839, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTbpDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		// TODO add your handling code here:
		Component seleccionado = this.jTbpDetalles.getSelectedComponent();
		this.jTbpDetalles.remove(seleccionado);
		this.contador--;
    }//GEN-LAST:event_jButton1ActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(IntDetalleProceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(IntDetalleProceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(IntDetalleProceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(IntDetalleProceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				IntDetalleProceso ventana = IntDetalleProceso._getVentana();
				ventana.agregarDetalle(11);
				IntDetalleProceso ventana2 = IntDetalleProceso._getVentana();
				ventana2.agregarDetalle(18);

			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JTabbedPane jTbpDetalles;
    // End of variables declaration//GEN-END:variables
}
