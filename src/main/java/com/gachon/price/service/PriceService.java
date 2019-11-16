package com.gachon.price.service;

import com.gachon.price.datatool.courait.CouraitClient;
import com.gachon.price.datatool.courait.CouraitResponse;
import com.gachon.price.datatool.naver.NaverClient;
import com.gachon.price.datatool.naver.NaverShoppingResponse;
import com.gachon.price.repository.Price;
import com.gachon.price.repository.PriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceService {

    private static final Logger log = LoggerFactory.getLogger(PriceService.class);

    private final String ID = "VOzzWsmirqxzdNiFVmn5";
    private final String SECRET = "iR9sZbni2S";

    private final NaverClient naverClient;
    private final CouraitClient couraitClient;
    private final PriceRepository priceRepository;

    public PriceService(NaverClient naverClient, CouraitClient couraitClient, PriceRepository priceRepository) {
        this.naverClient = naverClient;
        this.couraitClient = couraitClient;
        this.priceRepository = priceRepository;
    }

    @Scheduled(cron = "45 15 2 * * *")
    @CacheEvict(value = "priceList", allEntries = true)
    public void getPriceBatch() {
        CouraitResponse getAllPurchaseResponse = couraitClient.getAllPurchaseList();
        List<Price> priceList = getAllPurchaseResponse
            .getCategory_list()
            .stream()
            .map(this::getNaverPrice)
            .collect(Collectors.toList());
        priceRepository.saveAll(priceList);
        log.info(priceList.size() + "개 정보 저장 완료");
    }

    private Price getNaverPrice(CouraitResponse.PurchaseInfo purchaseInfo) {
        log.info(purchaseInfo.getItem_name());
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        NaverShoppingResponse naverShoppingResponse = naverClient.getItemPrice(ID, SECRET, purchaseInfo.getItem_name());
        NaverShoppingResponse.Item item = naverShoppingResponse.getItems().get(0);
        Price price = new Price();
        price.setTitle(item.getTitle().replaceAll("<b>", "").replaceAll("</b>", ""));
        price.setHprice(item.getHprice());
        price.setImage(item.getImage());
        price.setLink(item.getLink());
        price.setLprice(item.getLprice());
        price.setMallName(item.getMallName());
        price.setProductId(item.getProductId());
        price.setProductType(item.getProductType());
        price.setCategory(purchaseInfo.getCategory());
        price.setOriginalName(purchaseInfo.getItem_name());
        return price;
    }

    @Cacheable(value = "priceList", key = "#email")
    public List<PriceCompareResponse> getPriceCompare(String email) {
        CouraitResponse couraitResponse = couraitClient.getUserPurchaseList(email);
        return couraitResponse.getCategory_list()
                              .stream()
                              .map(this::transform)
                              .collect(Collectors.toList());
    }

    private PriceCompareResponse transform(CouraitResponse.PurchaseInfo purchaseInfo) {
        Price price = priceRepository.findTopByOriginalNameOrderByIdDesc(purchaseInfo.getItem_name());
        PriceCompareResponse priceCompareResponse = new PriceCompareResponse();
        priceCompareResponse.setOriginal_name(price.getOriginalName());
        priceCompareResponse.setCategory(price.getCategory());
        priceCompareResponse.setImage(price.getImage());
        priceCompareResponse.setLink(price.getLink());
        priceCompareResponse.setLprice(price.getLprice());
        priceCompareResponse.setOriginal_price(String.valueOf(purchaseInfo.getPrice()));
        priceCompareResponse.setTitle(price.getTitle());
        return priceCompareResponse;
    }

}
