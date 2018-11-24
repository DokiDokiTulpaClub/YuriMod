package yurimod.cards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import yurimod.yuriMod;
import yurimod.patches.AbstractCardEnum;

public class StealPen
extends CustomCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Big Slap
     * Deal 10(15)) damage.
     */


// TEXT DECLARATION 

    public static final String ID = yurimod.yuriMod.makeID("StealPen");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = yuriMod.makePath(yuriMod.yuri_STEALPEN);

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

// /TEXT DECLARATION/

// STAT DECLARATION 	

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = AbstractCardEnum.YURI_PURPLE;

    private static final int COST = 1;
    private static final int UPGRADE_COST = 0;
    private static final int MAGIC = 15;
    private static final int UPGRADE_MAGIC = 5;


// /STAT DECLARATION/

    public StealPen() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(CardTags.HEALING);
        this.baseMagicNumber = magicNumber = MAGIC;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.play("GOLD_JINGLE");
        for (int i = 0; i < this.magicNumber; ++i) {
            AbstractDungeon.effectList.add(new GainPennyEffect(m.hb.cX, m.hb.cY));
        }
        AbstractDungeon.player.gainGold(this.magicNumber);
    }




	// Which card to return when making a copy of this card.
	@Override
	public AbstractCard makeCopy() {
		return new StealPen();
	}
	
    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC);
            this.upgradeBaseCost(UPGRADE_COST);
            this.initializeDescription();
        }
    }
}