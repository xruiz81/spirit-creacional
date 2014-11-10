package com.spirit.inventario.session;

import javax.ejb.Local;
import com.spirit.inventario.session.ProductoSessionService;
import com.spirit.inventario.session.generated._ProductoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Local
public interface ProductoSessionLocal extends ProductoSessionService,_ProductoSessionService{
}
