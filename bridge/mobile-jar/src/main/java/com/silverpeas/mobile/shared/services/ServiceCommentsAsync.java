package com.silverpeas.mobile.shared.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.silverpeas.mobile.shared.dto.BaseDTO;
import com.silverpeas.mobile.shared.dto.comments.CommentDTO;
import com.silverpeas.mobile.shared.dto.documents.PublicationDTO;
import com.silverpeas.mobile.shared.dto.documents.TopicDTO;
import com.silverpeas.mobile.shared.exceptions.AuthenticationException;
import com.silverpeas.mobile.shared.exceptions.CommentsException;
import com.silverpeas.mobile.shared.exceptions.DocumentsException;

import java.util.List;

public interface ServiceCommentsAsync {
  void getComments(String id, String type, final AsyncCallback<List<CommentDTO>> async);

  void addComment(String id, String instanceId, String type, String message,
      final AsyncCallback<CommentDTO> async);
}
