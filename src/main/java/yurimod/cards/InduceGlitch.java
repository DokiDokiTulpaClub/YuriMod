package yurimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import basemod.abstracts.CustomCard;

import com.megacrit.cardcrawl.powers.WeakPower;
import yurimod.powers.GlitchedPower;
import yurimod.yuriMod;
import yurimod.patches.AbstractCardEnum;

public class InduceGlitch
extends CustomCard {
	
/*
 * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
 * 
 * for each loop x2"
 * "Apply 1 Vulnerable to all enemies, 2(3) times.
 */
		
	
// TEXT DECLARATION 
	
	public static final String ID = yurimod.yuriMod.makeID("InduceGlitch");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	
	public static final	String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

// /TEXT DECLARATION/
	
	// Image
	public static final String IMG = yuriMod.makePath(yuriMod.yuri_INDUCE_GLITCH);

// STAT DECLARATION 	
	
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.SKILL;
	public static final CardColor COLOR = AbstractCardEnum.YURI_PURPLE;

	private static final int COST = 1;
	private static final int MAGIC = 2;
	private static final int UPGRADE_MAGIC = 1;

// /STAT DECLARATION/
	
	public InduceGlitch() {
		
		
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);
		this.baseMagicNumber = this.magicNumber = MAGIC;
		this.exhaust = true;
	}
	
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new GlitchedPower(m, p, this.magicNumber), this.magicNumber));
    }

    
	// Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new InduceGlitch();
    }
    
    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC);
            this.initializeDescription();
        }
    }
}