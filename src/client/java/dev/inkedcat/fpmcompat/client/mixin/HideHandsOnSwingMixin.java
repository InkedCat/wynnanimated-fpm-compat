package dev.inkedcat.fpmcompat.client.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.zigythebird.playeranim.animation.PlayerAnimationController;
import com.zigythebird.playeranimcore.api.firstPerson.FirstPersonConfiguration;
import com.zigythebird.playeranimcore.api.firstPerson.FirstPersonMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import dev.tr7zw.firstperson.api.FirstPersonAPI;

@Mixin(value = net.sweenus.wynnanimated.client.AnimationRegistry.class, remap = false)
public class HideHandsOnSwingMixin {

	@WrapWithCondition(
			method = "playAnimation",
			at = @At(
					value = "INVOKE",
					target = "Lcom/zigythebird/playeranim/animation/PlayerAnimationController;setFirstPersonMode(Lcom/zigythebird/playeranimcore/api/firstPerson/FirstPersonMode;)Lcom/zigythebird/playeranimcore/animation/AnimationController;",
					remap = false))
	private static boolean onBeforeFirstPersonSets(PlayerAnimationController instance, FirstPersonMode firstPersonMode) {
		return !FirstPersonAPI.isEnabled();
	}

	@WrapWithCondition(
			method = "playAnimation",
			at = @At(
					value = "INVOKE",
					target = "Lcom/zigythebird/playeranim/animation/PlayerAnimationController;setFirstPersonConfiguration(Lcom/zigythebird/playeranimcore/api/firstPerson/FirstPersonConfiguration;)Lcom/zigythebird/playeranimcore/animation/AnimationController;",
					remap = false))
	private static boolean onAfterPlayAnimation(PlayerAnimationController instance, FirstPersonConfiguration firstPersonConfiguration) {
		return !FirstPersonAPI.isEnabled();
	}
}