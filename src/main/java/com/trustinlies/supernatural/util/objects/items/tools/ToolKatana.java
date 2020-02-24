package com.trustinlies.supernatural.util.objects.items.tools;

import com.google.common.collect.Multimap;
import com.trustinlies.supernatural.Main;
import com.trustinlies.supernatural.init.ItemInit;
import com.trustinlies.supernatural.util.interfaces.IHasModel;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.*;

public class ToolKatana extends ItemSword implements IHasModel {

    private final float attackDamage;
    public static final List<Item> SHINY = new ArrayList<Item>();

    public ToolKatana(String name, ToolMaterial material, String keyword){
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.ITEMTAB);
        this.attackDamage = 4.0F + material.getAttackDamage();


        ItemInit.ITEMS.add(this);

        if(keyword.contains("shiny")){
            SHINY.add(this);
        }

    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        if(SHINY.contains(stack.getItem())) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "inventory", "weapons");
    }

    /*
    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.attackDamage, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -1.5D, 0));
        }

        return multimap;
    }
    */

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slotIn, ItemStack stack)
    {
        final Multimap<String, AttributeModifier> modifiers = super.getAttributeModifiers(slotIn, stack);

        if (slotIn == EntityEquipmentSlot.MAINHAND)
        {
            replaceModifier(modifiers, SharedMonsterAttributes.ATTACK_SPEED /* or whatever attribute you want to modify */, ATTACK_SPEED_MODIFIER /* ... */,  -0.5);
            replaceModifier(modifiers, SharedMonsterAttributes.ATTACK_DAMAGE /* or whatever attribute you want to modify */, ATTACK_DAMAGE_MODIFIER /* ... */,  attackDamage);
        }

        return modifiers;
    }

    private void replaceModifier(Multimap<String, AttributeModifier> modifierMultimap, IAttribute attribute, UUID id, double multiplier)
    {
        // Get the modifiers for the specified attribute
        final Collection<AttributeModifier> modifiers = modifierMultimap.get(attribute.getName());

        // Find the modifier with the specified ID, if any
        final Optional<AttributeModifier> modifierOptional = modifiers.stream().filter(attributeModifier -> attributeModifier.getID().equals(id)).findFirst();

        if (modifierOptional.isPresent())
        {
            final AttributeModifier modifier = modifierOptional.get();

            modifiers.remove(modifier); // Remove it
            modifiers.add(new AttributeModifier(modifier.getID(), modifier.getName(), multiplier, modifier.getOperation())); // Might wanna change the formula a bit because it's funky...
        }
    }

}
