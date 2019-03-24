package com.zhangchangq.project.controller;

import com.zhangchangq.project.controller.viewobject.ItemVo;
import com.zhangchangq.project.error.BusinessException;
import com.zhangchangq.project.response.CommonReturnType;
import com.zhangchangq.project.service.ItemService;
import com.zhangchangq.project.service.model.ItemModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller("item")
@RequestMapping("/item")
public class ItemController extends BaseController {

    @Autowired
    private ItemService itemService;

    //创建商品的controller
    public CommonReturnType createItem(@RequestParam(name = "title") String title,
                                       @RequestParam(name = "description") String description,
                                       @RequestParam(name = "price") BigDecimal price,
                                       @RequestParam(name = "stock") Integer stock,
                                       @RequestParam(name = "imgUrl") String imgUrl) throws BusinessException {

        //封装service请求，用来创建商品
        ItemModel itemModel = new ItemModel();
        itemModel.setTitle(title);
        itemModel.setDescription(description);
        itemModel.setPrice(price);
        itemModel.setStock(stock);
        itemModel.setImageUrl(imgUrl);
        ItemModel model = itemService.createItem(itemModel);
        ItemVo itemVo = convertVoFromModel(model);
        return CommonReturnType.create(itemVo);

    }

    private ItemVo convertVoFromModel(ItemModel model) {

        if (model == null) {
            return null;
        }
        ItemVo itemVo = new ItemVo();
        BeanUtils.copyProperties(model, itemVo);
        return itemVo;
    }
}
