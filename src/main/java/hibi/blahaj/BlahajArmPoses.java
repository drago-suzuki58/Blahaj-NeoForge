package hibi.blahaj.client;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import net.neoforged.neoforge.client.IArmPoseTransformer;

public final class BlahajArmPoses {
    public static final EnumProxy<HumanoidModel.ArmPose> BLAHAJ_CUDDLE = new EnumProxy<>(
        HumanoidModel.ArmPose.class,
        true,
        (IArmPoseTransformer) BlahajArmPoses::blahajCuddleTransformer
    );

    private static void blahajCuddleTransformer(HumanoidModel<?> model, LivingEntity entity, HumanoidArm arm) {
        model.rightArm.xRot = -0.95F;
        model.rightArm.yRot = (float) (-Math.PI / 8);
        model.leftArm.xRot = -0.90F;
        model.leftArm.yRot = (float) (Math.PI / 8);
    }
}
