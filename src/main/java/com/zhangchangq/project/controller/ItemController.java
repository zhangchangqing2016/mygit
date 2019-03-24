package com.zhangchangq.project.controller;

import com.zhangchangq.project.controller.viewobject.ItemVo;
import com.zhangchangq.project.error.BusinessException;
import com.zhangchangq.project.response.CommonReturnType;
import com.zhangchangq.project.service.ItemService;
import com.zhangchangq.project.service.model.ItemModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Controller("item")
@RequestMapping("/item")
public class ItemController extends BaseController {

    @Autowired
    private ItemService itemService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String initPage() {

        logger.info("创建商品中----->>>>>>");
        return "createItem";
    }

    //创建商品的controller
    @RequestMapping(value = "/create", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
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
        itemModel.setImgUrl(imgUrl);
        logger.info("创建商品明细:" + itemModel.toString());
        ItemModel model = itemService.createItem(itemModel);
        ItemVo itemVo = convertVoFromModel(model);
        return CommonReturnType.create(itemVo);

    }

    //商品浏览功能
    //创建商品的controller
    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getItem(@RequestParam(name = "id") Integer id) {
        ItemModel itemModel = itemService.getItemById(id);
        ItemVo itemVo = convertVoFromModel(itemModel);
        return CommonReturnType.create(itemVo);
    }

    //

    private ItemVo convertVoFromModel(ItemModel model) {

        if (model == null) {
            return null;
        }
        ItemVo itemVo = new ItemVo();
        BeanUtils.copyProperties(model, itemVo);
        return itemVo;
    }
}
