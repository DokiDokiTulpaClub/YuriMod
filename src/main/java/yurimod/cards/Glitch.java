package yurimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

import yurimod.powers.GlitchedPower;
import yurimod.yuriMod;
import yurimod.patches.AbstractCardEnum;

public class Glitch
extends CustomCard {
	
/*
 * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
 * 
 * Defend
 * Gain 5 (8) block.
 */
	
	
// TEXT DECLARATION 
	
	public static final String ID = yurimod.yuriMod.makeID("Glitch");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String IMG = yuriMod.makePath(yuriMod.yuri_GLITCH);
	
	public static final	String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

// /TEXT DECLARATION/
	

// STAT DECLARATION 	
	
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.STATUS;
	public static final CardColor COLOR = CardColor.COLORLESS;

	private static final int COST = 0;

	
// /STAT DECLARATION/
	
	public Glitch() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);
		this.exhaust = true;
	}
	
	// Actions the card should do.
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m){
	    return true;
    }

    @Override
    public void triggerWhenDrawn(){
	 if (AbstractDungeon.player.hasPower("Evolve") && !AbstractDungeon.player.hasPower("No Draw"))   {
	     AbstractDungeon.player.getPower("Evolve").flash();
	     AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, AbstractDungeon.player.getPower("Evolve").amount));
     }
    }

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager
				.addToBottom(new ApplyPowerAction(p, p, new GlitchedPower(p, p, 1), 1));
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
        if (p.hasRelic("Medical Kit")) {
            this.useMedicalKit(p);
        }
	}
	
	// Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new Glitch();
    }
    
    //Upgraded stats.
    @Override
    public void upgrade() {
    }
}