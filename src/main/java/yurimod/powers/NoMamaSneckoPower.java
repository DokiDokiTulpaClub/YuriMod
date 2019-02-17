package yurimod.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;

import yurimod.yuriMod;


public class NoMamaSneckoPower extends AbstractPower implements InvisiblePower {
    public AbstractCreature source;

    public static final String POWER_ID = yurimod.yuriMod.makeID("NoMamaSneckoPower");

    public NoMamaSneckoPower(final AbstractCreature owner, final int amount) {
        this.name = "No.";
        this.ID = "NoMamaSneckoPower";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.img = ImageMaster.loadImage("yuriModResources/images/powers/DragonForm.png");
        this.source = source;
        this.canGoNegative = false;


    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.ID.equals("Strength") && target.id.equals("theJungle:MamaSnecko")) {
            AbstractDungeon.actionManager
                    .addToBottom(new ReducePowerAction(this.owner, this.owner, "Strength", 1));

        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        super.atEndOfTurn(isPlayer);
        if (this.owner.hasPower("Strength")){
            if (this.owner.getPower("Strength").amount > 0){
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner,this.owner,"Strength",2));
            }
        }

    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        this.description = "No.";
    }
}


