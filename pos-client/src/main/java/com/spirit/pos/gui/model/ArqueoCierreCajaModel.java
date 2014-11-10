
package com.spirit.pos.gui.model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.CajaIf;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.TipoPagoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.pos.entity.CajaSesionData;
import com.spirit.pos.entity.CajaSesionIf;
import com.spirit.pos.entity.CajasesionMovimientosIf;
import com.spirit.pos.entity.MultasDocumentosIf;
import com.spirit.pos.entity.VentasDocumentosIf;
import com.spirit.pos.entity.VentasPagosIf;
import com.spirit.pos.entity.VentasTrackIf;
import com.spirit.pos.gui.panel.JPArqueoCierreCaja;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.Utilitarios;


public class ArqueoCierreCajaModel extends JPArqueoCierreCaja{
	private static final int MAX_LONGITUD_CODIGO = 4;
	private DefaultTableModel tableModel;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");	
	private DecimalFormat formatoEntero = new DecimalFormat("###0");
	protected CajaSesionIf cajaSesion; 
	protected EmpleadoIf empleado;
	protected VentasTrackIf idVentasPrincipal;
	Vector<String> idTracks = new Vector<String>();	
	String idEfectivo=new String("EF");
	String idTc=new String("TA");
	String idGc=new String("GC");
	String idCheq=new String("CH");
	String idTd=new String("DB");
	String idCc=new String("CR");
	CajaIf caj;

	Vector<Vector> vectorDevoluciones = new Vector<Vector>();
	Vector<Vector> vectorTarjeta = new Vector<Vector>();
	boolean tarjetacredito=false;

	boolean noCerrarCajaIng=false;
	boolean noCerrarCajaEgr=false;

	BigDecimal sumaDescuadre=new BigDecimal("0.00");
	BigDecimal sumaMultas=new BigDecimal("0.00");
	
	
	private AutorizacionModel jdAutorizacionModel;


	public ArqueoCierreCajaModel(){	 
		initKeyListeners();
		initListeners();		
		clean();
		getTblTablaDetalle().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		new HotKeyComponent(this);		
	}

	private void initListeners(){			
		getBtnBloquearCaja().addActionListener(oActionListenerBotonBloquear);		
		getBtnCerrarCaja().addActionListener(oActionListenerBotonCerrar);
		getBtnDesbloquear().addActionListener(oActionListenerBotonDESBloquear);
		getLblIconAlert().setIcon(SpiritResourceManager.getImageIcon("images/pos/minus.png"));	 
		setVisible2(false,getLblXGiftC());
		setVisible2(false,getLblXTarjeta());
		setVisible2(false,getLblXCheque());
		//	setVisible2(false,getLblXCreditoC());
		setVisible2(false,getLblXDevoluciones());
		setVisible2(false,getLblXIngresos());
		setVisible2(false,getLblXEgresos());		

		getLblXGiftC().setText("");
		getLblXTarjeta().setText("");
		getLblXCheque().setText("");
		//getLblXCreditoC().setText("");
		getLblXDevoluciones().setText("");
		getLblXIngresos().setText("");
		getLblXEgresos().setText("");	
	}


	public void setVisible2(boolean mostrar,JLabel label){		 
		if(mostrar){
			label.setText("");
			label.setIcon(SpiritResourceManager.getImageIcon("images/pos/falta.png"));
		}			
		else
		{
			label.setText("");
			label.setIcon(SpiritResourceManager.getImageIcon("images/pos/lleno.png"));		
		}
	}

	public void initKeyListeners(){
		getLblFechaActual().setText(Utilitarios.fechaAhora());
	  
		//ventas
		getLblEfectivoLink().addKeyListener(oKeyAdapterTbDetalleVentasEfectivo);
		getLblEfectivoLink().addMouseListener(oMouseAdapterTblDetalleVentasEfectivo);

		getLblTarjetaLink().addKeyListener(oKeyAdapterTbDetalleVentasTarjeta);
		getLblTarjetaLink().addMouseListener(oMouseAdapterTblDetalleVentasTarjeta);

		getLblChequeLink().addKeyListener(oKeyAdapterTbDetalleVentasCheque);
		getLblChequeLink().addMouseListener(oMouseAdapterTblDetalleVentasCheque);

		getLblGiftCLink().addKeyListener(oKeyAdapterTbDetalleVentasGiftCards);
		getLblGiftCLink().addMouseListener(oMouseAdapterTblDetalleVentasGiftCards);

		getLblDevolucionesLink().addKeyListener(oKeyAdapterTbDetalleVentasDevoluciones);
		getLblDevolucionesLink().addMouseListener(oMouseAdapterTblDetalleVentasDevoluciones);

		///////////////////////////////////////////////////
		getLblCreditoClienteLink().addKeyListener(oKeyAdapterTbDetalleVentasCreditoC);
		getLblCreditoClienteLink().addMouseListener(oMouseAdapterTblDetalleVentasCreditoC);		

		//caja
		getLblCajaInicialLink().addKeyListener(oKeyAdapterTbDetalleCajaInicial);
		getLblCajaInicialLink().addMouseListener(oMouseAdapterTblDetalleCajaInicial);

		getLblCajaIngresosLink().addKeyListener(oKeyAdapterTbDetalleCajaIngresos);
		getLblCajaIngresosLink().addMouseListener(oMouseAdapterTbDetalleCajaIngresos);

		getLblCajaEgresosLink().addKeyListener(oKeyAdapterTbDetalleCajaEgresos);
		getLblCajaEgresosLink().addMouseListener(oMouseAdapterTbDetalleCajaEgresos);

		getLblIconAlert().setVisible(false);

		getTxtCantReal().addKeyListener(new NumberTextFieldDecimal()); 
		getTxtCantReal().addKeyListener(oKeyAdapterTblRubro);
		getTxtCantReal().addMouseListener(oMouseAdapterTblRubro);

		getTxtDescuadreEfectivo().addKeyListener(new NumberTextFieldDecimal());

		BigDecimal cero=new BigDecimal("0.00");	 

		getTxtDescuadreEfectivo().setText(cero.toString());
		getTxtDescuadreDoc().setText(cero.toString());	
	}


	WindowListener wl = new WindowAdapter(){
		@Override
		public void windowClosed(WindowEvent e) {
			jdAutorizacionModel = null;
			System.gc();
		}		
	};

