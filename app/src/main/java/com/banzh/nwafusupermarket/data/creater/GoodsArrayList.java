package com.banzh.nwafusupermarket.data.creater;

import android.content.Context;
import android.os.Build;

import com.banzh.nwafusupermarket.R;
import com.banzh.nwafusupermarket.data.ListGoodsItem;

import java.util.ArrayList;

public class GoodsArrayList {

    public static void createInitDataGoods(ArrayList<ListGoodsItem> goods, Context mContext){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ListGoodsItem listGoodsItem1 = new ListGoodsItem(mContext.getDrawable(R.drawable.anmuxi),
                    "安慕希", "来源于希腊的“Ambrosial”，更添加了希腊雅典农业大学最新研制的菌种，具有浓郁的口感和更多的蛋白质，比普通酸奶高出蛋白质35%，最长保质期六个月......");
            ListGoodsItem listGoodsItem2 = new ListGoodsItem(mContext.getDrawable(R.drawable.apple),
                    "苹果", "苹果是一种低热量的食物，每100克产生大约60千卡左右的热量。苹果中营养成分可溶性大，容易被人体吸收，故有“活水”之称。它有利于溶解硫元素，使皮肤润滑柔嫩......");
            ListGoodsItem listGoodsItem3 = new ListGoodsItem(mContext.getDrawable(R.drawable.baishikele),
                    "百事可乐", "诞生于19世纪90年代，由美国北卡罗莱纳州药剂师Caleb Bradham制造，以碳酸水、糖、香草、生油、胃蛋白酶（pepsin）及可乐果制成......");
            ListGoodsItem listGoodsItem4 = new ListGoodsItem(mContext.getDrawable(R.drawable.chocolate),
                    "德芙巧克力", "德芙，是世界最大宠物食品和休闲食品制造商美国跨国食品公司玛氏（Mars）公司在中国推出的系列产品，1989年进入中国......");
            ListGoodsItem listGoodsItem5 = new ListGoodsItem(mContext.getDrawable(R.drawable.chunzhen),
                    "纯甄", "蒙牛纯甄 [1-2]  是一款不添加色素、香精和防腐剂的高端酸牛奶产品。其严选优质牧场奶源，进口丹麦菌种发酵（保加利亚乳杆菌、嗜热链球菌），使得纯甄酸牛奶的口味简单纯净，让每一位品尝纯甄的人，回忆起了童年留在唇边的那一抹纯正奶香......");
            ListGoodsItem listGoodsItem6 = new ListGoodsItem(mContext.getDrawable(R.drawable.grape),
                    "葡萄", "葡萄为著名水果，生食或制葡萄干，并酿酒，酿酒后的酒脚可提酒石酸，根和藤药用能止呕、安胎......");
            ListGoodsItem listGoodsItem7 = new ListGoodsItem(mContext.getDrawable(R.drawable.guazi),
                    "瓜子", "葵花子是向日葵的果实，不但可以作为零食，而且还可以作为制作糕点的原料，同时也是重要的榨油原料，是高档健康的油脂来源......");
            ListGoodsItem listGoodsItem8 = new ListGoodsItem(mContext.getDrawable(R.drawable.hongniu),
                    "红牛", "“红牛”（Red Bull)是全球首先推出且被人熟知的的能量饮品之一......");
            ListGoodsItem listGoodsItem9 = new ListGoodsItem(mContext.getDrawable(R.drawable.huawei),
                    "华为手机", "香就完事了......");
            ListGoodsItem listGoodsItem10 = new ListGoodsItem(mContext.getDrawable(R.drawable.iphone),
                    "iphone8", "据说这个更香......");
            ListGoodsItem listGoodsItem11 = new ListGoodsItem(mContext.getDrawable(R.drawable.kekoukele),
                    "可口可乐", "碳水化合物......");
            ListGoodsItem listGoodsItem12 = new ListGoodsItem(mContext.getDrawable(R.drawable.lvjian),
                    "绿箭", "箭牌公司在中国的品牌组合包括“绿箭”（ Doublemint）、“黄箭”(Juicy Fruit)、“白箭”(Wrigley's Spearmint)、“ 箭牌咖啡口香糖 ”(Wrigley's coffee gum)、“益达”(Extra)无糖口香糖、“劲浪”(Cool Air)超凉口香糖 、“大大”（ Ta Ta）泡泡糖、“真知棒” (Pim Pom)棒棒糖、“瑞士糖”（Sugus）软糖和“彩虹”（Skittles）果汁糖等。......");
            ListGoodsItem listGoodsItem13 = new ListGoodsItem(mContext.getDrawable(R.drawable.maojin),
                    "毛巾", "洗脸必备呀......");
            ListGoodsItem listGoodsItem14 = new ListGoodsItem(mContext.getDrawable(R.drawable.nongfushanquan),
                    "农夫山泉", "农夫山泉有点甜......");
            ListGoodsItem listGoodsItem15 = new ListGoodsItem(mContext.getDrawable(R.drawable.shuibei),
                    "水杯", "喝水必备的杯子......");
            ListGoodsItem listGoodsItem16 = new ListGoodsItem(mContext.getDrawable(R.drawable.watermelon),
                    "西瓜", "这玩意吃着很不错......");
            ListGoodsItem listGoodsItem17 = new ListGoodsItem(mContext.getDrawable(R.drawable.xifalu),
                    "洗发露", "不用这玩意怎么洗头......");
            ListGoodsItem listGoodsItem18 = new ListGoodsItem(mContext.getDrawable(R.drawable.xinglixiang),
                    "行李箱", "出门必备......");
            ListGoodsItem listGoodsItem19 = new ListGoodsItem(mContext.getDrawable(R.drawable.xiyiye),
                    "洗衣液", "洗衣服必备......");
            ListGoodsItem listGoodsItem20 = new ListGoodsItem(mContext.getDrawable(R.drawable.xuanmai),
                    "炫迈", "又一个口香糖......");
            ListGoodsItem listGoodsItem21 = new ListGoodsItem(mContext.getDrawable(R.drawable.yikoulian),
                    "怡口莲", "零食......");
            goods.add(listGoodsItem1);
            goods.add(listGoodsItem2);
            goods.add(listGoodsItem3);
            goods.add(listGoodsItem4);
            goods.add(listGoodsItem5);
            goods.add(listGoodsItem6);
            goods.add(listGoodsItem7);
            goods.add(listGoodsItem8);
            goods.add(listGoodsItem9);
            goods.add(listGoodsItem10);
            goods.add(listGoodsItem11);
            goods.add(listGoodsItem12);
            goods.add(listGoodsItem13);
            goods.add(listGoodsItem14);
            goods.add(listGoodsItem15);
            goods.add(listGoodsItem16);
            goods.add(listGoodsItem17);
            goods.add(listGoodsItem18);
            goods.add(listGoodsItem19);
            goods.add(listGoodsItem20);
            goods.add(listGoodsItem21);
        }
    }
}
