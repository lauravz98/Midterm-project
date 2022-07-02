package com.ironhack.midtermproject.controller.impl;

import com.ironhack.midtermproject.controller.dto.TransferSendMoneyToAHFromThirdPartyDTO;
import com.ironhack.midtermproject.controller.dto.TransferThirdPartyGetMoneyFromAHDTO;
import com.ironhack.midtermproject.controller.interfaces.ThirdPartyController;
import com.ironhack.midtermproject.models.transfers.Transfer;
import com.ironhack.midtermproject.service.interfaces.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ThirdPartyControllerImpl implements ThirdPartyController {

    @Autowired
    private ThirdPartyService thirdPartyService;

    @GetMapping("/thirdParty/transfersReceived")
    @ResponseStatus(HttpStatus.OK)
    public List<Transfer> findTransfersReceiverByThirdId(@RequestHeader("ID")Long thirdId, @RequestHeader("Hash-Key")String hashKey) {
        return thirdPartyService.findTransferReceiver(thirdId, hashKey);
    }
    @GetMapping("/thirdParty/transfersSent")
    @ResponseStatus(HttpStatus.OK)
    public List<Transfer> findTransfersSenderByThirdId(@RequestHeader("ID")Long thirdId, @RequestHeader("Hash-Key")String hashKey) {
        return thirdPartyService.findTransferSender(thirdId, hashKey);
    }
    @PatchMapping("/thirdParty/transfer/sendMoney")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendMoneyAccountHolder(@RequestHeader("ID")Long thirdId, @RequestHeader("Hash-Key")String hashKey, @RequestBody TransferSendMoneyToAHFromThirdPartyDTO transferSendMoneyToAHFromThirdPartyDTO) {
        thirdPartyService.sendMoneyAccountHolder(thirdId, hashKey, transferSendMoneyToAHFromThirdPartyDTO);
    }

    @PatchMapping("/thirdParty/transfer/receiveMoney")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void receiveMoneyAccountHolder(@RequestHeader("ID")Long thirdId, @RequestHeader("Hash-Key")String hashKey, @RequestBody TransferThirdPartyGetMoneyFromAHDTO transferThirdPartyGetMoneyFromAHDTO) {
        thirdPartyService.getMoneyFromAccountHolder(thirdId, hashKey, transferThirdPartyGetMoneyFromAHDTO);
    }
}
