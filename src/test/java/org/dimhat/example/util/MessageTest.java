package org.dimhat.example.util;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageTest {
	
	public static void main(String[] args) {
        MessageSource resources = new ClassPathXmlApplicationContext("beans-message.xml");
        String message = resources.getMessage("msg.common.serverBusy", null, "Default", null);
        System.out.println(message);
        String message1 = resources.getMessage("msg.argument.required", new Object[] { "'联系方式'" }, null, Locale.CHINA);
        System.out.println(message1);
    }

}
