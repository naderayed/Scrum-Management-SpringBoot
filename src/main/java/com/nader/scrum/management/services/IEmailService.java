package com.nader.scrum.management.services;

import com.nader.scrum.management.entities.EmailDetails;

public interface IEmailService {

    String sendSimpleMail(EmailDetails details);


    String sendMailWithAttachment(EmailDetails details);
}
