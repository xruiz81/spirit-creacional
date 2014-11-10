package com.spirit.rrhh.gui.panel;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jidesoft.combobox.*;
import com.jidesoft.swing.*;
/*
 * Created by JFormDesigner on Thu Apr 25 10:04:31 COT 2013
 */
import com.spirit.client.model.SpiritModelImpl;



/**
 * @author icarrasco
 */
public abstract class JPOrganizacion extends SpiritModelImpl {
	public JPOrganizacion() {
		initComponents();
		setName("Organizacion");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		jtpOrganizacion = new JideTabbedPane();
		panelOrganizacion = new JPanel();
		lblEmpleado = new JLabel();
		txtEmpleado = new JTextField();
		btnEmpleado = new JButton();
		lblDepartamentoEmpleado = new JLabel();
		cmbDepartamentoEmpleado = new JComboBox();
		lblCargo = new JLabel();
		cmbCargo = new JComboBox();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		lblDescripcion = new JLabel();
		spTxtObservacion = new JScrollPane();
		txtDescripcion = new JTextArea();
		lblArchivo = new JLabel();
		txtArchivo = new JTextField();
		btnArchivo = new JButton();
		btnVisualizarArchivo = new JButton();
		panel1 = new JPanel();
		btnAgregarDetalle = new JButton();
		btnActualizarDetalle = new JButton();
		btnEliminarDetalle = new JButton();
		spTblOrganizacion = new JScrollPane();
		tblOrganizacion = new JTable();
		panelReporte = new JPanel();
		lblOficina = new JLabel();
		cmbOficina = new JComboBox();
		lblDepartamento = new JLabel();
		cmbDepartamento = new JComboBox();
		btnConsultar = new JButton();
		spTblVacacionesReporte = new JScrollPane();
		tblOrganizacionReporte = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Hoja de Valores");
		setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== jtpOrganizacion ========
		{

			//======== panelOrganizacion ========
			{
				panelOrganizacion.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(10)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(100)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(50)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(20)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(50)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(70), FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(10)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC
					},
					new RowSpec[] {
						new RowSpec(Sizes.dluY(10)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(12)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(22)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.DLUY6),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(10))
					}));

				//---- lblEmpleado ----
				lblEmpleado.setText("Empleado: ");
				panelOrganizacion.add(lblEmpleado, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtEmpleado ----
				txtEmpleado.setEditable(false);
				panelOrganizacion.add(txtEmpleado, cc.xywh(5, 3, 5, 1));
				panelOrganizacion.add(btnEmpleado, cc.xywh(11, 3, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

				//---- lblDepartamentoEmpleado ----
				lblDepartamentoEmpleado.setText("Departamento:");
				panelOrganizacion.add(lblDepartamentoEmpleado, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelOrganizacion.add(cmbDepartamentoEmpleado, cc.xywh(5, 5, 5, 1));

				//---- lblCargo ----
				lblCargo.setText("Cargo:");
				panelOrganizacion.add(lblCargo, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelOrganizacion.add(cmbCargo, cc.xywh(5, 7, 5, 1));

				//---- lblFechaInicio ----
				lblFechaInicio.setText("Fecha Inicio:");
				panelOrganizacion.add(lblFechaInicio, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelOrganizacion.add(cmbFechaInicio, cc.xy(5, 9));

				//---- lblFechaFin ----
				lblFechaFin.setText("Fecha Fin:");
				panelOrganizacion.add(lblFechaFin, cc.xywh(9, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelOrganizacion.add(cmbFechaFin, cc.xywh(11, 9, 5, 1));

				//---- lblDescripcion ----
				lblDescripcion.setText("Descripci\u00f3n:");
				panelOrganizacion.add(lblDescripcion, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//======== spTxtObservacion ========
				{
					spTxtObservacion.setViewportView(txtDescripcion);
				}
				panelOrganizacion.add(spTxtObservacion, cc.xywh(5, 11, 11, 3));

				//---- lblArchivo ----
				lblArchivo.setText("Archivo:");
				panelOrganizacion.add(lblArchivo, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtArchivo ----
				txtArchivo.setEditable(false);
				panelOrganizacion.add(txtArchivo, cc.xywh(5, 17, 11, 1));
				panelOrganizacion.add(btnArchivo, cc.xywh(17, 17, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
				panelOrganizacion.add(btnVisualizarArchivo, cc.xywh(19, 17, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//======== panel1 ========
				{
					panel1.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
						},
						RowSpec.decodeSpecs("default")));

					//---- btnAgregarDetalle ----
					btnAgregarDetalle.setText("A");
					panel1.add(btnAgregarDetalle, cc.xy(1, 1));

					//---- btnActualizarDetalle ----
					btnActualizarDetalle.setText("U");
					panel1.add(btnActualizarDetalle, cc.xy(3, 1));

					//---- btnEliminarDetalle ----
					btnEliminarDetalle.setText("D");
					panel1.add(btnEliminarDetalle, cc.xy(5, 1));
				}
				panelOrganizacion.add(panel1, cc.xywh(3, 25, 3, 1));

				//======== spTblOrganizacion ========
				{

					//---- tblOrganizacion ----
					tblOrganizacion.setPreferredScrollableViewportSize(new Dimension(450, 140));
					tblOrganizacion.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, "", null, "", null},
						},
						new String[] {
							"Departamento", "Cargo", "Fecha Inicio", "Fecha Fin", "Observaci\u00f3n", "Archivo"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, false, false, false, false, false
						};
						@Override
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					spTblOrganizacion.setViewportView(tblOrganizacion);
				}
				panelOrganizacion.add(spTblOrganizacion, cc.xywh(3, 27, 19, 3));
			}
			jtpOrganizacion.addTab("Organizacion", panelOrganizacion);


			//======== panelReporte ========
			{
				panelReporte.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(10)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(130)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(70)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(70), FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(10))
					},
					new RowSpec[] {
						new RowSpec(Sizes.dluY(10)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.DLUY6),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(10))
					}));

				//---- lblOficina ----
				lblOficina.setText("Oficina:");
				panelReporte.add(lblOficina, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelReporte.add(cmbOficina, cc.xy(5, 3));

				//---- lblDepartamento ----
				lblDepartamento.setText("Departamento:");
				panelReporte.add(lblDepartamento, cc.xy(3, 5));
				panelReporte.add(cmbDepartamento, cc.xy(5, 5));

				//---- btnConsultar ----
				btnConsultar.setText("Consultar");
				panelReporte.add(btnConsultar, cc.xy(9, 5));

				//======== spTblVacacionesReporte ========
				{

					//---- tblOrganizacionReporte ----
					tblOrganizacionReporte.setPreferredScrollableViewportSize(new Dimension(450, 140));
					tblOrganizacionReporte.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, null, null, null, null},
						},
						new String[] {
							"Empleado", "Fecha Ingreso", "Oficina", "Departamento", "D\u00edas disfrutados", "Saldo de d\u00edas"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, true, false, false, false, false
						};
						@Override
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					spTblVacacionesReporte.setViewportView(tblOrganizacionReporte);
				}
				panelReporte.add(spTblVacacionesReporte, cc.xywh(3, 9, 11, 3));
			}
			jtpOrganizacion.addTab("Reportes", panelReporte);

		}
		add(jtpOrganizacion, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JideTabbedPane jtpOrganizacion;
	private JPanel panelOrganizacion;
	private JLabel lblEmpleado;
	private JTextField txtEmpleado;
	private JButton btnEmpleado;
	private JLabel lblDepartamentoEmpleado;
	private JComboBox cmbDepartamentoEmpleado;
	private JLabel lblCargo;
	private JComboBox cmbCargo;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JLabel lblDescripcion;
	private JScrollPane spTxtObservacion;
	private JTextArea txtDescripcion;
	private JLabel lblArchivo;
	private JTextField txtArchivo;
	private JButton btnArchivo;
	private JButton btnVisualizarArchivo;
	private JPanel panel1;
	private JButton btnAgregarDetalle;
	private JButton btnActualizarDetalle;
	private JButton btnEliminarDetalle;
	private JScrollPane spTblOrganizacion;
	private JTable tblOrganizacion;
	private JPanel panelReporte;
	private JLabel lblOficina;
	private JComboBox cmbOficina;
	private JLabel lblDepartamento;
	private JComboBox cmbDepartamento;
	private JButton btnConsultar;
	private JScrollPane spTblVacacionesReporte;
	private JTable tblOrganizacionReporte;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JideTabbedPane getJtpOrganizacion() {
		return jtpOrganizacion;
	}

	public void setJtpOrganizacion(JideTabbedPane jtpOrganizacion) {
		this.jtpOrganizacion = jtpOrganizacion;
	}

	public JPanel getPanelOrganizacion() {
		return panelOrganizacion;
	}

	public void setPanelOrganizacion(JPanel panelOrganizacion) {
		this.panelOrganizacion = panelOrganizacion;
	}

	public JLabel getLblEmpleado() {
		return lblEmpleado;
	}

	public void setLblEmpleado(JLabel lblEmpleado) {
		this.lblEmpleado = lblEmpleado;
	}

	public JTextField getTxtEmpleado() {
		return txtEmpleado;
	}

	public void setTxtEmpleado(JTextField txtEmpleado) {
		this.txtEmpleado = txtEmpleado;
	}

	public JButton getBtnEmpleado() {
		return btnEmpleado;
	}

	public void setBtnEmpleado(JButton btnEmpleado) {
		this.btnEmpleado = btnEmpleado;
	}

	public JLabel getLblDepartamentoEmpleado() {
		return lblDepartamentoEmpleado;
	}

	public void setLblDepartamentoEmpleado(JLabel lblDepartamentoEmpleado) {
		this.lblDepartamentoEmpleado = lblDepartamentoEmpleado;
	}

	public JComboBox getCmbDepartamentoEmpleado() {
		return cmbDepartamentoEmpleado;
	}

	public void setCmbDepartamentoEmpleado(JComboBox cmbDepartamentoEmpleado) {
		this.cmbDepartamentoEmpleado = cmbDepartamentoEmpleado;
	}

	public JLabel getLblCargo() {
		return lblCargo;
	}

	public void setLblCargo(JLabel lblCargo) {
		this.lblCargo = lblCargo;
	}

	public JComboBox getCmbCargo() {
		return cmbCargo;
	}

	public void setCmbCargo(JComboBox cmbCargo) {
		this.cmbCargo = cmbCargo;
	}

	public JLabel getLblFechaInicio() {
		return lblFechaInicio;
	}

	public void setLblFechaInicio(JLabel lblFechaInicio) {
		this.lblFechaInicio = lblFechaInicio;
	}

	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}

	public void setCmbFechaInicio(DateComboBox cmbFechaInicio) {
		this.cmbFechaInicio = cmbFechaInicio;
	}

	public JLabel getLblFechaFin() {
		return lblFechaFin;
	}

	public void setLblFechaFin(JLabel lblFechaFin) {
		this.lblFechaFin = lblFechaFin;
	}

	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public void setCmbFechaFin(DateComboBox cmbFechaFin) {
		this.cmbFechaFin = cmbFechaFin;
	}

	public JLabel getLblDescripcion() {
		return lblDescripcion;
	}

	public void setLblDescripcion(JLabel lblDescripcion) {
		this.lblDescripcion = lblDescripcion;
	}

	public JScrollPane getSpTxtObservacion() {
		return spTxtObservacion;
	}

	public void setSpTxtObservacion(JScrollPane spTxtObservacion) {
		this.spTxtObservacion = spTxtObservacion;
	}

	public JTextArea getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(JTextArea txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public JLabel getLblArchivo() {
		return lblArchivo;
	}

	public void setLblArchivo(JLabel lblArchivo) {
		this.lblArchivo = lblArchivo;
	}

	public JTextField getTxtArchivo() {
		return txtArchivo;
	}

	public void setTxtArchivo(JTextField txtArchivo) {
		this.txtArchivo = txtArchivo;
	}

	public JButton getBtnArchivo() {
		return btnArchivo;
	}

	public void setBtnArchivo(JButton btnArchivo) {
		this.btnArchivo = btnArchivo;
	}

	public JButton getBtnVisualizarArchivo() {
		return btnVisualizarArchivo;
	}

	public void setBtnVisualizarArchivo(JButton btnVisualizarArchivo) {
		this.btnVisualizarArchivo = btnVisualizarArchivo;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public void setPanel1(JPanel panel1) {
		this.panel1 = panel1;
	}

	public JButton getBtnAgregarDetalle() {
		return btnAgregarDetalle;
	}

	public void setBtnAgregarDetalle(JButton btnAgregarDetalle) {
		this.btnAgregarDetalle = btnAgregarDetalle;
	}

	public JButton getBtnActualizarDetalle() {
		return btnActualizarDetalle;
	}

	public void setBtnActualizarDetalle(JButton btnActualizarDetalle) {
		this.btnActualizarDetalle = btnActualizarDetalle;
	}

	public JButton getBtnEliminarDetalle() {
		return btnEliminarDetalle;
	}

	public void setBtnEliminarDetalle(JButton btnEliminarDetalle) {
		this.btnEliminarDetalle = btnEliminarDetalle;
	}

	public JScrollPane getSpTblOrganizacion() {
		return spTblOrganizacion;
	}

	public void setSpTblOrganizacion(JScrollPane spTblOrganizacion) {
		this.spTblOrganizacion = spTblOrganizacion;
	}

	public JTable getTblOrganizacion() {
		return tblOrganizacion;
	}

	public void setTblOrganizacion(JTable tblOrganizacion) {
		this.tblOrganizacion = tblOrganizacion;
	}

	public JPanel getPanelReporte() {
		return panelReporte;
	}

	public void setPanelReporte(JPanel panelReporte) {
		this.panelReporte = panelReporte;
	}

	public JLabel getLblOficina() {
		return lblOficina;
	}

	public void setLblOficina(JLabel lblOficina) {
		this.lblOficina = lblOficina;
	}

	public JComboBox getCmbOficina() {
		return cmbOficina;
	}

	public void setCmbOficina(JComboBox cmbOficina) {
		this.cmbOficina = cmbOficina;
	}

	public JLabel getLblDepartamento() {
		return lblDepartamento;
	}

	public void setLblDepartamento(JLabel lblDepartamento) {
		this.lblDepartamento = lblDepartamento;
	}

	public JComboBox getCmbDepartamento() {
		return cmbDepartamento;
	}

	public void setCmbDepartamento(JComboBox cmbDepartamento) {
		this.cmbDepartamento = cmbDepartamento;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public JScrollPane getSpTblVacacionesReporte() {
		return spTblVacacionesReporte;
	}

	public void setSpTblVacacionesReporte(JScrollPane spTblVacacionesReporte) {
		this.spTblVacacionesReporte = spTblVacacionesReporte;
	}

	public JTable getTblOrganizacionReporte() {
		return tblOrganizacionReporte;
	}

	public void setTblOrganizacionReporte(JTable tblOrganizacionReporte) {
		this.tblOrganizacionReporte = tblOrganizacionReporte;
	}
	
	
}
