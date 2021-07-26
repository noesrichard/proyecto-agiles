/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import componentes.PnlDetalleProceso;
import componentes.TablaActivosProceso;
import componentes.TablaUsuarios;
import gestor.Conexion;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author carri
 */
public class IntValidarActivo extends javax.swing.JFrame {

	Conexion conexion;
	TablaActivosProceso tablaActivos;
	int posicion, idProceso;
	IntDetalleProceso intDetalle;
	PnlDetalleProceso pnlDetalle; 

	/**
	 * Creates new form IntValidarActivo
	 */
	public IntValidarActivo(IntDetalleProceso intDetalle, TablaActivosProceso tabla, int idProceso, int posicion) {
		initComponents();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.conexion = new Conexion();

		this.idProceso = idProceso;
		this.tablaActivos = tabla;
		this.posicion = posicion;

		this.intDetalle = intDetalle;

		this.jTxaObservacion.setEditable(false);
		this.jRbtCorrecto.setSelected(true);

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(jRbtCorrecto);
		grupo.add(jRbtObservacion);

		cargarDatosActivo();
	}

	public IntValidarActivo(PnlDetalleProceso intDetalle, TablaActivosProceso tabla, int idProceso, int posicion) {
		initComponents();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.conexion = new Conexion();
		this.setTitle("Validar Activo");
		this.idProceso = idProceso;
		this.tablaActivos = tabla;
		this.posicion = posicion;

		this.pnlDetalle = intDetalle;

		this.jTxaObservacion.setEditable(false);
		this.jRbtCorrecto.setSelected(true);

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(jRbtCorrecto);
		grupo.add(jRbtObservacion);

		cargarDatosActivo();
	}

	public void cargarDatosActivo() {
		String idActivo = (String) tablaActivos.getValueAt(posicion, 0);
		String nombreActivo = (String) tablaActivos.getValueAt(posicion, 2);
		String cedula = (String) tablaActivos.getValueAt(posicion, 4);
		String nombreUsuario = (String) tablaActivos.getValueAt(posicion, 5);
		String apellido = (String) tablaActivos.getValueAt(posicion, 6);
		jLblID.setText(idActivo);
		jLblNombreActivo.setText(nombreActivo);

		jLblCedula.setText(cedula);
		jLblNombreUsuario.setText(nombreUsuario);
		jLblApellido.setText(apellido);
	}

