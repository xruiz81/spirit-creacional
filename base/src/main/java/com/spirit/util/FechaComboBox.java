package com.spirit.util;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.util.Calendar;

import com.jidesoft.combobox.DateComboBox;
import com.jidesoft.combobox.DateModel;
import com.jidesoft.combobox.DefaultDateModel;

public class FechaComboBox extends DateComboBox {


	private static final long serialVersionUID = 4822660765446561141L;
	private DateComboBox _dateComboBox;
	
	public FechaComboBox() {
		super();
		
		this._dateComboBox = createDateComboBox(); 
	}

	public FechaComboBox(DateModel arg0) {
		super(arg0);
		
	}
	
    private DateComboBox createDateComboBox() {
        DefaultDateModel model = new DefaultDateModel();
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, 2010);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
        model.setMaxDate(calendar);

        calendar.set(Calendar.YEAR, 1980);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMinimum(Calendar.DAY_OF_YEAR));
        model.setMinDate(calendar);

        DateComboBox dateComboBox = new DateComboBox(model);
        //dateComboBox.setFormat(DateFormat.getDateInstance(DateFormat.LONG));
        dateComboBox.setFormat(DateFormat.getDateInstance(DateFormat.DEFAULT));//antes era short
        dateComboBox.setShowTodayButton(false);
        dateComboBox.setShowNoneButton(false);
        
        Calendar prototypeValue = Calendar.getInstance();
        prototypeValue.set(Calendar.YEAR, 2000);
        prototypeValue.set(Calendar.MONDAY, 8);
        prototypeValue.set(Calendar.DAY_OF_MONTH, 30);
        dateComboBox.setPrototypeDisplayValue(prototypeValue);
        Calendar currentDate = Calendar.getInstance();
        dateComboBox.setCalendar(currentDate);        

        dateComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    _dateComboBox.setSelectedItem(ie.getItem());
                }
            }
        });
        return dateComboBox;
    }

	public DateComboBox get_dateComboBox() {
		return _dateComboBox;
	}

	public void set_dateComboBox(DateComboBox comboBox) {
		_dateComboBox = comboBox;
	}
}
