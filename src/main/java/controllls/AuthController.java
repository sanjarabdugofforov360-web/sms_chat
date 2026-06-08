package controllls;

import dtos.LoginDto;
import dtos.UserDto;
import enums.UserRole;
import servise.AuthServise;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import static utils.Utill.*;

public class AuthController {
    private final AuthServise authServise = AuthServise.geInstanse();

    public void authMenu() {
        while (true) {
            System.out.println("""
                    1 login
                    2 registration
                    0 exit""");
            int menu = getNum("Menuni tanlang ");
            switch (menu) {
                case 1 -> {
                    login();
                }
                case 2 -> {
                    registration();
                }
                case 0 -> {
                    return;
                }
            }
        }
    }

    public void login() {
        String email = getStr("Enter your email");
        String password = getStr("Enter your password");

        LoginDto logindto = new LoginDto(email, password);

        Optional<UserRole> optional = authServise.login(logindto);

        if (optional.isEmpty()) {
            System.out.println("Invalid email or password");
            authMenu();
        }

        UserRole role = optional.get();

        if (role.equals(UserRole.USER)) {
            //userMenu();
        } else if (role.equals(UserRole.ADMIN)) {
            //adminMenu();
        }


    }


    public void registration() {
        String fullName = getStr("Enter your fullName: ");
        String email = getStr("Enter your email");
        String password = getStr("Create your password");

        int randomNumber = new Random().nextInt(10000, 100000);

        sendMessage(email, randomNumber);
        LocalTime localTime = LocalTime.now().plusMinutes(5);
        int number = getNum("Enter code: ");
        if (localTime.isAfter(LocalTime.now()) && number == randomNumber) {
            System.out.println("   ok   ");
            UserDto userDto = new UserDto(fullName, email, password);
            boolean res = authServise.registraton(userDto);
            if (res) {
                System.out.println("success");
            } else {
                System.out.println("Email already in use");
            }
        } else {
            System.out.println("Error");
        }


    }


    private void sendMessage(String email, int num) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");

        String userName = "6acc71f0758c36";
        String password = "407d3e418ab8d7";


        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setSubject("Verification Code");
            message.setContent("<h1 style=\"color:red;\">Your code: " + num + "</h1>", "text/html");
            message.setFrom(new InternetAddress("test@example.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));

            CompletableFuture.runAsync(() -> {
                try {
                    Transport.send(message);
                    System.out.println("\n[System] Email sent to: " + email);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        System.out.println("Message sending initiated...");
    }
}

