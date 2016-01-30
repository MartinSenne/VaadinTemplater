package com.example.templater.client.vaadintemplateinjector;

import java.util.Map.Entry;

import com.example.templater.VaadinTemplateInjector;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentContainerConnector;
import com.vaadin.shared.ui.Connect;
import com.vaadin.shared.Connector;

@Connect(VaadinTemplateInjector.class)
public class VaadinTemplateInjectorConnector extends AbstractComponentContainerConnector {

	private static final long serialVersionUID = 7364777474592332411L;

	public VaadinTemplateInjectorConnector() {
	}

	@Override
	protected Widget createWidget() {
		return GWT.create(HTML.class);
	}

	@Override
	public Widget getWidget() {
		return super.getWidget();
	}

	@Override
	public VaadinTemplateInjectorState getState() {
		return (VaadinTemplateInjectorState) super.getState();
	}

	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);
		HTML w = (HTML)getWidget();
		VaadinTemplateInjectorState s = getState();
		w.setSize(s.width, s.height);
		w.getElement().setInnerHTML(s.html);
		
		for (Entry<String, Connector> e: s.components.entrySet()) {
			Element ele = DOM.getElementById(e.getKey());
			ComponentConnector cc = (ComponentConnector)e.getValue();
			ele.removeAllChildren();
			ele.appendChild(cc.getWidget().getElement());
		}
	}
	
	public static native void logg(Object o) /*-{console.log(o)}-*/;

	@Override
	public void updateCaption(ComponentConnector connector) {
	}

	@Override
	public void onConnectorHierarchyChange(ConnectorHierarchyChangeEvent connectorHierarchyChangeEvent) {
	}

}