	MouseListener oMouseAdapterTblRubro = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			//	clean();
			//GuardarDetalles();						
		}
	};

	KeyListener oKeyAdapterTblRubro = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {			
			//GuardarDetalles();
			llenarDescuadreEfectivo();			
		}
	};

	public void llenarDescuadreEfectivo(){
		if(getTxtCantidadTeorica().getText().equals("")) getTxtCantidadTeorica().setText("0.00");
		if(getTxtCantReal().getText().equals("")) getTxtCantReal().setText("0.00");		
		BigDecimal teor=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtCantidadTeorica().getText()));			
		BigDecimal real=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtCantReal().getText()));
		BigDecimal descuadre=real.subtract(teor);
		getTxtDescuadreEfectivo().setText(descuadre.toString());
		
		BigDecimal cero=new BigDecimal("0.00"); 		
		if(descuadre.compareTo(cero)==-1)
		{
			getLblIconAlert().setVisible(true);				
			getLblIconAlert().setIcon(SpiritResourceManager.getImageIcon("images/pos/menos.png"));
		}
		if(descuadre.compareTo(cero)==0)
		{
			getLblIconAlert().setVisible(true);
			getLblIconAlert().setIcon(SpiritResourceManager.getImageIcon("images/pos/cuadrado.png"));				
		}			
		if(descuadre.compareTo(cero)>0)
		{
			getLblIconAlert().setVisible(true);
			getLblIconAlert().setIcon(SpiritResourceManager.getImageIcon("images/pos/mas.png"));				
		}
	}


	//llena el vector de detalles de DEVOLUCIONES y obtengo el total
	public BigDecimal llenarDevoluciones(Long idFacturaDevolucion,String revisado,Long idVentasDocumentos){
		BigDecimal sumaDevoluciones=new BigDecimal("0.00");
		if(cajaSesion.getId()!=new Long("0") ){	
			Iterator facturaIt;
			try {
				facturaIt = SessionServiceLocator.getFacturaSessionService().findFacturaById(idFacturaDevolucion).iterator();
				FacturaIf facturaIf=null;
				if (facturaIt.hasNext()) 			
				{
					FacturaIf dev = (FacturaIf) facturaIt.next();	
					Vector<Object> fila = new Vector<Object>();
					String fecha=dev.getFechaFactura().toString();
					fecha=fecha.substring(0,10);
					fila.add(0,fecha) ;//fecha
					BigDecimal valor=dev.getValor();
					valor=valor.add(dev.getIva());
					valor=valor.subtract(dev.getDescuento());
					valor=valor.subtract(dev.getDescuentoGlobal());
					fila.add(1,valor.toString()) ;	

					if(revisado==null) revisado="F";
					if(revisado.equals("T"))
						fila.add(2,new Boolean(true));
					else 
						fila.add(2,new Boolean(false));

					String numDocumento=dev.getPreimpresoNumero();
					if(numDocumento==null) numDocumento="";
					fila.add(3,numDocumento);						
					fila.add(4,idVentasDocumentos);					

					if(revisado.equals("F"))
					{
						BigDecimal multaDevolucion=getMultaDocumento("DEV");		
						sumaMultas=sumaMultas.add(multaDevolucion);							
					}						

					sumaDevoluciones=sumaDevoluciones.add(valor);
					vectorDevoluciones.add(fila);
				}
			} catch (GenericBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}			
		return sumaDevoluciones;
	}


	//obtengo todas las devoluciones que se han hecho en este turno o sesion
	public BigDecimal suma_devoluciones(Long caja_id,Vector<String> id_ventastrack) throws GenericBusinessException{
		BigDecimal sumaDevoluciones=new BigDecimal(0.00);
		boolean todoRevisado=true;
		Map aMap2 = new HashMap();
		aMap2.clear();
		Vector<String> ventastrackId = new Vector<String>();
		ventastrackId=id_ventastrack;		
		aMap2.put("ventastrackId",ventastrackId);
		aMap2.put("tablaNombre","DEVOLUCION");
		vectorDevoluciones.clear();
		if(caja_id!=new Long("0") ){	
			Iterator cajavalorIt2;
			cajavalorIt2 = SessionServiceLocator.getVentasDocumentosSessionService().findVentasDocumentosByQueryVariosId(aMap2).iterator();
			while (cajavalorIt2.hasNext()) {
				VentasDocumentosIf documentoDevolucion = (VentasDocumentosIf) cajavalorIt2.next();							
				Long idReferencia=documentoDevolucion.getTablaId();
				Long idVentasDocumentos=documentoDevolucion.getId();				
				//obtengo el valor de esa devolucion y lleno el vector de los detalles
				String revisado=documentoDevolucion.getRevisado();
				if(revisado==null) revisado="F";
				if(revisado.equals("F")) todoRevisado=false;
				sumaDevoluciones=sumaDevoluciones.add(llenarDevoluciones(idReferencia,revisado,idVentasDocumentos));	
			}
		}

		if(!todoRevisado) setVisible2(true,getLblXDevoluciones()); 
		else setVisible2(false,getLblXDevoluciones());

		return sumaDevoluciones;
	}

	//efec//tc//cc/gc//ch//dev
	
	public void sumaVarios(Long caja_id,Vector<String> id_ventastrack) throws GenericBusinessException{
		
		
		
		BigDecimal sumaventasEfec=new BigDecimal(0.00);    BigDecimal sumaventasCH=new BigDecimal(0.00); 	
		BigDecimal sumaventasGC=new BigDecimal(0.00);		BigDecimal sumaventasTC=new BigDecimal(0.00);
		BigDecimal sumaventasDEV=new BigDecimal(0.00);		BigDecimal sumaventasCC=new BigDecimal(0.00);

		Vector<String> ventastrackId = new Vector<String>();
		ventastrackId=id_ventastrack;		
		Map aMap2 = new HashMap();
		aMap2.clear();  		
		aMap2.put("ventastrackId",ventastrackId);
		

		boolean todoRevisado=true;
		boolean todoRevisadoE=true;boolean todoRevisadoGC=true;
		boolean todoRevisadoTC=true;boolean todoRevisadoCH=true;
		boolean todoRevisadoCC=true;
		
		
		BigDecimal valorMultaTarjetacred=getMultaDocumento("CCTC");
			
		BigDecimal valorMultaCheque=getMultaDocumento("COCL");
		
		

		if(caja_id!=new Long("0") ){	
			Iterator cajavalorIt2;
			cajavalorIt2 = SessionServiceLocator.getVentasPagosSessionService().findVentasPagosByQueryVariosId(aMap2).iterator();

			while (cajavalorIt2.hasNext()) {
				VentasPagosIf pagos = (VentasPagosIf) cajavalorIt2.next();
				Long tipo=pagos.getTipo();
				 
				
				if(tipo.toString().equals(idEfectivo.toString())) sumaventasEfec=pagos.getValor().add(sumaventasEfec);	
				if(tipo.toString().equals(idTc.toString())) sumaventasTC=pagos.getValor().add(sumaventasTC);
				if(tipo.toString().equals(idCc.toString())) sumaventasCC=pagos.getValor().add(sumaventasCC);
				if(tipo.toString().equals(idGc.toString())) sumaventasGC=pagos.getValor().add(sumaventasGC);
				if(tipo.toString().equals(idCheq.toString())) sumaventasCH=pagos.getValor().add(sumaventasCH);
				
				if(!tipo.toString().equals(idCc.toString())){
					String revisado=pagos.getRevisado();
					if(revisado==null) revisado="F";
					boolean sumaMultaCH=true;
					boolean sumaMultaTC=true;
					if(revisado.equals("F") && tipo.toString().equals(idTc.toString())) {
						todoRevisadoTC=false;
						sumaMultaTC=true;		
						
					}
					else{ sumaMultaTC=false;}
					
					if(revisado.equals("F") && tipo.toString().equals(idCheq.toString())) {
						todoRevisadoCH=false;
						sumaMultaCH=true;				
					}
					else{ sumaMultaCH=false;}
					
					if(tipo.toString().equals(idTc.toString()) && sumaMultaTC){						
						BigDecimal multaTarjeta=valorMultaTarjetacred;
						sumaMultas=sumaMultas.add(multaTarjeta);
						String numdocu=pagos.getNumDoc();
						if(numdocu==null) numdocu="";
						//es manual el cobro de t/c
						if(numdocu.equals(""))	sumaDescuadre=sumaDescuadre.add(pagos.getValor());
						}

					if(tipo.toString().equals(idCheq.toString()) && sumaMultaCH) {
						BigDecimal multaCheque=valorMultaCheque;
						sumaMultas=sumaMultas.add(multaCheque);
						sumaDescuadre=sumaDescuadre.add(pagos.getValor());
					}
				}
			}
		}


		//COCL/CCTC/DEV
		if(!todoRevisadoTC) setVisible2(true,getLblXTarjeta());
		if(todoRevisadoTC) 	setVisible2(false,getLblXTarjeta());
		
		if(todoRevisadoGC)  setVisible2(true,getLblXGiftC());
		if(todoRevisadoGC)  setVisible2(false,getLblXGiftC());

		if(!todoRevisadoCH) setVisible2(true,getLblXCheque());
		if(todoRevisadoCH)  setVisible2(false,getLblXCheque());


		getTxtEfectivo().setText(formatoDecimal.format(sumaventasEfec));
		//movimientos de ventas con forma pago tarjeta credito
		getTxtTarjeta().setText(formatoDecimal.format(sumaventasTC));
		//movimientos de ventas con forma pago Cheque		
		getTxtCheque().setText(formatoDecimal.format(sumaventasCH)); 
		//movimientos de ventas con forma pago gift cards		
		getTxtGiftCards().setText(formatoDecimal.format(sumaventasGC));
		//movimientos de ventas con forma pago Credito Cliente
		getTxtCreditoCliente().setText(formatoDecimal.format(sumaventasCC));		
	}
	 
	public void suma_movientos(Long caja_id) throws GenericBusinessException{
		//BigDecimal sumainegre=new BigDecimal("0");			
		
		BigDecimal sumaing=BigDecimal.ZERO;
		BigDecimal sumaegr=BigDecimal.ZERO;
		Map aMap2 = new HashMap();
		aMap2.clear();
		aMap2.put("cajasesionId",caja_id);		
		boolean todoRevisadoI=true;
		boolean todoRevisadoE=true;

		if(caja_id!=new Long("0")){	
			Iterator cajavalorIt2;
			cajavalorIt2 = SessionServiceLocator.getCajasesionMovimientosSessionService().findCajasesionMovimientosByQuery(aMap2).iterator();		 		
			while (cajavalorIt2.hasNext()) {
				CajasesionMovimientosIf pagos = (CajasesionMovimientosIf) cajavalorIt2.next();
				
				String tipo=pagos.getTipomovimiento();
				if(tipo.equals("I"))	sumaing=pagos.getValor().add(sumaing);	
				if(tipo.equals("E"))	sumaegr=pagos.getValor().add(sumaegr);

				String revisado=pagos.getRevisado();
				if(revisado==null) revisado="F";
				if(revisado.equals("F") && tipo.equals("I")) todoRevisadoI=false;
				if(revisado.equals("F") && tipo.equals("E")) todoRevisadoE=false;

				String numDoc=pagos.getNumDoc();
				if(numDoc==null) numDoc="";
				if(numDoc.equals("") && tipo.equals("I")) todoRevisadoI=false;
				if(numDoc.equals("") && tipo.equals("E")) todoRevisadoE=false;
			}
		}



		if(!todoRevisadoI) noCerrarCajaIng=true;
		
		if(todoRevisadoI ){
			noCerrarCajaIng=false;
			setVisible2(false,getLblXIngresos());
		}
		if(!todoRevisadoE ){
			noCerrarCajaEgr=true;
			setVisible2(true,getLblXEgresos());
		}
		if(todoRevisadoE ){ 
			noCerrarCajaEgr=false;
			setVisible2(false,getLblXEgresos());
		}
		
		getTxtCajaIngresos().setText(formatoDecimal.format(sumaing));
		getTxtCajaEgresos().setText(formatoDecimal.format(sumaegr));
	}
	
	private void mostrar_datos(CajaSesionIf modelo) throws GenericBusinessException{
		
		Long caja_id=new Long("0");
		
		BigDecimal cantTeorica=new BigDecimal(0.00);		
		BigDecimal sumaventas_DEV=new BigDecimal(0.00);		
		BigDecimal valor=modelo.getValorInicial();
		
		getTxtCajaInicial().setText(valor.toString());
		getTxtCajaInicial().setText(formatoDecimal.format(valor));		
		caja_id=modelo.getId();		
		
				
		//trae todos los id del a ventas track, por cada venta(1fact,xgift,tipos de pago,donacion,etc...) existe 1 id
		//entonces devuelde todas las "ventas" que se han hecho en ese turno con esa caja.
		idTracks=idtracks(caja_id);
		
		sumaDescuadre=new BigDecimal("0.00");
		sumaMultas=new BigDecimal("0.00");

		//verifica ingreso y egresos de caja(por banco o tx de caja)
		suma_movientos(caja_id);
		
		if(idTracks.size()>0)
		{	
			sumaVarios(caja_id, idTracks);			
			sumaventas_DEV=suma_devoluciones(caja_id,idTracks);;
			getTxtDevoluciones().setText(sumaventas_DEV.toString());
		}
	 		
	   if(getTxtCantidadTeorica().getText().equals("")) getTxtCantidadTeorica().setText("0");
	 
		BigDecimal teor=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtCantidadTeorica().getText()));		
		System.out.println("teor"+teor);
		
		cantTeorica=cantTeorica.add(new BigDecimal(Utilitarios.removeDecimalFormat(getTxtEfectivo().getText())));
		cantTeorica=cantTeorica.add(valor);
		cantTeorica=cantTeorica.add(new BigDecimal(Utilitarios.removeDecimalFormat(getTxtCajaIngresos().getText())));
		cantTeorica=cantTeorica.subtract(new BigDecimal(Utilitarios.removeDecimalFormat(getTxtCajaEgresos().getText()))); 

		getTxtCantidadTeorica().setText(formatoDecimal.format(cantTeorica));
		getTxtDescuadreDoc().setText(sumaDescuadre.toString());
		getTxtMultasPerdidasDoc().setText(sumaMultas.toString());

		llenarDescuadreEfectivo();

	}



	//toma los IDS de ventas_track
	private Vector<String> idtracks(Long cajasesion_id){
		Vector<String> idtracks = new Vector<String>();	

		Map aMap = new HashMap();
		aMap.clear();
		aMap.put("cajasesionId", cajasesion_id);
		if(cajasesion_id!=new Long("0")){	
			Iterator cajavalorIt2;
			try {
				cajavalorIt2 = SessionServiceLocator.getVentasTrackSessionService().findVentasTrackByQuery(aMap).iterator();
				while (cajavalorIt2.hasNext()) {
					VentasTrackIf valor_actual = (VentasTrackIf) cajavalorIt2.next();	
					idtracks.add(valor_actual.getId().toString());
				}
			} catch (GenericBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return idtracks;
	}


	private void llenar_formasPago_ids(){		
		//id de efectivo... para no setear un id fijo
		TipoPagoIf tipoPagoIf;
		try {
			Iterator it3 = SessionServiceLocator.getTipoPagoSessionService().getTipoPagoList().iterator();
			while (it3.hasNext()) {
				tipoPagoIf = (TipoPagoIf)it3.next();
				String codigo=tipoPagoIf.getCodigo();
				if(codigo.equals(idEfectivo)) idEfectivo=tipoPagoIf.getId().toString();
				if(codigo.equals(idGc)) idGc=tipoPagoIf.getId().toString();
				if(codigo.equals(idCheq)) idCheq=tipoPagoIf.getId().toString();
				if(codigo.equals(idTc)) idTc=tipoPagoIf.getId().toString();
				if(codigo.equals(idTd)) idTd=tipoPagoIf.getId().toString();
				if(codigo.equals(idCc)) idCc=tipoPagoIf.getId().toString();
			}

		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




	//llena los datos de todo el formulario: VERIFICA LA CAJA ABIERTA Y MUESTRA LOS DATOS
	private void llenar_datos(){		
		
		try {

				llenar_formasPago_ids();//		
	
	
				UsuarioIf usergeneral =(UsuarioIf)Parametros.getUsuarioIf();
				Long empleadoId= usergeneral.getEmpleadoId();	
				HashMap<String, Object> mapa = new HashMap<String, Object>();
				mapa.clear();
				mapa.put("id" , empleadoId );			
	
				Iterator cajeroIt = SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByQuery(mapa).iterator();
				empleado = (EmpleadoIf) cajeroIt.next();		
				getLblCajeroNombre().setText(empleado.getApellidos()+" "+empleado.getNombres());	
	
				Iterator cajaIt = SessionServiceLocator.getCajaSessionService().findCajaByOficinaId(Parametros.getIdOficina()).iterator();
				caj = (CajaIf) cajaIt.next();					
				getLblCajaPC().setText(caj.getCodigo()+" - "+caj.getNombre());
	
				Long usuarioId=usergeneral.getId();
	
	
				cajaSesion = Caja_abierta_id(usuarioId);
				//activa o desactiva botones que estan al final... activar caja, desactivar caja
				if (cajaSesion!=null) {
					//la session esta abierta, puedo bloquearla o cerrarla	
					getTxtCantReal().setEnabled(true);
					mostrar_datos(cajaSesion);
					//habilitar/deshabilitar botones
					getBtnBloquearCaja().setEnabled(true);getBtnCerrarCaja().setEnabled(true);	getBtnDesbloquear().setEnabled(false);
				}
				else{
	
				 
					
					Map aMap = new HashMap();
					aMap.clear();
					aMap.put("usuarioId", usuarioId);
					aMap.put("estado", "B");				
					Iterator cajavalorIt2 = SessionServiceLocator.getCajaSesionSessionService().findCajaSesionByQuery(aMap).iterator();				
					if (cajavalorIt2.hasNext()) {
						//la session bloqueada, no puedo cerrarla pero si desbloquearla
						CajaSesionIf valor_actual = (CajaSesionIf) cajavalorIt2.next();			
						cajaSesion=valor_actual;
						//MUESTRA TODOS LOS DATOS DE EL CIERRE DE CAJA!!!
						mostrar_datos(valor_actual);	
						//habilitar/deshabilitar botones
						getBtnDesbloquear().setEnabled(true);getBtnBloquearCaja().setEnabled(false);getBtnCerrarCaja().setEnabled(false);
					}
					else{
						//habilitar/deshabilitar botones
						getBtnBloquearCaja().setEnabled(false);getBtnCerrarCaja().setEnabled(false);getBtnDesbloquear().setEnabled(false);
						SpiritAlert.createAlert("No tiene una CAJA ABIERTA", SpiritAlert.INFORMATION);
					}
				}	
	
			} catch (GenericBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}


	/////////////////////////////////////////////



	//verifica que la caja no haya sido cerrado, ni bloqueada.
	public CajaSesionIf Caja_abierta_id(Long usuarioId){	//cual es el ID de la caja, de este usuario(cajero)
		Long cajasesion_id=new Long("0");
				
		Map aMap = new HashMap();
		aMap.clear();
		aMap.put("usuarioId", usuarioId);
		aMap.put("estado", "A");
		aMap.put("fechafin",null);
		Iterator cajavalorIt;
		CajaSesionIf sesion_actual=null;
		try {
			cajavalorIt = SessionServiceLocator.getCajaSesionSessionService().findCajaSesionByQuery(aMap).iterator();
			if (cajavalorIt.hasNext()) {
				sesion_actual = (CajaSesionIf) cajavalorIt.next();
				BigDecimal valor=sesion_actual.getValorInicial();				 	 
				cajasesion_id=sesion_actual.getId();				
			}
			else{		
				SpiritAlert.createAlert("Debe tener una caja Abierta",SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.cajaSesion=sesion_actual;
		return sesion_actual;
	}





	public void GuardarDetalles(){




		String tipo=getLblDetalleSeleccionado().getText();		
		int rowGeneral=getTblTablaDetalle().getModel().getRowCount();

		System.out.println("ROWGENERAL>"+rowGeneral);


		String idTipo="";

		if(tipo.equals("TC")) idTipo=idTc;
		if(tipo.equals("CH")) idTipo=idCheq;
		if(tipo.equals("GC")) idTipo=idGc;		  
		if(tipo.equals("CC")) idTipo=idCc;




		if(tipo.equals("TC"))
		{
			int row=getTblTablaDetalle().getModel().getRowCount();
			boolean todoRevisado=true;			  
			for(int i=0;i<row;i++){				  
				Long idDetalle= (Long)getTblTablaDetalle().getModel().getValueAt(i,6);				
				Map aMap2 = new HashMap();
				aMap2.clear();	 
				aMap2.put("tipo", new Long(idTipo));//ingreso
				aMap2.put("id",idDetalle);//					
				try {				 			
					Iterator cajavalorIt = SessionServiceLocator.getVentasPagosSessionService().findVentasPagosByQueryVariosId(aMap2).iterator();
					if (cajavalorIt.hasNext()) {					
						VentasPagosIf valor_actual = (VentasPagosIf) cajavalorIt.next();								 
						String revisado=getTblTablaDetalle().getModel().getValueAt(i,3).toString();
						if(revisado==null) revisado="F";
						if(revisado.length()>0) revisado=revisado.substring(0,1);

						revisado=revisado.toUpperCase();
						if(revisado.equals("F")) todoRevisado=false;

						/*if(!todoRevisado){					
									BigDecimal multaTarjeta=getMultaDocumento("CCTC");		
									sumaMultas=sumaMultas.add(multaTarjeta);
									String numdocu=valor_actual.getNumDoc();
									if(numdocu==null) numdocu="";
									System.out.println("NUMERO DE REFERENCIA DE LA TARJETA DE C:"+numdocu+"<--");
									if(numdocu.equals(""))//es manual el cobro de t/c										
											sumaDescuadre=sumaDescuadre.add(valor_actual.getValor());																	
									}*/

						valor_actual.setRevisado(revisado);

						//////////////////////////////////
						SessionServiceLocator.getVentasPagosSessionService().saveVentasPagos(valor_actual);								
					}				
				}
				catch (GenericBusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				  
			}

			if(tipo.equals(idTc) && !todoRevisado) setVisible2(true,getLblXTarjeta()); 
			if(tipo.equals(idTc) && todoRevisado) setVisible2(false,getLblXTarjeta());

		}


		if(tipo.equals("CH"))
		{
			int row=getTblTablaDetalle().getModel().getRowCount();
			boolean todoRevisado=true;			  
			for(int i=0;i<row;i++){				  
				Long idDetalle= (Long)getTblTablaDetalle().getModel().getValueAt(i,6);				
				Map aMap2 = new HashMap();
				aMap2.clear();	 
				aMap2.put("tipo", new Long(idTipo));//ingreso
				aMap2.put("id",idDetalle);//					
				try {				 			
					Iterator cajavalorIt = SessionServiceLocator.getVentasPagosSessionService().findVentasPagosByQueryVariosId(aMap2).iterator();
					if (cajavalorIt.hasNext()) {					
						VentasPagosIf valor_actual = (VentasPagosIf) cajavalorIt.next();								 
						String revisado=getTblTablaDetalle().getModel().getValueAt(i,2).toString();
						if(revisado==null) revisado="F";
						if(revisado.length()>0) revisado=revisado.substring(0,1);

						revisado=revisado.toUpperCase();
						if(revisado.equals("F")) todoRevisado=false;

						/*if(!todoRevisado){					
									BigDecimal multaCheque=getMultaDocumento("COCL");		
									sumaMultas=sumaMultas.add(multaCheque);
									sumaDescuadre=sumaDescuadre.add(valor_actual.getValor());																	
									}*/

						valor_actual.setRevisado(revisado);

						//////////////////////////////////
						SessionServiceLocator.getVentasPagosSessionService().saveVentasPagos(valor_actual);								
					}				
				}
				catch (GenericBusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				  
			}

			if(tipo.equals(idCheq) && !todoRevisado)setVisible2(true,getLblXCheque());
			if(tipo.equals(idCheq) && todoRevisado) setVisible2(false,getLblXCheque());

		}

		if(tipo.equals("GC"))
		{
			int row=getTblTablaDetalle().getModel().getRowCount();
			boolean todoRevisado=true;			  
			for(int i=0;i<row;i++){				  
				Long idDetalle= (Long)getTblTablaDetalle().getModel().getValueAt(i,5);				
				Map aMap2 = new HashMap();
				aMap2.clear();	 
				aMap2.put("tipo", new Long(idTipo));//ingreso
				aMap2.put("id",idDetalle);//					
				try {				 			
					Iterator cajavalorIt = SessionServiceLocator.getVentasPagosSessionService().findVentasPagosByQueryVariosId(aMap2).iterator();
					if (cajavalorIt.hasNext()) {					
						VentasPagosIf valor_actual = (VentasPagosIf) cajavalorIt.next();								 
						String revisado=getTblTablaDetalle().getModel().getValueAt(i,2).toString();
						if(revisado==null) revisado="F";
						if(revisado.length()>0) revisado=revisado.substring(0,1);

						revisado=revisado.toUpperCase();
						if(revisado.equals("F")) todoRevisado=false;

						valor_actual.setRevisado(revisado);

						//////////////////////////////////
						SessionServiceLocator.getVentasPagosSessionService().saveVentasPagos(valor_actual);								
					}				
				}
				catch (GenericBusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				  
			}

			if(tipo.equals(idGc) && !todoRevisado) setVisible2(true,getLblXGiftC());
			if(tipo.equals(idGc) && todoRevisado) setVisible2(false,getLblXGiftC());
		}


		if(tipo.equals("ING") || tipo.equals("EGR")  ) {

			System.out.println("UNREGSO EGRESO>**>"+tipo);
			int row=getTblTablaDetalle().getModel().getRowCount();
			boolean todoRevisado=true;			  
			for(int i=0;i<row;i++){	



				System.out.println(i+"cero uno"+getTblTablaDetalle().getModel().getValueAt(i,0));

				Long idDetalle= (Long)getTblTablaDetalle().getModel().getValueAt(i,5);

				System.out.println("idDetalle++"+idDetalle);
				Map aMap2 = new HashMap();
				aMap2.clear();
				aMap2.put("id",idDetalle);//					
				try {								
					Iterator cajavalorIt = SessionServiceLocator.getCajasesionMovimientosSessionService().findCajasesionMovimientosByQuery(aMap2).iterator();
					if (cajavalorIt.hasNext()) {					
						CajasesionMovimientosIf valor_actual = (CajasesionMovimientosIf) cajavalorIt.next();								 
						String revisado=getTblTablaDetalle().getModel().getValueAt(i,3).toString();
						if(revisado==null) revisado="F";
						if(revisado.length()>0) revisado=revisado.substring(0,1);

						revisado=revisado.toUpperCase();
						if(revisado.equals("F")) todoRevisado=false;

						String numDocumento=(String)getTblTablaDetalle().getModel().getValueAt(i,4);
						if(numDocumento==null) numDocumento="";
						if(numDocumento.equals("")) todoRevisado=false;

						valor_actual.setRevisado(revisado);
						valor_actual.setNumDoc(numDocumento);
						//////////////////////////////////
						SessionServiceLocator.getCajasesionMovimientosSessionService().saveCajasesionMovimientos(valor_actual);								
					}				
				}
				catch (GenericBusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				  
			}

			if(!todoRevisado && tipo.equals("ING") ) noCerrarCajaIng=true;
			if(todoRevisado && tipo.equals("ING") ) noCerrarCajaIng=false;

			if(!todoRevisado && tipo.equals("EGR") ) noCerrarCajaEgr=true;
			if(todoRevisado && tipo.equals("EGR") ) noCerrarCajaEgr=false;


			if(tipo.equals("ING") && !todoRevisado) setVisible2(true,getLblXIngresos());
			if(tipo.equals("ING") && todoRevisado) setVisible2(false,getLblXIngresos());				
			//getLblXIngresos().setVisible(true);
			if(tipo.equals("EGR") && !todoRevisado) setVisible2(true,getLblXEgresos()); 
			if(tipo.equals("EGR") && todoRevisado) setVisible2(false,getLblXEgresos());
			// getLblXEgresos().setVisible(true);
		}

		if(tipo.equals("DEV")){

			int tamanio=vectorDevoluciones.size();
			System.out.println("EN DEV! tamanio>"+tamanio);
			boolean todoRevisado=true;		


			if(vectorDevoluciones.size()>0){
				for(int i=0;i<tamanio;i++){				  
					Long idDetalle= new Long(((Vector)vectorDevoluciones.get(i)).get(4).toString());
					System.out.println("EN DEV>-***********"+idDetalle);
					Map aMap2 = new HashMap();
					aMap2.clear();
					aMap2.put("id",idDetalle);//					
					try {								
						Iterator cajavalorIt = SessionServiceLocator.getVentasDocumentosSessionService().findVentasDocumentosByQuery(aMap2).iterator();
						if (cajavalorIt.hasNext()) {					
							VentasDocumentosIf valor_actual = (VentasDocumentosIf) cajavalorIt.next();								 
							String revisado="";//((Vector)vectorDevoluciones.get(i)).get(2).toString();

							System.out.println("getTblTablaDetalle().getModel1()"+getTblTablaDetalle().getModel().getColumnCount());
							System.out.println("getTblTablaDetalle().getModel2()"+getTblTablaDetalle().getModel().getRowCount());
							
							if(getTblTablaDetalle().getModel().getValueAt(i,2)==null) revisado="F";
							else revisado=getTblTablaDetalle().getModel().getValueAt(i,2).toString();
							if(revisado==null) revisado="F";
							if(revisado.length()>0) revisado=revisado.substring(0,1);
							revisado=revisado.toUpperCase();
							if(revisado.equals("F")) todoRevisado=false;


							/*if(!todoRevisado){					
									BigDecimal multaCheque=getMultaDocumento("DEV");		
									sumaMultas=sumaMultas.add(multaCheque);

									}*/


							valor_actual.setRevisado(revisado);

							System.out.println(revisado+"todoRevisado-->devolucones<");

							//////////////////////////////////
							SessionServiceLocator.getVentasDocumentosSessionService().saveVentasDocumentos(valor_actual);								
						}				
					}
					catch (GenericBusinessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				  
				}
			}

			System.out.println("todoRevisado-->devolucones<"+todoRevisado);


			if(!todoRevisado) setVisible2(true,getLblXDevoluciones());
			if(todoRevisado) setVisible2(false,getLblXDevoluciones());
			// getLblXDevoluciones().setVisible(true);
		}

		//sumadescuadre
		//suma

	}




	///////////////////LISTENERS////////////////


	ActionListener oActionListenerBotonCerrar = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			String si = "Si";
			String no = "No";
			GuardarDetalles();
			llenar_datos();			
			BigDecimal sumadescuadreEfectivo=new BigDecimal(getTxtDescuadreEfectivo().getText());
			BigDecimal sumadescuadreDoc=new BigDecimal(getTxtDescuadreDoc().getText());			
			sumadescuadreEfectivo=sumadescuadreEfectivo.add(sumadescuadreDoc);
			BigDecimal sumaMultasPerdDoc=new BigDecimal(getTxtMultasPerdidasDoc().getText());
			
			if(!sumadescuadreEfectivo.equals(new BigDecimal("0.00"))) SpiritAlert.createAlert("Tiene valores de descuadre!", SpiritAlert.WARNING);
			if(!sumaMultasPerdDoc.equals(new BigDecimal("0.00"))) SpiritAlert.createAlert("Tiene multas por pérdidas de documentos", SpiritAlert.WARNING);
			
			Object[] options = { si, no };
		 
			int opcion = JOptionPane.showOptionDialog(null,"Esta seguro que desea cerrar la caja ?.... deudas por descuadre:"+sumadescuadreEfectivo+" ,multas:"+sumaMultasPerdDoc,
					"Confirmación",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,options, no);

			if (opcion == JOptionPane.YES_OPTION) {
				cerrar_caja();
			}


		}
	};

	ActionListener oActionListenerBotonBloquear= new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			update_bloquear();
		}
	};


	ActionListener oActionListenerBotonDESBloquear= new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			update_desbloquear();
		}
	};
	
	//efectivo
	KeyListener oKeyAdapterTbDetalleVentasEfectivo = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {	
			//	clean();

			update_Tabla_VentasEfectivo(idEfectivo,idTracks);
		}
	};	
	MouseListener oMouseAdapterTblDetalleVentasEfectivo = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			//	clean();
			GuardarDetalles();
			update_Tabla_VentasEfectivo(idEfectivo,idTracks);

		}
	};
	//tarjeta: TC_GC_CH:
	KeyListener oKeyAdapterTbDetalleVentasTarjeta = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {	
			GuardarDetalles();
			update_Tabla_VentasGeneral_Tc(idTc,"TARJETAC",idTracks);
		}
	};
	MouseListener oMouseAdapterTblDetalleVentasTarjeta = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {

			GuardarDetalles();
			update_Tabla_VentasGeneral_Tc(idTc,"TARJETAC",idTracks);		
		}
	};



	//gift cARDS
	KeyListener oKeyAdapterTbDetalleVentasGiftCards = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {	

			GuardarDetalles();
			update_Tabla_VentasGeneral_GC(idGc,"GIFTC",idTracks);;
		}
	};
	MouseListener oMouseAdapterTblDetalleVentasGiftCards = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {

			GuardarDetalles();
			update_Tabla_VentasGeneral_GC(idGc,"GIFTC",idTracks);		
		}
	};


	//credito Cliente
	KeyListener oKeyAdapterTbDetalleVentasCreditoC = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {		 

			GuardarDetalles();
			update_Tabla_Ventas_CreditoCliente(idCc,"CREDITOC",idTracks);
		}
	};
	MouseListener oMouseAdapterTblDetalleVentasCreditoC = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {

			GuardarDetalles();
			update_Tabla_Ventas_CreditoCliente(idCc,"CREDITOC",idTracks);
		}
	};


	//cheque
	KeyListener oKeyAdapterTbDetalleVentasCheque = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {		 

			GuardarDetalles();
			update_Tabla_VentasGeneral_Ch(idCheq,"CHEQUE",idTracks);
		}
	};
	MouseListener oMouseAdapterTblDetalleVentasCheque = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {

			GuardarDetalles();
			update_Tabla_VentasGeneral_Ch(idCheq,"CHEQUE",idTracks);
		}
	};

	//devoluciones
	KeyListener oKeyAdapterTbDetalleVentasDevoluciones = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {		

			GuardarDetalles();
			update_Tabla_Devoluciones();
		}
	};
	MouseListener oMouseAdapterTblDetalleVentasDevoluciones = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {

			GuardarDetalles();
			update_Tabla_Devoluciones();
		}
	};

	//caja INICIAL: tipo : efectivo
	KeyListener oKeyAdapterTbDetalleCajaInicial = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {		 
			clean();
			GuardarDetalles();
			caja_inicial();
		}
	};	
	MouseListener oMouseAdapterTblDetalleCajaInicial = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			clean();
			GuardarDetalles();
			caja_inicial();

		}
	};

	///CAJA INGRESOS
	KeyListener oKeyAdapterTbDetalleCajaIngresos = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {	
			//	clean();
			GuardarDetalles();
			caja_ingresosEgresos("I");

		}
	};	
	MouseListener oMouseAdapterTbDetalleCajaIngresos = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			//clean();
			GuardarDetalles();
			caja_ingresosEgresos("I");

		}
	};

	//caja EGRESOS
	KeyListener oKeyAdapterTbDetalleCajaEgresos = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {	
			//clean();
			GuardarDetalles();
			caja_ingresosEgresos("E");
		}
	};	
	MouseListener oMouseAdapterTbDetalleCajaEgresos = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			//clean();
			GuardarDetalles();
			caja_ingresosEgresos("E");

		}
	};



	//muestra detalles del valor de la caja inicial
	public void caja_inicial(){		
		limpiar_detalles();


		activarPanel("INI");

		getTblTablaDetalle().setModel(new DefaultTableModel(new Object[][] {}, new String[] {"Fecha Inicial", "Valor Inicial ($)", "Estado", "Descuadre", "Valor Final ($)", "Fecha Final"}) {
			boolean[] columnEditable = new boolean[] {false, false, false, false, false, false};
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return columnEditable[columnIndex];
			}
		});




		tableModel = (DefaultTableModel) getTblTablaDetalle().getModel();


		if(cajaSesion!=null){
			Vector<String> fila = new Vector<String>();		
//			fila.add(cajaSesion.getFechaini().toString()) ;
			String fecha=cajaSesion.getFechaini().toString();
			fecha=fecha.substring(0,16);
			//fila.add(cajaSesion.getFechaini().toString()) ;
			fila.add(fecha) ;

			fila.add(formatoDecimal.format(cajaSesion.getValorInicial()));
			fila.add(cajaSesion.getEstado());			


			if(cajaSesion.getDescuadre()==null)	{
				fila.add("-");			 
			}
			else fila.add(cajaSesion.getDescuadre().toString());

			if(cajaSesion.getValorFinal()==null)	{
				fila.add("-");			 
			}
			else fila.add(formatoDecimal.format(cajaSesion.getValorFinal()));



			if(cajaSesion.getFechafin()==null)	fila.add("-");
			else			fila.add(cajaSesion.getFechafin().toString());


			tableModel.addRow(fila);			
		}

	}


	DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
		private Color whiteColor = new Color(255, 255, 255);
		private Color grayColor = new Color(204, 204, 204);
		private Color blackColor = new Color(0, 0, 0);

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			c.setForeground(blackColor);

			if(column == 0)
				setHorizontalAlignment(JLabel.RIGHT);
			if(column == 1)
				setHorizontalAlignment(JLabel.RIGHT);
			if(column == 2)
				setHorizontalAlignment(JLabel.RIGHT);

			if(column == 4)
				setHorizontalAlignment(JLabel.RIGHT);
			/*if(column == 5)
        		setHorizontalAlignment(JLabel.RIGHT);
        	if(column == 6)
        		setHorizontalAlignment(JLabel.RIGHT);

        	if(((String) value).equals("<html><b>T O T A L E S :</b></html>") || ((String) value).equals("<html><b>S A L D O :</b></html>")) {
        		c.setBackground(grayColor);
        		setHorizontalAlignment(JLabel.RIGHT);
        	} else {
        		c.setBackground(whiteColor);
        	}

        	String detalle = (String) table.getValueAt(row, 3);
        	if ((column >= 0 && column <= 5) && (detalle.equals("<html><b>T O T A L E S :</b></html>") || detalle.equals("<html><b>S A L D O :</b></html>"))) {
        		c.setBackground(grayColor);
        	}*/

			return c;
		}
	};


	//Muestra los movimientos que se hacen en la caja 
	public void caja_ingresosEgresos(String tipo){ 
		limpiar_detalles();

		activarPanel(tipo);


		System.out.println("tipo"+tipo);
		if(tipo.equals("I")){
			getTblTablaDetalle().setModel(new DefaultTableModel(new Object[][] {}, new String[] {"Fecha Ingresos", "Valor ($)","Detalles","Check ","No. Papeleta","Id"}) 
			{
				boolean[] columnEditable = new boolean[] {false, false, false,true,true,false};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
				public Class getColumnClass(int c) {
					return getValueAt(0, c).getClass();
				}
			});	
		}
		if(tipo.equals("E")){
			getTblTablaDetalle().setModel(new DefaultTableModel(new Object[][] {}, new String[] {"Fecha Egresos", "Valor ($)","Detalles","Check ","No. Papeleta","Id"}) 
			{
				boolean[] columnEditable = new boolean[] {false, false, false,true,true,false};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
				public Class getColumnClass(int c) {
					return getValueAt(0, c).getClass();
				}
			});	
		}



		getTblTablaDetalle().getColumnModel().getColumn(0).setPreferredWidth(70);
		getTblTablaDetalle().getColumnModel().getColumn(0).setMinWidth(70);
		getTblTablaDetalle().getColumnModel().getColumn(0).setCellRenderer(cellRenderer);

		getTblTablaDetalle().getColumnModel().getColumn(1).setPreferredWidth(45);
		getTblTablaDetalle().getColumnModel().getColumn(1).setMinWidth(45);
		getTblTablaDetalle().getColumnModel().getColumn(1).setCellRenderer(cellRenderer);

		getTblTablaDetalle().getColumnModel().getColumn(2).setPreferredWidth(230);
		getTblTablaDetalle().getColumnModel().getColumn(2).setMinWidth(230);

		getTblTablaDetalle().getColumnModel().getColumn(3).setPreferredWidth(30);
		getTblTablaDetalle().getColumnModel().getColumn(3).setMinWidth(30);

		getTblTablaDetalle().getColumnModel().getColumn(4).setPreferredWidth(92);
		getTblTablaDetalle().getColumnModel().getColumn(4).setMinWidth(92);

		getTblTablaDetalle().getColumnModel().getColumn(5).setWidth(0);
		getTblTablaDetalle().getColumnModel().getColumn(5).setPreferredWidth(0);
		getTblTablaDetalle().getColumnModel().getColumn(5).setMinWidth(0);
		getTblTablaDetalle().getColumnModel().getColumn(5).setMaxWidth(0);

		getTblTablaDetalle().setEditingColumn(3);
		getTblTablaDetalle().setEditingColumn(4);


		tableModel = (DefaultTableModel) getTblTablaDetalle().getModel();
		boolean todoRevisado=true;

		Map aMap2 = new HashMap();
		aMap2.clear();	 
		aMap2.put("tipomovimiento", tipo);//ingreso	 
		aMap2.put("cajasesionId",cajaSesion.getId());//

		if(cajaSesion.getId()!=new Long("0")){	
			Iterator cajavalorIt2;
			try {
				cajavalorIt2 = SessionServiceLocator.getCajasesionMovimientosSessionService().findCajasesionMovimientosByQuery(aMap2).iterator();

				while (cajavalorIt2.hasNext()) {
					CajasesionMovimientosIf movimi = (CajasesionMovimientosIf) cajavalorIt2.next();		
					Vector<Object> fila = new Vector<Object>();		
					String fecha=movimi.getFecha().toString();
					fecha=fecha.substring(0,16);
					fila.add(fecha) ;//fecha
					fila.add(formatoDecimal.format(movimi.getValor())) ;

					String referencia="";
					String noPapeleta="";

					String documento=movimi.getNumDoc();
					if(documento==null) documento="";

					if(movimi.getCuentaId()!=null)
					{	
						referencia="Tx. Banco";
						referencia=obtenerBancoCuenta(movimi.getCuentaId());
					}
					if(movimi.getCajadestinoId()!=null)
					{					
						referencia="Tx. entre caja:";
						referencia=obtenerNombreCajaDestino(movimi.getCajadestinoId());
						if(documento.equals("")) documento="sin papeleta";
					}					
					fila.add(referencia);		

					String revisado=movimi.getRevisado();
					if(revisado==null) revisado="F";
					if(revisado.equals("T"))
						fila.add(new Boolean(true));
					else 
						fila.add(new Boolean(false));
					if(revisado.equals("F")) todoRevisado=false;


					if(documento.equals("")) todoRevisado=false;

					System.out.println("INGRESO>"+documento);

					fila.add(documento);				
					fila.add(movimi.getId());				
					tableModel.addRow(fila);						
				}

				if(!todoRevisado && tipo.equals("I") ) noCerrarCajaIng=true;
				if(todoRevisado && tipo.equals("I") ) noCerrarCajaIng=false;

				if(!todoRevisado && tipo.equals("E") ) noCerrarCajaEgr=true;
				if(todoRevisado && tipo.equals("E") ) noCerrarCajaEgr=false;


				if(tipo.equals("I") && !todoRevisado )setVisible2(true,getLblXIngresos());			
				if(tipo.equals("I") &&  todoRevisado )setVisible2(false,getLblXIngresos());
				if(tipo.equals("E") && !todoRevisado )setVisible2(true,getLblXEgresos());			
				if(tipo.equals("E") && todoRevisado ) setVisible2(false,getLblXEgresos());


			} catch (GenericBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	 
		}
	}


	//Devuelve el nombre de la Cuenta Y del Banco 
	public String obtenerBancoCuenta(Long idcuentaBancaria){
		String refer="";
		String cuenta="";
		String banco="";
		Iterator cajavalorIt2;
		try {
			cajavalorIt2 = SessionServiceLocator.getCuentaBancariaSessionService().findCuentaBancariaById(idcuentaBancaria).iterator();				
			if (cajavalorIt2.hasNext()) {
				CuentaBancariaIf cuentabancariaIf = (CuentaBancariaIf) cajavalorIt2.next();	
				cuenta=cuentabancariaIf.getCuenta();
				Long bancoId=cuentabancariaIf.getBancoId();
				Iterator cajavalorIt3;
				cajavalorIt3 = SessionServiceLocator.getBancoSessionService().findBancoById(bancoId).iterator();
				if (cajavalorIt3.hasNext()) {
					BancoIf bancoIf = (BancoIf) cajavalorIt3.next();
					banco=bancoIf.getNombre();
				}
			}
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		refer="Tx. Banco           :    "+banco+"  // Cta: "+cuenta;		
		return refer;
	}


	//devuelve el nombre de la caja destino
	public String obtenerNombreCajaDestino(Long idcajadestino){
		String refer="";		 
		Iterator cajavalorIt2;
		try {

			cajavalorIt2 = SessionServiceLocator.getCajaSessionService().findCajaById(idcajadestino).iterator();		
			if (cajavalorIt2.hasNext()) {
				CajaIf cajaIf = (CajaIf) cajavalorIt2.next();	
				refer=cajaIf.getCodigo()+"-"+cajaIf.getNombre();			
			}

		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		refer="Tx. Caja Destino:    "+refer;
		return refer;
	}



	//borra datos de la tabla
	public void limpiar_detalles(){		
		DefaultTableModel model = (DefaultTableModel) getTblTablaDetalle().getModel();
		for(int i= this.getTblTablaDetalle().getRowCount();i>0;--i)	model.removeRow(i-1); 
	}


	public void marcarPanel(JPanel cmp){
		cmp.setBorder(new LineBorder(Color.blue, 2));	
	}

	public void desmarcarPaneles(){
		Border compound = null;

		getPnCajaInicial().setBorder(compound);
		getPnIngresos().setBorder(compound);
		getPnEgresos().setBorder(compound);

		getPnEfectivo().setBorder(compound);
		getPnTarjetaC().setBorder(compound);
		getPnGiftC().setBorder(compound);
		getPnDevoluciones().setBorder(compound);
		getPnCheque().setBorder(compound);
		getPnCreditoC().setBorder(compound);

	}

	public void activarPanel(String tipoPago){

		desmarcarPaneles();

		System.out.println("EN ACTIVAR PANELES->"+tipoPago);

		if(tipoPago.equals("EFECTIVO")){ 
			getLblDetalleSeleccionado().setText("EF");
			marcarPanel(getPnEfectivo());		
		}
		if(tipoPago.equals("TARJETAC")) {
			getLblDetalleSeleccionado().setText("TC");
			marcarPanel(getPnTarjetaC());		
		}
		if(tipoPago.equals("GIFTC")){ 
			getLblDetalleSeleccionado().setText("GC");
			marcarPanel(getPnGiftC());		
		}
		if(tipoPago.equals("CREDITOC")){ 
			getLblDetalleSeleccionado().setText("CC");
			marcarPanel(getPnCreditoC());				
		}
		if(tipoPago.equals("CHEQUE")){ 
			getLblDetalleSeleccionado().setText("CH");
			marcarPanel(getPnCheque());		
		}
		if(tipoPago.equals("DEV")){ 
			getLblDetalleSeleccionado().setText("DEV");
			marcarPanel(getPnDevoluciones());				
		}
		if(tipoPago.equals("INI")){ 
			getLblDetalleSeleccionado().setText("INI");
			marcarPanel(getPnCajaInicial());				
		}
		if(tipoPago.equals("I")){ 
			getLblDetalleSeleccionado().setText("ING");
			marcarPanel(getPnIngresos());				
		}
		if(tipoPago.equals("E")){ 
			getLblDetalleSeleccionado().setText("EGR");
			marcarPanel(getPnEgresos());				
		}

	}



	//muetsra valores de ventas CON pagos realizadas CON T/C y por G.C, CREDC, cheque
	public void update_Tabla_Ventas_CreditoCliente(String TIPO_id,String tipoPago,Vector<String> id_ventastrack) {	
		//limpiar_detalles();	
		llenar_datos();
		System.out.println("EN LINK UPDATE"+tipoPago);

		activarPanel(tipoPago);




		getTblTablaDetalle().setModel(new DefaultTableModel(new Object[][] {}, new String[] {"Forma de Pago", "Fecha", "Observación", "Valor ($)"}) {
			boolean[] columnEditable = new boolean[] {false, false,false, false};
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return columnEditable[columnIndex];
			}
			public Class getColumnClass(int c) {
				return getValueAt(0, c).getClass();
			}
		});
		/////setear ancho de columnas////	
		TableColumn anchoColumna = getTblTablaDetalle().getColumnModel().getColumn(0);		
		anchoColumna.setMinWidth(50);
		anchoColumna = getTblTablaDetalle().getColumnModel().getColumn(1);
		anchoColumna.setMinWidth(50);
		anchoColumna = getTblTablaDetalle().getColumnModel().getColumn(2);
		anchoColumna.setMinWidth(90);


		tableModel = (DefaultTableModel) getTblTablaDetalle().getModel();	
		Map aMap2 = new HashMap();
		aMap2.clear(); 
		aMap2.put("tipo", new Long(TIPO_id));//	
		Vector<String> ventastrackId = new Vector<String>();
		//id_ventastrack tiene valores de todas las transacciones hechas en esa session
		ventastrackId=id_ventastrack;		
		aMap2.put("ventastrackId",ventastrackId);	
		if(cajaSesion.getId()!=new Long("0") && ventastrackId!=null && ventastrackId.size() > 0 ){
			Iterator cajavalorIt2;
			//clasifica por tipo y suma los valores.
			cajavalorIt2 = SessionServiceLocator.getVentasPagosSessionService().findVentasPagosByQueryVariosId(aMap2).iterator();
			while (cajavalorIt2.hasNext()) {
				VentasPagosIf pagos = (VentasPagosIf) cajavalorIt2.next();	
				Vector<Object> fila = new Vector<Object>();
				if(tipoPago.equals(new String("GIFTC")))
				{
					String cod_gc=pagos.getReferencia();
					int posicion=cod_gc.indexOf("(Código:");
					if(posicion!=-1)
					{
						String substring=cod_gc.substring(posicion);						
						int posicion2=substring.indexOf(")/");						
						String codigo=cod_gc.substring(posicion, posicion2+1);
						if (substring==null) substring="";
						fila.add("GC"+codigo);
					}
					else fila.add(tipoPago);

				}else{	
					anchoColumna = getTblTablaDetalle().getColumnModel().getColumn(0);
					anchoColumna.setMinWidth(0);
					anchoColumna.setMaxWidth(0);
					anchoColumna.setWidth(0);
					anchoColumna.setPreferredWidth(0);
					fila.add("");
				}

				String fecha=obtener_fecha_pago(pagos.getVentastrackId());
				fecha=fecha.substring(0,16);
				fila.add(fecha) ;//fecha					
				if(tipoPago.equals(new String("GIFTC")))
				{
					String observacion=pagos.getReferencia();					
					int posicion2=observacion.indexOf(")/");
					if(posicion2!=-1){
						observacion=observacion.substring(posicion2+2);
						fila.add(observacion);
					}
					else
						fila.add(pagos.getReferencia()) ;									
				}
				else{
					fila.add(pagos.getReferencia()) ;
				}			
				fila.add(formatoDecimal.format(pagos.getValor())) ;

				tableModel.addRow(fila);
			}
		}  

	}


	//OJO
	//muetsra valores de ventas CON pagos realizadas CON TARJETAC 
	public void update_Tabla_VentasGeneral_Tc(String TIPO_id,String tipoPago,Vector<String> id_ventastrack) {	
		//limpiar_detalles();	
		llenar_datos();
		System.out.println("EN LINK UPDATE"+tipoPago);

		activarPanel(tipoPago);

		//0-7

		getTblTablaDetalle().setModel(new DefaultTableModel(new Object[][] {}, new String[] {"Fecha", "Tarjeta", "Valor ($)","Check","Autoriz/Voucher","No. Refer.","Id"}) {
			boolean[] columnEditable = new boolean[] {false,false, false,true,false,false,false};
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return columnEditable[columnIndex];
			}
			public Class getColumnClass(int c) {
				return getValueAt(0, c).getClass();
			}
		});

		/////setear ancho de columnas////	
		TableColumn anchoColumna = getTblTablaDetalle().getColumnModel().getColumn(0);		
		anchoColumna.setMinWidth(50);
		anchoColumna = getTblTablaDetalle().getColumnModel().getColumn(1);
		anchoColumna.setMinWidth(50);
		anchoColumna = getTblTablaDetalle().getColumnModel().getColumn(2);
		anchoColumna.setMinWidth(90);


		anchoColumna = getTblTablaDetalle().getColumnModel().getColumn(6);
		anchoColumna.setMinWidth(0);
		anchoColumna.setMaxWidth(0);
		anchoColumna.setWidth(0);
		anchoColumna.setPreferredWidth(0);

		tableModel = (DefaultTableModel) getTblTablaDetalle().getModel();	
		Map aMap2 = new HashMap();
		aMap2.clear(); 
		aMap2.put("tipo", new Long(TIPO_id));//	
		Vector<String> ventastrackId = new Vector<String>();
		//id_ventastrack tiene valores de todas las transacciones hechas en esa session
		ventastrackId=id_ventastrack;		
		aMap2.put("ventastrackId",ventastrackId);	
		if(cajaSesion.getId()!=new Long("0") && ventastrackId!=null && ventastrackId.size() > 0 ){
			Iterator cajavalorIt2;
			//clasifica por tipo y suma los valores.
			cajavalorIt2 = SessionServiceLocator.getVentasPagosSessionService().findVentasPagosByQueryVariosId(aMap2).iterator();
			while (cajavalorIt2.hasNext()) {
				VentasPagosIf pagos = (VentasPagosIf) cajavalorIt2.next();	
				Vector<Object> fila = new Vector<Object>();
				///primera columna				
				//////1 columna :fecha
				String fecha=obtener_fecha_pago(pagos.getVentastrackId());
				fecha=fecha.substring(0,16);
				fila.add(fecha) ;//fecha		
				//////2-3 columna: nombre tc, saldos gc, banco cheque

				String referencia=pagos.getReferencia();
				String nombreTarjeta="";


				int posicion3=referencia.indexOf("/");
				if(posicion3!=-1)
					nombreTarjeta=referencia.substring(0,posicion3);

				fila.add(nombreTarjeta);				
				//3 columna: valor de la transaccion				
				fila.add(formatoDecimal.format(pagos.getValor())) ;

				//4 columna: si esta check
				String revisado=pagos.getRevisado();
				if(revisado==null) revisado="F";
				if(revisado.equals("T"))
					fila.add(new Boolean(true));
				else 
					fila.add(new Boolean(false));

				//5: no ref o numero de cuenta	
				String autorizVoucher="";
				int posicion2=referencia.indexOf("/Autoriz:");
				if(posicion2!=-1)					
					autorizVoucher=referencia.substring(posicion2+9);	
				fila.add(autorizVoucher);

				//6: 				
				String referTCautomatico=pagos.getNumDoc();
				if(referTCautomatico==null) referTCautomatico="";
				fila.add(referTCautomatico);

				//7: id de la transaccion ventas Pagos.
				fila.add(pagos.getId());

				tableModel.addRow(fila);
			}
		}  

	}

	//muetsra valores de ventas CON pagos realizadas CON CHEQUE
	public void update_Tabla_VentasGeneral_Ch(String TIPO_id,String tipoPago,Vector<String> id_ventastrack) {	
		//limpiar_detalles();	
		llenar_datos();
		System.out.println("EN LINK UPDATE"+tipoPago);

		activarPanel(tipoPago);


		getTblTablaDetalle().setModel(new DefaultTableModel(new Object[][] {}, new String[] {"Fecha","Valor ($)","Check","Banco","No. cheque","No. Cta.","Id"}) {
			boolean[] columnEditable = new boolean[] {false,false, true,false,false,false,false};
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return columnEditable[columnIndex];
			}
			public Class getColumnClass(int c) {
				return getValueAt(0, c).getClass();
			}
		});

		/////setear ancho de columnas////	
		TableColumn anchoColumna = getTblTablaDetalle().getColumnModel().getColumn(0);		
		anchoColumna.setMinWidth(50);
		anchoColumna = getTblTablaDetalle().getColumnModel().getColumn(1);
		anchoColumna.setMinWidth(50);
		anchoColumna = getTblTablaDetalle().getColumnModel().getColumn(2);
		anchoColumna.setMinWidth(90);


		anchoColumna = getTblTablaDetalle().getColumnModel().getColumn(6);
		anchoColumna.setMinWidth(0);
		anchoColumna.setMaxWidth(0);
		anchoColumna.setWidth(0);
		anchoColumna.setPreferredWidth(0);

		tableModel = (DefaultTableModel) getTblTablaDetalle().getModel();	
		Map aMap2 = new HashMap();
		aMap2.clear(); 
		aMap2.put("tipo", new Long(TIPO_id));//	
		Vector<String> ventastrackId = new Vector<String>();
		//id_ventastrack tiene valores de todas las transacciones hechas en esa session
		ventastrackId=id_ventastrack;		
		aMap2.put("ventastrackId",ventastrackId);	
		if(cajaSesion.getId()!=new Long("0") && ventastrackId!=null && ventastrackId.size() > 0 ){
			Iterator cajavalorIt2;
			//clasifica por tipo y suma los valores.
			cajavalorIt2 = SessionServiceLocator.getVentasPagosSessionService().findVentasPagosByQueryVariosId(aMap2).iterator();
			while (cajavalorIt2.hasNext()) {
				VentasPagosIf pagos = (VentasPagosIf) cajavalorIt2.next();	
				Vector<Object> fila = new Vector<Object>();
				///primera columna				
				//////1 columna :fecha
				String fecha=obtener_fecha_pago(pagos.getVentastrackId());
				fecha=fecha.substring(0,16);
				fila.add(fecha) ;//fecha		

				//2 columna: valor de la transaccion				
				fila.add(formatoDecimal.format(pagos.getValor())) ;

				//3 columna: si esta check
				String revisado=pagos.getRevisado();
				if(revisado==null) revisado="F";
				if(revisado.equals("T"))
					fila.add(new Boolean(true));
				else 
					fila.add(new Boolean(false));

				//4 columna
				String referencia=pagos.getReferencia();
				String nombreBanco="";

				int posicion3=referencia.indexOf("/");
				if(posicion3!=-1)
					nombreBanco=referencia.substring(0,posicion3);				
				fila.add(nombreBanco);				

				//5: no noCheque
				String noCheque="";
				int posicion2=referencia.indexOf("/No. Cheque:");
				if(posicion2!=-1)					
					noCheque=referencia.substring(posicion2+12);	
				fila.add(noCheque);

				//6: No. cta			
				String noCuenta=pagos.getNumDoc();
				if(noCuenta==null) noCuenta="";
				fila.add(noCuenta);

				//7: id de la transaccion ventas Pagos.
				fila.add(pagos.getId());

				tableModel.addRow(fila);
			}
		}  

	}

	//muetsra valores de ventas CON pagos realizadas CON CHEQUE
	public void update_Tabla_VentasGeneral_GC(String TIPO_id,String tipoPago,Vector<String> id_ventastrack) {	
		//limpiar_detalles();	
		llenar_datos();
		System.out.println("EN LINK UPDATE"+tipoPago);

		activarPanel(tipoPago);


		getTblTablaDetalle().setModel(new DefaultTableModel(new Object[][] {}, new String[] {"Fecha","Codigo GC:","Saldos GC","Valor ($)","Check","Id"}) {
			boolean[] columnEditable = new boolean[] {false,false, false,false,true,false};
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return columnEditable[columnIndex];
			}
			public Class getColumnClass(int c) {
				return getValueAt(0, c).getClass();
			}
		});

		/////setear ancho de columnas////	
		TableColumn anchoColumna = getTblTablaDetalle().getColumnModel().getColumn(0);		
		anchoColumna.setMinWidth(50);
		anchoColumna = getTblTablaDetalle().getColumnModel().getColumn(1);
		anchoColumna.setMinWidth(50);
		anchoColumna = getTblTablaDetalle().getColumnModel().getColumn(2);
		anchoColumna.setMinWidth(90);


		anchoColumna = getTblTablaDetalle().getColumnModel().getColumn(5);
		anchoColumna.setMinWidth(0);
		anchoColumna.setMaxWidth(0);
		anchoColumna.setWidth(0);
		anchoColumna.setPreferredWidth(0);

		tableModel = (DefaultTableModel) getTblTablaDetalle().getModel();	
		Map aMap2 = new HashMap();
		aMap2.clear(); 
		aMap2.put("tipo", new Long(TIPO_id));//	
		Vector<String> ventastrackId = new Vector<String>();
		//id_ventastrack tiene valores de todas las transacciones hechas en esa session
		ventastrackId=id_ventastrack;		
		aMap2.put("ventastrackId",ventastrackId);	
		if(cajaSesion.getId()!=new Long("0") && ventastrackId!=null && ventastrackId.size() > 0 ){
			Iterator cajavalorIt2;
			//clasifica por tipo y suma los valores.
			cajavalorIt2 = SessionServiceLocator.getVentasPagosSessionService().findVentasPagosByQueryVariosId(aMap2).iterator();
			while (cajavalorIt2.hasNext()) {
				VentasPagosIf pagos = (VentasPagosIf) cajavalorIt2.next();	
				Vector<Object> fila = new Vector<Object>();
				///primera columna				
				//////1 columna :fecha
				String fecha=obtener_fecha_pago(pagos.getVentastrackId());
				fecha=fecha.substring(0,16);
				fila.add(fecha) ;//fecha		
				//2
				String cod_gc=pagos.getReferencia();
				String codigo="";
				int posicion=cod_gc.indexOf("(Código:");
				if(posicion!=-1)
				{
					String substring=cod_gc.substring(posicion);						
					int posicion2=substring.indexOf(")/");						
					codigo=cod_gc.substring(posicion, posicion2+1);
					if (substring==null) substring="";

				}
				fila.add("GC"+codigo);
				// 3
				String saldos=pagos.getReferencia();					
				int posicion2=saldos.indexOf(")/");
				if(posicion2!=-1){
					saldos=saldos.substring(posicion2+2);					
				}
				fila.add(saldos);

				//4 columna: valor de la transaccion				
				fila.add(formatoDecimal.format(pagos.getValor())) ;



				//5 columna: si esta check
				String revisado=pagos.getRevisado();
				if(revisado==null) revisado="F";
				if(revisado.equals("T"))
					fila.add(new Boolean(true));
				else 
					fila.add(new Boolean(false));

				//6: id de la transaccion ventas Pagos.
				fila.add(pagos.getId());

				tableModel.addRow(fila);
			}
		}  

	}



	//dev //cocl /cctc 
	public BigDecimal getMultaDocumento(String codigo){		
		BigDecimal valorMulta=new BigDecimal("0.00");
		Long idDocumento=0L;
		try {
			Iterator itDocumento= SessionServiceLocator.getDocumentoSessionService().findDocumentoByCodigo(codigo).iterator();

			if(itDocumento.hasNext()){
				DocumentoIf documentoIf= (DocumentoIf)itDocumento.next();
				idDocumento=documentoIf.getId();
			}
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicación con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}

		//ojo		
		try {
			Iterator itMulta= SessionServiceLocator.getMultasDocumentosSessionService().findMultasDocumentosByDocumentoId(idDocumento).iterator();

			if(itMulta.hasNext()){
				MultasDocumentosIf multaDocIf= (MultasDocumentosIf)itMulta.next();
				valorMulta=multaDocIf.getValorMulta();
			}
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicación con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}

		return valorMulta;
	}




	public String obtener_fecha_pago(Long idtrackventas){
		String fecha="";

		Map aMap = new HashMap();
		aMap.clear();
		aMap.put("id", idtrackventas);		

		VentasTrackIf ventasTrackIf;
		try {
			ventasTrackIf = (VentasTrackIf)SessionServiceLocator.getVentasTrackSessionService().findVentasTrackByQuery(aMap).iterator().next();
			fecha=ventasTrackIf.getFechaVenta().toString();
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		return fecha;
	}


	public void update_Tabla_Devoluciones() {

		limpiar_detalles();
		llenar_datos();
		activarPanel("DEV");


		getTblTablaDetalle().setModel(new DefaultTableModel(new Object[][] {}, new String[] {"Fecha", "Valor ($)","Check", "Documento", "Id"}) {
			boolean[] columnEditable = new boolean[] {false, false,true,false,false};
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return columnEditable[columnIndex];
			}
			public Class getColumnClass(int c) {
				return getValueAt(0, c).getClass();
			}
		});

		tableModel = (DefaultTableModel) getTblTablaDetalle().getModel();



		TableColumn anchoColumna = getTblTablaDetalle().getColumnModel().getColumn(4);
		anchoColumna.setMinWidth(0);
		anchoColumna.setMaxWidth(0);
		anchoColumna.setWidth(0);
		anchoColumna.setPreferredWidth(0);

		//System.out.println(vectorDevoluciones.size()+"VECTOR DEVOLUCIONES!!"+((Vector)vectorDevoluciones.get(0)).get(2));

		if(vectorDevoluciones.size()>0){
			for(int i=0;i<vectorDevoluciones.size();i++){		
				Vector<Object> fila = new Vector<Object>();
				fila.add(((Vector)vectorDevoluciones.get(i)).get(0).toString());
				fila.add(((Vector)vectorDevoluciones.get(i)).get(1).toString());
				fila.add(((Vector)vectorDevoluciones.get(i)).get(2));
				fila.add(((Vector)vectorDevoluciones.get(i)).get(3).toString());
				fila.add(((Vector)vectorDevoluciones.get(i)).get(4).toString());

				System.out.println("en table moDEL!!!!!!!!!!!!!!!!!!!!!");
				tableModel.addRow(fila);
			}
		}
	}


	public void update_Tabla_VentasEfectivo(String TIPO_id,Vector<String> id_ventastrack) {

		limpiar_detalles();
		llenar_datos();
		activarPanel("EFECTIVO");

		getTblTablaDetalle().setModel(new DefaultTableModel(new Object[][] {}, new String[] {"Fecha", "Observación", "Valor ($)"}) {
			boolean[] columnEditable = new boolean[] {false,false, false};
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return columnEditable[columnIndex];
			}
		});

		tableModel = (DefaultTableModel) getTblTablaDetalle().getModel();

		Map aMap2 = new HashMap();
		aMap2.clear(); 
		aMap2.put("tipo", new Long(TIPO_id));//en efectivo



		Vector<String> ventastrackId = new Vector<String>();
		ventastrackId=id_ventastrack;

		aMap2.put("ventastrackId",ventastrackId);
		if(cajaSesion.getId()!=new Long("0") && ventastrackId!=null && ventastrackId.size() > 0 ){	
			Iterator cajavalorIt2=null;
			cajavalorIt2 = SessionServiceLocator.getVentasPagosSessionService().findVentasPagosByQueryVariosId(aMap2).iterator();

			while (cajavalorIt2.hasNext()) {
				VentasPagosIf pagos = (VentasPagosIf) cajavalorIt2.next();
				if(!pagos.getReferencia().equals("Movimiento ingreso") && !pagos.getReferencia().equals("Movimiento egreso"))
				{
					Vector<String> fila = new Vector<String>();
					String fecha=obtener_fecha_pago(pagos.getVentastrackId());
					fecha=fecha.substring(0,16);
					fila.add(fecha) ;//fecha									
					fila.add(pagos.getReferencia().toString()) ;
					fila.add(formatoDecimal.format(pagos.getValor())) ;					
					fila.add("Cliente 1") ;					
					tableModel.addRow(fila);
				}						    		 
			}
		} 
	}


	@Override
	public void clean() {
		// TODO Auto-generated method stub
		desmarcarPaneles();		
		getLblDetalleSeleccionado().setVisible(false);
		getTxtDevoluciones().setText("0.00");
		getTxtEfectivo().setText("0.00");
		getTxtGiftCards().setText("0.00");	
		getTxtTarjeta().setText("0.00");
		getTxtDevoluciones().setText("0.00");
		getTxtCreditoCliente().setText("0.00");
		getTxtCajaEgresos().setText("0.00");
		getTxtCajaIngresos().setText("0.00");
		getTxtCajaInicial().setText("0.00");
		
		DefaultTableModel model = (DefaultTableModel) getTblTablaDetalle().getModel();
		for(int i= this.getTblTablaDetalle().getRowCount();i>0;--i)			model.removeRow(i-1);
		
		vectorDevoluciones.clear();		
		idTracks.clear();	
		//llena los datos y los detalles
		getLblIconAlert().setText("");
		getTxtDescuadreEfectivo().setText("0.00");
		getTxtDescuadreDoc().setText("0.00");
		getTxtMultasPerdidasDoc().setText("0.00");
		
		llenar_datos();	
		 
	}


	public void cerrar_caja() {
		// TODO Auto-generated method stub
		if(noCerrarCajaIng || noCerrarCajaEgr){			
			SpiritAlert.createAlert("No puede cerrar la caja... Verifique que ingreso datos en Ingreso y Egresos de Caja!!", SpiritAlert.INFORMATION);			
		}else{
			Map aMap = new HashMap();
			aMap.clear();
			Long usuario_id=((UsuarioIf)Parametros.getUsuarioIf()).getId();
			aMap.put("usuarioId", usuario_id);
			aMap.put("estado", "A");

			try {
				//if(!getCajaSesionSessionService().findCajaSesionByQuery(aMap).isEmpty())
				//{				
					Iterator cajavalorIt = SessionServiceLocator.getCajaSesionSessionService().findCajaSesionByQuery(aMap).iterator();
					if (cajavalorIt.hasNext()) {					
						if(getTxtCantidadTeorica().getText().equals("")) getTxtCantReal().setText("0.00");
						if(getTxtCantReal().getText().equals("")) getTxtCantReal().setText("0.00");	
						if(getTxtDescuadreEfectivo().getText().equals("")) getTxtDescuadreEfectivo().setText("0.00");	
						getBtnBloquearCaja().setEnabled(true);
						getBtnCerrarCaja().setEnabled(true);
						//////////////////PIDE USUARIO SUPERVISOR
						/*jdAutorizacionModel = new AutorizacionModel(Parametros.getMainFrame());
						jdAutorizacionModel.addWindowListener(wl);
						int x = (Toolkit.getDefaultToolkit().getScreenSize().width - 730) / 2;
						int y = (Toolkit.getDefaultToolkit().getScreenSize().height - 650) / 2;
						jdAutorizacionModel.setLocation(x, y);
						jdAutorizacionModel.pack();
						jdAutorizacionModel.setModal(true);
						jdAutorizacionModel.setVisible(true);
						System.out.println("jdAutorizacionModel---"+jdAutorizacionModel.correctousr);
						if(jdAutorizacionModel.correctousr)
						*/
						if(true)			
						{
						 	////////////registra datos finales de la caja
							CajaSesionIf valor_actual = (CajaSesionIf) cajavalorIt.next();
							valor_actual.setEstado("C");
							//valor_actual.setFechafin(new java.sql.Timestamp(new Timestamp().getDateTime()));
							valor_actual.setFechafin(new java.sql.Timestamp(new java.util.Date().getTime()));
							
							valor_actual.setValorFinal(new BigDecimal(Utilitarios.removeDecimalFormat(getTxtCantReal().getText())));
							valor_actual.setDescuadre(new BigDecimal(Utilitarios.removeDecimalFormat(getTxtDescuadreEfectivo().getText())));
							valor_actual.setValorMultas(new BigDecimal(Utilitarios.removeDecimalFormat(getTxtMultasPerdidasDoc().getText())));
							valor_actual.setValorDocumentos(new BigDecimal(Utilitarios.removeDecimalFormat(getTxtDescuadreDoc().getText())));
							////// se ejecutan los asientos 
							BigDecimal faltantesCaja=new BigDecimal("0.00");
							BigDecimal multasPerdidasDoc=new BigDecimal("0.00");
							BigDecimal sobrantesCaja=new BigDecimal("0.00");
							
							BigDecimal sumadescuadreEfectivo=new BigDecimal(getTxtDescuadreEfectivo().getText());
							BigDecimal sumadescuadreDoc=new BigDecimal(getTxtDescuadreDoc().getText());
							BigDecimal sumaMultasPerdDoc=new BigDecimal(getTxtMultasPerdidasDoc().getText());							
							// -1 negativo // 0 zero  // 1 positivo
							int tipo = sumadescuadreEfectivo.signum();
							// hay sobrante de caja es decir saldo positivo
							if(tipo==1) 
								{
								sobrantesCaja=sumadescuadreEfectivo;
								faltantesCaja=sumadescuadreDoc;
								}
							// no hay descuadre de efectivo
							if(tipo==0)		faltantesCaja=sumadescuadreDoc;
							//si hay descuadre de efectivo
							if(tipo==-1)	faltantesCaja=sumadescuadreEfectivo.abs().add(sumadescuadreDoc);
							
						 
							HashMap<String, BigDecimal> mapaAsientos=new HashMap<String, BigDecimal>();
							
							mapaAsientos.put("SDC", sobrantesCaja);
							mapaAsientos.put("FCA", faltantesCaja);
							mapaAsientos.put("MPP", sumaMultasPerdDoc);
							SessionServiceLocator.getCajaSesionSessionService().cerrarCaja(empleado.toString(),valor_actual,mapaAsientos,Parametros.getIdEmpresa(), Parametros.getIdOficina());
							
							//////////registrar valor inicial para la proxima caja
							//CajaSesionIf caja = registrarCajaRegistradora();
							CajaSesionData caja = registrarCajaRegistradora();
							SessionServiceLocator.getCajaSesionSessionService().crearCaja(caja);							
							SpiritAlert.createAlert("Caja cerrada con EXITO!!", SpiritAlert.INFORMATION);
							//deshabilita ciertos botones
							getTxtCantReal().setEnabled(false);					
							getBtnBloquearCaja().setEnabled(false);
							getBtnCerrarCaja().setEnabled(false);	
							
							
							
						}
						else{
							SpiritAlert.createAlert("El usuario no tiene permisos para Cerrar Caja o .", SpiritAlert.INFORMATION);		
						}					
					}
					else
					{
						getBtnBloquearCaja().setEnabled(false);
						getBtnCerrarCaja().setEnabled(false);
						SpiritAlert.createAlert("No tiene una CAJA ABIERTA", SpiritAlert.INFORMATION);
					}
				/*}
				else
				{
					getBtnBloquearCaja().setEnabled(false);
					getBtnCerrarCaja().setEnabled(false);
					
				}*/
			} catch (GenericBusinessException e) {
				// TODO Auto-generated catch block
				SpiritAlert.createAlert("Se ha producido un error, No se cerro la caja", SpiritAlert.ERROR);

				e.printStackTrace();
			}
		}
	}


	public CajaSesionData registrarCajaRegistradora(){
		CajaSesionData cajaPosn = new CajaSesionData();
		Long cajaPC_id=caj.getId();
		String valor_inicial=getTxtCantReal().getText();
		cajaPosn.setCajaId(cajaPC_id);
//		cajaPosn.setFechaini(new java.sql.Timestamp(new Timestamp().getDateTime()));
		cajaPosn.setFechaini(new java.sql.Timestamp(new java.util.Date().getTime()));
		cajaPosn.setEstado("I");	 
		cajaPosn.setValorInicial(new BigDecimal(Utilitarios.removeDecimalFormat(valor_inicial)));		
		return cajaPosn;
	}


	public void update_bloquear() {
		// TODO Auto-generated method stub
		Map aMap = new HashMap();
		aMap.clear();
		Long usuario_id=((UsuarioIf)Parametros.getUsuarioIf()).getId();
		aMap.put("usuarioId", usuario_id);
		aMap.put("estado", "A");
		try {
			if(!SessionServiceLocator.getCajaSesionSessionService().findCajaSesionByQuery(aMap).isEmpty())
			{				
				Iterator cajavalorIt = SessionServiceLocator.getCajaSesionSessionService().findCajaSesionByQuery(aMap).iterator();
				if (cajavalorIt.hasNext()) {
					getBtnBloquearCaja().setEnabled(true);
					getBtnCerrarCaja().setEnabled(true);
					//////////////////PIDE USUARIO SUPERVISOR
					jdAutorizacionModel = new AutorizacionModel(Parametros.getMainFrame());
					jdAutorizacionModel.addWindowListener(wl);
					int x = (Toolkit.getDefaultToolkit().getScreenSize().width - 730) / 2;
					int y = (Toolkit.getDefaultToolkit().getScreenSize().height - 650) / 2;
					jdAutorizacionModel.setLocation(x, y);
					jdAutorizacionModel.pack();
					jdAutorizacionModel.setModal(true);
					jdAutorizacionModel.setVisible(true);
					if(jdAutorizacionModel.correctousr)			
					{
						///////////////////////////////
						CajaSesionData valor_actual = (CajaSesionData) cajavalorIt.next();
						valor_actual.setEstado("B");
						//////////////////////////////////
						SessionServiceLocator.getCajaSesionSessionService().saveCajaSesion(valor_actual);					
						getBtnBloquearCaja().setEnabled(false);	
						getBtnDesbloquear().setEnabled(true);				
						getBtnCerrarCaja().setEnabled(false);					
						SpiritAlert.createAlert("Caja BLOQUEADA con EXITO!!", SpiritAlert.INFORMATION);	
					}	
					else{
						SpiritAlert.createAlert("El usuario no tiene permisos para Cerrar Caja.", SpiritAlert.INFORMATION);		
					}
				}
				else
				{
					getBtnBloquearCaja().setEnabled(false);
					getBtnCerrarCaja().setEnabled(false);
				}
			}
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




	public void update_desbloquear() {
		// TODO Auto-generated method stub
		Map aMap = new HashMap();
		aMap.clear();
		Long usuario_id=((UsuarioIf)Parametros.getUsuarioIf()).getId();
		aMap.put("usuarioId", usuario_id);
		aMap.put("estado", "B");
		try {
			if(!SessionServiceLocator.getCajaSesionSessionService().findCajaSesionByQuery(aMap).isEmpty())
			{
				Iterator cajavalorIt = SessionServiceLocator.getCajaSesionSessionService().findCajaSesionByQuery(aMap).iterator();
				if (cajavalorIt.hasNext()) {
					getBtnBloquearCaja().setEnabled(true);
					getBtnCerrarCaja().setEnabled(true);
					//////////////////PIDE USUARIO SUPERVISOR
					jdAutorizacionModel = new AutorizacionModel(Parametros.getMainFrame());
					jdAutorizacionModel.addWindowListener(wl);
					int x = (Toolkit.getDefaultToolkit().getScreenSize().width - 730) / 2;
					int y = (Toolkit.getDefaultToolkit().getScreenSize().height - 650) / 2;
					jdAutorizacionModel.setLocation(x, y);
					jdAutorizacionModel.pack();
					jdAutorizacionModel.setModal(true);
					jdAutorizacionModel.setVisible(true);
					if(jdAutorizacionModel.correctousr)			
					{
						////////////////////////////////
						CajaSesionData valor_actual = (CajaSesionData) cajavalorIt.next();
						valor_actual.setEstado("A");
						SessionServiceLocator.getCajaSesionSessionService().saveCajaSesion(valor_actual);
						getBtnDesbloquear().setEnabled(false);									
						getBtnBloquearCaja().setEnabled(true);					
						getBtnCerrarCaja().setEnabled(true);
						SpiritAlert.createAlert("Caja DESBLOQUEADA con EXITO!!", SpiritAlert.INFORMATION);
					}
					else{
						SpiritAlert.createAlert("El usuario no tiene permisos para Cerrar Caja.", SpiritAlert.INFORMATION);		
					}
				}
				else
				{
					getBtnBloquearCaja().setEnabled(false);
					getBtnCerrarCaja().setEnabled(false);
				}
			}
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}

 
 
	@Override
	public void duplicate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void find() {
		// TODO Auto-generated method stub

	}


	public void report() {
		// TODO Auto-generated method stub

	}


	public void addDetail() {
		// TODO Auto-generated method stub

	}


	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}


	public void refresh() {
		// TODO Auto-generated method stub


		llenar_datos();

	}


	public void showFindMode() {
		// TODO Auto-generated method stub

	}


	public void showUpdateMode() {
		// TODO Auto-generated method stub

	}


	public void updateDetail() {
		// TODO Auto-generated method stub

	}
	
	public void deleteDetail() {
		
	}



	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

	}


	public void showSaveMode() {
		// TODO Auto-generated method stub
		setSaveMode();
		clean();

	}

	@Override
	public void update() {
	}



}
