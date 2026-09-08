package valeriafarinosi.olive_bridge;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import valeriafarinosi.olive_bridge.services.MailgunService;

@SpringBootTest
class MailgunServiceTest {

    @Autowired
    private MailgunService mailgunService;

    @Test
    void sendTestEmail() {

        mailgunService.sendEmail(
                "valeria.farinosi@gmail.com",
                "OliveBridge - Test Email",
                "This is a test email sent from OliveBridge."
        );
    }
}