package com.dcits.govsbu.sd.taxbankportal.excel.service.Impl;

import com.dcits.govsbu.sd.taxbankportal.excel.mapper.TbEntFinanceNewMappers;
import com.dcits.govsbu.sd.taxbankportal.excel.model.TbEntFinanceNews;
import com.dcits.govsbu.sd.taxbankportal.excel.service.ExcelService;
import com.dcits.govsbu.sd.taxbankportal.excel.util.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;

@Service("excelService")
public class ExcelServiceImpl implements ExcelService {
    @Autowired
    private TbEntFinanceNewMappers tbEntFinanceNewMappers;
    @Override
    public int insert(TbEntFinanceNews tbEntFinanceNews) {
        return tbEntFinanceNewMappers.insertSelective(tbEntFinanceNews);
    }

    @Override
    public String ajaxUploadExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        MultipartFile file = multipartRequest.getFile("upfile");
        if(file.isEmpty()){
            try {
                throw new Exception("文件不存在！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        InputStream in =null;
        try {
            in = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<List<Object>> listob = null;
        try {
            listob = new ExcelUtils().getBankListByExcel(in,file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
        for (int i = 0; i < listob.size(); i++) {
            List<Object> lo = listob.get(i);
            TbEntFinanceNews vo = new TbEntFinanceNews();
            /*//这里是主键验证，根据实际需要添加，可要可不要，加上之后，可以对现有数据进行批量修改和导入
            TbEntFinanceNews j = null;
			try {
				j = tbEntFinanceNewMapper.selectByPrimaryKey(Integer.valueOf(String.valueOf(lo.get(0))));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				System.out.println("没有新增");
			}*/
            //vo.setId(Integer.valueOf(String.valueOf(lo.get(0))));  // 刚开始写了主键，由于主键是自增的，又去掉了，现在只有批量插入的功能，不能对现有数据进行修改了
            vo.setEntname(String.valueOf(lo.get(0)));     // 表格的第一列   注意数据格式需要对应实体类属性
            vo.setEncreditid(String.valueOf(String.valueOf(lo.get(1))));   // 表格的第二列
            //以此类推有多少写多少
            vo.setIndname(String.valueOf(String.valueOf(lo.get(5))));
            vo.setRegmoney(String.valueOf(String.valueOf(lo.get(6))));
            vo.setCompanyStatus(String.valueOf(String.valueOf(lo.get(7))));
            vo.setRegistertime(Date.valueOf(String.valueOf(lo.get(16))));
            /*//由于数据库中此字段是datetime，所以要将字符串时间格式：yyyy-MM-dd HH:mm:ss，转为Date类型
            if (lo.get(16) != null && lo.get(16) != "") {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                vo.setRegistertime(sdf.parse(String.valueOf(lo.get(16))));
            }else {
               // vo.setRegistertime(new Date());
            }*/
            System.out.println("从excel中读取的实体类对象："+ vo);
            this.insert(vo);
			/*if(j == null)
			{
		            userMapper.insert(vo);
			}
			else
			{
		            userMapper.updateByPrimaryKey(vo);
			}*/
        }
        System.out.println("文件导入成功！");
        return "文件导入成功！";
    }
}
