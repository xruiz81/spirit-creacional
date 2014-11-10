package com.spirit.medios.gui.model;

import com.jidesoft.popup.JidePopup;

public class PopupMapaProveedor extends JidePopup {
/*	
	private static final long serialVersionUID = -3422896718837469377L;
	TipoProveedorIf tipoProveedorIf;
	PlanMedioMesIf planMedioMesIf;
	ClienteOficinaIf proveedorIf;
	Vector detallePlanMedioColeccion;
	Map programasByProveedorMap = new HashMap();
	Map mapasComercialesToDetallesMap;
	private DecimalFormat formatoDecimal=new DecimalFormat("#,##0.00");
	
	
	public PopupMapaProveedor(TipoProveedorIf tipoProveedor,PlanMedioMesIf planMedioMes,Vector detallePlanMedio,Map mapasComercialesToDetalles) {
		cmbProveedor = new JComboBox();
		cmbPrograma = new JComboBox();
		cmbTipoConsulta = new JComboBox();
		tipoProveedorIf = tipoProveedor;
		planMedioMesIf = planMedioMes;
		detallePlanMedioColeccion = detallePlanMedio;
		mapasComercialesToDetallesMap = mapasComercialesToDetalles; 
		tspDiasDelMes = new MySheetTableMapaProveedorScrollPane(planMedioMesIf.getFechaini(),planMedioMesIf.getFechafin(),tipoProveedorIf,"diario");
		tspSemanasDelMes = new MySheetTableMapaProveedorScrollPane(planMedioMesIf.getFechaini(),planMedioMesIf.getFechafin(),tipoProveedorIf,"semanal");
		
		//Muestro el tipo de consultas que se pueden hacer
		cmbTipoConsulta.addItem("DIAS");
		cmbTipoConsulta.addItem("SEMANAS");
		
		//Mando a  cargar los comerciales que tiene ese proveedor en los detalles de plan medio recibido
		cargarProgramasByProveedor();		
	
		//Obtengo el primer proveedor del combo
		proveedorIf = (ClienteOficinaIf) cmbProveedor.getItemAt(0);
		
		//Mando a cargar los programas de primer proveedor encontrado
		Vector programasPrimerProveedorVector = new Vector();
		
		//Añado el elemnto nulo al combo de programa
		cmbPrograma.addItem(null);
				
		if(proveedorIf!=null){
			//Si el proveedor es diferete de null mando a buscar el vector de programas en el mapa 
			if(programasByProveedorMap.get(proveedorIf.getId())!=null){
				programasPrimerProveedorVector = (Vector) programasByProveedorMap.get(proveedorIf.getId());
				for(int i=0;i<programasPrimerProveedorVector.size();i++)
					cmbPrograma.addItem(programasPrimerProveedorVector.get(i).toString());	
				
			}
			
			//mando a cargar los detalles con toda la informacion del primer proveedor encontrado en la base
			mostrarProgramasAndDetallesByProveedor(null);
		}
	
		initComponents();
		setName("Mapa Proveedor");

		
		//Desabilito el de mes año
		dsMesAnoMapaProveedor.setEnabled(false);
		//Seteo la fecha del DateSpinner a la del Plan Medio Mes recibido 
		dsMesAnoMapaProveedor.setValue(planMedioMesIf.getMes());
		
		//Manado a cargar los listeners para este popup
		cargarListenersComponents();
	}
	
	
	private void cargarProgramasByProveedor(){
		//Recorro los detalles del Resumen Mes
		for(int j=0;j<detallePlanMedioColeccion.size();j++){
			PlanMedioDetalleIf planMedioDetalleTemp = (PlanMedioDetalleIf) detallePlanMedioColeccion.get(j);
			
			//Si el plan medio es diferente de nulo
			if(planMedioDetalleTemp!=null){
				Vector programasByProveedorVector = new Vector();
				
				//Si el proveedor ya esta insertado en el mapa mando a cargar el vector que contiene los comerciales
				if(programasByProveedorMap.get(planMedioDetalleTemp.getProveedorId())!=null)
					programasByProveedorVector = (Vector) programasByProveedorMap.get(planMedioDetalleTemp.getProveedorId());
				else{
					//Mano a cargar el combo de proveedores
					try {
						ClienteOficinaIf proveedorTemp = (ClienteOficinaIf) getClienteOficinaSessionService().getClienteOficina(planMedioDetalleTemp.getProveedorId());
						cmbProveedor.addItem(proveedorTemp);
					} catch (GenericBusinessException e) {
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
						e.printStackTrace();
					}
				}
					
				//Añado el programa a la coleccion
				programasByProveedorVector.addElement(planMedioDetalleTemp.getPrograma());
				
				//Guardo la coleccion de nuevo en el mapa
				programasByProveedorMap.put(planMedioDetalleTemp.getProveedorId(),programasByProveedorVector);

			}
		}

	}
	
	private void mostrarProgramasAndDetallesByProveedor(Object programaSeleccionado){
		//Contador de filas
		int contFilas=0;
		//Variable que almacanea la tabal con la que estoy trabajo segun el tipo de vista
		MySheetTableMapaProveedorScrollPane tspTablaTemp = null;
		//Variable bandera la cual condicinona si debo hacer la busqueda por proveedor o por programa
		boolean isByPrograma = false;
		
		//veo si es que ha sido enviado algun programa 
		if(programaSeleccionado!=null)
			isByPrograma = true;
		
		//Limpio el contenido de las tablas de dia y de semana
		for (int i=0;i < tspDiasDelMes.getTable().getRowHeaderTable().getRowCount(); i++){
			//Vacio los registros de la Tabla DetalleRadioTV de la parte RowHeaderTable
			for (int j=0;j < tspDiasDelMes.getTable().getRowHeaderTable().getColumnCount(); j++)
				tspDiasDelMes.getTable().getRowHeaderTable().setValueAt(null,i,j);
			for (int j=0;j < tspDiasDelMes.getTable().getMainTable().getColumnCount(); j++)
				tspDiasDelMes.getTable().getMainTable().setValueAt(null,i,j);
		}
		for (int i=0;i < tspSemanasDelMes.getTable().getRowHeaderTable().getRowCount(); i++){
			//Vacio los registros de la Tabla DetalleRadioTV de la parte RowHeaderTable
			for (int j=0;j < tspSemanasDelMes.getTable().getRowHeaderTable().getColumnCount(); j++)
				tspSemanasDelMes.getTable().getRowHeaderTable().setValueAt(null,i,j);
			for (int j=0;j < tspSemanasDelMes.getTable().getMainTable().getColumnCount(); j++)
				tspSemanasDelMes.getTable().getMainTable().setValueAt(null,i,j);
		}
		
		
		//Ontengo la tabla con la que estoy trabajando segun el tipo de vista
		if(cmbTipoConsulta.getSelectedItem().equals("DIAS"))
			tspTablaTemp = tspDiasDelMes;
		else if(cmbTipoConsulta.getSelectedItem().equals("SEMANAS"))
			tspTablaTemp = tspSemanasDelMes;
		

		
		//Recorro los detalles del resumen mes para ver cual es el primer proveedor encontrado yver cuantos programas tinee este guardado en el mapa
		for(int j=0;j<detallePlanMedioColeccion.size();j++){
			PlanMedioDetalleIf planMedioDetalleTemp = (PlanMedioDetalleIf) detallePlanMedioColeccion.get(j);
			
			//Si el plan medio es diferente de nulo
			if(planMedioDetalleTemp!=null ){
				boolean hacerBusqueda = false; 
				//Si la busqueda se hace por programa y el programa recibido es igual al del detalle O Si la busqueda se hace por proveedor y este es igual al escogido en el combo 
				if((isByPrograma && planMedioDetalleTemp.getPrograma().equals(programaSeleccionado.toString())) || (!isByPrograma && (proveedorIf.getId().compareTo(planMedioDetalleTemp.getProveedorId())==0))) 
					hacerBusqueda = true;
				
				//SI el id del Proveedor seleccionado en el combo es igual al del detalle leido, muestro la informacion y los valores del mapa
				if(hacerBusqueda){
					//Si el tipo de proveedor es de radio o  prensa construyo las columnas de la izquierda
			    	if(tipoProveedorIf.getNombre().contains("RADIO") || tipoProveedorIf.getNombre().contains("TELEVISION")){
						//Extraigo a parte los datos que voy a insertar en la tabla
						Double raiting1Temp = planMedioDetalleTemp.getRaiting1().doubleValue();
						Double raiting2Temp = planMedioDetalleTemp.getRaiting2().doubleValue();
						Double raitingPonderadoTemp = planMedioDetalleTemp.getRaitingPonderado().doubleValue();
						Double audienciaTemp = planMedioDetalleTemp.getAudiencia().doubleValue();
						int totalCuñasTemp = planMedioDetalleTemp.getTotalcunas();
						Double invTarifa =  planMedioDetalleTemp.getValorTarifa().doubleValue();
						Double invCDescto =  planMedioDetalleTemp.getValorReal().doubleValue();
						
						//Calculo los valores del TGRP
						Double tgrpGYE =  totalCuñasTemp * raiting1Temp;
						Double tgrpUIO =  totalCuñasTemp * raiting2Temp;
						Double tgrpPond =  totalCuñasTemp * raitingPonderadoTemp;
						
						Double impactos =  totalCuñasTemp * audienciaTemp;
						Double CxPR =  invCDescto / tgrpPond;
						
						Double vCuñaTarifa = invTarifa/totalCuñasTemp;
						Double vCuñaNegociada = invCDescto/totalCuñasTemp;

			    		
						//Muesto los datos de los detalles de este proveedor
						tspTablaTemp.getTable().getRowHeaderTable().setValueAt(planMedioDetalleTemp.getPrograma(),contFilas,1);
						tspTablaTemp.getTable().getRowHeaderTable().setValueAt(planMedioDetalleTemp.getHoraini(),contFilas,2);
						tspTablaTemp.getTable().getRowHeaderTable().setValueAt(formatoDecimal.format(raiting1Temp),contFilas,3);
						tspTablaTemp.getTable().getRowHeaderTable().setValueAt(formatoDecimal.format(raiting2Temp),contFilas,4);
						tspTablaTemp.getTable().getRowHeaderTable().setValueAt(formatoDecimal.format(raitingPonderadoTemp),contFilas,5);
						tspTablaTemp.getTable().getRowHeaderTable().setValueAt(formatoDecimal.format(audienciaTemp),contFilas,6);
						tspTablaTemp.getTable().getRowHeaderTable().setValueAt(formatoDecimal.format(vCuñaTarifa),contFilas,7);
						tspTablaTemp.getTable().getRowHeaderTable().setValueAt(formatoDecimal.format(vCuñaNegociada),contFilas,8);
						
						//Muestro en la tabla la informacion del mapa comercial para ese detalle
						//Obtengo el mapa comercial para ese detalle
						Vector mapaComercialVector = (Vector) mapasComercialesToDetallesMap.get(j);
						
						
						//seteo los valores del mapa comercial ya sea este diario o semanal
						if(cmbTipoConsulta.getSelectedItem().equals("DIAS")){
							for(int x=0;x<mapaComercialVector.size();x++){
								if(mapaComercialVector.get(x)!=null)
									tspTablaTemp.getTable().getMainTable().setValueAt(((MapaComercialIf) mapaComercialVector.get(x)).getFrecuencia().toString(),contFilas,x);
							}
						}
						else if(cmbTipoConsulta.getSelectedItem().equals("SEMANAS")){
							for(int x=0;x<mapaComercialVector.size();x++){
								if(mapaComercialVector.get(x)!=null){
									//Saco el dia del mapa comercial que estoy leyendo
									int diaMapaComercial = Integer.parseInt(((MapaComercialIf) mapaComercialVector.get(x)).getFecha().toString().split("-")[2]);
									int frecuenciaMapaComercial = ((MapaComercialIf) mapaComercialVector.get(x)).getFrecuencia();
									//Veo en que semana deberia ir el mapa comercial
									for(int y=0;y<tspTablaTemp.getHEADERM().length;y++){
										int diaMinimoColumnaActual = Integer.parseInt(tspTablaTemp.getTable().getMainTable().getColumnName(y).split("-")[0].substring(2));
										int diaMaximoColumnaActual = Integer.parseInt(tspTablaTemp.getTable().getMainTable().getColumnName(y).split("-")[1].split("\\)")[0]);
										
										//veo si el dia del mapa comercial esta entre el rango de los dias pertenecientes a la semana
										if(diaMinimoColumnaActual <= diaMapaComercial && diaMapaComercial <= diaMaximoColumnaActual){
											int valorColumna = 0;

											//Veo si en la columna ya hay un valor
											if(tspTablaTemp.getTable().getMainTable().getValueAt(contFilas,y)!=null)
												//le sumo al valor que habia antes el valor del mapa comercial
												valorColumna = Integer.parseInt(tspTablaTemp.getTable().getMainTable().getValueAt(contFilas,y).toString()) + frecuenciaMapaComercial;
											else
												//el valor de la columan es igual a la frecuencia del mapa comercial
												valorColumna = frecuenciaMapaComercial;
											
											//Seteo el valor nuevo a la columna
											tspTablaTemp.getTable().getMainTable().setValueAt(valorColumna+"",contFilas,y);
											
											//Si se encontro y se acuatlizo el valor en la columna dejo de busar enlas otras columnas
											break;
										}										
									}
									
								}	
							}
						}
							
						//Obtengo el numero de columnas (Dias del mes) encontrados en la tabla
						int indiceSiguienteDiasMes= tspTablaTemp.getHEADERM().length;
						
						tspTablaTemp.getTable().getMainTable().setValueAt(formatoDecimal.format(tgrpGYE),contFilas,indiceSiguienteDiasMes);
						tspTablaTemp.getTable().getMainTable().setValueAt(formatoDecimal.format(tgrpUIO),contFilas,indiceSiguienteDiasMes+1);
						tspTablaTemp.getTable().getMainTable().setValueAt(formatoDecimal.format(tgrpPond),contFilas,indiceSiguienteDiasMes+2);
						tspTablaTemp.getTable().getMainTable().setValueAt(formatoDecimal.format(CxPR),contFilas,indiceSiguienteDiasMes+3);
						tspTablaTemp.getTable().getMainTable().setValueAt(formatoDecimal.format(impactos),contFilas,indiceSiguienteDiasMes+4);
						tspTablaTemp.getTable().getMainTable().setValueAt(formatoDecimal.format(invTarifa),contFilas,indiceSiguienteDiasMes+5);
						tspTablaTemp.getTable().getMainTable().setValueAt(formatoDecimal.format(invCDescto),contFilas,indiceSiguienteDiasMes+6);
			    	}
					else if(tipoProveedorIf.getNombre().contains("PRENSA")){
					
					}
			    	
			    	//Incremento en 1 el contador de las filas
			    	contFilas++;
				}
			}	
		}
	}
	
	
	private void cargarListenersComponents(){
		cmbProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				proveedorIf = (ClienteOficinaIf) ((JComboBox)evento.getSource()).getSelectedItem();
				
				//Remuevo lso items que habian en el proveedor anterior
				cmbPrograma.removeAllItems();
				//Añado nulo al combo de programas
				cmbPrograma.addItem(null);
				
				Vector programasByProveedorVector = new Vector();
				
				//Si el proveedor es diferete de null mando a buscar el vector de programas en el mapa 
				if(programasByProveedorMap.get(proveedorIf.getId())!=null){
					programasByProveedorVector = (Vector) programasByProveedorMap.get(proveedorIf.getId());
					for(int i=0;i<programasByProveedorVector.size();i++)
						cmbPrograma.addItem(programasByProveedorVector.get(i).toString());	
					
				}
				//Mando a cargar los programas y los detalles del  proveedor escogido
				mostrarProgramasAndDetallesByProveedor(null);
				
			}
		});
		
		cmbTipoConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(cmbTipoConsulta.getSelectedItem().equals("DIAS")){
					tspDiasDelMes.getTable().setVisible(true);
					tspSemanasDelMes.getTable().setVisible(false);
					//Mando a cargar los programas y los detalles del  proveedor escogido segun la vista
					mostrarProgramasAndDetallesByProveedor(cmbPrograma.getSelectedItem());
				}else if(cmbTipoConsulta.getSelectedItem().equals("SEMANAS")){
					tspDiasDelMes.getTable().setVisible(false);
					tspSemanasDelMes.getTable().setVisible(true);
					//Mando a cargar los programas y los detalles del  proveedor escogido segun la vista
					mostrarProgramasAndDetallesByProveedor(cmbPrograma.getSelectedItem());
				}					
			}
		});
		
		
		cmbPrograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				//Mando a cargar los programas y los detalles del  proveedor escogido segun el programa
				mostrarProgramasAndDetallesByProveedor(cmbPrograma.getSelectedItem());								
			}
		});
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		contentPane = new JPanel();
		panelBarraTitulo = new JPanel();
		lblTitulo = new JLabel();
		goodiesFormsSeparator = compFactory.createSeparator("");
		panelForm = new JPanel();
		lblPrograma = new JLabel();
		lblProveedor = new JLabel();
		lblMesAño = new JLabel();
		dsMesAnoMapaProveedor = new DateSpinner("MMM/yyyy");
		lblTipoConsulta = new JLabel();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setMaximumSize(new Dimension(1000, 300));
		setAttachable(true);
		setMovable(true);
		setLayout(new FormLayout(
			"default",
			"fill:default"));

		//======== contentPane ========
		{
			contentPane.setPreferredSize(new Dimension(1000, 300));
			contentPane.setMinimumSize(new Dimension(1000, 300));
			contentPane.setMaximumSize(new Dimension(1000, 300));
			contentPane.setLayout(new FormLayout(
				"default:grow",
				"fill:pref, 10dlu, default, 10dlu"));
			
			//======== panelBarraTitulo ========
			{
				panelBarraTitulo.setLayout(new FormLayout(
					"default:grow",
					"fill:default"));
				
				//---- lblTitulo ----
				lblTitulo.setText("Mapa Proveedor");
				lblTitulo.setBackground(UIManager.getColor("Button.light"));
				lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
				lblTitulo.setOpaque(true);
				panelBarraTitulo.add(lblTitulo, cc.xy(1, 1));
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
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(10))
					},
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(10)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.NO_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));
				panelForm.add(cmbProveedor, cc.xy(5, 1));
				
				//---- lblPrograma ----
				lblPrograma.setText("Programa:");
				panelForm.add(lblPrograma, cc.xywh(7, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- lblProveedor ----
				lblProveedor.setText("Proveedor:");
				panelForm.add(lblProveedor, cc.xy(3, 1));
				panelForm.add(cmbPrograma, cc.xy(9, 1));
				
				//---- lblMesAño ----
				lblMesAño.setText("Mes/A\u00f1o:");
				panelForm.add(lblMesAño, cc.xy(3, 3));
				panelForm.add(dsMesAnoMapaProveedor, cc.xy(5, 3));
				
				//---- lblTipoConsulta ----
				lblTipoConsulta.setText("TipoConsulta");
				panelForm.add(lblTipoConsulta, cc.xywh(7, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelForm.add(cmbTipoConsulta, cc.xy(9, 3));
				
				//======== tspDiasDelMes ========
				{
					tspDiasDelMes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					tspDiasDelMes.setVerifyInputWhenFocusTarget(true);
					tspDiasDelMes.setAutoscrolls(false);
					tspDiasDelMes.getTable().setVisible(true);
				}
				panelForm.add(tspDiasDelMes.getTable(), cc.xywh(3, 7, 7, 1));
				
				//======== tspSemanasDelMes ========
				{
					tspSemanasDelMes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					tspSemanasDelMes.setVerifyInputWhenFocusTarget(true);
					tspSemanasDelMes.setAutoscrolls(false);
					tspSemanasDelMes.getTable().setVisible(false);
				}
				panelForm.add(tspSemanasDelMes.getTable(), cc.xywh(3, 7, 7, 1));
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
	private JLabel lblTitulo;
	private JComponent goodiesFormsSeparator;
	private JPanel panelForm;
	private JComboBox cmbProveedor;
	private JLabel lblPrograma;
	private JLabel lblProveedor;
	private JComboBox cmbPrograma;
	private JLabel lblMesAño;
	private DateSpinner dsMesAnoMapaProveedor;
	private JLabel lblTipoConsulta;
	private JComboBox cmbTipoConsulta;
	private MySheetTableMapaProveedorScrollPane tspDiasDelMes;
	private MySheetTableMapaProveedorScrollPane tspSemanasDelMes;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public static ClienteOficinaSessionService getClienteOficinaSessionService() {
		try {
			return (ClienteOficinaSessionService) ServiceLocator.getService(ServiceLocator.CLIENTEOFICINASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicación con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}*/
}
