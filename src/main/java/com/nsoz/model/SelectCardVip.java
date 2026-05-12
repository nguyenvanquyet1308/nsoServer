/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsoz.model;

import com.nsoz.constants.ItemName;
import com.nsoz.constants.TaskName;
import com.nsoz.convert.Converter;
import com.nsoz.item.Item;
import com.nsoz.item.ItemFactory;
import com.nsoz.store.ItemStore;
import com.nsoz.store.StoreManager;
import com.nsoz.util.NinjaUtils;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author 
 */
public class SelectCardVip extends AbsSelectCard {

    private static final SelectCardVip instance = new SelectCardVip();

    public static SelectCardVip getInstance() {
        return instance;
    }
    
    public static final long EXPIRE_3_DAY = 3 * 24 * 60 * 60 * 1000;
    public static final long EXPIRE_7_DAY = 7 * 24 * 60 * 60 * 1000;
    public static final long EXPIRE_15_DAY = 15 * 24 * 60 * 60 * 1000;
    public static final long EXPIRE_30_DAY = 30 * 24 * 60 * 60 * 1000;
    public static final long EXPIRE_60_DAY = 60 * 24 * 60 * 60 * 1000;
    public static final long EXPIRE_90_DAY = 90 * 24 * 60 * 60 * 1000;

    @Override
    protected void init() {
        add(Card.builder().id(ItemName.MAT_NA_SUPER_BROLY).rate(0.1).expire(EXPIRE_7_DAY).build());
        add(Card.builder().id(ItemName.MAT_NA_ONNA_BUGEISHA).rate(0.1).expire(EXPIRE_7_DAY).build());
        add(Card.builder().id(ItemName.SACH_VO_CONG_KIEM_THUAT).rate(0.1).build());
        add(Card.builder().id(ItemName.SACH_VO_CONG_DAO_THUAT).rate(0.1).build());
        add(Card.builder().id(ItemName.SACH_VO_CONG_KUNAI_THUAT).rate(0.1).build());
        add(Card.builder().id(ItemName.SACH_VO_CONG_TIEU_THUAT).rate(0.1).build());
        add(Card.builder().id(ItemName.SACH_VO_CONG_QUAT_THUAT).rate(0.1).build());
        add(Card.builder().id(ItemName.SACH_VO_CONG_DAO_THUAT).rate(0.1).build());
        add(Card.builder().id(ItemName.HAKAIRO_YOROI).rate(0.005).build());
        add(Card.builder().id(ItemName.HAKAIRO_YOROI).rate(0.2).expire(EXPIRE_3_DAY).build());
        add(Card.builder().id(ItemName.MAT_NA_VO_DIEN).rate(0.005).build());
        add(Card.builder().id(ItemName.MAT_NA_VO_DIEN).rate(0.2).expire(EXPIRE_7_DAY).build());
        add(Card.builder().id(ItemName.MAT_NA_ONI).rate(0.2).expire(EXPIRE_3_DAY).build());
        add(Card.builder().id(ItemName.MAT_NA_KUMA).rate(0.2).expire(EXPIRE_7_DAY).build());
        add(Card.builder().id(ItemName.MAT_NA_INU).rate(0.2).expire(EXPIRE_3_DAY).build());
        add(Card.builder().id(ItemName.GAY_MAT_TRANG).rate(0.2).expire(EXPIRE_3_DAY).build());
        add(Card.builder().id(ItemName.GAY_TRAI_TIM).rate(0.2).expire(EXPIRE_3_DAY).build());
        add(Card.builder().id(ItemName.RUONG_HAC_AM).rate(1).build());
        add(Card.builder().id(ItemName.KHOA_HAC_AM).rate(1).build());
        add(Card.builder().id(ItemName.AO_NGU_THAN).rate(0.005).build());
        add(Card.builder().id(ItemName.AO_NGU_THAN).rate(0.3).expire(EXPIRE_7_DAY).build());
        add(Card.builder().id(ItemName.AO_NGU_THAN).rate(0.4).expire(EXPIRE_3_DAY).build());
        add(Card.builder().id(ItemName.AO_TAN_THOI).rate(0.005).build());
        add(Card.builder().id(ItemName.AO_TAN_THOI).rate(0.3).expire(EXPIRE_7_DAY).build());
        add(Card.builder().id(ItemName.AO_TAN_THOI).rate(0.4).expire(EXPIRE_3_DAY).build());
        add(Card.builder().id(ItemName.PET_01).rate(0.2).expire(EXPIRE_7_DAY).build());
        add(Card.builder().id(ItemName.PET_02).rate(0.2).expire(EXPIRE_7_DAY).build());
        add(Card.builder().id(ItemName.PET_03).rate(0.2).expire(EXPIRE_7_DAY).build());
        add(Card.builder().id(ItemName.PET_04).rate(0.2).expire(EXPIRE_7_DAY).build());
        add(Card.builder().id(ItemName.PET_LAN_SU_VU).rate(0.2).expire(EXPIRE_7_DAY).build());
        add(Card.builder().id(ItemName.PET_LAN_SU_VU).rate(0.2).expire(EXPIRE_3_DAY).build());
        add(Card.builder().id(ItemName.RUONG_BACH_NGAN).rate(0.4).build());
        add(Card.builder().id(ItemName.BAT_BAO).rate(0.5).build());
        add(Card.builder().id(ItemName.LINH_CHI_NGAN_NAM).rate(0.5).build());
        add(Card.builder().id(ItemName.LINH_CHI_VAN_NAM).rate(0.5).build());
        add(Card.builder().id(ItemName.YEN).rate(3).quantity(50000).build());
        add(Card.builder().id(ItemName.YEN).rate(3).quantity(100000).build());
        add(Card.builder().id(ItemName.YEN).rate(3).quantity(200000).build());
        add(Card.builder().id(ItemName.XE_MAY).rate(0.5).build());
        add(Card.builder().id(ItemName.HARLEY_DAVIDSON).rate(0.5).build());
        add(Card.builder().id(ItemName.HAJIRO).rate(0.4).expire(EXPIRE_3_DAY).build());
        add(Card.builder().id(ItemName.SHIRAIJI).rate(0.4).expire(EXPIRE_3_DAY).build());
        add(Card.builder().id(ItemName.TRUNG_VI_THU).rate(0.7).build());
        add(Card.builder().id(ItemName.CHUYEN_TINH_THACH).rate(0.5).build());
        add(Card.builder().id(ItemName.RUONG_HUYEN_BI).rate(0.1).build());
        add(Card.builder().id(ItemName.GIAY_CHUNG_NHAN).rate(1).build());
        add(Card.builder().id(ItemName.SACH_VO_CONG_IKKAKUJUU).rate(1).build());
        add(Card.builder().id(ItemName.SACH_VO_CONG_HIBASHIRI).rate(1).build());
        add(Card.builder().id(ItemName.SACH_VO_CONG_SAIHYOKEN).rate(1).build());
        add(Card.builder().id(ItemName.SACH_VO_CONG_AISU_MEIKU).rate(1).build());
        add(Card.builder().id(ItemName.SACH_VO_CONG_KAMINARI).rate(1).build());
        add(Card.builder().id(ItemName.SACH_VO_CONG_KOKAZE).rate(1).build());
        add(Card.builder().id(ItemName.TUI_VAI_CAP_5).rate(1).build());
        add(Card.builder().id(ItemName.SACH_VO_CONG_KAGE_BUNSHIN).rate(2).build());
        add(Card.builder().id(ItemName.BAO_HIEM_NHU_Y).rate(0.5).build());
        add(Card.builder().id(ItemName.SASHIMI_CAO_CAP).rate(1).build());
        add(Card.builder().id(ItemName.HOAN_COT_CHI_CHU_TRUNG_CAP).rate(3).build());
        add(Card.builder().id(ItemName.HOA_TUYET).rate(3).build());
        add(Card.builder().id(ItemName.NHAM_THACH_).rate(3).build());
        add(Card.builder().id(ItemName.PHA_LE).rate(3).build());
    }

