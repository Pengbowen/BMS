package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionNormal;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.OrderGroup;
import com.lanyuan.assembly.blogroll.ServiceBlogroll;
import com.lanyuan.assembly.blogroll.SingleBlogroll;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring.xml", "classpath:config/spring-mybatis.xml" })
public class TestBlogroll
{   
    
    @Autowired
    private ServiceBlogroll blogrollService;
    
   
    //新增
    @Test
    public void test1() {
        SingleBlogroll single = new SingleBlogroll();
        single.setLinkeUrl("http//:www.baidu.com");
        single.setLinkName("百度");
        single.setOrderNumber(1);
        single.setPictureUrl("http//:www.photoBaidu.com");
        int i = blogrollService.add(single);
            System.out.println("----------------------------");
            System.out.println("成功次数:"+i);
            System.out.println("----------------------------");
    }
        
    //修改
    @Test
    public void test2() {
        
        SingleBlogroll single = new SingleBlogroll();
        single.setLinkeUrl("http//:www.sogou.com");
        single.setLinkName("搜狗");
        single.setOrderNumber(3);
        single.setPictureUrl("http//:www.photoSougou.com");
        int i = blogrollService.update(single,"2");
        System.out.println("----------------------------");
        System.out.println(i);
        System.out.println("----------------------------");
    }
   
    //获取总数
    @Test
    public void test3() {
        
        int i = blogrollService.getTotalCount(null);
        System.out.println("----------------------------");
        System.out.println(i);
        System.out.println("----------------------------");
    }
   
    //searchById
    @Test
    public void test4(){
        SingleBlogroll single = new SingleBlogroll();
        single = blogrollService.selectById("1");
        System.out.println("----------------------------");
        System.out.println(single.getPictureUrl());
        System.out.println("----------------------------");
    }
    
    //searchBycondition
    @Test
    public void test5(){
        
        List<SingleBlogroll> list = new ArrayList<SingleBlogroll>();
        ConditionGroup cond = new ConditionGroup();
        cond.addWithAnd(new ConditionNormal(ServiceBlogroll.MappingList.id.name(),"1"));
        OrderGroup order = new OrderGroup();
        order.Add(ServiceBlogroll.MappingList.id.name(), true);
        list = blogrollService.selectByCondition(null, order);
        
        System.out.println("----------------------------");
        if(list != null && !list.isEmpty()){
            System.out.println(list.size());
            for (SingleBlogroll singleBlogroll : list)
            {
                System.out.println(singleBlogroll.getPictureUrl());
            }
        }
        System.out.println("----------------------------");
    }
    
  //searchByconditionAndPage
    @Test
    public void test6(){
        
        List<SingleBlogroll> list = new ArrayList<SingleBlogroll>();
        ConditionGroup cond = new ConditionGroup();
        cond.addWithAnd(new ConditionNormal(ServiceBlogroll.MappingList.id.name(),"1"));
        OrderGroup order = new OrderGroup();
        order.Add(ServiceBlogroll.MappingList.id.name(), true);
        list = blogrollService.selectByConditionAndPage(1, 10, cond, order);
        
        System.out.println("----------------------------");
        if(list != null && !list.isEmpty()){
            for (SingleBlogroll singleBlogroll : list)
            {
                System.out.println(singleBlogroll.toString());
            }
        }
        System.out.println("----------------------------");
    }
    
    //删除
    @Test
    public void test7(){
        String id = "3";
        int i = blogrollService.delete(id);
        System.out.println("----------------------------");
        System.out.println(i);
        System.out.println("----------------------------");
    }
}
