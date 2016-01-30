package com.example.templater.client.vaadintemplateinjector;

import java.util.HashMap;

import com.vaadin.shared.AbstractComponentState;
import com.vaadin.shared.Connector;

public class VaadinTemplateInjectorState extends AbstractComponentState {
	
	public String html;
	
	public HashMap<String, Connector> components;

}
