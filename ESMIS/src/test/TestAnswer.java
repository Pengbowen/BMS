package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lanyuan.assembly.answer.ServiceAnswer;
import com.lanyuan.assembly.answer.SingleAnswer;
import com.lanyuan.assembly.answer.SingleAnswerToAdd;
import com.lanyuan.assembly.answer.SingleAnswerToUpdata;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionNormal;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.OrderGroup;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring.xml", "classpath:config/spring-mybatis.xml" })
public class TestAnswer
{   
    
    @Autowired
    private ServiceAnswer answerService;
    
   
    //新增意见建议
    @Test
    public void test1() {
        
        SingleAnswerToAdd single = new SingleAnswerToAdd();
        single.setSubmitContent("submitContent");
        single.setSubmitID("submitID");
        single.setSubmitIP("submitIP");
        single.setSubmitName("submitName");
        single.setSubmitorEmail("submitorEmail");
        single.setSubmitorMobile("submitorMobile");
        single.setSubmitTitle("submitTitle");
        int i = answerService.addAnswer(single);
            System.out.println("----------------------------");
            System.out.println("成功次数:"+i);
            System.out.println("----------------------------");
    }
    
    //分享标准
    @Test
    public void test2() {
        
        SingleAnswerToAdd single = new SingleAnswerToAdd();
        single.setSubmitContent("submitContent");
        single.setSubmitID("submitID");
        single.setSubmitIP("submitIP");
        single.setSubmitName("submitName");
        single.setSubmitorEmail("submitorEmail");
        single.setSubmitorMobile("submitorMobile");
        single.setSubmitTitle("submitTitle");
        int i = answerService.addCriteria(single,"filePath");
            System.out.println("----------------------------");
            System.out.println("成功次数:"+i);
            System.out.println("----------------------------");
    }
   
    //我要标准
    @Test
    public void test3() {
        
        SingleAnswerToAdd single = new SingleAnswerToAdd();
        single.setSubmitContent("submitContent");
        single.setSubmitID("submitID");
        single.setSubmitIP("submitIP");
        single.setSubmitName("submitName");
        single.setSubmitorEmail("submitorEmail");
        single.setSubmitorMobile("submitorMobile");
        single.setSubmitTitle("submitTitle");
        int i = answerService.addAskForCriteria(single);
            System.out.println("----------------------------");
            System.out.println("成功次数:"+i);
            System.out.println("----------------------------");
    }
        
    //反馈意见建议
    @Test
    public void test4() {
        
        SingleAnswerToUpdata single = new SingleAnswerToUpdata();
        single.setReceiveContent("receiveContent");
        single.setReceiveIP("receiveIP");
        single.setReceiveName("receiveName");
        int i = answerService.replyAnswer(single, 1);
        System.out.println("----------------------------");
        System.out.println(i);
        System.out.println("----------------------------");
    }
   
    //获取总数
    @Test
    public void test7() {
        
        int i = answerService.getTotalCount(null);
        System.out.println("----------------------------");
        System.out.println(i);
        System.out.println("----------------------------");
    }
   
    //searchById
    @Test
    public void test8(){
        SingleAnswer single = new SingleAnswer();
        single = answerService.selectById("1");
        System.out.println("----------------------------");
        System.out.println(single.getSubmitType());
        System.out.println("----------------------------");
    }
    
    //searchBycondition
    @Test
    public void test9(){
        
        List<SingleAnswer> list = new ArrayList<SingleAnswer>();
        ConditionGroup cond = new ConditionGroup();
        cond.addWithAnd(new ConditionNormal(ServiceAnswer.MappingList.id.name(),"1"));
        OrderGroup order = new OrderGroup();
        list = answerService.selectByCondition(cond, order);
        
        System.out.println("----------------------------");
        if(list != null && !list.isEmpty()){
            System.out.println(list.size());
            for (SingleAnswer singleAnswer : list)
            {
                System.out.println(singleAnswer.getSubmitType());
            }
        }
        System.out.println("----------------------------");
    }
    
    //searchByconditionAndPage
    @Test
    public void test10(){
        
        List<SingleAnswer> list = new ArrayList<SingleAnswer>();
        ConditionGroup cond = new ConditionGroup();
        OrderGroup order = new OrderGroup();
        list = answerService.selectByConditionAndPage(1, 10, cond, order);
        
        System.out.println("----------------------------");
        if(list != null && !list.isEmpty()){
            for (SingleAnswer singleAnswer : list)
            {
                System.out.println(singleAnswer.getSubmitType());
            }
        }
        System.out.println("----------------------------");
    }
    
    //删除
    @Test
    public void test11(){
        String id = "3";
        int i = answerService.delete(id);
        System.out.println("----------------------------");
        System.out.println(i);
        System.out.println("----------------------------");
    }
}
