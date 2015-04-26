package com.github.kazuki43zoo.app;

import org.terasoluna.gfw.common.exception.ResultMessagesNotificationException;
import org.terasoluna.gfw.common.message.ResultMessages;

public class DownloadException extends ResultMessagesNotificationException {
    public DownloadException(ResultMessages messages) {
        super(messages);
    }
}
