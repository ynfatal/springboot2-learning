package com.fatal.controller;

import com.fatal.dto.ShopCartDTO;
import com.fatal.dto.ShopCartMainDTO;
import com.fatal.service.IShopCartService;
import com.fatal.vo.ShopCartMainVO;
import com.fatal.vo.ShopCartVO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;

/**
 * @author Fatal
 * @date 2019/8/16 0016 12:54
 */
@Validated
@RestController
@RequestMapping("/shopCart")
public class ShopCartController {

    private IShopCartService shopCartService;

    public ShopCartController(IShopCartService shopCartService) {
        this.shopCartService = shopCartService;
    }

    @PostMapping("/increment")
    public ResponseEntity<Void> increment(@NotNull(message = "userId不能为空") Long userId,
                                          @NotNull(message = "skuId不能为空") Long skuId,
                                          @NotNull(message = "increment不能为空")
                                          @Min(value = 1, message = "increment不能小于{value}") Long increment) {
        shopCartService.increment(userId, skuId, increment);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/decrement")
    public ResponseEntity<Void> decrement(@NotNull(message = "userId不能为空") Long userId,
                          @NotNull(message = "skuId不能为空") Long skuId) {
        shopCartService.removeOne(userId, skuId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/shopCarts")
    public ResponseEntity<List<ShopCartVO>> shopCarts(@NotNull(message = "userId不能为空") Long userId,
                                                      @NotEmpty(message = "skuIds不能为空")
                                                      @Size(max = 10, message = "购物车每次下拉最多只刷新{max}个sku") Long... skuIds) {
        List<ShopCartDTO> shopCartDTOs = shopCartService.shopCarts(userId, Arrays.asList(skuIds));
        return ResponseEntity.ok(ShopCartVO.of(shopCartDTOs));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> delete(@NotNull(message = "userId不能为空") Long userId,
                       @NotEmpty(message = "skuIds不能为空") Long... skuIds) {
        shopCartService.remove(userId, skuIds);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clear(@NotNull(message = "userId不能为空") Long userId) {
        shopCartService.clear(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ShopCartMainVO>> into(@NotNull(message = "userId不能为空") Long userId) {
        List<ShopCartMainDTO> shopCartMainDTOs = shopCartService.into(userId);
        return ResponseEntity.ok(ShopCartMainVO.of(shopCartMainDTOs));
    }

}
