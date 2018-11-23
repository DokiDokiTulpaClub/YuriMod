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

public class GlitchPowder extends CustomRelic
{
	// ID, images, stats.
	public static final String ID = yurimod.yuriMod.makeID("GlitchPowder");
	public static final String IMG = yuriMod.makePath(yuriMod.yuri_GLITCH_POWDER);
	public static final String OUTLINE = yuriMod.makePath(yuriMod.yuri_GLITCH_POWDER_OUTLINE);

	public GlitchPowder()
    {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.RARE, LandingSound.MAGICAL);
    }

    // Description
	@Override
	public String getUpdatedDescription() {
            return DESCRIPTIONS[0];
	}



    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy()
    {
        return new GlitchPowder();
    }
}
