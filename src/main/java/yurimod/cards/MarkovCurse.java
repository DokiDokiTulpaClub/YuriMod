package yurimod.cards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.SetDontTriggerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardQueueItem;
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

public class MarkovCurse
extends CustomCard

	{

		/*
		 * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
		 */


// TEXT DECLARATION 

		public static final String ID = yurimod.yuriMod.makeID("MarkovCurse");
		private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
		public static final String IMG = yuriMod.makePath(yuriMod.yuri_MARKOV_CURSE);

		public static final String NAME = cardStrings.NAME;
		public static final String DESCRIPTION = cardStrings.DESCRIPTION;
		public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

// /TEXT DECLARATION/

// STAT DECLARATION 	

		private static final CardRarity RARITY = CardRarity.SPECIAL;
		private static final CardTarget TARGET = CardTarget.NONE;
		private static final CardType TYPE = CardType.CURSE;
		public static final CardColor COLOR = CardColor.CURSE;

		private static final int COST = -2;
		private static final int MAGIC = 1;

// /STAT DECLARATION/
	
	public MarkovCurse() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		this.baseMagicNumber = magicNumber = MAGIC;
	}

		// Actions the card should do.
		@Override
		public void use (AbstractPlayer p, AbstractMonster m) {
            if (this.dontTriggerOnUseCard) {
                AbstractDungeon.actionManager
                        .addToBottom(new ApplyPowerAction(p, p, new InsanityPower(p, p, this.magicNumber), this.magicNumber));
            }
        }

        @Override
        public void triggerWhenDrawn() {AbstractDungeon.actionManager.addToBottom(new SetDontTriggerAction(this, false));
	}

	@Override
    public void triggerOnEndOfTurnForPlayingCard(){
	    this.dontTriggerOnUseCard = true;
	    AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, true));
    }

    @Override
    public void triggerOnExhaust(){
	    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(this.makeCopy()));
	    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(this.makeCopy()));
    }


		// Which card to return when making a copy of this card.
		@Override
		public AbstractCard makeCopy () {
		return new MarkovCurse();
	}

		//Upgraded stats.
		@Override
		public void upgrade() {
		if (!this.upgraded) {
			this.initializeDescription();
		}
	}
}