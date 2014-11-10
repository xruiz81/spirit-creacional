package com.spirit.timeTracker.gui.main;

import javax.swing.UIManager;

public class main extends javax.swing.JFrame {
    
    /** Creates new form main */
    public main() {
        try {
            
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        initComponents();
        
    }

    public javax.swing.JMenuBar getBarraMenu() {
        return barraMenu;
    }

    public void setBarraMenu(javax.swing.JMenuBar barraMenu) {
        this.barraMenu = barraMenu;
    }

    public javax.swing.JSplitPane getJSplitPane2() {
        return jSplitPane2;
    }

    public void setJSplitPane2(javax.swing.JSplitPane jSplitPane2) {
        this.jSplitPane2 = jSplitPane2;
    }

    public javax.swing.JMenu getMenuAplicacion() {
        return menuAplicacion;
    }

    public void setMenuAplicacion(javax.swing.JMenu menuAplicacion) {
        this.menuAplicacion = menuAplicacion;
    }

    public javax.swing.JMenu getMenuArchivo() {
        return menuArchivo;
    }

    public void setMenuArchivo(javax.swing.JMenu menuArchivo) {
        this.menuArchivo = menuArchivo;
    }

    public javax.swing.JPanel getPanelSuperior() {
        return panelSuperior;
    }

    public void setPanelSuperior(javax.swing.JPanel panelSuperior) {
        this.panelSuperior = panelSuperior;
    }

    public javax.swing.JScrollPane getSpanelArbol() {
        return spanelArbol;
    }

    public void setSpanelArbol(javax.swing.JScrollPane spanelArbol) {
        this.spanelArbol = spanelArbol;
    }

    public javax.swing.JScrollPane getSpanelProcesos() {
        return spanelProcesos;
    }

    public void setSpanelProcesos(javax.swing.JScrollPane spanelProcesos) {
        this.spanelProcesos = spanelProcesos;
    }

    public javax.swing.JSplitPane getSplitContenido() {
        return splitContenido;
    }

    public void setSplitContenido(javax.swing.JSplitPane splitContenido) {
        this.splitContenido = splitContenido;
    }

    public javax.swing.JMenuItem getMiSalir() {
        return miSalir;
    }

    public void setMiSalir(javax.swing.JMenuItem miSalir) {
        this.miSalir = miSalir;
    }

    public javax.swing.JPanel getPanelInferior() {
        return panelInferior;
    }

    public void setPanelInferior(javax.swing.JPanel panelInferior) {
        this.panelInferior = panelInferior;
    }

    public com.spirit.timeTracker.gui.model.PanelProcesosModel getPanelProcesos() {
        return panelProcesos;
    }

    public void setPanelProcesos(com.spirit.timeTracker.gui.model.PanelProcesosModel panelProcesos) {
        this.panelProcesos = panelProcesos;
    }

    public javax.swing.JSplitPane getJSplitPane1() {
        return jSplitPane1;
    }

    public void setJSplitPane1(javax.swing.JSplitPane jSplitPane1) {
        this.jSplitPane1 = jSplitPane1;
    }

    public javax.swing.JMenuItem getMiMostrarProcesos() {
        return miMostrarProcesos;
    }

    public void setMiMostrarProcesos(javax.swing.JMenuItem miMostrarProcesos) {
        this.miMostrarProcesos = miMostrarProcesos;
    }

    public com.spirit.timeTracker.gui.model.PanelListaProyectosModel getPanelListaProyectos() {
        return panelListaProyectos;
    }

    public void setPanelListaProyectos(com.spirit.timeTracker.gui.model.PanelListaProyectosModel panelListaProyectos) {
        this.panelListaProyectos = panelListaProyectos;
    }

    public com.spirit.timeTracker.gui.model.PanelListaTareasModel getPanelListaTareas() {
        return panelListaTareas;
    }

    public void setPanelListaTareas(com.spirit.timeTracker.gui.model.PanelListaTareasModel panelListaTareas) {
        this.panelListaTareas = panelListaTareas;
    }

    public javax.swing.JPanel getPanelProyectosTareas() {
        return panelProyectosTareas;
    }

    public void setPanelProyectosTareas(javax.swing.JPanel panelProyectosTareas) {
        this.panelProyectosTareas = panelProyectosTareas;
    }

    public com.spirit.timeTracker.gui.model.PanelListaSubTareasModel getPanelListaSubTareas() {
        return panelListaSubTareas;
    }

    public void setPanelListaSubTareas(com.spirit.timeTracker.gui.model.PanelListaSubTareasModel panelListaSubTareas) {
        this.panelListaSubTareas = panelListaSubTareas;
    }

    public javax.swing.JMenu getMenuProyectos() {
        return menuProyectos;
    }

    public void setMenuProyectos(javax.swing.JMenu menuProyectos) {
        this.menuProyectos = menuProyectos;
    }

    public javax.swing.JMenuItem getMiNuevoProyecto() {
        return miNuevoProyecto;
    }

    public void setMiNuevoProyecto(javax.swing.JMenuItem miNuevoProyecto) {
        this.miNuevoProyecto = miNuevoProyecto;
    }

    public javax.swing.JMenuItem getMiIniciarMuestreo() {
        return miIniciarMuestreo;
    }

    public void setMiIniciarMuestreo(javax.swing.JMenuItem miIniciarMuestreo) {
        this.miIniciarMuestreo = miIniciarMuestreo;
    }

    public javax.swing.JMenuItem getMiMostrarInformacion() {
        return miMostrarInformacion;
    }

    public void setMiMostrarInformacion(javax.swing.JMenuItem miMostrarInformacion) {
        this.miMostrarInformacion = miMostrarInformacion;
    }

    public javax.swing.JMenuItem getMiNuevaTarea() {
        return miNuevaTarea;
    }

    public void setMiNuevaTarea(javax.swing.JMenuItem miNuevaTarea) {
        this.miNuevaTarea = miNuevaTarea;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        panelListaSubTareas = new com.spirit.timeTracker.gui.model.PanelListaSubTareasModel();
        jSplitPane2 = new javax.swing.JSplitPane();
        panelSuperior = new javax.swing.JPanel();
        panelInferior = new javax.swing.JPanel();
        splitContenido = new javax.swing.JSplitPane();
        jSplitPane1 = new javax.swing.JSplitPane();
        panelProyectosTareas = new javax.swing.JPanel();
        panelListaProyectos = new com.spirit.timeTracker.gui.model.PanelListaProyectosModel();
        panelListaTareas = new com.spirit.timeTracker.gui.model.PanelListaTareasModel();
        spanelArbol = new javax.swing.JScrollPane();
        spanelProcesos = new javax.swing.JScrollPane();
        panelProcesos = new com.spirit.timeTracker.gui.model.PanelProcesosModel();
        barraMenu = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        miSalir = new javax.swing.JMenuItem();
        menuProyectos = new javax.swing.JMenu();
        miMostrarInformacion = new javax.swing.JMenuItem();
        miNuevoProyecto = new javax.swing.JMenuItem();
        miNuevaTarea = new javax.swing.JMenuItem();
        menuAplicacion = new javax.swing.JMenu();
        miMostrarProcesos = new javax.swing.JMenuItem();
        miIniciarMuestreo = new javax.swing.JMenuItem();

        getContentPane().setLayout(new java.awt.CardLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("TimeTracker");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(300, 300));
        jSplitPane2.setDividerSize(0);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        panelSuperior.setLayout(new javax.swing.BoxLayout(panelSuperior, javax.swing.BoxLayout.X_AXIS));

        panelSuperior.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        panelSuperior.setMaximumSize(new java.awt.Dimension(300, 25));
        panelSuperior.setMinimumSize(new java.awt.Dimension(100, 25));
        panelSuperior.setPreferredSize(new java.awt.Dimension(300, 25));
        jSplitPane2.setLeftComponent(panelSuperior);
        
        panelInferior.setLayout(new java.awt.CardLayout());

        splitContenido.setDividerSize(3);
        jSplitPane1.setDividerSize(3);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setMaximumSize(new java.awt.Dimension(180, 2147483647));
        jSplitPane1.setMinimumSize(new java.awt.Dimension(180, 337));
        jSplitPane1.setPreferredSize(new java.awt.Dimension(180, 407));
        panelProyectosTareas.setLayout(new javax.swing.BoxLayout(panelProyectosTareas, javax.swing.BoxLayout.Y_AXIS));

        panelProyectosTareas.setMaximumSize(new java.awt.Dimension(180, 2147483647));
        panelProyectosTareas.setMinimumSize(new java.awt.Dimension(180, 250));
        panelProyectosTareas.setPreferredSize(new java.awt.Dimension(180, 300));
        panelProyectosTareas.add(panelListaProyectos);

        panelProyectosTareas.add(panelListaTareas);

        jSplitPane1.setTopComponent(panelProyectosTareas);

        spanelArbol.setMinimumSize(new java.awt.Dimension(80, 80));
        spanelArbol.setPreferredSize(new java.awt.Dimension(80, 100));
        jSplitPane1.setRightComponent(spanelArbol);

        splitContenido.setLeftComponent(jSplitPane1);

        spanelProcesos.setMinimumSize(new java.awt.Dimension(200, 50));
        spanelProcesos.setPreferredSize(new java.awt.Dimension(500, 50));
        spanelProcesos.setViewportView(panelProcesos);

        splitContenido.setRightComponent(spanelProcesos);

        panelInferior.add(splitContenido, "card2");

        jSplitPane2.setDividerLocation(0.25);
        jSplitPane2.setRightComponent(panelInferior);

        getContentPane().add(jSplitPane2, "card2");

        menuArchivo.setText("Archivo");
        miSalir.setText("Salir");
        menuArchivo.add(miSalir);

        barraMenu.add(menuArchivo);

        menuProyectos.setText("Proyecto");
        miMostrarInformacion.setText("Mostrar Informacion");
        menuProyectos.add(miMostrarInformacion);

        miNuevoProyecto.setText("Nuevo Proyecto");
        menuProyectos.add(miNuevoProyecto);

        miNuevaTarea.setText("Nueva Tarea");
        menuProyectos.add(miNuevaTarea);

        barraMenu.add(menuProyectos);

        menuAplicacion.setText("Aplicaciones");
        miMostrarProcesos.setText("Mostrar Procesos");
        menuAplicacion.add(miMostrarProcesos);

        miIniciarMuestreo.setText("Iniciar Muestreo");
        menuAplicacion.add(miIniciarMuestreo);

        barraMenu.add(menuAplicacion);

        setJMenuBar(barraMenu);

        pack();
    }// </editor-fold>//GEN-END:initComponents
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JMenu menuAplicacion;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuProyectos;
    private javax.swing.JMenuItem miIniciarMuestreo;
    private javax.swing.JMenuItem miMostrarInformacion;
    private javax.swing.JMenuItem miMostrarProcesos;
    private javax.swing.JMenuItem miNuevaTarea;
    private javax.swing.JMenuItem miNuevoProyecto;
    private javax.swing.JMenuItem miSalir;
    private javax.swing.JPanel panelInferior;
    private com.spirit.timeTracker.gui.model.PanelListaProyectosModel panelListaProyectos;
    private com.spirit.timeTracker.gui.model.PanelListaSubTareasModel panelListaSubTareas;
    private com.spirit.timeTracker.gui.model.PanelListaTareasModel panelListaTareas;
    private com.spirit.timeTracker.gui.model.PanelProcesosModel panelProcesos;
    private javax.swing.JPanel panelProyectosTareas;
    private javax.swing.JPanel panelSuperior;
    private javax.swing.JScrollPane spanelArbol;
    private javax.swing.JScrollPane spanelProcesos;
    private javax.swing.JSplitPane splitContenido;
    // End of variables declaration//GEN-END:variables
    
}
