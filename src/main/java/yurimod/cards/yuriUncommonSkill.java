package yurimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

import basemod.abstracts.CustomCard;

import yurimod.yuriMod;
import yurimod.patches.AbstractCardEnum;

public class yuriUncommonSkill
extends CustomCard {
	
/*
 * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
 * 
 * A better defend
 * Gain 1 plated armor. Affected by Dexterity.
 */
	
	
// TEXT DECLARATION 
	
	public static final String ID = yurimod.yuriMod.makeID("yuriUncommonSkill");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String IMG = yuriMod.makePath(yuriMod.yuri_UNCOMMON_SKILL);
	
	public static final	String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

// /TEXT DECLARATION/
	

// STAT DECLARATION 	
	
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	public static final CardColor COLOR = AbstractCardEnum.YURI_PURPLE;

	private static final int COST = 1;	
	private static final int UPGRADE_REDUCED_COST = 0;
	
	private static final int BLOCK = 1;
	private static final int UPGRADE_PLUS_BLOCK = 2;
	
// /STAT DECLARATION/
	
	public yuriUncommonSkill() {
		
		
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);
		this.baseBlock = BLOCK;
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager
				.addToBottom(new ApplyPowerAction(p, p, new PlatedArmorPower(p, this.block), this.block));

	}
	
	// Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new yuriUncommonSkill();
    }
    
    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_PLUS_BLOCK);
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.upgradeBaseCost(UPGRADE_REDUCED_COST); 
            this.initializeDescription();
        }
    }
}