package com.jhp.foryouth.find.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmailUserIdService {

    private final JavaMailSender javaMailSender;

    public void emailContent(String userId, String userEmail) throws MessagingException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm");
        String formattedNow = now.format(formatter);

        String title = "ForYouth : 아이디 찾기 결과";

        String to = userEmail;

        String content =
                "<div style='font-family: Arial, sans-serif; padding: 20px; background-color: #f4f4f4;'>"
                        + "  <div style='max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 30px; border-radius: 10px; box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);'>"
                        + "    <h2 style='color: #333;'>안녕하세요, 청년들을 위한 플랫폼 ForYouth입니다:) 📖</h2>"
                        + "    <p style='font-size: 16px; color: #555;'>회원님은 <strong>" + formattedNow + "</strong>에 아이디 찾기를 요청하셨습니다.</p>"
                        + "    <p style='font-size: 16px; color: #555;'>회원님의 아이디는 <strong>" + userId + "</strong> 입니다.</p>"
                        + "    <p style='font-size: 16px; color: #555;'><strong>ForYouth</strong>를 이용하시며 항상 유익한 시간 되시길 바랍니다. 감사합니다:)</p>"
                        + "    <p style='font-size: 16px; color: #555; margin-top: 50px;'><strong>ForYouth 드림</strong></p>"
                        + "  </div>"
                        + "</div>";

        sendEmail(to, title, content);
    }

    public void sendEmail(String to, String title, String content) throws MessagingException {
        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject(title);
        helper.setText(content, true);
        
        javaMailSender.send(mail);
        log.info("아이디 찾기 이메일을 {}님에게 전송했습니다!", to);

    }


}
