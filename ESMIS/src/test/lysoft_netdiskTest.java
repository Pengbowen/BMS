package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lanyuan.assembly.netdisk.ServiceNetdisk;
import com.lanyuan.assembly.netdisk.SingleNetdisk;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring.xml", "classpath:config/spring-mybatis.xml" })
public class lysoft_netdiskTest
{   
    
    @Autowired
    private ServiceNetdisk netdiskService;
   
    //新增文件，文件夹
    @Test
    public void testAdd() {
    	SingleNetdisk single = new SingleNetdisk();
        single.setParentobjectNo("/D");
        single.setKeyWords("文件");
        single.setChirldItemCount(1);
        single.setexplains("这个是文件");
        single.setObjectSuffix("");
        single.setObjectSavePath("/D/JAVA/");
        single.setRemark("备注信息");
        single.setObjectName("文件01");
        single.setObjectType(1);
        netdiskService.addParentObject(single);
    }
    //修改文件，文件夹
    @Test
    public void testUpdate() {
    	SingleNetdisk single = new SingleNetdisk();
    	single.setObjectName("文件夹001");
    	single.setObjectNo("F160825135334");
    	netdiskService.modifyParentObject(single);
    }
    //删除文件，文件夹
     @Test
   public void testDelete() {
    	String objectNo = "D160825833276";
    	netdiskService.deleteById(objectNo);
    }
    //根据对象编号查询文件、文件夹
    @Test
    public void testSelectByObjectNo(){
    	SingleNetdisk singleNetdisk = netdiskService.selectParentObjectByNo("F160825135334");
    	System.out.println("查询到的对象名称为："+singleNetdisk.getRemark());
    }
    /*//获取总数
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
        
        List<SingleBlogrollToAll> list = new ArrayList<SingleBlogrollToAll>();
        ConditionGroup cond = new ConditionGroup();
        cond.addWithAnd(new ConditionNormal(ServiceBlogroll.MappingList.id.name(),"1"));
        OrderGroup order = new OrderGroup();
        order.Add(ServiceBlogroll.MappingList.id.name(), true);
        list = blogrollService.selectByCondition(null, order);
        
        System.out.println("----------------------------");
        if(list != null && !list.isEmpty()){
            System.out.println(list.size());
            for (SingleBlogrollToAll singleBlogrollToAll : list)
            {
                System.out.println(singleBlogrollToAll.getPictureUrl());
            }
        }
        System.out.println("----------------------------");
    }
    
  //searchByconditionAndPage
    @Test
    public void test6(){
        
        List<SingleBlogrollToAll> list = new ArrayList<SingleBlogrollToAll>();
        ConditionGroup cond = new ConditionGroup();
        cond.addWithAnd(new ConditionNormal(ServiceBlogroll.MappingList.id.name(),"1"));
        OrderGroup order = new OrderGroup();
        order.Add(ServiceBlogroll.MappingList.id.name(), true);
        list = blogrollService.selectByConditionAndPage(1, 10, cond, order);
        
        System.out.println("----------------------------");
        if(list != null && !list.isEmpty()){
            for (SingleBlogrollToAll singleBlogrollToAll : list)
            {
                System.out.println(singleBlogrollToAll.toString());
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
    }*/
}
