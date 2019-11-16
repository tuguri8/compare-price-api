package com.gachon.price.datatool.courait;

import com.gachon.price.datatool.naver.NaverClient;
import com.gachon.price.datatool.naver.NaverShoppingResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouraitClientTest {

    @Autowired
    private CouraitClient couraitClient;

    @Autowired
    private NaverClient naverClient;

    @Test
    public void test() {
        CouraitResponse getAllPurchaseResponse = couraitClient.getAllPurchaseList();
        System.out.println(getAllPurchaseResponse.getCategory_list().get(0).getItem_name());
    }

    @Test
    public void test2() {
        NaverShoppingResponse naverShoppingResponse = naverClient.getItemPrice("VOzzWsmirqxzdNiFVmn5", "iR9sZbni2S", "롬앤 제로 벨벳 틴트");
        System.out.println(naverShoppingResponse.getItems().get(0).getLink());
    }

}