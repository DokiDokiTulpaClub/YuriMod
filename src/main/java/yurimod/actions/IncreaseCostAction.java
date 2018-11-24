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

public class IncreaseCostAction extends AbstractGameAction {
	private AbstractCard card;

	public IncreaseCostAction(AbstractCard card, int amount)
	{
		this.card = card;
		this.amount = amount;
	}

	public void update(){
        card.modifyCostForCombat(this.amount);
        this.isDone = true;
}

}
