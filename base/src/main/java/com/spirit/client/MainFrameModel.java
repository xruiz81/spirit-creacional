package com.spirit.client;

import java.util.Map;

import javax.swing.JFrame;

import com.jidesoft.docking.DockableFrame;
import com.jidesoft.docking.DockingManager;
import com.jidesoft.status.LabelStatusBarItem;
import com.jidesoft.swing.JideButton;


public class MainFrameModel {

	public static JFrame _frame;
	public static DockingManager _dockingManager;
	public static Map _panels;
	public static LabelStatusBarItem _option;
	public static JideButton _btnFind;
	public static JideButton _btnSave;
	public static JideButton _btnDelete;
	public static JideButton _btnPrint;
	public static JideButton _btnAuthorize;
	public static JideButton _btnDuplicate;
	public static JideButton _btnGenerateGraphic;
	public static DockableFrame panelBackground;
	
	public static DockingManager get_dockingManager() {
		return _dockingManager;
	}
	
	public static void set_dockingManager(DockingManager manager) {
		_dockingManager = manager;
	}
	
	public static JFrame get_frame() {
		return _frame;
	}
	
	public static void set_frame(JFrame _frame) {
		MainFrameModel._frame = _frame;
	}
	
	public static LabelStatusBarItem get_option() {
		return _option;
	}
	
	public static void set_option(LabelStatusBarItem _option) {
		MainFrameModel._option = _option;
	}
	
	public static Map get_panels() {
		return _panels;
	}
	
	public static void set_panels(Map _panels) {
		MainFrameModel._panels = _panels;
	}
	
	public static DockableFrame getPanelBackground() {
		return panelBackground;
	}

	public static void setPanelBackground(DockableFrame panelBackground) {
		MainFrameModel.panelBackground = panelBackground;
	}

	public static JideButton get_btnAuthorize() {
		return _btnAuthorize;
	}

	public static void set_btnAuthorize(JideButton authorize) {
		_btnAuthorize = authorize;
	}

	public static JideButton get_btnDelete() {
		return _btnDelete;
	}

	public static void set_btnDelete(JideButton delete) {
		_btnDelete = delete;
	}

	public static JideButton get_btnFind() {
		return _btnFind;
	}

	public static void set_btnFind(JideButton find) {
		_btnFind = find;
	}

	public static JideButton get_btnGenerateGraphic() {
		return _btnGenerateGraphic;
	}

	public static void set_btnGenerateGraphic(JideButton generateGraphic) {
		_btnGenerateGraphic = generateGraphic;
	}

	public static JideButton get_btnPrint() {
		return _btnPrint;
	}

	public static void set_btnPrint(JideButton print) {
		_btnPrint = print;
	}

	public static JideButton get_btnSave() {
		return _btnSave;
	}

	public static void set_btnSave(JideButton save) {
		_btnSave = save;
	}

	public static JideButton get_btnDuplicate() {
		return _btnDuplicate;
	}

	public static void set_btnDuplicate(JideButton duplicate) {
		_btnDuplicate = duplicate;
	}
}