	public void validarActivo() {
		cargarDatosActivo();
		String idActivo = (String) tablaActivos.getValueAt(posicion, 0);
		String estadoActivo;
		String observacionActivo;
		if (jRbtCorrecto.isSelected()) {
			estadoActivo = "CORRECTO";
			observacionActivo = "";
		} else {
			estadoActivo = "OBSERVACION";
			observacionActivo = jTxaObservacion.getText();
		}
		try {
			this.conexion.validarActivo(idActivo, idProceso, estadoActivo, observacionActivo);
		} catch (Exception ex) {
			Logger.getLogger(IntValidarActivo.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void validarYSiguiente() {
		System.out.println(posicion);
		validarActivo();
		this.posicion += 1;
		if (this.posicion == tablaActivos.getRowCount()) {
			JOptionPane.showMessageDialog(null, "Ha terminado el proceso de validacion!");
			this.tablaActivos.cargarTabla(String.valueOf(idProceso));
			this.dispose();
		} else {
			cargarDatosActivo();
			this.jRbtCorrecto.setSelected(true);
			this.jTxaObservacion.setText("");
			this.jTxaObservacion.setEditable(false);
		}
	}

	public IntValidarActivo() {
		initComponents();
		this.jRbtCorrecto.setSelected(true);
		this.jTxaObservacion.setEditable(false);
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(jRbtCorrecto);
		grupo.add(jRbtObservacion);
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radioButtonGroup = new javax.swing.ButtonGroup();
        jRbtCorrecto = new javax.swing.JRadioButton();
        jRbtObservacion = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTxaObservacion = new javax.swing.JTextArea();
        jBtnCancelar = new javax.swing.JButton();
        jBtnGuardar = new javax.swing.JButton();
        jBtnSiguiente = new javax.swing.JButton();
        jLblID = new javax.swing.JLabel();
        jLblCedula = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLblNombreActivo = new javax.swing.JLabel();
        jLblNombreUsuario = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLblApellido = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jRbtCorrecto.setText("Correcto");
        jRbtCorrecto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRbtCorrectoItemStateChanged(evt);
            }
        });
        jRbtCorrecto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRbtCorrectoMouseClicked(evt);
            }
        });
        jRbtCorrecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRbtCorrectoActionPerformed(evt);
            }
        });

        jRbtObservacion.setText("Observacion");
        jRbtObservacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRbtObservacionMouseClicked(evt);
            }
        });

        jTxaObservacion.setColumns(20);
        jTxaObservacion.setRows(5);
        jScrollPane1.setViewportView(jTxaObservacion);

        jBtnCancelar.setText("Cancelar");
        jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelarActionPerformed(evt);
            }
        });

        jBtnGuardar.setText("Guardar y Salir");
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGuardarActionPerformed(evt);
            }
        });

        jBtnSiguiente.setText("Guardar y Siguiente");
        jBtnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSiguienteActionPerformed(evt);
            }
        });

        jLblID.setText("ID Activo");

        jLblCedula.setText("Cedula Usuario");

        jLabel1.setText("ID Activo:");

        jLabel2.setText("Cedula:");

        jLabel3.setText("Nombre:");

        jLabel4.setText("Nombre:");

        jLabel5.setText("Activo");

        jLabel6.setText("Usuario");

        jLblNombreActivo.setText("jLabel7");

        jLblNombreUsuario.setText("jLabel8");

        jLabel9.setText("Apellido:");

        jLblApellido.setText("jLabel10");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLblNombreActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(39, 39, 39)
                                                .addComponent(jLabel5)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLblID)
                                        .addGap(50, 50, 50)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLblNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLblCedula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addComponent(jLabel6))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLblApellido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jRbtCorrecto)
                                        .addGap(63, 63, 63)
                                        .addComponent(jRbtObservacion))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jBtnCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnSiguiente)))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblID)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLblCedula))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLblNombreActivo)
                    .addComponent(jLblNombreUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLblApellido))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRbtCorrecto)
                    .addComponent(jRbtObservacion))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnCancelar)
                    .addComponent(jBtnGuardar)
                    .addComponent(jBtnSiguiente))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
		// TODO add your handling code here:
		this.validarActivo();
		this.tablaActivos.cargarTabla(String.valueOf(idProceso));
		this.pnlDetalle.setProceso(this.tablaActivos.getProceso());
		this.dispose();
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    private void jBtnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSiguienteActionPerformed
		// TODO add your handling code here
		this.validarYSiguiente();
		this.tablaActivos.cargarTabla(String.valueOf(idProceso));
		this.pnlDetalle.setProceso(this.tablaActivos.getProceso());
    }//GEN-LAST:event_jBtnSiguienteActionPerformed

    private void jRbtCorrectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRbtCorrectoActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_jRbtCorrectoActionPerformed

    private void jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarActionPerformed
		// TODO add your handling code here:
		this.tablaActivos.cargarTabla(String.valueOf(idProceso));
		this.dispose();
    }//GEN-LAST:event_jBtnCancelarActionPerformed

    private void jRbtCorrectoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRbtCorrectoItemStateChanged
    }//GEN-LAST:event_jRbtCorrectoItemStateChanged

    private void jRbtCorrectoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRbtCorrectoMouseClicked
		// TODO add your handling code here:
		this.jTxaObservacion.setEditable(false);
		this.jTxaObservacion.setText("");
    }//GEN-LAST:event_jRbtCorrectoMouseClicked

    private void jRbtObservacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRbtObservacionMouseClicked
		// TODO add your handling code here:
		jTxaObservacion.setEditable(true);
    }//GEN-LAST:event_jRbtObservacionMouseClicked

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
			java.util.logging.Logger.getLogger(IntValidarActivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(IntValidarActivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(IntValidarActivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(IntValidarActivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new IntValidarActivo().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnCancelar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JButton jBtnSiguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLblApellido;
    private javax.swing.JLabel jLblCedula;
    private javax.swing.JLabel jLblID;
    private javax.swing.JLabel jLblNombreActivo;
    private javax.swing.JLabel jLblNombreUsuario;
    private javax.swing.JRadioButton jRbtCorrecto;
    private javax.swing.JRadioButton jRbtObservacion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTxaObservacion;
    private javax.swing.ButtonGroup radioButtonGroup;
    // End of variables declaration//GEN-END:variables
}
