package com.dcits.govsbu.sd.taxbankportal.excel.controller;


import com.dcits.govsbu.sd.taxbankportal.excel.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/test/")
public class ExcelControlller {
@Autowired
    private ExcelService excelService;
    @ResponseBody
    @RequestMapping(value="fileUpload",method = RequestMethod.POST,produces = "application/text; charset=utf-8")
    public String ajaxUploadExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("111");
        return excelService.ajaxUploadExcel(request, response);
       // return null;
    }
}
