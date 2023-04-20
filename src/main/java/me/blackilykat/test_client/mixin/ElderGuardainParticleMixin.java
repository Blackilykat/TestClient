package me.blackilykat.test_client.mixin;

import me.blackilykat.test_client.modules.utility.BlockGuardianParticlesModule;
import me.blackilykat.test_client.util.SharedVariables;
import net.minecraft.client.particle.ElderGuardianAppearanceParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ElderGuardianAppearanceParticle.Factory.class)
public class ElderGuardainParticleMixin {
	@Inject(method = "createParticle(Lnet/minecraft/particle/DefaultParticleType;Lnet/minecraft/client/world/ClientWorld;DDDDDD)Lnet/minecraft/client/particle/Particle;", at = @At("HEAD"))
	private void testClient$limitGuardianParticles(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, CallbackInfoReturnable<Particle> cir) {
		if(BlockGuardianParticlesModule.instance.isEnabled) cir.cancel();
		if(SharedVariables.elderGuardianParticleTimer > 0) cir.cancel();
		SharedVariables.elderGuardianParticleTimer = 200;
	}
}
