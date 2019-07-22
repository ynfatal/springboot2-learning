package com.fatal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter22ApplicationTests {

    @Autowired
    private JavaMailSender mailSender;

    private static String FILENAME = "娜美桌面壁纸";

    private static String PATHNAME = "E:/Pictures/桌面壁纸/娜美.png";

    @Test
    public void sendSimpleMail() {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("634137063@qq.com");    // 发送者
        mailMessage.setTo("2235535456@qq.com");     // 接收者
        mailMessage.setSubject("您好，这是fatal的一份邮件");  // 标题
        mailMessage.setText("Hello world!");    // 内容

        mailSender.send(mailMessage);
    }

    @Test
    public void sendAttachmentsMail() throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        /**
         * 第二个参数表示是否创建多部分消息
         */
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom("634137063@qq.com");    // 发送者
        helper.setTo("2235535456@qq.com");     // 接收者
        helper.setSubject("桌面壁纸");  // 标题

        // 嵌入静态资源
        helper.setText("<html><body><h1></h1><img style='max-width: 500px' src=\"cid:wallpaper\" ></body></html>", true); // 内容
        // FileSystemResource 加载绝对路径的流
        FileSystemResource file = new FileSystemResource(new File(PATHNAME));
        // 设置 contentId 及其对应的 `文件资源`（Resource）
        helper.addInline("wallpaper", file);

        // 在邮件中添加附件，添加多个附件可以使用多条
        // helper.addAttachment(附件名, 附件对象);
        helper.addAttachment(FILENAME, file);

        mailSender.send(mimeMessage);

    }

}
