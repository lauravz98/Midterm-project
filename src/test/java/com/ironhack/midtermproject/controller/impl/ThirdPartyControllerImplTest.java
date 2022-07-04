package com.ironhack.midtermproject.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.midtermproject.classes.Address;
import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.controller.dto.TransferSendMoneyToAHFromThirdPartyDTO;
import com.ironhack.midtermproject.controller.dto.TransferSendMoneyToAccountHolderFromAHDTO;
import com.ironhack.midtermproject.controller.dto.TransferThirdPartyGetMoneyFromAHDTO;
import com.ironhack.midtermproject.models.accounts.*;
import com.ironhack.midtermproject.models.users.AccountHolder;
import com.ironhack.midtermproject.models.users.Admin;
import com.ironhack.midtermproject.models.users.ThirdParty;
import com.ironhack.midtermproject.repository.accounts.AccountRepository;
import com.ironhack.midtermproject.repository.transfers.TransferRepository;
import com.ironhack.midtermproject.repository.users.AccountHolderRepository;
import com.ironhack.midtermproject.repository.users.AdminRepository;
import com.ironhack.midtermproject.repository.users.ThirdPartyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import static com.ironhack.midtermproject.utils.utils.encoderBase64;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ThirdPartyControllerImplTest {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private AccountRepository accountRepository;
    private Account account1, account2, account3, account4;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    private AccountHolder accountHolder1, accountHolder2;
    private Address address1, address2;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminRepository adminRepository;
    private Admin admin1, admin2;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    private ThirdParty thirdParty;

    @BeforeEach
    void setUp() throws ParseException {
        admin1 = new Admin("Admin1", passwordEncoder.encode("123"));
        admin2 = new Admin("Admin2", passwordEncoder.encode("1234"));
        adminRepository.saveAll(List.of(admin1, admin2));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        address1 = new Address("Enim. Avenue", 3,"Cáceres", "France");
        accountHolder1 = new AccountHolder("Vanna Maynard", passwordEncoder.encode("aa"), sdf.parse("1999-08-13"), address1);

        address2 = new Address("Pellentesque, Avenue", 4,"Melilla", "Spain");
        accountHolder2 = new AccountHolder("Ruth Hogan", passwordEncoder.encode("aa"), sdf.parse("1981-11-29"), address2);

        accountHolderRepository.saveAll(List.of(accountHolder1, accountHolder2));

        account1 = new Saving(accountHolder1, "YNQ23PAD6PT", new Money(new BigDecimal(1015)));
        account2 = new CreditCard(accountHolder2, accountHolder1, sdf.parse("2019-08-13"), "HPJ73PDN1LX", new Money(new BigDecimal(1015)), null, null);
        account3 = new Checking(accountHolder2, null, sdf.parse("2019-12-12"), "IHK24EXJ0UC",  new Money(new BigDecimal(2015)));
        account4 = new StudentChecking(accountHolder1, "VEO46CPC5DR", new Money(new BigDecimal(2015)));

        accountRepository.saveAll(List.of(account1, account2, account3, account4));

        thirdParty = new ThirdParty("647086f4-b159-4c4b-91fb-b785bb1364f8");
        thirdPartyRepository.save(thirdParty);
    }

    @AfterEach
    void tearDown() {
        adminRepository.deleteAll();
        transferRepository.deleteAll();
        accountRepository.deleteAll();
        accountHolderRepository.deleteAll();
        thirdPartyRepository.deleteAll();
    }

    @Test
    void findTransfersReceiverByThirdId() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("ID", "1");
        httpHeaders.add("Hash-Key", thirdParty.getHashKey());

        Money amountTransfer =  new Money(new BigDecimal(100));
        TransferThirdPartyGetMoneyFromAHDTO transferThirdPartyGetMoneyFromAHDTO =
                new TransferThirdPartyGetMoneyFromAHDTO(amountTransfer, account2.getPrimaryOwner().getName(),account2.getAccountId(),  account2.getSecretKey());
        String body = objectMapper.writeValueAsString(transferThirdPartyGetMoneyFromAHDTO);

        MvcResult mvcResult = mockMvc.perform(patch("/thirdParty/transfer/receiveMoney")
                        .headers(httpHeaders)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        MvcResult mvcResult2 = mockMvc.perform(get("/thirdParty/transfersReceived")
                        .headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        System.out.println(mvcResult2.getResponse().getContentAsString());
        assertTrue(mvcResult2.getResponse().getContentAsString().contains(account2.getPrimaryOwner().getName()));


    }

    @Test
    void findTransfersSenderByThirdId() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("ID", "1");
        httpHeaders.add("Hash-Key", thirdParty.getHashKey());

        Money amountTransfer =  new Money(new BigDecimal(100));
        TransferSendMoneyToAHFromThirdPartyDTO transferSendMoneyToAHFromThirdPartyDTO =
                new TransferSendMoneyToAHFromThirdPartyDTO(amountTransfer, account2.getAccountId(), account2.getPrimaryOwner().getName(), account2.getSecretKey());
        String body = objectMapper.writeValueAsString(transferSendMoneyToAHFromThirdPartyDTO);

        MvcResult mvcResult = mockMvc.perform(patch("/thirdParty/transfer/sendMoney")
                        .headers(httpHeaders)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        MvcResult mvcResult2 = mockMvc.perform(get("/thirdParty/transfersSent")
                        .headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        System.out.println(mvcResult2.getResponse().getContentAsString());
        assertTrue(mvcResult2.getResponse().getContentAsString().contains(account2.getPrimaryOwner().getName()));


    }

    @Test
    void sendMoneyAccountHolder_validHashKey() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("ID", "1");
        httpHeaders.add("Hash-Key", thirdParty.getHashKey());

        Money amountTransfer =  new Money(new BigDecimal(100));
        TransferSendMoneyToAHFromThirdPartyDTO transferSendMoneyToAHFromThirdPartyDTO =
                new TransferSendMoneyToAHFromThirdPartyDTO(amountTransfer, account2.getAccountId(), account2.getPrimaryOwner().getName(), account2.getSecretKey());
        String body = objectMapper.writeValueAsString(transferSendMoneyToAHFromThirdPartyDTO);

        MvcResult mvcResult = mockMvc.perform(patch("/thirdParty/transfer/sendMoney")
                        .headers(httpHeaders)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        Money newAccount2 = new Money(account2.getBalance().increaseAmount(amountTransfer));
        Optional<Account> optionalAccount2 = accountRepository.findById(account2.getAccountId());

        assertEquals(optionalAccount2.get().getBalance(), newAccount2);

    }

    @Test
    void sendMoneyAccountHolder_InvalidHashKey() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("ID", "1");
        httpHeaders.add("Hash-Key", "c6671408-ae94-4628-9b75-9a83ee936acd");

        Money amountTransfer =  new Money(new BigDecimal(100));
        TransferSendMoneyToAHFromThirdPartyDTO transferSendMoneyToAHFromThirdPartyDTO =
                new TransferSendMoneyToAHFromThirdPartyDTO(amountTransfer, account2.getAccountId(), account2.getPrimaryOwner().getName(), account2.getSecretKey());
        String body = objectMapper.writeValueAsString(transferSendMoneyToAHFromThirdPartyDTO);

        MvcResult mvcResult = mockMvc.perform(patch("/thirdParty/transfer/sendMoney")
                        .headers(httpHeaders)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

    }
    @Test
    void receiveMoneyAccountHolder_validSecretKey() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("ID", "1");
        httpHeaders.add("Hash-Key", thirdParty.getHashKey());

        Money amountTransfer =  new Money(new BigDecimal(100));
        TransferThirdPartyGetMoneyFromAHDTO transferThirdPartyGetMoneyFromAHDTO =
                new TransferThirdPartyGetMoneyFromAHDTO(amountTransfer, account2.getPrimaryOwner().getName(),account2.getAccountId(),  account2.getSecretKey());
        String body = objectMapper.writeValueAsString(transferThirdPartyGetMoneyFromAHDTO);

        MvcResult mvcResult = mockMvc.perform(patch("/thirdParty/transfer/receiveMoney")
                        .headers(httpHeaders)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        Money newAccount2 = new Money(account2.getBalance().decreaseAmount(amountTransfer));
        Optional<Account> optionalAccount2 = accountRepository.findById(account2.getAccountId());

        assertEquals(optionalAccount2.get().getBalance(), newAccount2);
    }

    @Test
    void receiveMoneyAccountHolder_InvalidSecretKey() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("ID", "1");
        httpHeaders.add("Hash-Key", thirdParty.getHashKey());

        Money amountTransfer =  new Money(new BigDecimal(100));
        TransferThirdPartyGetMoneyFromAHDTO transferThirdPartyGetMoneyFromAHDTO =
                new TransferThirdPartyGetMoneyFromAHDTO(amountTransfer, account2.getPrimaryOwner().getName(),account2.getAccountId(),  account1.getSecretKey());
        String body = objectMapper.writeValueAsString(transferThirdPartyGetMoneyFromAHDTO);

        MvcResult mvcResult = mockMvc.perform(patch("/thirdParty/transfer/receiveMoney")
                        .headers(httpHeaders)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

    }
}