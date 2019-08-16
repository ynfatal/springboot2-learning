package com.fatal.controller;

import com.fatal.dto.ShopCartDTO;
import com.fatal.dto.ShopCartMainDTO;
import com.fatal.service.IShopCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
                                          @NotNull(message = "goodsId不能为空") Long goodsId,
                                          @NotNull(message = "increment不能为空") Long increment) {
        shopCartService.increment(userId, goodsId, increment);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/decrement")
    public ResponseEntity<Void> decrement(@NotNull(message = "userId不能为空") Long userId,
                          @NotNull(message = "goodsId不能为空") Long goodsId) {
        shopCartService.remove(userId, goodsId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/shopCarts")
    public ResponseEntity<List<ShopCartDTO>> shopCarts(@NotNull(message = "userId不能为空") Long userId,
                                       @NotEmpty(message = "goodsIds不能为空") Long... goodsIds) {
        return ResponseEntity.ok(shopCartService.shopCarts(userId, Arrays.asList(goodsIds)));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@NotNull(message = "userId不能为空") Long userId,
                       @NotEmpty(message = "goodsIds不能为空") Long... goodsIds) {
        shopCartService.delete(userId, goodsIds);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clear(@NotNull(message = "userId不能为空") Long userId) {
        shopCartService.clear(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ShopCartMainDTO>> into(@NotNull(message = "userId不能为空") Long userId) {
        return ResponseEntity.ok(shopCartService.into(userId));
    }

}
