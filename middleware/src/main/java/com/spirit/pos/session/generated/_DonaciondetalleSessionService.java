package com.spirit.pos.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _DonaciondetalleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.pos.entity.DonaciondetalleIf addDonaciondetalle(com.spirit.pos.entity.DonaciondetalleIf model) throws GenericBusinessException;

   void saveDonaciondetalle(com.spirit.pos.entity.DonaciondetalleIf model) throws GenericBusinessException;

   void deleteDonaciondetalle(java.lang.Long id) throws GenericBusinessException;

   Collection findDonaciondetalleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.pos.entity.DonaciondetalleIf getDonaciondetalle(java.lang.Long id) throws GenericBusinessException;

   Collection getDonaciondetalleList() throws GenericBusinessException;

   Collection getDonaciondetalleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getDonaciondetalleListSize() throws GenericBusinessException;
    java.util.Collection findDonaciondetalleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findDonaciondetalleByDescripcion(java.lang.String descripcion) throws GenericBusinessException;
    java.util.Collection findDonaciondetalleByFecha(java.sql.Timestamp fecha) throws GenericBusinessException;
    java.util.Collection findDonaciondetalleByCant(java.math.BigDecimal cant) throws GenericBusinessException;
    java.util.Collection findDonaciondetalleByDev(java.math.BigDecimal dev) throws GenericBusinessException;
    java.util.Collection findDonaciondetalleByCantfinal(java.math.BigDecimal cantfinal) throws GenericBusinessException;
    java.util.Collection findDonaciondetalleByValortipo(java.math.BigDecimal valortipo) throws GenericBusinessException;
    java.util.Collection findDonaciondetalleByTotal(java.math.BigDecimal total) throws GenericBusinessException;
    java.util.Collection findDonaciondetalleByFundacion(java.lang.String fundacion) throws GenericBusinessException;
    java.util.Collection findDonaciondetalleByFundacionid(java.lang.Long fundacionid) throws GenericBusinessException;
    java.util.Collection findDonaciondetalleByModeloId(java.lang.Long modeloId) throws GenericBusinessException;
    java.util.Collection findDonaciondetalleByColorId(java.lang.Long colorId) throws GenericBusinessException;
    java.util.Collection findDonaciondetalleByTipoproducto(java.lang.Long tipoproducto) throws GenericBusinessException;
    java.util.Collection findDonaciondetalleByTallaId(java.lang.Long tallaId) throws GenericBusinessException;
    java.util.Collection findDonaciondetalleByNombrecliente(java.lang.String nombrecliente) throws GenericBusinessException;
    java.util.Collection findDonaciondetalleByClienteoficinaid(java.lang.Long clienteoficinaid) throws GenericBusinessException;
    java.util.Collection findDonaciondetalleByPreimpreso(java.lang.String preimpreso) throws GenericBusinessException;
    java.util.Collection findDonaciondetalleByFacturaaplId(java.lang.Long facturaaplId) throws GenericBusinessException;
    java.util.Collection findDonaciondetalleByFundaciondevolucionid(java.lang.Long fundaciondevolucionid) throws GenericBusinessException;
    java.util.Collection findDonaciondetalleByNombrefundaciondevuelta(java.lang.String nombrefundaciondevuelta) throws GenericBusinessException;

}
