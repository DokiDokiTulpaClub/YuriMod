package yurimod.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.relics.Omamori;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import yurimod.cards.MarkovCurse;
import yurimod.powers.GlitchedPower;
import yurimod.yuriMod;

public class PortraitOfMarkov extends CustomRelic {
    // ID, images, stats.
    public static final String ID = yurimod.yuriMod.makeID("PortraitOfMarkov");
    public static final String IMG = yuriMod.makePath(yuriMod.yuri_MARKOV);
    public static final String OUTLINE = yuriMod.makePath(yuriMod.yuri_MARKOV_OUTLINE);

    public PortraitOfMarkov() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.BOSS, LandingSound.FLAT);
    }

    // Flash at the start of Battle.
    @Override
    public void atPreBattle(){
        this.flash();
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new GlitchedPower(AbstractDungeon.player, AbstractDungeon.player, 1)));
    }

    // Gain 1 energy on equip.
    @Override
    public void onEquip() {
        if (AbstractDungeon.player.hasRelic("Omamori") && AbstractDungeon.player.getRelic("Omamori").counter != 0) {
            ((Omamori)AbstractDungeon.player.getRelic("Omamori")).use();
            ((Omamori)AbstractDungeon.player.getRelic("Omamori")).use();
        }
        CardCrawlGame.sound.play("NECRONOMICON");
        AbstractDungeon.player.energy.energyMaster += 1;
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new MarkovCurse(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
    }

    //
    @Override
    public void onMasterDeckChange() {
    boolean curseless = true;
	for(AbstractCard c :AbstractDungeon.player.masterDeck.group) {
        if (c.cardID.equals(MarkovCurse.ID)) {
            curseless = false;
            break;
        }
    }
            if (curseless) {
                if (AbstractDungeon.player.hasRelic("Omamori") && AbstractDungeon.player.getRelic("Omamori").counter != 0) {
                    ((Omamori)AbstractDungeon.player.getRelic("Omamori")).use();
                    ((Omamori)AbstractDungeon.player.getRelic("Omamori")).use();
                }
                CardCrawlGame.sound.play("NECRONOMICON");
                AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new MarkovCurse(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new MarkovCurse(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
        }
}
    
    // Lose 1 energy on unequip.
    @Override
    public void onUnequip()
    {
        AbstractDungeon.player.energy.energyMaster -= 1;
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
        return new PortraitOfMarkov();
    }
}
