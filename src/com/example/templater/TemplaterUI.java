package com.example.templater;

import javax.servlet.annotation.WebServlet;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.ClickListenerCollection;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("templater")
public class TemplaterUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = TemplaterUI.class, widgetset = "com.example.templater.TemplaterWidgetset")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		VaadinTemplateInjector vti = new VaadinTemplateInjector();
		vti.setWidth("100%");
		vti.setHeight("100%");
		setContent(vti);
		vti.setHtml("<div style='width: 30%; height: 30%; border: red dotted 5px;' id='test-place'></div>"
				+"<div style='width: 30%; height: 30%; border: red dotted 5px;' id='test-place1'></div>");
		Button bb = new Button("CLICK ME!!!!111");
		vti.setComponentAtId("test-place", bb);
		Panel pp = new Panel();
		pp.setCaption("Panel");
		pp.setSizeFull();
		vti.setComponentAtId("test-place1", pp);
		
		bb.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				pp.setCaption("popopo");
			}
			
		});
	}

}