package yurimod.cards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

import com.megacrit.cardcrawl.powers.StrengthPower;
import yurimod.powers.InsanityPower;
import yurimod.yuriMod;
import yurimod.patches.AbstractCardEnum;

public class NerfBat
extends CustomCard {
	
/*
 * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
 * 
 * Strike 
 * Deal 7(9) damage.
*/
	
	
// TEXT DECLARATION 
	
	public static final String ID = yurimod.yuriMod.makeID("NerfBat");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String IMG = yuriMod.makePath(yuriMod.yuri_NERFBAT);

	public static final	String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

// /TEXT DECLARATION/

// STAT DECLARATION 	
	
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	public static final CardColor COLOR = AbstractCardEnum.YURI_PURPLE;

	private static final int COST = 1;
	private static final int DAMAGE = 6;
	private static final int UPGRADE_PLUS_DAMAGE = 4;
    private static final int MAGIC = 1;
	
// /STAT DECLARATION/
	
	public NerfBat() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);
		this.baseDamage = DAMAGE;
        this.baseMagicNumber = magicNumber = MAGIC;
	}
	
	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        AbstractDungeon.actionManager
                .addToBottom(new ApplyPowerAction(m, p, (new StrengthPower(m, -this.magicNumber)), -this.magicNumber));
	}
		
	// Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new NerfBat();
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
			this.upgradeDamage(UPGRADE_PLUS_DAMAGE);
            this.initializeDescription();
        }
    }
}