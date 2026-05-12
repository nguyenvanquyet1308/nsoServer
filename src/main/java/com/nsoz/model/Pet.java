package com.nsoz.model;

import com.mongodb.client.MongoCollection;
import com.nsoz.ability.AbilityCustom;
import com.nsoz.ability.AbilityFromEquip;
import com.nsoz.convert.Converter;
import com.nsoz.db.jdbc.DbManager;
import com.nsoz.effect.Effect;
import com.nsoz.effect.EffectManager;
import com.nsoz.event.eventpoint.EventPoint;
import com.nsoz.fashion.FashionCustom;
import com.nsoz.fashion.FashionFromEquip;
import com.nsoz.item.*;
import com.nsoz.lib.ParseData;
import com.nsoz.map.world.World;
import com.nsoz.map.zones.Zone;
import com.nsoz.mob.Mob;
import com.nsoz.network.Controller;
import com.nsoz.network.NoService;
import com.nsoz.network.Service;
import com.nsoz.server.GameData;
import com.nsoz.skill.Skill;
import com.nsoz.util.Log;
import com.nsoz.util.NinjaUtils;
import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

public class Pet extends Char{
    public Char human;
    public int damePercent;
    public short selectSkillId;
    public short bodyPet;
    public short legPet;
    public Pet(Char _char, int damePercent,String name, short head, short body, short leg) {
        super(-(190000 + _char.id));
        this.isHuman = false;
        this.isPet = true;
        this.human = _char;
        this.damePercent = damePercent;
        this.name = name;
        this.head = head;
        this.bodyPet = body;
        this.legPet = leg;
    }
    public void setDefault() {
        this.bag = new Item[0];
        this.box = new Item[0];
        this.equipment = new Equip[16];
        this.fashion = new Equip[16];
        this.mount = new Mount[5];
        this.bijuu = new Item[5];
        this.level = 1;
        this.typePk = 0;
        this.classId = 0;
        this.vSkill = new Vector<>();
        this.vSupportSkill = new Vector<>();
        this.vSkillFight = new Vector<>();
        Skill skill = GameData.getInstance().getSkill(this.classId, 0, 0);
        if (skill.template.type == Skill.SKILL_AUTO_USE) {
            this.vSupportSkill.add(skill);
        } else if ((skill.template.type == Skill.SKILL_CLICK_USE_ATTACK
                || skill.template.type == Skill.SKILL_CLICK_LIVE
                || skill.template.type == Skill.SKILL_CLICK_USE_BUFF
                || skill.template.type == Skill.SKILL_CLICK_NPC)
                && (skill.template.maxPoint == 0 || (skill.template.maxPoint > 0 && skill.point > 0))) {

            this.vSkillFight.add(skill);
        }
        this.vSkill.add(skill);
        this.isBot = true;
        Item item = ItemFactory.getInstance().newItem(194);
        Equip equip = Converter.getInstance().toEquip(item);
        equip.isLock = true;
        this.equipment[1] = equip;
    }
    @Override
    public EventPoint getEventPoint() {
        return human.getEventPoint();
    }

    public World findWorld(byte type) {
        if (isPet) {
            return human.findWorld(type);
        }
        return super.findWorld(type);

    }

    @Override
    public boolean isMeCanAttackOtherPlayer(Char cAtt) {
        if (isPet) {
            return human.isMeCanAttackOtherPlayer(cAtt);
        }
        return super.isMeCanAttackOtherPlayer(cAtt);
    }

    @Override
    public boolean isMeCanAttackNpc(Mob cAtt) {
        if (isPet) {
            return human.isMeCanAttackNpc(cAtt);
        }
        return super.isMeCanAttackNpc(cAtt);
    }


    @Override
    public void addMp(int add) {
        if (!isPet) {
            this.mp += add;
        }
    }


    @Override
    public void setAbility() {
        super.setAbility();
        if (this.isPet) {
            this.damage = this.damage * damePercent / 100; // human.dame
            this.damage2 = damage;
            this.damage2 -= damage / 10;
        }
    }

    public void move(short x, short y) {
        this.x = x;
        this.y = y;
        zone.getService().playerMove(this);
    }

    @Override
    public void selectSkill(short templateId) {
        try {
            super.selectSkill(templateId);
        } finally {
            if (selectedSkill != null) {
                if (selectedSkill.template.type == Skill.SKILL_CLICK_USE_ATTACK) {
                    selectSkillId = (short) selectedSkill.template.id;
                }
            }
        }
    }

    public void create() {
        this.isDead = false;
        AbilityCustom abilityCustom = AbilityCustom.builder()
                .hp(2000000)
                .build();
        setAbilityStrategy(abilityCustom);
        setAbility();
        FashionCustom fashionCustom = FashionCustom.builder()
                .head(head)
                .body(bodyPet)
                .leg(legPet)
                .weapon((short) 15)
                .build();
        this.setFashionStrategy(fashionCustom);
        setFashion();
        setAbility();
        this.hp = this.maxHP;
        this.mp = this.maxMP;
        selectSkill(this.selectSkillId);
        setXY(human.x, human.y);

    }


    @Override
    public void close() {
        this.isDead = true;
    }

    @Override
    public Service getService() {
            return NoService.getInstance();
    }

    @Override
    public void updateEveryHalfSecond() {
        super.updateEveryHalfSecond();
        if (!isDead) {
            if (isPet && classId == 6) {
                synchronized (vSkillFight) {
                    for (Skill skill : vSkillFight) {
                        if (!skill.isCooldown() && skill.template.type == Skill.SKILL_CLICK_USE_BUFF) {
                            human.useSkillBuff((byte) (human.x > this.x ? 1 : -1), skill);
                        }
                    }
                }
            }
        }
    }

}
