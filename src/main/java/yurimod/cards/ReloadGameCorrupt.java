package yurimod.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

import yurimod.actions.CorruptAction;
import yurimod.powers.InsanityPower;
import yurimod.powers.NextTurnInsanityPower;
import yurimod.yuriMod;
import yurimod.patches.AbstractCardEnum;

public class ReloadGameCorrupt
extends CustomCard {
	
/*
 * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
 * 
 * Defend
 * Gain 5 (8) block.
 */
	
	
// TEXT DECLARATION 
	
	public static final String ID = yurimod.yuriMod.makeID("ReloadGameCorrupt");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String IMG = yuriMod.makePath(yuriMod.yuri_RELOAD_CORRUPT);
	
	public static final	String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

// /TEXT DECLARATION/
	

// STAT DECLARATION 	
	
	private static final CardRarity RARITY = CardRarity.SPECIAL;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	public static final CardColor COLOR = AbstractCardEnum.YURI_RED;

	private static final int COST = 0;

	
// /STAT DECLARATION/
	
	public ReloadGameCorrupt() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);
		this.exhaust = true;
	}
	
	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (p.hasPower("InsanityPower")) {
			if (p.getPower("InsanityPower").amount < 5) {
				AbstractDungeon.actionManager
						.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(p, p, "InsanityPower"));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new InsanityPower(p, p, 5), 5));
			}
		} else {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new InsanityPower(p, p, 5), 5));
		}
	}
	
	// Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new ReloadGameCorrupt();
    }
    
    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isInnate = true;
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}