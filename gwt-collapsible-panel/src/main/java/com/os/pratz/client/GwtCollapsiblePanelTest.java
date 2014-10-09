package com.os.pratz.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.os.pratz.client.component.CollapsiblePanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtCollapsiblePanelTest implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		final CollapsiblePanel panel = new CollapsiblePanel();
		
		VerticalPanel vp = new VerticalPanel();
		vp.getElement().getStyle().setMargin(10, Unit.PX);;
		Label lbl = new Label("I'm going to get collapsed!!!");
		
		Button collapseBtn = new Button("Collapse Me!!!");
		collapseBtn.getElement().getStyle().setMarginTop(10, Unit.PX);
		collapseBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				panel.hideContentPanel();
			}
		});
		vp.add(lbl);
		vp.add(collapseBtn);
		vp.setCellHorizontalAlignment(collapseBtn, HasHorizontalAlignment.ALIGN_RIGHT);
		
		panel.setContent(vp);
		panel.setHeaderText("Collapsible Panel");
		
		RootPanel.get().add(panel);
	}
}
