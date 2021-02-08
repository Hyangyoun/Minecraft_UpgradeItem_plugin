package com.hyangyoun;

import net.minecraft.server.v1_16_R3.Enchantment;
import net.minecraft.server.v1_16_R3.WeightedRandomEnchant;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
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
        if(event.getClickedBlock() != null && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(event.getClickedBlock().getType().equals(Material.ANVIL)){
                event.setCancelled(true);
                OpenInventory(p);
            }
        }
        if(event.getClickedBlock() != null && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(event.getClickedBlock().getType().equals(Material.ENCHANTING_TABLE)){
                event.setCancelled(true);
                OpenEnchant(p);
            }
        }
    }

    @EventHandler
    public void CloseInventory(InventoryCloseEvent event){
        Player player = (Player)event.getPlayer();
        if(event.getView().getTitle().equalsIgnoreCase("스타포스")){
            ItemStack upgradeItem = event.getInventory().getItem(4);
            if(upgradeItem == null){
                return;
            }
            else{
                player.getInventory().addItem(upgradeItem);
            }
        }
    }

    @EventHandler
    public void ClickStarposInventory(InventoryClickEvent event){
        Player player = (Player)event.getWhoClicked();

        if(!event.getView().getTitle().equalsIgnoreCase("스타포스") || !event.getView().getTitle().equalsIgnoreCase("잠재능력")){
            return;
        }
        if(event.getCurrentItem() != null && event.getView().getTitle().equalsIgnoreCase("스타포스")){
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
                    if(event.getRawSlot() == 4 && event.isRightClick()){
                        for(ItemStack item:player.getInventory()){
                            if(item == null){
                                continue;
                            }
                            else if(item.getType().equals(Material.EMERALD)){
                                item.setAmount(item.getAmount()-1);
                                ItemMeta weapon = WeaponUpgrade(event.getCurrentItem().getItemMeta(),1.6f,7,player);
                                if(weapon==null){
                                    event.setCurrentItem(null);
                                }
                                else{
                                    event.getCurrentItem().setItemMeta(weapon);
                                    event.setCancelled(true);
                                }
                                return;
                            }
                            else{
                                continue;
                            }
                        }
                        event.setCancelled(true);
                        player.sendMessage(ChatColor.DARK_RED+"강화에 필요한 에메랄드가 부족합니다.");
                        player.closeInventory();
                    }
                    break;
                case DIAMOND_AXE:
                    if(event.getRawSlot() == 4 && event.isRightClick()){
                        for(ItemStack item:player.getInventory()){
                            if(item == null){
                                continue;
                            }
                            else if(item.getType().equals(Material.EMERALD)){
                                item.setAmount(item.getAmount()-1);
                                ItemMeta weapon = WeaponUpgrade(event.getCurrentItem().getItemMeta(),1,9,player);
                                if(weapon==null){
                                    event.setCurrentItem(null);
                                }
                                else{
                                    event.getCurrentItem().setItemMeta(weapon);
                                    event.setCancelled(true);
                                }
                                return;
                            }
                            else{
                                continue;
                            }
                        }
                        event.setCancelled(true);
                        player.sendMessage(ChatColor.DARK_RED+"강화에 필요한 에메랄드가 부족합니다.");
                        player.closeInventory();
                    }
                    break;
                case DIAMOND_HELMET:
                    if(event.getRawSlot() == 4 && event.isRightClick()){
                        for(ItemStack item:player.getInventory()){
                            if(item == null){
                                continue;
                            }
                            else if(item.getType().equals(Material.EMERALD)){
                                item.setAmount(item.getAmount()-1);
                                ItemMeta armor = ArmorUpgrade(event.getCurrentItem().getItemMeta(),3,2,EquipmentSlot.HEAD,player);
                                if(armor==null){
                                    event.setCurrentItem(null);
                                }
                                else{
                                    event.getCurrentItem().setItemMeta(armor);
                                    event.setCancelled(true);
                                }
                                return;
                            }
                            else{
                                continue;
                            }
                        }
                        event.setCancelled(true);
                        player.sendMessage(ChatColor.DARK_RED+"강화에 필요한 에메랄드가 부족합니다.");
                        player.closeInventory();
                    }
                    break;
                case DIAMOND_CHESTPLATE:
                    if(event.getRawSlot() == 4 && event.isRightClick()){
                        for(ItemStack item:player.getInventory()){
                            if(item == null){
                                continue;
                            }
                            else if(item.getType().equals(Material.EMERALD)){
                                item.setAmount(item.getAmount()-1);
                                ItemMeta armor = ArmorUpgrade(event.getCurrentItem().getItemMeta(),8,2,EquipmentSlot.CHEST,player);
                                if(armor==null){
                                    event.setCurrentItem(null);
                                }
                                else{
                                    event.getCurrentItem().setItemMeta(armor);
                                    event.setCancelled(true);
                                }
                                return;
                            }
                            else{
                                continue;
                            }
                        }
                        event.setCancelled(true);
                        player.sendMessage(ChatColor.DARK_RED+"강화에 필요한 에메랄드가 부족합니다.");
                        player.closeInventory();
                    }
                    break;
                case DIAMOND_LEGGINGS:
                    if(event.getRawSlot() == 4 && event.isRightClick()){
                        for(ItemStack item:player.getInventory()){
                            if(item == null){
                                continue;
                            }
                            else if(item.getType().equals(Material.EMERALD)){
                                item.setAmount(item.getAmount()-1);
                                ItemMeta armor = ArmorUpgrade(event.getCurrentItem().getItemMeta(),6,2,EquipmentSlot.LEGS,player);
                                if(armor==null){
                                    event.setCurrentItem(null);
                                }
                                else{
                                    event.getCurrentItem().setItemMeta(armor);
                                    event.setCancelled(true);
                                }
                                return;
                            }
                            else{
                                continue;
                            }
                        }
                        event.setCancelled(true);
                        player.sendMessage(ChatColor.DARK_RED+"강화에 필요한 에메랄드가 부족합니다.");
                        player.closeInventory();
                    }
                    break;
                case DIAMOND_BOOTS:
                    if(event.getRawSlot() == 4 && event.isRightClick()){
                        for(ItemStack item:player.getInventory()){
                            if(item == null){
                                continue;
                            }
                            else if(item.getType().equals(Material.EMERALD)){
                                item.setAmount(item.getAmount()-1);
                                ItemMeta armor = ArmorUpgrade(event.getCurrentItem().getItemMeta(),3,2,EquipmentSlot.FEET,player);
                                if(armor==null){
                                    event.setCurrentItem(null);
                                }
                                else{
                                    event.getCurrentItem().setItemMeta(armor);
                                    event.setCancelled(true);
                                }
                                return;
                            }
                            else{
                                continue;
                            }
                        }
                        event.setCancelled(true);
                        player.sendMessage(ChatColor.DARK_RED+"강화에 필요한 에메랄드가 부족합니다.");
                        player.closeInventory();
                    }
                    break;
                default:
                    break;
            }
        }

        if(event.getCurrentItem() != null && event.getView().getTitle().equalsIgnoreCase("잠재능력")){
            switch (event.getCurrentItem().getType()){
                case BOOK:
                    event.setCancelled(true);
                    break;
                case DIAMOND_SWORD:
                    event.setCancelled(true);
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
    public void OpenEnchant(Player player){
        Inventory enchant = Bukkit.createInventory(null, 9 , "잠재능력");
        ItemStack book = new ItemStack(Material.BOOK);
        ItemMeta bookm = book.getItemMeta();
        bookm.setDisplayName(ChatColor.GOLD+"잠재능력 부여");
        book.setItemMeta(bookm);

        enchant.setItem(0,book);
        enchant.setItem(1,book);
        enchant.setItem(2,book);
        enchant.setItem(3,book);
        enchant.setItem(5,book);
        enchant.setItem(6,book);
        enchant.setItem(7,book);
        enchant.setItem(8,book);
        player.openInventory(enchant);
    }

    public ItemMeta WeaponUpgrade(ItemMeta item,float attackSpeed, float attackDamage, Player player){
        Integer ilsp = null, ilfp = null, ildp = null;
        Integer sp = null, fp = null, dp = null;
        float ad = attackDamage;
        String star;
        if(!item.hasCustomModelData()){
            item.setLore(Arrays.asList(ChatColor.GOLD+"★☆☆☆☆☆☆☆☆☆","",ChatColor.WHITE+"성공 확률: "+ChatColor.GREEN+"90%"
                    ,ChatColor.WHITE+"하락 확률: "+ChatColor.RED+"0%",ChatColor.WHITE+"파괴 확률: "+ChatColor.DARK_RED+"0%"));
            item.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,new AttributeModifier(UUID.randomUUID(),"diaswordas",attackSpeed,
                    AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND));
            item.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,new AttributeModifier(UUID.randomUUID(),"attack.damage",ad+0.2,
                    AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND));
            item.setCustomModelData(1);
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE,0.1f,1);
            return item;
        }
        else{
            int posPoint = item.getCustomModelData();
            switch (posPoint){
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
                item.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,new AttributeModifier(UUID.randomUUID(),"attack.damage",ad,
                        AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND));
                item.setCustomModelData(posPoint+1);
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE,0.1f,1);
                return item;
            }
            else{
                if(new Random().nextInt(100)<dp) {
                    player.playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 0.3f, 1);
                    return null;
                }
                else if(new Random().nextInt(100)<fp){
                    switch (posPoint){
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
                    item.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,new AttributeModifier(UUID.randomUUID(),"attack.damage",ad,
                            AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND));
                    item.setCustomModelData(posPoint-1);
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_DESTROY,0.1f,1);
                    return item;
                }
                else{
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE,0.1f,1);
                    return item;
                }
            }
        }
    }

    public ItemMeta ArmorUpgrade(ItemMeta item,float armor, float armor_toughness, EquipmentSlot equipment, Player player){
        Integer ilsp = null, ilfp = null, ildp = null;
        Integer sp = null, fp = null, dp = null;
        float mh = 0;
        String star;
        if(!item.hasCustomModelData()){
            item.setLore(Arrays.asList(ChatColor.GOLD+"★☆☆☆☆☆☆☆☆☆","",ChatColor.WHITE+"성공 확률: "+ChatColor.GREEN+"90%"
                    ,ChatColor.WHITE+"하락 확률: "+ChatColor.RED+"0%",ChatColor.WHITE+"파괴 확률: "+ChatColor.DARK_RED+"0%"));
            item.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS,new AttributeModifier(UUID.randomUUID(),"armor.toughness",armor_toughness,
                    AttributeModifier.Operation.ADD_NUMBER,equipment));
            item.addAttributeModifier(Attribute.GENERIC_ARMOR,new AttributeModifier(UUID.randomUUID(),"armor",armor,
                    AttributeModifier.Operation.ADD_NUMBER,equipment));
            item.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH,new AttributeModifier(UUID.randomUUID(),"max.health",0.5,
                    AttributeModifier.Operation.ADD_NUMBER,equipment));
            item.setCustomModelData(1);
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE,0.1f,1);
            return item;
        }
        else{
            int posPoint = item.getCustomModelData();
            switch (posPoint){
                case 1:
                    ilsp = 80;
                    ilfp = 5;
                    ildp = 0;
                    sp = 90;
                    fp = 0;
                    dp = 0;
                    mh = 1;
                    star = ChatColor.GOLD+"★★☆☆☆☆☆☆☆☆";
                    break;
                case 2:
                    ilsp = 70;
                    ilfp = 10;
                    ildp = 0;
                    sp = 80;
                    fp = 5;
                    dp = 0;
                    mh = 1.5f;
                    star = ChatColor.GOLD+"★★★☆☆☆☆☆☆☆";
                    break;
                case 3:
                    ilsp = 60;
                    ilfp = 15;
                    ildp = 0;
                    sp = 70;
                    fp = 10;
                    dp = 0;
                    mh = 2;
                    star = ChatColor.GOLD+"★★★★☆☆☆☆☆☆";
                    break;
                case 4:
                    ilsp = 60;
                    ilfp = 15;
                    ildp = 1;
                    sp = 60;
                    fp = 15;
                    dp = 0;
                    mh = 2.5f;
                    star = ChatColor.GOLD+"★★★★★☆☆☆☆☆";
                    break;
                case 5:
                    ilsp = 50;
                    ilfp = 20;
                    ildp = 2;
                    sp = 60;
                    fp = 15;
                    dp = 1;
                    mh = 3;
                    star = ChatColor.GOLD+"★★★★★★☆☆☆☆";
                    break;
                case 6:
                    ilsp = 40;
                    ilfp = 40;
                    ildp = 3;
                    sp = 50;
                    fp = 20;
                    dp = 2;
                    mh = 3.5f;
                    star = ChatColor.GOLD+"★★★★★★★☆☆☆";
                    break;
                case 7:
                    ilsp = 30;
                    ilfp = 60;
                    ildp = 4;
                    sp = 40;
                    fp = 40;
                    dp = 3;
                    mh = 4;
                    star = ChatColor.GOLD+"★★★★★★★★☆☆";
                    break;
                case 8:
                    ilsp = 20;
                    ilfp = 80;
                    ildp = 5;
                    sp = 30;
                    fp = 60;
                    dp = 4;
                    mh = 4.5f;
                    star = ChatColor.GOLD+"★★★★★★★★★☆";
                    break;
                case 9:
                    ilsp = 100;
                    ilfp = 100;
                    ildp = 100;
                    sp = 20;
                    fp = 80;
                    dp = 5;
                    mh = 5;
                    star = ChatColor.AQUA+"★★★★★★★★★★";
                    break;
                default:
                    star = null;
                    break;
            }
            if(new Random().nextInt(100) < sp){
                item.setLore(Arrays.asList(star,"",ChatColor.WHITE+"성공 확률: "+ChatColor.GREEN+ilsp+"%"
                        ,ChatColor.WHITE+"하락 확률: "+ChatColor.RED+ilfp+"%",ChatColor.WHITE+"파괴 확률: "+ChatColor.DARK_RED+ildp+"%"));
                item.removeAttributeModifier(Attribute.GENERIC_MAX_HEALTH);
                item.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH,new AttributeModifier(UUID.randomUUID(),"max.health",mh,
                        AttributeModifier.Operation.ADD_NUMBER,equipment));
                item.setCustomModelData(posPoint+1);
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE,0.1f,1);
                return item;
            }
            else{
                if(new Random().nextInt(100)<dp) {
                    player.playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 0.3f, 1);
                    return null;
                }
                else if(new Random().nextInt(100)<fp){
                    switch (posPoint){
                        case 2:
                            ilsp = 90;
                            ilfp = 0;
                            ildp = 0;
                            mh = 0.5f;
                            star = ChatColor.GOLD+"★☆☆☆☆☆☆☆☆☆";
                            break;
                        case 3:
                            ilsp = 80;
                            ilfp = 5;
                            ildp = 0;
                            mh = 1;
                            star = ChatColor.GOLD+"★★☆☆☆☆☆☆☆☆";
                            break;
                        case 4:
                            ilsp = 70;
                            ilfp = 10;
                            ildp = 0;
                            mh = 1.5f;
                            star = ChatColor.GOLD+"★★★☆☆☆☆☆☆☆";
                            break;
                        case 5:
                            ilsp = 60;
                            ilfp = 15;
                            ildp = 0;
                            mh = 2;
                            star = ChatColor.GOLD+"★★★★☆☆☆☆☆☆";
                            break;
                        case 6:
                            ilsp = 60;
                            ilfp = 15;
                            ildp = 1;
                            mh = 2.5f;
                            star = ChatColor.GOLD+"★★★★★☆☆☆☆☆";
                            break;
                        case 7:
                            ilsp = 50;
                            ilfp = 20;
                            ildp = 2;
                            mh = 3;
                            star = ChatColor.GOLD+"★★★★★★☆☆☆☆";
                            break;
                        case 8:
                            ilsp = 40;
                            ilfp = 40;
                            ildp = 3;
                            mh = 3.5f;
                            star = ChatColor.GOLD+"★★★★★★★☆☆☆";
                            break;
                        case 9:
                            ilsp = 30;
                            ilfp = 60;
                            ildp = 4;
                            mh = 4;
                            star = ChatColor.GOLD+"★★★★★★★★☆☆";
                            break;
                        default:
                            star = null;
                            break;
                    }
                    item.setLore(Arrays.asList(star,"",ChatColor.WHITE+"성공 확률: "+ChatColor.GREEN+ilsp+"%"
                            ,ChatColor.WHITE+"하락 확률: "+ChatColor.RED+ilfp+"%",ChatColor.WHITE+"파괴 확률: "+ChatColor.DARK_RED+ildp+"%"));
                    item.removeAttributeModifier(Attribute.GENERIC_MAX_HEALTH);
                    item.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH,new AttributeModifier(UUID.randomUUID(),"max.health",mh,
                            AttributeModifier.Operation.ADD_NUMBER,equipment));
                    item.setCustomModelData(posPoint-1);
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_DESTROY,0.1f,1);
                    return item;
                }
                else{
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE,0.1f,1);
                    return item;
                }
            }
        }
    }
}
