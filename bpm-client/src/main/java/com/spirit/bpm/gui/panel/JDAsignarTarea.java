/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JDAsignarTarea.java
 *
 * Created on 14/06/2010, 12:53:15
 */

package com.spirit.bpm.gui.panel;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import com.spirit.bpm.gui.helper.UsuarioListRender;
import com.spirit.bpm.process.elements.Tarea;
import com.spirit.bpm.process.elements.UserBpm;

/**
 * 
 * @author Administrador
 */
public class JDAsignarTarea extends javax.swing.JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** A return status code - returned if Cancel button has been pressed */
	public static final int RET_CANCEL = 0;
	/** A return status code - returned if OK button has been pressed */
	public static final int RET_OK = 1;
	private UserBpm user;
	private Tarea tarea;

	/** Creates new form JDAsignarTarea */
	public JDAsignarTarea(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		setTitle("Asignar Tarea");
	}

	public void setUserList(List<UserBpm> userBpmList) {
		cmbUsuario.setRenderer(new UsuarioListRender());
		cmbUsuario.setModel(new DefaultComboBoxModel(userBpmList.toArray()));
	}

	/** @return the return status of this dialog - one of RET_OK or RET_CANCEL */
	public int getReturnStatus() {
		return returnStatus;
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		okButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		cmbUsuario = new javax.swing.JComboBox();
		lblTarea = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				closeDialog(evt);
			}
		});

		okButton.setText("OK");
		okButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				okButtonActionPerformed(evt);
			}
		});

		cancelButton.setText("Cancel");
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});

		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel1.setText("Asignar Tarea:");

		cmbUsuario.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4" }));

		lblTarea.setText("Compra");

		jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
		jLabel3.setText("A:");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																layout
																		.createSequentialGroup()
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel1)
																						.addComponent(
																								okButton,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								67,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								layout
																										.createSequentialGroup()
																										.addGap(
																												134,
																												134,
																												134)
																										.addComponent(
																												cancelButton,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												73,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								layout
																										.createSequentialGroup()
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												lblTarea))))
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel3)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				cmbUsuario,
																				0,
																				250,
																				Short.MAX_VALUE)))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(lblTarea))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																cmbUsuario,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel3))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																cancelButton)
														.addComponent(okButton))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_okButtonActionPerformed
		doClose(RET_OK);
	}// GEN-LAST:event_okButtonActionPerformed

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancelButtonActionPerformed
		doClose(RET_CANCEL);
	}// GEN-LAST:event_cancelButtonActionPerformed

	/** Closes the dialog */
	private void closeDialog(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_closeDialog
		doClose(RET_CANCEL);
	}// GEN-LAST:event_closeDialog

	private void doClose(int retStatus) {
		returnStatus = retStatus;
		user = (UserBpm) cmbUsuario.getModel().getSelectedItem();
		setVisible(false);
		dispose();
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	protected javax.swing.JButton cancelButton;
	protected javax.swing.JComboBox cmbUsuario;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel3;
	protected javax.swing.JLabel lblTarea;
	protected javax.swing.JButton okButton;
	// End of variables declaration//GEN-END:variables

	private int returnStatus = RET_CANCEL;

	public UserBpm getUser() {
		return user;
	}

	public Tarea getTarea() {
		return tarea;
	}

	public void setTarea(Tarea tarea) {
		lblTarea.setText(tarea.getInfo());
	}
}
