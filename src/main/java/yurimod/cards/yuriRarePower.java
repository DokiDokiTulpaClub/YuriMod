package yurimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

import yurimod.yuriMod;
import yurimod.patches.AbstractCardEnum;
import yurimod.powers.RarePower;

public class yuriRarePower
extends CustomCard {
	
/*
 * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
 * 
 * In-Progress Form
 * At the start of your turn, play a TOUCH.
 */
	
	
	
// TEXT DECLARATION 
	
	public static final String ID = yurimod.yuriMod.makeID("yuriRarePower");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	
	public static final	String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

// /TEXT DECLARATION/
	
	public static final String IMG = yuriMod.makePath(yuriMod.yuri_RARE_POWER);

// STAT DECLARATION 	
	
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.POWER;
	public static final CardColor COLOR = AbstractCardEnum.YURI_PURPLE;

	private static final int COST = 3;	
	private static final int UPGRADE_COST = 2;
	
	private static final int MAGIC = 1;
	
// /STAT DECLARATION/
	
	public yuriRarePower() {
		
		
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);
		this.magicNumber = this.baseMagicNumber = MAGIC;
	}
	
	 @Override
	    public void use(AbstractPlayer p, AbstractMonster m) {
	        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RarePower(p, p, this.magicNumber), this.magicNumber));
	    }

	 
	// Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new yuriRarePower();
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADE_COST);
            this.initializeDescription();
        }
    }
}