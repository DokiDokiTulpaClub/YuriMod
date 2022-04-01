package yurimod.relics;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.OnReceivePowerRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import yurimod.powers.GlitchedPower;
import yurimod.powers.InsanityPower;
import yurimod.yuriMod;

import java.util.Iterator;

public class PortableGlitches extends CustomRelic
{
	// ID, images, stats.
	public static final String ID = yurimod.yuriMod.makeID("PortableGlitches");
	public static final String IMG = yuriMod.makePath(yuriMod.yuri_GLITCH_BAG);
	public static final String OUTLINE = yuriMod.makePath(yuriMod.yuri_GLITCH_BAG_OUTLINE);

	public PortableGlitches()
    {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.RARE, LandingSound.FLAT);
    }

    // Description
	@Override
	public String getUpdatedDescription() {
            return DESCRIPTIONS[0];
	}

	//
    @Override
    public void atBattleStart() {
        this.flash();
        Iterator var1 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        while(var1.hasNext()) {
            AbstractMonster mo = (AbstractMonster)var1.next();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(mo, this));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player, new GlitchedPower(mo, AbstractDungeon.player, 1), 1, true));
        }

    }


    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy()
    {
        return new PortableGlitches();
    }
}
