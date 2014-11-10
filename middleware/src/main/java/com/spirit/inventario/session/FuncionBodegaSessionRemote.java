package com.spirit.inventario.session;

import javax.ejb.Remote;
import com.spirit.inventario.session.FuncionBodegaSessionService;
import com.spirit.inventario.session.generated._FuncionBodegaSessionService;
/**
 *
 * @author  www.versality.com.ec
 *
 */
@Remote
public interface FuncionBodegaSessionRemote extends FuncionBodegaSessionService,_FuncionBodegaSessionService {
}
