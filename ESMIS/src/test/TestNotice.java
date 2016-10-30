package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lanyuan.assembly.basic.commonmethods.OperatorInfo;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionNormal;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.OrderGroup;
import com.lanyuan.assembly.notice.ServiceNotice;
import com.lanyuan.assembly.notice.SingleNotice;
import com.lanyuan.assembly.notice.SingleNoticeToOperate;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring.xml", "classpath:config/spring-mybatis.xml" })
public class TestNotice
{
   
    
    @Autowired
    private ServiceNotice noticeService;
    
    private OperatorInfo operator = new OperatorInfo("12", "operatorName", "172.16.1.1");
   
    //新增
    @Test
    public void test1() {
        SingleNoticeToOperate single = new SingleNoticeToOperate();
        single.setDeadline("2016-08-30");
        single.setNoticeContent("noticeContent");
        single.setNoticeTitle("noticeTitle");
        single.setShowNum(1);
        int i = noticeService.add(single,operator);
            System.out.println("----------------------------");
            System.out.println("成功次数:"+i);
            System.out.println("----------------------------");
    }
        
    //修改
    @Test
    public void test2() {
        
        SingleNoticeToOperate single = new SingleNoticeToOperate();
        single.setDeadline("2016-12-30");
        single.setNoticeContent("noticeContent+++");
        single.setNoticeTitle("noticeTitle+++");
        single.setShowNum(1);
        int i = noticeService.update(single,operator,2);
        System.out.println("----------------------------");
        System.out.println(i);
        System.out.println("----------------------------");
    }
   
    //获取总数
    @Test
    public void test3() {
        ConditionGroup cond = new ConditionGroup();
        int i = noticeService.getTotalCount(cond);
        System.out.println("----------------------------");
        System.out.println(i);
        System.out.println("----------------------------");
    }
   
    //searchById
    @Test
    public void test4(){
        SingleNotice single = new SingleNotice();
        single = noticeService.selectById("1");
        System.out.println("----------------------------");
        System.out.println(single.getNoticeTitle());
        System.out.println("----------------------------");
    }
    
    //searchBycondition
    @Test
    public void test5(){
        
        List<SingleNotice> list = new ArrayList<SingleNotice>();
        ConditionGroup cond = new ConditionGroup();
        cond.addWithAnd(new ConditionNormal(ServiceNotice.MappingList.id.name(),"1"));
        OrderGroup order = new OrderGroup();
        order.Add(ServiceNotice.MappingList.id.name(), true);
        list = noticeService.selectList(cond, order);
        
        System.out.println("----------------------------");
        if(list != null && !list.isEmpty()){
            System.out.println(list.size());
            for (SingleNotice SingleNotice : list)
            {
                System.out.println(SingleNotice.getNoticeTitle());
            }
        }
        System.out.println("----------------------------");
    }
    
  //searchByconditionAndPage
    @Test
    public void test6(){
        
        List<SingleNotice> list = new ArrayList<SingleNotice>();
        ConditionGroup cond = new ConditionGroup();
        cond.addWithAnd(new ConditionNormal(ServiceNotice.MappingList.id.name(),"1"));
        OrderGroup order = new OrderGroup();
        order.Add(ServiceNotice.MappingList.id.name(), true);
        list = noticeService.selectPageList(1, 10, cond, order);
        
        System.out.println("----------------------------");
        if(list != null && !list.isEmpty()){
            for (SingleNotice SingleNotice : list)
            {
                System.out.println(SingleNotice.toString());
            }
        }
        System.out.println("----------------------------");
    }
    
    //删除
    @Test
    public void test7(){
        String id = "2";
        int i = noticeService.deleteById(id);
        System.out.println("----------------------------");
        System.out.println(i);
        System.out.println("----------------------------");
    }

}
