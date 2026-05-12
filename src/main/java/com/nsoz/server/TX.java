package com.nsoz.server;

import com.mysql.cj.util.Util;
import com.nsoz.constants.NpcName;
import com.nsoz.db.jdbc.DbManager;
import com.nsoz.lib.ParseData;
import com.nsoz.model.Char;
import com.nsoz.model.History;
import com.nsoz.model.LichSu;
import com.nsoz.model.SoiCau;
import com.nsoz.util.NinjaUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TX {

    public static Thread thread;
    public static TX instance;
    public static long time = 60000L;
    public static int TAI = 1;
    public static int XIU = 2;
    public int totalTai;
    public int totalXiu;
    public HashMap<Integer, Integer> memberTai;
    public HashMap<Integer, Integer> memberXiu;
    public long timeStart;
    public int typeWin;
    public int baseId;

    public TX() {
        this.baseId = 1;
        this.totalTai = 0;
        this.totalXiu = 0;
        this.memberTai = new HashMap<>();
        this.memberXiu = new HashMap<>();
        this.timeStart = System.currentTimeMillis();
        this.typeWin = 0;
        thread = new Thread(this::processGame);
        thread.start();
    }

    public void Info(Char player) {
        int time = getRemainingTime();
        player.getService().serverDialog(String.format("Thông tin Phiên #%s\n"
                + "Thời Gian : %s giây\n"
                + "Số Người Tham Gia : %s\n"
                + "Tổng Xu Tham Gia Kiếm : %s Xu\n"
                + "Tổng Xu Tham Gia Tiêu : %s Xu\n\n"
                + "Kết Quả Phiên Trước : %s", baseId, time, memberTai.size() + memberXiu.size(), NinjaUtils.getCurrency(totalTai), NinjaUtils.getCurrency(totalXiu), getTypeWin()));
    }

    public static TX gI() {
        if (instance == null) {
            instance = new TX();
            return instance;
        }
        return instance;
    }

    private void processGame() {
        while (true) {
            if (checkTime()) {
                try {
                    calculateResult();
                    result();
                } catch (SQLException e) {
                    System.err.println("ERROR TAI XIU");
                }
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }






    public void joinGame(Char player, int betType, int betAmount) {
        if (player == null) {
            return;
        }
        if (NinjaSchool.isStop) {
            player.getService().serverDialog("Máy chủ đang tiến hành bảo trì không thể đặt cược");
            return;
        }
        if (betAmount <= 0 || betAmount % 100 != 0) {
            player.getService().serverDialog("Giá trị đặt cược phải là bội số của 100");
            return;
        }
        if (betAmount < 1000000 || betAmount > 100000000) {
            player.getService().serverDialog("Min Đặt 1tr Xu Max 100Tr Xu");
            return;
        }
        if (player.coin > 1000000000) {
            player.getService().serverDialog("Số xu trong hành trang quá 1 tỷ vui lòng cất xu để đặt cược.");
            return;
        }
        if (player.coin < betAmount) {
            player.getService().serverDialog("Không đủ xu để đặt cược.");
            return;
        }
        if (betType != 1 && betType != 2) {
            return;
        }
        if (betType == XIU && memberTai.containsKey(player.id)) {
            player.getService().serverDialog("Không thể đặt");
            return;
        } else if (betType == TAI && memberXiu.containsKey(player.id)) {
            player.getService().serverDialog("Không thể đặt");
            return;
        }
        if (memberTai.containsKey(player.id) || memberXiu.containsKey(player.id)) {
            // Người chơi đã đặt cược trước đó
            // Nếu cùng loại cược, cộng tiền cược vào cược hiện tại
            // Nếu khác loại cược, không cho đặt cược thêm
            if (betType == TAI) {
                int existingBetAmount = memberTai.get(player.id);
                memberTai.put(player.id, existingBetAmount + betAmount);
                totalTai += betAmount;
                player.getService().serverDialog("Bạn đã đặt thêm " + NinjaUtils.getCurrency(betAmount) + " Xu vào Kiếm");
            } else {
                int existingBetAmount = memberXiu.get(player.id);
                memberXiu.put(player.id, existingBetAmount + betAmount);
                totalXiu += betAmount;
                player.getService().serverDialog("Bạn đã đặt thêm " + NinjaUtils.getCurrency(betAmount) + " Xu vào Tiêu");
            }
            LichSu.HistoryTX(player.name, player.id, baseId, "Đặt " + NinjaUtils.getCurrency(betAmount) + " Xu Vào TYPE " + betType, player.coin, (player.coin - betAmount), -betAmount);
            player.addCoin(-betAmount);
        } else {
            // Người chơi chưa đặt cược trước đó
            if (betType == TAI) {
                memberTai.put(player.id, betAmount);
                totalTai += betAmount;
                player.getService().serverDialog("Bạn đã tham gia " + NinjaUtils.getCurrency(betAmount) + " Xu thành công vào Kiếm");
            } else if (betType == XIU) {
                memberXiu.put(player.id, betAmount);
                totalXiu += betAmount;
                player.getService().serverDialog("Bạn đã tham gia " + NinjaUtils.getCurrency(betAmount) + " Xu thành công vào Tiêu");
            }
            LichSu.HistoryTX(player.name, player.id, baseId, "Đặt " +  NinjaUtils.getCurrency(betAmount) + " Xu Vào TYPE " + betType, player.coin, (player.coin - betAmount), -betAmount);
            player.addCoin(-betAmount);
        }
    }


    public byte intervention;

    private void calculateResult() {
        int a, b, c, result;
        a = NinjaUtils.nextInt(1, 6);
        b = NinjaUtils.nextInt(1, 6);
        c = NinjaUtils.nextInt(1, 6);
        ArrayList<Integer> list = new ArrayList<>();
        if (intervention == 1) {
            int at = NinjaUtils.nextInt(1, 6);
            int tmp = 9 - at;
            if (tmp > 6) {
                tmp = 6;
            }
            int bt = NinjaUtils.nextInt(1, tmp);
            tmp = 10 - (at + bt);
            if (tmp > 6) {
                tmp = 6;
            }
            int ct = NinjaUtils.nextInt(1, tmp);
            list.add(at);
            list.add(bt);
            list.add(ct);
        }
        if (intervention == 2) {
            int at = NinjaUtils.nextInt(1, 6);
            int tmp = 5 - at;
            if (tmp < 1) {
                tmp = 1;
            }
            int bt = NinjaUtils.nextInt(tmp, 6);
            tmp = 11 - (at + bt);
            if (tmp < 1) {
                tmp = 1;
            }
            int ct = NinjaUtils.nextInt(tmp, 6);
            list.add(at);
            list.add(bt);
            list.add(ct);
        }
        if (intervention != 0) {
            intervention = 0;
            int index = NinjaUtils.nextInt(3);
            a = list.get(index);
            list.remove(index);
            index = NinjaUtils.nextInt(2);
            b = list.get(index);
            list.remove(index);
            c = list.get(0);
            list.clear();
        }
        result = a + b + c;
        if (3 <= result && result <= 10) {
            typeWin = XIU;
        } else if (result > 10) {
            typeWin = TAI;
        }
        GlobalService.getInstance().chat("Thông Báo", String.format("Kết Quả Phiên #%s : %s. Tổng %d + %d + %d = %d ", baseId, getTypeWin(), a, b, c, result));
        SoiCau.soicau.add(new SoiCau(String.format("- Kết quả Phiên #%s : %s. \n- Tổng %d + %d + %d = %d ", baseId, getTypeWin(), a, b, c, result), ""));
        LichSu.KetQuaSoiCau(baseId, getTypeWin());
    }


//    private void calculateResult() {
//        int a, b, c, result;
//        int randomValue = NinjaUtils.nextInt(100);
//        if (totalTai < totalXiu && randomValue > 90) {
//            do {
//                a = NinjaUtils.nextInt(1, 6);
//                b = NinjaUtils.nextInt(1, 6);
//                c = NinjaUtils.nextInt(1, 6);
//                result = a + b + c;
//            } while (result <= 10);
//            typeWin = TAI;
//        } else if (totalXiu > totalTai && randomValue > 90) {
//            do {
//                a = NinjaUtils.nextInt(1, 6);
//                b = NinjaUtils.nextInt(1, 6);
//                c = NinjaUtils.nextInt(1, 6);
//                result = a + b + c;
//            } while (result > 10);
//            typeWin = XIU;
//        } else {
//            a = NinjaUtils.nextInt(1, 6);
//            b = NinjaUtils.nextInt(1, 6);
//            c = NinjaUtils.nextInt(1, 6);
//            result = a + b + c;
//            typeWin = (result > 10) ? TAI : XIU;
//        }
//        SoiCau.soicau.add(new SoiCau(String.format("Kết quả # %s : %s. Tổng %d + %d + %d = %d ", baseId, getTypeWin(), a, b, c, result), "", ""));
//    }

    public void result() throws SQLException {
        switch (this.typeWin) {
            case 1:
                reward(memberTai);
                break;
            case 2:
                reward(memberXiu);
                break;
            default:
                break;
        }
        // Clear game data for the next round
        baseId++;
        totalTai = 0;
        totalXiu = 0;
        memberTai.clear();
        memberXiu.clear();
        timeStart = System.currentTimeMillis();
    }

    public void reward(HashMap<Integer, Integer> list_members) throws SQLException {
        for (Map.Entry<Integer, Integer> entry : list_members.entrySet()) {
            int key = entry.getKey();
            long value = entry.getValue();
            value = value * 19 / 10;
            Char pl = ServerManager.findCharById(key);
            String text = "Bạn nhân được " + NinjaUtils.getCurrency(value) + " xu từ trò chơi Kiếm Tiêu";
            if (pl != null) {
                LichSu.HistoryTX(pl.name, pl.id, baseId, "Thắng " + NinjaUtils.getCurrency(value) +" Xu ", pl.coin, (pl.coin + value), Long.parseLong("+" + value));
                pl.addCoin(value);
                pl.serverDialog("Bạn nhận được " + NinjaUtils.getCurrency(value) + " xu từ trò chơi Kiếm Tiêu");
            } else {
                long coin = 0;
                int gold = 0;
                int yen = 0;
                long coinold = 0;
                try (Connection conn = DbManager.getInstance().getConnection()) {
                    PreparedStatement stmt = conn.prepareStatement(
                            "SELECT `players`.`xu`, `players`.`data`, `players`.`yen`, `users`.`luong` FROM `players` INNER JOIN `users` ON `players`.`user_id` = `users`.`id` WHERE `players`.`id` = ?;",
                            ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    try {
                        History history = new History(key, History.TX);
                        stmt.setInt(1, key);
                        try (ResultSet res = stmt.executeQuery()) {
                            if (res.first()) {
                                coinold =  res.getLong("xu");
                                coin = res.getLong("xu");
                                yen = res.getInt("yen");
                                gold = res.getInt("luong");
                                history.setBefore(coin, gold, yen);
                                coin += value;
                                if (coin > 1500000000) {
                                    coin = 1500000000;
                                }
                                history.setAfter(coin, gold, yen);
                                History.insert(history);
                            }
                        }
                    } finally {
                        stmt.close();
                    }
                    LichSu.HistoryTX("OFFLINE", key, baseId, "Thắng " +  NinjaUtils.getCurrency(value) +" Xu ", coinold, coin, coin);
                    DbManager.getInstance().updateMessage(key, text);
                    DbManager.getInstance().updateCoin(key, (int) coin);
                }
            }
        }
    }

    public String getTypeWin() {
        return (typeWin == TAI) ? "Kiếm" : ((typeWin == XIU) ? "Tiêu" : "Chưa có thông tin");
    }


    public boolean checkTime() {
        return System.currentTimeMillis() - this.timeStart >= TX.time;
    }

    private int getRemainingTime() {
        long currentTime = System.currentTimeMillis();
        long endTime = timeStart + time;
        int remainingSeconds = (int) ((endTime - currentTime) / 1000);
        return Math.max(0, remainingSeconds);
    }
}
