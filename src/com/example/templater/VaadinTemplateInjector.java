package com.example.templater;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.example.templater.client.vaadintemplateinjector.VaadinTemplateInjectorState;
import com.vaadin.shared.Connector;
import com.vaadin.ui.Component;

public class VaadinTemplateInjector extends com.vaadin.ui.AbstractComponentContainer {

	private static final long serialVersionUID = -7063177153212665187L;

	public VaadinTemplateInjector() {
	}

	@Override
	public VaadinTemplateInjectorState getState() {
		VaadinTemplateInjectorState s =(VaadinTemplateInjectorState) super.getState();
		if (s.html == null) s.html = "";
		if (s.components == null) s.components = new HashMap<String, Connector>();
		return s;
	}
	
	public void setComponentAtId(String id, Component component) {
		Component old = (Component)getState().components.get(id);
		if (old != null) {
			super.removeComponent(old);
		}
		super.addComponent(component);
		getState().components.put(id, component);
		markAsDirty();
	}
	
	public Component getComponentAtId(String id) {
		return (Component)getState().components.get(id);
	}
	
	public void setHtml(String html) {
		getState().html = html;
	}
	
	public String getHtml() {
		return getState().html;
	}

	@Override
	public void replaceComponent(Component oldComponent, Component newComponent) {
		HashMap<String, Connector> m = getState().components;
		String foundkey = null;
		for (Entry<String, Connector> e: m.entrySet()) {
			if (e.getValue().equals(oldComponent)) {
				foundkey = e.getKey();
			}
		}
		if (foundkey != null) {
			m.replace(foundkey,  oldComponent, newComponent);
		}
		markAsDirty();
	}

	@Override
	public int getComponentCount() {
		HashMap<String, Connector> m = getState().components;
		return m.size();
	}

	@Override
	public Iterator<Component> iterator() {
		final HashMap<String, Connector> m = getState().components;
		
		return new Iterator<Component>() {

			Iterator<Connector> ic = m.values().iterator();
			
			@Override
			public boolean hasNext() {
				return ic.hasNext();
			}

			@Override
			public Component next() {
				return (Component)ic.next();
			}
			
		};
	}

	
}
