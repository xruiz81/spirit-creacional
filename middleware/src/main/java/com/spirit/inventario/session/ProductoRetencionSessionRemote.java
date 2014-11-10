package com.spirit.inventario.session;

import javax.ejb.Remote;
import com.spirit.inventario.session.ProductoRetencionSessionService;
import com.spirit.inventario.session.generated._ProductoRetencionSessionService;
/**
 *
 * @author  www.versality.com.ec
 *
 */
@Remote
public interface ProductoRetencionSessionRemote extends ProductoRetencionSessionService,_ProductoRetencionSessionService {
}
