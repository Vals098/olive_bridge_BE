package valeriafarinosi.olive_bridge.services;

import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.model.message.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailgunService {

    private final MailgunMessagesApi mailgunMessagesApi;

    @Value("${mailgun.domain}")
    private String domain;

    @Value("${mailgun.from}")
    private String from;

    public MailgunService(
            MailgunMessagesApi mailgunMessagesApi
    ) {
        this.mailgunMessagesApi = mailgunMessagesApi;
    }

    public void sendEmail(
            String to,
            String subject,
            String text
    ) {

        Message message = Message.builder()
                .from(from)
                .to(to)
                .subject(subject)
                .text(text)
                .build();

        mailgunMessagesApi.sendMessage(domain, message);
    }

    public void sendSampleRequestReply(
            String to,
            String reply
    ) {

        sendEmail(
                to,
                "OliveBridge — Sample Request",
                reply
        );
    }

    public void sendBusinessInquiryReply(
            String to,
            String reply
    ) {

        sendEmail(
                to,
                "OliveBridge — Business Inquiry",
                reply
        );
    }

    public void sendOrderConfirmation(
            String to,
            String orderId,
            String total
    ) {

        sendEmail(
                to,
                "OliveBridge — Order Confirmation",
                "Thank you for your order!\n\n"
                        + "Order: #" + orderId + "\n"
                        + "Total: €" + total + "\n\n"
                        + "We will keep you updated on the status of your order.\n\n"
                        + "OliveBridge"
        );
    }
}