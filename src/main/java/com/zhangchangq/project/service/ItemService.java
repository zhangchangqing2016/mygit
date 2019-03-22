package com.zhangchangq.project.service;

import com.zhangchangq.project.error.BusinessException;
import com.zhangchangq.project.service.model.ItemModel;

import java.util.List;

public interface ItemService {

    //创建商品
     ItemModel createItem(ItemModel itemModel) throws BusinessException;

    //商品列表浏览
    List<ItemModel> listItem();

    //商品详情浏览
    ItemModel getItemById(Integer id);


}
