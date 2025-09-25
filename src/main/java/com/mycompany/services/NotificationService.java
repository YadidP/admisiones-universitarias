package com.mycompany.services;

import com.mycompany.model.Notificacion;
import com.mycompany.model.Postulante;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {

    public NotificationService() {
    }

    public boolean sendNotificationToPostulante(Postulante postulante, String message) {
        return true;
    }

    public boolean sendGeneralNotification(String message) {
        return true;
    }

    public List<Notificacion> getAllNotifications() {
        return new ArrayList<>();
    }

    public boolean markAsRead(Notificacion notification) {
        return true;
    }
}