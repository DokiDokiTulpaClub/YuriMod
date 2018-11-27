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

public class ScareAction extends AbstractGameAction {
	private AbstractMonster m;

	public ScareAction(AbstractMonster m)
	{
		this.m = m;
	}

	public void update(){
	if (m.escapeTimer <= 0){
		AbstractDungeon.getCurrRoom().monsters.monsters.remove(m);
		this.isDone = true;
	}
}
}
