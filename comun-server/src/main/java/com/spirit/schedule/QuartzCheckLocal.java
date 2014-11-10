package com.spirit.schedule;

import javax.ejb.Local;

import org.quartz.Job;

@Local
public interface QuartzCheckLocal {
	public boolean validate(Job quartzBean);
}
