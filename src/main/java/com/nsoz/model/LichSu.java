package com.nsoz.model;

import com.nsoz.util.NinjaUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class LichSu {
    public static void HistoryTX(String name, int playerId , int Phien , String noidung, long coinold, long coinnew, long coinchange) {
        try {
            String time = NinjaUtils.milliSecondsToDateString(System.currentTimeMillis(), "dd/MM/yyyy HH:mm:ss");
            String File = "LichSu/TX/LichSuTX.txt";
            FileWriter fw = new FileWriter(File, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("----------Thời Gian : " + time + "--------\n"
                    + "- Name : " + name + " -  ID : " + playerId + " - Phiên #" + Phien + "\n"
                    + "- Nội Dung : " + noidung + "\n"
                    + "- Xu Cũ : " + coinold + " - Xu Mới : " + coinnew + " - Xu Thay Đổi : " + coinchange + "\n");
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void KetQuaSoiCau(int Phien , String KetQua) {
        try {
            String time = NinjaUtils.milliSecondsToDateString(System.currentTimeMillis(), "dd/MM/yyyy HH:mm:ss");
            String File = "LichSu/TX/KetQua.txt";
            FileWriter fw = new FileWriter(File, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("----------Thời Gian : " + time + "--------\n"
                            + "- Phiên #" + Phien + "- Kết Quả : " + KetQua + "\n");
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
