package com.zhangchangq.project.service;

import com.zhangchangq.project.dao.PromoDoMapper;
import com.zhangchangq.project.dataobject.PromoDo;
import com.zhangchangq.project.service.impl.PromoService;
import com.zhangchangq.project.service.model.PromoModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PromoServiceImpl implements PromoService {

    @Autowired
    private PromoDoMapper promoDoMapper;

    @Override
    public PromoModel getPromoByItemId(Integer itemId) {
        PromoModel promoModel = null;
        try {
            //获取对应商品的秒杀活动信息
            PromoDo promoDo = promoDoMapper.selectByItemId(itemId);

            //dataobject->model
            promoModel = convertFromDataObject(promoDo);
            if (promoModel == null) {
                return null;
            }
            //判断当前时间是否秒杀活动即将开始或正在进行
            if (promoModel.getStartDate().isAfterNow()) {
                promoModel.setStatus(1);
            } else if (promoModel.getEndDate().isBeforeNow()) {
                promoModel.setStatus(3);
            } else {
                promoModel.setStatus(2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return promoModel;
    }

    private PromoModel convertFromDataObject(PromoDo promoDo) {
        if (promoDo == null) {
            return null;
        }
        PromoModel model = new PromoModel();
        BeanUtils.copyProperties(promoDo, model);
        model.setPromoItemPrice(new BigDecimal(promoDo.getPromoItemPrice()));
        model.setStartDate(new DateTime(promoDo.getStartDate()));
        model.setEndDate(new DateTime(promoDo.getEndDate()));
        return model;
    }
}
