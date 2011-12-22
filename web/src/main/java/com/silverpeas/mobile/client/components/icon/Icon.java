package com.silverpeas.mobile.client.components.icon;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.silverpeas.mobile.client.resources.ApplicationResources;

public class Icon extends Composite implements HasText, HasClickHandlers, ClickHandler {

	private Grid grid = new Grid(2,1);
	private SimplePanel image = new SimplePanel();
	private Label label = new Label();
	private ClickHandler handler = null;
	
	private ApplicationResources res =  GWT.create(ApplicationResources.class);

	public Icon() {
		grid.setStyleName(res.css().icon());
		grid.addClickHandler(this);
		grid.setWidget(0, 0, image);
		grid.setWidget(1, 0, label);		
		initWidget(grid);
		
		/*VerticalPanel p = new VerticalPanel();
		p.add(image);
		p.add(label);
		initWidget(p);*/
	}

	public String getText() {
		return label.getText();
	}

	public void setText(String text) {
		label.setText(text);
	}

	@Override
	public void setStyleName(String style) {
		image.setStyleName(style);
	}

	/**
	 * Permet de s'abonner au clique sur l'icone.
	 */
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		this.handler = handler;
		return new IconHandlerRegistration();
	}
	
	/**
	 * Permet de se désabonner au clique sur l'icone.
	 * @author svuillet
	 */
	public class IconHandlerRegistration implements HandlerRegistration {		
		public IconHandlerRegistration() {
			super();
		}

		public void removeHandler() {
			handler = null;
		}
	}

	public void onClick(ClickEvent event) {
		if (handler != null) handler.onClick(event);		
	}
	
}