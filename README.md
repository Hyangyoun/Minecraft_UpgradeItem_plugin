Item# Item Upgrade Pluign
## 소개
Spigot 기반의 아이템 강화 플러그인 입니다. / ver 1.16.5
## 사용법
기본적으로 모루에서 에메랄드를 이용해 아이템 강화가 가능합니다.
## 플러그인 파일
아이템 추가가 필요 없을 시

	Minecraft_1.16.5_SpigotAPI\plugins

경로에 있는 파일을 사용 하시면 됩니다.
## 강화 가능 아이템 추가시
``` java
WeaponUpgrade(ItemMeta item,float attackSpeed, float attackDamage, Player player)
ArmorUpgrade(ItemMeta item,float armor, float armor_toughness, EquipmentSlot equipment, Player player)
```
메소드를 이용해 클릭이벤트 안에서 추가가 가능합니다.