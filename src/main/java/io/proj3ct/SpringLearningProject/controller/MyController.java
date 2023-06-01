package io.proj3ct.SpringLearningProject.controller;

import io.proj3ct.SpringLearningProject.model.Request;
import io.proj3ct.SpringLearningProject.model.Response;
import io.proj3ct.SpringLearningProject.service.ModifyRequestService;
import io.proj3ct.SpringLearningProject.service.MyModifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;

@Slf4j
@RestController
public class MyController {

    private final MyModifyService myModifyService;
    private final ModifyRequestService modifyRequestService;

    @Autowired
    public MyController(@Qualifier("ModifyErrorMessage") MyModifyService myModifyService,
                        ModifyRequestService modifyRequestService){
        this.myModifyService = myModifyService;
        this.modifyRequestService = modifyRequestService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@RequestBody Request request){

        log.warn("Входящий request : " + String.valueOf(request));

        Response response = Response.builder().uid(request.getUid()).operationUid(request.getOperationUid())
                .systemTime(request.getSystemTime()).code("success").errorCode("").errorMessage("").build();

        modifyRequestService.modifyRq(request);
        Response responseAfterModify = myModifyService.modify(response);

        log.warn("Исходящий response : " + String.valueOf(response));

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
