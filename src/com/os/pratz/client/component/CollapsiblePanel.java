package com.os.pratz.client.component;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class CollapsiblePanel extends Composite{
	
	private static final String COLLAPSE_IMG = "images/collapse.png";
	private static final String EXPAND_IMG = "images/expand.png";
	private SimplePanel contentPanel;
	private HorizontalPanel headerPanel;
	private Image img;
	private WipeAnimation wipeAnimate;
	private int contentPanelHeight;
	private int contentPanelWidth;
	private Label headerLabel;
	
	public CollapsiblePanel() {
		Panel wrapperPanel = new SimplePanel();
		
		VerticalPanel mainPanel = new VerticalPanel();
		
		headerPanel = new HorizontalPanel();
		headerPanel.setStyleName("header-css");
		
		createHeaderPanel(headerPanel);
		
		contentPanel = new SimplePanel();
		contentPanel.setStyleName("collapsible-panel-border");
		
		mainPanel.add(headerPanel);
		mainPanel.add(contentPanel);
		
		wrapperPanel.add(mainPanel);
		
		initWidget(wrapperPanel);
		initAnim();
	}
	
	@Override
	protected void onLoad() {
		int offsetHeight = contentPanel.getElement().getClientHeight();
		int offsetWidth = contentPanel.getElement().getOffsetWidth();
		contentPanelHeight = offsetHeight==0?contentPanelHeight:offsetHeight;
		contentPanelWidth = offsetWidth==0?contentPanelWidth:offsetWidth; 
	}
	
	@Override
	protected void onUnload() {
		contentPanel.getElement().getStyle().setHeight(contentPanelHeight, Unit.PX);
		contentPanel.getElement().getStyle().setDisplay(Display.BLOCK);
	}
	
	private void initAnim() {
		wipeAnimate = new WipeAnimation(contentPanel);
	}


	private void createHeaderPanel(Panel headerPanel) {
		img = new Image();
		img.setUrl(COLLAPSE_IMG);
		img.addClickHandler(imageClickHandler);
		headerPanel.add(img);
		((HorizontalPanel)headerPanel).setCellHorizontalAlignment(img, HasHorizontalAlignment.ALIGN_RIGHT);
	}
	
	private ClickHandler imageClickHandler = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			if(contentPanel.isVisible()){
				hideContentPanel();	
			}else{
				showContentPanel();
			}
		}
	};

	/**
	 * Expands the content of the panel with default duration 1000ms
	 */
	public void showContentPanel(){
		showContentPanel(500);
	}

	/**
	 * Expands the content of the panel with specified duration in ms
	 */
	public void showContentPanel(int duration){
		if(!contentPanel.isVisible()){
			wipeAnimate.animatedShow(duration, contentPanelHeight);
			img.setUrl(COLLAPSE_IMG);
		}
	}
	
	/**
	 * Collapses the content of the panel with default duration 1000ms
	 */
	public void hideContentPanel(){
		hideContentPanel(500);
	}

	/**
	 * Collapses the content of the panel with specified duration in ms
	 */
	public void hideContentPanel(int duration){
		if(contentPanel.isVisible()){
			int offsetHeight = contentPanel.getElement().getClientHeight();
			wipeAnimate.animatedHide(duration, offsetHeight);
			img.setUrl(EXPAND_IMG);
			headerPanel.setWidth(contentPanelWidth+"px");
		}
	}
	
	public boolean isContentPanelVisible(){
		return contentPanel.isVisible();
	}
	
	public void setContent(IsWidget w){
		contentPanel.setWidget(w);
	}
	
	public void setContent(Widget w){
		contentPanel.setWidget(w);
	}
	
	public void setHeaderText(String headerText){
		if(headerLabel==null){
			headerLabel = new Label(headerText);
			headerPanel.insert(headerLabel, 0);
			headerPanel.setCellVerticalAlignment(headerLabel, HasVerticalAlignment.ALIGN_MIDDLE);
		}else{
			headerLabel.setText(headerText);
		}
		
	}

}
