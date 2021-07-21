/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidades.Usuario;
import gestor.Conexion;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carri
 */
public class IntActivosUsuario extends javax.swing.JFrame {
	DefaultTableModel modeloTabla; 	
	Conexion conexion; 
	Usuario usuario; 
	/**
	 * Creates new form IntActivosUsuario
	 */
	public IntActivosUsuario(Usuario usuario) {
		initComponents();
		this.usuario = usuario; 
		conexion = new Conexion(); 
		this.cargarTabla();
		cargarDatosUsuario();
	}

	private IntActivosUsuario() {
		initComponents();
	}

	private void cargarDatosUsuario(){
		jLblCedula.setText(usuario.getCedula());
		jLblNombre.setText(usuario.getNombre());
		jLblApellido.setText(usuario.getApellido());
	}

	private void cargarTabla(){
		String [] titulos = {"ID", "ID ACTIVO", "NOMBRE", "DESCRIPCION"}; 
		this.modeloTabla = new DefaultTableModel(null, titulos); 
		try { 
			String [][] activos = conexion.getActivosUsuarios(usuario.getCedula());
			for(String [] activo : activos){ 
				this.modeloTabla.addRow(activo);
			}
			jTblActivos.setModel(this.modeloTabla);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error: No se ha podido comunicar con el servidor");
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTblActivos = new javax.swing.JTable();
        jLblCedula = new javax.swing.JLabel();
        jLblNombre = new javax.swing.JLabel();
        jLblApellido = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTblActivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTblActivos);

        jLblCedula.setText("jLabel1");

        jLblNombre.setText("jLabel2");

        jLblApellido.setText("jLabel3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLblCedula)
                        .addGap(69, 69, 69)
                        .addComponent(jLblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(jLblApellido))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblCedula)
                    .addComponent(jLblNombre)
                    .addComponent(jLblApellido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
			java.util.logging.Logger.getLogger(IntActivosUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(IntActivosUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(IntActivosUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(IntActivosUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				Usuario usuario = new Usuario("123","Richard","Carrion");
				new IntActivosUsuario(usuario).setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLblApellido;
    private javax.swing.JLabel jLblCedula;
    private javax.swing.JLabel jLblNombre;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblActivos;
    // End of variables declaration//GEN-END:variables
}
