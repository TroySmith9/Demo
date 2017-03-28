package designParttern.decorator;

/**
 * Created by yiban on 2017/3/18.
 */
public interface Weapon {

    void shoot();

}

class Gun implements Weapon {

    Weapon weapon;

    @Override
    public void shoot() {
        System.out.println("一把枪");
    }

}

class Musket extends Gun {


    public Musket(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public void shoot() {
        System.out.println("增加火枪属性");
        weapon.shoot();
    }
}

class WaterGun implements Weapon{

    private Weapon weapon;

    public WaterGun(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public void shoot() {
        System.out.println("增加水枪属性");
        weapon.shoot();
    }
}


class MachineGun implements Weapon{

    private Weapon weapon;

    public MachineGun() {

    }

    public MachineGun(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public void shoot() {
        System.out.println("机关枪");
        weapon.shoot();
    }
}





