package com.spirit.inventario.session;

import javax.ejb.Remote;
import com.spirit.inventario.session.ProductoSessionService;
import com.spirit.inventario.session.generated._ProductoSessionService;
/**
 *
 * @author  www.versality.com.ec
 *
 */
@Remote
public interface ProductoSessionRemote extends ProductoSessionService,_ProductoSessionService {
}