    @Override
    protected Card reward(@NotNull Char p, Card card) {
        int itemID = card.getId();
        int quantity = card.getQuantity();
        if (itemID == ItemName.YEN) {
            p.addCoin(quantity);
            p.serverMessage("Bạn nhận được " + NinjaUtils.getCurrency(quantity) + " Xu");
        } else {
            Item item = ItemFactory.getInstance().newItem(itemID);
            long expire = card.getExpire();
            if (expire == -1) {
                item.expire = -1;
            } else {
                item.expire = System.currentTimeMillis() + expire;
            }
//            if (NinjaUtils.nextInt(2000) == 0 || p.user.isAdmin()) {
//                int itemLevel = p.level / 10 * 10;
//                if (itemLevel < 10) {
//                    itemLevel = 10;
//                }
//                if (itemLevel > 80) {
//                    itemLevel = 80;
//                }
//                List<ItemStore> list = StoreManager.getInstance().getListEquipmentWithLevelRange(itemLevel, itemLevel + 9);
//                if (!list.isEmpty()) {
//                    int rd = NinjaUtils.nextInt(list.size());
//                    ItemStore itemStore = list.get(rd);
//                    if (itemStore != null) {
//                        itemID = itemStore.getItemID();
//                        item = Converter.getInstance().toItem(itemStore, Converter.MAX_OPTION);
//                        card = Card.builder().id(itemID).build();
//                    }
//                }
//            }
            p.addItemToBag(item);
        }
        return card;
    }

    @Override
    protected boolean isCanSelect(Char p) {
        int index = p.getIndexItemByIdInBag(ItemName.VE_VIP);
        if (index == -1 || p.bag[index] == null || !p.bag[index].has()) {
            p.serverDialog("Bạn Cần Phiếu Víp Để Tham Gia !");
            return false;
        }
        if (p.getSlotNull() == 0) {
            p.serverDialog("Hành Trang Con Không đủ chỗ trống.");
            return false;
        }
        return true;
    }

    @Override
    protected void selecctCardSuccessful(@NotNull Char p) {
        int index = p.getIndexItemByIdInBag(ItemName.VE_VIP);
        p.removeItem(index, 1, true);
        if (p.taskId == TaskName.NV_THU_TAI_MAY_MAN) {
            if (p.taskMain != null && p.taskMain.index == 3) {
                p.updateTaskCount(1);
            }
        }
    }

}
