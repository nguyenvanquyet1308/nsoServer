/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsoz.fashion;

import com.nsoz.constants.ItemName;
import com.nsoz.item.Equip;
import com.nsoz.item.Item;
import com.nsoz.item.Mount;
import com.nsoz.model.Char;
import com.nsoz.item.ItemTemplate;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class FashionFromEquip implements FashionStrategy {

    public void set(Char owner) {
        Equip eVuKhi = owner.equipment[ItemTemplate.TYPE_VUKHI];
        if (eVuKhi != null) {
            owner.weapon = eVuKhi.template.part;
        } else {
            owner.weapon = 15;
        }
        Equip eAo = owner.equipment[ItemTemplate.TYPE_AO];
        if (eAo != null) {
            owner.body = eAo.template.part;
        } else {
            if (owner.gender == 0) {
                owner.body = 10;
            } else {
                owner.body = 1;
            }
        }
        Equip eQuan = owner.equipment[ItemTemplate.TYPE_QUAN];
        if (eQuan != null) {
            owner.leg = eQuan.template.part;
        } else {
            if (owner.gender == 0) {
                owner.leg = 9;
            } else {
                owner.leg = 0;
            }
        }
        Equip eMatNa = owner.equipment[ItemTemplate.TYPE_MATNA];
        Item maskI = owner.getMask();
        if (eMatNa != null || maskI != null) {
            int id = -1;
            if (eMatNa != null) {
                owner.head = eMatNa.template.part;
                id = eMatNa.id;
            }
            if (maskI != null) {
                owner.head = maskI.template.part;
                id = maskI.id;
                if (id == ItemName.AKATSUKI_NAM  || id == ItemName.AKATSUKI_NU) {
                    owner.head = owner.original_head;
                }
            }
            switch (id) {
                case ItemName.THUY_TINH:
                    owner.body = 186;
                    owner.leg = 187;
                    break;

                case ItemName.SON_TINH:
                    owner.body = 189;
                    owner.leg = 190;
                    break;

                case ItemName.MAT_NA_THANH_GIONG_:
                    owner.body = 206;
                    owner.leg = 207;
                    break;

                case ItemName.MAT_NA_JIRAI_:
                    owner.body = 224;
                    owner.leg = 225;
                    break;

                case ItemName.MAT_NA_JUMITO:
                    owner.body = 227;
                    owner.leg = 228;
                    break;

                case ItemName.TOC_3:
                    owner.body = 230;
                    owner.leg = 231;
                    break;

                case ItemName.TOC_4:
                    owner.body = 233;
                    owner.leg = 234;
                    break;

                case ItemName.TOC_5:
                    owner.body = 236;
                    owner.leg = 237;
                    break;

                case ItemName.TOC_6:
                    owner.body = 239;
                    owner.leg = 240;
                    break;

                case ItemName.TOC_7:
                    owner.body = 242;
                    owner.leg = 243;
                    break;

                case ItemName.TOC_8:
                    owner.body = 245;
                    owner.leg = 246;
                    break;

                case ItemName.MAT_NA_CHUOT:
                    owner.body = 265;
                    owner.leg = 266;
                    break;

                case ItemName.JACK_HOLLOW:
                    owner.body = 259;
                    owner.leg = 260;
                    break;

                case ItemName.SANTA_CLAUS:
                    owner.body = 268;
                    owner.leg = 269;
                    break;

                case ItemName.SUMIMURA_:
                    owner.body = 271;
                    owner.leg = 272;
                    break;

                case ItemName.YUKIMURA_:
                    owner.body = 277;
                    owner.leg = 278;
                    break;

                case ItemName.TON_HANH_GIA:
                    owner.body = 202;
                    owner.leg = 203;
                    owner.weapon = 204;
                    break;
                case ItemName.MAT_NA_MARADA:
                    owner.body = 283;//than
                    owner.leg = 284;//chan
                    break;

                case ItemName.MAT_NA_NARUTO:
                    owner.body = 286;//than
                    owner.leg = 287;//chan
                    break;

                case ItemName.MAT_NA_SAKURA:
                    owner.body = 289;//than
                    owner.leg = 290;//chan
                    break;
                case ItemName.AKATSUKI_NAM:
                    owner.ID_HAIR = 180;
                    owner.ID_BODY = 181;
                    owner.ID_LEG = 182;
                    break;
                case ItemName.AKATSUKI_NU:
                    owner.ID_HAIR = 183;
                    owner.ID_BODY = 184;
                    owner.ID_LEG = 185;
                    break;
                
                case ItemName.CAI_TRANG_SAYDA:
                    owner.body = 307;//than
                    owner.leg = 308;//chan
                    break;
                    
                case ItemName.CAI_TRANG_THIEU_NU_1:
                    owner.body = 310;//than
                    owner.leg = 311;//chan
                    break;
                    
                case ItemName.CAI_TRANG_TAO_QUAN:
                    owner.body = 313;//than
                    owner.leg = 314;//chan
                    break;
                    
                case ItemName.CAI_TRANG_TAO_QUAN_ĐO:
                    owner.body = 316;//than
                    owner.leg = 317;//chan
                    break;
                    
                case ItemName.CAI_TRANG_QUY_LAO_KAME:
                    owner.body = 319;//than
                    owner.leg = 320;//chan
                    break;
                    
                case ItemName.CAI_TRANG_THIEU_NU_2:
                    owner.body = 322;//than
                    owner.leg = 323;//chan
                    break;
                    
                case ItemName.CAI_TRANG_KHI_DAU_CHO:
                    owner.body = 325;//than
                    owner.leg = 326;//chan
                    break;
                    
                case ItemName.CAI_TRANG_NOEL_NEW:
                    owner.body = 328;//than
                    owner.leg = 329;//chan
                    break;
                   
                case ItemName.CAI_TRANG_NOEL_NU_NEW:
                    owner.body = 331;//than
                    owner.leg = 332;//chan
                    break;
                    
                case ItemName.CAI_TRANG:
                    owner.body = 334;//than
                    owner.leg = 335;//chan
                    break;
            }

        } else {
            owner.head = owner.original_head;
        }
        if (owner.fashion[11] != null) {
            owner.ID_MAT_NA = owner.fashion[11].template.fashion;
        } else {
            owner.ID_MAT_NA = -1;
        }
        if (owner.fashion[2] != null) {
            short idFashion = owner.fashion[2].template.fashion;

            if (idFashion > -1) {
                owner.ID_HAIR = idFashion;
                switch (idFashion) {
                    case 37:
                        owner.ID_BODY = 38;
                        owner.ID_LEG = 39;
                        break;

                    case 40:
                        owner.ID_BODY = 41;
                        owner.ID_LEG = 42;
                        break;

                    case 55:
                        owner.ID_BODY = 56;
                        owner.ID_LEG = 57;
                        break;

                    case 58:
                        owner.ID_BODY = 59;
                        owner.ID_LEG = 60;
                        break;

                    case 67:
                        owner.ID_BODY = 68;
                        owner.ID_LEG = 69;
                        break;

                    case 70:
                        owner.ID_BODY = 71;
                        owner.ID_LEG = 72;
                        break;

                    case 132:
                        owner.ID_BODY = 133;
                        break;

                    case 141:
                        owner.ID_BODY = 142;
                        break;

                    case 174:
                        owner.ID_BODY = 175;
                        owner.ID_LEG = 176;
                        break;

                    case 177:
                        owner.ID_BODY = 178;
                        owner.ID_LEG = 179;
                        break;
                    
                    case 230:
                        owner.ID_BODY = 231;
                        owner.ID_LEG = 232;
                        break;
                        
                    case 233:
                        owner.ID_BODY = 234;
                        owner.ID_LEG = 235;
                        break;
                        
                }
            }
        } else if (maskI == null
                || (maskI.id != ItemName.AKATSUKI_NAM && maskI.id != ItemName.AKATSUKI_NU)) {
            owner.ID_HAIR = -1;
            owner.ID_BODY = -1;
            owner.ID_LEG = -1;
        }
        /*Equip eIDTB1_VUKHI = owner.equipment[ItemTemplate.TYPE_VUKHI];
        if (eIDTB1_VUKHI != null) {
            int eTB1 = owner.equipment[ItemTemplate.TYPE_VUKHI].template.id;
            
            switch (eTB1) {
                case 1094:
                    owner.ID_WEA_PONE = 164;
                    break;
                    
                case 1095:
                    owner.ID_WEA_PONE = 167;
                    break;    
            }
        } else {
            owner.ID_WEA_PONE = -1;
        }
        Equip eIDTB1_AO = owner.equipment[ItemTemplate.TYPE_AO];
        if (eIDTB1_AO != null) {
            int eTB1 = owner.equipment[ItemTemplate.TYPE_AO].template.id;
            switch (eTB1) {
                case 1102:
                    owner.ID_HAIR = 132;// đầu
                    owner.ID_BODY = 133;// thân
                    owner.ID_LEG = 134;// chân
                    break;

                case 1103:
                    owner.ID_HAIR = 140;
                    owner.ID_BODY = 141;
                    owner.ID_LEG = 141;
                    break;
            }
        } else {
            owner.ID_HAIR = -1;
            owner.ID_BODY = -1;
            owner.ID_LEG = -1;
        }*/
        Equip eAoChoang = owner.equipment[ItemTemplate.TYPE_AOCHOANG];
        if (eAoChoang
                != null) {
            short idFashion = eAoChoang.template.fashion;
            if (idFashion > -1) {
                owner.ID_PP = idFashion;
                owner.coat = -1;
            } else {
                owner.coat = (short) eAoChoang.id;
                owner.ID_PP = -1;
            }
        } else {
            owner.coat = -1;
            owner.ID_PP = -1;
        }
        if (owner.fashion[ItemTemplate.TYPE_THUNUOI] != null && owner.fashion[ItemTemplate.TYPE_THUNUOI].id
                != 864) {
            owner.ID_NAME = owner.fashion[ItemTemplate.TYPE_THUNUOI].template.fashion;
        } else {
            owner.ID_NAME = -1;
        }
        Equip eBaoTay = owner.equipment[ItemTemplate.TYPE_BAOTAY];
        if (eBaoTay
                != null) {
            owner.glove = (short) eBaoTay.id;
        } else {
            owner.glove = -1;
        }
        if (owner.fashion[1] != null) {
            short idFashion = owner.fashion[1].template.fashion;
            if (idFashion > -1) {
                owner.ID_WEA_PONE = idFashion;
            }
        } else {
            owner.ID_WEA_PONE = -1;
        }
        Mount mount = owner.mount[4];
        if (mount
                != null) {
            short idFashion = mount.template.fashion;
            if (idFashion > -1) {
                owner.ID_HORSE = idFashion;
            }
        } else {
            owner.ID_HORSE = -1;
        }
        ArrayList<Integer> listMax = new ArrayList<>();
        for (Equip equip : owner.equipment) {
            if (equip != null && (equip.template.isTypeClothe() || equip.template.isTypeAdorn()
                    || equip.template.isTypeWeapon())) {
                listMax.add(equip.getMaxUpgradeGem());
            }
        }

        if (listMax.size()
                == 10) {
            listMax.sort((o1, o2) -> o1 - o2);
            int min = listMax.get(0);
            switch (min) {
                case 6:
                case 7:
                    owner.haoQuang = 0;
                    break;
                case 8:
                case 9:
                    owner.haoQuang = 1;
                    break;
                case 10:
                    owner.haoQuang = 2;
                    break;
            }
        } else {
            owner.haoQuang = -1;
        }
        Equip eThuNuoi2 = owner.fashion[ItemTemplate.TYPE_THUNUOI];
        if (eThuNuoi2 != null && eThuNuoi2.id
                == 864) {
            if (owner.classId > 0) {
                short[] honorIds = new short[]{16, 17, 15, 13, 12, 14};
                owner.honor = honorIds[owner.classId - 1];
            }
        } else {
            owner.honor = -1;
        }
    }

}
