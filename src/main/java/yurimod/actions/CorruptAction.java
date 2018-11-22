package yurimod.actions;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.relics.BottledFlame;
import com.megacrit.cardcrawl.relics.BottledLightning;
import com.megacrit.cardcrawl.relics.BottledTornado;
import com.megacrit.cardcrawl.ui.panels.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.actions.common.*;

import com.megacrit.cardcrawl.unlock.UnlockTracker;
import yurimod.powers.CommonPower;

public class CorruptAction extends AbstractGameAction {
	private AbstractCard card;
	private AbstractCard cardC;


	public CorruptAction(AbstractCard card, AbstractCard cardC)
	{
		this.card = card;
		this.cardC = cardC;
	}
	public void update(){
        UnlockTracker.markCardAsSeen(this.cardC.cardID);
        if (card.upgraded && cardC.canUpgrade()){
            this.cardC.upgrade();
        }
        if (this.card.type != AbstractCard.CardType.POWER) {
            AbstractDungeon.actionManager.addToTop(new MakeTempCardInDiscardAction(cardC, 1));
        }
        AbstractDungeon.player.masterDeck.addToTop(cardC);

        if (this.card.inBottleFlame) {
            BottledFlame bf = (BottledFlame)AbstractDungeon.player.getRelic("Bottled Flame");
            bf.card = this.cardC;
            bf.setDescriptionAfterLoading();
        }
        if (this.card.inBottleLightning) {
            BottledLightning bl = (BottledLightning)AbstractDungeon.player.getRelic("Bottled Lightning");
            bl.card = this.cardC;
            bl.setDescriptionAfterLoading();
        }
        if (this.card.inBottleTornado) {
            BottledTornado bt = (BottledTornado)AbstractDungeon.player.getRelic("Bottled Tornado");
            bt.card = this.cardC;
            bt.setDescriptionAfterLoading();
        }
        this.isDone = true;
}

}
