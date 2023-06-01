package io.proj3ct.SpringLearningProject.service;

import io.proj3ct.SpringLearningProject.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("ModifyErrorMessage")
public class ModifyErrorMessage implements MyModifyService{
    @Override
    public Response modify(Response response){
        response.setSystemTime("Что-то сломалось");
        return response;
    }
}
