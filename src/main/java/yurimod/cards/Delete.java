package yurimod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import yurimod.patches.AbstractCardEnum;
import yurimod.powers.GlitchedPower;
import yurimod.yuriMod;

public class Delete
extends CustomCard {

/*
 * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
 *
 * for each loop x2"
 * "Apply 1 Vulnerable to all enemies, 2(3) times.
 */


// TEXT DECLARATION

	public static final String ID = yuriMod.makeID("Delete");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

	public static final	String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

// /TEXT DECLARATION/

	// Image
	public static final String IMG = yuriMod.makePath(yuriMod.yuri_DELETE);

// STAT DECLARATION

	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	public static final CardColor COLOR = AbstractCardEnum.YURI_PURPLE;

	private static final int COST = 1;
	private static final int MAGIC = 2;

// /STAT DECLARATION/

	public Delete() {
		
		
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);
		this.baseMagicNumber = this.magicNumber = MAGIC;
	}
	
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
		AbstractDungeon.actionManager
				.addToBottom(new com.megacrit.cardcrawl.actions.common.ExhaustAction(2, false));
    }

    
	// Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new Delete();
    }
    
    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
			this.selfRetain = true;
			this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}