package com.silverpeas.mobile.client.apps.contacts.resources;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.safehtml.shared.SafeHtml;

public interface ContactsMessages extends Messages {
  String title();
  String callContactButton();
  String addContactButton();
  SafeHtml allContacts();
  SafeHtml myContacts();
  String phoneNumber();
  String eMail();
  String firstName();
  String lastName();
  String identity();
}
