package com.ironhack.midtermproject.controller.interfaces;

import com.ironhack.midtermproject.controller.dto.TransferSendMoneyToAHFromThirdPartyDTO;
import com.ironhack.midtermproject.controller.dto.TransferThirdPartyGetMoneyFromAHDTO;
import com.ironhack.midtermproject.models.transfers.Transfer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public interface ThirdPartyController {

    List<Transfer> findTransfersReceiverByThirdId(Long thirdId, String hashKey);
    List<Transfer> findTransfersSenderByThirdId(Long thirdId, String hashKey);
    void sendMoneyAccountHolder(Long thirdId, String hashKey, TransferSendMoneyToAHFromThirdPartyDTO transferSendMoneyToAHFromThirdPartyDTO);
    void receiveMoneyAccountHolder(Long thirdId, String hashKey, TransferThirdPartyGetMoneyFromAHDTO transferThirdPartyGetMoneyFromAHDTO);

}
