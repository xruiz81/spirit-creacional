package com.spirit.compras.gui.util;

import java.awt.Component;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.AbstractCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import com.spirit.client.SpiritAlert;
import com.spirit.compras.entity.CompraAsociadaGastoIf;
import com.spirit.compras.entity.CompraDetalleGastoIf;
import com.spirit.compras.entity.CompraDetalleIf;
import com.spirit.compras.entity.CompraGastoData;
import com.spirit.compras.entity.CompraGastoIf;
import com.spirit.compras.entity.GastoIf;
import com.spirit.compras.gastos.CompraAsociadaGastoClase;
import com.spirit.compras.gastos.CompraDetalleGastoClase;
import com.spirit.compras.gastos.CompraGastoClase;
import com.spirit.compras.gui.model.CompraModel;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class TextFieldCellEditor extends AbstractCellEditor implements TableCellEditor {

	private static final long serialVersionUID = -5562781699698331239L;

	public final static int NORMAL = 0;
	public final static int NUMERICO = 0;
	
	public static final int CABECERA = 0;
	public static final int DETALLE = 1;
	
	JTextField campo = new JTextField();
	int tipo = 0;
	int tipoTabla = 0;
	Double valorAntiguo = null;
	Double valor = null;
	int indiceSeleccionado = 0;
	String nombreTipoPago = null;
	
	CompraGastoClase compraGastoClase = null;
	
	Map<String, GastoIf> mapaGastoPorNombre = null;
	Map<Long, CompraGastoIf> mapaCompraGasto = null;
	Map<Long, CompraDetalleGastoClase> mapaCompraDetalleGastoClase = null;
	CompraGastoIf compraGasto = null;
	GastoIf gasto = null;
	
	Map<Long,String> mapaProductoNombre = null;
	Map<CompraDetalleIf, CompraDetalleGastoIf> mapaCompraDetalleGasto = null;
	CompraDetalleGastoIf compraDetalleGasto = null;
	
	public TextFieldCellEditor( int tipoTabla,int tipo, int maxCaracteres) {
		this.tipo = tipo;
		campo.addKeyListener(new TextChecker(maxCaracteres));
		if ( tipo == NUMERICO ){
			campo.addKeyListener(new NumberTextFieldDecimal());
		}
		this.tipoTabla = tipoTabla;
	}
	
	public Object getCellEditorValue() {
		try{
			valor = Double.valueOf( Utilitarios.removeDecimalFormat(campo.getText()) );
			if (campo.getText()==null || "".equals(campo.getText())
				|| valor == 0D ){				
				if ( tipoTabla == CABECERA ){
					CompraGastoIf cg = mapaCompraGasto.get(gasto.getId());
					Double valorGasto = Utilitarios.redondeoValor(cg.getValor().doubleValue());
					
					Map<CompraDetalleIf, CompraDetalleGastoIf> mapaDetalleGastos = mapaCompraDetalleGastoClase.get(gasto.getId()).getDetalle();
					Map<Long, CompraAsociadaGastoClase> mapaComprasAsociadas = compraGastoClase.getMapaComprasAsociadas();
					
					if ( valor == 0D && valorGasto > 0D && 
						 (mapaDetalleGastos.size() > 0 ||  mapaComprasAsociadas.size() > 0) &&
						 confirmacionEncerarValor() == false ){
						valor = valorAntiguo;
					} else {
						if ( compraGasto.getId() != null )
							compraGastoClase.getListaEliminacionComprasGastos().add(compraGasto);
						compraGasto = new CompraGastoData();
						compraGasto.setGastoId(gasto.getId());
						compraGasto.setValor(BigDecimal.ZERO);
						
						CompraDetalleGastoClase claseEliminacion = mapaCompraDetalleGastoClase.get(gasto.getId()); 
						mapaCompraDetalleGastoClase.put(gasto.getId(), new CompraDetalleGastoClase(compraGasto));
						ArrayList<CompraDetalleGastoIf> listaEliminacionDetalle = compraGastoClase.getListaEliminacionComprasDetalleGastos();
						Map<CompraDetalleIf,CompraDetalleGastoIf> mapaEliminacion = claseEliminacion.getDetalle();
						for ( CompraDetalleIf cd : mapaEliminacion.keySet() ){
							CompraDetalleGastoIf cdg = mapaEliminacion.get(cd);
							if ( cdg.getId() != null )
								listaEliminacionDetalle.add(cdg);
						}
						
						CompraAsociadaGastoClase cagc = mapaComprasAsociadas.get(gasto.getId());
						mapaComprasAsociadas.put(gasto.getId(), new CompraAsociadaGastoClase());
						if ( cagc != null ){
							ArrayList<CompraAsociadaGastoIf> listaEliminacionComprasAsociadas = compraGastoClase.getListaEliminacionComprassAsociadas();
							ArrayList<CompraAsociadaGastoIf> comprasAsociadas = cagc.getDetalle();
							for ( CompraAsociadaGastoIf cag : comprasAsociadas){
								if ( cag.getId() != null )
									listaEliminacionComprasAsociadas.add(cag);
							}
						}
					}
				}
			} else {
				valor = Double.valueOf(campo.getText());
			}
			
			campo.setText(valor.toString());
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		if ( tipoTabla == CABECERA ){
			compraGasto.setValor(new BigDecimal(valor));
			mapaCompraGasto.put(gasto.getId(), compraGasto);
		} else if (  tipoTabla == DETALLE  ){
			if ( compraDetalleGasto != null )
				compraDetalleGasto.setValor(new BigDecimal(valor));
			else {
				SpiritAlert.createAlert("Detalle no identificado !!", SpiritAlert.ERROR);
				valor = 0D;
			}
		}
		return valor;
	}

	public Component getTableCellEditorComponent(JTable table,
			Object value,boolean isSelected,int row,int column) {
		
		String nombreGasto = "";
		gasto = null;
		
		if (tipoTabla == CABECERA ){
			nombreGasto = (String)table.getValueAt(row, CompraModel.COLUMNA_GASTO_NOMBRE_GASTO );
			gasto = mapaGastoPorNombre.get(nombreGasto);
		}
		
		try{
			valorAntiguo = 0D;
			if ( value instanceof String )
				valorAntiguo = Double.parseDouble((Utilitarios.removeDecimalFormat((String)value)));
			else if ( value instanceof Double  )
				valorAntiguo = (Double)value;
			valor = valorAntiguo;
			campo.setText(valor.toString());
		} catch(Exception e){
			
		}
				
		if( tipoTabla == CABECERA ){
			compraGasto = mapaCompraGasto.get(gasto.getId());
		} else if (  tipoTabla == DETALLE  ){
			compraDetalleGasto = establecerValorMapaDetalle(row);
		}
		return campo;
	}
	
	private CompraDetalleGastoIf establecerValorMapaDetalle(int fila){
		int contador = 0;
		for ( CompraDetalleIf cd: mapaCompraDetalleGasto.keySet() ){
			CompraDetalleGastoIf cdg = mapaCompraDetalleGasto.get(cd);
			if ( contador == fila ){
				return cdg;
			}
			contador++;
		}
		return null;
	}
	
	private boolean confirmacionEncerarValor(){
		Object[] options = {"Si","No"}; 
		int opcion = JOptionPane.showOptionDialog(
				null,"¿Está seguro de que desea encerar Valor de gasto?" +
						"\nLos detalles de gasto y compras asociadas serán eliminadas también.", "Confirmación"
				,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE
				,null,options,"No");
		return opcion == JOptionPane.YES_OPTION ? true : false;
	}

	public Map<Long, CompraGastoIf> getMapaCompraGasto() {
		return mapaCompraGasto;
	}

	public void setCompraGasto(CompraGastoClase cgc) {
		this.compraGastoClase = cgc;
		this.mapaCompraGasto = cgc.getMapaComprasGastos();
		this.mapaCompraDetalleGastoClase = cgc.getMapaCompraDetalleGasto();
	}

	public Map<String, GastoIf> getMapaGastoPorNombre() {
		return mapaGastoPorNombre;
	}

	public void setMapaGastoPorNombre(Map<String, GastoIf> mapaGastoPorNombre) {
		this.mapaGastoPorNombre = mapaGastoPorNombre;
	}

	public Map<CompraDetalleIf, CompraDetalleGastoIf> getMapaCompraDetalleGasto() {
		return mapaCompraDetalleGasto;
	}

	public void setMapaCompraDetalleGasto(
			Map<CompraDetalleIf, CompraDetalleGastoIf> mapaCompraDetalleGasto) {
		this.mapaCompraDetalleGasto = mapaCompraDetalleGasto;
	}

	public Map<Long, String> getMapaProductoNombre() {
		return mapaProductoNombre;
	}

	public void setMapaProductoNombre(Map<Long, String> mapaProductoNombre) {
		this.mapaProductoNombre = mapaProductoNombre;
	}
}
