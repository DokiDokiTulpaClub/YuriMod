package yurimod.powers;

import com.badlogic.gdx.math.MathUtils;
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


public class GlitchedPower extends AbstractPower {
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

    // Reduce damage the same way as strength.
    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage - (float) (this.amount * 0.5) : damage;
    }

    // Increases damage taken.
    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage + (float) this.amount : damage;
    }

    // Hp loss at turn end
    @Override
    public void atEndOfTurn(final boolean isPlayer) {
        if (!this.owner.isPlayer && AbstractDungeon.player.hasRelic("yuri:GlitchPowder"))
            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.LoseHPAction(this.owner, this.owner, this.amount * 3));
        else {
            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.LoseHPAction(this.owner, this.owner, this.amount));
        }
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        if (!this.owner.isPlayer && AbstractDungeon.player.hasRelic("yuri:GlitchPowder")) {
            this.description = DESCRIPTIONS[0] + (this.amount * 0.5) + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2] + (this.amount * 3) + DESCRIPTIONS[3];
        } else {
            this.description = DESCRIPTIONS[0] + (this.amount * 0.5) + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3];
        }
    }
}



