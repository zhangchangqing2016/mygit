package com.zhangchangq.project.service.impl;

import com.zhangchangq.project.dao.ItemDOMapper;
import com.zhangchangq.project.dao.ItemStockDoMapper;
import com.zhangchangq.project.dataobject.ItemDO;
import com.zhangchangq.project.dataobject.ItemStockDo;
import com.zhangchangq.project.error.BusinessException;
import com.zhangchangq.project.error.EmBusinessError;
import com.zhangchangq.project.service.ItemService;
import com.zhangchangq.project.service.model.ItemModel;
import com.zhangchangq.project.validator.ValidationResult;
import com.zhangchangq.project.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ValidatorImpl validator;
    @Autowired
    private ItemDOMapper itemDOMapper;
    @Autowired
    private ItemStockDoMapper stockDoMapper;

    private ItemDO convertItemDOFromItemModel(ItemModel itemModel) throws BusinessException {

        if (itemModel == null) {
            return null;
        }
        ItemDO itemDO = new ItemDO();
        BeanUtils.copyProperties(itemModel, itemDO);
        itemDO.setPrice(itemModel.getPrice().doubleValue());
        return itemDO;
    }

    private ItemStockDo convertItemStockFromItemMode(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemStockDo itemStockDo = new ItemStockDo();
        itemStockDo.setItemId(itemModel.getId());
        itemStockDo.setStock(itemModel.getStock());
        return itemStockDo;
    }

    @Override
    @Transactional
    public ItemModel createItem(ItemModel itemModel) throws BusinessException {
        //校验入参
        ValidationResult result = validator.validate(itemModel);
        if (result.isHasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMMETER_VALIDATION_ERROR, result.getErrMsg());
        }

        //转化itemModel->dataobject
        ItemDO itemDO = this.convertItemDOFromItemModel(itemModel);
        //写入数据库
        itemDOMapper.insertSelective(itemDO);
        itemModel.setId(itemDO.getId());
        ItemStockDo itemStockDo = convertItemStockFromItemMode(itemModel);

        //返回创建完成的对象
        stockDoMapper.insertSelective(itemStockDo);

        return this.getItemById(itemModel.getId());
    }


    @Override
    public List<ItemModel> listItem() {
        List<ItemDO> itemDOList = itemDOMapper.listItem();
        List<ItemModel> itemModelList = itemDOList.stream().map(itemDO -> {
            ItemStockDo stockDo = stockDoMapper.selectByItemId(itemDO.getId());
            ItemModel itemModel = this.convertModelFromDataObject(itemDO, stockDo);
            return itemModel;
        }).collect(Collectors.toList());
        return itemModelList;
    }

    @Override
    public ItemModel getItemById(Integer id) {
        ItemDO itemDO = itemDOMapper.selectByPrimaryKey(id);
        if (itemDO == null) {
            return null;
        }
        //操作获得库存数量
        ItemStockDo itemStockDo = stockDoMapper.selectByItemId(itemDO.getId());
        //将dataobject转化为model
        ItemModel itemModel = convertModelFromDataObject(itemDO, itemStockDo);
        return itemModel;
    }

    @Override
    @Transactional
    public boolean decreaseStock(Integer itemId, Integer amount) throws BusinessException {
        int affectedRow = stockDoMapper.descreaseStock(itemId, amount);
        if (affectedRow > 0) {
            //更新库存成功
            return true;
        }
        return false;
    }

    private ItemModel convertModelFromDataObject(ItemDO itemDO, ItemStockDo itemStockDo) {
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDO, itemModel);
        itemModel.setPrice(new BigDecimal(itemDO.getPrice()));
        itemModel.setStock(itemStockDo.getStock());
        return itemModel;

    }
}
