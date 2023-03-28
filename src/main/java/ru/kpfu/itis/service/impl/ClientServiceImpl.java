package ru.kpfu.itis.service.impl;

import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.config.MailConfig;
import ru.kpfu.itis.dto.ClientDto;
import ru.kpfu.itis.dto.CreateClientRequestDto;
import ru.kpfu.itis.dto.CreateUserRequestDto;
import ru.kpfu.itis.dto.UserResponseDto;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.shop.Client;
import ru.kpfu.itis.repository.shop.ClientRepository;
import ru.kpfu.itis.service.ClientService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository clientRepository;

    private final BCryptPasswordEncoder encoder;

    private final JavaMailSender javaMailSender;
    private final MailConfig mailConfig;

    public ClientDto getByEmail(String email) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
        }
        return clientRepository.getClientByEmail(email).stream().map(ClientDto::fromEntity).findFirst().orElse(null);
    }

    public Client findByName(String name) {
        return clientRepository.findByName(name);
    }

    public Optional<Client> findById(Integer id) {
        return clientRepository.findById(id);
    }

    public List<Client> findByCartIsNull() {
        return clientRepository.findByCartIsNull();
    }

    @Override
    public ClientDto create(CreateClientRequestDto createClientDto, String url) {
        String code = RandomString.make(64);
        String encodedPassword = encoder.encode(createClientDto.getPassword());
        Client client = Client.builder()
                .name(createClientDto.getName())
                .email(createClientDto.getEmail())
                .verificationCode(code)
                .password(encodedPassword)
                .build();
        clientRepository.save(client);
        sendVerificationMail(createClientDto.getEmail(), createClientDto.getName(), code, url);
        return ClientDto.fromEntity(client);
    }
    @Override
    public boolean verify(String verificationCode) {
        Client client = clientRepository.findByVerificationCode(verificationCode);
        if (client != null) {
            client.setVerificationCode(null);
            client.setEnabled(true);
            clientRepository.save(client);
            return true;
        }
        return false;
    }

    @Override
    public void sendVerificationMail(String mail, String name, String code, String url) {
        String from = mailConfig.getFrom();
        String sender = mailConfig.getSender();
        String subject = mailConfig.getSubject();
        String content = mailConfig.getContent();


        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {
            helper.setFrom(from, sender);

            helper.setTo(mail);
            helper.setSubject(subject);

            content = content.replace("{name}", name);
            content = content.replace("{url}", url + "/verification?code=" + code);

            helper.setText(content, true);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mimeMessage);
    }

}
