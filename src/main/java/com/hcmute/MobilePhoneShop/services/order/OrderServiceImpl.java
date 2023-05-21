package com.hcmute.MobilePhoneShop.services.order;

import com.hcmute.MobilePhoneShop.dtos.request.RequestCheckoutDTO;
import com.hcmute.MobilePhoneShop.dtos.response.BaseResponse;
import com.hcmute.MobilePhoneShop.entities.Order;
import com.hcmute.MobilePhoneShop.entities.OrderDetail;
import com.hcmute.MobilePhoneShop.entities.Product;
import com.hcmute.MobilePhoneShop.entities.User;
import com.hcmute.MobilePhoneShop.entities.embedded.ProductPrice;
import com.hcmute.MobilePhoneShop.entities.embedded.QuantityOfColor;
import com.hcmute.MobilePhoneShop.exceptions.NotFoundException;
import com.hcmute.MobilePhoneShop.repositories.OrderDetailRepository;
import com.hcmute.MobilePhoneShop.repositories.OrderRepository;
import com.hcmute.MobilePhoneShop.repositories.ProductRepository;
import com.hcmute.MobilePhoneShop.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    OrderRepository orderRepository;

    @Override
    public BaseResponse checkout(RequestCheckoutDTO requestCheckoutDTO) {
        Product product = productRepository.findById(requestCheckoutDTO.getProductId()).orElseThrow(() ->
                new NotFoundException(String.format("Product with id %s not found",requestCheckoutDTO.getProductId())));
        User user = userRepository.findById(requestCheckoutDTO.getUserId()).orElseThrow(() ->
                new NotFoundException(String.format("User with id %s not found",requestCheckoutDTO.getUserId())));

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductName(product.getProductName());
        orderDetail.setQuantity(requestCheckoutDTO.getQuantity());
        orderDetail.setPrice(requestCheckoutDTO.getPrice());
        orderDetail.setColor(requestCheckoutDTO.getColor());
        orderDetail.setStorage(requestCheckoutDTO.getStorage());
        orderDetailRepository.save(orderDetail);

        Order order = new Order();
        order.setPhone(user.getPhoneNumber());
        List<ProductPrice> productPrices = new ArrayList<>();
        double totalPrice = 0;
        for (ProductPrice price : product.getPrice()){
            if(price.getColors().equalsIgnoreCase(requestCheckoutDTO.getColor())
                    && price.getStorage().equalsIgnoreCase(requestCheckoutDTO.getStorage())){
                totalPrice = price.getPrice() * requestCheckoutDTO.getQuantity();
            }
        }
        order.setTotalPrice(totalPrice);
        order.setUserId(user.getId());
        order.setOrderDetails(orderDetail);
        orderRepository.save(order);

        List<QuantityOfColor> quantityOfColors = new ArrayList<>();
        for (QuantityOfColor quantityOfColor: product.getQuantityOfColors()){
            if (quantityOfColor.getColor().equalsIgnoreCase(requestCheckoutDTO.getColor())
                    && quantityOfColor.getStorage().equalsIgnoreCase(requestCheckoutDTO.getStorage())) {
                int quantity = requestCheckoutDTO.getQuantity();
                int quantityOfProduct = quantityOfColor.getQuantity();
                int quantityColor = quantityOfProduct - quantity;
                if (quantityColor == 0)
                    quantityOfColor.setSold_out(true);
                quantityOfColor.setQuantity(quantityColor);
            }
            quantityOfColors.add(quantityOfColor);
        }
        int quantityProduct = product.getQuantity() - requestCheckoutDTO.getQuantity();
        if(quantityProduct == 0){
            product.setDisable(true);
        }
        product.setQuantity(quantityProduct);
        product.setQuantityOfColors(quantityOfColors);

        productRepository.save(product);

        return new BaseResponse(true, "Checkout successful");
    }
}
