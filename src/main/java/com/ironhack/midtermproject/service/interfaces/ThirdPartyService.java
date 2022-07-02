package com.ironhack.midtermproject.service.interfaces;

import com.ironhack.midtermproject.controller.dto.TransferSendMoneyToAHFromThirdPartyDTO;
import com.ironhack.midtermproject.controller.dto.TransferThirdPartyGetMoneyFromAHDTO;
import com.ironhack.midtermproject.models.transfers.Transfer;

import java.util.List;

public interface ThirdPartyService {
    List<Transfer> findTransferReceiver(Long thirdId, String hashKey);

    List<Transfer> findTransferSender(Long thirdId, String hashKey);

    void sendMoneyAccountHolder(Long thirdId, String hashKey, TransferSendMoneyToAHFromThirdPartyDTO transferSendMoneyThirdPartyDTO);
    void getMoneyFromAccountHolder(Long thirdId, String hashKey, TransferThirdPartyGetMoneyFromAHDTO transferThirdPartyGetMoneyFromAHDTO);
    }
