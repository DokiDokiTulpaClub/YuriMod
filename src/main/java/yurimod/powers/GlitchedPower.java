package yurimod.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;

import yurimod.yuriMod;


public class GlitchedPower extends AbstractPower implements HealthBarRenderPower {
    public AbstractCreature source;

    public static final String POWER_ID = yurimod.yuriMod.makeID("GlitchedPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public GlitchedPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        this.name = NAME;
        this.ID = "GlitchedPower";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.DEBUFF;
        this.isTurnBased = false;
        this.img = ImageMaster.loadImage("yuriModResources/images/powers/Glitch.png");
        this.source = source;
        this.canGoNegative = false;


    }

    @Override
    public int getHealthBarAmount() {
        return this.amount;
    }

    @Override
    public Color getColor(){
        return Color.MAGENTA;
    }

    // Reduce damage the same way as strength.
    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (!owner.isPlayer && !yuriMod.BrutalInsanity && AbstractDungeon.player.hasRelic("yuri:GlitchPowder")) {
            return type == DamageInfo.DamageType.NORMAL ? damage - (float) this.amount : damage;
        } else  if (!owner.isPlayer && yuriMod.BrutalInsanity && !AbstractDungeon.player.hasRelic("yuri:GlitchPowder")) {
            return type == DamageInfo.DamageType.NORMAL ? damage - (float) (this.amount * 0.25) : damage;
        } else {
            return type == DamageInfo.DamageType.NORMAL ? damage - (float) (this.amount * 0.5) : damage;
            }
        }

    // Increases damage taken.
    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage + (float) this.amount : damage;
    }

    // Hp loss at turn end
    @Override
    public void atStartOfTurn() {
            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.LoseHPAction(this.owner, this.owner, this.amount));
        }


    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        if (owner.isPlayer) {
            this.description = DESCRIPTIONS[0] + (MathUtils.ceil((float)this.amount / 2)) + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[3] + this.amount + DESCRIPTIONS[4];
        } else if (AbstractDungeon.player.hasRelic("yuri:GlitchPowder") && !yuriMod.BrutalInsanity) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[4];
        } else if (!AbstractDungeon.player.hasRelic("yuri:GlitchPowder") && yuriMod.BrutalInsanity) {
            this.description = DESCRIPTIONS[0] + (MathUtils.ceil((float)this.amount / 4)) + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[4];
        } else {
            this.description = DESCRIPTIONS[0] + (MathUtils.ceil((float)this.amount / 2)) + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[4];
        }
    }
}



