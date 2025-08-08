package com.example.HospitalManagment.service;

import com.example.HospitalManagment.common.PageObject;
import com.example.HospitalManagment.common.ResponseObject;
import com.example.HospitalManagment.enums.Action;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;

@Log4j2
public class BaseService<T> {

    protected final String Action_Create = Action.CREATE.name();
    protected final String Action_Update = Action.UPDATE.name();
    protected final String Action_Delete = Action.DELETE.name();
    protected final String Action_Deactivate = Action.DEACTIVATE.name();

    protected final String Message_Created = Action.CREATE.name();
    protected final String Message_Update = Action.UPDATE.name();
    protected final String Message_Delete = Action.DELETE.name();
    protected final String Message_Deactivate = Action.DEACTIVATE.name();



    protected final String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    protected static String generateRandomPassword(int len) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
                +"lmnopqrstuvwxyz!@#$%&";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }

    protected static String genrateRadomNumber() {

        Random random = new Random();
        long number = 10000 + random.nextLong();

        return String.valueOf(Math.abs(number));
    }

    protected PageObject mapPage(Page page) {
        return this.mapPage(page, null);
    }
    protected PageObject mapPage(Page page, List<T> data) {
        String methodName = "mapPage";

        log.info("{} -> Map page object: {}", methodName, page);
        PageObject pageObject = new PageObject();

        if (data != null) {
            pageObject.setData(data);
        } else {
            pageObject.setData(page.getContent());
        }

        pageObject.setPage(page.getPageable().getPageNumber()+1);
        pageObject.setSize(page.getPageable().getPageSize());
        pageObject.setTotalPages(page.getTotalPages());
        pageObject.setTotalSize(page.getTotalElements());

        log.info("{} -> Map page object: {}, page: {}, size: {}, totalPage: {}, totalSize", methodName, page,
                pageObject.getPage(), pageObject.getSize(), pageObject.getTotalPages(), pageObject.getTotalSize());
        return pageObject;
    }

    protected static String searchString(String search) {

        return search.trim().toLowerCase(Locale.ROOT);

    }



    protected ResponseObject<?> isPresentDataResult(Optional object){
        if(object.isEmpty()){
            ResponseObject<?> dataResult = new ResponseObject<>();
            dataResult.prepareHttpStatus(HttpStatus.BAD_REQUEST);

            return  dataResult;
        }
        return new ResponseObject<>();
    }
}