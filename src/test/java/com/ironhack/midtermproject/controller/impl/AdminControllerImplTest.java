package com.ironhack.midtermproject.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.midtermproject.classes.Address;
import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.enums.TypeAccountEnum;
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

import static com.ironhack.midtermproject.utils.utils.encoderBase64;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerImplTest {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;
    private Account account1, account2, account3, account4;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    private AccountHolder accountHolder1, accountHolder2;
    private Address address1, address2;

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
        thirdPartyRepository.deleteAll();
        adminRepository.deleteAll();
        accountRepository.deleteAll();
        accountHolderRepository.deleteAll();
    }

    @Test
    void findAllAccounts_validUser() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", encoderBase64(admin1.getUsername(), "123"));

        MvcResult mvcResult = mockMvc.perform(get("/accounts").headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains(account1.getSecretKey()));
        assertTrue(mvcResult.getResponse().getContentAsString().contains(account1.getAccountId().toString()));
        assertTrue(mvcResult.getResponse().getContentAsString().contains(account4.getSecretKey()));
        assertTrue(mvcResult.getResponse().getContentAsString().contains(account4.getAccountId().toString()));
        assertTrue(mvcResult.getResponse().getContentAsString().contains(account2.getSecretKey()));
        assertTrue(mvcResult.getResponse().getContentAsString().contains(account3.getSecretKey()));
    }

    @Test
    void findAllAccounts_invalidUser() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", encoderBase64(accountHolder1.getUsername(), "aa"));

        MvcResult mvcResult = mockMvc.perform(get("/accounts").headers(httpHeaders))
                .andExpect(status().isForbidden())
                .andReturn();
    }

    @Test
    void findAccountById_validAccountId() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", encoderBase64(admin1.getUsername(), "123"));

        MvcResult mvcResult = mockMvc.perform(get("/accounts/"+ account1.getAccountId())
                        .headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains(account1.getSecretKey()));
        assertTrue(mvcResult.getResponse().getContentAsString().contains(account1.getAccountId().toString()));
        assertFalse(mvcResult.getResponse().getContentAsString().contains(account4.getSecretKey()));
        assertFalse(mvcResult.getResponse().getContentAsString().contains(account2.getSecretKey()));
        assertFalse(mvcResult.getResponse().getContentAsString().contains(account3.getSecretKey()));
    }

    @Test
    void findAccountById_InValidAccountId() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", encoderBase64(admin1.getUsername(), "123"));

        MvcResult mvcResult = mockMvc.perform(get("/accounts/5")
                        .headers(httpHeaders))
                .andExpect(status().isNotFound())
                .andReturn();
    }


    @Test
    void getBalanceByAccountId_validAccountId() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", encoderBase64(admin1.getUsername(), "123"));

        MvcResult mvcResult = mockMvc.perform(get("/accounts/balance/"+ account1.getAccountId())
                        .headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains(account1.getBalance().getAmount().toString()));
        assertFalse(mvcResult.getResponse().getContentAsString().contains(account2.getBalance().getAmount().toString()));
    }

    @Test
    void findByTypeAccount_validType() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", encoderBase64(admin1.getUsername(), "123"));

        MvcResult mvcResult = mockMvc.perform(get("/accounts/typeAccount/"+ TypeAccountEnum.CHECKING)
                        .headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains(account3.getSecretKey()));
        assertFalse(mvcResult.getResponse().getContentAsString().contains(account1.getSecretKey()));
        assertFalse(mvcResult.getResponse().getContentAsString().contains(account2.getSecretKey()));

    }

    @Test
    void findAllTransfer() {
    }

    @Test
    void createNewAccountSaving() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", encoderBase64(admin1.getUsername(), "123"));

        Saving newAccount = new Saving(accountHolder1, "123456", new Money(new BigDecimal(8910)));
        String body = objectMapper.writeValueAsString(newAccount);

        MvcResult mvcResult = mockMvc.perform(post("/accounts/saving")
                        .headers(httpHeaders)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains(accountHolder1.getName()));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("123456"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("8910"));

    }

    @Test
    void createNewAccountChecking() {
    }

    @Test
    void createNewAccountCreditCard() {
    }

    @Test
    void createThirdParty() {
    }

    @Test
    void updateBalanceByAccountId() {
    }

    @Test
    void deleteAccount() {
    }
}