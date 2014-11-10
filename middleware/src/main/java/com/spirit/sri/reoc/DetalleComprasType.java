/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: DetalleComprasType.java,v 1.1 2014/03/28 18:06:47 xrf Exp $
 */

package com.spirit.sri.reoc;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class DetalleComprasType.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:47 $
 */
public class DetalleComprasType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _tpIdProv
     */
    private java.lang.String _tpIdProv;

    /**
     * Field _idProv
     */
    private java.lang.String _idProv;

    /**
     * Field _tipoComp
     */
    private int _tipoComp;

    /**
     * keeps track of state for field: _tipoComp
     */
    private boolean _has_tipoComp;

    /**
     * Field _aut
     */
    private java.lang.String _aut;

    /**
     * Field _estab
     */
    private java.lang.String _estab;

    /**
     * Field _ptoEmi
     */
    private java.lang.String _ptoEmi;

    /**
     * Field _sec
     */
    private int _sec;

    /**
     * keeps track of state for field: _sec
     */
    private boolean _has_sec;

    /**
     * Field _fechaEmiCom
     */
    private java.lang.String _fechaEmiCom;

    /**
     * Field _air
     */
    private com.spirit.sri.reoc.Air _air;

    /**
     * Field _autRet
     */
    private java.lang.String _autRet;

    /**
     * Field _estabRet
     */
    private java.lang.String _estabRet;

    /**
     * Field _ptoEmiRet
     */
    private java.lang.String _ptoEmiRet;

    /**
     * Field _secRet
     */
    private int _secRet;

    /**
     * keeps track of state for field: _secRet
     */
    private boolean _has_secRet;

    /**
     * Field _fechaEmiRet
     */
    private java.lang.String _fechaEmiRet;

    /**
     * Field _autRet1
     */
    private java.lang.String _autRet1;

    /**
     * Field _estabRet1
     */
    private java.lang.String _estabRet1;

    /**
     * Field _ptoEmiRet1
     */
    private java.lang.String _ptoEmiRet1;

    /**
     * Field _secRet1
     */
    private int _secRet1;

    /**
     * keeps track of state for field: _secRet1
     */
    private boolean _has_secRet1;

    /**
     * Field _fechaEmiRet1
     */
    private java.lang.String _fechaEmiRet1;


      //----------------/
     //- Constructors -/
    //----------------/

    public DetalleComprasType() 
     {
        super();
    } //-- com.spirit.sri.reoc.DetalleComprasType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteSec
     * 
     */
    public void deleteSec()
    {
        this._has_sec= false;
    } //-- void deleteSec() 

    /**
     * Method deleteSecRet
     * 
     */
    public void deleteSecRet()
    {
        this._has_secRet= false;
    } //-- void deleteSecRet() 

    /**
     * Method deleteSecRet1
     * 
     */
    public void deleteSecRet1()
    {
        this._has_secRet1= false;
    } //-- void deleteSecRet1() 

    /**
     * Method deleteTipoComp
     * 
     */
    public void deleteTipoComp()
    {
        this._has_tipoComp= false;
    } //-- void deleteTipoComp() 

    /**
     * Returns the value of field 'air'.
     * 
     * @return Air
     * @return the value of field 'air'.
     */
    public com.spirit.sri.reoc.Air getAir()
    {
        return this._air;
    } //-- com.spirit.sri.reoc.Air getAir() 

    /**
     * Returns the value of field 'aut'.
     * 
     * @return String
     * @return the value of field 'aut'.
     */
    public java.lang.String getAut()
    {
        return this._aut;
    } //-- java.lang.String getAut() 

    /**
     * Returns the value of field 'autRet'.
     * 
     * @return String
     * @return the value of field 'autRet'.
     */
    public java.lang.String getAutRet()
    {
        return this._autRet;
    } //-- java.lang.String getAutRet() 

    /**
     * Returns the value of field 'autRet1'.
     * 
     * @return String
     * @return the value of field 'autRet1'.
     */
    public java.lang.String getAutRet1()
    {
        return this._autRet1;
    } //-- java.lang.String getAutRet1() 

    /**
     * Returns the value of field 'estab'.
     * 
     * @return String
     * @return the value of field 'estab'.
     */
    public java.lang.String getEstab()
    {
        return this._estab;
    } //-- java.lang.String getEstab() 

    /**
     * Returns the value of field 'estabRet'.
     * 
     * @return String
     * @return the value of field 'estabRet'.
     */
    public java.lang.String getEstabRet()
    {
        return this._estabRet;
    } //-- java.lang.String getEstabRet() 

    /**
     * Returns the value of field 'estabRet1'.
     * 
     * @return String
     * @return the value of field 'estabRet1'.
     */
    public java.lang.String getEstabRet1()
    {
        return this._estabRet1;
    } //-- java.lang.String getEstabRet1() 

    /**
     * Returns the value of field 'fechaEmiCom'.
     * 
     * @return String
     * @return the value of field 'fechaEmiCom'.
     */
    public java.lang.String getFechaEmiCom()
    {
        return this._fechaEmiCom;
    } //-- java.lang.String getFechaEmiCom() 

    /**
     * Returns the value of field 'fechaEmiRet'.
     * 
     * @return String
     * @return the value of field 'fechaEmiRet'.
     */
    public java.lang.String getFechaEmiRet()
    {
        return this._fechaEmiRet;
    } //-- java.lang.String getFechaEmiRet() 

    /**
     * Returns the value of field 'fechaEmiRet1'.
     * 
     * @return String
     * @return the value of field 'fechaEmiRet1'.
     */
    public java.lang.String getFechaEmiRet1()
    {
        return this._fechaEmiRet1;
    } //-- java.lang.String getFechaEmiRet1() 

    /**
     * Returns the value of field 'idProv'.
     * 
     * @return String
     * @return the value of field 'idProv'.
     */
    public java.lang.String getIdProv()
    {
        return this._idProv;
    } //-- java.lang.String getIdProv() 

    /**
     * Returns the value of field 'ptoEmi'.
     * 
     * @return String
     * @return the value of field 'ptoEmi'.
     */
    public java.lang.String getPtoEmi()
    {
        return this._ptoEmi;
    } //-- java.lang.String getPtoEmi() 

    /**
     * Returns the value of field 'ptoEmiRet'.
     * 
     * @return String
     * @return the value of field 'ptoEmiRet'.
     */
    public java.lang.String getPtoEmiRet()
    {
        return this._ptoEmiRet;
    } //-- java.lang.String getPtoEmiRet() 

    /**
     * Returns the value of field 'ptoEmiRet1'.
     * 
     * @return String
     * @return the value of field 'ptoEmiRet1'.
     */
    public java.lang.String getPtoEmiRet1()
    {
        return this._ptoEmiRet1;
    } //-- java.lang.String getPtoEmiRet1() 

    /**
     * Returns the value of field 'sec'.
     * 
     * @return int
     * @return the value of field 'sec'.
     */
    public int getSec()
    {
        return this._sec;
    } //-- int getSec() 

    /**
     * Returns the value of field 'secRet'.
     * 
     * @return int
     * @return the value of field 'secRet'.
     */
    public int getSecRet()
    {
        return this._secRet;
    } //-- int getSecRet() 

    /**
     * Returns the value of field 'secRet1'.
     * 
     * @return int
     * @return the value of field 'secRet1'.
     */
    public int getSecRet1()
    {
        return this._secRet1;
    } //-- int getSecRet1() 

    /**
     * Returns the value of field 'tipoComp'.
     * 
     * @return int
     * @return the value of field 'tipoComp'.
     */
    public int getTipoComp()
    {
        return this._tipoComp;
    } //-- int getTipoComp() 

    /**
     * Returns the value of field 'tpIdProv'.
     * 
     * @return String
     * @return the value of field 'tpIdProv'.
     */
    public java.lang.String getTpIdProv()
    {
        return this._tpIdProv;
    } //-- java.lang.String getTpIdProv() 

    /**
     * Method hasSec
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasSec()
    {
        return this._has_sec;
    } //-- boolean hasSec() 

    /**
     * Method hasSecRet
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasSecRet()
    {
        return this._has_secRet;
    } //-- boolean hasSecRet() 

    /**
     * Method hasSecRet1
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasSecRet1()
    {
        return this._has_secRet1;
    } //-- boolean hasSecRet1() 

    /**
     * Method hasTipoComp
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasTipoComp()
    {
        return this._has_tipoComp;
    } //-- boolean hasTipoComp() 

    /**
     * Method isValid
     * 
     * 
     * 
     * @return boolean
     */
    public boolean isValid()
    {
        try {
            validate();
        }
        catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    } //-- boolean isValid() 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param out
     */
    public void marshal(java.io.Writer out)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, out);
    } //-- void marshal(java.io.Writer) 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param handler
     */
    public void marshal(org.xml.sax.ContentHandler handler)
        throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, handler);
    } //-- void marshal(org.xml.sax.ContentHandler) 

    /**
     * Sets the value of field 'air'.
     * 
     * @param air the value of field 'air'.
     */
    public void setAir(com.spirit.sri.reoc.Air air)
    {
        this._air = air;
    } //-- void setAir(com.spirit.sri.reoc.Air) 

    /**
     * Sets the value of field 'aut'.
     * 
     * @param aut the value of field 'aut'.
     */
    public void setAut(java.lang.String aut)
    {
        this._aut = aut;
    } //-- void setAut(java.lang.String) 

    /**
     * Sets the value of field 'autRet'.
     * 
     * @param autRet the value of field 'autRet'.
     */
    public void setAutRet(java.lang.String autRet)
    {
        this._autRet = autRet;
    } //-- void setAutRet(java.lang.String) 

    /**
     * Sets the value of field 'autRet1'.
     * 
     * @param autRet1 the value of field 'autRet1'.
     */
    public void setAutRet1(java.lang.String autRet1)
    {
        this._autRet1 = autRet1;
    } //-- void setAutRet1(java.lang.String) 

    /**
     * Sets the value of field 'estab'.
     * 
     * @param estab the value of field 'estab'.
     */
    public void setEstab(java.lang.String estab)
    {
        this._estab = estab;
    } //-- void setEstab(java.lang.String) 

    /**
     * Sets the value of field 'estabRet'.
     * 
     * @param estabRet the value of field 'estabRet'.
     */
    public void setEstabRet(java.lang.String estabRet)
    {
        this._estabRet = estabRet;
    } //-- void setEstabRet(java.lang.String) 

    /**
     * Sets the value of field 'estabRet1'.
     * 
     * @param estabRet1 the value of field 'estabRet1'.
     */
    public void setEstabRet1(java.lang.String estabRet1)
    {
        this._estabRet1 = estabRet1;
    } //-- void setEstabRet1(java.lang.String) 

    /**
     * Sets the value of field 'fechaEmiCom'.
     * 
     * @param fechaEmiCom the value of field 'fechaEmiCom'.
     */
    public void setFechaEmiCom(java.lang.String fechaEmiCom)
    {
        this._fechaEmiCom = fechaEmiCom;
    } //-- void setFechaEmiCom(java.lang.String) 

    /**
     * Sets the value of field 'fechaEmiRet'.
     * 
     * @param fechaEmiRet the value of field 'fechaEmiRet'.
     */
    public void setFechaEmiRet(java.lang.String fechaEmiRet)
    {
        this._fechaEmiRet = fechaEmiRet;
    } //-- void setFechaEmiRet(java.lang.String) 

    /**
     * Sets the value of field 'fechaEmiRet1'.
     * 
     * @param fechaEmiRet1 the value of field 'fechaEmiRet1'.
     */
    public void setFechaEmiRet1(java.lang.String fechaEmiRet1)
    {
        this._fechaEmiRet1 = fechaEmiRet1;
    } //-- void setFechaEmiRet1(java.lang.String) 

    /**
     * Sets the value of field 'idProv'.
     * 
     * @param idProv the value of field 'idProv'.
     */
    public void setIdProv(java.lang.String idProv)
    {
        this._idProv = idProv;
    } //-- void setIdProv(java.lang.String) 

    /**
     * Sets the value of field 'ptoEmi'.
     * 
     * @param ptoEmi the value of field 'ptoEmi'.
     */
    public void setPtoEmi(java.lang.String ptoEmi)
    {
        this._ptoEmi = ptoEmi;
    } //-- void setPtoEmi(java.lang.String) 

    /**
     * Sets the value of field 'ptoEmiRet'.
     * 
     * @param ptoEmiRet the value of field 'ptoEmiRet'.
     */
    public void setPtoEmiRet(java.lang.String ptoEmiRet)
    {
        this._ptoEmiRet = ptoEmiRet;
    } //-- void setPtoEmiRet(java.lang.String) 

    /**
     * Sets the value of field 'ptoEmiRet1'.
     * 
     * @param ptoEmiRet1 the value of field 'ptoEmiRet1'.
     */
    public void setPtoEmiRet1(java.lang.String ptoEmiRet1)
    {
        this._ptoEmiRet1 = ptoEmiRet1;
    } //-- void setPtoEmiRet1(java.lang.String) 

    /**
     * Sets the value of field 'sec'.
     * 
     * @param sec the value of field 'sec'.
     */
    public void setSec(int sec)
    {
        this._sec = sec;
        this._has_sec = true;
    } //-- void setSec(int) 

    /**
     * Sets the value of field 'secRet'.
     * 
     * @param secRet the value of field 'secRet'.
     */
    public void setSecRet(int secRet)
    {
        this._secRet = secRet;
        this._has_secRet = true;
    } //-- void setSecRet(int) 

    /**
     * Sets the value of field 'secRet1'.
     * 
     * @param secRet1 the value of field 'secRet1'.
     */
    public void setSecRet1(int secRet1)
    {
        this._secRet1 = secRet1;
        this._has_secRet1 = true;
    } //-- void setSecRet1(int) 

    /**
     * Sets the value of field 'tipoComp'.
     * 
     * @param tipoComp the value of field 'tipoComp'.
     */
    public void setTipoComp(int tipoComp)
    {
        this._tipoComp = tipoComp;
        this._has_tipoComp = true;
    } //-- void setTipoComp(int) 

    /**
     * Sets the value of field 'tpIdProv'.
     * 
     * @param tpIdProv the value of field 'tpIdProv'.
     */
    public void setTpIdProv(java.lang.String tpIdProv)
    {
        this._tpIdProv = tpIdProv;
    } //-- void setTpIdProv(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return DetalleComprasType
     */
    public static com.spirit.sri.reoc.DetalleComprasType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.reoc.DetalleComprasType) Unmarshaller.unmarshal(com.spirit.sri.reoc.DetalleComprasType.class, reader);
    } //-- com.spirit.sri.reoc.DetalleComprasType unmarshal(java.io.Reader) 

    /**
     * Method validate
     * 
     */
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
