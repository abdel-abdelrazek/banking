/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2016, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package edu.mum.cbs.service.impl;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import edu.mum.cbs.domain.Reconcile;

@Service("emailService")
public class EmailService {

	private static final String IM_THE_GUY = "templates/images/imtheguy.jpg";
	private static final String JPG_MIME = "image/jpg";


	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private SpringTemplateEngine templateEngine;

	/*
	 * Send HTML mail
	 */
	public void sendOrderReceivedMail(final String recipientName, final String recipientEmail, Reconcile result,
			final Locale locale) throws MessagingException {

		// Prepare the Thymeleaf evaluation context
		final Context thymeContext = new Context(locale);
		thymeContext.setVariable("name", recipientName);
		thymeContext.setVariable("reconcile", result);

		// Prepare message using a Spring helper
		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		message.setSubject("Reconcile Results");

		// could have CC, BCC, will also take an array of Strings
		message.setTo(recipientEmail);

		// Create the HTML body using Thymeleaf..template is orderReceivedMail.html
		final String htmlContent = this.templateEngine.process("reconcileResultMail", thymeContext);
		message.setText(htmlContent, true /* isHtml */);

		// Add imtheguy.jpg
		message.addInline("imtheguy", new ClassPathResource(IM_THE_GUY), JPG_MIME);

		// Send email
		this.mailSender.send(mimeMessage);

	}

	public void sendExceptionMail(final String recipientName, final String recipientEmail, final String details,
			final Locale locale) throws MessagingException {

		// Prepare the Thymeleaf evaluation context
		final Context thymeContext = new Context(locale);
		thymeContext.setVariable("name", recipientName);
		thymeContext.setVariable("details", details);
		
		// Prepare message using a Spring helper
		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		message.setSubject("Opza Exception");

		// could have CC, BCC, will also take an array of Strings
		message.setTo(recipientEmail);

		// Create the HTML body using Thymeleaf..template is orderReceivedMail.html
		final String htmlContent = this.templateEngine.process("exceptionMail", thymeContext);
		message.setText(htmlContent, true /* isHtml */);

		// Add imtheguy.jpg
		message.addInline("imtheguy", new ClassPathResource(IM_THE_GUY), JPG_MIME);

		// Send email
		this.mailSender.send(mimeMessage);

	}
	
}
