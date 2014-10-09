package com.os.pratz.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
 
public interface CollapsiblePanelClientBundle extends ClientBundle {

    static final CollapsiblePanelClientBundle INSTANCE = GWT.create(CollapsiblePanelClientBundle.class);

    @Source("resource/images/collapse.png")
    ImageResource collapse();
    
    @Source("resource/images/expand.png")
    ImageResource expand();

}
