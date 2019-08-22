package com.dcits.govsbu.sd.taxbankportal.excel.service;

import com.dcits.govsbu.sd.taxbankportal.excel.model.TbEntFinanceNews;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ExcelService {
    public int insert(TbEntFinanceNews tbEntFinanceNews);

    String ajaxUploadExcel(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
