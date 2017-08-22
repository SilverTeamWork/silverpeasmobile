package com.silverpeas.mobile.client.apps.navigation.pages.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.silverpeas.mobile.shared.dto.navigation.Apps;
import com.silverpeas.mobile.client.apps.navigation.events.pages.ClickItemEvent;
import com.silverpeas.mobile.client.common.EventBus;
import com.silverpeas.mobile.client.resources.ApplicationMessages;
import com.silverpeas.mobile.shared.dto.navigation.ApplicationInstanceDTO;
import com.silverpeas.mobile.shared.dto.navigation.SilverpeasObjectDTO;
import com.silverpeas.mobile.shared.dto.navigation.SpaceDTO;

public class NavigationItem extends Composite {

  private SilverpeasObjectDTO data;
  private static NavigationItemUiBinder uiBinder = GWT.create(NavigationItemUiBinder.class);
  @UiField Anchor link;
  protected ApplicationMessages msg = null;


  interface NavigationItemUiBinder extends UiBinder<Widget, NavigationItem> {
  }

  public NavigationItem() {
    initWidget(uiBinder.createAndBindUi(this));
    msg = GWT.create(ApplicationMessages.class);
  }

  public void setData(SilverpeasObjectDTO data) {
    this.data = data;
    link.setText(data.getLabel());
    if (data instanceof SpaceDTO) {
      if (((SpaceDTO)data).isPersonal()) {
        link.setText(msg.personalSpace());
      }
    } else {
      String type = ((ApplicationInstanceDTO) data).getType();
      if (type.equalsIgnoreCase(Apps.kmelia.name())) {
        setStyleName("folder-ged");
      } else if (type.equalsIgnoreCase(Apps.gallery.name())) {
        setStyleName("folder-galery");
      } else if (type.equalsIgnoreCase(Apps.quickinfo.name())) {
        setStyleName("app-actuality");
      } else if (type.equalsIgnoreCase(Apps.webPages.name())) {
        setStyleName("app-pageWeb");
      }
    }
    link.setStyleName("ui-btn ui-btn-icon-right ui-icon-carat-r");
  }

  @UiHandler("link")
  protected void onClick(ClickEvent event) {
    EventBus.getInstance().fireEvent(new ClickItemEvent(data));
  }
}
