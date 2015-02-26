package com.spacechase0.minecraft.condensedpotions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CondensedPotionItem extends Item
{
	public CondensedPotionItem( int id, boolean theSplash )
	{
		super( id );
		splash = theSplash;

		setUnlocalizedName( "condensedPotion" );
		setTextureName( "potion" );
        this.setMaxStackSize(1);
        this.setHasSubtypes(true);
        this.setMaxDamage(9);
        this.setCreativeTab(CreativeTabs.tabBrewing);
	}
	
    @Override
    public boolean isDamageable()
    {
    	return true;
    }
    
    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player)
    {
    	ItemStack potion = ItemStack.loadItemStackFromNBT( getPotionTag( stack ) );
    	Item.potion.onEaten( potion, world, player );

        if (!player.capabilities.isCreativeMode)
        {
        	stack.setItemDamage( stack.getItemDamage() + 1 );
            if (stack.getItemDamage() > getMaxDamage())
            {
                return new ItemStack( Item.glassBottle, 9 );
            }
        }

        return stack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
    	ItemStack potion = ItemStack.loadItemStackFromNBT(  getPotionTag( stack ) );
    	return Item.potion.getMaxItemUseDuration( stack );
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
    	ItemStack potion = ItemStack.loadItemStackFromNBT( getPotionTag( stack ) );
    	return Item.potion.getItemUseAction( stack );
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
    	ItemStack potion = ItemStack.loadItemStackFromNBT( getPotionTag( stack ) );
    	Item.potion.onItemRightClick( potion, world, player );
    	
        if (Item.potion.isSplash(potion.getItemDamage()))
        {
        	stack.setItemDamage( stack.getItemDamage() + 1 );
            if (stack.getItemDamage() > getMaxDamage())
            {
                return new ItemStack( 0, 0, 0 );//new ItemStack( Item.glassBottle, 9 );
            }
            
            return stack;
        }
        else
        {
            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
            return stack;
        }
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
    	potionIcon = par1IconRegister.registerIcon(this.getIconString() + "_" + "bottle_drinkable");
    	splashIcon = par1IconRegister.registerIcon(this.getIconString() + "_" + "bottle_splash");
    	contentsIcon = par1IconRegister.registerIcon(this.getIconString() + "_" + "overlay");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage( int damage )
    {
    	return splash ? splashIcon : potionIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamageForRenderPass(int damage, int pass)
    {
    	if ( pass != 0 )
    	{
    		return getIconFromDamage( damage );
    	}
    	
    	return Item.potion.getIconFromDamageForRenderPass( damage, pass );
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int pass)
    {
    	ItemStack potion = ItemStack.loadItemStackFromNBT( getPotionTag( stack ) );
        return Item.potion.getColorFromItemStack( potion, pass );
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return Item.potion.requiresMultipleRenderPasses();
    }

    @Override
    public String getItemDisplayName(ItemStack stack)
    {
    	ItemStack potion = ItemStack.loadItemStackFromNBT( getPotionTag( stack ) );
    	return "Condensed " + Item.potion.getItemDisplayName( potion );
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4)
    {
    	ItemStack potion = ItemStack.loadItemStackFromNBT( getPotionTag( stack ) );
    	Item.potion.addInformation( potion, player, par3List, par4 );
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
    	ItemStack potion = ItemStack.loadItemStackFromNBT( getPotionTag( stack ) );
    	return Item.potion.hasEffect( stack );
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs tabs, List list)
    {
    	List potions = new ArrayList();
    	Item.potion.getSubItems( id, tabs, potions );
    	
        Iterator it = potions.iterator();
        while (it.hasNext())
        {
        	ItemStack potion = ( ItemStack ) it.next();
        	if ( splash == ItemPotion.isSplash( potion.getItemDamage() ) )
        	{
	        	NBTTagCompound potCompound = new NBTTagCompound();
	        	potion.writeToNBT( potCompound );
	        	
	        	NBTTagCompound compound = new NBTTagCompound();
	        	compound.setTag( "Potion", potCompound );
	        	
	        	ItemStack cond = new ItemStack( this, 1, 1 );
	        	cond.setTagCompound( compound );
	        	list.add( cond );
        	}
        }
    }
    
    @Override
    @SideOnly( Side.CLIENT )
    public EnumRarity getRarity( ItemStack stack )
    {
    	return EnumRarity.uncommon;
    }
    
    public NBTTagCompound getPotionTag( ItemStack stack )
    {
    	if ( stack.getTagCompound() == null )
    	{
    		return new NBTTagCompound();
    	}
    	
    	return ( NBTTagCompound ) stack.getTagCompound().getTag( "Potion" );
    }
    
    private final boolean splash;
    private Icon potionIcon, splashIcon, contentsIcon;
}
