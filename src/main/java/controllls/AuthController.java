package controllls;

import dtos.UserDto;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import servise.AuthServise;

import java.time.LocalTime;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import static utils.Utill.*;
public class AuthController {
    private AuthServise authServise=AuthServise.geInstanse();

    public void  authMenu(){
        while(true){
            System.out.println("""
                    1 login
                    2 registration
                    0 exit""");
            int menu = scStr.nextInt();
            switch(menu){
                case 1->{login();}
                case 2->{registration();}
                case 0->{return;}
            }
        }
    }
    public void login(){
        String email=getStr("Enter your email");
        String password=getStr("Enter your password");



    }



    public void registration(){
        String fullname=getStr("Enter your fullname: ");
        String email=getStr("Enter your email: ");
        String pasword=getStr("Create your password: ");

        int randomNumber=new Random().nextInt(10000,100000);

        sendMessage(email,randomNumber );
        LocalTime localTime=LocalTime.now().plusMinutes(1);
        int number=getNum("Enter code: ");
        if (localTime.isAfter(LocalTime.now())&&number==randomNumber){
            System.out.println("   ok   ");
            UserDto userDto=new UserDto(fullname,email,pasword);
            boolean res=authServise.registraton(userDto);
            if (res){
                System.out.println("success");
            }else  {
                System.out.println("fail");
            }
        }
        else{
            System.out.println("Errorr");
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


        Session session = Session.getInstance(properties, new jakarta.mail.Authenticator() {
            @Override
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
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
