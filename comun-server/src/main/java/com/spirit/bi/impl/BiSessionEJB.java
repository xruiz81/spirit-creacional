package com.spirit.bi.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.spirit.bi.BiSessionRemote;
import com.spirit.bi.EJBDataSource;
import com.spirit.bi.FactTable;
import com.spirit.bi.Statistics;
import com.spirit.bi.cubes.facturacion.FacturacionFact;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.comun.querys.QueryHelperServerLocal;
import com.spirit.facturacion.session.FacturaDetalleSessionLocal;

@Stateless
public class BiSessionEJB implements BiSessionRemote,BiSessionLocal {
	@EJB
	private JPAManagerLocal jpaManagerLocal;

	@EJB
	private FacturaDetalleSessionLocal facturaDetalleSessionLocal;

	@EJB
	private QueryHelperServerLocal queryHelperServerLocal;

	private List<FactTable> listaFactTables = new ArrayList<FactTable>();

	private void addFactTable() {
		listaFactTables.add(new FacturacionFact());
	}

	private void exec(boolean update) {
		try {
			Statistics.reset();
			long inicio = System.currentTimeMillis() / 1000;
			EJBDataSource.setJpaManagerLocal(jpaManagerLocal);
			EJBDataSource.setQueryHelperServerLocal(queryHelperServerLocal);
			EJBDataSource
					.setFacturaDetalleSessionLocal(facturaDetalleSessionLocal);
			if (listaFactTables.isEmpty())
				addFactTable();

			for (FactTable f : listaFactTables) {
				if (update) {
					//COMO VEO SI YA ESTA CARGADA??
					f.update();
				} else {
					f.clean();
					f.cleanDimensions();
					f.doFill();
				}
			}

			Statistics.print();
			long fin = System.currentTimeMillis() / 1000;
			System.out.println("FIN: " + (fin - inicio));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void buildBIModel() {
		exec(false);
	}

	public void updateBIModel() {
		exec(true);
	}
}
