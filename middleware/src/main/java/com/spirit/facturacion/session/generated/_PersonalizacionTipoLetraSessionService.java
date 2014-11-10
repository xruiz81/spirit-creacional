package com.spirit.facturacion.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PersonalizacionTipoLetraSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.facturacion.entity.PersonalizacionTipoLetraIf addPersonalizacionTipoLetra(com.spirit.facturacion.entity.PersonalizacionTipoLetraIf model) throws GenericBusinessException;

   void savePersonalizacionTipoLetra(com.spirit.facturacion.entity.PersonalizacionTipoLetraIf model) throws GenericBusinessException;

   void deletePersonalizacionTipoLetra(java.lang.Long id) throws GenericBusinessException;

   Collection findPersonalizacionTipoLetraByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.facturacion.entity.PersonalizacionTipoLetraIf getPersonalizacionTipoLetra(java.lang.Long id) throws GenericBusinessException;

   Collection getPersonalizacionTipoLetraList() throws GenericBusinessException;

   Collection getPersonalizacionTipoLetraList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPersonalizacionTipoLetraListSize() throws GenericBusinessException;
    java.util.Collection findPersonalizacionTipoLetraById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPersonalizacionTipoLetraByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findPersonalizacionTipoLetraByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findPersonalizacionTipoLetraByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
