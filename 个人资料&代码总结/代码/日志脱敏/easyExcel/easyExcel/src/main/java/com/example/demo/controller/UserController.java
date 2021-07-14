package com.example.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.example.demo.model.Park;
import com.example.demo.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import java.util.*;

/**
 * @Author:wjup
 * @Date: 2018/9/26 0026
 * @Time: 14:42
 */

@Controller
@RequestMapping("/testBoot")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("getUser/{id}")
    public String GetUser(@PathVariable int id, Model model) {
        logger.info("mobileNo=17855911518");
        logger.info("idCardNo=140321199606482668");
        logger.info("name=王爬爬");
        log.info("mobileNo=17855911518============idCardNo=140321199606482668=============name=王爬爬");

        Map<String, String> user = new HashMap<String, String>();
        user.put("mobileNo", "17855911518");
        user.put("手机号", "17855911518");
        user.put("idCardNo", "140321199606482668");
        user.put("name", "王爬爬");
        JSONObject userDetails = new JSONObject(user);
        logger.info("User JSON: {}", userDetails);
        User u = userService.Sel(id);
        model.addAttribute("user", u);
        logger.error("xxxxxxxxxxx" + u.toString());
        return "index";
    }

    //分页导出 每页行数上限是：65535
/*    xls是03版的excel，行数限制6w+
    xlsx是07版excel，行数限制100w+*/
    @RequestMapping("export")
    public String export(){
        int num = userService.getCount();
        log.info("=====数据量为：===="+num);
        //只需要更改后缀名，就可以导出两种不同格式的excel
        //String path = "D:\\"+UUID.randomUUID().toString().replace("-", "").toUpperCase()+".xls";
        String path = "D:\\"+UUID.randomUUID().toString().replace("-", "").toUpperCase()+".xlsx";
        ExcelWriter build = EasyExcel.write(path, User.class).build();
        //每50W条一个sheet页  xls不能达到50W条  注意调整
        for(int i = 0; i< num ;i+=500000){
            List<User> temp = userService.getUserByPage(i,500000);
            WriteSheet build1 = EasyExcel.writerSheet(i+"~"+(i+500000)).build();
            build.write(temp,build1);
        }
        build.finish();
        System.out.println("导出成功！！！");
        return "export";
    }


    //动态表头  就不用在实体前加注解了
    @RequestMapping("exportBySelfTitle")
    public String exportBySelfTitle(){

        //1.用实体的方式
       // List<Student> list = getList();
        //2.用非实体方式
        List<List<String>> list = getResolveList();

        List<List<String>> heads = new ArrayList<>();
        List<String> head1 = new ArrayList<>();
        head1.add("基本信息");
        head1.add("ID");
        List<String> head2 = new ArrayList<>();
        head2.add("基本信息");
        head2.add("姓名");
        List<String> head3 = new ArrayList<>();
        head3.add("基本信息");
        head3.add("性别");
        List<String> head4 = new ArrayList<>();
        head4.add("教育信息");
        head4.add("年级");
        List<String> head5 = new ArrayList<>();
        head5.add("教育信息");
        head5.add("老师");

        heads.add(head1);
        heads.add(head2);
        heads.add(head3);
        heads.add(head4);
        heads.add(head5);


        String fileName = "D:\\"+UUID.randomUUID().toString().replace("-", "").toUpperCase()+".xlsx";
        ExcelWriterBuilder builder = EasyExcel.write(fileName);


        builder.head(heads).sheet("学生信息").doWrite(list);
        System.out.println("导出成功！！！");
        return "export";
    }

    //不一样的sheet导出不一样的数据
    @RequestMapping("exportByDifferenceSheet")
    public String exportByDifferenceSheet(){
        int num = userService.getCount();
        log.info("=====数据量为：===="+num);
        //只需要更改后缀名，就可以导出两种不同格式的excel
        //String path = "D:\\"+UUID.randomUUID().toString().replace("-", "").toUpperCase()+".xls";
        String path = "D:\\"+UUID.randomUUID().toString().replace("-", "").toUpperCase()+".xlsx";
        List<User> temp = userService.getUserByPage(0,500);
        List<Park> parks = new ArrayList<>();
        parks.add(new Park("ss","park","beijing","xicheng","bucuo","henduo"));

        ExcelWriter build = EasyExcel.write(path).build();
        WriteSheet sheet1 = EasyExcel.writerSheet("sheet1").head(User.class).build();
        WriteSheet sheet2 = EasyExcel.writerSheet("sheet2").head(Park.class).build();
        build.write(temp,sheet1);
        build.write(parks,sheet2);
        build.finish();
        System.out.println("导出成功！！！");
        return "export";
    }
    public static void main(String args[]) {
        // 文件输出位置
        String fileName = "D:\\"+UUID.randomUUID().toString().replace("-", "").toUpperCase()+".xlsx";
        ExcelWriterBuilder builder = EasyExcel.write(fileName);
        List<Object> data = new ArrayList<>();
        List<List<String>> header = head(data);
        System.out.println(data.toString());
        List<List<Object>> list = new ArrayList<>();
        list.add(data);
        builder.head(header).sheet("模板").doWrite(list);

    }

    /**
     * 返回的数据结构如下
     * {"data":{"xxxxxxx":{"":32,"1011":42,"1010":8,"1008":52,"0006":3,"0004":86,"1004":2,"0013":7,"0002":7,"1003":4,"0003":56,"1002":18,"0011":13,"1001":12,"0012":6,"0001":66,"1009":42}},
     * "deviceTypeSet":{"switching":["1008","1004","1003","1002","1001","1011","1010"],
     * "analog":["0006","0004","0013","0002","0003","0011","0012","0001"],
     * "switchingOff":["1009"],"substation":[""]},
     * "success":true}
     *
     * @return
     */
    //解析表头  同时填充data  表头和data是互不相干的
    private static List<List<String>> head(List<Object> data) {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        head0.add("机构名称");
        list.add(head0);
        data.add("张璐的矿");
        Map<String, List<String>> map = getHeader();
        Map<String, Long> dataMap = getData();
        map.forEach((k, v) -> {
            String deviceCategory = k;
            List<String> ls = v;
            ls.forEach(e -> {
                List<String> head = new ArrayList<>();
                head.add(deviceCategory);
                head.add(e);
                list.add(head);
                if (dataMap.containsKey(e)) {
                    data.add(dataMap.get(e));
                } else {
                    data.add(0);
                }
            });

        });
        List<String> headn = new ArrayList<String>();
        headn.add("合计");
        list.add(headn);
        data.add(dataMap.get("合计"));
        return list;
    }

    private static Map<String, Long> getData() {
        //{"10017904-1":
        //{"":32,"1011":42,"1010":8,"1008":52,"0006":3,"0004":86,"1004":2,"0013":7,"0002":7,"1003":4,"0003":56,"1002":18,"0011":13,"1001":12,"0012":6,"0001":66,"1009":42}},
        Map<String, Long> data = new HashMap<>();
        long atotal = 0;
        long stotal = 0;
        long subtotal = 0;
        for (int i = 0; i < 10; i++) {
            String column = "温度" + i;
            atotal += Math.round(Math.random()) + i;
            data.put(column, Math.round(Math.random()) + i);
            String scolumn = "风门" + i;
            stotal += Math.round(Math.random()) + i;
            data.put(scolumn, Math.round(Math.random()) + i);
        }
        data.put("小计1", atotal);
        data.put("小计2", stotal);
        String subColumn = "其它";
        data.put(subColumn, 55l);
        data.put("小计3", 55l);
        data.put("合计", 55 + atotal + stotal);
        return data;
    }

    private static Map<String, List<String>> getHeader() {
        Map<String, List<String>> map = new HashMap<>();
        List<String> aList = new ArrayList<>();
        List<String> sList = new ArrayList<>();
        List<String> subList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            String column = "温度" + i;
            aList.add(column);
            String scolumn = "风门" + i;
            sList.add(scolumn);
        }
        aList.add("小计1");
        sList.add("小计2");
        String subColumn = "其它";
        subList.add(subColumn);
        subList.add("小计3");
        map.put("模拟量传感器", aList);
        map.put("开关量传感器", sList);
        return map;
    }

    private List<Student>  getList(){
        List<Student> list = new ArrayList<>();
        list.add(new Student("1","zhangsan","nan","1","tom"));
        list.add(new Student("2","lisi","nv","2","peter"));
        list.add(new Student("3","wangwu","nan","3","ppp"));
        list.add(new Student("4","zhaoliu","nv","4","lilei"));
        return list;
    }

    private List<List<String>> getResolveList(){
        List<List<String>> result = new ArrayList<>();
        List<Student> list = this.getList();
        //对其进行分解
        list.forEach(x->{
            List<String> temp = new ArrayList<>();
            temp.add(x.getId());
            temp.add(x.getName());
            temp.add(x.getSex());
            temp.add(x.getGrade());
            temp.add(x.getTeacher());
            result.add(temp);
        });
        return result;
    }

}