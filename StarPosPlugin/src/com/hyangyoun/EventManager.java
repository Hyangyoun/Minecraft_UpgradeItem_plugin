package com.hyangyoun;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class EventManager implements Listener {

    private Main plugin;
    EventManager(Main plugin){
        this.plugin = plugin;
    }

    HashMap<UUID,Integer> taskuser = new HashMap<UUID,Integer>();

    @EventHandler
    public void onOpenAnvil(PlayerInteractEvent event){
        Player p = (Player)event.getPlayer();
        Action action = event.getAction();
        ItemStack is = new ItemStack(Material.NETHER_STAR);
        ItemMeta im = is.getItemMeta();
        im.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,new AttributeModifier(new UUID(7092,7092),"af",500f, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND));
        is.setItemMeta(im);
        if(event.getClickedBlock() != null && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(event.getClickedBlock().getType().equals(Material.ANVIL)){
                event.setCancelled(true);
                OpenInventory(p);
            }
        }
        if(action.equals(Action.RIGHT_CLICK_AIR)&&p.getInventory().getItemInMainHand().equals(is)){
            p.sendMessage("gd");
        }
    }

    @EventHandler
    public void ClickStarposInventory(InventoryClickEvent event){
        Player player = (Player)event.getWhoClicked();

        if(!event.getView().getTitle().equalsIgnoreCase("스타포스")){
            return;
        }
        if(event.getCurrentItem() != null){
            switch (event.getCurrentItem().getType()){
                case PAPER:
                    event.setCancelled(true);
                    taskuser.put(player.getUniqueId(),Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                        int i = 3;
                        @Override
                        public void run() {
                            if(i != -1 && i != 0){
                                player.sendMessage(i+ "초 후 이동합니다");
                                i--;
                            }
                            else{
                                player.teleport(player.getBedSpawnLocation());
                                Bukkit.getScheduler().cancelTask(taskuser.get(player.getUniqueId()));
                            }
                        }
                    },0,20));
                    break;
                case DIAMOND_SWORD:
                    if(event.getRawSlot() == 4){
                        if(event.isRightClick()){
                            ItemMeta weapon = WeaponUpgrade(event.getCurrentItem().getItemMeta(),1.6f,7);
                            if(weapon==null){
                                event.setCurrentItem(null);
                            }
                            else{
                                event.getCurrentItem().setItemMeta(weapon);
                                event.setCancelled(true);
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public void OpenInventory(Player player){
        Inventory starPos = Bukkit.createInventory(null,9,"스타포스");
        ItemStack paper = new ItemStack(Material.PAPER);
        ItemMeta paperm = paper.getItemMeta();
        paperm.setDisplayName(ChatColor.GOLD+"장비강화");
        paper.setItemMeta(paperm);

        starPos.setItem(0,paper);
        starPos.setItem(1,paper);
        starPos.setItem(2,paper);
        starPos.setItem(3,paper);
        starPos.setItem(5,paper);
        starPos.setItem(6,paper);
        starPos.setItem(7,paper);
        starPos.setItem(8,paper);
        player.openInventory(starPos);
    }

    public ItemMeta WeaponUpgrade(ItemMeta item,float attackSpeed, float attackDamage){
        Integer ilsp = null, ilfp = null, ildp = null;
        Integer sp = null, fp = null, dp = null;
        float ad = attackDamage;
        String star;
        if(item.getLore() == null){
            item.setLore(Arrays.asList(ChatColor.GOLD+"★☆☆☆☆☆☆☆☆☆","",ChatColor.WHITE+"성공 확률: "+ChatColor.GREEN+"90%"
                    ,ChatColor.WHITE+"하락 확률: "+ChatColor.RED+"0%",ChatColor.WHITE+"파괴 확률: "+ChatColor.DARK_RED+"0%"));
            item.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,new AttributeModifier(UUID.randomUUID(),"diaswordas",attackSpeed,
                    AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND));
            item.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,new AttributeModifier(UUID.randomUUID(),"attack.damage",ad+0.2,
                    AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND));
        }
        else{
            switch (item.getCustomModelData()){
                case 1:
                    ilsp = 80;
                    ilfp = 5;
                    ildp = 0;
                    sp = 90;
                    fp = 0;
                    dp = 0;
                    ad = attackDamage+0.4f;
                    star = ChatColor.GOLD+"★★☆☆☆☆☆☆☆☆";
                    break;
                case 2:
                    ilsp = 70;
                    ilfp = 10;
                    ildp = 0;
                    sp = 80;
                    fp = 5;
                    dp = 0;
                    ad = attackDamage+0.6f;
                    star = ChatColor.GOLD+"★★★☆☆☆☆☆☆☆";
                    break;
                case 3:
                    ilsp = 60;
                    ilfp = 15;
                    ildp = 0;
                    sp = 70;
                    fp = 10;
                    dp = 0;
                    ad = attackDamage+0.8f;
                    star = ChatColor.GOLD+"★★★★☆☆☆☆☆☆";
                    break;
                case 4:
                    ilsp = 60;
                    ilfp = 15;
                    ildp = 1;
                    sp = 60;
                    fp = 15;
                    dp = 0;
                    ad = attackDamage+1.0f;
                    star = ChatColor.GOLD+"★★★★★☆☆☆☆☆";
                    break;
                case 5:
                    ilsp = 50;
                    ilfp = 20;
                    ildp = 2;
                    sp = 60;
                    fp = 15;
                    dp = 1;
                    ad = attackDamage+1.2f;
                    star = ChatColor.GOLD+"★★★★★★☆☆☆☆";
                    break;
                case 6:
                    ilsp = 40;
                    ilfp = 40;
                    ildp = 3;
                    sp = 50;
                    fp = 20;
                    dp = 2;
                    ad = attackDamage+1.4f;
                    star = ChatColor.GOLD+"★★★★★★★☆☆☆";
                    break;
                case 7:
                    ilsp = 30;
                    ilfp = 60;
                    ildp = 4;
                    sp = 40;
                    fp = 40;
                    dp = 3;
                    ad = attackDamage+1.6f;
                    star = ChatColor.GOLD+"★★★★★★★★☆☆";
                    break;
                case 8:
                    ilsp = 20;
                    ilfp = 80;
                    ildp = 5;
                    sp = 30;
                    fp = 60;
                    dp = 4;
                    ad = attackDamage+1.8f;
                    star = ChatColor.GOLD+"★★★★★★★★★☆";
                    break;
                case 9:
                    ilsp = 100;
                    ilfp = 100;
                    ildp = 100;
                    sp = 20;
                    fp = 80;
                    dp = 5;
                    ad = attackDamage+2.0f;
                    star = ChatColor.AQUA+"★★★★★★★★★★";
                    break;
                default:
                    star = null;
                    break;
            }
            if(new Random().nextInt(100) < sp){
                item.setLore(Arrays.asList(star,"",ChatColor.WHITE+"성공 확률: "+ChatColor.GREEN+ilsp+"%"
                        ,ChatColor.WHITE+"하락 확률: "+ChatColor.RED+ilfp+"%",ChatColor.WHITE+"파괴 확률: "+ChatColor.DARK_RED+ildp+"%"));
                item.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
                item.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
                item.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,new AttributeModifier(UUID.randomUUID(),"attack.speed",attackSpeed,
                        AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND));
                item.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,new AttributeModifier(UUID.randomUUID(),"attack.damage",ad,
                        AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND));
                item.setCustomModelData(item.getCustomModelData()+1);
                return item;
            }
            else{
                if(new Random().nextInt(100)<fp){
                    switch (item.getCustomModelData()){
                        case 2:
                            ilsp = 90;
                            ilfp = 0;
                            ildp = 0;
                            ad = attackDamage+0.2f;
                            star = ChatColor.GOLD+"★☆☆☆☆☆☆☆☆☆";
                            break;
                        case 3:
                            ilsp = 80;
                            ilfp = 5;
                            ildp = 0;
                            ad = attackDamage+0.4f;
                            star = ChatColor.GOLD+"★★☆☆☆☆☆☆☆☆";
                            break;
                        case 4:
                            ilsp = 70;
                            ilfp = 10;
                            ildp = 0;
                            ad = attackDamage+0.6f;
                            star = ChatColor.GOLD+"★★★☆☆☆☆☆☆☆";
                            break;
                        case 5:
                            ilsp = 60;
                            ilfp = 15;
                            ildp = 0;
                            ad = attackDamage+0.8f;
                            star = ChatColor.GOLD+"★★★★☆☆☆☆☆☆";
                            break;
                        case 6:
                            ilsp = 60;
                            ilfp = 15;
                            ildp = 1;
                            ad = attackDamage+1.0f;
                            star = ChatColor.GOLD+"★★★★★☆☆☆☆☆";
                            break;
                        case 7:
                            ilsp = 50;
                            ilfp = 20;
                            ildp = 2;
                            ad = attackDamage+1.2f;
                            star = ChatColor.GOLD+"★★★★★★☆☆☆☆";
                            break;
                        case 8:
                            ilsp = 40;
                            ilfp = 40;
                            ildp = 3;
                            ad = attackDamage+1.4f;
                            star = ChatColor.GOLD+"★★★★★★★☆☆☆";
                            break;
                        case 9:
                            ilsp = 30;
                            ilfp = 60;
                            ildp = 4;
                            ad = attackDamage+1.6f;
                            star = ChatColor.GOLD+"★★★★★★★★☆☆";
                            break;
                        default:
                            star = null;
                            break;
                    }
                    item.setLore(Arrays.asList(star,"",ChatColor.WHITE+"성공 확률: "+ChatColor.GREEN+ilsp+"%"
                            ,ChatColor.WHITE+"하락 확률: "+ChatColor.RED+ilfp+"%",ChatColor.WHITE+"파괴 확률: "+ChatColor.DARK_RED+ildp+"%"));
                    item.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
                    item.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
                    item.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,new AttributeModifier(UUID.randomUUID(),"attack.speed",attackSpeed,
                            AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND));
                    item.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,new AttributeModifier(UUID.randomUUID(),"attack.damage",ad,
                            AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND));
                    item.setCustomModelData(item.getCustomModelData()-1);
                    return item;
                }
                else if(new Random().nextInt(100)<dp){
                    return null;
                }
                else{
                    return item;
                }
            }
        }
        return item;
    }
}
