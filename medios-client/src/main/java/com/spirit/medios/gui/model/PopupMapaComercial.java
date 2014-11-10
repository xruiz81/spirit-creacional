package com.spirit.medios.gui.model;

import com.jidesoft.popup.JidePopup;

public class PopupMapaComercial extends JidePopup {
/*	

	PlanMedioMesIf planMedioMesIf;
	int totalCuñas;
	String estadoPlanMedio;
	Vector mapaComercialColeccion = new Vector();
	
	public PopupMapaComercial(PlanMedioMesIf planMedioMes,String estadoPM) {
		initComponents(planMedioMes);
		setName("Mapa Comercial");
		
		//Recibo el objeto Plan MedioMes y cliente para ver la fecha dentro de la cual puede estar el Mapa COmercial
		planMedioMesIf = planMedioMes;
		estadoPlanMedio = estadoPM;

		//Seteo la fecha del DateSpinner a la del Plan Medio Mes recibido 
		getDsMesAnoMapaComercial().setValue(planMedioMes.getMes());
		
		//Mando a llenar las filas del vector con nulo
		for(int i=0;i<getTspDiasDelMes().getTable().getRowHeaderTable().getRowCount();i++)
			mapaComercialColeccion.add(null);
		
	}
	
	public PopupMapaComercial(PlanMedioMesIf planMedioMes,Vector mapaComercialColeccion,String estadoPM) {
		initComponents(planMedioMes);
		setName("Mapa Comercial");

		//Recibo el objeto Plan MedioMes,cliente  y la coleccion de mapa comercial para mostrarlos en la tabla
		this.mapaComercialColeccion = mapaComercialColeccion;
		planMedioMesIf = planMedioMes;
		estadoPlanMedio = estadoPM;
		
		//Seteo la fecha del DateSpinner a la del Plan Medio Mes recibido 
		getDsMesAnoMapaComercial().setValue(planMedioMes.getMes());
		
		cargarMapaComercialColeccion();
		
	}
	
	private void cargarMapaComercialColeccion(){
		//Barro uno por los regsitros de la coleccion de mapa comercial y los añado a la tabla 
		for(int i= 0; i < mapaComercialColeccion.size(); i++){
			//Leo el objeto del arreglo que va a ser insertado en la tabla Mapa CoOmercial
			MapaComercialIf mapaComercialTemp = (MapaComercialIf) mapaComercialColeccion.get(i);

			//Veo si el objeto recupeardo no este nulo, si es asi me salto una fila
			if (mapaComercialTemp!=null){
				//Extraigo la frecuencia del objeto
				String frecuencia = mapaComercialTemp.getFrecuencia().toString();
				
				getTspDiasDelMes().getTable().getMainTable().setValueAt(frecuencia,i,0);
			}
		}
	}
	
	
	private void initComponents(PlanMedioMesIf planMedioMes) {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		contentPane = new JPanel();
		panelBarraTitulo = new JPanel();
		btnAceptar = new JideButton();
		lblTitulo = new JLabel();
		goodiesFormsSeparator = compFactory.createSeparator("");
		panelForm = new JPanel();
		lblMesAño = new JLabel();
		dsMesAnoMapaComercial = new DateSpinner("MMM/yyyy");
		String[] headerTblDiasDelMes = new String[]{"Dia","Frecuencia"};
		tspDiasDelMes = new MySheetTableMapaComercialScrollPane(headerTblDiasDelMes,planMedioMes.getFechaini(),planMedioMes.getFechafin(),1,1,115);
		CellConstraints cc = new CellConstraints();

		//======== this ========
		
			setMaximumSize(new Dimension(300, 300));
			setAttachable(true);
			setMovable(true);
			setLayout(new FormLayout(
				"default",
				"default"));
			
			//======== contentPane ========
			{
				contentPane.setPreferredSize(new Dimension(300, 300));
				contentPane.setMinimumSize(new Dimension(300, 300));
				contentPane.setMaximumSize(new Dimension(300, 300));
				contentPane.setLayout(new FormLayout(
					"default",
					"fill:pref, 10dlu, default, 10dlu"));
				
				//======== panelBarraTitulo ========
				{
					panelBarraTitulo.setLayout(new FormLayout(
						"default, default:grow, default",
						"fill:default"));
					
					//---- lblTitulo ----
					lblTitulo.setText("Mapa Comercial");
					lblTitulo.setBackground(UIManager.getColor("Button.light"));
					lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
					lblTitulo.setOpaque(true);
					panelBarraTitulo.add(lblTitulo, cc.xy(2, 1));
					
					//---- btnAceptar ----
					btnAceptar.setBackground(UIManager.getColor("Button.light"));
					btnAceptar.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/saveRecord.png"));
					btnAceptar.setToolTipText("Asignar Mapa Comercial a Detalle");
					panelBarraTitulo.add(btnAceptar, cc.xy(3, 1));
				}
				contentPane.add(panelBarraTitulo, cc.xy(1, 1));
				contentPane.add(goodiesFormsSeparator, cc.xywh(1, 2, 1, 1, CellConstraints.DEFAULT, CellConstraints.TOP));
				
				
				//======== panelForm ========
				{
					panelForm.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(10)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(10))
						},
						new RowSpec[] {
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(10)),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.NO_GROW)
						}));
					
					//---- lblMesAño ----
					lblMesAño.setText("Mes/A\u00f1o:");
					dsMesAnoMapaComercial.setEnabled(false);
					panelForm.add(lblMesAño, cc.xy(3, 1));
					panelForm.add(dsMesAnoMapaComercial, cc.xy(5, 1));
					
					//======== tspDiasDelMes ========
					{
						tspDiasDelMes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
						tspDiasDelMes.setVerifyInputWhenFocusTarget(true);
						tspDiasDelMes.setAutoscrolls(false);
					}
					panelForm.add(tspDiasDelMes.getTable(), cc.xywh(3, 5, 3, 1));
				}
				contentPane.add(panelForm, cc.xy(1, 3));
			}
			add(contentPane, cc.xy(1, 1));
			setDefaultFocusComponent(contentPane);
			// JFormDesigner - End of component initialization  //GEN-END:initComponents
		}

	
	

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel contentPane;
	private JPanel panelBarraTitulo;
	private JideButton btnAceptar;
	private JLabel lblTitulo;
	private JComponent goodiesFormsSeparator;
	private JPanel panelForm;
	private JLabel lblMesAño;
	private DateSpinner dsMesAnoMapaComercial;
	private MySheetTableMapaComercialScrollPane tspDiasDelMes;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	public DateSpinner getDsMesAnoMapaComercial() {
		return dsMesAnoMapaComercial;
	}

	public void setDsMesAnoMapaComercial(DateSpinner dsMesAnoMapaComercial) {
		this.dsMesAnoMapaComercial = dsMesAnoMapaComercial;
	}

	public Vector getMapaComercialColeccion() {
		return mapaComercialColeccion;
	}

	public void setMapaComercialColeccion(Vector mapaComercialColeccion) {
		this.mapaComercialColeccion = mapaComercialColeccion;
	}

	public int getTotalCuñas() {
		return totalCuñas;
	}

	public void setTotalCuñas(int totalCuñas) {
		this.totalCuñas = totalCuñas;
	}

	public JideButton getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(JideButton btnAceptar) {
		this.btnAceptar = btnAceptar;
	}

	public MySheetTableMapaComercialScrollPane getTspDiasDelMes() {
		return tspDiasDelMes;
	}

	public void setTspDiasDelMes(MySheetTableMapaComercialScrollPane tspDiasDelMes) {
		this.tspDiasDelMes = tspDiasDelMes;
	}*/
}